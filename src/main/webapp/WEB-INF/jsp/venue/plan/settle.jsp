<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 19/03/2018
  Time: 21:03

  Description: 已结算场馆计划简介
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>已结算计划</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">

    <style>
        .panel {
            margin-top: 5%;
        }
    </style>
</head>
<body>
<%-- nav begin --%>
<%@include file="../nav.jsp" %>
<%-- nav end --%>

<div id="plans-container" class="container">
    <div class="row">
        <h2 class="text-center">已结算计划</h2>
        <c:forEach items="${venuePlans}" var="plan">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="panel panel-default">
                        <div class='panel-heading'>
                            <h3 class='panel-title'><c:out value="${plan.description}"/></h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">演出地点：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.city} ${plan.venueName}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-offset-1 col-md-2 control-label">演出类型：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.showType}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">开始时间：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.begin}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-offset-1 col-md-2 control-label">结束时间：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.end}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">票价收入：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.totalIncome}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-offset-1 col-md-2 control-label">结算收入：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${plan.actualIncome}"/>
                                        </p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../../../../html/footer.html" %>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>
