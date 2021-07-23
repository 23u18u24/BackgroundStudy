import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest2 {
    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = null;
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //2.获取连接
            String url = "jdbc:mysql://localhost:3306/db2";
            String username = "root";
            String password = "wuruxin";
            connection = DriverManager.getConnection(url, username, password);
            //3.获取数据库创建操作对象
            statement = connection.createStatement();
            //4.执行sql语句
            //jdbc的sql语句不需要写分号
            String sql = "delete from dept where deptno=50";
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
