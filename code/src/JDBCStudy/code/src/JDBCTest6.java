import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class JDBCTest6 {
    public static void main(String[] args) {
        // 初始化一个界面
        Map<String, String> userInfo = initUI();
        // 验证用户名和密码
        boolean loginSuccess = login(userInfo);
        // 最后输出结果w
        System.out.println(loginSuccess ? "登录成功":"登录失败");
    }
    /**
     * 用户登录
     * @param userInfo 用户登录信息
     * @return false表示失败，true表示成功
     * */
    private static boolean login(Map<String, String> userInfo) {
        Connection connection = null;
        PreparedStatement prestatement = null;
        ResultSet resultSet = null;
        boolean result = false;
        String loginName = userInfo.get("loginName");
        String loginPwd = userInfo.get("loginPwd");
        try {
            //1.注册驱动
            ResourceBundle ini = ResourceBundle.getBundle("ini");
            String driver = ini.getString("driver");
            Class.forName(driver);
            //2.获取连接
            String url = ini.getString("url");
            String username = ini.getString("username");
            String password = ini.getString("password");
            connection = DriverManager.getConnection(url, username, password);
            //3.获取预编译的数据库操作对象
            String sql = "select loginName,loginPwd from t_user where loginName = ? and loginPwd = ?";//?为占位符，使只能填写值
            prestatement = connection.prepareStatement(sql);
            //给占位符?传值（下标从1开始）
            prestatement.setString(1, loginName);
            prestatement.setString(2, loginPwd);
            //4.执行sql语句
            resultSet = prestatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6.释放资源
            try {
                if (resultSet!=null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (prestatement!=null) {
                    prestatement.close();
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
        return result;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和密码等登录信息
     * */
    private static Map<String,String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.print("用户名：");
        String loginName = s.nextLine();
        System.out.print("密码：");
        String loginPwd = s.nextLine();
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("loginName", loginName);
        userInfo.put("loginPwd", loginPwd);
        return userInfo;
    }
}
