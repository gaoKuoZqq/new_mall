<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收货地址</title>
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
		<div style="width:70%;margin:auto;margin-top:8%;">
		<form class="form-horizontal" id="login_form">
			<div class="form-group form-group-sm">
				<label class="control-label" for="formGroupInputSmall">收货人</label>
				<div class="col-sm-10">
					<input class="form-control" type="text"
						 id="receiver_name">
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="control-label" for="formGroupInputSmall">收获电话</label>
				<div class="col-sm-10">
					<input class="form-control" type="text"
						 id="receiver_mobile">
				</div>
			</div>
			<div class="col-sm-10 form-group form-group-sm">
				<label class="control-label" for="formGroupInputSmall">收获地址</label>
				<div style="margin-bottom: 10px;">
					省份 : <select onchange="findCity()" name="receiver_province" class="form-control" id="provinceSelect">
					<c:forEach items="${provinces }" var="province">
						<option value="${province.id }">${province.name }</option>
					</c:forEach>
					</select>
					城市 : <select name="receiver_city" class="form-control" id="citySelect" onchange="findArea()">
						<option id="cityNoSelected">未选择</option>
					</select>
					地区 : <select name="receiver_district" class="form-control" id="areaSelect">
						<option id="areaNoSelected">未选择</option>
					</select>
					<br/>
					<label class="control-label" for="formGroupInputSmall">详细地址</label>
					<textarea class="form-control" rows="3" id="receiver_address"></textarea>
				</div>
				<button onclick="addShipping()" type="button" class="btn btn-primary btn-block">提交</button>
			</div>
		</form>
	</div>
</body>
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
	<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
	<script>
		function submitForm() {
	    var options = {
	        url : "${ctx}/user/login.shtml",
	        type : "post",
	        dataType : "json",
	        data : $("#login_form").serialize(),
	        success : function(news) {
		            if (news) {
		                //当你在iframe页面关闭自身时
		                var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
		                setTimeout(function() {
		                    parent.layer.close(index); //再执行关闭 
		                    window.parent.location.reload();//刷新父页面
		                }, 1000);
		            } else {
		                layer.msg("用户名或密码错误");
		            }
		        }
		    };
		    $.ajax(options);
		}
		
		function findCity(){
			var parent_id = $("#provinceSelect").val();
			$.post(
				"${ctx}/location/findcity.shtml",
				{"parent_id" : parent_id},
				function(data){
					$("option#temporaryCity").remove();
					for(var a in data){
	            		$("#cityNoSelected").after("<option id='temporaryCity' value='"+data[a].id+"'>"+data[a].name+"</option>")
	            	 }
				},
				"json"
			)
		}
		
		function findArea(){
			var parent_id = $("#citySelect").val();
			$.post(
				"${ctx}/location/findarea.shtml",
				{"parent_id" : parent_id},
				function(data){
					$("option#temporaryArea").remove();
					for(var a in data){
	            		$("#areaNoSelected").after("<option id='temporaryArea' value='"+data[a].id+"'>"+data[a].name+"</option>")
	            	 }
				},
				"json"
			)
		}
		
		$(document).ready(findCity());
		
		function addShipping(){
			var receiver_name = $("#receiver_name").val();
			var receiver_mobile = $("#receiver_mobile").val();
			var receiver_province = $("#provinceSelect").find("option:selected").text();
			var receiver_city = $("#citySelect").find("option:selected").text();
			var receiver_district = $("#areaSelect").find("option:selected").text();
			var receiver_address = $("#receiver_address").val();
			options = {
					url : '${ctx}/shipping/add.shtml',
					data : {
						receiver_name : receiver_name,
						receiver_mobile : receiver_mobile,
						receiver_province : receiver_province,
						receiver_city : receiver_city,
						receiver_district : receiver_district,
						receiver_address : receiver_address
					},
					dataType : 'json',
					success : function (news){
						if (news) {
			                //当你在iframe页面关闭自身时
			                var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
			                setTimeout(function() {
			                    parent.layer.close(index); //再执行关闭 
			                    window.parent.location.reload();//刷新父页面
			                }, 1000);
			            } else {
			                layer.msg("未知错误");
			            }
					}
			}
			$.post(options);
		}
	</script>
</html>