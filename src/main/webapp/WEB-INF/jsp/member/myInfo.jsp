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
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的信息</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style rel="stylesheet">

        #info-panel {
            margin-top: 6%;
        }

    </style>
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<div class="container" id="info-panel">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
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
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>
    $(document).ready(function () {
        $("#modify_password_confirm_btn").on("click", function () {
            alert("oldPassword: " + $("#oldPassword").val() + "newPassword: " + $("#newPassword").val());
            $.ajax({
                url: "${pageContext.request.contextPath}/member/modifyPassword",
                method: "post",
                data: {
                    "oldPassword": $("#oldPassword").val(),
                    "newPassword": $("#newPassword").val()
                },
                dataType: "text",
                success: function (data, value) {
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
                url: "${pageContext.request.contextPath}/member/disqualify",
                method: "get",
                dataType: "text",
                success: function (data, value) {
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
