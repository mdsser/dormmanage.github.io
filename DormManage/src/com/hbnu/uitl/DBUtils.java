package com.hbnu.uitl;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.omg.CORBA.PUBLIC_MEMBER;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
    private static DataSource dataSource;
    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = DBUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(resourceAsStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException
    {
        return dataSource.getConnection();
    }



    public static void close(ResultSet resultSet, Statement statement,Connection connection)
    {

        try {
            if(resultSet !=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
                if(statement!=null)
                {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
