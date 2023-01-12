package com.hbnu.service.impl;


import com.hbnu.dao.DormBuildDao;
import com.hbnu.dao.impl.DormBuildDaoImpl;
import com.hbnu.dao.impl.UserDaoImpl;
import com.hbnu.dao.UserDao;
import com.hbnu.pojo.DormBuild;
import com.hbnu.pojo.User;
import com.hbnu.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    DormBuildDao dormBuildDao = new DormBuildDaoImpl();

    @Override
    public User findUserByStuCodeAndPassword(String stuCode, String password) {
        return userDao.findUserByStuCodeAndPwd(stuCode,password);
    }

    @Override
    public List<User> getDormManager() {
        //1、获取宿舍楼管理员信息
        List<User> dormManagers = userDao.findDormManager();
        for (User dormManager : dormManagers) {
            //2、获取该宿舍管理员管理的宿舍楼信息
            List<DormBuild> dormBuilds = dormBuildDao.findDormBuildsByUserId(dormManager.getId());
            dormManager.setDormBuilds(dormBuilds);

        }
        return dormManagers;
    }

    @Override
    public List<User> getDormManager(String searchType, String keyword) {
        StringBuffer sql = new StringBuffer("select * from tb_user where role_id=1 ");
        if(keyword!=null && !keyword.equals("")){
            if("name".equals(searchType)){
                sql.append(" and name like '%"+keyword.trim()+"%'");
            }else if("sex".equals(searchType)){
                sql.append(" and sex like '%"+keyword.trim()+"%'");
            }else if("tel".equals(searchType)){
                sql.append(" and tel like '%"+keyword.trim()+"%'");
            }
        }
        System.out.println(sql.toString());

        List<User> dormManagers = userDao.findDormManager(sql.toString());
        for (User dormManager : dormManagers) {
            //2、获取该宿舍管理员管理的宿舍楼信息
            List<DormBuild> dormBuilds = dormBuildDao.findDormBuildsByUserId(dormManager.getId());
            dormManager.setDormBuilds(dormBuilds);

        }
        return dormManagers;
    }

    @Override
    public void saveManagerAndBuilds(User user, String[] dormBuildIds) {
        //1、保存宿舍楼管理员信息
        String stuCodeMax = userDao.findUserStuCodeMax();
        user.setStuCode(stuCodeMax);
        Integer userId= userDao.saveManager(user);
        System.out.println("当前插入主键"+userId);

        //2、保存宿舍楼管理员管理的宿舍楼信息
        dormBuildDao.saveManagerAndBuilds(userId,dormBuildIds);
    }

    @Override
    public User getDormManagerById(int id) {
        return userDao.findDormManagerById(id);
    }

    @Override
    public void updateManager(User user) {
        userDao.updateUser(user);
    }
}
