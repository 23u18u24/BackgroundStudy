import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class IDUCop {
    public boolean insert(String table, Connection connection, Object o) {
        PreparedStatement preparedStatement = null;
        try {
            //执行插入的sql语句
            String sql = "insert into ?(?) values(?)";
            preparedStatement = connection.prepareStatement(sql);
            int count = preparedStatement.executeUpdate();
            if (count==1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(String table, Connection connection, String op) {
        Statement statement = null;
        try {
            //执行插入的sql语句
            String sql = "delete from " + table + " where " + op;
            statement = connection.createStatement();
            int count = statement.executeUpdate(sql);
            if (count==1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(String table, Connection connection, String op) {
        Statement statement = null;
        try {
            //执行插入的sql语句
            String sql = "update " + table + "set " + op;
            statement = connection.createStatement();
            int count = statement.executeUpdate(sql);
            if (count==1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
