<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 21/03/2018
  Time: 10:12
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>小麦网</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- 模态框的css -->
    <link href="../../../css/index.css" rel="stylesheet">
    <!-- footer的css -->
    <link href="../../../css/footer.css" rel="stylesheet">
    <!-- plan panel -->
    <link href="../../../css/plan-panel.css" rel="stylesheet">

</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<!-- plan container -->
<%--<%@include file="../../../html/plan-container.html" %>--%>
<div id="plans-container" class="container" style="height: 100%; overflow: scroll">
    <div class="row">
        <h2 class="text-center">近期演出</h2>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>

<script>

    let comingShows = null;

    $(document).ready(function () {

        //会员登录
        $("#login_btn").on("click", function () {
            $("#login_form").submit();
        });

        //会员注册
        $("#reg_btn").on("click", function () {
            $("#reg_form").submit();
        });

        //场馆登录
        $("#venue-login-btn").on("click", function () {
            $("#venue-login-form").submit();
        });

        //场馆注册
        $("#venue-register-btn").on("click", function () {
            $(location).attr("href", "/venue/registerView");
        });

        //经理登录
        $("#manager-login-btn").on("click", function () {
            $("#manager-login-form").submit();
        });

        //去买票按钮添加监听
        $("#plans-container").on("click", ".booking", function () {
            // console.log("选中了");
            // console.log($(this).parent().parent().parent().prev().html());
            let venuePlanId = $(this).parent().parent().parent().prev().val();
            $(location).attr("href", "/member/booking/" + venuePlanId);
        });

        if (${sessionScope.containsKey("mail")}) {
            var user_mail = $("#user-mail");
            user_mail.removeClass("hidden");
            user_mail.addClass("show");
            user_mail.text("${sessionScope.get("mail")}");

            $("#login-li").addClass("hidden");
            $("#register-li").addClass("hidden");
        }

        comingShows = ${comingShows};
        // console.log(comingShows);

        $.each(comingShows, function (index, show) {
            $("#plans-container").append(
                "<div class=\"row\">\n" +
                "    <div class=\"col-md-offset-3 col-md-6\">\n" +
                "        <div class=\"panel panel-default\">\n" +
                "            <div class=\"panel-body\">\n" +
                "                <input type=\"text\" value=\"" + show.venuePlanId + "\" hidden>\n" +
                "                <form class=\"form-horizontal\" role=\"form\">\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"col-md-offset-2 col-md-3 control-label\">开始时间：</label>\n" +
                "                        <div class=\"col-md-4\">\n" +
                "                            <p class=\"form-control-static\">" + show.begin + "</p>\n" +
                "                        </div>\n" +
                "                       <div class='col-md-3'>" +
                "                            <button type='button' class='btn-sm btn-primary booking'>去买票</button>" +
                "                       </div>" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"col-md-offset-2 col-md-3 control-label\">结束时间：</label>\n" +
                "                        <div class=\"col-md-4\">\n" +
                "                            <p class=\"form-control-static\">" + show.end + "</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"col-sm-offset-2 col-sm-3 control-label\">类型：</label>\n" +
                "                        <div class=\"col-sm-4\">\n" +
                "                            <p class=\"form-control-static\">" + show.showType + "</p>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"col-sm-offset-2 col-sm-3 control-label\">描述：</label>\n" +
                "                        <div class=\"col-sm-5\">\n" +
                "                                <textarea class=\"form-control\" style=\"overflow: scroll; resize: none;\" disabled>" + show.description +
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
    });
</script>
</body>
</html>
