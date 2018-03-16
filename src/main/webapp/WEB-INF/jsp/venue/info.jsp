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
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- 模态框的css -->
    <link href="../../../css/index.css" rel="stylesheet">

    <!-- seat css -->
    <link href="../../../css/jquery.seat-charts.css" rel="stylesheet">
    <link href="../../../css/seat.css" rel="stylesheet">

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
        座位修改按钮组的样式
        */
        #modify-seat-btn-grp {
            margin-left: 17%;
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

        #modify-basic-btn-grp {
            margin-left: 17%;
        }

    </style>
</head>
<body>

<%@include file="../../../html/venue/venueNav.html" %>

<!-- container begin -->
<div id="venue-info-container" class="container">
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
                <button id="modify-seat-btn" type="button" class="btn btn-primary center-block">修改座位信息</button>
                <div id="modify-seat-btn-grp" class="btn-group hidden">
                    <button id="modify-seat-confirm-btn" type="button" class="btn btn-primary">保存</button>
                    <button id="modify-seat-cancel-btn" type="button" class="btn btn-primary">取消</button>
                </div>
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
            <button id="modify-basic-info-btn" type="button" class="btn btn-primary center-block">修改基本信息</button>
            <div id="modify-basic-btn-grp" class="btn-group hidden">
                <button id="modify-basic-confirm-btn" type="button" class="btn btn-primary">保存</button>
                <button id="modify-basic-cancel-btn" type="button" class="btn btn-primary">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- container end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="../../../js/jquery.seat-charts.min.js"></script>
<!-- 对座位图操作的js -->
<script src="../../../js/seat-operation.js"></script>

<script>

    /**
     * 保存后端渲染的场馆座位及基本信息
     */
    var venueInfo = ${venue};

    /**
     * 确认/取消修改座位信息时，js共同动作
     */
    function seat_info_save_cancel() {
        $("#modify-seat-btn-grp").addClass("hidden");
        $("#modify-seat-btn").removeClass("hidden");
        //座位信息不可编辑
        $("#seat-info-fieldset").attr("disabled", "disabled");
        $("#seat-map-container").off("click", ".seatCharts-space:empty", space2seat);
        //删除座位转换到空位的点击事件
        delete seatInfo.a.click;
    }

    /**
     * 确认/取消修改基本信息时，js共同动作
     */
    function basic_info_save_cancel() {
        $("#modify-basic-btn-grp").addClass("hidden");
        $("#modify-basic-info-btn").removeClass("hidden");
        //基本信息不可编辑
        $("#basic-info-fieldset").attr("disabled", "disabled");
    }

    /**
     * 取消修改座位信息
     */
    function modify_seat_info_cancel() {
        seat_info_save_cancel();
        //座位图复原
        initSeatMap(venueInfo.rowNum, venueInfo.columnNum, venueInfo.seatMap);
        //重新渲染
        rerender();
    }

    /**
     * 取消修改基本信息
     */
    function modify_basic_info_cancel() {
        basic_info_save_cancel();

        //基本信息复原
        $("#venue-name").val(venueInfo.name);
        $("#venue-city").val(venueInfo.city);
    }

    $(document).ready(function () {

        //基本信息
        $("#venue-name").val(venueInfo.name);
        $("#venue-city").val(venueInfo.city);

        //座位信息
        initSeatMap(venueInfo.rowNum, venueInfo.columnNum, venueInfo.seatMap);
        renderSeats();

        //导航栏的active calss 添加删除
        $(".navbar-nav li").on("click", function () {
            $(".navbar-nav li").removeClass("active");
            $(this).addClass("active");
        });

        $("#add-row").on("click", add_row);
        $("#add-column").on("click", add_column);
        $("#delete-row").on("click", delete_row);
        $("#delete-column").on("click", delete_column);

        //修改座位信息的按钮
        $("#modify-seat-btn").on("click", function modify_seat() {
            //确认没有在修改基本信息
            if ($("#modify-basic-btn-grp").hasClass("hidden")) {
                //修改座位信息按钮隐藏，显示保存取消按钮组
                $(this).addClass("hidden");
                $("#modify-seat-btn-grp").removeClass("hidden");
                //座位信息可编辑
                $("#seat-info-fieldset").removeAttr("disabled");
                //空位添加由空位转换到座位的点击事件
                $("#seat-map-container").on("click", ".seatCharts-space:empty", space2seat);
                //座位添加由座位转换到空位的点击事件
                seatInfo.a.click = seat2space;
                //重新渲染
                rerender();
            }
            else {
                alert("请先保存或取消基本信息");
            }

        });

        //修改基本信息按钮
        $("#modify-basic-info-btn").on("click", function modify_basic_info() {
            //确认没有在修改座位信息
            if ($("#modify-seat-btn-grp").hasClass("hidden")) {
                //修改基本信息按钮隐藏，显示保存取消按钮组
                $(this).addClass("hidden");
                $("#modify-basic-btn-grp").removeClass("hidden");
                //基本信息可编辑
                $("#basic-info-fieldset").removeAttr("disabled");
            }
            else {
                alert("请先保存或取消座位信息")
            }
        });

        //修改座位信息取消按钮
        $("#modify-seat-cancel-btn").on("click", modify_seat_info_cancel);

        //修改基本信息取消按钮
        $("#modify-basic-cancel-btn").on("click", modify_basic_info_cancel);

        //修改座位信息确认按钮
        //更新座位信息
        $("#modify-seat-confirm-btn").on("click", function () {

            var seatMapArr = [];
            $.each(seatMap, function (row, val) {
                $.each(val, function (column, char) {
                    var newVenueSeat = {
                        "venueSeatId": {
                            "row": row + 1,
                            "column": column + 1,
                        },
                        "hasSeat": char === 'a'
                    };
                    seatMapArr.push(newVenueSeat);
                });
            });

            var seatMapInfo = {
                "venueId": ${sessionScope.venueId},
                "rowNum": seatMap.length,
                "columnNum": seatMap[0].length,
                "seatMap": seatMapArr
            };

            $.ajax({
                url: "/venue/updateSeatMap",
                contentType: "application/json;charset=utf-8",
                method: "post",
                data: JSON.stringify(seatMapInfo),
                processData: false,
                success: function (data) {
                    if (data === "true") {
                        alert("更新座位信息成功");
                        seat_info_save_cancel();
                    }
                    else {
                        alert("更新座位信息出现了问题");
                        modify_seat_info_cancel();
                    }
                },
                error: function () {
                    console.log("错误了");
                }
            });
        });

        //修改基本信息确认按钮
        //更新场馆基本信息
        $("#modify-basic-confirm-btn").on("click", function () {

            var venueBasicInfoDTO = {
                "venueId": ${sessionScope.venueId},
                "name": $("#venue-name").val(),
                "city": $("#venue-city").val(),
            };

            $.ajax({
                url: "/venue/updateBasicInfo",
                contentType: "application/json;charset=utf-8",
                method: "post",
                data: JSON.stringify(venueBasicInfoDTO),
                processData: false,
                success: function (data) {
                    if (data === "true") {
                        alert("更新基本信息成功");
                        basic_info_save_cancel();
                    }
                    else {
                        alert("更新基本信息出现了问题");
                        modify_basic_info_cancel();
                    }
                },
                error: function () {
                    console.log("错误了");
                }
            });
        });
    });
</script>
</body>
</html>
