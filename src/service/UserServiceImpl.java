package service;

import dao.UserDao;
import domain.User;
import org.springframework.transaction.annotation.Transactional;
import utils.MD5Utils;

/**
 * 用户的业务层
 */
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //通过登录名进行验证
    @Override
    public User checkCode(String user_code) {
        return userDao.checkCode(user_code);
    }

    //保存用户 密码加密
    @Override
    public void save(User user) {
        String pwd = user.getUser_password();
        //加密
        user.setUser_password(MD5Utils.md5(pwd));
        //设置用户默认状态
        user.setUser_state("1");

        userDao.save(user);
    }

    //用户名和密码做校验 密码加密
    @Override
    public User login(User user) {
        String pwd = user.getUser_password();
        //加密
        user.setUser_password(MD5Utils.md5(pwd));
        return userDao.login(user);
    }
}
