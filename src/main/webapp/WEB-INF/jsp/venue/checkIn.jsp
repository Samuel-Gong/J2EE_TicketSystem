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
                        <td style="background-color: #3a78c3;"></td>
                        <td>未登记</td>
                    </tr>
                    <tr>
                        <td style="background-color: red"></td>
                        <td>已登记</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-8">
            <%@include file="../../../html/venue/seat-map-container.html" %>
        </div>
        <div class="col-md-2">
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
    </div>
</div>
<!-- container end -->

<footer></footer>

<!-- check-in 模态框 -->
<div class="modal fade" id="check-in-modal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">确认检票</h4>
            </div>
            <div class="modal-body">
                <p>确认<span id="check-in-row"></span>排<span id="check-in-column"></span>座检票入场</p>
            </div>
            <div class="modal-footer">
                <button type="button" id="check-in-btn" class="btn btn-primary login_btn center-block">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- check-in 模态框end -->

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

    /**
     * 选票购买时，应该添加点击座位的监听
     */
    function seatClick() {
        if (this.status() === 'available') {

            let id = this.node().attr("id");
            let rowAndColumn = id.split("_");
            let row = rowAndColumn[0];
            let column = rowAndColumn[1];

            //显示确认检票的模态框
            $("#check-in-row").text(row);
            $("#check-in-column").text(column);
            $("#check-in-modal").modal("show")

            return "available";
        }
        //已被检票的座位不可再点击
        else if (this.status() === 'selected') {
            return 'selected';
        }
        else if (this.status() === 'unavailable') {
            return 'unavailable';
        }
    }

    $("#check-in-btn").on("click", function () {

        let seatCheckIn = {
            venuePlanId: venuePlan.venuePlanId,
            rowAndColumnDTO: {
                row: $("#check-in-row").text(),
                column: $("#check-in-column").text()
            }
        };

        console.log(JSON.stringify(seatCheckIn));

        //登记检票
        $.ajax({
            url: "${pageContext.request.contextPath}/venue/checkIn",
            method: "post",
            data: JSON.stringify(seatCheckIn),
            contentType: 'application/json;charset=UTF-8',
            processData: false,
            success: function (data) {
                if (data === true) {
                    alert("检票成功");
                    //刷新页面
                    window.location.reload();
                }
                else {
                    alert("检票失败，该座位还未被预订");
                }
            },
            error: function () {
                alert("检票失败");
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

        //初始化seatMap
        fillSeatMapWithType(planDetail.rowNum, planDetail.columnNum, venuePlan.venuePlanSeats);

        //点击座位，不反应
        seatChartsSetting.addClick(seatClick);

        //对检票登记的座位图进行渲染
        checkInRender(venuePlan.venuePlanSeats);

    });
</script>
</body>
</html>
