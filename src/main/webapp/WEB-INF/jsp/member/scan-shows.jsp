<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 21/03/2018
  Time: 10:12
  
  Description: 近期演出
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
        <c:forEach items="${comingShows}" var="show">
            <div class="row">
                <div class="col-md-offset-2 col-md-8">
                    <div class="panel panel-default">
                        <div class='panel-heading'>
                            <h3 class='panel-title'><c:out value="${show.description}"/></h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" role="form">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">演出地点：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${show.city} ${show.venueName}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-offset-1 col-md-2 control-label">演出类型：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${show.showType}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">开始时间：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${show.begin}"/>
                                        </p>
                                    </div>
                                    <label class="col-md-offset-1 col-md-2 control-label">结束时间：</label>
                                    <div class="col-md-3">
                                        <p class="form-control-static">
                                            <c:out value="${show.end}"/>
                                        </p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-4">
                                        <input type="text" value="<c:out value="${show.venuePlanId}"/>" hidden>
                                        <button type="button" class="btn btn-primary center-block booking">去买票</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<footer></footer>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../../../js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="../../../js/bootstrap.min.js"></script>

<script>

    $(document).ready(function () {
        //去买票按钮添加监听
        $("#plans-container").on("click", ".booking", function () {
            let venuePlanId = $(this).prev().val();
            // console.log(venuePlanId);
            $(location).attr("href", "/member/booking/" + venuePlanId);
        });
    });
</script>
</body>
</html>
