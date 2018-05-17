<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <title>易世通达报表系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../common/inc.jsp" %>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>js/dist/bootstrap-select/bootstrap-select.css"></link>
    <script type="text/javascript" src="<%=basePath %>js/dist/bootstrap-select/bootstrap-select.js"></script>
    <style type="text/css">
        .modal-body input{
            width: 240px;
        }
    </style>
</head>
<body>
	<%@ include file="../common/horizontal_nav.jsp"%>
	<div class="container-fluid">
        <div class="row-fluid">
            <div class="span9 content" id="content">
                <div class="row-fluid">
                    <div class="block" style="min-height: 450px;">
                        <div class="navbar navbar-inner block-header">
                            <div class="muted pull-left">Amazon平台</div>
                        </div>
                        <div class="block-content collapse in">
                        	<div class="row">
	                        	<form id="search-form" class="form-horizontal">
									<label class="control-label span1">商品名称</label>
									<div class="span3">
										<input type="text" placeholder="" name="goodsName" class="form-control" value="">
									</div>
									<label class="control-label span1">分类</label>
									<div class="span3">
										<input type="text" placeholder="" name="category" class="form-control" value="">
									</div>
									<label class="control-label span1">价格</label>
									<div class="span3 input-group">
										<input type="text" placeholder="" name="minPrice" class="form-control" value="">
										<span class="input-group-addon">-</span>
										<input type="text" placeholder="" name="maxPrice" class="form-control" value="">
									</div>
									<label class="control-label span1">大小</label>
									<div class="span3">
										<input type="text" placeholder="" name="goodsSize" class="form-control" value="">
									</div>
									<label class="control-label span1">颜色</label>
									<div class="span3">
										<input type="text" placeholder="" name="goodsColor" class="form-control" value="">
									</div>
									<label class="control-label span1">颜色</label>
									<div class="span3 input-group">
										<input type="text" placeholder="" name="minReviewsNum" class="form-control" value="">
										<span class="input-group-addon">-</span>
										<input type="text" placeholder="" name="maxReviewsNum" class="form-control" value="">
									</div>
	                           	</form>
                        	</div>
                            <div id="product-toolbar">
	                            <button class="btn btn-info" id="search_btn"><i class="icon-search"></i>查询</button>
	                            <button class="btn btn-info" id="add_btn"><i class="icon-plus"></i>添加</button>
	                            <button class="btn btn-info" id="edit_btn"><i class="icon-edit"></i>编辑</button>
	                            <button class="btn btn-info" id="delete_btn"><i class="icon-remove"></i>删除</button>
                            </div>
                            <table id="amazon-product-table"></table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="../common/footer.jsp"%>
    </div>
    
    <div id="edit-product-dialog" style="display:none;"></div>
    <script type="text/javascript" src="/web/js/productdev/amazonProduct.js"></script>
</body>
</html>