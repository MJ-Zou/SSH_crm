package dao;

import domain.User;

public interface UserDao {
    User checkCode(String user_code);

    void save(User user);

    User login(User user);
}
