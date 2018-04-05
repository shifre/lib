<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp"%>
<c:if test="${not empty param.id}">
    <c:set var="data" value='${data: serviceUsage(param.id)}' scope="request"/>
</c:if> 
<!DOCTYPE html>
<html lang="en">
<head>
    <script>
    var uh;
	$(document).ready(function() {
	    var uhInitData={
	        linkPrefix:"service"
	    }
	    uh = new UpdateHander(uhInitData);
	});
    </script>        
</head>
<body>    
   <div class="contentpanel">
       <form id="form" method="post" class="form-horizontal form-bordered">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title"><fmt:message key="field.service.info" /></div>
                </div>
                <div class="panel-body nopadding">
                    <div class="errorForm"></div>
                    <input type="hidden" id="id" name="id" value="${data.id}"/>
                    <c:set var="form" value="${es:form('serviceUsageView')}" scope="request" />
                    <c:set var="readonly" value="true" scope="request" />
                    <%@ include file="/WEB-INF/layouts/form/formView.jsp" %>
                </div>
                <div class="panel-footer">
                    <button type="button" class="btn btnReturn btn-default"><fmt:message key="button.return"/></button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>