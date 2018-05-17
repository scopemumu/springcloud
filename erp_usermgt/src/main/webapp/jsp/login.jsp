<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
<title>易世通达ERP系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="common/inc.jsp" %>
<script type="text/javascript" src="<%=proxyPath %>js/dist/jquery.form.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                	<a class="hide" id="redirectUrl" href="${cookie.redirectUrl.value}"><span id="redirectBtn">login redirect url</span></a>
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="用户名" name="username" id="username" autofocus>
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="密码"  name="password" id="password" type="password" value="">
                        </div>
                        <div class="checkbox">
                            <label><input name="remember" type="checkbox" value="Remember Me">记住我</label>
                        </div>
                        <!-- Change this to a button or input when using this as a form -->
                        <a href="#" id="submitBtn" class="btn btn-lg btn-success btn-block">Login</a>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
$(function(){
	$("#submitBtn").click(function(){
	  $("#submitBtn").ajaxSubmit({
          type: 'post',
          url: '/usermgt/authentication/form',
          data: {
			"username":$("#username").val(),
			"password":$("#password").val()
		},
         success: function(result) { 
        	 if(result.authorizedFail){
        		 layer.msg("用户名或密码错误!");
        		 return;
        	 }
        	 setAccessToken(result.accessToken);
        	 var redirectUrl = $("#redirectUrl").val();
        	 if(trim(redirectUrl) == null){
        		 redirectUrl = result.redirectUrl;
        	 }
        	 if(redirectUrl == null){
        		 layer.msg("未设置默认登录跳转url,请联系管理员!");
        		 return;
        	 }
        	 
        	 $("#redirectUrl").attr("href", redirectUrl);
        	 $("#redirectBtn").trigger("click");
         }
      });
	});
});
</script>
</body>
</html>