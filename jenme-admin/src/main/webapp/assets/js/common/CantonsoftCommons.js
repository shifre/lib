var DEBUG_MODE = false;
var messages = {
	'zh' : {
		//datatables
		'sZeroRecords' : '没有查询到数据',
		'sLengthMenu' : "每页显示 _MENU_ 条记录",
		"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		"sInfoFiltered" : "(从 _MAX_ 条数据中查询)",
		"sFirst" : "首页",
		"sPrevious" : "前一页",
		"sNext" : "后一页",
		"sLast" : "尾页",
		"sSearch":"筛选",
		
		//order action
		
		"action.signoff": "签收",
		"action.confirm": "确认",
		"action.delivery": "发货",
		"action.completed": "完成",
		"action.cancel": "取消",
		
		// payment state
		"order.payment.state.payed":"已付款",
		"order.payment.state.unpay":"未付款",
		"order.payment.state.refund":"退款",
		// delivery state
		"order.delivery.state.undelivery":"未发货",
		"order.delivery.state.delivery":"已发货",
		"order.delivery.state.receipt":"已签收",
		"order.delivery.state.return":"退货",
		// order state
		"order.state.created":"新建",
		"order.state.confirmed":"已确认",
		"order.state.deliveried":"已发货",
		"order.state.receipted":"已签收",
		"order.state.completed":"完成",
		"order.state.canceled":"已失效",
		
		// payment method
		"order.paymentmentod.online":"在支付",
		"order.paymentmentod.cod":"货到付款",
		"order.express.name": "快递公司",
		"order.express.number": "快递单号",
		
		//others
		'locking':'加锁',
		'deblocking':'解锁',
		'all':'所有',
		'detail': '详情',
		'enter.site': '进入站点',
		'moveup': '上移',
		'movedown': '下移',
		'moveleft': '左移',
		'moveright': '右移',
		'existed.id' : '已存在此ID',
		'error.get': '获取失败, 请尝试重新获取或通过其它途径找到经纬度信息',
		'done.get' : '获取成功!',
		'prompt.refreshdata' : '确定要刷新数据吗?',
		'attach' : '附着',
		'error.save': '保存时发生错误',
		'done.save': '保存成功',
		'done.op': '操作成功',
		'done.upload':'上传成功',
		'manage' : '管理',
		'active' : '激活',
		'enabled' : '有效',
		'disabled' : '无效',
		'template.market' : '模版市场',
		'template.oneself' : '自定义',
		'agree' : '同意',
		'close' : '关闭',
		'buy' : '购买',
		'pay' : '支付',
		'use' : '使用',
		'export' : '导出',
		'import' : '导入',
		'preview' : '预览',
		'current.template' : '当前模版',
		'export.template' : '导出模版',
		'park.bw': '黑白名单',
		'y' : '是',
		'n' : '否',
		'site': '站点',
		'app' : 'APP',
		'option' : '选项',
		'value' : '值',
		'add' : '添加',
		'hours' : '小时',
		'mins' : '分钟',
		'secs' : '秒',
		'unnamed': '未命名',
		'add.image': '添加图片',
		'add.file': '添加文件',
		'add.tags': '添加标签',
		'add.article': '添加文章',
		'add.child.nav': '添加下级货架',
		'error.numberonly' : '仅允许输入数字',
		'article.content' : '文件内容',
		'edit' : '修改',
		'detail' : '详情',
		'remove' : '删除',
		'hint': '提示',
		'message': '消息', 
		'select' : '选择',
		'change' : '更换',
		'change.template' : '更换模版',
		'change.template.hint' : '更换模版后,原门店的数据将会删除,确定要更换模版',
		'please.select': '请选择',
		'terminal' : '终端',
		'park.card' : '停车卡',
		'tenpay.setting':'财付通设置',
		'please.check.article' : '请勾选文章!',
		'yes' : '是的',
		'iknow' : '知道了',
		'no,thanks' : '不，谢谢',
		'order.record' : '订单记录',
		'parking.record' : '停车记录',
		'password.save' : '保存密码',
		'title.dialog' : '弹出窗口',
		'title.notice' : '请注意',
		'discount.stores':'门店折扣',
		'select.file': '选择文件',
		'platform.user.admin': '平台管理员',
		'user.generic':'普通用户',
		'password.change':'修改密码',
		'prompt.save' : '确定要保存吗?',
		'prompt.remove' : '确定要删除',
		'prompt.close' : '确定要关闭?',
		'prompt.return' : '确定要返回?',
		'prompt.bind' : '确定要绑定?',
		'prompt.unbind' : '确定要取消绑定?',
		'prompt.addmore' : '是否继续添加?',
		'prompt.reject' : '确定要退回?',
		'prompt.approve' : '确定要接受?',
		'prompt.remove.site.nav': '该货架下面所有的子货架也将会跟着一起删除！',
		'prompt.not.add.site.nav': '还没有添加任何货架',
		'error.400' : "提交了非法数据。",
		'error.401' : "您的登录已超时，请重新登录。",
		'error.403' : "您没有进行此操作的权限。请重新登录后再尝试。",
		'error.500' : "服务器发生错误，请稍后重试。",
		'error.0' : "无法连接到服务器，请检查网络状态。",
		'error.others' : "服务器暂时无法提供服务，请稍后重试。",
		'error.client' : "接收服务器数据时发生错误。",
		'error.before.add.basic' :"请先填写基本信息。",
		'error.option.cannot.be.empty':"选项不能为空。",
		'error.value.cannot.be.empty':"值不能为空。",
		'error.article.has.been.added':"这篇文章已经添加过了。",
		'error.title.length.not.overstep.50':"名称的长度不能超过50字符。",
		'error.description.length.not.overstep.200':"描述的长度不能超过50字符。",
		'error.day.price.not.empty':"日价是必填项。",
		'error.day.price.format.is.wrong':'日价必须为小于等于10位的正数金额，如：99999.99。',
		'error.cashback.price.format.is.wrong':'请填写正确的数值，如:99.99。'
	},
	'en' : {
		//datatables
		'sZeroRecords' : 'No data returned',
		'sLengthMenu' : "_MENU_ record(s) a page",
		"sInfo" : "Displaying from _START_ to _END_ /Total _TOTAL_ record(s)",
		"sInfoFiltered" : "(in _MAX_ record(s))",
		"sFirst" : "First",
		"sPrevious" : "Previous",
		"sNext" : "Next",
		"sLast" : "End",
		"sSearch":"Filter",
		
		//order action
		"action.signoff": "Signoff",
		"action.confirm": "Confirm",
		"action.delivery": "Delivery",
		"action.completed": "Completed",
		"action.cancel": "Cancel",
		
		// payment state
		"order.payment.state.payed":"Payed",
		"order.payment.state.unpay":"Unpay",
		"order.payment.state.refund":"Refund",
		// delivery state
		"order.delivery.state.undelivery":"Undelivery",
		"order.delivery.state.delivery":"Delivery",
		"order.delivery.state.receipt":"Receipt",
		"order.delivery.state.return":"Return",
		// order state
		"order.state.created":"Created",
		"order.state.confirmed":"Confirmed",
		"order.state.deliveried":"Deliveried",
		"order.state.receipted":"Receipted",
		"order.state.completed":"Completed",
		
		// payment method
		"order.paymentmentod.online":"Online",
		"order.paymentmentod.cod":"Cash On Delivery",
		
		"order.express.name": "Express Name",
		"order.express.number": "Express Number",
		
		//others
		'locking':'Locking',
		'deblocking':'Deblocking',
		'all':'All',
		'detail': 'Detail',
		'enter.site': 'Enter site',
		'moveup': 'Move up',
		'movedown': 'Move down',
		'moveleft': 'Move left',
		'moveright': 'Move Right',
		'existed.id' : 'ID exists',
		'error.get': 'Failed to get, please try other alterative',
		'done.get' : 'Got!',
		'prompt.refreshdata' : 'Are you sure to refresh data?',
		'attach' : 'Attach',
		'error.save': 'Failed to save',
		'done.save': 'Saved',
		'done.upload':'Upload success ',
		'manage' : 'Manage',
		'active' : 'Active',
		'enabled' : 'Enabled',
		'disabled' : 'Disabled',
		'template.market' : 'Template market',
		'template.oneself' : 'Oneself',
		'agree' : 'Agree',
		'close' : 'Close',
		'buy' : 'Buy',
		'pay' : 'Pay',
		'use' : 'Use',
		'export' : 'Export',
		'import' : 'Import',
		'preview' : 'Preview',
		'export.template' : 'Export template',
		'current.template' : 'Current template',
		'park.bw': 'Black and white list',
		'y' : 'Yes',
		'n' : 'No',
		'site': 'Site',
		'app' : 'APP',
		'option' : 'Option',
		'value' : 'Value',
		'add' : 'Add',
		'hours' : 'Hours',
		'mins' : 'Mins',
		'secs' : 'Secs',
		'unnamed': 'Unnamed',
		'add.image': 'Add image',
		'add.file': 'Add file',
		'add.tags': 'Add tag',
		'add.article': 'Add article',
		'add.child.nav': 'Add child nav',
		'error.numberonly' : 'Number only',
		'article.content' : 'Article Content',
		'edit' : 'Edit',
		'remove' : 'Remove',
		'hint': 'Hint',
		'message': 'Message',
		'select' : 'Select',
		'change' : 'Change',
		'change.template' : 'Change template',
		'change.template.hint' : 'After changing the template, the original data store will be deleted, make sure you replace the template',
		'terminal' : 'Terminal',
		'park.card' : 'Park card',
		'tenpay.setting':'Tenpay setting',
		'please.select': 'Please choose',
		'please.check.article' : 'Please check the article!',
		'yes' : 'Yes',
		'iknow' : 'I know',
		'no,thanks' : 'No，thanks',
		'order.record' : 'Order record',
		'parking.record' : 'Parking record',
		'password.save' : 'Save Password',
		'title.dialog' : 'Popup',
		'title.notice' : 'Notice',
		'discount.stores':'Discount stores',
		'select.file': 'Select file',
		'platform.user.admin': 'Platform Manager',
		'user.generic':'Generic User',
		'password.change':'Change password',
		'prompt.save' : 'Are you sure to save?',
		'prompt.remove' : 'Are you sure to remove',
		'prompt.close' : 'Are you sure to close?',
		'prompt.return' : 'Are you sure to return?',
		'prompt.addmore' : 'Do you want to add one more?',
		'prompt.reject' : 'Are you sure to reject?',
		'prompt.bind' : 'Are you sure to bind?',
		'prompt.unbind' : 'Are you sure to unbind?',
		'prompt.approve' : 'Are you sure to accept?',
		'prompt.remove.site.nav': 'The columns below all child columns will be deleted along!',
		'prompt.not.add.site.nav': "Haven't add any columns,",
		'error.400' : "Illegal data",
		'error.401' : "Your session was timeout, please login",
		'error.403' : "You do not have proper rights now, please re-login",
		'error.500' : "Server error",
		'error.0' : "Cannot connect to server, please check network status",
		'error.others' : "Service is unavailable now, please retry later",
		'error.client' : "Error when processing data",
		'error.before.add.basic' :"Please fill out basic information",
		'error.option.cannot.be.empty':"Option cannot be empty",
		'error.value.cannot.be.empty':"Value cannot be empty",
		'error.article.has.been.added':"This article has been added",
		'error.title.length.not.overstep.50':"The name length can't more than 50 characters",
		'error.description.length.not.overstep.200':"Description length can't more than 200 characters",
		'error.day.price.not.empty':"Day price not empty",
		'error.day.price.format.is.wrong':'Day price must be less than or equal to 16 positive amount, such as: 99999.99'
}};
var bundle = messages[window.language];
if (!bundle)
{
	bundle = messages['zh'];
}

