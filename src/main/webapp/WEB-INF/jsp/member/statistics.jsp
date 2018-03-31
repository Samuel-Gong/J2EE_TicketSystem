<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/30
  Time: 16:37

  Description: 会员消费统计
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>消费统计</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 模态框的css -->
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-offset-1 col-md-4 ">
            <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
            <div id="total-consumption" style="height:400px;"></div>
        </div>
        <div class="col-md-offset-1 col-md-4">
            <div id="refund" style="height:400px;"></div>
        </div>
    </div>
</div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- 引入 echarts.js -->
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>

<script type="text/javascript">

    // 基于准备好的dom，初始化echarts实例
    let totatConsumptionChart = echarts.init($("#total-consumption").get(0));
    let refundConsumptionChart = echarts.init($("#refund").get(0));

    // 指定总消费图表的配置项和数据
    let totalConsumptionOption = {
        title: {
            text: '总消费明细',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已预订', '已消费', '手续费']
        },
        series: [
            {
                name: '总消费',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data:
                ,
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 指定退订图表的配置项和数据
    let refundOption = {
        title: {
            text: '退订明细',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['退还金额', '手续费']
        },
        series: [
            {
                name: '退订明细',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                data: [],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    $(document).ready(function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/member/statistics/details",
            method: "get",
            success: function (statistics) {

                totalConsumptionOption.series[0].data =
                    [
                        {
                            value: statistics.totalBooked,
                            name: "已预订"
                        },
                        {
                            value: statistics.totalConsumed,
                            name: "已消费"
                        },
                        {
                            value: statistics.totalFee,
                            name: "手续费"
                        }
                    ];

                refundOption.series[0].data =
                    [
                        {
                            value: statistics.totalRefund,
                            name: "退还金额"
                        },
                        {
                            value: statistics.totalFee,
                            name: "手续费"
                        }
                    ];

                console.log(statistics);

                // 总消费
                totatConsumptionChart.setOption(totalConsumptionOption);

                // 退订
                refundConsumptionChart.setOption(refundOption);
            },
            error: function () {
                alert("获取会员统计信息失败");
            }
        });
    });
</script>
</body>
</html>
