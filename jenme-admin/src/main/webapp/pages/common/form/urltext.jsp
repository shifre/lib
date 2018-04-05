<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-4">
		    	<div class="well well-sm">
		        	 /<c:out value="${es:htmlProp(data, field.name)}" ></c:out>
		        </div>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-7">
		        <div class="input-group">
				    <span class="input-group-addon">/</span>
				    <input type="text" class="form-control" name="${field.name}" value="${es:htmlProp(data, field.name)}" />
				</div>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 