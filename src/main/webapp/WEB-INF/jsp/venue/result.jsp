<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 05/03/2018
  Time: 12:47

  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Spring MVC表单处理</title>
</head>
<body>

<h2>提交的学生信息如下 - </h2>
<table>
    <tr>
        <td>id：</td>
        <td>${venue.id}</td>
    </tr>
    <tr>
        <td>密码：</td>
        <td>${venue.password}</td>
    </tr>
    <tr>
        <td>邮箱：</td>
        <td>${venue.seatDistribution}</td>
    </tr>
</table>
</body>
</html>