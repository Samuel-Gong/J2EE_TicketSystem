<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 12:00
  
  Description: 场馆审批界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>场馆审批</title>
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
<%@include file="../../../html/manager/nav.html" %>
<!-- nav end -->

<table class="table text-center">
    <caption>待审批场馆列表</caption>
    <thead>
    <tr>
        <th>场馆编号</th>
        <th>场馆名称</th>
        <th>场馆所在城市</th>
        <th>详情查看</th>
    </tr>
    </thead>
    <tbody id="venue-table">
        <td>南京</td>
    </tr>
    <tr>
        <td>Sachin</td>
        <td>Mumbai</td>
    </tr>
    </tbody>
</table>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>


<script>

    /**
     * 向表格中添加场馆信息
     * @param venues
     */
    function addVenues(venues) {
        $.each(venues, function (index, venue) {
            $("#venue-table").append(
                "<tr>\n" +
                "        <td>" + venue.id + "</td>\n" +
                "        <td>" + venue.name + "</td>\n" +
                "        <td>" + venue.city + "</td>\n" +
                "        <td><button type='button' class='btn btn-link' href='/manager/audit/venue/" + venue.id + "' >查看详情</button></td>\n" +
                "    </tr>");
        });
    }

    $(document).ready(function () {

        $.ajax({
            url: "/manager/venues",
            method: "get",
            dataType: "json",
            success: function (venues) {
                addVenues(JSON.stringify(venues));
            },
            error: function () {
                alert("获取场馆数据错误");
            }
        });

    });

</script>
</body>
</html>
