<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 25/03/2018
  Time: 16:54

  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>现场购票</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="${pageContext.request.contextPath}/css/seat.css" rel="stylesheet">
    <!-- footer -->
    <link href="${pageContext.request.contextPath}/css/footer.css" rel="stylesheet">

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
            <h4 class="text-center">已选座位</h4>
            <ul id="selected-seats-list"></ul>
            <h5>总票价:<span id="total-price">0</span></h5>
            <button id="buy-btn" type="button" class="btn btn-primary center-block hidden" data-toggle="modal"
                    data-target="#buyModal">下单
            </button>
        </div>
    </div>
</div>
<!-- container end -->

<footer></footer>
<!-- buy 模态框 -->
<div class="modal fade" id="buyModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">订单信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-offset-1 col-md-3">是否是会员</label>
                        <div class="radio col-md-2">
                            <label>
                                <input id="not-member-radio" type="radio" name="member-options" value="not-member"
                                       checked>非会员
                            </label>
                        </div>
                        <div class="radio col-md-2">
                            <label>
                                <input id="is-member-radio" type="radio" name="member-options" value="is-member">会员
                            </label>
                        </div>
                    </div>
                    <div id="member-input" class="form-group hidden">
                        <label class="control-label col-md-offset-2 col-md-2">会员账号</label>
                        <div class="col-md-5">
                            <input id="member-id" type="text" class="form-control" placeholder="请输入会员账号">
                        </div>
                        <div class="col-md-3">
                            <button id="check-member" type="button" class="btn-sm btn-primary">查询会员</button>
                        </div>
                    </div>
                    <div id="member-info" class="form-group hidden">
                        <label class="control-label col-md-offset-2 col-md-2">会员信息</label>
                        <div class="col-md-3">
                            <p class="form-control-static">等级：Lv<span id="member-level"></span></p>
                        </div>
                        <div class="col-md-3">
                            <p class="form-control-static">折扣：<span id="discount"></span>折</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">原票价</label>
                        <div class="col-md-3">
                            <p id="raw-price" class="form-control-static"></p>
                        </div>
                        <label class="control-label col-md-2">实际票价</label>
                        <div class="col-md-3">
                            <p id="actual-price" class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="col-md-offset-4 col-md-2">
                    <button type="button" id="buy-comfirm" class="btn btn-primary center-block">确认</button>
                </div>
                <div class="col-md-2">
                    <button type="button" id="buy-cancel" class="btn btn-primary center-block" data-dismiss="modal">取消
                    </button>
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
<!-- seat js -->
<script src="${pageContext.request.contextPath}/js/jquery-seat-charts.js"></script>
<!-- 对座位操作的js -->
<script src="${pageContext.request.contextPath}/js/seat-operation.js"></script>
<!-- date -->
<script src="${pageContext.request.contextPath}/js/date-format.js"></script>

<script>

    /**
     * 订单信息模态框显示时，计算总价格，选择非会员状态
     */
    $("#buyModal").on("show.bs.modal", function () {
        // $("#not-member-radio").attr("checked", "checked");
        let totalPrice = $("#total-price").text();
        //原价格显示总价
        $("#raw-price").text(totalPrice);

        //如果有会员信息
        if (!$("#member-info").hasClass("hidden")) {
            let discount = parseInt($("#discount").text());
            $("#actual-price").text(parseInt(totalPrice) * discount / 10);
        }
        else {
            $("#actual-price").text(totalPrice);
        }

    });

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
                    $("#buy-btn").removeClass("hidden");
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
                let priceShow = $("#total-price");
                let typeChar = this.char();
                let totalPrice = parseInt(priceShow.text()) + parseInt(seatTypes[findIndexInSeatTypes(typeChar)].price);
                priceShow.text(totalPrice);
                return 'selected';
            }
        }
        else if (this.status() === 'selected') {
            if (selectedSeats.length === 1) {
                $("#buy-btn").addClass("hidden");
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
            let priceShow = $("#total-price");
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
     * 检查会员按钮
     */
    $("#check-member").on("click", function () {
        let memberId = $("#member-id").val();
        console.log(memberId);
        $.ajax({
            url: "${pageContext.request.contextPath}/member/discount",
            method: "post",
            data: {
                mail: memberId
            },
            dataType: "json",
            success: function (data) {
                console.log(data);
                let level = data.level;
                let discount = data.discount;
                //等级为-1时
                if (level === -1) {
                    alert("该会员不存在");
                }
                else {
                    //显示会员信息
                    $("#member-info").removeClass("hidden");

                    $("#member-level").text(level);
                    $("#discount").text(discount);

                    //更改实际价格显示
                    let totalPrice = $("#total-price").text();
                    $("#actual-price").text(parseInt(totalPrice) * discount / 10);
                }
            },
            error: function () {
                alert("出错了");
            }
        });
    });

    /**
     * 确认购买按钮监听
     */
    $("#buy-comfirm").on("click", function () {

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

        let isMember = $("#is-member-radio").is(":checked");
        let data = {
            mail: $("#member-id").val(),
            //会员购买
            memberOrder: isMember,
            //非线上购买
            boughtOnline: false,
            venueId: planDetail.venueId,
            venuePlanId: venuePlan.venuePlanId,
            createTime: new Date().Format("yyyy-MM-dd hh:mm:ss"),
            orderSeats: orderSeats,
            price: parseInt($("#actual-price").text().trim())
        };

        console.log(JSON.stringify(data));

        $.ajax({
            url: '/venue/buyOnSite',
            method: 'post',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(data),
            processData: false,
            success: function (orderId) {
                if (orderId) {
                    alert("订票成功");
                    //刷新
                    window.location.reload();
                }
                else {
                    alert("订票失败");
                    window.location.reload();
                }
            },
            error: function () {
                alert("出错了");
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

        $("input:radio[name='member-options']").on("change", function () {
            if (this.value === "is-member") {
                //非会员转会员
                $("#member-input").removeClass("hidden");
            } else {
                //非会员checked
                $("#member-input").addClass("hidden");
                //会员id设置为空
                $("#member-id").val("");
                //如果会员信息未隐藏，隐藏会员信息
                if (!$("#member-info").hasClass("hidden")) {
                    $("#member-info").addClass("hidden");
                }
                //修改实际票价为总票价
                $("#actual-price").text($("#raw-price").text());
            }
        });

        seatChartsSetting.addClick(seatClick);
        renderSeats(venuePlan.venuePlanSeats);
    });
</script>
</body>
</html>