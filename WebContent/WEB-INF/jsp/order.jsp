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
			<hr/>
			<!-- 收货地址 -->
			<div style="height:40px; margin-left:80px;">
				<p><a href="javaScript:openAddShipping()" >添加新地址</a>,最多存储三条收货信息</p>
			</div>
			<div class="row" style="height:150px;width:100%">
			<div class="col-md-1 col-sm-1" ></div>
			<c:forEach items="${shippingsList }" var="shipping" varStatus="status">
			<c:if test="${status.count < 4 }">
				<div class="col-md-3 col-sm-3" style="height:100%;background-color:#FFFFCC;margin:15px;padding:10px;">
					<div style="float:right;background-color:gray;width:20px;height:20px;">
					<c:if test="${status.first }">
					<input checked="checked" type="radio" value="${shipping.id }" name="shippingBoxItem" style="display:block;margin:auto;margin-top:4px;"/>
					</c:if>
					<c:if test="${!status.first }">
					<input type="radio" value="${shipping.id }" name="shippingBoxItem" style="display:block;margin:auto;margin-top:4px;"/>
					</c:if>
					</div>
					<a style="float:right;margin-right:20px;" href="javaScript:deleteShipping(${shipping.id })">删除</a>
					<div style="height:40%;width:80%">
						<p>${shipping.receiver_name }&nbsp;&nbsp;&nbsp;${shipping.receiver_mobile }</p>
					</div>
					<div style="height:30%;width:80%">
						<p>${shipping.receiver_province }&nbsp;&nbsp;${shipping.receiver_city }&nbsp;&nbsp;${shipping.receiver_district }</p>
					</div>
					<div style="height:30%;width:80%">
						<p>${shipping.receiver_address }</p>
					</div>
				</div>
			</c:if>
			</c:forEach>
			</div>
			<hr/>
			<div style="height:50px; margin-left:80px;">
				<p>付款方式: <span style="color:blue;font-size: 16px;">在线付款</span></p>
				<hr/>
			</div>
			<!-- 购物车信息 -->
			<c:forEach items="${cartsList}" var="cart">
				<div class="row" style="height: 150px;" id="cartDiv${cart.id }">
					<div class="col-md-1 col-sm-1"></div>
					<div class="col-md-2 col-sm-2">
						<img src="/pic/${cart.product.main_image }"
							style="height: 150px; width: 100%">
					</div>
					<div class="col-md-3 col-sm-3" style="padding-top: 50px;">
						<a style="display: block; width: 100%;">${cart.product.name }</a>
					</div>
					<div class="col-md-2 col-sm-2"
						style="padding-top: 55px;">
						<p id="price${cart.id }" style="margin: auto;">￥${cart.product.price }/件</p>
					</div>
					<div class="col-md-1 col-sm-1" style="padding-top: 55px;">
						<p>${cart.quantity }件</p>
					</div>
					<div class="col-md-2 col-sm-2"
						style="padding-top: 55px;" id="totalPrice${cart.id }">
						共￥${cart.product.price * cart.quantity }</div>
				</div>
				<hr />
			</c:forEach>
		</div>
		<!-- 展示区域 结束-->
		<!-- 右侧留出1个格子 -->
		<div class="col-md-1 col-sm-1"></div>
	</div>
	<!-- 底部的导航条开始 -->
	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<p class="navbar-text" style="font-size:22px;margin-left:180px;">
				Buy&nbsp;&nbsp;Now&nbsp;&nbsp;and&nbsp;&nbsp;enjoy&nbsp;&nbsp;a&nbsp;&nbsp;discount&nbsp;!
			</p>
			<button onclick="addOrder()" type="button" class="btn btn-danger btn-lg" 
				style="float:right;margin-right:180px;margin-top:8px;width:150px;">立即付款</button>
			<p id="payment" class="navbar-text" style="font-size:22px; float:right; color:black;">￥${payment }&nbsp;&nbsp;&nbsp;</p>
		</div>
	</nav>
	<!-- 底部的导航条结束 -->
	<!-- 用于提交订单的form -->
	<form action="${ctx }/order/add.shtml" method="post" id="addOrderForm">
		<input type="hidden" name="cart_ids" value="${cart_ids }"/>
		<input type='hidden' name="shipping_id" id="shipping_id"/>
	</form>
</body>
<script type="text/javascript">
	function addOrder(){
		var obj=document.getElementsByName('shippingBoxItem');
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				var shipping_id = obj[i].value;
				break;
			}
		}
		$("#shipping_id").val(shipping_id);
		$("#addOrderForm").submit();
	}
	
    function openAddShipping(){
        layer.open({
            type:2,//（iframe层）
            title:'添加收货地址',
            area: ['350px', '600px'],
            offset: '20px',//只定义top坐标，水平保持居中
            content:"${ctx}/shipping/goadd.shtml"
        });
    }
    
    function deleteShipping(id){
    	var msg = "您真的确定要删除吗？\n\n请确认！";
    	  if (confirm(msg) == false){
    	    return;
    	  }
	    var options = {
		        url : "${ctx}/shipping/delete.shtml",
		        type : "post",
		        dataType : "json",
		        data : {shipping_id : id},
		        success : function(news) {
		            if (news) {
		            	window.location.reload();
		            } else {
		                layer.msg("记录不符合要求");
		            }
		        }
		    };
		    $.ajax(options);
    }
</script>
</html>