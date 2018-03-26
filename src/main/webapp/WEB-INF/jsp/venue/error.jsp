<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 11:46
  
  Description: 场馆错误界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true" %>
<html>
<head>
    <title>错误了</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <h3 class="center-block">${errorMsg}</h3>
            <a href="${pageContext.request.contextPath}/index">返回主页</a>
        </div>
    </div>
</div>
</body>
</html>
