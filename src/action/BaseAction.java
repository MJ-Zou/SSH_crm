package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Action 的父类
 */
public class BaseAction extends ActionSupport {
    //属性驱动的方式
    //当前页默认是1
    private Integer pageCode = 1;
    //每页显示的条数
    private Integer pageSize = 2;


    public void setPageCode(Integer pageCode) {
        if (pageCode == null) {
            pageCode = 1;
        }
        this.pageCode = pageCode;
    }

    public Integer getPageCode() {
        return pageCode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    //调用值栈对象的set方法
    public void setVs(String key, Object obj) {
        ActionContext.getContext().getValueStack().set(key, obj);
    }
    //调用值栈对象的push方法
    public void pushVs(Object obj) {
        ActionContext.getContext().getValueStack().push(obj);
    }

}
