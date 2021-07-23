import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCTest7 {
    public static void main(String[] args) {
        ResourceBundle ini = ResourceBundle.getBundle("ini");
        String driver = ini.getString("driver");
        String username = ini.getString("username");
        String password = ini.getString("password");
        String url = ini.getString("url");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            String sql = "delete from emp1 where ename=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "SMITH");
            int count = preparedStatement.executeUpdate();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement!=null) {
                try {
                    preparedStatement.close();
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
}
