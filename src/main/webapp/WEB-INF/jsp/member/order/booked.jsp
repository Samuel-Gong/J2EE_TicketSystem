<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 24/03/2018
  Time: 14:55
  
  Description: 展示会员已预订票的列表
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>已预订订单</title>
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
        <div id="booked-container" class="col-md-8">
            <div id="order-container" class="col-md-offset-1 col-md-10">
                <c:forEach items="${bookedOrders}" var="detailOrder">
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
                                    <label class="col-md-3 control-label">优惠票价:</label>
                                    <div class="col-md-2">
                                        <p class="form-control-static">
                                            <c:out value="${order.actualPrice}"/>元
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4">
                                        <input type="text" value="${order.orderId}" hidden>
                                        <button type="button" class="btn btn-primary center-block retreat-btn"
                                                data-toggle="modal" data-target="#refund-tip">退订
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<footer></footer>

<!-- refund-tip 模态框 -->
<div class="modal fade" id="refund-tip" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">确认退订</h4>
            </div>
            <div class="modal-body">
                <p id="more-than-max" class="hidden">距离演出开始多于14天，全款退还票价</p>
                <p id="less-than-max" class="hidden">距离演出不足
                    <span id="milestone-day"></span>天,收取已收票价的
                    <span id="fee-rate"></span>%,作为手续费
                </p>
                <p>已收票价：<span id="order-price"></span></p>
                <p>退还票价：<span id="refund"></span></p>

            </div>
            <div class="modal-footer">
                <div class="col-md-offset-4 col-md-4">
                    <input id="refund-order-id" type="text" class="hidden">
                    <button type="button" id="refund-confirm" class="btn btn-primary center-block">确认</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- login 模态框end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- date format -->
<script src="${pageContext.request.contextPath}/js/date-format.js"></script>

<script>
    //左侧导航栏active
    $("#booked-li").addClass("active");

    function retreat(orderId, refund) {
        $.ajax({
            url: "${pageContext.request.contextPath}/member/order/retreat/" + orderId,
            method: "get",
            data: {
                refund: refund
            },
            success: function (data) {
                if (data === true) {
                    alert("订单退订成功")
                    //跳转到退订订单界面
                    $(location).attr("href", "${pageContext.request.contextPath}/member/order/retreat");
                }
                else {
                    alert("订单退订失败");
                }
            }
        });
    }

    $("#booked-container").on("click", ".retreat-btn", function () {
        let orderId = $(this).prev().val();
        //获取退订订单的信息
        $.ajax({
            url: "${pageContext.request.contextPath}/member/order/retreat/tip/" + orderId,
            method: "get",
            success: function (refundTip) {
                console.log(refundTip);

                if (refundTip.moreThanMaxDay) {
                    $("#more-than-max").removeClass("hidden");
                    $("#less-than-max").addClass("hidden");
                }
                else {
                    $("#less-than-max").removeClass("hidden");
                    $("#more-than-max").addClass("hidden");

                    $("#milestone-day").text(refundTip.milestoneDay);
                    $("#fee-rate").text(refundTip.feeRate);
                }

                $("#refund-order-id").val(orderId);

                $("#order-price").text(refundTip.actualPrice);
                $("#refund").text(refundTip.refund);
            }
        });
    });

    $("#refund-confirm").on("click", function () {
        let orderId = $("#refund-order-id").val();
        let refund = $("#refund").text();
        // 退订
        retreat(orderId, refund);
    });
</script>
</body>
</html>
