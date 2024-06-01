package com.dech53.schedule.test;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc {
    private String url = "jdbc:mysql://localhost:3306/schedule_system";
    private String userName = "root";
    private String pwd = "root";

    public jdbc() throws SQLException {

    }
    @Test
    public void test() throws Exception{
        String sql = "select * from sys_user";
        Connection con = DriverManager.getConnection(url, userName, pwd);
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int uid = resultSet.getInt(1);
            String userName = resultSet.getString(2);
            String pwd = resultSet.getString(3);
            System.out.println(uid);
            System.out.println(userName);
            System.out.println(pwd);
        }
        preparedStatement.close();
        con.close();
    }

}
