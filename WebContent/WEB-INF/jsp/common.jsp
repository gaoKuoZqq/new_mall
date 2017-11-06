<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.mall.*" %>
<%@page import="java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
	<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
	<script src="${ctx }/resource/lib/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container" style="height: 30px; margin: 8px auto 0px;">
			<ol class="breadcrumb" style="margin-top: 7px;  margin: auto; float: left;">
			<c:if test="${empty user }">
				<li style=";margin-left: 30px;"><a style="color:red" href="javaScript:openLogin()">登录</a></li>
				<li><a href="javaScript:openGoAddUser()">注册</a></li>
			</c:if>
			<c:if test="${not empty user }">
				<li><a style="color:red">${user.username }</a></li>
				<li><a href="javaScript:logOut()">注销</a></li>
			</c:if>
			</ol>
			<div style="position:absolute;left:50%;margin-left:-200px">
				<form action="${ctx }/product/find.shtml" method="post">
					<input type="hidden" name="product.category_id" value="${pageBean.product.category_id }"/>
					<input type="text" class="form-control" style="width:220px;float:left;"
						name="product.name" value="${pageBean.product.name }"/>
					<input class="btn btn-primary" type="submit"
						style="float:left;margin-left:10px;" value="find"/>
				</form>
			</div>
			<ol class="breadcrumb" style="margin-top: 7px;  margin: auto; float: right;">
				<li><a href="${ctx }/home/gohome.shtml">首页</a></li>
				<li><a href="${ctx }/cart/gocart.shtml">购物车</a></li>
				<li><a href="${ctx }/order/goMyOrder.shtml">我的主页</a></li>
			</ol>
		</div>
	</nav>
</body>
<script>
    function openLogin(){
        layer.open({
            type:2,//（iframe层）
            title:'用户登录',
            area: ['350px', '300px'],
            offset: '50px',//只定义top坐标，水平保持居中
            content:"${ctx}/user/gologin.shtml"
        });
    }
    
    function openGoAddUser(){
        layer.open({
            type:2,//（iframe层）
            title:'用户登录',
            area: ['350px', '550px'],
            offset: '50px',//只定义top坐标，水平保持居中
            content:"${ctx}/user/goadd.shtml"
        });
    }

    function logOut(){
    	$.ajax(
    		"${ctx}/user/logout.shtml",
    	)
    	window.location.reload();
    }
</script>
</html>