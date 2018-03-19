<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 19/03/2018
  Time: 21:03
  
  Description: 查看计划界面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <title>查看计划</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../css/footer.css" rel="stylesheet">

    <style>
        .panel {
            margin-top: 5%;
        }
    </style>
</head>
<body>
<%-- nav begin --%>
<%@include file="../../../html/venue/venue-nav.html" %>
<%-- nav end --%>

<div id="plans-container" class="container">
    <div class="row">
        <h2 class="text-center">近期计划</h2>
    </div>
</div>
<%@include file="../../../html/footer.html" %>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>
<script>
    //todo 获取数据

    //声明全局变量，保存场馆计划
    let allPlans = [];

    $(document).ready(function () {
        $.ajax({
            url: "/venue/getAllPlan",
            method: "get",
            async: false,     //同步请求
            success: function (data) {
                allPlans = JSON.parse(data);
            },
            error: function () {
                console.log("错误了");
            }
        });

        if (allPlans.length !== 0) {
            $.each(allPlans, function (index, plan) {
                $("#plans-container").append(
                    "<div class=\"row\">\n" +
                    "    <div class=\"col-md-offset-3 col-md-6\">\n" +
                    "        <div class=\"panel panel-default\">\n" +
                    "            <div class=\"panel-body\">\n" +
                    "                <input type=\"text\" value=\"" + plan.venuePlanId + "\" hidden>\n" +
                    "                <button type=\"button\" class=\"btn btn-primary pull-right\">查看详情</button>\n" +
                    "                <form class=\"form-horizontal\" role=\"form\">\n" +
                    "                    <div class=\"form-group\">\n" +
                    "                        <label class=\"col-md-offset-2 col-md-3 control-label\">开始时间：</label>\n" +
                    "                        <div class=\"col-md-4\">\n" +
                    "                            <p class=\"form-control-static\">" + plan.begin + "</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"form-group\">\n" +
                    "                        <label class=\"col-md-offset-2 col-md-3 control-label\">结束时间：</label>\n" +
                    "                        <div class=\"col-md-4\">\n" +
                    "                            <p class=\"form-control-static\">" + plan.end + "</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"form-group\">\n" +
                    "                        <label class=\"col-sm-offset-2 col-sm-3 control-label\">类型：</label>\n" +
                    "                        <div class=\"col-sm-4\">\n" +
                    "                            <p class=\"form-control-static\">" + plan.showType + "</p>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                    <div class=\"form-group\">\n" +
                    "                        <label class=\"col-sm-offset-2 col-sm-3 control-label\">描述：</label>\n" +
                    "                        <div class=\"col-sm-5\">\n" +
                    "                                <textarea class=\"form-control\" style=\"overflow: scroll; resize: none;\" disabled>" + plan.description +
                    "                                </textarea>\n" +
                    "                        </div>\n" +
                    "                    </div>\n" +
                    "                </form>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>"
                );
            });

            $("#plans-container").on("click", ".panel-body .btn", function () {
                //跳转
                let venuePlanId = $(this).siblings("input").val();
                $(location).href = "/venue/plan-detail/" + venuePlanId;
            });

            //todo 分页
        }
        else {
            //todo 显示近期没有场馆计划
            console.log("近期没有场馆计划");
        }
    });
</script>
</body>
</html>
