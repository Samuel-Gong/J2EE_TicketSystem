<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 18/03/2018
  Time: 19:28
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>发布计划</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- seat css -->
    <link href="../../../css/seat.css" rel="stylesheet">

    <!-- datetimepicker css -->
    <link href="../../../css/bootstrap-datetimepicker.min.css" rel="stylesheet">

    <style>

        .modal select {
            border-radius: 0;
        }

        #all-venue-info {
            padding-top: 2%;
            margin-top: 2%;
            border-top: #adadad solid 1px;
        }

        table {
            table-layout: fixed;
        }

        td {
            white-space: nowrap;
            overflow: scroll;
            /*text-overflow: ellipsis;*/
        }
    </style>

</head>
<body>

<!-- nav begin -->
<%@include file="../../../html/venue/venue-nav.html" %>
<!-- nav end -->

<!-- container begin -->
<div id="venue-register-container" class="container">
    <div class="row">
        <h2 class="text-center">发布计划</h2>
        <button id="release-plan-btn" type="button" class="btn btn-primary col-lg-offset-9"
                style="display: inline-block">发布计划
        </button>
    </div>
    <div id="all-venue-info" class="row">
        <div class="col-md-2">
            <div id="legend-info">
                <h3 class="text-center">座位类型</h3>
                <table class="table table-bordered text-center">
                    <thead>
                    <tr>
                        <th class="text-center">类型</th>
                        <th class="text-center">价格</th>
                        <th class="text-center">描述</th>
                    </tr>
                    </thead>
                    <tbody id="seat-type-table-body">
                    </tbody>
                </table>
                <div class="btn-group">
                    <button type="button" class="btn-sm btn-primary" data-toggle="modal"
                            data-target="#add-seat-type-modal">
                        增加类型
                    </button>
                    <button type="button" class="btn-sm btn-primary" data-toggle="modal"
                            data-target="#delete-seat-type-modal">删除类型
                    </button>
                </div>
            </div>
            <div id="plan-basic-info">
                <h3 class="text-center">基础信息</h3>
                <form id="venue-plan-info-form">
                    <div class="form-group">
                        <label class="control-label">开始时间</label>
                        <div id="plan-begin-datetimepicker" class="input-group date">
                            <input id="plan-begin" class="form-control" type="text" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label">结束时间</label>
                        <div id="plan-end-datetimepicker" class="input-group date">
                            <input id="plan-end" class="form-control" type="text" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label">演出类型</label>
                        <select id="show-type" class="form-control">
                            <option>演唱会</option>
                            <option>演唱会</option>
                            <option>演唱会</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="control-label">描述</label>
                        <textarea id="plan-description" class="form-control" rows="5"
                                  style="overflow: scroll; resize: none;"></textarea>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-8">
            <div id="seat-map-container" class="text-center">
                <div class="front-indicator">Front</div>
                <div id="seat-map"></div>
            </div>
        </div>
        <div class="col-md-2">
            <h3 class="text-center">座位指定</h3>
            <div>
                <h4 class="text-center">单个座位指定</h4>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-4 control-label">行</label>
                        <div class="col-md-8">
                            <input id="single-seat-row" type="text" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">列</label>
                        <div class="col-md-8">
                            <input id="single-seat-column" type="text" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">类型</label>
                        <div class="col-md-8">
                            <select id="single-seat-type-list" class="form-control"></select>
                        </div>
                    </div>
                    <button id="single-seat-appoint-btn" type="button" class="btn btn-primary center-block">确认指定
                    </button>
                </form>
            </div>
            <div>
                <h4 class="text-center">一行座位指定</h4>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-md-4 control-label">行</label>
                        <div class="col-md-8">
                            <input id="whole-row" type="text" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">类型</label>
                        <div class="col-md-8">
                            <select id="row-seat-type-list" class="form-control"></select>
                        </div>
                    </div>
                    <button id="row-seat-appoint-btn" type="button" class="btn btn-primary center-block">确认指定</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- container end -->

