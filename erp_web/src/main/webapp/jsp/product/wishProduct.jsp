<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <title>易世通达ERP系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../common/inc.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/web/js/dist/bootstrap-select/bootstrap-select.css"></link>
    <script type="text/javascript" src="<%=basePath %>/web/js/dist/bootstrap-select/bootstrap-select.js"></script>
    <style type="text/css">
    </style>
</head>
<body>
	<div id="wrapper">
		<%@include file="../common/nav.jsp" %>
		<div id="page-wrapper" class="page-wrapper on">
			<!-- 页面header -->
			<div class="row">
                <div class="col-lg-12"><h3 class="page-header">Wish产品</h3></div>
            </div>
            <div class="row">
            	<div class="col-lg-12">
            		<form id="search-form" class="form-horizontal">
            			<div class="form-body">
							<div class="form-group">
								<label class="control-label col-md-1">商品id</label>
								<div class="col-md-3">
									<input type="text" placeholder="" name="pid" class="form-control" value="">
								</div>
								<label class="control-label col-md-1">商品名称</label>
								<div class="col-md-3">
									<input type="text" placeholder="" name="pname" class="form-control" value="">
								</div>
								<label class="control-label col-md-1">价格</label>
								<div class="col-md-3 input-group float-left">
									<input type="text" placeholder="" name="minPrice" class="form-control" value="">
									<span class="input-group-addon">-</span>
									<input type="text" placeholder="" name="maxPrice" class="form-control" value="">
								</div>
							</div>
						</div>	
                	</form>
               	 	<div id="product-toolbar">
                    	<button class="btn btn-info" id="search_btn"><i class="fa fa-search" aria-hidden="true"></i>查询</button>
                         <button class="btn btn-info" id="add_btn"><i class="fa fa-plus" aria-hidden="true"></i>添加</button>
                         <button class="btn btn-info" id="edit_btn"><i class="fa fa-pencil" aria-hidden="true"></i>编辑</button>
                         <button class="btn btn-info" id="delete_btn"><i class="fa fa-times" aria-hidden="true"></i>删除</button>
                  	</div>
                    <table id="wish-product-table"></table>
            	</div>
            </div>
		</div>
	</div>
	
    <div id="edit-product-dialog" style="display:none;">
    	<form action="" id="edit_from" role="form">
	   		<div class="form-group">
	           <label>商品标题</label>
	           <input class="form-control"  id="pname" name="pname"/>
	       </div>
	       <div class="form-group">
	           <label>价格</label>
	           <input class="form-control"  id="price" name="price">
	       </div>
    	</form>
    </div>
    <script type="text/javascript" src="<%=webPath %>js/productdev/wishProduct.js"></script>
</body>
</html>