jQuery.es = {
	asialen: function(text)
	{
		var len = 0;
		var i = 0;
		while (i < text.length) {
			var chr1 = text.charCodeAt(i++);
			if (chr1 > 255)
			{
				len++;
			}
			else
			{
				len += 0.5;
			}
		}
		return len;
	},
	scrollTo: function(id)
	{
		var offset = $(id).offset().top;
    	$("html, body").animate({scrollTop: offset - 100}, 'slow');
	},
	encodeHtml: function(s){  
	    return (typeof s != "string") ? s :  
	        s.replace(/"|&|'|<|>|[\x00-\x20]|[\x7F-\xFF]|[\u0100-\u2700]/g,  
	                  function($0){  
	                      var c = $0.charCodeAt(0), r = ["&#"];  
	                      c = (c == 0x20) ? 0xA0 : c;  
	                      r.push(c); r.push(";");  
	                      return r.join("");  
	                  });  
	},
	formatMoney: function(num) {
		num = num.toString().replace(/\$|\,/g,'');  
	    if(isNaN(num))
	    {
	    	num = "0";
	    }	
	    //num = num/100;
	    sign = (num == (num = Math.abs(num)));  
	    num = Math.floor(num * 100 + 0.50000000001);  
	    cents = num % 100;  
	    num = Math.floor(num/100).toString();  
	    if(cents < 10)
	    {
		    cents = "0" + cents;
	    }	  
	    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	    {
		    num = num.substring(0,num.length-(4*i+3))+','+  num.substring(num.length-(4*i+3)); 
	    }	 
	    return (((sign)?'':'-') + num + '.' + cents);
	},
	date: function(dateVal)
	{
		if (!dateVal)
		{
			return "";
		}
		var date = new Date(dateVal);
		var year=date.getFullYear();
	    var month = date.getMonth() + 1;
	    if(month<10){
	    	month ="0"+month;
	    }
	    var day =date.getDate();
	    if(day<10){
	    	day ="0"+day;
	    }
	    return year + "-" + month + "-" + day;
	},
	datetime: function(dateVal)
	{
		if (!dateVal)
		{
			return "";
		}
		var date = new Date(dateVal);
		var year=date.getFullYear();
	    var month = date.getMonth() + 1;
	    if(month<10){
	    	month ="0"+month;
	    }
	    var day =date.getDate();
	    if(day<10){
	    	day ="0"+day;
	    }
	    
	    var hours = date.getHours();
	    if(hours<10){
	    	hours ="0"+hours;
	    }
	    var mins = date.getMinutes();
	    if(mins<10){
	    	mins ="0"+mins;
	    }
	    return year + "-" + month + "-" + day + " " + hours + ":" + mins;
	},
	datetimess: function(dateVal)
	{
		if (!dateVal)
		{
			return "";
		}
		var date = new Date(dateVal);
		var year=date.getFullYear();
	    var month = date.getMonth() + 1;
	    if(month<10){
	    	month ="0"+month;
	    }
	    var day =date.getDate();
	    if(day<10){
	    	day ="0"+day;
	    }
	    
	    var hours = date.getHours();
	    if(hours<10){
	    	hours ="0"+hours;
	    }
	    var mins = date.getMinutes();
	    if(mins<10){
	    	mins ="0"+mins;
	    }
	    var sec = date.getSeconds();
	    if(sec<10){
	    	sec ="0"+sec;
	    }

	    return year + "-" + month + "-" + day + " " + hours + ":" + mins + ":" + sec;
	},
	datehour: function(time)
	{
		if (!time)
		{
			return "";
		}
		var hours = parseInt("" + time / 3600);
 		var mins = parseInt("" + (time - parseInt(hours) * 3600) / 60);
 		var secs = "" + (time - parseInt(hours) * 3600 - parseInt(mins) * 60);
	    if( hours < 10 )
	    {
	    	hours = "0" + hours;
	    }
	    if( mins < 10 )
	    {
	    	mins = "0" + mins;
	    }
	    if( secs < 10 )
	    {
	    	secs = "0" + secs;
	    }
	    return hours + bundle['hours'] + mins + bundle['mins'] + secs + bundle['secs'];
	},
	encode: function(input)
	{
		var keyString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = $.es.utf8Encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + keyString.charAt(enc1) + keyString.charAt(enc2) + keyString.charAt(enc3) + keyString.charAt(enc4);
		}
		return output;
	},

	decode: function(input)
	{
		var keyString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_";

		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\-\_]/g, "");
		while (i < input.length) {
			enc1 = keyString.indexOf(input.charAt(i++));
			enc2 = keyString.indexOf(input.charAt(i++));
			enc3 = keyString.indexOf(input.charAt(i++));
			enc4 = keyString.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64 && chr2 > 0) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64 && chr3 > 0) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = $.es.utf8Decode(output);

		return output;
	},

	utf8Encode: function (string)
	{
		string = string.replace(/\r\n/g,"\n");
		var utftext = "";

		for (var n = 0; n < string.length; n++)
		{
			var c = string.charCodeAt(n);
 
			if (c < 128)
			{
				utftext += String.fromCharCode(c);
			}
			else if((c > 127) && (c < 2048))
			{
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			}
			else
			{
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}
		}
 
		return utftext;
	},
 
	utf8Decode : function (utftext)
	{
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
 
		while ( i < utftext.length )
		{
			c = utftext.charCodeAt(i);
 
			if (c < 128)
			{
				string += String.fromCharCode(c);
				i++;
			}
			else if((c > 191) && (c < 224))
			{
				c2 = utftext.charCodeAt(i+1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			}
			else
			{
				c2 = utftext.charCodeAt(i+1);
				c3 = utftext.charCodeAt(i+2);
				string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	}
};

/* http form submit*/
(function() {
    $.mergeJsonObject = function(jsonbject1, jsonbject2)  
    {  
        var resultJsonObject={};  
        for(var attr in jsonbject1){  
            resultJsonObject[attr]=jsonbject1[attr];  
        }  
        for(var attr in jsonbject2){  
            resultJsonObject[attr]=jsonbject2[attr];  
        }  

        return resultJsonObject;  
    };
	$.extend({
		form: function(action, extra, callback, promptOn)
		{
			var form = $('<form  action="' + action + '" method="post" promptOn="'+ promptOn +'"></form>');   
            $(form).ajax(true, extra, callback);
		}
	});
	
	$.extend({
		newform: function(action, method, promptOn)
		{
			var form = $('<form  action="' + action + '" method="' + method + '" promptOn="'+ promptOn +'"></form>');
            return form;
		}
	});
	
	$.fn.extend({
        upload: function(initData)
        {
            var form = $('<form  action="' + initData.url+ '" method="POST" id="' + initData.formId + '" enctype="multipart/form-data"></form>');   
            var oldElement = $(this);
            var newElement = $(oldElement).clone();
            
            var parent = $(oldElement).parent();
            $(oldElement).appendTo(form);
            
            var callback = function(resp){
            	$(oldElement).appendTo(parent);
            	if (!resp.error)
            	{
            		if (initData.resultAt)
                    {
                        $(initData.resultAt).val(resp.uri);
                        $(opts.resultAt).show();
                    }
                    if (initData.previewAt)
                    {
                        $(initData.previewAt).attr("src", initData.filehost + resp.uri);
                        $(initData.previewAt).show();
                    }
                    if (initData.callback)
                    {
                        initData.callback(resp);
                    }
            	}
            	else
            	{
            		addErrorMessage(findPromptOn(initData.promptOn), resp.message);
            	}
            }
            $(form).ajax(true, initData.extra, callback, $(initData.btnFile)[0]);
            var timer_alert = setTimeout(function() {
            	 $(".ladda-progress").remove();
                 $(".ladda-spinner").remove();
                 $(".ladda-label").attr("class", "fileupload-new");
                 $(".ladda-label").text(bundle[['select.file']]);
                 $(".ladda-label .fileupload-new").remove();
			}, 600);
        }
    });
	
	 $.fn.extend({
		 locate: function(url) {
			var loading = new LoadingHandler($(this)[0]);
			loading.show();
	  		window.document.location = url; 
		 }
	});
	 
	 $.fn.extend({
		 target: function(skipValidation, target, callback) {
		  		if (skipValidation == true || $(this).validate().form())
			    {
		  			var options = { 
		  		        target:        target,
		  		        beforeSubmit:  function (formData, jqForm, options) {
		  		        	
		  		        },
		  		        complete: function (xmlHttpRequest, statusText) {
	
		  		        },
		  		        success:       callback
		  		    };
		  	    	$(this).ajaxSubmit(options);
			    }
		  		else
		  		{
		  			$.es.scrollTo(findPromptOn($(this)));
		  		}
		 }
	 });
	 
	 $.fn.extend({
		 http: function(skipValidation) {
	  		if (skipValidation == true || $(this).validate().form())
		    {
				$(this).submit();
		    }
	  		else
	  		{
	  			$.es.scrollTo(findPromptOn($(this)));
	  		}
		 }
	});
	 
	$.fn.extend({
	  	ajax: function(skipValidation, extra, callback, btn, extraOpt) {
	  		var loading = new LoadingHandler(btn);
	  		if (skipValidation == true || $(this).validate().form())
		    {
	  			var handler = new FormHandler(this);
	  			var options = { 
				        beforeSubmit:  function (formData, jqForm, options) {
				        	loading.show();
				        	handler.internalShowRequest(formData, jqForm, options);
				        },
				        complete:	   function (xmlHttpRequest, statusText) {
				        	handler.internalShowResponse(xmlHttpRequest, statusText);
				        	loading.close();
				        	if (extraOpt && extraOpt.callbackCompleted)
				        	{
				        		extraOpt.callbackCompleted();
				        	}
				        },
				        success: function(data, status) {
				        	try
				        	{
				        		if (callback)
				        		{
					        		var ret = callback(data);
					        		if (ret == undefined || ret == true)
					        		{
					        		
					        		}
					        		else
					        		{
					        			return;
					        		}
				        		}
				        		if (extraOpt && extraOpt.mute)
				        		{
				        			
				        		}
				        		else
				        		{
					        		handler.handleSuccess(data, status);
				        		}
				        	}catch(err)
				        	{
			        			handler.handleException(err, data, status);
				        	}
				        },
				        error:		  function (xmlHttpRequest, statusText, errorThrown){
		        			//handler.handleError(xmlHttpRequest, textStatus, errorThrown);
				        },
				        data:          extra,
				        dataType:      'json'
				};
		    	$(this).ajaxSubmit(options);
		    }
	  		else
	  		{
	  			$.es.scrollTo(findPromptOn($(this)));
	  		}
	    }
	});
}).call(this);

function LoadingHandler(target)
{
	var l;
	var $target;
	if (target)
	{
		try{
			l = Ladda.create(target);
			$target = $(target);
		}catch(e){}
	}
	this.show = function(){
		try{
			if (l)
			{
				if (!$target.hasClass("ladda-button"))
				{
					$target.addClass("ladda-button");
				}
				$target.attr("data-style", "zoom-out");
			 	l.start();
			}
		}catch(e){}
	};
	
	this.close = function(){
		try{
			if (l)
			{
				var timer_alert = setTimeout(function() {
					l.stop();
				}, 500);
			}
		}catch(e){}
	};
}


function FormHandler(formObj)
{
	this.formObj = formObj;
	this.internalShowRequest = function (formData, jqForm, options) {
		this.hideMessage();
		if (DEBUG_MODE)
		{
		    alert('About to submit:' +  '\n\n' + $(jqForm)[0].action +  '\n\n' + $.param(formData) ); 
		}
	    return true; 
	};

	this.internalShowResponse = function (xmlHttpRequest, statusText) 
	{
		if (DEBUG_MODE)
		{
		    alert('Response code ' + xmlHttpRequest.status + ' text: \n\n' + xmlHttpRequest.responseText + ' statusText: ' + statusText); 
		}
		var message = "";
		if ("" + xmlHttpRequest.status == "400")
		{
			message = bundle['error.400'];
		}
		else if ("" + xmlHttpRequest.status == "401")
		{
			message = bundle['error.401'];
		}
		else if ("" + xmlHttpRequest.status == "403")
		{
			message = bundle['error.403'];
		}
		else if ("" + xmlHttpRequest.status == "500")
		{
			message = bundle['error.500'];
		}
		else if ("" + xmlHttpRequest.status == "0")
		{
			message = bundle['error.0'];
		}
		else if ("" + xmlHttpRequest.status != "200")
		{
			message = bundle['error.others'];
		}
		else
		{
			return;
		}
		this.complete("error", message);
	};
	
	this.handleError = function (xmlHttpRequest, textStatus, errorThrown)
	{
		this.complete("error", bundle['error.others']);
	};
	
	this.handleException = function (err, data, status)
	{
		if (DEBUG_MODE)
		{
			alert(err);
		}
		this.complete("error", bundle['error.client']);
	};
	
	this.handleSuccess = function(data, status)
	{
		if (data.error)
		{
			this.complete("error", data.message);
		}
		else
		{
			this.complete("success", data.message);
		}
	};
	
	this.complete = function (status, message)
	{
		this.showMessage(status, message);
	};
	
	this.showMessage = function(status, message)
	{
		var targetObj = findPromptOn($(this.formObj));
		if (status == "success")
		{
			triggerNotification(bundle['hint'], message);
		}
		else
		{
			addErrorMessage(targetObj, message);
		}
	}
	
	this.hideMessage = function()
	{
		removeErrorMessage();
	}
}

function findPromptOn(form)
{
	var promptOn = $(form).attr("promptOn");
	var targetObj = $(form);
	if ($(form).find(".errorForm").length > 0)
	{
		targetObj = $(form).find(".errorForm");
	}
	if (promptOn && promptOn.length > 0)
	{
		targetObj = $(promptOn);
	}
	return targetObj;
}

function triggerNotification(title, message, time, sticky, overlay, image)
{
	if (message && message.length > 0)
	{
		$.gritter.add({
			title: 	(typeof title !== 'undefined') ? title : bundle['hint'],
			text: 	(typeof message !== 'undefined') ? message : bundle['message'],
			image: 	(typeof image !== 'undefined') ? image : null,
			sticky: (typeof sticky !== 'undefined') ? sticky : false,
			time: 	(typeof time !== 'undefined') ? time : 3000
		});
	}
}
/*
 *   title
 *   message
 *   lblYes
 *   lblNo
 *   callbackYes
 *   callbackNo
 * */ 
function confirmDialog(opt)
{
	opt.yes = false;
	$("#confirmDialog").remove();
	
	var confirmPanel = '<div class="modal fade" id="confirmDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
	confirmPanel += '<div class="modal-dialog">';
	confirmPanel += '<div class="modal-content">';
	confirmPanel += '<div class="modal-header">';
	confirmPanel += '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>';
	confirmPanel += '<h4 class="modal-title" id="myModalLabel">'+ opt.title +'</h4>';
	confirmPanel += '</div>';
	confirmPanel += '<div class="modal-body">';
	confirmPanel += opt.message;
	confirmPanel += '</div>';
	confirmPanel += '<div class="modal-footer">';
	if(opt.lblNo)
	{
		confirmPanel += '<button type="button" class="btn btn-default confirmNo" data-dismiss="modal">' + opt.lblNo + '</button>';
	}
	if (opt.lblYes)
	{
		confirmPanel += '<button type="button" class="btn btn-primary confirmYes">' + opt.lblYes + '</button>';
	}
	confirmPanel += '</div>';
	confirmPanel += '</div>';
	confirmPanel += '</div>';
	confirmPanel += '</div>';
	$(confirmPanel).appendTo($(document.body));
	if (null != opt.callbackYes)
	{
		$('.confirmYes').click(function(){
			if (opt.callbackYes())
			{
				$("#confirmDialog").modal('hide');
				opt.yes = true;
			}
		});
	}
	
	if (null != opt.callbackNo)
	{
		$('.confirmNo').click(function(){
			if (opt.callbackNo())
			{
				$("#confirmDialog").modal('hide');
				opt.yes = false;
			}
		});
	}
	
    $("#confirmDialog").on('hidden', function(){
    	if (!opt.yes && null != opt.callbackNo)
    	{
    		//opt.callbackNo();
    	}
    });
	
	$("#confirmDialog").modal('show');
}

function DialogHandler()
{
	this.confirm = function(message, callbackX, callbackN)
	{
		var opt = {
			title : bundle['please.select'],
			message: message,
			lblYes: bundle['yes'],
			lblNo: bundle['no,thanks'],
			callbackYes: callbackX,
			callbackNo: callbackN
		};
		confirmDialog(opt);
	};
	
	this.alert = function(message, callbackX)
	{
		var opt = {
			title : bundle['title.notice'],
			message: message,
			lblYes: bundle['iknow'],
			lblNo: null,
			callbackOk: callbackX
		};
		confirmDialog(opt);
	};
}

function CondBuilder(){
	this.conds= new Array();
	this.add = function(cond){
		this.conds[this.conds.length] = cond;
	};
	this.addWhere = function(name, op, value)
	{
		var cond = {};
		cond.type = "where";
		cond.name = name;
		cond.op = op;
		cond.value = value;
		this.conds[this.conds.length] = cond;
		return this;
	};
	
	this.merge = function(otherConds)
	{
		this.conds = this.conds.concat(otherConds);
	};
	
	this.toString = function(){
		return JSON.stringify(this.conds);
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

	this.toConds = function()
	{
		var obj = this;
		obj.parseData();
		var cb = new CondBuilder();
		var pageCond = {};
		pageCond.type = "page";
		pageCond.number = obj.pageNum;
		pageCond.size = obj.pageSize;
		cb.add(pageCond);
		$(obj.dirData).each(function(i,v){
			if(v){
				var name = obj.propData[obj.sortData[i]];
				var pattern = v;
				var orderCond = {};
				orderCond.type = "order";
				orderCond.name = name;
				orderCond.asc = "desc" != pattern;
				cb.add(orderCond);
			}
		});
		return cb.conds;
	};
}

var defaultShInitData={
		popup:false,
		btnSearch:".btnSearch",
		table:"#resultTable",
		btnAdd:".btnAdd",

        linkPrefix:null,
		addLink:"edit.html",
		editLink:"edit.html?id=",
		showLink:"view.html?id=",
		
		searchAction:"search.json",
		removeAction:"delete.json",
		aoColumn:null,
		passConds: null
};

function SearchHander(initData){
	 initData = $.mergeJsonObject(defaultShInitData, initData);
	 if (!initData.linkPrefix)
     {
		 initData.linkPrefix = "";
     }
	 
	var oTable = null;
	this.editItem=function(id){
		window.location.href = initData.linkPrefix + initData.editLink+id;
	};
	this.showItem=function(id)
	{
		window.location.href = initData.linkPrefix + initData.showLink+id;
	}
	this.delItem=function(id, name){
		var dh = new DialogHandler();
		if (!name)
		{
			name = "";
		}
		dh.confirm(bundle["prompt.remove"] + ' <b>' + name + '</b>?', function(){
			var removeCallback = null;
			if (initData.callbackRemoved)
			{
				removeCallback = initData.callbackRemoved;
			}
			else
			{
				if (oTable)
				{
					if (!initData.key)
					{
						initData.key = "id";
					}
					var b = oTable.fnFindCellRowIndexes(id, initData.key);
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
			var parameters = {};
			parameters[initData.key] = id;
		    $.newform(initData.removeAction, "post", initData.promptOn).ajax(true, parameters, removeCallback, null, {mute: false});
    		return true;
		});
   	};
	this.retrieveData=function(sSource, aoData, fnCallback) {
		var objInitData = initData;
		var callback = function(data)
		{
			if (!data.error)
			{
				if (objInitData.rawDataCallback)
				{
					objInitData.rawDataCallback(data.data);
				}
				fnCallback(data.data);
			}
		};
		var dtConds = new DataTableConds(aoData);
		var cb = new CondBuilder();
		cb.merge(dtConds.toConds());
		if (null != objInitData.passConds)
		{
			cb.merge(objInitData.passConds(cb));
		}
	    $.newform(initData.searchAction, "post", objInitData.promptOn).ajax(true, {query: cb.toString()}, callback, $(objInitData.btnSearch)[0], {mute: true});
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
		$("form").on('submit', function(e) { e.preventDefault();}); // prevent native submit
		if (null == initData.promptOn)
		{
			initData.promptOn = initData.table;
		}
		if (null == oTable && false != initData.useDataTable)
		{
			oTable = applyDatatable(initData.table, initData.searchAction, this.retrieveData, initData.aoColumn, null, initData.tableExtraOpt);
		}
		$(initData.btnSearch).click(function(){
			oTable.fnDraw();
	    });
		
		$(initData.btnAdd).click(function() {
			window.location.href = initData.linkPrefix + initData.addLink;
		});
	};
	if (!initData.lazy)
	{
		this.init();
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
 
function getItemHtml(callbackStr, title, iconClass){
	return '<a href="' + callbackStr + '" class="btn" rel="tooltip" title="'+title+'"><i class="' + iconClass + '"></i></a>';
}
 
var defaultUhInitData ={
        id:"#id",
  		addLink: "edit.html",
  		editLink: "edit.html?id=",
  		returnLink: "search.html",
  		
        addAction: "add.json",
        updateAction: "update.json",
		removeAction:"delete.json",
        
        btnRemove : ".btnRemove",
        btnSave:".btnSave",
        btnEdit:".btnEdit",
        btnReturn:".btnReturn",
        submitForm:"#form",
        promptOn: ".errorForm",
        linkPrefix: null
 };
 
 function UpdateHander(initData){
	 initData = $.mergeJsonObject(defaultUhInitData, initData);
	 if (!initData.linkPrefix)
     {
		 initData.linkPrefix = "";
     }
	 this.changed = false;
	 var obj = this;
	 this.isAddMode = function()
	 {
		 return $(initData.id).length == 0 || $(initData.id).val() == "";
	 };
	 
	 if(initData.btnReturn)
	 {
		 $(initData.btnReturn).click(function(){
			 location.href = initData.linkPrefix + initData.returnLink;
		 });
	 }

	 if(initData.btnEdit)
	 {
		 $(initData.btnEdit).click(function(){
			 location.href = initData.linkPrefix + initData.editLink + $(initData.id).val();
		 });
	 }
	 if(initData.btnRemove)
	 {
		 $(initData.btnRemove).click(function(){
			 var dh = new DialogHandler();
			 dh.confirm(bundle['prompt.remove'] + "?", function(){
					 var removeCallback = function(data)
					 {
						 if (initData.removeCallback)
						 {
							 initData.removeCallback(data);
							 return;
						 }	 
						 if (!data.error)
					     {
							 location.href = initData.linkPrefix + initData.returnLink;
					     }
					 }
					 var parameters = {};
					 parameters["id"] = id.value;
				     $.newform(initData.removeAction+"?id="+parameters["id"], "post", initData.promptOn).ajax(true, parameters, removeCallback, null, {mute: false});
		    		return true;
				});
		 });
	 }	 
	 
	 $(initData.submitForm).on('submit', function(e) {
         e.preventDefault(); // prevent native submit
         if(obj.isAddMode()){
 			$(initData.submitForm).attr("action", initData.addAction);
 		}else{
 			$(initData.submitForm).attr("action", initData.updateAction);
 		}
 		if (initData.callbackBeforeSubmit)
 		{
 			if(initData.callbackBeforeSubmit() == false)
 			{
 				return;
 			}
 		}
 		$(initData.submitForm).ajax(false, null, callback,  $(initData.btnSave)[0]);
     });
	
	 var callback = function(resp){
		if(!resp.error){
			if (null != initData.callbackSave)
			{
				if (!initData.callbackSave(resp))
				{
					return;
				}
			}
			if(resp.data != null){
				if ($(initData.id).val() == "")
				{
					changed = true;
					$(initData.id).val(resp.data.id);
					var dh = new DialogHandler();
					dh.confirm(bundle['prompt.addmore'], function(){
						window.location.href = initData.linkPrefix + initData.addLink;
					}, function(){
						window.location.href = initData.linkPrefix + initData.returnLink;
					});
				}
			}else{
				if ($(initData.id).val() != "")
				{
					location.href = initData.linkPrefix + initData.returnLink;
				}
			}
		}
	};
}


function Concurrent(){
	this.signal = false;
	this.lock = function()
	{
		if (this.signal == true)
		{
			return false;
		}
		else
		{
			this.signal = true;
			return true;
		}
	};
	this.unlock = function(){
		this.signal = false;
	};
};

function checkNum(orders) {
	var r = /^\+?[1-9][0-9]*$/;
	if(orders.value != "" && !r.test(orders.value))
	{
		triggerNotification(bundle['hint'], bundle['error.numberonly']);
		orders.value = "";
	}
};

function openModaless(url, title, width, height, options)
{
	var shadeClose = false;
	var titleInfo = [bundle['title.dialog'], true];
	var maxmin = true;
	if (title)
	{
		titleInfo = [title,true];
	}
	
	if (options)
	{
		if (options.shadeClose == true)
		{
			shadeClose = true;
		}

		if (options.showTitle == false)
		{
			maxmin = false
			titleInfo = false;
		}
	}
	
	if (!width)
	{
		width = '900';
	}
	if (!height)
	{
		height = ($(window).height() - 100);
	}
	var winWidth = $(window).width();
	if (winWidth > 0)
	{
		if (width > winWidth)
		{
			width = winWidth;
		}
	}
	width = '' + width + 'px';
	height = '' + height + 'px';
	
	var i = $.layer({
		loading: {
		    type: 1
		},
		maxmin: maxmin,
	    type : 2,
	    shadeClose: shadeClose,
	    shade: [0.5, '#000'],
	    moveOut: false,
	    fix: false,
	    fadeIn: 300,
	    title : titleInfo,
	    iframe : {src : url},
	    area : [width , height],
	    offset : ['10px', ''],
	    close : function(index){
	    	if(window.needLoad){
	    		location.reload();
	    	}
	    	else if (window.sh)
	    	{
	    		window.sh.refreshTable();
	    	}
	    },
	    success: function(index){
	    	
	    }
	});
}

function addErrorMessage(targetObj, message){
	var text = '<label class="error">';
	text += message;
	text += '</label>';
	if($(targetObj).is('table')) {
		$(targetObj).before(text);
	} else {
		$(targetObj).append(text);
	}
	$.es.scrollTo($(targetObj));
}

function removeErrorMessage()
{
	$("label.error").remove();
}

$(document).ready(function() {
	//init
    var $text = $("input:text");
    if ($text.length > 0)
    {
    	$text.first().focus();
    }

});
