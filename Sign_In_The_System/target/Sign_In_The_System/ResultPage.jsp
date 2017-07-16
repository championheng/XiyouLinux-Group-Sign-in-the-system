<%--
  Created by IntelliJ IDEA.
  User: hg_yi
  Date: 17-7-14
  Time: 下午5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>ResultPage</title>
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
<div>
    <br> <br>
    <h1><%=request.getAttribute("result")%>
        <br> <br> <br> <br> <br>
        <a href="<%=request.getContextPath()%>/index.jsp">点击返回签到页面!</a>
    </h1>
</div>

<div class="footer">
    <p>Copyright © 2006 - 2017 西邮Linux兴趣小组 All Rights Reserved.<a target="_blank"></a>
    </p>
</div>
</body>
</html>