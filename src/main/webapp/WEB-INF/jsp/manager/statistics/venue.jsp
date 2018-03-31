<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/31
  Time: 20:20
  
  Description: 各场馆统计
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>各场馆统计</title>
    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

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
<%@include file="../nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <table class="table text-center">
                <caption class="text-center">各场馆盈利统计列表</caption>
                <thead>
                <tr>
                    <th class="text-center">场馆编号</th>
                    <th class="text-center">场馆名称</th>
                    <th class="text-center">场馆所在城市</th>
                    <th class="text-center">已结算盈利</th>
                </tr>
                </thead>
                <tbody id="venue-table">
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<script>

    /**
     * 向表格中添加场馆信息
     * @param venues
     */
    function addVenues(statisticVenues) {
        $.each(statisticVenues, function (index, statisticVenues) {
            let venue = statisticVenues.venue;
            $("#venue-table").append(
                "<tr>\n" +
                "        <td>" + venue.id + "</td>\n" +
                "        <td>" + venue.name + "</td>\n" +
                "        <td>" + venue.city + "</td>\n" +
                "        <td>" + statisticVenues.totalIncome + "</td>\n" +
                "    </tr>");
        });
    }

    $(document).ready(function () {

        $.ajax({
            url: "${pageContext.request.contextPath}/manager/statistics/venue/details",
            method: "get",
            dataType: "json",
            success: function (statisticVenues) {
                console.log(statisticVenues);
                addVenues(statisticVenues);
            },
            error: function () {
                alert("获取场馆统计数据错误");
            }
        });

    });

</script>
</body>
</html>

