package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * 用户的拦截器 判断用户是否已经登陆
 * 已登录 执行下一拦截器
 * 没登录 返回登录界面 login 和regist方法不拦截
 * 继承指定拦截器
 */
public class UserInterceptor extends MethodFilterInterceptor {

    /**
     * 拦截目标action的方法
     */
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        //获取session
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if (user==null){
            return "login";
        }
        //执行下一个拦截器
        return invocation.invoke();
    }
}
