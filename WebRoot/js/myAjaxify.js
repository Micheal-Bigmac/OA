$("a.ajaxify").click(function(e) {
	e.preventDefault();
	pageContent = $('.page-content .page-content-body');
	
 	App.blockUI(pageContent, false);
	$.ajax({
		url: $(this).attr('href'), 
		success: function(res) {
			App.unblockUI(pageContent);
        	pageContent.html(res);
		}
	});
});
$("#selectAll").click( function (e) {
	e.preventDefault();
	console.log("select-all click");
	$("input[type='checkbox']").each(function () {
		if (! $(this)[0].checked)
			$(this).click();
	});
});

$("#unselect").click( function (e) {
	e.preventDefault();
	
	$("input[type='checkbox']").each(function () {
		$(this).click();
	});
 });
