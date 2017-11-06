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
		<div class="col-md-4 col-sm-4"></div>
		<!-- 展示区域 开始-->
		<div class="col-md-4 col-sm-4" style="margin-top:200px">
		<h3>付款成功,欢迎<a style="color:red" href="${ctx }/home/gohome.shtml">继续选购</a></h3>
		</div>
		<!-- 展示区域 结束-->
		<!-- 右侧留出1个格子 -->
		<div class="col-md-4 col-sm-4"></div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>