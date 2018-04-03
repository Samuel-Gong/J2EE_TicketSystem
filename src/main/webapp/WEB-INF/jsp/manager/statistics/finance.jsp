<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/31
  Time: 20:21
  
  Description:  Tickets财务统计
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>财务统计</title>
    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 模态框的css -->
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet">
</head>
<body>

<!-- nav begin -->
<%@include file="../nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <div id="finance-chart" style="height: 500px;"></div>
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
    let financeChart = echarts.init($("#finance-chart").get(0));

    // 指定tickets财务图表的配置项和数据
    let financeChartOption = {
        title: {
            text: 'Tickets财务统计',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['已盈利收入', '未结算收入']
        },
        series: [
            {
                name: '财务统计',
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
            url: "${pageContext.request.contextPath}/manager/statistics/finance/details",
            method: "get",
            success: function (statistics) {

                console.log(statistics);

                financeChartOption.series[0].data =
                    [
                        {
                            value: statistics.settleIncome,
                            name: "已盈利收入"
                        },
                        {
                            value: statistics.unsettleIncome,
                            name: "未结算收入"
                        },
                    ];

                // 财务统计
                financeChart.setOption(financeChartOption);
            },
            error: function () {
                alert("获取Tickets财务统计失败");
            }
        });
    });
</script>

</body>
</html>
