<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>购物车</title>
</head>
<body style="padding-top: 50px;padding-bottom: 70px;">
	<!-- 用于结算提交数据的表单 --> 
	<form id="goAddOrderForm" action="${ctx }/order/goadd.shtml" method="post">
		<input type="hidden" id="cart_ids" name="cart_ids"/>
	</form>
	<jsp:include page="common.jsp" />
	<hr />
	<div class="row">
		<div class="col-md-1 col-sm-1"></div>
		<div class="col-md-10 col-sm-10">
			<div
				style="height: 30px; font-size: 15px; margin-left: 100px; width: 50%;">
				<span style="float: left;">我的购物车:&nbsp;&nbsp;</span>
				<div style="display: none; float: left" id="prompt">
					<a href="javaScript:openLogin()"> 当前 <span style="color: red">未登录</span>
						,无法享受快捷购买,折扣购买等优质服务!
					</a>
				</div>
			</div>
			<hr />
			<c:forEach items="${cartsList}" var="cart">
				<div class="row" style="height: 150px;" id="cartDiv${cart.id }">
					<input type="hidden" id="stock${cart.id }" value="${cart.product.stock }"/>
					<div class="col-md-1 col-sm-1"
						style="padding-left: 45px; padding-top: 55px;">
						<input onchange="payment()" type="checkbox" value="${cart.id }" name="checkboxItem"/>
					</div>
					<div class="col-md-2 col-sm-2" onclick="goIntroduction(${cart.product.id})">
						<img src="/pic/${cart.product.main_image }"
							style="height: 150px; width: 100%">
					</div>
					<div class="col-md-3 col-sm-3" style="padding-top: 50px;" onclick="goIntroduction(${cart.product.id})">
						<a style="display: block; width: 100%;">${cart.product.name }</a>
					</div>
					<div class="col-md-1 col-sm-1"
						style="padding-top: 55px;">
						<span>￥</span><a id="price${cart.id }" style="margin: auto;">${cart.product.price }</a>
					</div>
					<div class="col-md-2 col-sm-2" style="padding-top: 47px;">
						<!-- 商品数量选择器 开始 -->
						<div style="width: 120px;">
							<div class="input-group">
								<span class="input-group-btn">
									<button onclick="cutNumber(${cart.id})" class="btn btn-default"
										type="button">-</button>
								</span> <input onblur="modifyNumber(${cart.id})" id="quantityNow${cart.id }"
									class="form-control" value="${cart.quantity }" type="text"/>
								<span class="input-group-btn">
									<button onclick="addNumber(${cart.id})" class="btn btn-default"
										type="button">+</button>
								</span>
							</div>
							<p id="nowStock">库存:${cart.product.stock } 件</p>
						</div>
						<!-- 商品数量选择器 结束 -->
					</div>
					<div class="col-md-1 col-sm-1"
						style="padding-top: 50px;" id="totalPrice${cart.id }">
						${cart.product.price * cart.quantity }</div>
					<div class="col-md-1 col-sm-1"
						style="padding-top: 47px;">
						<input onclick="deleteCart(${cart.id})" type="button" class="btn btn-warning" value="删除" />
					</div>
				</div>
				<hr />
			</c:forEach>
		</div>
		<div class="col-md-2 col-sm-2"></div>
	</div>
	<!-- 底部的导航条开始 -->
	<nav class="navbar navbar-default navbar-fixed-bottom">
		<div class="container">
			<button onclick="modifyCheckboxsStatus()" type="button" class="btn btn-warning btn-lg" 
				style="float:left;margin-top:8px;margin-left:35px;">全选/反选</button>
			<a href="javaScript:deleteCarts()" class="navbar-text" style="float:left;margin-left:10px;margin-top:30px;">删除</a>
			<p class="navbar-text" style="font-size:22px;margin-left:180px;">
				Buy&nbsp;&nbsp;Now&nbsp;&nbsp;and&nbsp;&nbsp;enjoy&nbsp;&nbsp;a&nbsp;&nbsp;discount&nbsp;!
			</p>
			<button onclick="goAddOrder()" type="button" class="btn btn-danger btn-lg" 
				style="float:right;margin-right:80px;margin-top:8px;width:100px;">结算</button>
			<p id="payment" class="navbar-text" style="font-size:22px; float:right; color:black;">￥0.0</p>
		</div>
	</nav>
	<!-- 底部的导航条结束 -->
