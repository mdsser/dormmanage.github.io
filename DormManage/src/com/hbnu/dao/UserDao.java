package com.hbnu.dao;

import com.hbnu.pojo.User;

import java.util.List;

public interface UserDao {
    User findUserByStuCodeAndPwd(String stuCode, String password);

    List<User> findDormManager();

    List<User> findDormManager(String sql);

    Integer saveManager(User user);
    String findUserStuCodeMax();

    User findDormManagerById(int id);

    void updateUser(User user);
}
