import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest1 {
    public static void main(String[] args) {
        Statement statement = null;
        Connection connection = null;
        //编程六步
        try {
            //1.注册驱动
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //2.获取连接
            //url：统一资源定位符（网络中某个资源的绝对路径）
            //url=协议+端口+IP+资源名
            String url = "jdbc:mysql://localhost:3306/db2"; // 格式：jdbc:mysql://IP地址:端口号/数据库名
            String user = "root";
            String password = "wuruxin";
            connection = DriverManager.getConnection(url,user,password);
            //3.获取数据库操作对象（statement专门执行sql语句的）
            statement = connection.createStatement();
            //4.执行sql语句
            String sql = "delete from dept where deptno=60";
            //专门执行DML语句(insert,delete,update)
            //返回值是影响数据库的记录条数
            int count = statement.executeUpdate(sql);
            System.out.println(count);
            //5.处理数据库结果集
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            //为了保证资源一定释放，故在finally中释放资源，并且遵循从小到大以此关闭
            //要分别对其try...catch
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
