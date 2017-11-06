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
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body style="padding-top: 50px;">
	<div style="width:70%;margin:auto;margin-top:20%;">
		应付款:${payment }
		<input onclick="yes()" type="button" value="付款"/>
		<input onclick="no()" type="button" value="取消"/>
	</div>
</body>
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
	<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
<script type="text/javascript">
	function no(){
		window.location.href="${ctx}/home/gohome.shtml"
	}
	
	function yes(){
		window.location.href="${ctx}/order/paysuccess.shtml";
	}
</script>
</html>