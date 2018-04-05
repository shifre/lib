$.fn.datetimepicker.dates['en'] = {
		days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
		daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
		daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
		months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
		today: "今天",
		suffix: [],
		meridiem: ["上午", "下午"]
};

function renderForm(scope)
{
	//CKEDITOR.replaceAll();
	//$(scope).find('.html').ckeditor();

	$(scope).find('.datetimepicker').datetimepicker({
		format: 'yyyy-MM-dd hh:mm:ss',
        pickDate: true,
        pickTime: true,
        hourStep: 1,
        minuteStep: 15,
        secondStep: 30,
        inputMask: true
    });
	
	$(scope).find('.datepicker').datetimepicker({
		format: 'yyyy-MM-dd',
        pickDate: true,
        pickTime: false,
        hourStep: 1,
        minuteStep: 15,
        secondStep: 30,
        inputMask: true
    });
	
	$(scope).find('.toggle').each(function(e, n){
        var checked = $($(n).attr("data-toggle-checkbox")).attr("checked");
        var isOn = false;
        if (checked)
        {
            isOn = true;
        }
        $(this).toggles({
              drag: true,
              click: true,
              on:isOn,
              text: {
                on: 'ON',
                off: 'OFF'
              },
              animate: 500,
              width: 70,
              height: 20,
              type: 'compact'
          });
        $(this).bind('toggle', function (e, active) {
            $($(this).attr("data-toggle-checkbox")).attr("checked", active);
        });
    });
	
	$(scope).find('.select').each(function(e, n){
		try
		{
			
		}catch(e){alert(e);}
		$(this).select2({});
	});
}
    

jQuery(document).ready(function() {
   "use strict";
  	//city
  	$('.city').each(function(){
  		var $obj = $(this);
  		var selectedValue = $obj.attr("data-select-value");
  		$(cityJson.children).each(function(e,n){
  			var group = "<optgroup></optgroup>";
  			$obj.append(group);
  			var $child = $obj.children().last();
  			$child.attr("label", n.value);
  			$(n.children).each(function(j, k){
  	  			var option = "<option></option>";
  	  			$child.append(option);
  	  			var $child2 = $child.children().last();
  	  			$child2.text(k.value);
  	  			$child2.val(k.key);
  	  			if (k.key == selectedValue)
  	  			{
  	  				$child2.attr("selected", true);
  	  			}
  			});
  		});
  	});

  	$('.expresscompany').each(function(){
  		var $obj = $(this);
  		var selectedValue = $obj.attr("data-select-value");
  		
  		$(expresscompanyJson.children).each(function(e,n){
  			var option = "<option></option>";
  			$obj.append(option);
  			
  			var $child = $obj.children().last();
  			$child.attr("label", n.value);
	  		$child.text(n.value);
	  		$child.val(n.key);
	  		if(n.key == selectedValue){
	  			$child.attr("selected",true)
	  		}
  			
  		});
  	});
  	
    renderForm('body');
    
  	
   // Tooltip
   jQuery('.tooltips').tooltip({ container: 'body'});

   // Popover
   jQuery('.popovers').popover();

   // Show panel buttons when hovering panel heading
   jQuery('.panel-heading').hover(function() {
      //jQuery(this).find('.panel-btns').fadeIn('fast');
   }, function() {
      //jQuery(this).find('.panel-btns').fadeOut('fast');
   });

   // Close Panel
   jQuery('.panel .panel-close').click(function() {
      jQuery(this).closest('.panel').fadeOut(200);
      return false;
   });

   // Minimize Panel
   jQuery('.panel .panel-minimize').click(function(){
      var t = jQuery(this);
      var p = t.closest('.panel');
      if(!jQuery(this).hasClass('maximize')) {
         p.find('.panel-body, .panel-footer').slideUp(200);
         t.addClass('maximize');
         t.find('i').removeClass('fa-minus').addClass('fa-plus');
         jQuery(this).attr('data-original-title','Maximize Panel').tooltip();
      } else {
         p.find('.panel-body, .panel-footer').slideDown(200);
         t.removeClass('maximize');
         t.find('i').removeClass('fa-plus').addClass('fa-minus');
         jQuery(this).attr('data-original-title','Minimize Panel').tooltip();
      }
      return false;
   });

   jQuery('.leftpanel .nav .parent > a').click(function() {

      var coll = jQuery(this).parents('.collapsed').length;

      if (!coll) {
         jQuery('.leftpanel .nav .parent-focus').each(function() {
            jQuery(this).find('.children').slideUp('fast');
            jQuery(this).removeClass('parent-focus');
         });

         var child = jQuery(this).parent().find('.children');
         if(!child.is(':visible')) {
            child.slideDown('fast');
            if(!child.parent().hasClass('active'))
               child.parent().addClass('parent-focus');
         } else {
            child.slideUp('fast');
            child.parent().removeClass('parent-focus');
         }
      }
      return false;
   });

// For Media Queries
   jQuery(window).resize(function() {
      hideMenu();
   });

   hideMenu(); // for loading/refreshing the page
   function hideMenu() {

      if($('.header-right').css('position') == 'relative') {
         $('body').addClass('hidden-left');
         $('.headerwrapper, .mainwrapper').removeClass('collapsed');
      } else {
         $('body').removeClass('hidden-left');
      }

      // Seach form move to left
      if ($(window).width() <= 360) {
         if ($('.leftpanel .form-search').length == 0) {
            $('.form-search').insertAfter($('.profile-left'));
         }
      } else {
         if ($('.header-right .form-search').length == 0) {
            $('.form-search').insertBefore($('.btn-group-notification'));
         }
      }
   }

   collapsedMenu(); // for loading/refreshing the page
   function collapsedMenu() {
      if($('.logo').css('position') == 'relative') {
         $('.headerwrapper, .mainwrapper').addClass('collapsed');
      } else {
         $('.headerwrapper, .mainwrapper').removeClass('collapsed');
      }
   }
   
   // Menu Toggle
   jQuery('.menu-collapse').click(function() {
	      if (!$('body').hasClass('hidden-left')) {
	         if ($('.headerwrapper').hasClass('collapsed')) {
	            $('.headerwrapper, .mainwrapper').removeClass('collapsed');
	         } else {
	            $('.headerwrapper, .mainwrapper').addClass('collapsed');
	            $('.children').hide(); // hide sub-menu if leave open
	         }
	      } else {
	         if (!$('body').hasClass('show-left')) {
	            $('body').addClass('show-left');
	         } else {
	            $('body').removeClass('show-left');
	         }
	      }
	      return false;
	   });

   // Add class nav-hover to mene. Useful for viewing sub-menu
   jQuery('.leftpanel .nav li').hover(function(){
      $(this).addClass('nav-hover');
   }, function(){
      $(this).removeClass('nav-hover');
   });

});