package action;

import com.opensymphony.xwork2.ModelDriven;
import domain.PageBean;
import domain.User;
import domain.Visit;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.VisitService;

import javax.annotation.Resource;

/**
 * 客户拜访的控制器
 */
@Controller(value = "visitAction")
@Scope(value = "prototype")
public class VisitAction extends BaseAction implements ModelDriven<Visit> {
    Visit visit = new Visit();

    @Override
    public Visit getModel() {
        return visit;
    }

    @Resource(name = "visitService")
    private VisitService visitService;

    private String beginDate;
    private String endDate;

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * 分页查询
     * 客户拜访记录 根据用户主键查询
     *
     * @return
     */
    public String findByPage() {
        //获取当前登录的用户
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        //判断
        if (user == null) {
            //配置全局结果跳转
            return LOGIN;
        }
        //查询该用户下所有的拜访记录
        DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
        //添加该用户用户查询条件
        criteria.add(Restrictions.eq("user.user_id", user.getUser_id()));

        //添加日期限制条件
        if (beginDate != null && !beginDate.trim().isEmpty()) {
            criteria.add(Restrictions.ge("visit_time",beginDate));
        }
        if (endDate != null && !endDate.trim().isEmpty()) {
            criteria.add(Restrictions.le("visit_time",endDate));
        }


        //分页查询
        PageBean<Visit> page = visitService.findByPage(this.getPageCode(), this.getPageSize(), criteria);
        this.setVs("page", page);

        return "page";
    }

    //保存客户拜访记录
    public String save() {
        //获取当前登录的用户
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        //判断
        if (user == null) {
            //配置全局结果跳转
            return LOGIN;
        }

        visit.setUser(user);
        visitService.save(visit);
        return "save";
    }


}
