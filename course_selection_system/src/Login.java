import java.sql.*;
import java.util.Map;
import java.util.ResourceBundle;

public class Login {
    public static void main(String[] args) {
        //获取配置文件中的信息
        ResourceBundle ini = ResourceBundle.getBundle("ini");
        String driver = ini.getString("driver");
        String url = ini.getString("url");
        String username = ini.getString("username");
        String password = ini.getString("password");
        Connection connection = null;
        try {
            //注册驱动
            Class.forName(driver);
            //获取连接
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 登录是否成功
     *
     * @param userInfo     :用户信息
     * @param connection :数据库连接对象
     * @return true为成功，false为失败
     */
    public boolean isSuccess(Map<String, String> userInfo, Connection connection) {
        boolean result = false;

        //获取用户输入的账号和密码
        String inputName = userInfo.get("inputName");
        String inputPwd = userInfo.get("inputPwd");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取数据库预编译操作对象
            String sql = "select * from userInfo where loginName=? and loginPassword=?";
            preparedStatement = connection.prepareStatement(sql);
            //执行sql
            preparedStatement.setString(1, inputName);
            preparedStatement.setString(2, inputPwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet!=null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return result;
    }
}
