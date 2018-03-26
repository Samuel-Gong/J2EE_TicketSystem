<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 21/03/2018
  Time: 10:23
  
  Description:  会员界面的导航栏
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!-- nav begin -->
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <!-- 导航栏头 -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">小麦网</a>
        </div>

        <p id="user-mail" class="navbar-text">${sessionScope.mail}</p>

        <!-- 导航内容 -->
        <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    我的小麦
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/member/myInfo">个人信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/member/order/booked">我的订单</a></li>
                    <li><a href="#">我的优惠券</a></li>
                </ul>
            </li>
        </ul>

        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/member/scanShow">近期演出</a>
            </li>
        </ul>

        <ul class="nav navbar-nav pull-right">
            <!-- todo logout-->
            <li id="member-logout">
                <a href="${pageContext.request.contextPath}/member/logout">登出</a>
            </li>
        </ul>
    </div>
</nav>
<!-- nav end -->