<!-- 增加类型模态框 begin -->
<div id="add-seat-type-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">增加座位类型</h4>
            </div>
            <div class="modal-body">
                <form id="add-seat-type-form" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">选择类型</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <select id="add-seat-type-list" class="form-control">
                                    <option>b</option>
                                    <option>c</option>
                                    <option>d</option>
                                    <option>e</option>
                                    <option>f</option>
                                    <option>g</option>
                                    <option>h</option>
                                    <option>i</option>
                                </select>
                                <span id="add-seat-type-color-show" class="input-group-addon"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">座位价格</label>
                        <div class="col-md-4">
                            <input id="add-seat-type-price" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">座位描述</label>
                        <div class="col-md-4">
                            <input id="add-seat-type-description" type="text" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="add-seat-type-btn" type="button" class="btn btn-primary center-block">增加类型</button>
            </div>
        </div>
    </div>
</div>
<!-- 增加类型模态框 end -->
<!-- 删除类型模态框 begin -->
<div id="delete-seat-type-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">删除座位类型</h4>
            </div>
            <div class="modal-body">
                <form id="delte-seat-type-form" class="form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">选择类型</label>
                        <div class="col-md-4">
                            <div class="input-group">
                                <select id="delete-seat-type-list" class="form-control"></select>
                                <span id="delete-seat-type-color-show" class="input-group-addon"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">座位价格</label>
                        <div class="col-md-4">
                            <input id="delete-seat-type-price" type="text" class="form-control" disabled>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-offset-2 col-md-2">座位描述</label>
                        <div class="col-md-4">
                            <input id="delete-seat-type-description" type="text" class="form-control" disabled>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="delete-seat-type-btn" type="button" class="btn btn-primary center-block">删除类型</button>
            </div>
        </div>
    </div>
</div>
<!-- 删除类型模态框 end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<!-- seat js -->
<script src="../../../js/jquery-seat-charts.js"></script>
<!-- datetimepicker -->
<script src="../../../js/bootstrap-datetimepicker.min.js"></script>
<!-- datetimepicker locale -->
<script src="../../../js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- 对座位操作的js -->
<script src="../../../js/seat-operation.js"></script>

