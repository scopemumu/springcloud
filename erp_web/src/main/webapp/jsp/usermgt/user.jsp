<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <title>易世通达ERP系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../common/inc.jsp" %>
    <style type="text/css">
    </style>
</head>
<body>
	<div id="wrapper">
		<%@include file="../common/nav.jsp" %>
		<div id="page-wrapper" class="page-wrapper on">
			<!-- 页面header -->
			<div class="row">
                <div class="col-lg-12"><h3 class="page-header">用户列表</h3></div>
            </div>
            <div class="row">
            	<div class="col-lg-12">
            		<form id="search-form" class="form-horizontal">
            			<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-1">用户名</label>
								<div class="col-md-3">
									<input type="text" placeholder="" name="userName" class="form-control" value="">
								</div>
							</div>
						</div>	
                	</form>
               	 	<div id="user-toolbar">
                    	<button class="btn btn-info" id="search_btn"><i class="icon-search"></i>查询</button>
                    	<button class="btn btn-info" id="edit_btn"><i class="icon-search"></i>编辑</button>
                  	</div>
                    <table id="user-table"></table>
            	</div>
            </div>
		</div>
	</div>
    <script type="text/javascript" src="<%=webPath %>js/usermgt/user.js"></script>
</body>
</html>