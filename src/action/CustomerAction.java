package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import domain.Customer;
import domain.Dict;
import domain.PageBean;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.CustomerService;
import utils.FastJsonUtil;
import utils.UploadUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 客户的控制层
 */
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    //手动new
    private Customer customer = new Customer();

    // 模型驱动 封装数据
    public Customer getModel() {
        return customer;
    }


    //提供service的成员属性 set方法
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    //属性驱动的方式
    //当前页默认是1
    private Integer pageCode = 1;

    public void setPageCode(Integer pageCode) {
        if (pageCode == null) {
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }

    //每页显示的条数
    private Integer pageSize = 2;

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //分页查询的方法
    public String findByPage() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        //拼接名称条件
        String cust_name = customer.getCust_name();
        if (cust_name != null && !cust_name.trim().isEmpty()) {
            //输入值了
            criteria.add(Restrictions.like("cust_name", "%" + cust_name + "%"));
        }

        //拼接级别条件
        Dict level = customer.getLevel();
        if (level != null && !level.getDict_id().trim().isEmpty()) {
            //选择了一个级别
            criteria.add(Restrictions.eq("level.dict_id", level.getDict_id()));
        }

        //拼接来源条件
        Dict source = customer.getSource();
        if (source != null && !source.getDict_id().trim().isEmpty()) {
            //选择了一个级别
            criteria.add(Restrictions.eq("source.dict_id", source.getDict_id()));
        }


        //查询
        PageBean<Customer> page = customerService.findByPage(pageCode, pageSize, criteria);
        //压栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("page", page);
        return "page";
    }


    //初始化到添加的页面
    public String initAddUI() {
        return "initAddUI";
    }


    //文件上传
    //要上传的文件
    private File upload;
    //文件的名称
    private String uploadFileName;
    //文件的MIME类型
    private String uploadContentType;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    //保存客户的方法
    public String save() throws IOException {
        //做文件上传
        if (uploadFileName != null) {
            //打印
            System.out.println("文件名称：" + uploadFileName);
            System.out.println("文件类型：" + uploadContentType);

            //处理名称
            String uuidname = UploadUtils.getUUIDName(uploadFileName);
            //上传到C:\\apache-tomcat-7.0.52\\webapps\\upload
            String path = "C:\\apache-tomcat-7.0.52\\webapps\\upload\\";

            //创建file对象
            File file = new File(path + uuidname);
            //简单方式
            FileUtils.copyFile(upload, file);

            //路径保存到数据库
            customer.setFilepath(path + uuidname);
        }

        System.out.println("web层：保存客户");
        //System.out.println(customer);
        customerService.save(customer);
        return "save";
    }


    //删除客户
    public String delete() {
        //获取客户信息 上传文件的路径
        customer = customerService.findById(customer.getCust_id());
        String filepath = customer.getFilepath();
        //删除客户
        customerService.delete(customer);
        //删除文件
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
        return "delete";
    }


    //跳转到初始化的修改页面
    public String initUpdate() {
        //默认customer压栈
        customer = customerService.findById(this.customer.getCust_id());

        return "initUpdate";
    }


    //修改客户的功能
    public String update() throws IOException {
        //判断上传了新的图片
        if (uploadFileName != null) {
            //删除旧的
            String oldfilepath = customer.getFilepath();
            if (oldfilepath != null && !oldfilepath.trim().isEmpty()) {
                //旧图片存在 删除
                File f = new File(oldfilepath);
                f.delete();
            }
            //上传新的
            //处理名称
            String uuidname = UploadUtils.getUUIDName(uploadFileName);
            String path = "C:\\apache-tomcat-7.0.52\\webapps\\upload\\";
            File file = new File(path + uuidname);
            FileUtils.copyFile(upload, file);
            //新路径更新到数据库
            customer.setFilepath(path + uuidname);
        }
        //更新客户信息
        customerService.update(customer);
        return "update";
    }

    /**
     * 查询所有客户
     *
     * @return
     */
    public String findAll() {
        List<Customer> list = customerService.findAll();
        //转换成json
        String jsonString = FastJsonUtil.toJSONString(list);
        HttpServletResponse response = ServletActionContext.getResponse();

        FastJsonUtil.write_json(response, jsonString);
        return NONE;
    }

    /**
     * 统计来源客户的数量
     *
     * @return
     */
    public String findBySource() {
        List<Object[]> list=customerService.findBySource();
        //压栈
        ValueStack vs = ActionContext.getContext().getValueStack();
        vs.set("list",list);
        return "findBySource";
    }


}
