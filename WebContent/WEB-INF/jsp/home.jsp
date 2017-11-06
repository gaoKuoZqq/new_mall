<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<div class="col-md-10 col-sm-10">
			<div class="row">
					<!-- 根分类列表开始 -->
					<div id="rootCategoryDiv" class="col-md-2 col-sm-2" style="padding:0;">
						<div class="btn-group-vertical" role="group" style="width:100%">
						<c:forEach items="${rootCategoriesList }" var="rootCategory" varStatus="status">
						<c:if test="${status.first }">
							<input id="firstRootCategory" type="hidden" value="${rootCategory.id }"/>
						</c:if>
							<input onclick="findCategory(${rootCategory.id })" id="rootCategory${rootCategory.id }"
							 class="btn btn-success btn-block btn-lg" value="${rootCategory.name }" type="button"/>
						</c:forEach>
						</div>
					</div>
				<!-- 根分类列表结束 -->
				<!--轮播图插件,开始-->
				<div id="myCarousel" class="carousel slide col-md-10 col-sm-10" data-ride="carousel" style="padding:0;">
					<!-- 轮播控制器 -->
					<ol class="carousel-indicators">
					<c:forEach items="${carouselsList }" varStatus="status">
					<c:if test="${status.first }">
						<li data-target="#myCarousel" data-slide-to="${status.index }" class="active"></li>
					</c:if>
					<c:if test="${!status.first }">
						<li data-target="#myCarousel" data-slide-to="${status.index }"></li>
					</c:if>
					</c:forEach>
					</ol>
					<!-- 轮播图片 -->
					<div id="carousels" class="carousel-inner">
					<c:forEach items="${carouselsList }" var="carousel" varStatus="status">
					<c:if test="${status.first }">
						<div class="item active">
							<img src="/pic/${carousel.name }" onclick="goIntroduction(${carousel.product_id})"/>
						</div>
					</c:if>
					<c:if test="${!status.first }">
						<div class="item">
							<img src="/pic/${carousel.name }" onclick="goIntroduction(${carousel.product_id})"/>
						</div>
					</c:if>
					</c:forEach>
					</div>
				</div>
			</div>
			<div id="categoryDiv" style="padding-top:5px;"></div>
		</div>
		<!--轮播插件结束-->
		<!--右侧留出一个格子-->
		<div class="col-md-1 col-sm-1"></div>
	</div>
</body>
<script type="text/javascript">
	$("#carousels img").height($("#rootCategoryDiv").height());
	
	function findCategory(rootId){
		var options = {
		        url:"${ctx}/home/findcategory.shtml",
		        type:"post",
		        dataType:"json",
		        data:{"parent_id" : rootId},
		        success:function(categories){
		        	var thisCategories = $("#categoryDiv").html();
		        	thisCategories = "";
		        	$("#rootCategoryDiv").find('input').removeClass('btn-danger');
		        	$("#rootCategory"+rootId).addClass('btn-danger');
		        	for(var i in categories){
		        		thisCategories = thisCategories + 
		        		"<button onclick='goProductFind("+categories[i].id+")'"+
		        		"style='margin:0 5px 10px 0;' type='button' class='btn btn-info btn-lg'>"
		        		+categories[i].name+"</button>"
		        	}
		        	$("#categoryDiv").html(thisCategories);
		        }
		}
		$.ajax(options);
	}
	
	$(document).ready(findCategory($("#firstRootCategory").val()));
	
	function goProductFind(category_id){
		window.location.href="${ctx}/product/find.shtml?product.category_id="+category_id+"";
	}
	
	function goIntroduction(product_id){
		window.location.href="${ctx}/product/introduction.shtml?product_id="+product_id+"";
	}
</script>
</html>