<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:set var="ids" value="${es:prop(data, field.name)}" />
<c:if test="${not empty ids }">
	<c:set var="refs" value="${data:refs(ids)}" scope="request" />
</c:if>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10" style="margin-left: -10px;">
		    	<c:forEach var="ref" items="${refs}" varStatus="status">
		    		<div class="col-sm-12 refs">
		    			<div class="col-sm-5" style="margin-left: -10px;">
		    				<div class="input-group" style="margin-bottom: 5px;">
						        <input type="text" readonly="readonly" value="${ref.title}" class="form-control" />
						    </div>
		    			</div>
		    		</div>
		    	</c:forEach>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10" style="margin-left: -10px;">
		    	<c:forEach var="ref" items="${refs}" varStatus="status">
		    		<div class="col-sm-12 refs">
		    			<div class="col-sm-5" style="margin-left: -10px;">
		    				<div class="input-group" style="margin-bottom: 5px;">
						        <input type="text" readonly="readonly" value="${ref.title}" class="form-control" />
						        <div class="input-group-btn">
						        	<input class="form-control" for="${field.name}" value="${ref.id}" type="hidden">
						            <button type="button" onclick="removeTag('.refs', this);" class="btn btn-default"><fmt:message key="button.delete" /></button>
						        </div>
						    </div>
		    			</div>
		    		</div>
		    	</c:forEach>
		    	<div class="col-sm-10 addArticle${field.name}">
			        <button type="button" onclick="showView('refs', '${field.name}')" class="btn btn-info mr5"><fmt:message key="field.article.select" /></button>
			        <input type="hidden" view-type="submit" class="form-control" name="${field.name}" value="${es:htmlProp(data, field.name)}" />
		        </div>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 

<c:set var="refs" value="${null}" scope="request" />
<c:set var="ids" value="${null}" />