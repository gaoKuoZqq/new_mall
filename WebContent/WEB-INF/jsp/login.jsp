<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div style="width:70%;margin:auto;margin-top:8%;">
		<form class="form-horizontal" id="login_form">
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">username</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="username" name="username">
				</div>
			</div>
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">password</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="password" name="password">
				</div>
			</div>
			<div class="btn-group btn-group-justified" role="group"
				aria-label="...">
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-default" onclick="submitForm()">登录</button>
				</div>
			</div>
		</form>
	</div>
</body>
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
	<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
<script type="text/javascript">
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
</script>
</html>