$(function() {
	$("#form").validation();
})

$("a.ajaxify").click(function(e) {
	e.preventDefault();
	pageContent = $('.page-content .page-content-body');

	App.blockUI(pageContent, false);
	$.ajax({
		url : $(this).attr('href'),
		success : function(res) {
			App.unblockUI(pageContent);
			pageContent.html(res);
		}
	});
});
$("#selectAll").click(function(e) {
	e.preventDefault();
	console.log("select-all click");
	$("input[type='checkbox']").each(function() {
		if (!$(this)[0].checked)
			$(this).click();
	});
});

$("#unselect").click(function(e) {
	e.preventDefault();

	$("input[type='checkbox']").each(function() {
		$(this).click();
	});
});
$("#deleteChose").click(function(e) {
	e.preventDefault();
	var action = $(this).attr("data-action");
	action = action.split("|");

	console.log("deletechose")
	console.log(action);
	console.log($('#fom').serialize());
	if ($('input[name=delid]:checked').length == 0) {
		console.log("iii");
		return;
	}
	var pageContent = $('.page-content .page-content-body');
	$.ajax({
		url : action[0],
		data : $('#fom').serialize(),
		success : function(e) {

			reload();
		},
		error : function() {
			alert("操作失败 有依赖性");
		}
	});

	function reload() {

		$.ajax({
			url : action[1],
			success : function(res) {
				pageContent.html(res);
			},
		});
	}
});
$("a.deleteOne").click(function(e) {
//	e.preventDefault();

	var action = $(this).attr("data-action");
	action = action.split("|");
//	App.blockUI(pageContent, false);
	console.log("deleteOne");

	var pageContent = $('.page-content .page-content-body');
	$.ajax({
		url : action[0],
		dataType: "text",
		success : function(res) {
			res=res.replace(/[\r]|[\n]/g,"");
			var result=res.replace(/.*<body>(.*)<\/body>.*/,"$1");
			
			console.log(result.trim());
			if(result.trim()=="操作成功"){
				reload();
			}else{
				pageContent.html(res);
				reload();
			}
		/*	if(res==""){
				console.log("null");
				
			}else{
				var result=res;
				
				
				console.log("not null");
//				alert(res);
			}*/
		},
		error : function() {
			alert("操作失败 有依赖性");
		}
	});

	function reload() {

		$.ajax({
			url : action[1],
			success : function(res) {
				pageContent.html(res);
			},
		});
	}
})

$("#submit").click(function(e) {
	var action = $(this).attr("data-action");
	console.log(action);
	console.log("aaa");
	if ($("#form").valid(this, '填写信息不完整。') == false) {
		return false;
	}

	pageContent = $('.page-content .page-content-body');
	$.ajax({
		url : $('#form').attr('action'),
		data : $('#form').serialize(),
		success : function(res) {
			pageContent.html(res);
			// e.preventDefault();

			$.ajax({
				url : action,
				success : function(res) {
					pageContent.html(res);
				}
			});
		}
	});
});
$('.datapicker').datepicker({
	autoclose : true,
	format : "yyyy-mm-dd",
	todayHighlight : true,
	todayBtn : true,
	language : "zh-CN"
});
$("#select").click(function(e) {
	e.preventDefault();
	var pageContent = $('.page-content .page-content-body');

	$.ajax({
		url : $('#form_Select').attr('action'),
		data : $('#form_Select').serialize(),
		success : function(res) {
			pageContent.html(res);
		},
		error : function() {
			alert("你输入的有问题");
		}
	});
});
