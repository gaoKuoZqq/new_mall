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
			<div class="row">
			<c:forEach items="${pageBean.objList }" var="product">
				<div class="col-md-3 col-sm-3" style="margin-top:15px;margin-bottom:-3px;">
					<a href="${ctx }/product/introduction.shtml?product_id=${product.id}">
						<img alt="${product.subtitle }" src="/pic/${product.main_image }" style="width:100%;height:250px;">
					</a>
					<br/>
					<a>
					<c:if test="${fn:length(product.name)>25 }">
						${fn:substring(product.name, 0, 25)}... 
					</c:if>
					<c:if test="${fn:length(product.name)<=25 }">
						${product.name}
					</c:if>
					</a>
				</div>
			</c:forEach>
				<!-- 分页开始 -->
				<!-- 用于翻页传递信息的表单开始 -->
				<form id="goPageForm" action="${ctx }/product/find.shtml" method="post">
					<input type="hidden" name="pageIndex" id="pageIndex"/>
					<input type="hidden" name="pageSize" value="12"/>
					<input type="hidden" name="product.category_id" value="${pageBean.product.category_id }"/>
					<input type="hidden" name="product.name" value="${pageBean.product.name }"/>
				</form>
				<!-- 用于翻页传递信息的表单开结束 -->
	       		<nav aria-label="Page navigation" style="float:right; margin-right:20px;">
					<ul class="pagination" >
						<!-- 上一页开始 -->
						<c:if test="${pageBean.pageIndex==1 }">
							<li class="disabled">
				    			<a href="javascript:void(0);" aria-label="Previous">
				   				<span aria-hidden="true">&laquo;</span>
				   				</a>
		 					</li>
						</c:if>
						<c:if test="${pageBean.pageIndex>1 }">
							<li>
				    			<a href="javascript:goPage('${pageBean.pageIndex-1 }');" aria-label="Previous">
				   					<span aria-hidden="true">&laquo;</span>
				   				</a>
			  				</li>
						</c:if>
						<!-- 上一页结束 -->
						<c:forEach begin='1' end="${pageBean.totalPage }" var="page">
						<c:if test="${pageBean.pageIndex!=page}">
				 	       <li><a href="javascript:goPage('${page }');">${page}</a></li>
			  			</c:if>
			  			<!-- 遍历的时候page和pageIndex相等，高亮显示 -->
			  			<c:if test="${pageBean.pageIndex==page}">
					        <li class="active"><a href="javascript:goPage('${page }');">${page}</a></li>
			  			</c:if>
						</c:forEach>
						<!-- 下一页开始 -->
						<c:if test="${pageBean.pageIndex==pageBean.totalPage }">
							<li class="disabled">
					    		<a href="javascript:void(0);" aria-label="Next"/>
				   				<span aria-hidden="true">&raquo;</span>
			 				</li>
						</c:if>
						<c:if test="${pageBean.pageIndex<pageBean.totalPage }">
							<li>
				    			<a href="javascript:goPage(${pageBean.pageIndex+1 });" aria-label="Next"/>
				   				<span aria-hidden="true">&raquo;</span>
				   			</li>
						</c:if>
						<!-- 下一页结束 -->
					</ul>
				</nav>
				<!-- 分页结束 -->
			</div>
		</div>
		<!-- 展示区域 结束-->
		<!-- 右侧留出1个格子 -->
		<div class="col-md-1 col-sm-1"></div>
	</div>
</body>
<script type="text/javascript">
	function goPage(pageIndex){
		$("#pageIndex").val(pageIndex);
		$("#goPageForm").submit();
	}
</script>
</html>