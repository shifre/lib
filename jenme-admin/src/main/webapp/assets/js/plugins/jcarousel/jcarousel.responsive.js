function initImages(site, filehost)
{
	var jcarousels = $(".jcarousel");
	var prevs = $('.jcarousel-control-prev');
	var nexts = $('.jcarousel-control-next');
	var width;
	$(jcarousels).each(function(i, v){
		 
		 width = $(v).innerWidth();
	   	 if(width > 500)
	   	 {
	   		 width = width / 5 ;
	   	 }
	   	 else if(width > 400)
	   	 {
	   		 width = width / 4 ;
	   	 }
	   	 else if(width > 300)
	   	 {
	   		 width = width / 3 ;
	   	 }
		
		if($(v).find("li").length > 0)
		{
			$(v).on('jcarousel:reload jcarousel:create', function () {
	             
	             $(v).jcarousel('items').css('width', width + 'px');
	         })
			
			$(v).jcarousel({
	            wrap: 'circular'
	        });
			
			$(prevs[i]).jcarouselControl({
	            target: '-=1'
	        });
			
			$(nexts[i]).jcarouselControl({
				 target: '+=1'
	        });
		}	
		
		var $images = $(v).closest(".images"); 
		
		var id = $images.find("input[type='hidden']").val();
		var url = site + 'site/asset/image/upload?id=' + id;
		
    	var initData = {
                formId : 'formWillBeCreated',
                url : url,
                filehost: filehost,
                resultAt: null, 
                previewAt: null,
                promptOn: $("#basicForm"),
                callback: function(resp){
                	if(!resp.error)
                	{
                		var imgUrl = filehost + resp.value;
                		$ul = $(v).find("ul");
                		$ul.prepend("<li style='width:" + width + "px;'><img src='" + imgUrl + "'></li>");
                		
                		if($(v).find("li").length > 0)
                		{
                			$(v).on('jcarousel:reload jcarousel:create', function () {});
                			
                			$(v).jcarousel({
                	            wrap: 'circular'
                	        });
                			
                			$(prevs[i]).jcarouselControl({
                	            target: '-=1'
                	        });
                			
                			$(nexts[i]).jcarouselControl({
                				 target: '+=1'
                	        });
                		}
                	}	
                }
            };
    	
    	$images.find("input[type='file']").change(function(){
	    	$(this).upload(initData);
	    });  
		
	});	
}