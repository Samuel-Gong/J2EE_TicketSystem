<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 22/03/2018
  Time: 00:32

  Description: 未支付订单界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>未支付订单</title>
    <!-- Normalize -->
    <link href="../../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../../css/footer.css" rel="stylesheet">
    <!-- order -->
    <link href="../../../../css/member/order.css" rel="stylesheet">
</head>
<body>

<!-- nav begin -->
<%@include file="../nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-2 col-md-offset-1">
            <%@include file="../../../../html/member/side-nav.html" %>
        </div>
        <div id="unpaid-container" class="col-md-8">
            <div id="order-container" class="col-md-offset-1 col-md-10">
                <c:forEach items="${unpaidOrders}" var="detailOrder">
                <c:set var="order" value="${detailOrder.order}"/>
                <c:set var="venuePlan" value="${detailOrder.venuePlan}"/>
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
                                                <c:forEach items="${order.venuePlanSeats}"
                                                           var="selectedSeat">
                                        <span>
                                            <c:out value="${selectedSeat.row}排${selectedSeat.column}座"/>
                                        </span>
                                                </c:forEach>
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
                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-3">
                                    <!-- 保存订单编号 -->
                                    <input type="text" value="${order.orderId}" hidden>
                                    <button type="button" class="btn btn-primary center-block pay-btn">去支付
                                    </button>
                                </div>
                                <div class="col-md-offset-1 col-md-3">
                                    <!-- 保存订单编号 -->
                                    <input type="text" value="${order.orderId}" hidden>
                                    <button type="button" class="btn btn-primary center-block cancel-btn">取消支付
                                    </button>
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
<script src="../../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../../js/bootstrap.min.js"></script>
<!-- date format -->
<script src="../../../../js/date-format.js"></script>

<script>

    $("#unpaid-li").addClass("active");

    //去支付按钮监听
    $("#order-container").on("click", ".pay-btn", function () {
        let orderId = $(this).prev().val();
        console.log(orderId);
        $(location).attr("href", "/member/pay/" + orderId);
    });

    //取消支付按钮监听
    $("#order-container").on("click", ".cancel-btn", function () {
        let orderId = $(this).prev().val();
        console.log(orderId);
        $(location).attr("href", "/member/pay/" + orderId);
    });

</script>
</body>
</html>
