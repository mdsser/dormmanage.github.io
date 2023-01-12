package com.hbnu.service;


import com.hbnu.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByStuCodeAndPassword(String stuCode, String password);
    List<User> getDormManager();

    List<User> getDormManager(String searchType, String keyword);

    void saveManagerAndBuilds(User user, String[] dormBuildIds);

    User getDormManagerById(int parseInt);

    void updateManager(User user);
}
