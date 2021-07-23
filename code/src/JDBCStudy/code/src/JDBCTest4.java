import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest4 {
    public static void main(String[] args) {
        //从ini配置文件中获取数据
        ResourceBundle ini = ResourceBundle.getBundle("ini");
        String driver = ini.getString("driver");
        String url = ini.getString("url");
        String username = ini.getString("username");
        String password = ini.getString("password");
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName(driver);
            //2.获取连接
            connection = DriverManager.getConnection(url, username, password);
            //3.获取数据库对象
            statement = connection.createStatement();
            //4.执行sql语句
            String sql = "select empno,ename,sal from emp";
            rs = statement.executeQuery(sql);
            //5.处理查询结果集
            while (rs.next()) {
                //无论数据库中数据类型是什么，都以String类型返回
                //JDBC的下标从1开始，表示列数
                //getString参数也可以跟列名（且是查询结果的列名）
                String empno = rs.getString(1);
                String ename = rs.getString("ename");
                String sal = rs.getString(3);
                System.out.println(empno + "\t" + ename + "\t" + sal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
