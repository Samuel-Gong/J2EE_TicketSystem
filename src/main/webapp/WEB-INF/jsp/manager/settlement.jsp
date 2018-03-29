<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/29
  Time: 21:50
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>结算中心</title>
    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

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

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-3">
            <h4>已结算收入：<span id="settle-income"></span>元</h4>
            <h4>未结算收入：<span id="unsettle-income"></span>元</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <table class="table text-center">
                <caption class="text-center">未结算场馆计划</caption>
                <thead>
                <tr>
                    <th class="text-center">场馆编号</th>
                    <th class="text-center">计划编号</th>
                    <th class="text-center">计划描述</th>
                    <th class="text-center">计划结束时间</th>
                    <th class="text-center">计划总收入</th>
                    <th class="text-center">结算</th>
                </tr>
                </thead>
                <tbody id="plan-table">
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- 结算 模态框 -->
<div class="modal fade" id="settle-modal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">计划结算</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-offset-1 col-md-3">计划编号</label>
                        <div class="col-md-2">
                            <p id="plan-id" class="form-control-static"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-1 col-md-3">场馆分成</label>
                        <div class="col-md-3">
                            <input class="form-control" id="rate" type="text" placeholder="场馆分成">
                        </div>
                        <p class="help-block col-md-offset-4">请输入1-10的整数</p>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-1 col-md-3">计划总收入</label>
                        <div class="col-md-2">
                            <p id="total-income" class="form-control-static"></p>
                        </div>
                        <label class="control-label col-md-2">实际收入</label>
                        <div class="col-md-2">
                            <p id="actual-income" class="form-control-static"></p>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="col-md-offset-4 col-md-2">
                    <button type="button" id="settle-comfirm" class="btn btn-primary center-block">确认</button>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary center-block" data-dismiss="modal">
                        取消
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 结算 模态框end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<script>

    $("#settle-comfirm").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/manager/settle",
            method: "get",
            data: {
                venuePlanId: $("#plan-id").text(),
                rate: $("#rate").val()
            },
            success: function (data) {
                if(data === true){
                    alert("结算成功");
                    window.location.reload();
                }
                else {
                    alert("结算失败");
                }
            },
            error: function () {
                alert("结算失败");
            }
        });
    });

    /**
     * 向表格中添加场馆信息
     */
    function addUnsettlePlans(venueAndPlans) {
        $.each(venueAndPlans, function (index, venueAndPlan) {
            let venue = venueAndPlan.venue;
            let plan = venueAndPlan.venuePlan;
            $("#plan-table").append(
                "<tr>\n" +
                "        <td>" + venue.id + "</td>\n" +
                "        <td>" + plan.venuePlanId + "</td>\n" +
                "        <td>" + plan.description + "</td>\n" +
                "        <td>" + plan.end + "</td>\n" +
                "        <td>" + plan.totalIncome + "</td>\n" +
                "        <td>" +
                "<button type='button' class='btn-link btn-primary settle-btn' data-toggle='modal' data-target='#settle-modal'>结算</button>" +
                "</td>\n" +
                "    </tr>");
        });
    }

    $(document).ready(function () {

        /**
         * 获取网站收入
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/manager/income",
            method: "get",
            dataType: "json",
            success: function (income) {
                let settleIncome = income.settleIncome;
                let unsettleIncome = income.unsettleIncome;

                $("#settle-income").text(settleIncome);
                $("#unsettle-income").text(unsettleIncome);
            },
            error: function () {
                alert("获取网站收入错误");
            }
        });

        /**
         * 获取所有已结束、未结算的场馆计划
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/manager/unsettlePlans",
            method: "get",
            dataType: "json",
            success: function (venueAndPlans) {
                console.log(venueAndPlans);
                addUnsettlePlans(venueAndPlans);
            },
            error: function () {
                alert("获取场馆数据错误");
            }
        });

        /**
         * 给结算按钮添加点击监听
         */
        $("#plan-table").on("click", ".settle-btn", function () {
            let planId = $(this).parent().siblings(":eq(1)").text();
            let totalIncome = $(this).parent().prev().text();

            $("#plan-id").text(planId);
            $("#total-income").text(totalIncome);

            $("#rate").val(10);

            $("#actual-income").text(totalIncome);
        });

        $("#rate").on("input", function () {
            let totalIncome = parseInt($("#total-income").text());
            let rate = parseInt($("#rate").val());
            $("#actual-income").text(totalIncome * rate / 10);
        });

    })

</script>
</body>
</html>
