package action;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import domain.Customer;
import domain.Linkman;
import domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import service.LinkmanService;

public class LinkmanAction extends BaseAction implements ModelDriven<Linkman> {
    private Linkman linkman = new Linkman();

    @Override
    public Linkman getModel() {
        return linkman;
    }

    LinkmanService linkmanService;

    public void setLinkmanService(LinkmanService linkmanService) {
        this.linkmanService = linkmanService;
    }

    //分页查询
    public String findByPage() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);

        //获取联系人名称
        String lkm_name = linkman.getLkm_name();
        System.out.println(lkm_name);
        if (lkm_name != null && !lkm_name.trim().isEmpty()) {
            criteria.add(Restrictions.like("lkm_name", "%" + lkm_name + "%"));
        }

        //获取客户
        Customer c = linkman.getCustomer();
        if (c != null && c.getCust_id() != null) {
            //拼接查询条件
            criteria.add(Restrictions.eq("customer.cust_id", c.getCust_id()));
        }

        //调用业务层
        PageBean<Linkman> page = linkmanService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
        //压栈
        this.setVs("page", page);
        return "page";
    }
}
