<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 16:13
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!-- nav begin -->
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <!-- 导航栏头 -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">小麦网</a>
        </div>

        <!-- 导航内容 -->
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/manager/audit">审批</a></li>
            <li><a href="#">结算</a></li>
            <li><a href="#">统计信息</a></li>
        </ul>

        <ul class="nav navbar-nav pull-right">
            <li><a href="${pageContext.request.contextPath}/manager/logout">登出</a>
            </li>
        </ul>
    </div>
</nav>
<!-- nav end -->
