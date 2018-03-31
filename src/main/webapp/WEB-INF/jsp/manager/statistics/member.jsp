<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/31
  Time: 20:21
  
  Description: 会员统计
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>会员统计</title>
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
        <div class="col-md-offset-4 col-md-4">
            <div id="member-chart" style="height: 400px;"></div>
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
    let memberChart = echarts.init($("#member-chart").get(0));

    // 指定退订图表的配置项和数据
    let memberChartOption = {
        title: {
            text: '会员人数',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['Lv0', 'Lv1', 'Lv2', 'Lv3', 'Lv4', 'Lv5']
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
            url: "${pageContext.request.contextPath}/manager/statistics/member/details",
            method: "get",
            success: function (statistics) {

                console.log(statistics);

                memberChartOption.series[0].data =
                    [
                        {
                            value: statistics["0"],
                            name: "Lv0"
                        },
                        {
                            value: statistics["1"],
                            name: "Lv1"
                        },
                        {
                            value: statistics["2"],
                            name: "Lv2"
                        },
                        {
                            value: statistics["3"],
                            name: "Lv3"
                        },
                        {
                            value: statistics["4"],
                            name: "Lv4"
                        },
                        {
                            value: statistics["5"],
                            name: "Lv5"
                        },
                    ];

                // 会员统计
                memberChart.setOption(memberChartOption);
            },
            error: function () {
                alert("获取会员统计信息失败");
            }
        });
    });
</script>

</body>
</html>
