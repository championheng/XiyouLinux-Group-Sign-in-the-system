package control;

import model.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hg_yi on 17-7-14.
 */

@WebServlet("/signin.do")
public class SignIn extends HttpServlet {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private final String SUCCESS_VIEW = "signin_success.jsp";
    private final String ERROR_VIEW = "ResultPage.jsp";

    String resultInfo = null;
    String username = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentid");

        //查询数据库(检查本学号是否注册过)
        boolean isExist = queryId(studentId);
        if (isExist == true) {
            //获取签到的当前时间
            String currentTime = getCurrentTime();
            //判断是否今天重复签到
            boolean isSignIn = queryTime(studentId, currentTime);
            if (isSignIn == true) {
                resultInfo = "你今天已经签过到了哦，明天再来吧！";
                request.setAttribute("result", resultInfo);
                request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
            } else {
                //将签到信息保存到数据库
                savaDataBase(studentId, currentTime);
                //修改注册表中用户的状态
                alterDataBase(studentId);
                request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response);
            }

        } else {
            resultInfo = "当前学号尚未注册，请返回签到页面进行注册之后进行签到！";
            request.setAttribute("result", resultInfo);
            request.getRequestDispatcher(ERROR_VIEW).forward(request, response);
        }
    }

    private boolean queryId(String studentId) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "SELECT * FROM student_message");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(3).equals(studentId)) {
                    username = resultSet.getString(2);
                    return true;
                }
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getCurrentTime() {
        Calendar now = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String nowTime = sdf.format(now.getTime());

        return nowTime;
    }

    private boolean queryTime(String studentId, String currentTime) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "SELECT * FROM student_SignIN");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(3).equals(studentId)) {
                    String time = resultSet.getString(4);
                    //将时间进行格式统一，只比较年、月、日
                    String newCurrentTime = currentTime.substring(0, 10);
                    String newTime = time.substring(0, 10);

                    if (newCurrentTime.equals(newTime)) {
                        return true;
                    }
                }
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void savaDataBase(String studentId, String currentTime) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO student_SignIN(username, studentId, data) " +
                            "VALUES (?, ?, ?)");

            statement.setString(1, username);
            statement.setString(2, studentId);
            statement.setString(3, currentTime);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void alterDataBase(String id) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement("UPDATE student_message " +
                    "SET status = 'true' WHERE studentId = " + id);
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}