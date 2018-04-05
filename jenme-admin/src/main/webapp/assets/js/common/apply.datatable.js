

 /*
  * Data table support
  * */
 function applyDatatable(tableId, sAjaxSource, retrieveData, aoColumn, aoColumnDefs, opt)
 {
 	if (null == opt)
 	{
 		opt = {
 			remote:true
 		};
 	}
 	if (null == opt.remote)
 	{
 		opt.remote = true;
 	}
 	if (null == opt.bPaginate)
 	{
 		opt.bPaginate = true;
 	}
 	if (false != opt.sortable)
 	{
 		opt.sortable = true;		
 	}
 	if (!opt.defaultSorting)
 	{
 		opt.defaultSorting = [[ 0, "asc" ]];
 	}

 	var tableOpt = {
 		"oLanguage" : {
 				"sLengthMenu" : bundle['sLengthMenu'],
 				"sInfo" : bundle['sInfo'],
 				"sInfoEmpty": "",
 				"sInfoFiltered" : bundle['sInfoFiltered'],
 				"oPaginate" : {
 					"sFirst" : bundle['sFirst'],
 					"sPrevious" : bundle['sPrevious'],
 					"sNext" : bundle['sNext'],
 					"sLast" : bundle['sLast']
 				},
 				"sZeroRecords" : "<p class='dataTables_empty'>"+bundle['sZeroRecords']+"</p>",
 			    "sSearch":bundle['sSearch']
 			},
 	     "bAutoWidth": !!opt.bAutoWidth,
 	     "bPaginate" :opt.bPaginate,
 		 "sPaginationType": "full_numbers",
 	     "bSort": opt.sortable,
 	     "aaSorting": opt.defaultSorting,
 	     "headerClickable":true,
 	     "bFilter": false,
 	     "bLengthChange": false,
 	     "iDisplayLength": 10,
 	     "bProcessing": false,
 	     "bServerSide": opt.remote,
 	     "sAjaxSource": sAjaxSource,
 	     "fnServerData": retrieveData,
 	     "aoColumns":aoColumn,
 	     "aoColumnDefs" :aoColumnDefs,
 	     "scrollX":!!opt.scrollX
 			  };
 	if (opt.grouping == true)
 	{
 		tableOpt.bLengthChange = false;
 		tableOpt.bPaginate = false;
 	}
 	var oTable = $(tableId).dataTable(tableOpt);
 	if (opt.grouping == true)
 	{
 		var rowOpt = {
 			bExpandableGrouping: true
 		};
 		oTable.rowGrouping(rowOpt);
 	}
 	return oTable;
 }

 function CondBuilder(url){
	this.exportUrl = "export.json";
	if(url){
		this.exportUrl = url;
	}
	this.formHtml = "<form id='export_form' style='display: none;' action='"+ this.exportUrl +"' method='post'>";
	this.index = 0;	 
	this.json = {};
 	this.page = function(num, size)
 	{
 		this.json.page = num;
 		this.json.size = size;
 	};
 	this.sort = function(name, asc)
 	{
 		if (!this.json.sorts)
 		{
 			this.json.sorts = [];
 		}
 		this.json.sorts[this.json.sorts.length] = {name: name, asc: true == asc};
 	};
 	this.where = function(name, op, value)
 	{
 		if (!this.json.conds)
 		{
 			this.json.conds = [];
 		}
 		this.json.conds[this.json.conds.length] = {name: name, op: op, value: value};
		this.formHtml = this.formHtml + "<input name='conds["+ this.index +"].name' value='"+ name +"'>"
	     +"<input name='conds["+ this.index +"].op' value='"+ op +"'>"
	     +"<input name='conds["+ this.index +"].value' value='"+ value +"'>";
	    this.index ++;
 		return this;
 	};
 	
	this.startExport = function(){
    	$.fileDownload($("#export_form").attr("action"), {
		    successCallback: function (url) {
		    	$.gritter.add({ title : bundle['hint'], text : bundle['prompt.export.ok']});
		    },
		    failCallback: function (html, url) {
		    	var message = html.toString().split(",")[2].split(":")[1];
		    	message = message.substring(1, message.length - 1);
		    	$.gritter.add({ title : bundle['hint'], text : message});
		    },data:$("#export_form").serialize()
		});
    	return true;
    };
    
	this.exportReport = function(){				
		this.createSort();
		this.formHtml +"<input type='submit' id='submit_export_form' value='submit'></form>";
		$("#export_form").remove();
		$("body").append(this.formHtml);
		this.startExport();
    };
    
    this.createSort = function(){
    	var index = $("#resultTable thead th.sorting_asc").index();
    	var arry = window.sh.mDataPropArray();
    	if(index != -1){
    		this.formHtml = this.formHtml + "<input name='sorts[0].name' value='"+ arry[index] +"'>"
		     +"<input name='sorts[0].asc' value='"+ true +"'>";
    	}else{
    		index = $("#resultTable thead th.sorting_desc").index();
    		this.formHtml = this.formHtml + "<input name='sorts[0].name' value='"+ arry[index] +"'>"
		     +"<input name='sorts[0].asc' value='"+ false +"'>";
    	}
    }; 
 }

 function DataTableConds(aoData){
 	this.pageStart = 0;
 	this.pageNum = 0;
 	this.pageSize = 0;
 	this.sortData = new Array();
 	this.propData = new Array();
 	this.dirData = new Array();
 	this.data = aoData;
 	this.parseData = function()
 	{
 		var obj = this;
 		
 		$(obj.data).each(function(i,v){
 			if(v.name=="iDisplayStart"){
 				obj.pageStart=v.value;
 			}
 			else if(v.name=="iDisplayLength"){
 				obj.pageSize=v.value;
 			}
 			else if(v.name.indexOf("iSortCol_") >= 0){
 				obj.sortData[obj.sortData.length] = v.value;
 			}
 			else if(v.name.indexOf("mDataProp_") >= 0){
 				obj.propData[obj.propData.length] = v.value;
 			}
 			else if(v.name.indexOf("sSortDir_") >= 0){
 				var i = parseInt(v.name.substring("sSortDir_".length), 10);
 				obj.dirData[i] = v.value;
 			}
 		});
 		
 		obj.pageNum = obj.pageStart / obj.pageSize;
 		if (obj.pageStart > obj.pageNum * obj.pageSize)
 		{
 			obj.pageNum = obj.pageNum + 1;
 		}
 	};

 	this.make = function()
 	{
 		var obj = this;
 		obj.parseData();
 		var cb = new CondBuilder();
 		cb.page(obj.pageNum, obj.pageSize);

 		$(obj.dirData).each(function(i,v){
 			if(v){
 				var name = obj.propData[obj.sortData[i]];
 				var pattern = v;
 				cb.sort(name, "desc" != pattern);
 			}
 		});
 		return cb.json;
 	};
 }

 var defaultSearchOpts={
	 	popup:false,
 		key: "id",
 		btnSearch:".btnSearch",
 		table:"#resultTable",
 		btnAdd:".btnAdd",
        linkPrefix:null,
 		addLink:"edit.xhtml",
 		editLink:"edit.xhtml?id=",
 		showLink:"view.xhtml?id=",
 		
 		searchAction:"search.json",
 		removeAction:"delete.json?id=",
 		aoColumn:null,
 		passConds: null
 };

 function SearchHandler(opts){
 	 opts = $(opts).merge(defaultSearchOpts);
 	 if (!opts.linkPrefix)
      {
 		 opts.linkPrefix = "";
      }
 	var oTable = null;
 	this.editItem=function(id){
 		window.location.href = opts.linkPrefix + opts.editLink+id;
 	};
 	this.showItem=function(id)
 	{
 		window.location.href = opts.linkPrefix + opts.showLink+id;
 	}
 	this.delItem=function(id, name){
 		var dh = new DialogHandler();
 		if (!name)
 		{
 			name = "";
 		}
 		dh.confirm(bundle["prompt.remove"] + ' <b>' + name + '</b>?', function(){
 			var removeCallback = null;
 			if (opts.callbackRemoved)
 			{
 				removeCallback = opts.callbackRemoved;
 			}
 			else
 			{
 				if (oTable)
 				{
 					var b = oTable.fnFindCellRowIndexes(id, opts.key);
 			    	removeCallback = function(data)
 		    		{
 		    			if (b.length > 0)
 		    			{
 		    				if (!data.error)
 		    				{
 		    					oTable.fnDeleteRow(b[0]);
 		    				}
 		    			}
 		    		}
 				}
 			}
 		    $.newform(opts.removeAction + id, "post", opts.promptOn).ajax(false, null, removeCallback, null);
     		return true;
 		});
    };
 	this.retrieveData=function(sSource, aoData, fnCallback) {
 		if (opts.retrieveData)
		{
 			opts.retrieveData(sSource, aoData, fnCallback, oTable);
		}
 		else
 		{
 			var objInitData = opts;
 	 		var callback = function(data)
 	 		{
 	 			if (!data.error)
 	 			{
 	 				if (objInitData.rawDataCallback)
 	 				{
 	 					objInitData.rawDataCallback(data.data);
 	 				}
 	 				
 	 				var tabledata = {};
 	 				tabledata.iTotalRecords = data.data.total;
 	 				tabledata.iTotalDisplayRecords = data.data.total;
 	 				tabledata.aaData = data.data.elements;
 	 				fnCallback(tabledata);
 	 			}
 	 			else
 	 			{
 	 			}
 	 		};
 	 		var json = new DataTableConds(aoData).make();
 	 		if (null != objInitData.passConds)
 	 		{
 	 			var json2 = objInitData.passConds();
 	 			json = $(json).merge(json2);
 	 		}
 	 		$.newform(objInitData.searchAction, "post", objInitData.promptOn).ajax(false, json, callback, $(objInitData.btnSearch)[0], true);
 		}	
     };

 	this.refreshTable =  function ()
 	{
 		if (oTable)
 		{
 			oTable.fnStandingRedraw();
 		}
 		else
 		{
 			location.reload(true);
 		}
 	};
 	
 	this.table = function()
 	{
 		return oTable;
 	};
 	
 	this.init = function ()
 	{
 		if (null == opts.promptOn)
 		{
 			opts.promptOn = opts.table;
 		}
 		if (null == oTable && false != opts.useDataTable)
 		{
 			oTable = applyDatatable(opts.table, opts.searchAction, this.retrieveData, opts.aoColumn, null, opts.tableExtraOpt);
 		}
 		$(opts.btnSearch).click(function(){
 			oTable.fnDraw();
 	    });
 		
 		$(opts.btnAdd).click(function() {
 			window.location.href = opts.linkPrefix + opts.addLink;
 		});
 	};
 	if (!opts.lazy)
 	{
 		this.init();
 	}
	this.mDataPropArray = function(){
		var mDataPropArray = new Array();
		$(opts.aoColumn).each(function(i, v){
			mDataPropArray[mDataPropArray.length] = v.mDataProp;
		});
		return mDataPropArray;
	}
	
	this.rowData = function(value, key) {
		var row = oTable.fnFindCellRowIndexes(value, key);
		return oTable.fnGetData()[row];
	}
	
	this.rowIndexe = function(value, key) {
		var row = oTable.fnFindCellRowIndexes(value, key);
		return row;
	}
	
	this.addRow = function(row) {
		var row = oTable.api().row.add(row).draw(false);
		return row;
	}
 }

 function ActionBuilder()
 {
 	 this.actions = "";
 	 this.count = 0;
 	 this.start = function()
 	 {
 		 return this;
 	 };
 	 this.add = function(title, action, icon, click)
 	 {
 		 this.actions += '<a href="' + action+ '" class="icon" rel="tooltip" title="'+title+'" onclick="' + click + '"><i class="' + icon + '"></i></a>';
 		 return this;
 	 };
 	 
 	 this.end = function()
 	 {
 		 return this.actions;
 	 };
 }