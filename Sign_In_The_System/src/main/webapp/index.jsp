<%--
  Created by IntelliJ IDEA.
  User: hg_yi
  Date: 17-7-16
  Time: 下午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign_In_The_System</title>
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
    <!-- css files -->
    <link href="css/style.css" rel='stylesheet' type='text/css' media="all"/>
    <!-- /css files -->
</head>
<body>
<h1>XiyouLinux Group 签到系统</h1>
<div style="text-align: center; font-size:40px">
    <a href="<%=request.getContextPath()%>/signin_success.jsp">已签到</a>
    <a href="<%=request.getContextPath()%>/Not_SignIn.jsp">未签到</a>
</div>
<br>
<div class="log">
    <div class="content1">
        <h2>Sign In</h2>
        <form method="post" action="<%=request.getContextPath()%>/signin.do">
            <input type="text" name="studentid" value="STUDENT ID" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'STUDENT ID';}">
            <div class="button-row">
                <input type="submit" class="sign-in" value="Sign In">
                <input type="reset" class="reset" value="Reset">
                <div class="clear"></div>
            </div>
        </form>
    </div>
    <div class="content2">
        <h2>Sign Up</h2>
        <form method="post" action="<%=request.getContextPath()%>/register.do">
            <input type="text" name="username" value="USERNAME" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'USERNAME';}">
            <input type="text" name="studentid" value="STUDENT ID" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'STUDENT ID';}">
            <input type="submit" class="register" value="Register">
        </form>
    </div>
    <div class="clear"></div>
</div>
<div class="footer">
    <br>
    <p>Copyright © 2006 - 2017 西邮Linux兴趣小组 All Rights Reserved.<a target="_blank"></a>
    </p>
</div>

</body>
</html>
