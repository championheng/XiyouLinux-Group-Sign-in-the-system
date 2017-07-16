package model;

import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-7-14.
 */
public class DBUtils {
    public static String URL;
    public static String USERNAME;
    public static String PASSWD;
    public static String Driver;

    //加载配置文件
    private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

    //静态代码块在加载类时只执行一次
    static {
        //读取配置文件信息
        URL = rb.getString("jdbc.URL");
        USERNAME = rb.getString("jdbc.USERNAME");
        PASSWD = rb.getString("jdbc.PASSWD");
        Driver = rb.getString("jdbc.Driver");
        try {
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWD);
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("获取链接失败");
        }
        return conn;
    }

    /**
     * 关闭数据库链接
     */
    public static void close(ResultSet rs, Statement stat, Connection conn) {
        try {
            if(rs != null) {
                rs.close();
            }
            if(stat != null) {
                stat.close();
            }

            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
