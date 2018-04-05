<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:set var="id" value="${es:prop(data, field.name)}" />
<c:if test="${not empty id}">
	<c:set var="asset" value="${data:asset(id)}" scope="request" />
</c:if>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10" style="margin-left: -10px;">
		    	<c:if test="${asset != null}">
			    	<div class="col-sm-12 files" style="margin-top: 10px;">
		    		 	<div class="col-sm-12 well-file">
		    		 		<c:set var="files" value=""/>
		    		 		<c:forEach var="file" items="${asset.items}" varStatus="iStatus">
		    		 			<c:if test="${files != ''}">
		    		 				<c:set var="files" value="${files}, ${file.title}"/>
		    		 			</c:if>
		    		 			<c:if test="${files == ''}">
		    		 				<c:set var="files" value="${file.title}"/>
		    		 			</c:if>
					   		</c:forEach>
			   				<c:out value="${files}"></c:out>
		   				</div>
			   		</div>
		   		</c:if>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10" style="margin-left: -10px;">
		    	<c:if test="${asset != null}">
			    	<div class="col-sm-12 files" style="margin-top: 10px;">
		    		 	<div class="col-sm-12 well-file">
		    		 		<c:set var="files" value=""/>
		    		 		<c:forEach var="file" items="${asset.items}" varStatus="iStatus">
		    		 			<c:if test="${files != ''}">
		    		 				<c:set var="files" value="${files}, ${file.title}"/>
		    		 			</c:if>
		    		 			<c:if test="${files == ''}">
		    		 				<c:set var="files" value="${file.title}"/>
		    		 			</c:if>
					   		</c:forEach>
			   				<c:out value="${files}"></c:out>
		   				</div>
			   			<div class="col-sm-12" style="margin-left: -10px;">
			   				<input type="hidden" class="form-control" name="${field.name}" value="${id}" />
			   				<div class="btn btn-primary btn-file mr5">
						      <span class="fileupload-new"><fmt:message key="field.file.add" /></span>
						      <input type="file" accept="*" name="file" style="width: 200px;">
					   	    </div>
							<button type="button" onclick="showView('files','${field.name}',this);" class="btn btn-warning mr5"><fmt:message key="button.change" /></button>
							<button type="button" onclick="removeTag('.files', this, '${field.name}');" class="btn btn-danger"><fmt:message key="button.delete" /></button>
						</div>
			   		</div>
		   		</c:if>
		    	<div class="col-sm-12 addFiles${field.name}" style="display: ${asset == null ? '' : 'none'}">
			        <button type="button" onclick="showView('files','${field.name}')" class="btn btn-info mr5"><fmt:message key="select.file" /></button>
			        <button type="button" onclick="showAddView('files','${field.name}')" class="btn btn-success"><fmt:message key="field.files.add" /></button>
		        </div>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 
<c:set var="asset" value="${null}" scope="request" />
<c:set var="ids" value="${null}"/>