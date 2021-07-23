package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid连接池工具类
 * */
public class JDBCUtils {
    private static DataSource ds;
    static {
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource((pro));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接
     * */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     * */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet!=null) {
            resultSet.close();
        }
        if (statement!=null) {
            statement.close();
        }
        if (connection!=null) {
            connection.close();
        }
    }

    /**
     * 获取连接池
     * */
    public static DataSource getDataSource() {
        return ds;
    }
}
