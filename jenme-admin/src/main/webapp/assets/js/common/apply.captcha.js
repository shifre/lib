$(document).ready(function() {
	$("#captcha").click(function() {
		refreshCaptcha();
	});
});

function refreshCaptcha()
{
	var $captcha = $("#captcha");
	var ind = $captcha.attr("src").lastIndexOf('?');
	var src = $captcha.attr("src");
	if (ind > 0)
	{
		src = $captcha.attr("src").substring(0, ind);
	}
	
	src = src + "?" + Math.random();
	$captcha.attr("src", src);
}