</body>
<script type="text/javascript">
	$(document).ready(function(){
		if('${user}' == ""){
			$("#prompt").css('display','block');
		}
	})
	
	function cutNumber(cart_id){
		var number = parseInt($("#quantityNow"+cart_id).val());
		if(number >= 2){
			modifyCartQuantity(cart_id,number - 1);
		}
	}
	
	function addNumber(cart_id){
		var number = parseInt($("#quantityNow"+cart_id).val());
		var stock = parseInt($("#stock"+cart_id).val());
		if(number < stock){
			modifyCartQuantity(cart_id,number + 1);
		}
	}
	
	function modifyNumber(cart_id){
		var stock = parseInt($("#stock"+cart_id).val());
		var number = parseInt($("#quantityNow"+cart_id).val());
		if(!isNaN(number) && number < stock && number > 0){
			modifyCartQuantity(cart_id,number);
		}else{
			 layer.msg("数量不合法");
			 $("#quantityNow"+cart_id).val(1);
		}
	}
	
	function modifyTotalPrice(cart_id,number){
		var price = parseFloat($("#price"+cart_id).html());
		$("#totalPrice"+cart_id).html(price * number);
	}
	
	function payment(){
		var obj=document.getElementsByName('checkboxItem');
		var sum = 0;
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				sum = parseFloat($("#totalPrice"+obj[i].value)[0].innerHTML) + parseFloat(sum);
			}
		}
		$("#payment").html("￥"+sum);
	}
	
	$(document).ready(payment());
	
	function modifyCheckboxsStatus(){
		var obj=document.getElementsByName('checkboxItem');
		var j = 0;
		for(var i=0; i<obj.length; i++){ 
			if(!obj[i].checked){
				j = j + 1;
			}
		}
		if(j == 0){
			$("[name='checkboxItem']").prop("checked",false);//反选
		}else{
			$("[name='checkboxItem']").prop("checked",true);//全选 
		}
		payment();
	}
	
	function modifyCartQuantity(cart_id,quantity){
		var options = {
				url : '${ctx}/cart/modifyQuantity.shtml',
				type : 'post',
				dataType : 'json',
				data : {
					"id" : cart_id,
					"quantity" : quantity
				},
				error : function (){
					layer.msg("无法预知的错误");
				},
				success : function (news){
					if(!news){
						layer.msg("操作失败");
					}else{
						$("#quantityNow"+cart_id).val(quantity);
						modifyTotalPrice(cart_id,quantity);
						payment();
					}
				}
		}
		$.ajax(options);
	}
	
	function deleteCart(cart_id){
		var options = {
				url : '${ctx}/cart/delete.shtml',
				type : 'post',
				dataType : 'json',
				data : {"ids" : cart_id},
				error : function(){
					layer.msg("操作失败");
				},
				success : function (news){
					if(!news){
						layer.msg("操作失败");
					}else{
						$("#cartDiv"+cart_id).remove();
						payment();
					}
				}
		}
		$.ajax(options);
	}
	
	function deleteCarts(){
		var obj=document.getElementsByName('checkboxItem');
		var sum = "";
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				sum = sum + obj[i].value + " ";
			}
		}
		if(sum == ""){
			layer.msg("没有任何一条被选择的记录");
			return;
		}
		var options = {
				url : '${ctx}/cart/delete.shtml',
				type : 'post',
				dataType : 'json',
				data : {"ids" : sum},
				error : function(){
					layer.msg("操作失败");
				},
				success : function (news){
					if(!news){
						layer.msg("操作失败");
					}else{
						var length = obj.length;
						for(var i=0; i<length;){ 
							if(obj[i].checked){
								$("#cartDiv"+obj[i].value).remove();
							}else{
								i  = i + 1;
							}
						}
						payment();
					}
				}
		}
		$.ajax(options);
	}
	
	function goAddOrder(){
		if('${user}' == null || '${user}' == ""){
	        layer.open({
	            type:2,//（iframe层）
	            title:'用户登录',
	            area: ['350px', '300px'],
	            offset: '50px',//只定义top坐标，水平保持居中
	            content:"${ctx}/user/gologin.shtml"
	        });
	        return;
		}
		var obj=document.getElementsByName('checkboxItem');
		var sum = "";
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				sum = sum + obj[i].value + " ";
			}
		}
		$("#cart_ids").val(sum);
		$("#goAddOrderForm").submit();
	}
	
	function goIntroduction(product_id){
		window.location.href="${ctx}/product/introduction.shtml?product_id="+product_id+"";
	}
</script>
</html>