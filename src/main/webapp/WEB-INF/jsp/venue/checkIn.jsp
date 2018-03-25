<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 25/03/2018
  Time: 17:59
  
  Description: 检票登记界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>检票登记</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="../../../css/seat.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../css/footer.css" rel="stylesheet">

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
<%@include file="../../../html/venue/nav.html" %>
<!-- nav end -->

<!-- container begin -->
<div id="venue-register-container" class="container">
    <div class="row">
        <h2 id="plan-description" class="text-center"></h2>
    </div>
    <div id="all-venue-info" class="row">
        <div class="col-md-2">
            <div id="legend-info">
                <h3 class="text-center">座位登记</h3>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th class="text-center">颜色</th>
                        <th class="text-center">登记状况</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr id="sold-tr">
                        <td style="background-color: #472B34"></td>
                        <td>可以登记</td>
                    </tr>
                    <tr>
                        <td style="background-color: #3a78c3"></td>
                        <td>不能登记</td>
                    </tr>
                    <tr>
                        <td style="background-color: red"></td>
                        <td>已经登记</td>
                    </tr>
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
            <h4 class="text-center">检票登记</h4>
            <form>
                <div class="form-group">
                    <label class="control-label">行数</label>
                    <input id="row" class="form-control" type="text" placeholder="行数">
                </div>
                <div class="form-group">
                    <label class="control-label">列数</label>
                    <input id="column" class="form-control" type="text" placeholder="列数">
                </div>
                <div class="form-group">
                    <button id="checkIn-btn" type="button" class="btn btn-primary center-block">登记</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- container end -->

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="../../../js/jquery-seat-charts.js"></script>
<!-- 对座位操作的js -->
<script src="../../../js/seat-operation.js"></script>
<!-- date -->
<script src="../../../js/date-format.js"></script>

<script>

    //用于保存场馆计划详细信息
    let planDetail = ${planDetail};
    let venuePlan = planDetail.venuePlan;

    $("#checkIn-btn").on("click", function () {
        let row = $("#row").val();
        let column = $("#column").val();

        //判断是否在座位图中
        if (isInSeatMap(row, column)) {
            //判断该座位是否已被预订，isAvailable表示该座位未被预订
            if (isAvailable(row, column)) {
                alert("该座位还未预订，不能检票登记");
            } else {
                $.ajax({
                    url: "/venue/checkIn",
                    method: "post",
                    data:
                        {
                            venuePlanId: venuePlan.venuePlanId,
                            row: row,
                            column: column
                        },
                    success: function (data) {
                        if (data === true) {
                            console.log(row + "排" + column + "座" + "登记成功");
                            seatCheckIn(row, column);
                        }
                        else {
                            console.log(row + "排" + column + "座" + "登记失败");
                        }
                    }
                });
            }
        }
        else {
            alert("所选座位不在座位图中");
        }
    });

    $(document).ready(function () {

        console.log(venuePlan);

        //设置基础信息
        $("#plan-begin").val(venuePlan.begin);
        $("#plan-end").val(venuePlan.end);
        $("#plan-showType").val(venuePlan.showType);
        $("#plan-description").text(venuePlan.description);

        //初始化seatMap
        fillSeatMapWithType(planDetail.rowNum, planDetail.columnNum, venuePlan.venuePlanSeats);

        //点击座位，不反应
        seatChartsSetting.clickDoNothing();

        //对检票登记的座位图进行渲染
        checkInRender(venuePlan.venuePlanSeats);

    });
</script>
</body>
</html>
