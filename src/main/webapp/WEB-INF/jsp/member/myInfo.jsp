<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 13/03/2018
  Time: 15:55
  
  Description:  个人信息
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>小麦网</title>

    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <style rel="stylesheet">

        #info-panel {
            margin-top: 6%;
        }

        .sidenav {
            border: 1px solid #efefef;
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

        <%-- todo 替换text为${sessionScope.mail} --%>
        <p id="user-mail" class="navbar-text">${sessionScope.mail}</p>

        <!-- 导航内容 -->
        <ul class="nav navbar-nav">
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
            <!-- dropdown end -->
        </ul>
    </div>
</nav>
<!-- nav end -->

<div class="container" id="info-panel">
    <div class="row">
        <div class="col-md-2 col-md-offset-1">
            <ul class="nav nav-pills nav-stacked sidenav">
                <li class="active"><a href="#">个人信息</a></li>
                <li><a href="#">订单管理</a></li>
                <li><a href="#">我的钱包</a></li>
                <li><a href="#">我的优惠券</a></li>
            </ul>
        </div>
        <div class="col-md-7 col-md-offset-1">
            <!-- panel begin -->
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        个人信息
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-2 control-label">Email</label>
                            <div class="col-md-7">
                                <p class="form-control-static">${sessionScope.mail}</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">密码</label>
                            <div class="col-md-7">
                                <button type="button" class="btn btn-link" data-toggle="modal"
                                        data-target="#modify-password">
                                    修改密码
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-2 control-label">会员资格</label>
                            <div class="col-md-7">
                                <button type="button" class="btn btn-link" data-toggle="modal"
                                        data-target="#disqualify">
                                    取消资格
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- panel end -->
        </div>
    </div>
</div>

<!-- 模态框 -->
<!-- modify_password_modal begin -->
<div class="modal fade" id="modify-password" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">密码修改</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="modify_password_confirm_form" method="post">
                    <div class="form-group">
                        <label class="control-label col-md-3">原密码</label>
                        <div class="col-md-6">
                            <input type="password" class="form-control" id="oldPassword" placeholder="请输入原密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">新密码</label>
                        <div class="col-md-6">
                            <input type="password" class="form-control" id="newPassword" placeholder="请输入新密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">确认新密码</label>
                        <div class="col-md-6">
                            <input type="password" class="form-control" placeholder="请确认新密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="btn-group">
                    <button type="button" id="modify_password_confirm_btn" class="btn btn-primary">确认</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- modify_password_modal end -->
<!-- disqualify modal begin -->
<div class="modal fade" id="disqualify" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">确认取消会员资格</h4>
            </div>
            <div class="modal-footer">
                <form class="form-horizontal" method="post">
                    <div class="btn-group">
                        <button type="button" id="disqualify_confirm_btn" class="btn btn-primary">确认</button>
                        <button type="button" class="btn btn-primary" data-dismiss="modal">关闭
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- disqualify modal end -->

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $("#modify_password_confirm_btn").on("click", function () {
            alert("oldPassword: " + $("#oldPassword").val() + "newPassword: " + $("#newPassword").val());
            $.ajax({
                url: "/member/modifyPassword",
                method: "post",
                data: {
                    "oldPassword": $("#oldPassword").val(),
                    "newPassword": $("#newPassword").val()
                },
                dataType: "text",
                success: function (data, status) {
                    if (data === "true")
                        alert("密码修改成功");
                    else
                        alert("密码修改失败");
                },
                error: function () {
                    alert("修改密码失败");
                }
            });
        });

        $("#disqualify_confirm_btn").on("click", function () {
            $.ajax({
                url: "/member/disqualify",
                method: "get",
                dataType: "text",
                success: function (data, status) {
                    if (data === "true")
                        alert("取消资格成功");
                    else
                        alert("取消资格失败");
                },
                error: function () {
                    alert("取消资格失败");
                }
            });
        });
    });
</script>
</body>
</html>
