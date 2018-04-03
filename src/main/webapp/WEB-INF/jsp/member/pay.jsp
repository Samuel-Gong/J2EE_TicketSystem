<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 25/03/2018
  Time: 12:04

  Description:  订单支付界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>未支付订单</title>
    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">
    <style>
        .panel {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div id="unpaid-container" class="col-md-8 col-md-offset-2">
            <div class="col-md-offset-1 col-md-10">
                <c:set var="order" value="${unpaidOrder.order}"/>
                <c:set var="venuePlan" value="${unpaidOrder.venuePlan}"/>
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
                                <label class="col-md-3 control-label">原票价:</label>
                                <div class="col-md-2">
                                    <p class="form-control-static">
                                        <c:out value="${order.price}"/>元
                                    </p>
                                </div>
                                <label class="col-md-3 control-label">优惠票价:</label>
                                <div class="col-md-2">
                                    <p class="form-control-static">
                                        <c:out value="${order.actualPrice}"/>元
                                    </p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer">
                        剩余时间:<span id="countdown-minute"></span>分<span id="countdown-second"></span>秒
                    </div>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-md-2 col-md-offset-4">
            <button id="pay-btn" type="button" class="btn btn-primary center-block">支付</button>
        </div>
        <div class="col-md-2">
            <button id="cancel-btn" type="button" class="btn btn-primary center-block">取消</button>
        </div>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- moment -->
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script>

    /**
     * 支付按钮监听
     */
    $("#pay-btn").on("click", function () {
        $.get("${pageContext.request.contextPath}/member/order/pay/" + ${order.orderId},
            function (data) {
                if (data === true) {
                    alert("订单支付成功");
                    $(location).attr("href", "${pageContext.request.contextPath}/member/order/booked");
                }
                else {
                    alert("订单支付失败，账户余额不足");
                    //跳转到未支付订单
                    $(location).attr("href", "${pageContext.request.contextPath}/member/order/unpaid");
                }
            }
        );
    });

    /**
     * 取消按钮监听
     */
    $("#cancel-btn").on("click", function () {
        $.get("${pageContext.request.contextPath}/member/order/cancel/" + ${order.orderId},
            function (data) {
                if (data === true) {
                    alert("订单取消成功");
                    //跳转到订单取消界面
                    $(location).attr("href", "${pageContext.request.contextPath}/member/order/cancel");
                }
                else {
                    alert("订单取消失败");
                    //跳转到未支付订单
                    $(location).attr("href", "${pageContext.request.contextPath}/member/order/unpaid");
                }
            }
        );
    });

    /**
     * 倒计时
     */

        //计算倒计时分钟数和秒数
    let createTime = moment("${order.createTime}", "YYYY-MM-DDTHH:mm:ss");
    let now = moment();

    console.log(createTime);
    console.log(now);

    //获取相差分数
    let millis = now.diff(createTime);
    let passMinute = Math.floor(millis / 1000 / 60);
    let passSecond = Math.floor(now.diff(createTime) / 1000) - 60 * passMinute;

    let remainMinute = 14 - passMinute;
    let remainSecond = 60 - passSecond;
    console.log(remainMinute);
    console.log(remainSecond);

    //显示倒数秒数
    function countdown() {
        $("#countdown-minute").text(remainMinute);
        if (remainSecond < 10) {
            $("#countdown-second").text("0" + remainSecond);
        } else {
            $("#countdown-second").text(remainSecond);
        }
        if (remainMinute === 0 && remainSecond === 0) {
            alert("支付超时，订单自动取消");
            //支付超时，取消订单
            $.ajax({
                url: "${pageContext.request.contextPath}/member/order/cancel/${order.orderId}",
                method: "get",
                async: false,
                success: function (data) {
                    if (data === true) {
                        console.log("订单取消成功");
                        $(location).attr("href", "${pageContext.request.contextPath}/member/order/cancel");
                    }
                },
                error: function () {
                    alert("订单取消有故障");
                }
            });
        }
        else if (remainSecond === 0) {
            remainMinute -= 1;
            remainSecond = 59;
        }
        else {
            remainSecond -= 1;
        }
        //每秒执行一次,countdown()
        setTimeout(countdown, 1000);
    }

    countdown();

</script>
</body>
</html>
