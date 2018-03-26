<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 18:13
  
  Description: 场馆审批详情界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>场馆审批详情</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="${pageContext.request.contextPath}/css/seat.css" rel="stylesheet">

    <style>
        #all-venue-info {
            padding-top: 2%;
            margin-top: 2%;
            border-top: #adadad solid 1px;
        }

        #legend-info {
            margin-top: 3%;
        }

        #venue-basic-info-form {
            margin-top: 30%;
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
        <h2 class="text-center">场馆审批</h2>
        <div class="col-md-offset-8 col-md-2">
            <button id="pass-btn" type="button" class="btn btn-primary center-block">审批通过</button>
        </div>
    </div>
    <div id="all-venue-info" class="row">
        <div id="legend-info" class="col-md-2">
            <div id="legend-container">
                <div id="legend-box"></div>
            </div>
            <div id="seat-operation-container">
            </div>
        </div>
        <div class="col-md-8">
            <div id="seat-map-container" class="text-center">
                <div class="front-indicator">Front</div>
                <div id="seat-map"></div>
            </div>
        </div>
        <div class="col-md-2">
            <form id="venue-basic-info-form">
                <div class="form-group">
                    <label class="control-label">场馆名</label>
                    <input id="venue-name" type="text" class="form-control" readonly>
                </div>
                <div class="form-group">
                    <label class="control-label">城市</label>
                    <input id="venue-city" type="text" class="form-control" readonly>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- container end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="${pageContext.request.contextPath}/js/jquery-seat-charts.js"></script>
<!-- 对座位图操作的js -->
<script src="${pageContext.request.contextPath}/js/seat-operation.js"></script>

<script>

    /**
     * 设置场馆的信息
     * @param venue
     */
    function setVenueInfo(venue) {
        $("#venue-name").val(venue.name);
        $("#venue-city").val(venue.city);
        //点击座位不发生任何事情
        seatChartsSetting.clickDoNothing();
        fillSeatMapWithDefaultType(venue.rowNum, venue.columnNum, venue.seatMap);
        renderSeats();
    }

    /**
     * 审批通过按钮监听
     */
    $("#pass-btn").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/manager/audit/pass/" + ${venueId},
            method: "post",
            success: function () {
                alert("${venueId}号场馆审批通过");
                $(location).attr("href", "${pageContext.request.contextPath}/manager/audit");
            },
            error: function () {
                alert("审批出现错误");
            }
        });
    });

    $(document).ready(function () {

        $.ajax({
            url: "${pageContext.request.contextPath}/manager/audit/venue/detail/" +${venueId},
            method: "get",
            dataType: "json",
            success: function (venue) {
                console.log(venue);
                setVenueInfo(venue);
            },
            error: function () {
                alert("出错了");
            }
        });
    });
</script>
</body>
</html>
