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
                            <label class="col-md-3 control-label">Email</label>
                            <div class="col-md-7">
                                <p id="member-mail" class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">支付宝账户</label>
                            <div class="col-md-7">
                                <p id="is-bind-account" class="form-control-static">已绑定</p>
                                <button id="bind-account-btn" type="button" class="btn btn-link" data-toggle="modal"
                                        data-target="#bind-account">
                                    去绑定
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">会员积分</label>
                            <div class="col-md-7">
                                <p id="member-points" class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">总消费</label>
                            <div class="col-md-7">
                                <p id="member-total-consumption" class="form-control-static"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">会员等级</label>
                            <div class="col-md-3">
                                <p class="form-control-static"><span>Lv.</span><span id="member-level"></span></p>
                            </div>
                            <label class="col-md-3 control-label">享受优惠</label>
                            <div class="col-md-3">
                                <p class="form-control-static"><span id="member-discount"></span>折</p>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">密码</label>
                            <div class="col-md-7">
                                <button type="button" class="btn btn-link" data-toggle="modal"
                                        data-target="#modify-password">
                                    修改密码
                                </button>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">会员资格</label>
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
<!-- bind-account modal begin -->
<div class="modal fade" id="bind-account" data-backdrop="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">绑定支付宝</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="bind-account-form" method="post">
                    <div class="form-group">
                        <label class="control-label col-md-3">账户</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" id="account-id" placeholder="请输入支付宝账户">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">支付密码</label>
                        <div class="col-md-6">
                            <input type="password" class="form-control" id="account-password" placeholder="请输入支付密码">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <div class="btn-group">
                    <button type="button" id="bind-account-confirm-btn" class="btn btn-primary">确认</button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- bind-account modal end -->

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

        /**
         * 请求会员信息显示（不包含等级和折扣）
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/info",
            method: "get",
            data: {
                memberId: "${sessionScope.mail}"
            },
            success: function (info) {
                console.log(info);
                //显示会员的邮箱、积分
                $("#member-mail").text(info.mail);
                $("#member-points").text(info.points);
                $("#member-total-consumption").text(info.totalConsumption);

                //已经绑定支付宝账户，去绑定支付宝账户按钮隐藏
                if (info.bindAccount === true) {
                    $("#bind-account-btn").addClass("hidden");
                }
                else {
                    $("#is-bind-account").addClass("hidden");
                }
            },
            error: function () {
                alert("请求会员信息失败");
            }
        });

        /**
         * 请求显示会员等级和折扣
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/discount",
            method: "get",
            data: {
                memberId: "${sessionScope.mail}"
            },
            success: function (levelAndDiscount) {
                //显示会员的邮箱和信息
                $("#member-level").text(levelAndDiscount.level);
                $("#member-discount").text(levelAndDiscount.discount);
            },
            error: function () {
                alert("请求会员等级和折扣失败");
            }
        });

    });

    /**
     * 绑定支付宝账户监听
     */
    $("#bind-account-confirm-btn").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/member/bindAccount",
            method: "post",
            data: {
                "id": $("#account-id").val(),
                "password": $("#account-password").val()
            },
            success: function (data) {
                if (data === true) {
                    alert("绑定支付宝成功");
                    window.location.reload();
                }
                else
                    alert("绑定支付宝失败");
            },
            error: function () {
                alert("绑定支付宝失败");
            }
        });
    });

    /**
     * 修改密码监听
     */
    $("#modify_password_confirm_btn").on("click", function () {
        console.log("oldPassword: " + $("#oldPassword").val() + "newPassword: " + $("#newPassword").val());
        $.ajax({
            url: "${pageContext.request.contextPath}/member/modifyPassword",
            method: "post",
            data: {
                "oldPassword": $("#oldPassword").val(),
                "newPassword": $("#newPassword").val()
            },
            success: function (data) {
                if (data === true) {
                    alert("密码修改成功");
                    window.location.reload();
                }
                else
                    alert("密码修改失败");
            },
            error: function () {
                alert("修改密码失败");
            }
        });
    });

    /**
     * 取消资格监听
     */
    $("#disqualify_confirm_btn").on("click", function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/member/disqualify",
            method: "get",
            success: function (data) {
                if (data === true)
                    alert("取消资格成功");
                else
                    alert("取消资格失败");
            },
            error: function () {
                alert("取消资格失败");
            }
        });
    });
</script>
</body>
</html>
