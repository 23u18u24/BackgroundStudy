package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest8 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
            connection = DBUtil.getConnection();
            //获取数据库操作对象
            String sql = "select ename from emp where ename like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"_A%");
            //执行sql语句
            resultSet = preparedStatement.executeQuery();
            //对结果集进行操作
            while (resultSet.next()) {
                System.out.println(resultSet.getString("ename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }
}
