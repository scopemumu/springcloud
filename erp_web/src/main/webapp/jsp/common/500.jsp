<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" isELIgnored="false"%>
<%@include file="../common/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <title>易世通达销售平台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@include file="../common/inc.jsp" %>
</head>
<%
	response.setStatus(HttpServletResponse.SC_OK);
	pageContext.setAttribute("exception", exception);
%>
<body>
    <div class="container-fluid">
    	<div class="row">
			<div class="col-md-12">
				<div class="container bottomblank" style="width: 960px;margin-top: 50px;">
					<div class="panel panel-default form-horizontal form-bordered min">
						<div class="panel-heading">
							<h3 class="panel-title text-lan">
								<i class="ico-tree5 mr5"></i><span style="color: red;font-weight: 600;font-size: 15px;">${apiResult.errorCode.value}: ${apiResult.errorCode.reasonPhrase}</span>
								<c:if test="${!empty apiResult.redirectUrl }">
									</b>点击<a href="/web/${apiResult.redirectUrl }" style="color: blue;">返回</a>
								</c:if>
							</h3>
							<div class="panel-toolbar text-right">
                                <div class="option"><button data-toggle="panelcollapse" class="btn up" type="button"><i class="arrow"></i></button></div>
                            </div>
						</div>
						<div class="panel-body panel-collapse pull out">
							<div class="form-group bdr0">
								<label class="col-sm-1 control-label">class</label> 
								<div class="col-sm-11">${apiResult.clazz }</div>
							</div>
							<div class="form-group bdr0">
								<label class="col-sm-1 control-label">错误信息</label> 
								<div class="col-sm-11">${apiResult.msg }</div>
							</div>
						</div>
					</div>
						
				</div>
			</div>
		</div>
        
        <%@ include file="../common/footer.jsp"%>
    </div>
    <script  type="text/javascript" language="javascript">
    </script>
</body>
</html>