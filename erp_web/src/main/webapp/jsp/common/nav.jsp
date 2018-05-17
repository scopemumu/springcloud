<%@ page language="java" errorPage="../common/500.jsp" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%    
String userName = "admin";
String treeViewJson = null;
%>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0">
	<!-- 导航栏header -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">易世通达ERP系统</a>
    </div>

	<ul class="nav navbar-top-links navbar-left menu">
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">用户管理<i class="fa fa-caret-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="<%=webPath %>/user/detail">用户列表</a></li>
			</ul>
		</li>
	</ul>
	
	<ul class="nav navbar-top-links navbar-left menu">
		<li class="dropdown">
			<a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="#">产品开发<i class="fa fa-caret-down"></i></a>
			<ul class="dropdown-menu">
				<li><a href="<%=webPath %>wish/page">Wish产品</a></li>
			</ul>
		</li>
	</ul>

	<!-- 导航栏右上角菜单 -->
    <ul class="nav navbar-top-links navbar-right">
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-messages">
                <li></li>
                <li class="divider"></li>
                <li></li>
                <li class="divider"></li>
                <li></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-tasks">
                <li></li>
                <li class="divider"></li>
                <li></li>
                <li class="divider"></li>
                <li></li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-alerts">
                <li></li>
                <li class="divider"></li>
                <li></li>
            </ul>
        </li>
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                </li>
                <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li><a id="logout" href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
            </ul>
        </li>
    </ul>
	
	<!-- 导航栏左侧菜单 -->
    <div class="navbar-default navbar-static-side navbar-left on" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="sidebar-search">
                    <div class="input-group custom-search-form">
                        <input type="text" class="form-control" placeholder="Search...">
                        <span class="input-group-btn">
                        	<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                    	</span>
                    </div>
                </li>
                <li>
                    <a href="#"><i class="fa fa-users fa-fw" aria-hidden="true"></i>用户管理<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="<%=webPath %>/user/detail">用户列表</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-wrench fa-fw" aria-hidden="true"></i>产品开发<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a href="<%=webPath %>wish/page">Wish产品</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
               		
<script type="text/javascript">
    (function(window, $, undefined){
    	initErpModule("menu");
    	window.erp.menu = {
    			data: {
    				treeView: <%=treeViewJson %>
    	        },
    	        init: function() {
    	            this.initData();
    	            this.bindEvent();
    	        },
    	        initData: function(){
    	        	var me = this;
    	        	var treeView = me.data.treeView;
    	        	if(treeView == null || treeView == ""){
    	                console.log("初始化菜单错误，请联系管理员设置权限！");
    	                return;
    	            }
    	        	
    	        	var nodes = treeView.nodes;
    	            if(nodes.length > 0){
    	                var html = "";
    	                $.each(nodes, function(i, node){
    	                    if(hasChilds(node)){
    	                        html += getChildHtml("", node, false);
    	                    }else {
    	                        html += ['<li><a href="', getHref(node.url),'">', node.text,'</a></li>'].join("");
    	                    }
    	                });
    	                $("#menuNav").html( html);
    	            }
    	        },
    	        bindEvent: function() {
    	        	$("#logout").click(function(){
    	        		logout("退出成功！");
    	        	});
    	        },
    	        getChildHtml: function (html, data, subFlag){
    	            var menuType = subFlag? "dropdown-submenu" : "dropdown";
    	            var icon = subFlag ? '' : '<b class="caret"></b>';
    	            html += ['<li class="', menuType, '"><a href="', getHref(data.url), '" data-toggle="dropdown" class="dropdown-toggle ', 
    	                data.code + '-menu','">', data.text, icon, '</a>', '<ul class="dropdown-menu" id="' + data.code + '-menu">'].join("");
    	            $.each(data.nodes, function(i, node){
    	                if(hasChilds(node)){
    	                    html += getChildHtml("", node, true);
    	                }else {
    	                    html += ['<li><a href="', getHref(node.url),'">', node.text,'</a></li>'].join("");
    	                }
    	            });
    	            html += '</ul></li>';
    	            return html;
    	        }
    	};
        
        function hasChilds(node){
            return node.nodes != null && node.nodes.length > 0;
        }
        
        function getHref(url){
            if(url == null || url == "#"){
                return "#";
            }   
            
            return projectName + url;
        }
        
        window.erp.menu.init();
    })(window, $, undefined);
</script>