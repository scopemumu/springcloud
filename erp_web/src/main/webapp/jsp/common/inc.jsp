<%@ page language="java" errorPage="../common/500.jsp" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%    
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";    
String projectName = request.getContextPath();
String webPath = "/web/";
%>
<link rel="shortcut icon" href="<%=webPath %>images/favicon.png" />
<link href="<%=webPath %>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=webPath %>css/font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="<%=webPath %>css/sb-admin.css" rel="stylesheet">
 <link href="<%=webPath %>js/dist/bootstrap-select/bootstrap-select.css"/>   

<link href="<%=webPath %>js/dist/bootstrap-table/bootstrap-table.min.css" rel="stylesheet" media="screen"/>
<link href="<%=webPath %>js/dist/jquery-ui-1.12.1/jquery-ui.min.css" rel="stylesheet" media="screen"/>
<link href="<%=webPath %>js/dist/layer/skin/layer.css" rel="stylesheet" media="screen"/>
<link href="<%=webPath %>css/common.css" rel="stylesheet" media="screen"/>
<link href="<%=webPath %>js/dist/bootstrap-table/fixed-columns/bootstrap-table-fixed-columns.css" rel="stylesheet" media="screen"/>
<link href="<%=webPath %>js/dist/bootstrap-table/extensions/sticky-header/bootstrap-table-sticky-header.css" rel="stylesheet" media="screen"/>

<script src="<%=webPath %>js/dist/jquery.11.3.min.js"></script>  
<script src="<%=webPath %>js/dist/sb-admin/bootstrap.min.js"></script>
<script src="<%=webPath %>js/dist/sb-admin/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=webPath %>js/dist/sb-admin/sb-admin.js"></script>

<script src="<%=webPath %>js/dist/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=webPath %>js/dist/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
<script src="<%=webPath %>js/dist/chosen.jquery.js"></script>
<script src="<%=webPath %>js/dist/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script src="<%=webPath %>js/dist/layer/layer.js"></script>
<script src="<%=webPath %>js/dist/date-time/bootstrap-datepicker.js"></script>
<script src="<%=webPath %>js/dist/bootstrap-table/fixed-columns/bootstrap-table-fixed-columns.js"></script>
<script src="<%=webPath %>js/dist/bootstrap-table/extensions/sticky-header/bootstrap-table-sticky-header.min.js"></script>
<script src="<%=webPath %>js/dist/bootstrap-select/bootstrap-select.js"></script>
<script src="<%=webPath %>js/dist/jquery.serialize-object.min.js"></script>
<script src="<%=webPath %>js/dist/jquery.cookie.js"></script>
<script src="<%=webPath %>js/dist/ajax-hook/ajaxhook.js"></script>
<script src="<%=webPath %>js/common.js"></script>
<script  type="text/javascript" language="javascript">
var basePath = '<%=basePath%>';
var projectName = '<%=projectName%>';
var webPath = '<%=webPath %>';
</script>