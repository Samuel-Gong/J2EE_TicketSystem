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
            <li><a href="${pageContext.request.contextPath}/manager/settlement">结算中心</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/statistics/venue">各场馆统计</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/statistics/member">会员统计</a></li>
            <li><a href="${pageContext.request.contextPath}/manager/statistics/finance">财务统计</a></li>
        </ul>

        <ul class="nav navbar-nav pull-right">
            <li><a href="${pageContext.request.contextPath}/manager/logout">登出</a>
            </li>
        </ul>
    </div>
</nav>
<!-- nav end -->
