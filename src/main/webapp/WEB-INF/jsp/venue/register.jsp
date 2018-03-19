<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 16/03/2018
  Time: 00:49
  
  Description:  场馆注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>场馆注册</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="../../../css/seat.css" rel="stylesheet">

    <style>
        #all-venue-info {
            padding-top: 2%;
            margin-top: 2%;
            border-top: #adadad solid 1px;
        }

        #legend-info {
            margin-top: 3%;
        }

        /*
        增加删除按钮组的上下间距
        */
        #add-delete-container {
            margin-top: 20%;
            margin-bottom: 20%;
        }

        #venue-basic-info-form {
            margin-top: 30%;
        }

    </style>

</head>
<body>

<!-- nav begin -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- 导航栏头 -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">小麦网</a>
        </div>
    </div>
</nav>
<!-- nav end -->

<!-- container begin -->
<div id="venue-register-container" class="container">
    <div class="row">
        <h2 class="text-center">场馆注册</h2>
    </div>
    <div id="all-venue-info" class="row">
        <div id="legend-info" class="col-md-2">
            <div id="legend-container">
                <div id="legend-box"></div>
            </div>
            <div id="seat-operation-container">
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
                    <input id="venue-name" type="text" class="form-control">
                </div>
                <div class="form-group">
                    <label class="control-label">密码</label>
                    <input id="venue-password" type="password" class="form-control">
                </div>
                <div class="form-group">
                    <label class="control-label">确认密码</label>
                    <input id="venue-password-confirm" type="password" class="form-control">
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
                <button id="venue-register-btn" type="button" class="btn btn-primary center-block">注册</button>
            </form>
        </div>
    </div>
</div>
<!-- container end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="../../../js/jquery-seat-charts.js"></script>
<!-- 对座位图操作的js -->
<script src="../../../js/seat-operation.js"></script>

<script>

    $(document).ready(function () {

        renderSeats();

        $("#add-row").on("click", add_row);
        $("#add-column").on("click", add_column);
        $("#delete-row").on("click", delete_row);
        $("#delete-column").on("click", delete_column);

        //空位添加由空位转换到座位的点击事件
        $("#seat-map-container").on("click", ".seatCharts-space:empty", space2seat);

        //注册按钮点击后，将数据封装成JSON对象传给后端
        $("#venue-register-btn").on("click", function () {
            let seatMapArr = [];
            $.each(seatMap, function (row, val) {
                $.each(val, function (column, char) {
                    let newVenueSeat = {
                        "venueSeatId": {
                            "row": row + 1,
                            "column": column + 1
                        },
                        "hasSeat": char === 'a'
                    };
                    seatMapArr.push(newVenueSeat);
                });
            });

            let venue = {
                "name": $("#venue-name").val(),
                "city": $("#venue-city").val(),
                "password": $("#venue-password").val(),
                "rowNum": seatMap.length,
                "columnNum": seatMap[0].length,
                "seatMap": seatMapArr
            };

            $.ajax({
                url: "/venue/register",
                contentType: "application/json;charset=utf-8",
                method: "post",
                data: JSON.stringify(venue),
                processData: false,
                success: function (data) {
                    //todo 提交之后的操作
                    if (data === "true") {
                        alert("注册成功，请等待经理审核");
                        //返回主页
                        $(location).attr("href", "/index");
                    }
                    else {
                        alert("注册出现了问题");
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
