package com.hbnu.dao.impl;

import com.hbnu.dao.DormBuildDao;
import com.hbnu.pojo.DormBuild;
import com.hbnu.pojo.User;
import com.hbnu.uitl.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormBuildDaoImpl implements DormBuildDao {

    @Override
    public List<DormBuild> findDormBuildsByUserId(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<DormBuild> dormBuilds = new ArrayList<>();

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql = "SELECT tb_dormbuild.* from tb_manage_dormbuild left join tb_dormbuild on tb_dormbuild.id=tb_manage_dormbuild.dormBuild_id where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            //4、处理查询结果
            while (resultSet.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setId(resultSet.getInt(1));
                dormBuild.setName(resultSet.getString("name"));
                dormBuild.setRemark(resultSet.getString("remark"));
                dormBuild.setDisabled(resultSet.getInt("disabled"));
                dormBuilds.add(dormBuild);

            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }

        return null;
    }

    @Override
    public List<DormBuild> findAllDormBuild() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<DormBuild> dormBuilds = new ArrayList<>();

        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql = "SELECT *  from tb_dormbuild";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();
            //4、处理查询结果
            while (resultSet.next()) {
                DormBuild dormBuild = new DormBuild();
                dormBuild.setId(resultSet.getInt(1));
                dormBuild.setName(resultSet.getString("name"));
                dormBuild.setRemark(resultSet.getString("remark"));
                dormBuild.setDisabled(resultSet.getInt("disabled"));
                dormBuilds.add(dormBuild);

            }
            return dormBuilds;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(resultSet, preparedStatement, connection);
        }

        return null;
    }

    @Override
    public void saveManagerAndBuilds(Integer userId, String[] dormBuildIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql = "insert into tb_manage_dormbuild(user_id,dormBuild_id) value (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (String dormBuildId : dormBuildIds) {
                preparedStatement.setInt(1,userId);
                preparedStatement.setInt(2,Integer.parseInt(dormBuildId));
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }

    @Override
    public void deleteDormBuildsByUserId(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        //1、注册驱动和获取连接
        try {
            connection = DBUtils.getConnection();

            //2、获取数据库操作对象
            String sql = "delete from tb_manage_dormbuild where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(null, preparedStatement, connection);
        }
    }
}
