<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
	<script src="${ctx }/resource/lib/bootstrap.min.js"></script>
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<table class="table">
		<tr>
			<td>商品</td>
			<td>预览</td>
			<td>单价</td>
			<td>数量</td>
			<td>总价</td>
		</tr>
		<c:forEach items="${order_items }" var="order_item">
		<tr>
			<td>${order_item.product_name }</td>
			<td>
			<img alt="" src="/pic/${order_item.product_image}" style="width:50px;height:40px">
			</td>
			<td>${order_item.current_unit_price }</td>
			<td>${order_item.quantity }</td>
			<td>${order_item.total_price }</td>
		</tr>
		</c:forEach>
	</table>
</body>
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
</script>
</html>