<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="${ctx }/resource/lib/bootstrap.min.css" rel="stylesheet" type="text/css" />
<title>注册</title>
</head>
<body>
	<div style="width:70%;margin:auto;margin-top:1%;">
		<form class="form-horizontal" id="register_form">
			<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" id="usernameLabel">username</label>
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
						<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">email</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="email" name="email">
				</div>
			</div>
						<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">phone</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="phone" name="phone">
				</div>
			</div>
						<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">question</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="question" name="question">
				</div>
			</div>
						<div class="form-group form-group-sm">
				<label class="col-sm-2 control-label" for="formGroupInputSmall">answer</label>
				<div class="col-sm-10">
					<input class="form-control" type="text" id="formGroupInputSmall"
						placeholder="answer" name="answer">
				</div>
			</div>
			<div class="btn-group btn-group-justified" role="group"
				aria-label="...">
				<div class="btn-group" role="group">
					<input type="button" class="btn btn-default" onclick="addUser()" value="注册"/>
				</div>
			</div>
		</form>
	</div>
</body>
	<script src="${ctx }/resource/lib/jquery-3.2.1.min.js"></script>
	<script src="${ctx }/resource/lib/layer-v3.1.0/layer/layer.js"></script>
	<script type="text/javascript">
		function addUser(){
		    var options = {
			        url : "${ctx}/user/add.shtml",
			        type : "post",
			        dataType : "json",
			        data : $("#register_form").serialize(),
			        success : function(news) {
			            if (news.status == 0) {
			                //当你在iframe页面关闭自身时
			                var index = parent.layer.getFrameIndex(window.name);//先得到当前iframe层的索引
			                setTimeout(function() {
			                    parent.layer.close(index); //再执行关闭 
			                    alert("注册成功");
			                    parent.layer.open({ //打开登录层
			                        type:2,//（iframe层）
			                        title:'用户登录',
			                        area: ['350px', '300px'],
			                        offset: '50px',//只定义top坐标，水平保持居中
			                        content:"${ctx}/user/gologin.shtml"
			                    });
			                }, 1000);
			            } else {
			                layer.msg(news.msg);
			            }
			        }
			    };
			    $.ajax(options);
		}
		
		$("input[name='username']").blur(function(){
			var username = $(this).val();
		    var options = {
			        url : "${ctx}/user/checkname.shtml",
			        type : "post",
			        dataType : "json",
			        data : {"username" : username},
			        success : function(news) {
			            if (news) {
			    			layer.msg("重复的用户名");
			            }
			        }
			    };
			    $.ajax(options);
		})
	</script>
</html>