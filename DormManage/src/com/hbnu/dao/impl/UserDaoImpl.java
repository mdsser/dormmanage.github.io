package com.hbnu.dao.impl;

import com.hbnu.dao.UserDao;
import com.hbnu.pojo.User;
import com.hbnu.uitl.DBUtils;
import sun.dc.pr.PRError;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User findUserByStuCodeAndPwd(String stuCode, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

        //2、获取数据库操作对象
        String sql = "select * from tb_user where stu_code = ? and password = ? and disabled = 0";
        preparedStatement = connection.prepareStatement(sql);

        //3、执行SQL语句
        preparedStatement.setString(1, stuCode);
        preparedStatement.setString(2,password);
        resultSet = preparedStatement.executeQuery();
        //4、处理查询结果
        while(resultSet.next())
        {
            User user = new User();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
            user.setStuCode(resultSet.getString("stu_code"));
            user.setDormcode(resultSet.getString("dorm_code"));
            user.setSex(resultSet.getString("sex"));
            user.setTel(resultSet.getString("tel"));
            user.setDormBuildId(resultSet.getInt("dormBuildId"));
            user.setRoleId(resultSet.getInt("role_id"));
            user.setCreatUserId(resultSet.getInt("create_user_id"));
            user.setDisabled(resultSet.getInt("disabled"));
            return user;
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public List<User> findDormManager() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> users = new ArrayList<>();

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql = "select * from tb_user where role_id=1 ";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            //4、处理查询结果
            while(resultSet.next())
            {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setStuCode(resultSet.getString("stu_code"));
                user.setDormcode(resultSet.getString("dorm_code"));
                user.setSex(resultSet.getString("sex"));
                user.setTel(resultSet.getString("tel"));
                user.setDormBuildId(resultSet.getInt("dormBuildId"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setCreatUserId(resultSet.getInt("create_user_id"));
                user.setDisabled(resultSet.getInt("disabled"));
                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }


    @Override
    public List<User> findDormManager(String sql) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<User> users = new ArrayList<>();

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            //4、处理查询结果
            while(resultSet.next())
            {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setStuCode(resultSet.getString("stu_code"));
                user.setDormcode(resultSet.getString("dorm_code"));
                user.setSex(resultSet.getString("sex"));
                user.setTel(resultSet.getString("tel"));
                user.setDormBuildId(resultSet.getInt("dormBuildId"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setCreatUserId(resultSet.getInt("create_user_id"));
                user.setDisabled(resultSet.getInt("disabled"));
                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public Integer saveManager(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql="insert into tb_user(name,password,stu_code,sex,tel,role_id,create_user_id,disabled) value (?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getStuCode());
            preparedStatement.setString(4,user.getSex());
            preparedStatement.setString(5,user.getTel());
            preparedStatement.setInt(6,user.getRoleId());
            preparedStatement.setInt(7,user.getCreatUserId());
            preparedStatement.setInt(8,user.getDisabled());
            preparedStatement.executeUpdate();
            resultSet= preparedStatement.getGeneratedKeys();    //获取主键
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }


    @Override
    public String findUserStuCodeMax(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql="select LPAD(IFNULL(Max(stu_code),0000)+1,4,0) from tb_user";
            preparedStatement=connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public User findDormManagerById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql="select * from tb_user where id =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            //4、处理查询结果
            while(resultSet.next())
            {
                User user = new User();

                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setStuCode(resultSet.getString("stu_code"));
                user.setDormcode(resultSet.getString("dorm_code"));
                user.setSex(resultSet.getString("sex"));
                user.setTel(resultSet.getString("tel"));
                user.setDormBuildId(resultSet.getInt("dormBuildId"));
                user.setRoleId(resultSet.getInt("role_id"));
                user.setCreatUserId(resultSet.getInt("create_user_id"));
                user.setDisabled(resultSet.getInt("disabled"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(resultSet,preparedStatement,connection);
        }

        return null;
    }

    @Override
    public void updateUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql="update tb_user set name=?,password=?,sex=?,tel=?,disabled=? where id =? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getSex());
            preparedStatement.setString(4,user.getTel());
            preparedStatement.setInt(5,user.getDisabled());
            preparedStatement.setInt(6,user.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(null,preparedStatement,connection);
        }

    }
}
