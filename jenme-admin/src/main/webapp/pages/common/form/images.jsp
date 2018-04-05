<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<c:set var="images" value="${es:prop(data, field.name)}" />
<c:choose> 
  	<c:when test="${display_mode == 'readonly'}">
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10">
		         <div class="row media-manager col-sm-12 images${field.name}">
		           <c:forEach var="image" items="${images}" varStatus="status">
			           <div class="col-xs-6 col-sm-4 col-md-3 document" style="margin-left: -10px;">
			             <div class="thmb">
			               <div class="thmb-prev">
			                 <a href="${es:config('filehost')}${image['uri']}" data-rel="prettyPhoto" class="img-single">
			               	  	<img src="${es:config('filehost')}${image['uri']}" class="img-responsive" alt="${image['title']}" />
			               	  </a>
			               </div>
			               <h5 class="fm-title"><c:out value="${image['title']}"></c:out></h5>
			             </div>
			           </div>
			        </c:forEach>
		         </div>
		    </div>
		</div>
	</c:when>
  	<c:otherwise>
  		<div class="form-group">
		    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
		    <div class="col-sm-10">
		         <div class="row media-manager col-sm-12 images${field.name}">
		           <c:forEach var="image" items="${images}" varStatus="status">
			           <div class="col-xs-6 col-sm-4 col-md-3 document" style="margin-left: -10px;">
			             <div class="thmb">
			               <div class="btn-group fm-group">
			                   <button type="button" class="btn btn-default dropdown-toggle fm-toggle" data-toggle="dropdown">
			                     <span class="caret"></span>
			                   </button>
			                   <ul class="dropdown-menu fm-menu pull-right" role="menu">
			                     <li><a href="javascript:void(0);" onclick="showImageEdit('${field.name}', this)"><i class="fa fa-pencil"></i><fmt:message key="button.edit" /></a></li>
			                     <li><a href="javascript:void(0);" onclick="removeTag('.col-xs-6', this)"><i class="fa fa-trash-o"></i><fmt:message key="button.delete" /></a></li>
			                     <li><a href="javascript:void(0);" onclick="moveImageLeft('.col-xs-6', this)"><i class="glyphicon glyphicon-arrow-left"></i><fmt:message key="button.move.left" /></a></li>
			                     <li><a href="javascript:void(0);" onclick="moveImageRight('.col-xs-6', this)"><i class="glyphicon glyphicon-arrow-right"></i><fmt:message key="button.move.right" /></a></li>
			                   </ul>
			               </div>
			               <div class="thmb-prev">
			               	  <a href="${es:config('filehost')}${image['uri']}" data-rel="prettyPhoto" class="img-single">
			               	  	<img src="${es:config('filehost')}${image['uri']}" class="img-responsive" alt="${image['title']}" />
			               	  </a>	
			               	  <input type="hidden" name="${field.name}" value="${image['uri']}" />
			               	  <input type="hidden" name="${field.name}.title" value="${image['title']}" />
			               	  <input type="hidden" name="${field.name}.description" value="${image['description']}" />	
			               </div>
			               <h5 class="fm-title"><c:out value="${image['title']}"></c:out></h5>
			             </div>
			           </div>
			        </c:forEach>
		         </div>
		    	<button type="button" onclick="uploadImage('${field.name}');"  class="btn btn-primary mr5"><fmt:message key="field.image.upload" /></button>
		       	<div class="btn btn-primary btn-file" style="margin-left: 5px;display: none;">
			        <span class="fileupload-new"><fmt:message key="select.file" /></span>
			        <input type="file" accept="image/*" name="file" style="width: 200px;">
		   	    </div>
		    </div>
		</div>
	</c:otherwise> 
</c:choose> 
<c:set var="images" value="${null}" />