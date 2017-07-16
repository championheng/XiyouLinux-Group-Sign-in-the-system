<%@ page import="model.DBUtils" %>
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
    <title>Not_SignIn</title>
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
            width:500px;
            height: 100px;
            margin:0 auto;
        }
        .box{
            width: auto;
            font-size: 200px;
        }
        .btn{
            width:100px;
            margin:0 auto;
            display: block;
        }

        table{
            margin-left: auto;
            margin-right: auto
        }

        .footer {
            margin-top:3%;
            padding:50px 0;
            text-align:center;
        }

        .footer a,a:active {
            color:#fff;
            text-decoration:none;
            transition: all 0.5s ease-in-out;
        }
        .footer a:hover {
            color:#c0392b;
            transition: all 0.5s ease-in-out;
        }
    </style>
</head>
<body>
<div class="test">
    <h1 class="text-center">Xiyou Linux Group 签到系统</h1>
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
                        "SELECT * FROM student_message");
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    if (resultSet.getString(4).equals("false")) {
        %>

        <tr class="success">
            <td><%= i%></td>
            <td><%= resultSet.getString(3)%></td>
            <td><%= resultSet.getString(2)%></td>
            <td>未签到</td>
        </tr>

        <%
                        i++;
                    }
                }

                DBUtils.close(resultSet, statement, connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
        </tbody>
    </table>
<div class="box footer">
    <br>
    <a href="<%=request.getContextPath()%>/index.jsp">
        <button type="button" class="btn">还没有签到?</button>
    </a>
</div>>
</body>
</html>