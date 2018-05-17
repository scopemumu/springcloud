<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <title>易世通达销售平台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../common/inc.jsp" %>
    <style type="text/css">
		#main {
			width:1000px; 
			margin:0 auto;
			background:url(/web/css/image/404.jpg) no-repeat left top;
			font-size:1.125em;
			overflow:hidden;
			position:relative;
			font-style:italic;
		}
		#header {
			height:100px;
			font-style:italic;
		}
		#content {
			min-height:457px;
			height:auto !important;
			font-style:italic;
		}
		h1 {
			font-size:40px;
			line-height:1.2em;
			font-weight:normal;
			color:#8e8fc9;
			text-align:center;
			padding:80px 0 0 0;
			text-transform:capitalize;
			letter-spacing:-2px;
		}
		h1 span {
			display:block;
			font-size:24px;
			line-height:25px;
			font-variant:normal;
			text-transform:none;
			letter-spacing:-1px;
			font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
		}
		#content .nav {
			position:relative;
			height:360px;
		}
		#content .nav li {
			position:absolute;
			font-size:24px;
			line-height:1.2em;
			text-transform:capitalize;
		}
		#content .nav li.home {
			left:480px;
			top:270px;
		}
		#content .nav li a {
			color:#a8a9d4;
			margin: 0px;
			padding: 0px;
			text-decoration:none;
		}
		#content .nav li a:hover {
			text-decoration:underline;
		}
	</style>
</head>
<%
response.setStatus(HttpServletResponse.SC_OK);
pageContext.setAttribute("redirectUrl", "/web/wish/page");
%>    
<body>
    <div class="container-fluid">
        <div id="main">
			<div id="header">
				<h1><span>404 Error - 你访问的页面不存在 </span></h1>
			</div>
			<div id="content">
				<ul class="nav">
		         	<li class="home"><a href="${redirectUrl }">返回</a></li>
		         </ul>
			</div>
		</div>
       	<%@ include file="../common/footer.jsp"%>
    </div>

    <script  type="text/javascript" language="javascript">
    
    </script>
</body>
</html>