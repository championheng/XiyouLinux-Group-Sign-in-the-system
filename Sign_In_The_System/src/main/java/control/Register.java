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

import static java.lang.System.out;

/**
 * Created by hg_yi on 17-7-14.
 */

@WebServlet("/register.do")
public class Register extends HttpServlet{
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    private final String Result_VIEW = "ResultPage.jsp";
    String resultInfo = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String username = new String(request.getParameter("username").getBytes(
                "iso-8859-1"), "utf-8");
        String studentId= new String(request.getParameter("studentid").getBytes(
                "iso-8859-1"), "utf-8");

        if (username == null || studentId == null || username.equals("USERNAME") || studentId.equals("STUDENT ID") || studentId.length() < 8) {
            resultInfo = "您输入的用户名或学号有错，请重新输入进行注册！";
            request.setAttribute("result", resultInfo);
            request.getRequestDispatcher(Result_VIEW).forward(request, response);
        } else {
            //先检查是否已经进行了注册
            boolean result = isRegister(studentId);
            if (result == true) {
                resultInfo = "您输入的学号已经被注册，请核对后重新输入！";
                request.setAttribute("result", resultInfo);
                request.getRequestDispatcher(Result_VIEW).forward(request, response);
            } else {
                resultInfo = "注册成功！";
                saveUserdata(username, studentId);
                request.setAttribute("result", resultInfo);
                request.getRequestDispatcher(Result_VIEW).forward(request, response);
            }
        }
    }

    //将信息存储到数据库中
    private void saveUserdata(String username, String studentId) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "INSERT INTO student_message(username, studentId, status) " +
                            "VALUES (?, ?, ?)");

            statement.setString(1, username);
            statement.setString(2, studentId);
            statement.setString(3, "false");
            statement.execute();

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //检查本学号是否已经进行了注册
    private boolean isRegister(String studentId) {
        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(
                    "SELECT studentId FROM student_message");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(1).equals(studentId)) {
                    return true;
                }
            }

            DBUtils.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
