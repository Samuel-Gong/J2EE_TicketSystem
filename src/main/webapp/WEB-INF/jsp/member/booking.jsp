<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 21/03/2018
  Time: 11:37

  Description: 用户订票页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>购票</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="${pageContext.request.contextPath}/css/seat.css" rel="stylesheet">

    <style>
        table {
            table-layout: fixed;
        }

        td {
            font-size: 12px;
            white-space: nowrap;
            overflow: scroll;
            /*text-overflow: ellipsis;*/
        }
    </style>

</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<!-- container begin -->
<div id="venue-register-container" class="container">
    <div class="row">
        <h2 id="plan-description" class="text-center"></h2>
    </div>
    <div id="all-venue-info" class="row">
        <div class="col-md-2">
            <div id="legend-info">
                <h3 class="text-center">座位类型</h3>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th class="text-center">类型</th>
                        <th class="text-center">价格</th>
                        <th class="text-center">描述</th>
                    </tr>
                    </thead>
                    <tbody id="seat-type-table-body">
                    </tbody>
                </table>
            </div>
            <div id="plan-basic-info">
                <h3 class="text-center">演出信息</h3>
                <form id="venue-plan-info-form">
                    <div class="form-group">
                        <label class="control-label">开始时间</label>
                        <input id="plan-begin" class="form-control" type="text" value="" readonly>
                    </div>

                    <div class="form-group">
                        <label class="control-label">结束时间</label>
                        <input id="plan-end" class="form-control" type="text" value="" readonly>
                    </div>

                    <div class="form-group">
                        <label class="control-label">演出类型</label>
                        <input id="plan-showType" class="form-control" type="text" value="" readonly>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-8">
            <%@include file="../../../html/venue/seat-map-container.html" %>
        </div>
        <div class="col-md-2">
            <h3 class="text-center">购买方式</h3>
            <form id="book-type-form">
                <div class="radio">
                    <label>
                        <input type="radio" name="book-type-options" value="pick" checked>选座购买
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="book-type-options" value="buyNow">立即购买
                    </label>
                </div>
            </form>
            <div id="selected-seat-form">
                <h4 class="text-center">已选座位</h4>
                <ul id="selected-seats-list"></ul>
                <h5>总票价:<span id="pick-seat-price-show">0</span></h5>
                <button id="pick-seat-btn" type="button" class="btn btn-primary center-block hidden">下单</button>
            </div>
            <form id="buy-now-form" class="hidden">
                <div class="form-group">
                    <label class="control-label">指定座位类型</label>
                    <select id="seat-type-list" class="form-control">
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label">座位数量</label>
                    <input id="buy-now-seat-num" type="text" class="form-control">
                    <p class="help-block">最多不超过20个座位</p>
                    <h5>总票价:<span id="buy-now-price-show">0</span></h5>
                </div>
                <button id="buy-now-btn" type="button" class="btn btn-primary center-block">下单</button>
            </form>
        </div>
    </div>
</div>
<!-- container end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="${pageContext.request.contextPath}/js/jquery-seat-charts.js"></script>
<!-- 对座位操作的js -->
<script src="${pageContext.request.contextPath}/js/seat-operation.js"></script>
<!-- date -->
<script src="${pageContext.request.contextPath}/js/date-format.js"></script>

