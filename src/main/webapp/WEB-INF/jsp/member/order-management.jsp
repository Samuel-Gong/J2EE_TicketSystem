<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 22/03/2018
  Time: 00:32

  Description: 会员订单界面，显示未支付、已预订、已配票、已消费
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>订单管理</title>
    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../css/footer.css" rel="stylesheet">
    <style>
        .panel {
            margin-top: 20px;
        }

        .order-container {
            height: 600px;
            overflow: scroll;
        }
    </style>
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#unpaid" data-toggle="tab">未支付</a></li>
                <li><a href="#not-with-tickets" data-toggle="tab">未配票</a></li>
                <li><a href="#already-with-tickets" data-toggle="tab">已配票</a></li>
                <li><a href="#consumed" data-toggle="tab">已消费</a></li>
                <li><a href="#retreat" data-toggle="tab">已消费</a></li>
            </ul>
            <div id="all-orders" class="tab-content">
                <div id="unpaid" class="tab-pane fade in active">
                    <div id="unpaid-container">
                        <c:forEach items="${unpaidOrders}" var="unpaidOrder">
                            <c:set var="order" value="${unpaidOrder.order}"/>
                            <c:set var="venuePlan" value="${unpaidOrder.venuePlan}"/>
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
                                                        <fmt:formatDate value="${venuePlan.begin}"/>
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
                                                <label class="col-md-3 control-label">总票价:</label>
                                                <div class="col-md-2">
                                                    <p class="form-control-static">
                                                        <c:out value="${order.price}"/>元
                                                    </p>
                                                </div>
                                                <label class="col-md-3 control-label">座位：</label>
                                                <div class="col-md-4">
                                                    <c:choose>
                                                        <c:when test="${order.seatSettled}">
                                                        <textarea class="form-control"
                                                                  style="overflow: scroll; resize: none;" disabled>
                                                        <c:forEach items="${order.venuePlanSeats}"
                                                                   var="selectedSeat">
                                                            <c:out value="${selectedSeat.row}排${selectedSeat.column}座 "/>
                                                        </c:forEach>
                                                        </textarea>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <p class="form-control-static">还未配票</p>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-offset-1 col-md-2 control-label">支付时间:</label>
                                                <div class="col-md-3">
                                                    <p class="form-control-static">
                                                        <span class="countdown-minute"></span>
                                                        <span>分</span>
                                                        <span class="countdown-second"></span>
                                                        <span>秒</span>
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-md-offset-2 col-md-3">
                                                    <button type="button" class="btn btn-primary center-block">去支付
                                                    </button>
                                                </div>
                                                <div class="col-md-offset-1 col-md-3">
                                                    <button type="button" class="btn btn-primary center-block">取消支付
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
                <div id="not-with-tickets" class="tab-pane fade">
                    <div id="not-with-tickets-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                        </div>
                    </div>
                </div>
                <div id="already-with-tickets" class="tab-pane fade">
                    <div id="already-with-tickets-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                        </div>
                    </div>
                </div>
                <div id="consumed" class="tab-pane fade">
                    <div id="consumed-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                        </div>
                    </div>
                </div>
                <div id="retreat" class="tab-pane fade">
                    <div id="retreat-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>

<script>

    let unpaidOrders = ${unpaidOrders};
    $.each(unpaidOrders, function (index, unpaidOrderDetail) {
        let order = unpaidOrderDetail.order;
        let venuePlan = unpaidOrderDetail.venuePlan;
    });

    let createTimeStr = "2018-03-23 01:50:00";
    let createTime = new Date(Date.parse(createTimeStr.replace(/-/g, "/")));
    let now = new Date();

    //设定倒数秒数
    let minute = parseInt((now - createTime) / 1000 / 60);
    let second = parseInt((now - createTime - minute * 1000 * 60) / 1000);

    /**
     * 倒计时
     */
    //显示倒数秒数
    function countdown() {
        $(".countdown-minute").text(minute);
        if (second < 10) {
            $(".countdown-second").text("0" + second);
        } else {
            $(".countdown-second").text(second);
        }
        if (minute === 0 && second === 0) {
            //todo 倒计时完成的时候ajax
            alert("倒计时完成");
        }
        else if (second === 0) {
            minute -= 1;
            second = 59;
        }
        else {
            second -= 1;
        }
    }

    //每秒执行一次,countdown()
    setTimeout(countdown, 1000);

</script>
</body>
</html>
