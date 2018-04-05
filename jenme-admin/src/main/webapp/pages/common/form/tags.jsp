<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-4">
		        <textarea name="${field.name}" class="form-control" rows="3">${es:htmlProp(data, field.name)}</textarea>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-7">
		        <textarea name="${field.name}" class="form-control">${es:htmlProp(data, field.name)}</textarea>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 