<script>

    //用于保存场馆计划详细信息
    let planDetail = ${planDetail};
    let venuePlan = planDetail.venuePlan;
    //保存了：typeChar price description等信息
    let seatTypes = venuePlan.seatTypes;

    /**
     * 保存被选择的座位id
     */
    let selectedSeats = [];

    /**
     * 通过座位id在selectedSeats中找到index
     */
    function findIndexInSelectedSeats(id) {
        let result;
        $.each(selectedSeats, function (index, seatId) {
            if (seatId === $(this).attr("id")) {
                selectedSeats.splice(index, 1);
                result = index;
                return false;
            }
        });
        return result;
    }

    /**
     * 通过座位类型字符在seatTypes中找到index
     */
    function findIndexInSeatTypes(typeChar) {
        let result;
        $.each(seatTypes, function (index, seatType) {
            if (typeChar === seatType.typeChar) {
                result = index;
            }
        });
        return result;
    }

    /**
     * 选票购买时，应该添加点击座位的监听
     */
    function seatClick() {
        if (this.status() === 'available') {
            if (selectedSeats.length === 6) {
                alert("所选座位不能超过6个");
                return 'available';
            }
            else {
                if (selectedSeats.length === 0) {
                    $("#pick-seat-btn").removeClass("hidden");
                }
                let id = this.node().attr("id");
                let rowAndColumn = id.split("_");
                let row = rowAndColumn[0];
                let column = rowAndColumn[1];

                //更新selectedSeats数组以及右侧已选座位列表
                selectedSeats.push(id);
                $("#selected-seats-list").append(
                    "<li>" + row + "排" + column + "座</li>"
                );

                //更新总票价
                let priceShow = $("#pick-seat-price-show");
                let typeChar = this.char();
                let totalPrice = parseInt(priceShow.text()) + parseInt(seatTypes[findIndexInSeatTypes(typeChar)].price);
                priceShow.text(totalPrice);
                return 'selected';
            }
        }
        else if (this.status() === 'selected') {
            if (selectedSeats.length === 1) {
                $("#selected-seat-btn").addClass("hidden");
            }
            let id = this.node().attr("id");
            let rowAndColumn = id.split("_");
            let row = rowAndColumn[0];
            let column = rowAndColumn[1];

            //更新selectedSeats数组以及右侧已选座位列表
            selectedSeats.splice(findIndexInSelectedSeats(id), 1);
            $("#selected-seats-list").children().each(function () {
                let text = $(this).text();
                let rowInText = text[0];
                let columnInText = text[2];
                if (row === rowInText && column === columnInText) {
                    $(this).remove();
                    return false;
                }
            });

            //更新总票价
            let priceShow = $("#pick-seat-price-show");
            let typeChar = this.char();
            let totalPrice = parseInt(priceShow.text()) - parseInt(seatTypes[findIndexInSeatTypes(typeChar)].price);
            priceShow.text(totalPrice);

            return 'available';
        }
        //如果座位不可用，那么不允许更改样式
        else if (this.status() === 'unavailable') {
            return 'unavailable';
        }
    }

    /**
     * 立即购买切换到选座购买
     */
    function buyNow2PickSeat() {

        //总价格初始化为0
        $("#pick-seat-price-show").text(0);

        //给座位添加点击监听
        seatChartsSetting.addClick(seatClick);
        rerenderSeats(venuePlan.venuePlanSeats);

        console.log("选座购买");
        $("#selected-seat-form").removeClass("hidden");
        $("#buy-now-form").addClass("hidden");
    }

    /**
     * 选座购买切换到立即购买
     */
    function pickSeat2BuyNow() {

        //座位不可选择，已选座位状态改为available
        seatChartsSetting.clickDoNothing();
        rerenderSeats(venuePlan.venuePlanSeats);

        //清空已选座位，已选座位列表
        selectedSeats = [];
        $("#selected-seats-list").empty();

        $("#selected-seat-form").addClass("hidden");
        $("#buy-now-form").removeClass("hidden");
    }

    //选座购买下单按钮监听
    $("#pick-seat-btn").on("click", function () {
        let orderPlanSeats = [];
        $.each(selectedSeats, function (index, rawId) {
            let rowAndColumn = rawId.split("_");
            let row = rowAndColumn[0];
            let column = rowAndColumn[1];

            orderPlanSeats.push(
                {
                    row: row,
                    column: column
                }
            );
        });

        let data = {
            mail: "${sessionScope.mail}",
            venueId: planDetail.venueId,
            venuePlanId: venuePlan.venuePlanId,
            createTime: new Date().Format("yyyy-MM-dd hh:mm:ss"),
            orderPlanSeats: orderPlanSeats,
            price: parseInt($("#pick-seat-price-show").text())
        };

        console.log(JSON.stringify(data));

        $.ajax({
            url: '${pageContext.request.contextPath}/member/pickSeatOrder',
            method: 'post',
            contentType: 'application/json;charset=UTF-8',
            async: false,           //同步操作
            data: JSON.stringify(data),
            processData: false,
            success: function (orderId) {
                if (orderId) {
                    console.log("成功了");
                    //跳转到支付界面
                    $(location).attr("href", "${pageContext.request.contextPath}/member/pay/" + orderId);
                }
                else {
                    console.log("失败了");
                }
            },
            error: function () {
                console.log("出错了");
            }
        });
    });

    //立即购买下单按钮监听
    $("#buy-now-btn").on("click", function () {

        let seatType = $("#seat-type-list").val();
        let seatNum = $("#buy-now-seat-num").val();

        let data = {
            mail: "${sessionScope.mail}",
            venueId: planDetail.venueId,
            venuePlanId: venuePlan.venuePlanId,
            createTime: new Date().Format("yyyy-MM-dd hh:mm:ss"),
            seatType: seatType,
            seatNum: seatNum,
            price: parseInt($("#pick-seat-price-show").text())
        };

        console.log(JSON.stringify(data));

        $.ajax({
            url: '${pageContext.request.contextPath}/order/buyNow',
            method: 'post',
            contentType: 'application/json;charset=UTF-8',
            async: false,           //同步操作
            data: JSON.stringify(data),
            processData: false,
            success: function (data) {
                if (data === "true") {
                    console.log("成功了");
                    // $(location).attr("href", "${pageContext.request.contextPath}/member/orderManagement");
                }
                else {
                    console.log("失败了");
                }
            },
            error: function () {
                console.log("出错了");
            }
        });
    });

    $(document).ready(function () {

        console.log(venuePlan);

        //设置基础信息
        $("#plan-begin").val(venuePlan.begin);
        $("#plan-end").val(venuePlan.end);
        $("#plan-showType").val(venuePlan.showType);
        $("#plan-description").text(venuePlan.description);

        //设置左侧座位类型表格的内容
        $.each(seatTypes, function (index, seatType) {

            let typeChar = seatType.typeChar;

            //设置左侧座位类型表格的内容
            $("#seat-type-table-body").append(
                "<tr>" +
                "<td class='seat-type-" + typeChar + "'" + ">" + typeChar + "</td>" +
                "<td>" + seatType.price + "</td>" +
                "<td>" + seatType.description + "</td>" +
                "</tr>");

            //添加seatInfo的内容
            seatInfo[typeChar] = {
                classes: "seat-type-" + typeChar,
            };

            //添加右侧seat-type-select中的option
            $("#seat-type-list").append(
                "<option value='" + typeChar + "'>" + typeChar + "</option>"
            );
        });

        //初始化seatMap
        fillSeatMapWithType(planDetail.rowNum, planDetail.columnNum, venuePlan.venuePlanSeats);

        $("input:radio[name='book-type-options']").on("change", function () {
            //选票购买
            if (this.value === "pick") {
                buyNow2PickSeat();
            } else {
                pickSeat2BuyNow();
            }
        });

        seatChartsSetting.addClick(seatClick);
        renderSeats(venuePlan.venuePlanSeats);
    });
</script>
</body>
</html>
