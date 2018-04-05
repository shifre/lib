<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/layouts/commonImport.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="/WEB-INF/layouts/defaultTable.jsp"%>
	<script type="text/javascript">
	var sh;
	$(document).ready(function() {
		var aoColumn = [
           { "mDataProp": "clientName", "bSortable": true, "sWidth" : "15%",
           	"mRender": function ( data, type, full ) {
           		return $.es.encodeHtml(data);
           	}	  
           },
           { "mDataProp": "clientPhone", "bSortable": false, "sWidth" : "15%", "sClass": "wrapable",
              	"mRender": function ( data, type, full ) {
              		return $.es.encodeHtml(data);
              }	  
           },
           { "mDataProp": "siteName", "bSortable": true, "sWidth" : "15%", "sClass": "wrapable",
           	"mRender": function ( data, type, full ) {
           		return $.es.encodeHtml(data);
           	}	  
           },
           { "mDataProp": "createdTime", "bSortable": true, "sWidth" : "15%", "sClass": "wrapable",
           	"mRender": function ( data, type, full ) {
           		return $.es.datetime(data);
           	}	  
           },
           { "mDataProp": "expiryTime", "bSortable": true, "sWidth" : "15%", "sClass": "wrapable",
           	"mRender": function ( data, type, full ) {
           		return $.es.datetime(data);
           	}	  
           },
           { "mDataProp": "status", "bSortable": true, "sWidth" : "10%", "sClass": "wrapable",
              	"mRender": function ( data, type, full ) {
              			var ret = ""
                 		if(data == "ACTIVE")
                 		{
                 			ret = bundle['active'];
                 		}
                 		else if(data = "DISABLED")
                 		{
                 			ret = bundle['disabled'];
                 		}
                 		return ret;
              	}	  
              },
           { "mDataProp": "id", "bSortable": false, "sWidth" : "10%",
           	"mRender": function ( data, type, full ) {
                       return new ActionBuilder().start()
                       .add(bundle['detail'], "javascript:sh.showItem(" + full.id + ");", "glyphicon glyphicon-search")
                       .end();
             }
           }
       ];
		
		var shInitData = {
	            linkPrefix:"service",
				aoColumn:aoColumn,
				passConds: function(){
					var cb = new CondBuilder();
					cb.addWhere("clientName", "%", $("#title").val());
					return cb.conds;
				}
			};
			sh=new SearchHander(shInitData);
	});
	
	function showItem(siteId)
	{
		location.href = "${ctx}/site?id=" + siteId;
	}
	</script>
</head>
<body>
	<div class="contentpanel">
		<div class="panel  panel-default">
			<div class="panel-heading">
            	<div class="panel-btns col-sm-5">
            		<form>
	             		<button type="submit" class="btn btn-primary mr5 btnSearch right ladda-button"><fmt:message key="button.search" /></button>
		                <div class="col-sm-5 right">
		             		 <input type="text" class="form-control" id="title" placeholder="${es: local('client.title')}">
		             	</div>
	             	</form>
             	</div>
                <div class="panel-title">
	             	<fmt:message key="field.site.list" />
                </div>
            </div>
		</div>
		<table id="resultTable" class="table table-bordered table-hover">
		      <thead class="">
		          <tr>
		             <th><fmt:message key="client.title"/></th>
		             <th><fmt:message key="field.phone"/></th>
		             <th><fmt:message key="field.site.name"/></th>
		             <th><fmt:message key="field.created.time"/></th>
		             <th><fmt:message key="field.service.year"/></th>
		             <th><fmt:message key="field.status"/></th>
		             <th><fmt:message key="field.op"/></th>
		         </tr>
		      </thead>
		      <tbody>
		      </tbody>
		</table>
	 </div>
</body>
</html>