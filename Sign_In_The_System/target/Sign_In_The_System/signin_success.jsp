<%@ page import="model.DBUtils" %>
<%@ page import="control.SignIn" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %><%--
  Created by IntelliJ IDEA.
  User: hg_yi
  Date: 17-7-15
  Time: 下午12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success_SignIn</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/images/bitbug_favicon.ico" />
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);
    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <link href="css/tablestyle.css" rel="stylesheet">
    <style type="text/css">
        *{
            margin:0px;
            padding:0px;
        }

        th,tr{
            text-align: center;
        }

        .col-md-71{
            margin-top: 100px;
            width:700px;
        }

        .sp1{
            display:inline-block;
            margin-right:20px;
            font-size: 20px;
        }

        .test{
            text-align: center;
            vertical-align:middle;
            width:500px;
            height: 100px;
            margin:0 auto
        }
        table{
            margin-left: auto;
            margin-right: auto
        }
    </style>
</head>
<body>
<div class="test" >
    <h1>Xiyou Linux Group 签到系统</h1>
</div>
<br >
<br >
    <table style="text-align: center; font-size: 25px; width: 1000px " class="table table-bordered table-hover table-condensed" >
        <thead>
        <tr>
            <th>编号</th>
            <th>学号</th>
            <th>姓名</th>
            <th>签到状态</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                int i = 1;
                Connection connection = DBUtils.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM student_SignIN");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {

        %>

        <tr class="success">
            <td><%= i%></td>
            <td><%= resultSet.getString(3)%></td>
            <td><%= resultSet.getString(2)%></td>
            <td><%= resultSet.getString(4)%></td>
        </tr>

        <%
                    i++;
                }
                DBUtils.close(resultSet, statement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>

        </tbody>
    </table>
</body>
</html>