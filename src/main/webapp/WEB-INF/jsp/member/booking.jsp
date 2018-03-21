<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 21/03/2018
  Time: 11:37
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>购票</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="../../../css/seat.css" rel="stylesheet">
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<!-- container begin -->
<div id="venue-register-container" class="container">
    <div class="row">
        <h2 class="text-center">座位分布</h2>
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
                    <div class="form-group">
                        <label class="control-label">描述</label>
                        <textarea id="plan-description" class="form-control" rows="5"
                                  style="overflow: scroll; resize: none;" readonly></textarea>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-8">
            <%@include file="../../../html/venue/seat-map-container.html" %>
        </div>
        <div class="col-md-2">
            <h3>购买方式</h3>
            <form id="book-type-form">
                <div class="radio">
                    <label>
                        <input type="radio" name="book-type-options" checked>选票购买
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input type="radio" name="book-type-options">立即购买
                    </label>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- container end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="../../../js/jquery-seat-charts.js"></script>
<!-- 对座位操作的js -->
<script src="../../../js/seat-operation.js"></script>

<script>

    //用于保存场馆计划详细信息
    let planDetail = ${planDetail};
    let venuePlan = planDetail.venuePlan;

    $(document).ready(function () {

        //设置基础信息
        $("#plan-begin").val(venuePlan.begin);
        $("#plan-end").val(venuePlan.end);
        $("#plan-showType").val(venuePlan.showType);
        $("#plan-description").val(venuePlan.description);

        //设置左侧座位类型表格的内容
        let seatTypes = venuePlan.seatTypes;
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
                classes: "seat-type-" + typeChar
            };
        });

        //初始化seatMap
        fillSeatMapWithType(planDetail.rowNum, planDetail.columnNum, venuePlan.venue);

        //改变seatMap的颜色
        let venuePlanSeats = venuePlan.venuePlanSeats;
        console.log(venuePlanSeats);

        $.each(venuePlanSeats, function (index, planSeat) {
            seatMap[planSeat.row - 1][planSeat.column - 1] = planSeat.typeChar;
        });
        renderSeats();


        let selectedSeats = [];
        //添加点击座位修改座位的颜色

        //todo 选票购买和立即购买方式的切换
    });
</script>
</body>
</html>
