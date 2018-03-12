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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>小麦网</title>

    <!-- Normalize -->
    <link href="../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <style>
        .modal-dialog {
            margin-top: 8%;
            padding-left: 7%;
            padding-right: 7%;
        }

        .modal-dialog .login_btn, .reg_btn {
            width: 35%;
        }

        .modal-dialog form {
            width: 80%;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 5%;
        }

        .modal-dialog form .input-group {
            margin-top: 8%;
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

        <p id="user-mail" class="navbar-text hidden">哈哈哈</p>

        <!-- 导航内容 -->
        <ul class="nav navbar-nav">
            <li id="login-li"><a href="#" data-toggle="modal" data-target="#loginModal">登录</a></li>
            <li id="register-li"><a href="#" data-toggle="modal" data-target="#registerModal">注册</a></li>

            <!-- 导航栏中的下拉菜单 注意下拉菜单的toggle是a标签 而不是button标签 -->
            <!-- dropdown begin -->
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    我的小麦
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">个人信息</a></li>
                    <li><a href="#">订单管理</a></li>
                    <li><a href="#">我的钱包</a></li>
                    <li><a href="#">我的优惠券</a></li>
                </ul>
            </li>

            <li><a href="#">我的订单</a></li>
            <!-- dropdown end -->
        </ul>
    </div>
</nav>
<!-- nav end -->

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

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $("#login_btn").on("click", function () {
            $("#login_form").submit();
        });

        $("#reg_btn").on("click", function () {
            $("#reg_form").submit();
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
    });
</script>
</body>
</html>