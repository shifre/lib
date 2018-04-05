<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<div class="form-group">
    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
    <div class="col-sm-8 control-label">
        <div class="toggle toggle-success" data-toggle-checkbox="#${field.name}" name="${field.name}"></div>
        <input style="display:none;" type="checkbox" id="${field.name}" name="${field.name}" ${null == data || es:prop(data, field.name) ? 'checked="checked"': ''}/>
    </div>
</div>