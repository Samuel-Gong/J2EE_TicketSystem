<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 15/03/2018
  Time: 17:31

  Description:  场馆信息界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>场馆信息</title>

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

        /*
        左边座位信息的样式
        */
        #legend-info {
            border-right: #adadad solid 1px;
        }

        #seat-operation-container {
            margin-top: 20%;
        }

        /*
        增加删除按钮组的上下间距
        */
        #add-delete-container {
            margin-bottom: 20%;
        }

        /*
        右边场馆基本信息表单样式
        */
        #basic-info-form-container {
            border-left: #adadad solid 1px;
        }

        #venue-basic-info-form {
            margin-top: 30%;
        }

    </style>
</head>
<body>

<%@include file="nav.jsp" %>

<!-- container begin -->
<div id="venue-info-container" class="container">
    <div class="row">
        <h2 class="text-center">场馆信息</h2>
        <div class="col-md-offset-8 col-md-2">
            <button id="modify-info-btn" type="button" class="btn btn-primary center-block">修改信息</button>
        </div>
        <div class="col-md-offset-8 col-md-1">
            <button id="modify-info-confirm-btn" type="button" class="btn btn-primary center-block hidden">确认</button>
        </div>
        <div class="col-md-1">
            <button id="modify-info-cancel-btn" type="button" class="btn btn-primary center-block hidden">取消</button>
        </div>
    </div>
    <div id="all-venue-info" class="row">
        <div id="legend-info" class="col-md-2">
            <div id="legend-container">
                <div id="legend-box"></div>
            </div>
            <div id="seat-operation-container">
                <fieldset id="seat-info-fieldset" disabled="disabled">
                    <div id="add-delete-container">
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary" id="add-row">增加一行</button>
                            <button type="button" class="btn btn-primary" id="add-column">增加一列</button>
                        </div>
                        <div class="btn-group">
                            <button type="button" class="btn btn-warning" id="delete-row">删除一行</button>
                            <button type="button" class="btn btn-warning" id="delete-column">删除一列</button>
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
        <div class="col-md-8">
            <div id="seat-map-container" class="text-center">
                <div class="front-indicator">Front</div>
                <div id="seat-map"></div>
            </div>
        </div>
        <div id="basic-info-form-container" class="col-md-2">
            <fieldset id="basic-info-fieldset" disabled>
                <form id="venue-basic-info-form">
                    <div class="form-group">
                        <label class="control-label">场馆名</label>
                        <input id="venue-name" type="text" class="form-control">
                    </div>
                    <div class="form-group">
                        <label class="control-label">城市</label>
                        <select id="venue-city" class="form-control">
                            <option>北京</option>
                            <option>上海</option>
                            <option>重庆</option>
                            <option>南京</option>
                        </select>
                    </div>
                </form>
            </fieldset>
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

    //修改信息按钮监听
    $("#modify-info-btn").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/venue/modify/check",
            method: "get",
            success: function (data) {
                if (data) {
                    alert("可以修改场馆信息");
                    //修改信息按钮隐藏，显示保存取消按钮组
                    $("#modify-info-btn").addClass("hidden");
                    $("#modify-info-confirm-btn").removeClass("hidden");
                    $("#modify-info-cancel-btn").removeClass("hidden");

                    //基本信息可编辑
                    $("#basic-info-fieldset").removeAttr("disabled");

                    //座位信息可编辑
                    $("#seat-info-fieldset").removeAttr("disabled");
                    //空位添加由空位转换到座位的点击事件
                    $("#seat-map-container").on("click", ".seatCharts-space:empty", space2seat);
                    //座位添加由座位转换到空位的点击事件
                    seatInfo.a.click = seat2space;
                    //重新渲染
                    rerenderSeats();
                }
                else {
                    alert("场馆还有未完成的场馆计划，请等场馆计划完成再修改场馆信息");
                }
            },
            error: function () {
                alert("出错了");
            }
        });
    });


    function disableField() {
        //座位信息不可编辑
        $("#seat-info-fieldset").attr("disabled", "disabled");
        $("#seat-map-container").off("click", ".seatCharts-space:empty", space2seat);

        //基本信息不可编辑
        $("#basic-info-fieldset").attr("disabled", "disabled");
    }

    //修改信息取消按钮监听
    $("#modify-info-cancel-btn").on("click", function () {
        //确认取消按钮隐藏
        $("#modify-info-confirm-btn").addClass("hidden");
        $("#modify-info-cancel-btn").addClass("hidden");

        //显示修改信息按钮
        $("#modify-info-btn").removeClass("hidden");

        //场馆信息取消修改
        modify_info_cancel();
    });

    //修改信息确认按钮监听
    $("#modify-info-confirm-btn").on("click", function () {

        let seatMapArr = [];
        $.each(seatMap, function (row, val) {
            $.each(val, function (column, char) {
                let newVenueSeat = {
                    "row": row + 1,
                    "column": column + 1,
                    "hasSeat": char === 'a'
                };
                seatMapArr.push(newVenueSeat);
            });
        });

        let venue = {
            "id": ${sessionScope.venueId},
            "name": $("#venue-name").val(),
            "city": $("#venue-city").val(),
            "rowNum": seatMap.length,
            "columnNum": seatMap[0].length,
            "seatMap": seatMapArr
        };

        console.log(JSON.stringify(seatMapArr));

        $.ajax({
            url: "${pageContext.request.contextPath}/venue/update",
            contentType: "application/json;charset=utf-8",
            method: "post",
            data: JSON.stringify(venue),
            processData: false,
            success: function (data) {
                if (data) {
                    alert("更新基本信息成功，等待经理审批\n审批期间不可登陆");
                    $(location).attr("href", "${pageContext.request.contextPath}/venue/logout");
                }
                else {
                    alert("更新基本信息出现了问题");
                    //场馆信息取消修改
                    modify_info_cancel();
                }
            },
            error: function () {
                alert("错误了");
                //场馆信息取消修改
                modify_info_cancel();
            }
        });

    });


    /**
     * 取消修改场馆信息
     */
    function modify_info_cancel() {
        //输入框不可编辑
        disableField();

        //删除座位转换到空位的点击事件
        delete seatInfo.a.click;
        //座位图复原
        fillSeatMapWithDefaultType(venueInfo.rowNum, venueInfo.columnNum, venueInfo.seatMap);
        //重新渲染
        rerenderSeats();

        //基本信息复原
        $("#venue-name").val(venueInfo.name);
        $("#venue-city").val(venueInfo.city);
    }

    /**
     * 保存后端渲染的场馆座位及基本信息
     */
    let venueInfo = ${venueInfo};

    $(document).ready(function () {

        //基本信息
        $("#venue-name").val(venueInfo.name);
        $("#venue-city").val(venueInfo.city);

        seatChartsSetting.clickDoNothing();
        //座位信息
        fillSeatMapWithDefaultType(venueInfo.rowNum, venueInfo.columnNum, venueInfo.seatMap);
        renderSeats();

        //增加/删除 行/列 按钮添加监听
        $("#add-row").on("click", add_row);
        $("#add-column").on("click", add_column);
        $("#delete-row").on("click", delete_row);
        $("#delete-column").on("click", delete_column);
    });
</script>
</body>
</html>
