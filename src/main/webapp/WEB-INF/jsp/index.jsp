<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 05/03/2018
  Time: 12:46

  Description:  售票网站主页
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>小麦网</title>

    <!-- Normalize -->
    <link href="../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <!-- 模态框的css -->
    <link href="../../css/index.css" rel="stylesheet">
    <!-- footer的css -->
    <link href="../../css/footer.css" rel="stylesheet">

    <style>
        .panel {
            margin-top: 5%;
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

        <p id="user-mail" class="navbar-text hidden"></p>

        <!-- 导航内容 -->
        <ul class="nav navbar-nav">
            <li id="login-li"><a href="#" data-toggle="modal" data-target="#loginModal">登录</a></li>
            <li id="register-li"><a href="#" data-toggle="modal" data-target="#registerModal">注册</a></li>
        </ul>

        <ul class="nav navbar-nav pull-right">
            <li id="venue-login"><a href="#venueModal" data-toggle="modal">场馆登录</a></li>
            <li id="manager-login"><a href="#managerModal" data-toggle="modal">经理登录</a></li>
        </ul>
    </div>
</nav>
<!-- nav end -->

<div id="plans-container" class="container" style="height: 100%; overflow: scroll">
    <div class="row">
        <h2 class="text-center">近期演出</h2>
    </div>
</div>


<%--todo 分页 --%>
<%--<div id="pagination-container" class="container">--%>
<%--<div class="row">--%>
<%--<div class="col-md-offset-3 col-md-6 text-center">--%>
<%--<ul class="pagination pagination-lg">--%>
<%--<li><a href="#">&laquo;</a></li>--%>
<%--<li><a href="#">1</a></li>--%>
<%--<li><a href="#">2</a></li>--%>
<%--<li><a href="#">3</a></li>--%>
<%--<li><a href="#">4</a></li>--%>
<%--<li><a href="#">5</a></li>--%>
<%--<li><a href="#">&raquo;</a></li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>

<footer></footer>

<!-- 模态框 -->
<!-- login 模态框 -->
<div class="modal fade" id="loginModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">会员登录</h4>
            </div>
            <div class="modal-body">
                <form id="login_form" action="/member/login" method="post">
                    <div class="input-group">
                        <span class="input-group-addon">邮箱</span>
                        <input type="text" class="form-control" name="mail" placeholder="请输入邮箱">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">密码</span>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="login_btn" class="btn btn-primary login_btn center-block">登录</button>
            </div>
        </div>
    </div>
</div>
<!-- login 模态框end -->

<!-- register 模态框 -->
<div class="modal fade" id="registerModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">会员注册</h4>
            </div>
            <div class="modal-body">
                <form id="reg_form" action="/member/register" method="post">
                    <div class="input-group">
                        <span class="input-group-addon">邮箱</span>
                        <input type="email" class="form-control" name="mail" placeholder="请输入验证邮箱">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">密码</span>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码">
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon">确认密码</span>
                        <input type="password" class="form-control" placeholder="请确认密码">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="reg_btn" class="btn btn-primary reg_btn center-block">注册</button>
            </div>
        </div>
    </div>
</div>
<!-- register模态框 end -->

<!-- venue login 模态框 -->
<div class="modal fade" id="venueModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">场馆登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="venue-login-form" action="/venue/login" method="post">
                    <div class="form-group">
                        <label class="col-md-offset-1 col-md-3 control-label">编号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="venue-id" placeholder="请输入编号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-offset-1 col-md-3 control-label">密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" name="venue-password" placeholder="请输入密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="btn-group">
                    <button type="button" id="venue-login-btn" class="btn btn-default">登录</button>
                    <button type="button" id="venue-register-btn" class="btn btn-primary">注册</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- venue login 模态框 end -->

<!-- manager login 模态框 -->
<div class="modal fade" id="managerModal" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!-- data-dismiss：关闭模态窗口 -->
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center">经理</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="manager-login-form" action="/manager/login" method="post">
                    <div class="form-group">
                        <label class="col-md-offset-1 col-md-3 control-label">账号</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="manager-id" placeholder="请输入账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-offset-1 col-md-3 control-label">密码</label>
                        <div class="col-md-7">
                            <input type="password" class="form-control" name="manager-password" placeholder="请输入密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" id="manager-login-btn" class="btn btn-default">登录</button>
            </div>
        </div>
    </div>
</div>
<!-- manager login 模态框 end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../js/bootstrap.min.js"></script>
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

        if (${sessionScope.containsKey("mail")}) {
            console.log("contains mail");
            console.log("${sessionScope.get("mail")}");
            var user_mail = $("#user-mail");
            user_mail.removeClass("hidden");
            user_mail.addClass("show");
            user_mail.text("${sessionScope.get("mail")}");

            $("#login-li").addClass("hidden");
            $("#register-li").addClass("hidden");
        }

        comingShows = ${comingShows};
        console.log(comingShows);

        $.each(comingShows, function (index, show) {
            $("#plans-container").append(
                "<div class=\"row\">\n" +
                "    <div class=\"col-md-offset-3 col-md-6\">\n" +
                "        <div class=\"panel panel-default\">\n" +
                "            <div class=\"panel-body\">\n" +
                "                <input type=\"text\" value=\"" + show.venuePlanId + "\" hidden>\n" +
                "                <button type=\"button\" class=\"btn btn-primary pull-right\">查看详情</button>\n" +
                "                <form class=\"form-horizontal\" role=\"form\">\n" +
                "                    <div class=\"form-group\">\n" +
                "                        <label class=\"col-md-offset-2 col-md-3 control-label\">开始时间：</label>\n" +
                "                        <div class=\"col-md-4\">\n" +
                "                            <p class=\"form-control-static\">" + show.begin + "</p>\n" +
                "                        </div>\n" +
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
                "</div>");
        });
    });
</script>
</body>
</html>