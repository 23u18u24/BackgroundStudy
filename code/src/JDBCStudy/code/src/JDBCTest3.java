import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCTest3 {
    public static void main(String[] args) {
        ResourceBundle ini = ResourceBundle.getBundle("ini");
        String url = ini.getString("url");
        String username = ini.getString("username");
        String password = ini.getString("password");
        String driver = ini.getString("driver");
        Statement statement = null;
        Connection connection = null;
        try {
            //1.注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //2.获取连接
            connection = DriverManager.getConnection(url, username, password);
            //3.获取数据库操作对象
            statement = connection.createStatement();
            //4.执行sql语句
            String sql = "insert into dept values(50,'人事部','北京')";
            int count = statement.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            try {
                if (statement!=null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection!=null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
