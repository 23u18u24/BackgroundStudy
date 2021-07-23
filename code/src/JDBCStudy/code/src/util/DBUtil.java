package util;

import java.sql.*;
import java.util.ResourceBundle;

/*
* JDBC工具类
* 简化JDBC编程
* */
public class DBUtil {
    private static final String url;
    private static final String username;
    private static final String password;

    public DBUtil() {}
    static {
        //获取配置文件
        ResourceBundle ini = ResourceBundle.getBundle("ini");
        String driver = ini.getString("driver");
        url = ini.getString("url");
        username = ini.getString("username");
        password = ini.getString("password");
        //获取驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     *
     * @return 返回连接对象
     * @throws SQLException
     * */
    public static Connection getConnection() throws SQLException {
        //获取连接
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 关闭资源
     * @param connection 连接对象
     * @param statement 数据库操作对象
     * @param resultSet 结果集对象
     * */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet!=null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement!=null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection!=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
