<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 16:17
  
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
            <li id="venue-info-li"><a href="${pageContext.request.contextPath}/venue/infoView">场馆信息</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    计划
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/venue/plan/coming">未结束计划</a></li>
                    <li><a href="${pageContext.request.contextPath}/venue/plan/unsettle">未结算计划</a></li>
                    <li><a href="${pageContext.request.contextPath}/venue/plan/settle">已结算计划</a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/venue/release-plan">发布计划</a></li>
            <li id="venue-statistic-li"><a href="${pageContext.request.contextPath}/venue/statistics">场馆统计</a></li>
        </ul>

        <ul class="nav navbar-nav pull-right">
            <li id="venue-logout">
                <a href="${pageContext.request.contextPath}/venue/logout">
                    登出
                </a>
            </li>
        </ul>
    </div>
</nav>
<!-- nav end -->
