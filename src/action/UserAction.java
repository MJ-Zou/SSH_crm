package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import domain.User;

import org.apache.struts2.ServletActionContext;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 用户的控制器
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

    private User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //注册功能
    public String regist() {
        userService.save(user);
        return LOGIN;
    }


    //通过登录名判断登录名是否已经存在
    public String checkCode() {
        User u = userService.checkCode(user.getUser_code());

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            if (u != null) {
                //登录名已经存在 不可注册
                writer.print("no");
            } else {
                //登录名不存在 可以注册
                writer.print("yes");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }

    //登陆功能
    public String login() {
        User existUser = userService.login(user);
        if (existUser == null) {
            //登录失败
            return LOGIN;
        } else {
            ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
            //登录成功
            return "loginOK";
        }
    }

    public String exist() {
        ServletActionContext.getRequest().getSession().removeAttribute("existUser");
        return LOGIN;
    }


}
