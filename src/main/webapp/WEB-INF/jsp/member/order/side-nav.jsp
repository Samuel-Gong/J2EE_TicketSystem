<%--
  Created by IntelliJ IDEA.
  User: Shenmiu
  Date: 26/03/2018
  Time: 16:15
  
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<ul id="nav-side" class="nav nav-pills nav-stacked">
    <li id="unpaid-li"><a href="${pageContext.request.contextPath}/member/order/unpaid">等待支付</a></li>
    <li id="booked-li"><a href="${pageContext.request.contextPath}/member/order/booked">已预订</a></li>
    <li id="consumed-li"><a href="${pageContext.request.contextPath}/member/order/consumed">已消费</a></li>
    <li id="retreat-li"><a href="${pageContext.request.contextPath}/member/order/retreat">已退订</a></li>
    <li id="cancel-li"><a href="${pageContext.request.contextPath}/member/order/cancel">已取消</a></li>
</ul>
