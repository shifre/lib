<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp" %>
<div class="form-group">
    <label class="col-sm-2 control-label"><fmt:message key="${field.title}" /></label>
    <div class="col-sm-7">
        <select name="${field.name}" class="city select" style="width:100%;" data-select-value="${es:htmlProp(data, field.name)}"  data-select-options="${es:html(field.plattern)}">
            <option value=""><fmt:message key="please.select" /></option>
        </select>
    </div>
</div>