<script>

    /**
     * addon移除当前颜色
     *
     * @param addon 当前的jquery对象
     */
    function addonRemoveColor(addon) {
        addon.removeClass();
        addon.addClass("input-group-addon");
    }

    /**
     * addon添加背景色
     *
     * @param addon 当前的addon的jquery对象
     * @param typeChar 表示座位的字母
     */
    function addonAddColor(addon, typeChar) {
        addon.addClass("seat-type-" + typeChar);
    }

    /**
     * add颜色变化
     *
     * @param addon 当前的addon的jquery对象
     * @param typeChar 表示座位的字母
     */
    function addonColorChange(addon, typeChar) {
        addonRemoveColor(addon);
        addonAddColor(addon, typeChar);
    }

    /**
     * 往下拉框中添加一个option
     * @param select jQuery对象
     * @param typeChar 表示该类型的字符
     */
    function appendSeatType(select, typeChar) {
        select.append("<option>" + typeChar + "</option>");
    }

    /**
     * 从下拉框中删除一个option
     * @param select 当前选中的下拉框jQuery对象
     * @param typeChar 表示该类型的字符
     */
    function removeSeatType(select, typeChar) {
        select.children().each(function () {
            if ($(this).text() === typeChar) {
                $(this).remove();
                return false;
            }
        });
    }

    /**
     * 清空数据及颜色
     * @param dataContainer 数据容器
     * @param addon 显示颜色的addon
     */
    function clearDataAndColor(dataContainer, addon) {
        dataContainer.find("select,input").val("");
        addonRemoveColor(addon);
    }

    /**
     * 指定单个座位类型
     */
    function appointSingleSeatType() {
        let rowAndColumn = this.node().attr("id").split("_");
        let row = rowAndColumn[0];
        let column = rowAndColumn[1];

        $("#single-seat-row").val(row);
        $("#single-seat-column").val(column);

    }

    /**
     * 渲染显示座位类型的表格
     */
    function renderSeatTypeTable() {
        let body = $("#seat-type-table-body");
        body.empty();
        $.each(seatInfo, function (typeChar, seatType) {
            //如果不是一个函数，说明是一个seatType
            if (!$.isFunction(seatType)) {
                body.append(
                    "<tr>" +
                    "<td class='seat-type-" + typeChar + "'" + ">" + typeChar + "</td>" +
                    "<td>" + seatType.price + "</td>" +
                    "<td>" + seatType.description + "</td>" +
                    "</tr>"
                );
            }
        });
    }

    $(document).ready(function () {

        //todo 开始日期改变后的限制
        //开始日期
        $("#plan-begin-datetimepicker").datetimepicker({
            language: 'zh-CN',          //语言
            format: "yyyy-mm-dd hh:ii", //时间显示格式
            weekStart: 1,               //一周开始时间
            startDate: new Date(),      //默认开始时间，之前的时间都被disabled
            todayBtn: 1,
            autoclose: 1,               //当选择一个日期后是否立即关闭此日期选择器
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            maxView: 3
        });

        //结束日期
        $("#plan-end-datetimepicker").datetimepicker({
            language: 'zh-CN',          //语言
            format: "yyyy-mm-dd hh:ii", //时间显示格式
            weekStart: 1,               //一周开始时间
            startDate: new Date(),      //默认开始时间，之前的时间都被disabled
            todayBtn: 1,
            autoclose: 1,               //当选择一个日期后是否立即关闭此日期选择器
            todayHighlight: 1,
            startView: 2,
            minView: 0,
            maxView: 3
        });

        //增加类型模态框显示监听
        $("#add-seat-type-modal").on("show.bs.modal", function () {
            //清空选择框的数据及颜色
            clearDataAndColor($(this), $("#add-seat-type-color-show"));
        });

        //删除类型模态框显示监听
        $("#delete-seat-type-modal").on("show.bs.modal", function () {
            //清空选择框的数据及颜色
            clearDataAndColor($(this), $("#delete-seat-type-color-show"));

            //检查select中是否存在option
            let deleteList = $("#delete-seat-type-list");
            if (deleteList.children().length === 0) {
                deleteList.attr("disabled", "disabled");
            }
            else {
                deleteList.removeAttr("disabled");
            }
        });

        //增加类型的select
        $("#add-seat-type-list").on("change", function () {
            addonColorChange($("#add-seat-type-color-show"), $(this).val());
        });

        //删除类型的select
        $("#delete-seat-type-list").on("change", function () {
            let deleteChar = $(this).val();
            console.log("被选中将要被删除的座位类型是: " + deleteChar);
            addonColorChange($("#delete-seat-type-color-show"), deleteChar);
            //显示价格及其描述
            $.each(seatInfo, function (typeChar, item) {
                if (typeChar === deleteChar) {
                    $("#delete-seat-type-price").val(item["price"]);
                    $("#delete-seat-type-description").val(item["description"]);
                }
            });
        });

        //增加类型监听
        $("#add-seat-type-btn").on("click", function () {
            let char = $("#add-seat-type-list").val();
            let price = $("#add-seat-type-price").val();
            let description = $("#add-seat-type-description").val();

            //如果有数据为空
            if (char === null || price.trim() === "" || description.trim() === "") {
                alert("座位类型信息不完整");
            }
            else {
                //添加一个legendInfo和seatInfo
                let newLegendInfo = [char, 'avalaible', description];
                legendInfo.items.push(newLegendInfo);
                seatInfo[char] = {
                    classes: "seat-type-" + char,
                    click: appointSingleSeatType,
                    price: price,
                    description: description
                };

                //当前选中option设为不可用，第一个未被disabled掉的元素设置为选中
                let curSelectedOption = $("#add-seat-type-list").children(":selected");
                curSelectedOption.attr("disabled", "disabled");
                let nextSelected = curSelectedOption.siblings(":not([disabled='disabled']):first");

                //还有可以添加的类型，设置显示的值
                if (nextSelected.length === 1) {
                    $("#add-seat-type-list").val(nextSelected.text());
                }
                //没有可以添加的类型，清空数据
                else {
                    clearDataAndColor($("#add-seat-type-modal"), $("#add-seat-type-color-show"));
                }

                //删除类型select及设置类型select增加一个选项
                appendSeatType($("#delete-seat-type-list"), char);
                appendSeatType($("#single-seat-type-list"), char);
                appendSeatType($("#row-seat-type-list"), char);

                //重绘
                rerenderSeats();

                //重绘表格
                renderSeatTypeTable();
            }
        });

        //删除类型监听
        $("#delete-seat-type-btn").on("click", function () {

            //删除选择框jqeury对象
            let deleteSelect = $("#delete-seat-type-list");

            let deleteChar = deleteSelect.val();

            //todo 检查当前座位当中是否还有此类型，有的话要提示

            //从legendInfo中删除这条座位类型
            legendInfo.removeItem(deleteChar);
            //从seatInfo中删除这条座位类型
            seatInfo.removeTypeChar(deleteChar);

            //增加类型模态框中的该选项设置为可选
            $("#add-seat-type-list").children().each(function () {
                if ($(this).text() === deleteChar) {
                    $(this).removeAttr("disabled");
                    return false;
                }
            });

            //删除类型模态框移除当前选项，选用剩余选项的第一个选项
            let curSelectedOption = deleteSelect.children(":selected");
            let nextSelected = curSelectedOption.siblings(":first");
            curSelectedOption.remove();

            //还有可以添加的类型，设置显示的值
            if (nextSelected.length === 1) {
                // 设置select框的值，并且手动触发select框的change事件
                deleteSelect.val(nextSelected.text());
                deleteSelect.change();
            }
            //没有可以添加的类型，清空数据
            else {
                clearDataAndColor($("#delete-seat-type-modal"), $("#delete-seat-type-color-show"));
            }

            //设置座位的select删除一个选项
            removeSeatType($("#single-seat-type-list"), deleteChar);
            removeSeatType($("#row-seat-type-list"), deleteChar);

            //重绘
            rerenderSeats();

            //重绘表格
            renderSeatTypeTable();
        });

        //设置一排座位类型监听
        $("#seat-map-container").on("click", ".seatCharts-row", function () {
            $("#whole-row").val($(this).children(":first").text());
        });

        //单个座位的确认指定按钮
        $("#single-seat-appoint-btn").on("click", function () {
            let row = $("#single-seat-row").val().trim();
            let column = $("#single-seat-column").val().trim();

            seatMap[row - 1][column - 1] = $("#single-seat-type-list").val().trim();
            rerenderSeats();
        });

        //一排座位的确认指定按钮
        $("#row-seat-appoint-btn").on("click", function () {
            let row = $("#whole-row").val().trim();
            let type = $("#row-seat-type-list").val().trim();

            $.each(seatMap[row - 1], function (index, val) {
                seatMap[row - 1][index] = val === '_' ? '_' : type;
            });

            rerenderSeats();
        });

        /**
         * 初始化界面要渲染的座位的信息
         */

        let venueInfo;
        $.ajax({
            url: "/venue/info",
            method: "get",
            async: false,     //同步请求
            success: function (data) {
                venueInfo = JSON.parse(data);
            },
            error: function () {
                console.log("错误了");
            }
        });

        //初始化seatMap
        initSeatMap(venueInfo.rowNum, venueInfo.columnNum, venueInfo.seatMap);

        //初始化座位的点击事件
        seatInfo.removeTypeChar('a');
        seatInfo.addTypeChar('a', {
            click: appointSingleSeatType,
            price: "/",
            description: "未指定"
        });
        //点击未指定座位设置类型监听
        seatInfo.addClick('a', appointSingleSeatType);

        //初始化座位类型
        legendInfo.removeItem('a');
        legendInfo.addItem(['a', 'available', '未指定']);

        renderSeats();

        renderSeatTypeTable();

        /**
         * 发布计划按钮监听
         */
        $("#release-plan-btn").on("click", function () {

            //开始/结束时间
            let begin = $("#plan-begin").val();
            let end = $("#plan-end").val();

            let showType = $("#show-type").val();
            let description = $("#plan-description").val();

            //根据seatInfo装载座位类型
            let seatTypes = [];
            $.each(seatInfo, function (typeChar, type) {
                //如果不是函数，说明是座位信息
                if (!$.isFunction(type)) {
                    //如果不是'a',说明是场馆自定义座位
                    if (typeChar !== 'a') {
                        seatTypes.push({
                            "typeChar": typeChar,
                            "price": type.price,
                            "description": type.description
                        });
                    }
                }
            });

            //根据seatMap装载具体座位及对应座位类型
            let venuePlanSeats = [];
            $.each(seatMap, function (rowIndex, rowInfo) {
                $.each(rowInfo, function (columnIndex, typeChar) {
                    venuePlanSeats.push({
                        "row": rowIndex + 1,
                        "column": columnIndex + 1,
                        "typeChar": typeChar + 1
                    });
                });
            });

            let venuePlan = {
                "begin": begin,
                "end": end,
                "showType": showType,
                "description": description,
                "seatTypes": seatTypes,
                "venuePlanSeats": venuePlanSeats
            };

            console.log();

            //todo 提交数据
            $.ajax({
                url: "/venue/addPlan",
                method: "post",
                data: JSON.stringify(venuePlan),
                success: function (data) {
                    console.log("成功发布");
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
