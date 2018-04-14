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
            text-align: center;
            table-layout: fixed;
        }

        caption {
            text-align: center;
        }

        td {
            text-align: center;
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
                        <input id="pick-seat-radio" type="radio" name="book-type-options" value="pick" checked>选座购买
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input id="buy-now-radio" type="radio" name="book-type-options" value="buyNow">立即购买
                    </label>
                </div>
                <p id="time-not-enough" class="help-block hidden">距演出开始不足两周，不能立即购票</p>
            </form>
            <div id="selected-seat-form">
                <h4 class="text-center">已选座位</h4>
                <ul id="selected-seats-list"></ul>
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
                </div>
            </form>
            <div>
                <h4 class="text-center">优惠方式</h4>
            </div>
            <form id="offer-type-form">
                <div class="radio">
                    <label>
                        <input id="discount-radio" type="radio" name="offer-type-options" value="discount" checked>会员折扣
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input id="coupon-radio" type="radio" name="offer-type-options" value="coupon">优惠券
                    </label>
                </div>
                <p id="no-coupon-tip" class="help-block hidden">无可用优惠券</p>
            </form>
            <form id="discount-info" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-md-6">会员等级:</label>
                    <div class="col-md-6">
                        <p class="form-control-static">Lv.<span id="member-level"></span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-6">享受折扣:</label>
                    <div class="col-md-6">
                        <p class="form-control-static"><span id="member-discount">折</span></p>
                    </div>
                </div>
            </form>
            <table id="coupon-table" class="hidden">
                <caption>优惠券</caption>
                <thead>
                <tr>
                    <th>面额</th>
                    <th>剩余</th>
                    <th></th>
                </tr>
                </thead>
                <tbody id="remain-coupon">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <h5>原票价:<span id="raw-price">0</span></h5>
            <h5>优惠票价:<span id="actual-price">0</span></h5>
            <button id="buy-btn" type="button" class="btn btn-primary center-block">下单</button>
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
<!-- 专门处理日期的js -->
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>

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
     * 更新优惠票价
     */
    function updateActualPrice(rawPrice) {
        //会员折扣
        if ($("#discount-radio").is(":checked")) {
            let discount = parseInt($("#member-discount").text());
            console.log(discount);
            console.log(discount / 10 * rawPrice);
            $("#actual-price").text(discount / 10 * rawPrice);
        }
        //优惠券方式
        else {
            //当总价不为0的时候才减价
            if (rawPrice !== 0) {
                let decreasePrice = $("input:radio[name='coupon-value-options']:checked").val();
                console.log(decreasePrice);
                $("#actual-price").text(rawPrice - decreasePrice);
            }
        }
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
                let totalPriceNode = $("#raw-price");
                let typeChar = this.char();
                let totalPrice = parseInt(totalPriceNode.text()) + parseInt(seatTypes[findIndexInSeatTypes(typeChar)].price);
                totalPriceNode.text(totalPrice);
                updateActualPrice(totalPrice);

                return 'selected';
            }
        }
        else if (this.status() === 'selected') {

            let id = this.node().attr("id");
            let rowAndColumn = id.split("_");
            let row = rowAndColumn[0];
            let column = rowAndColumn[1];

            console.log("已选择座位:" + row + "排" + column + "座");

            console.log(selectedSeats);
            //更新selectedSeats数组以及右侧已选座位列表
            selectedSeats.splice(findIndexInSelectedSeats(id), 1);
            $("#selected-seats-list").children().each(function () {
                let text = $(this).text();
                let rowColumnArr = text.split("排");
                let rowInText = rowColumnArr[0];
                let columnText = rowColumnArr[1];
                let columnInText = columnText.split("座")[0];
                if (row === rowInText && column === columnInText) {
                    $(this).remove();
                    return false;
                }
            });

            //更新总票价
            let totalPriceNode = $("#raw-price");
            let typeChar = this.char();
            let totalPrice = parseInt(totalPriceNode.text()) - parseInt(seatTypes[findIndexInSeatTypes(typeChar)].price);
            totalPriceNode.text(totalPrice);
            updateActualPrice(totalPrice);

            return 'available';
        }
        //如果座位不可用，那么不允许更改样式
        else if (this.status() === 'unavailable') {
            return 'unavailable';
        }
    }

    $("#buy-now-seat-num").on("input", function () {
        let typeChar = $("#seat-type-list").val();
        //单价
        let singleSeatPrice;
        $.each(seatTypes, function (index, seatType) {
            if (typeChar === seatType.typeChar) {
                singleSeatPrice = seatType.price;
            }
        });
        //座位数量
        let seatNum = $(this).val();

        let totalPrice = singleSeatPrice * seatNum;
        //显示总价
        $("#raw-price").text(totalPrice);
        updateActualPrice(totalPrice);
    });

    /**
     * 立即购买切换到选座购买
     */
    function buyNow2PickSeat() {

        //总价格初始化为0
        $("#raw-price").text(0);

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

        //总价格初始化为0
        $("#raw-price").text(0);

        //座位不可选择，已选座位状态改为available
        seatChartsSetting.clickDoNothing();
        rerenderSeats(venuePlan.venuePlanSeats);

        //清空已选座位，已选座位列表
        selectedSeats = [];
        $("#selected-seats-list").empty();

        $("#selected-seat-form").addClass("hidden");
        $("#buy-now-form").removeClass("hidden");
    }

    /**
     * 会员折扣切换到优惠券
     */
    function discount2Coupon() {
        $("#coupon-table").removeClass("hidden");
        $("#discount-info").addClass("hidden");

        //更新实际价格
        let rawPrice = parseInt($("#raw-price").text());
        updateActualPrice(rawPrice);
    }

    /**
     * 优惠券切换到会员折扣
     */
    function coupon2Discount() {
        $("#discount-info").removeClass("hidden");
        $("#coupon-table").addClass("hidden");

        //更新实际价格
        let rawPrice = parseInt($("#raw-price").text());
        updateActualPrice(rawPrice);
    }

    /**
     * 下单按钮监听
     */
    $("#buy-btn").on("click", function () {

        let data = {
            mail: "${sessionScope.mail}",
            venueId: planDetail.venueId,
            venuePlanId: venuePlan.venuePlanId,
            createTime: moment().format("YYYY-MM-DD HH:mm:ss"),
            price: parseInt($("#raw-price").text()),
            actualPrice: parseInt($("#actual-price").text()),
            //是否使用会员折扣
            memberDiscount: $("#discount-info").is(":checked"),
            //线上购买
            boughtOnline: true,
            //会员购票
            memberOrder: true,
        };

        //判断是否是选座购买
        let seatSettled = $("#pick-seat-radio").is(":checked");
        //如果是选座购买
        if (seatSettled) {
            let orderSeats = [];
            $.each(selectedSeats, function (index, rawId) {
                let rowAndColumn = rawId.split("_");
                let row = rowAndColumn[0];
                let column = rowAndColumn[1];

                orderSeats.push(
                    {
                        row: row,
                        column: column
                    }
                );
            });
            data["seatSettled"] = true;
            //装载订单选取的座位
            data["orderSeats"] = orderSeats;
            console.log("选座购买:\n" + JSON.stringify(data));
        }
        //立即购买
        else {
            data["seatSettled"] = false;
            data["seatType"] = $("#seat-type-list").val();
            data["seatNum"] = $("#buy-now-seat-num").val();
            console.log("立即购买:\n" + JSON.stringify(data));
        }

        //是否使用优惠券
        let useCoupon = $("#coupon-radio").is(":checked");
        data["useCoupon"] = useCoupon;
        //使用优惠券
        if (useCoupon) {
            data["couponValue"] = $("input:radio[name='coupon-value-options']:checked").val();
        }

        console.log(data);

        $.ajax({
            url: '${pageContext.request.contextPath}/member/takeOrder',
            method: 'post',
            contentType: 'application/json;charset=UTF-8',
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

    $(document).ready(function () {

        /**
         * 请求显示会员等级和折扣
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/discount",
            method: "get",
            data: {
                memberId: "${sessionScope.mail}"
            },
            success: function (levelAndDiscountDTO) {
                //显示会员的邮箱和信息
                $("#member-level").text(levelAndDiscountDTO.level);
                $("#member-discount").text(levelAndDiscountDTO.discount);
            },
            error: function () {
                alert("请求会员等级和折扣失败");
            }
        });

        /**
         * 获取会员所拥有的优惠券及积分
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/coupons",
            method: "get",
            data: {
                memberId: "${sessionScope.mail}"
            },
            success: function (pointsAndCouponsDTO) {
                console.log(pointsAndCouponsDTO);
                $("#points").text(pointsAndCouponsDTO.points);
                let coupons = pointsAndCouponsDTO.coupons;
                if (Object.keys(coupons).length === 0) {
                    $("#coupon-radio").attr("disabled", "disabled");
                    $("#no-coupon-tip").removeClass("hidden");
                }
                else {
                    $.each(coupons, function (value, remain) {
                        $("#remain-coupon").append("" +
                            "<tr>\n" +
                            "   <td>" + value + "</td>\n" +
                            "   <td>" + remain + "</td>\n" +
                            "   <td>" +
                            "       <input type='radio' name='coupon-value-options' value='" + value + "'>" +
                            "   </td>\n" +
                            "</tr>");
                    });
                }

                //选中第一个选项
                $("input:radio[name='coupon-value-options']:first").attr("checked", "checked");
            },
            error: function () {
                alert("获取会员积分和优惠券失败");
            }
        });

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

        //检查当前时间与场馆计划开始时间是否相差两周，如果相差两周就准许立即买票
        let begin = moment(venuePlan.begin, "YYYY-MM-DD HH:mm:ss");
        let now = moment();
        //如果当前时间往后推两周在场馆计划开始之后，将立即购票的radio disabled
        if (now.add(2, "weeks").isAfter(begin)) {
            $("#buy-now-radio").attr("disabled", "disabled");
            $("#time-not-enough").removeClass("hidden");
        }

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

        $("input:radio[name='offer-type-options']").on("change", function () {
            //会员折扣
            if (this.value === 'discount') {
                coupon2Discount();
            } else {        //优惠券
                discount2Coupon();
            }
        });

        $("#coupon-table").on("change", "input:radio[name='coupon-value-options']", function () {
            let rawPrice = parseInt($("#raw-price").text());
            updateActualPrice(rawPrice);
        });

        seatChartsSetting.addClick(seatClick);
        renderSeats(venuePlan.venuePlanSeats);
    });
</script>
</body>
</html>
