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