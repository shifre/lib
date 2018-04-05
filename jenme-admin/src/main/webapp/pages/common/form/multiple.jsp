<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-4 multiple">
		        <div class="well well-sm">
		       	  	<span><c:out value="${es:htmlProp(data, field.name)}" ></c:out></span>
		       	  	<input type="hidden" class="form-control" value="${field.plattern}" />
		        </div>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-4 multiple">
		    	<select name="${field.name}" class="select" data-select-value="${es:htmlProp(data, field.name)}">
		            <option value=""><fmt:message key="please.select" /></option>
		        </select>
		        <input type="hidden" class="form-control" value="${field.plattern}" />
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 
