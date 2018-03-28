<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 2018/3/28
  Time: 19:52

  Description: 会员优惠券视图
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的优惠券</title>

    <!-- Normalize -->
    <link href="${pageContext.request.contextPath}/css/normalize.css" rel="stylesheet">
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <style rel="stylesheet">
        table {
            text-align: center;
            table-layout: fixed;
        }

        caption {
            text-align: center;
        }

        td {
            text-align: center;
            white-space: nowrap;
            overflow: scroll;
            /*text-overflow: ellipsis;*/
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
                        我的优惠券
                    </h3>
                </div>
                <div class="panel-body">
                    <form id="coupon-form" class="form-horizontal">
                    </form>
                </div>
                <div class="panel-footer">
                    <p>剩余积分：<span id="points"></span></p>
                </div>
            </div>
            <!-- panel end -->
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <table class="table table-bordered text-center">
                <caption>可兑换优惠券</caption>
                <thead>
                <tr>
                    <th class="text-center">面值（元）</th>
                    <th class="text-center">所需积分</th>
                    <th class="text-center"></th>
                </tr>
                </thead>
                <tbody id="coupon-types">
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script>

    $(document).ready(function () {

        /**
         * 获取所有类型的优惠券
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/couponType",
            method: "get",
            success: function (couponTypes) {
                console.log(couponTypes);
                $.each(couponTypes, function (index, coupon) {
                    $("#coupon-types").append("" +
                        "<tr>\n" +
                        "                    <td>" + coupon.value + "</td>\n" +
                        "                    <td>" + coupon.requiredPoints + "</td>\n" +
                        "                    <td>" +
                        "<button type='button' class='btn btn-link exchange-btn' >兑换一张</button>" +
                        "</td>\n" +
                        "                </tr>");

                    $("#coupon-form").append("" +
                        "<div class=\"form-group\">\n" +
                        "   <label class=\"col-md-2 control-label\">" + coupon.value + "元券:</label>\n" +
                        "   <div class=\"col-md-7\">\n" +
                        "       <p class=\"form-control-static\">" +
                        "          <span id=\"" + coupon.value + "-yuan-coupon\">0</span>张" +
                        "       </p>\n" +
                        "   </div>\n" +
                        "</div>");
                });
            }
        });

        /**
         * 获取会员所拥有的优惠券及积分
         */
        $.ajax({
            url: "${pageContext.request.contextPath}/member/coupons",
            method: "get",
            data: {
                memberId: "${sessionScope.mail}"
            },
            success: function (pointsAndCoupons) {
                console.log(pointsAndCoupons);
                $("#points").text(pointsAndCoupons.points);
                let coupons = pointsAndCoupons.coupons;
                $.each(coupons, function (value, remain) {
                    //显示剩余张数
                    $("#" + value + "-yuan-coupon").text(remain);
                });
            },
            error: function () {
                alert("获取会员积分和优惠券失败");
            }
        });

        $("#coupon-types").on("click", ".exchange-btn", function () {
            let requiredPoints = parseInt($(this).parent().prev().text());
            let memberPoints = parseInt($("#points").text());
            let couponValue = parseInt($(this).parent().prev().prev().text());
            console.log("需要积分:" + requiredPoints);
            console.log("会员积分:" + memberPoints);
            console.log(couponValue);
            if (requiredPoints > memberPoints) {
                alert("当前积分不足，不可以兑换" + couponValue + "元券");
            }
            else {
                alert("积分足够");
                //给当前用户增加一张优惠券
                $.ajax({
                    url: "${pageContext.request.contextPath}/member/exchangeCoupon",
                    data: {
                        memberId: "${sessionScope.mail}",
                        couponValue: couponValue,
                    },
                    success: function (data) {
                        if (data === true) {
                            alert("兑换优惠券成功");
                            //更改会员积分
                            $("#points").text(memberPoints - requiredPoints);
                            //更改优惠券张数
                            let addCoupon = $("#" + couponValue + "-yuan-coupon");
                            let curCoupon = addCoupon.text();
                            addCoupon.text(parseInt(curCoupon) + 1);
                        }
                        else {
                            alert("未知原因：兑换优惠券失败");
                        }
                    },
                    error: function () {
                        alert("兑换优惠券失败");
                    }
                });
            }
        });
    });
</script>
</body>
</html>
