<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/31
  Time: 19:41
  
  Description: 场馆统计
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>场馆统计</title>

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
            <div id="order-status" style="height:400px;"></div>
        </div>
        <div class="col-md-offset-1 col-md-4">
            <div id="settle" style="height:400px;"></div>
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
    let orderChart = echarts.init($("#order-status").get(0));
    let settleChart = echarts.init($("#settle").get(0));

    // 指定场馆预订退订情况的配置项和数据
    let orderChartOption = {
        title: {
            text: '订票情况',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已预订', '已退订']
        },
        series: [
            {
                name: '订票情况',
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

    // 指定退订图表的配置项和数据
    let settleChartOption = {
        title: {
            text: '结算情况',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已结算', '未结算']
        },
        series: [
            {
                name: '结算情况',
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
            url: "${pageContext.request.contextPath}/venue/statistics/details",
            method: "get",
            success: function (statistics) {

                console.log(statistics);

                orderChartOption.series[0].data =
                    [
                        {
                            value: statistics.totalBooked,
                            name: "已预订"
                        },
                        {
                            value: statistics.totalRefund,
                            name: "已退订"
                        }
                    ];

                settleChartOption.series[0].data =
                    [
                        {
                            value: statistics.totalSettle,
                            name: "已结算"
                        },
                        {
                            value: statistics.totalUnsettle,
                            name: "未结算"
                        }
                    ];

                // 总消费
                orderChart.setOption(orderChartOption);

                // 退订
                settleChart.setOption(settleChartOption);
            },
            error: function () {
                alert("获取会员统计信息失败");
            }
        });
    });
</script>
</body>
</html>

