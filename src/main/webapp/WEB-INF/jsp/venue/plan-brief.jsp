<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 19/03/2018
  Time: 21:03
  
  Description: 查看计划简介界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>查看计划</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../css/footer.css" rel="stylesheet">

    <style>
        .panel {
            margin-top: 5%;
        }
    </style>
</head>
<body>
<%-- nav begin --%>
<%@include file="../../../html/venue/nav.html" %>
<%-- nav end --%>

<div id="plans-container" class="container">
    <div class="row">
        <h2 class="text-center">近期计划</h2>
        <c:forEach items="${venuePlans}" var="plan">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="panel panel-default">
                        <div class='panel-heading'>
                            <h3 class='panel-title'><c:out value="${plan.description}"/></h3>
                        </div>
                        <div class="panel-body">
                            <input type="text" value="<c:out value="${plan.venuePlanId}"/>" hidden>
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
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../../../html/footer.html" %>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
</body>
</html>
