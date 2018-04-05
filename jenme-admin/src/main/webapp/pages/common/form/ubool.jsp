<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-8">
		    	<c:choose>
					<c:when test="${es:prop(data, field.name) }">
						<img alt="" src="${ctx }/images/enabled_yes.png" width="20px" class="mt10" />
					</c:when>
					<c:otherwise>
						<img alt="" src="${ctx }/images/enabled_no.png" width="20px" class="mt10" />
					</c:otherwise>
				</c:choose>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-8 control-label">
		        <div class="toggle toggle-success" data-toggle-checkbox="#${field.name}" name="${field.name}"></div>
		        <input style="display:none;" type="checkbox" id="${field.name}" name="${field.name}" ${null != data && true == es:prop(data, field.name) ? 'checked="checked"': ''}/>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 