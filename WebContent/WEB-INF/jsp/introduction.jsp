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
	<div class="row" style="margin-top:30px;">
		<!-- 左侧留出1个格子 -->
		<div class="col-md-1 col-sm-1"></div>
		<!-- 展示区域 开始-->
		<div class="col-md-10 col-sm-10">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					<img src="/pic/${product.main_image }" class="img-thumbnail img-responsive" style="height:400px;">
				</div>
				<div class="col-md-1 col-sm-1"></div>
				<div class="col-md-5 col-sm-5">
					<h3>${product.name }<small><span
							class="label label-danger">HOT</span></small>
					</h3>
					<hr />
					<h4>${product.subtitle }</h4>
					<hr />
					<hr />
					<div style="height:65px;">
						<div style="float:left">
							<h4>
								<del>￥${product.price*1.75 }</del>
							</h4>
							<h3 style="color: red;">
								￥${product.price }<small>每件</small>
							</h3>
						</div>
						<div style="float:left;width:150px;height:60px;background-color:gray;margin-left:12%;padding-top:4px;margin-top:8px;">
							<div class="alert alert-info" role="alert" style="color:#9400D3;">
							限时折扣
							购买即享
							</div>
						</div>
					</div>
					<hr />
					<!-- 商品数量选择器 开始 -->
					<div class="row">
						<div class="col-md-5 col-sm-5">
							<div class="input-group">
								<span class="input-group-btn">
									<button onclick="cutNumber()" class="btn btn-default" type="button">-</button>
								</span>
								<input onblur="modifyNumber()" id="quantityNow" class="form-control" value="1" type="text">
								<span class="input-group-btn">
									<button onclick="addNumber()" class="btn btn-default" type="button">+</button>
								</span>
							</div>
							<p id="nowStock">&nbsp;&nbsp;库存:${product.stock } 件</p>
						</div>
					</div>
					<!-- 商品数量选择器 结束 -->
					<br/>
					<button onclick="buyNow()" type="button" class="btn btn-danger btn-lg" style="width:150px;">立即登录</button>
					<button onclick="addCart()" type="button" class="btn btn-primary btn-lg" style="width:150px;">加入购物车</button>
				</div>
				<div class="col-md-2 col-sm-2" style="height:360px;margin-top:40px;">
					<div style="background-color: gray;">
						<p style="color:white;">&nbsp;优购超市以顾客为宗旨</p>
						<p style="color:white;">&nbsp;为顾客提供优秀的产品</p>
					</div>
					<br/>
					<div class="alert alert-warning" role="alert">所有商品免运费</div>
					<div class="alert alert-success" role="alert">七天无理由退货</div>
					<div class="alert alert-info" role="alert">一月内免费更换</div>
					<div class="alert alert-danger" role="alert">终身优质服务</div>
				</div>
			</div>
		</div>
		<!-- 展示区域 结束-->
		<!-- 右侧留出1个格子 -->
		<div class="col-md-2 col-sm-2"></div>
	</div>
	<!-- 商品细节小图 -->
	<div class="row">
		<div class="col-md-1 col-sm-1"></div>
		<c:forEach items="${sub_images }" var="sub_image">
		<div class="col-md-1 col-sm-1">
			<img src="/pic/${sub_image} " class="img-circle"
			 style="display: block; width:90px; height: 90px; float: left;">
		</div>
		</c:forEach>
	</div>
	<!-- 商品详情 -->
	<div class="row">
		<div class="col-md-1 col-sm-1"></div>
		<div class="col-md-10 col-sm-10">
			${product.detail }
		</div>
		<div class="col-md-1 col-sm-1"></div>
	</div>
</body>
<script type="text/javascript">
	function goPage(pageIndex){
		$("#pageIndex").val(pageIndex);
		$("#goPageForm").submit();
	}
	
	function cutNumber(){
		var number = parseInt($("#quantityNow").val());
		if(number >= 2){
			$("#quantityNow").val(number - 1);
		}
	}
	
	function addNumber(){
		var number = parseInt($("#quantityNow").val());
		var stock = parseInt('${product.stock}');
		if(number < stock){
			$("#quantityNow").val(number + 1);
		}
	}
	
	function modifyNumber(){
		var stock = parseInt('${product.stock}');
		var number = parseInt($("#quantityNow").val());
		if(!isNaN(number) && number < stock && number > 0){
			$("#quantityNow").val(number);
		}else{
			 layer.msg("数量不合法");
			 $("#quantityNow").val(1);
		}
	}
	
	function addCart(){
		var product_id = '${product.id}';
		var number = $("#quantityNow").val();
		var options = {
		        url : "${ctx}/cart/add.shtml",
		        type : "post",
		        dataType : "json",
		        data : {
			        	"product_id" : product_id,
			        	"quantity" : number
			        	},
		        success : function(news) {
		            if (news) {
		    			layer.msg("商品已加入购物车");
		            }else{
		            	layer.msg("操作失败");
		            }
		        }
		};
		$.ajax(options);
	}
	
	function buyNow(){
		if('${user}' == ""){
	        layer.open({
	            type:2,//（iframe层）
	            title:'用户登录',
	            area: ['350px', '300px'],
	            offset: '50px',//只定义top坐标，水平保持居中
	            content:"${ctx}/user/gologin.shtml"
	        });
		}else{
			
		}
	}
</script>
</html>