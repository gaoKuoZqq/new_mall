<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商城首页</title>
</head>
<body style="padding-top: 50px;">
	<jsp:include page="common.jsp" />
	<div class="row">
		<!-- 左侧留出1个格子 -->
		<div class="col-md-1 col-sm-1"></div>
		<!-- 展示区域 开始-->
		<div class="col-md-10 col-sm-10">
		<table class="table">
		<tr>
			<td>花费</td>
			<td>消费时间</td>
			<td>查看详情</td>
		</tr>
		<c:forEach items="${orders }" var="order">
			<tr>
				<td>${order.payment }</td>
				<td>${order.update_time }</td>
				<td><a href="javaScript:open('${order.order_no}')">查看详情</a></td>
			</tr>
		</c:forEach>
		</table>
		</div>
		<!-- 展示区域 结束-->
		<!-- 右侧留出1个格子 -->
		<div class="col-md-1 col-sm-1"></div>
	</div>
</body>
<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
<script type="text/javascript">
function open(order_no){
    layer.open({
        type:2,//（iframe层）
        title:'订单详情',
        area: ['1100px', '500px'],
        offset: '50px',//只定义top坐标，水平保持居中
        content:"${ctx}/orderItem/see.shtml?order_no="+order_no+""
    });
}
</script>
</html>