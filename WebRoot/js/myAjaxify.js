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
$("#deleteChose").click(function(){
	var action = $(this).attr("data-action");
	action=action.split("|");
	
	var id;
	
	console.log("deletechose")
	console.log(action);
	console.log($('#fom').serialize());
	if($('input[name=delid]:checked').length==0){
		console.log("iii");
		return ;
	}
	$.ajax({
		url:action[0],
		data:$('#fom').serialize(),
		success: function(){
			reload();
		},
		error:function(){
			alert("操作失败");
		}
	});
	
	function reload(){
		var pageContent = $('.page-content .page-content-body');
		$.ajax({
			url:action[1],
			success: function(res){
				pageContent.html(res);
			},
		});
	}
});

