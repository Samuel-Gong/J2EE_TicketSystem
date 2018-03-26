<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 25/03/2018
  Time: 16:18
  
  Description: 取消支付的订单列表
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>已取消支付订单</title>
    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
    <!-- order -->
    <link href="${pageContext.request.contextPath}/css/member/order.css" rel="stylesheet">
</head>
<body>

<!-- nav begin -->
<%@include file="../nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-1">
            <%@include file="../side-nav.jsp" %>
        </div>
        <div id="cancel-container" class="col-md-8">
            <c:forEach items="${cancelOrders}" var="detailOrder">
                <c:set var="order" value="${detailOrder.order}"/>
                <c:set var="venuePlan" value="${detailOrder.venuePlan}"/>
                <div class="col-md-offset-1 col-md-10 order-container">
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <c:out value="${venuePlan.description}"/>
                            </h4>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <label class="col-md-offset-1 col-md-2 control-label">演出城市:</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${venuePlan.city} ${venuePlan.venueName}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-2 control-label">演出类型:</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${venuePlan.showType}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${venuePlan.begin}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-2 control-label">结束时间:</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${venuePlan.end}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">座位：</label>
                                    <div class="col-md-9">
                                        <p class="form-control-static">
                                            <c:choose>
                                                <c:when test="${order.seatSettled}">
                                                    <span class="form-control-static">
                                                        <c:out value="${order.bookedSeatStr}"/>
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="form-control-static">还未配票</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">总票价:</label>
                                    <div class="col-md-2">
                                        <p class="form-control-static">
                                            <c:out value="${order.price}"/>元
                                        </p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- date format -->
<script src="${pageContext.request.contextPath}/js/date-format.js"></script>

<script>
    //左侧导航栏active
    $("#cancel-li").addClass("active");
</script>
</body>
</html>
