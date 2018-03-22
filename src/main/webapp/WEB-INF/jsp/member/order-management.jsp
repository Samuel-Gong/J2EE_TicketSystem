<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 22/03/2018
  Time: 00:32
  
  Description: 会员订单界面，显示未支付、已预订、已配票、已消费
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>订单管理</title>
    <!-- Normalize -->
    <link href="../../../css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <!-- footer -->
    <link href="../../../css/footer.css" rel="stylesheet">
    <style>
        .panel {
            margin-top: 20px;
        }

        .order-container {
            height: 600px;
            overflow: scroll;
        }
    </style>
</head>
<body>

<!-- nav begin -->
<%@include file="nav.jsp" %>
<!-- nav end -->

<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#unpaid" data-toggle="tab">未支付</a></li>
                <li><a href="#not-with-tickets" data-toggle="tab">未配票</a></li>
                <li><a href="#already-with-tickets" data-toggle="tab">已配票</a></li>
                <li><a href="#consumed" data-toggle="tab">已消费</a></li>
                <li><a href="#retreat" data-toggle="tab">已消费</a></li>
            </ul>
            <div id="all-orders" class="tab-content">
                <div id="unpaid" class="tab-pane fade in active">
                    <div id="unpaid-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        陈奕迅演唱会
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">演出城市:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">北京</p>
                                            </div>
                                            <label class="col-md-2 control-label">演出类型:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">演唱会</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                            <label class="col-md-2 control-label">结束时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">总票价:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">1200元</p>
                                            </div>
                                            <label class="col-md-3 control-label">座位：</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" style="overflow: scroll; resize: none;"
                                                          disabled>1排1座 1排2座 1排3座 1排4座 1排5座
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">支付时间:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">14:00</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-4">
                                                <button type="button" class="btn btn-primary center-block">去支付</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="not-with-tickets" class="tab-pane fade">
                    <div id="not-with-tickets-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        陈奕迅演唱会
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">演出地点:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">陈奕迅演唱会</p>
                                            </div>
                                            <label class="col-md-2 control-label">演出类型:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">演唱会</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                            <label class="col-md-2 control-label">结束时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                        </div>
                                        <%--<div class='col-md-3'>--%>
                                        <%--<button type='button' class='btn btn-primary paying'>去支付</button>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">总票价:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">1200元</p>
                                            </div>
                                            <label class="col-md-3 control-label">座位：</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" style="overflow: scroll; resize: none;"
                                                          disabled>1排1座 1排2座 1排3座 1排4座 1排5座
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-4">
                                                <button type="button" class="btn btn-primary center-block">去支付</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="already-with-tickets" class="tab-pane fade">
                    <div id="already-with-tickets-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        陈奕迅演唱会
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">演出地点:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">陈奕迅演唱会</p>
                                            </div>
                                            <label class="col-md-2 control-label">演出类型:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">演唱会</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                            <label class="col-md-2 control-label">结束时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                        </div>
                                        <%--<div class='col-md-3'>--%>
                                        <%--<button type='button' class='btn btn-primary paying'>去支付</button>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">总票价:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">1200元</p>
                                            </div>
                                            <label class="col-md-3 control-label">座位：</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" style="overflow: scroll; resize: none;"
                                                          disabled>1排1座 1排2座 1排3座 1排4座 1排5座
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-4">
                                                <button type="button" class="btn btn-primary center-block">去支付</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="consumed" class="tab-pane fade">
                    <div id="consumed-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        陈奕迅演唱会
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">演出地点:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">陈奕迅演唱会</p>
                                            </div>
                                            <label class="col-md-2 control-label">演出类型:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">演唱会</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                            <label class="col-md-2 control-label">结束时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                        </div>
                                        <%--<div class='col-md-3'>--%>
                                        <%--<button type='button' class='btn btn-primary paying'>去支付</button>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">总票价:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">1200元</p>
                                            </div>
                                            <label class="col-md-3 control-label">座位：</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" style="overflow: scroll; resize: none;"
                                                          disabled>1排1座 1排2座 1排3座 1排4座 1排5座
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-4">
                                                <button type="button" class="btn btn-primary center-block">去支付</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="retreat" class="tab-pane fade">
                    <div id="retreat-container" class="order-container">
                        <div class="col-md-offset-1 col-md-10 order-container">
                            <div class="panel panel-warning">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        陈奕迅演唱会
                                    </h4>
                                </div>
                                <div class="panel-body">
                                    <form class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">演出地点:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">陈奕迅演唱会</p>
                                            </div>
                                            <label class="col-md-2 control-label">演出类型:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">演唱会</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-offset-1 col-md-2 control-label">开始时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                            <label class="col-md-2 control-label">结束时间:</label>
                                            <div class="col-md-3">
                                                <p class="form-control-static">2016-10-10 10:10</p>
                                            </div>
                                        </div>
                                        <%--<div class='col-md-3'>--%>
                                        <%--<button type='button' class='btn btn-primary paying'>去支付</button>--%>
                                        <%--</div>--%>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">总票价:</label>
                                            <div class="col-md-2">
                                                <p class="form-control-static">1200元</p>
                                            </div>
                                            <label class="col-md-3 control-label">座位：</label>
                                            <div class="col-md-4">
                                                <textarea class="form-control" style="overflow: scroll; resize: none;"
                                                          disabled>1排1座 1排2座 1排3座 1排4座 1排5座
                                                </textarea>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-md-offset-4 col-md-4">
                                                <button type="button" class="btn btn-primary center-block">去支付</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>

<script>

</script>
</body>
</html>
