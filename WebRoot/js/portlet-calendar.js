var debug = false;


var Calendar = function() {
	var that = $('#calendar');
	
	var event_feeds = [{
		url: "EventAction!getListEvent",
		error: function() {
			alert('there was an error while fetching events!');
        }
	}];
	
	$('body').click(function(e) {
		if (e.isDefaultPrevented())
			return;
        $('.fc-event').popover('hide');
//        console.log("hide");
    });
	
	return {
		// main function to initiate the module
		init : function() {

//			App.addResponsiveHandler(function() {
//				Calendar.initCalendar();
//			});

			$('.page-sidebar .sidebar-toggler').click(function() {
				Calendar.initCalendar();
			});

			Calendar.initCalendar();
		},

		initCalendar : function() {
			
			if (!jQuery().fullCalendar) {
				return;
			}

			var h = {};

			if (App.isRTL()) {
				if (that.parents(".portlet").width() <= 720) {
					that.addClass("mobile");
					h = {
						right : 'title, prev, next',
						center : '',
						right : 'agendaDay, agendaWeek, month, today'
					};
				} else {
					that.removeClass("mobile");
					h = {
						right : 'title',
						center : '',
						left : 'agendaDay, agendaWeek, month, today, prev,next'
					};
				}
			} else {
				if (that.parents(".portlet").width() <= 720) {
					that.addClass("mobile");
					h = {
						left : 'title, prev, next',
						center : '',
						right : 'today,month,agendaWeek,agendaDay'
					};
				} else {
					that.removeClass("mobile");
					h = {
						left : 'title',
						center : '',
						right : 'prev,next,today,month,agendaWeek,agendaDay'
					};
				}
			}
			
			// destroy the calendar
			that.fullCalendar('destroy'); 
			
			// re-initialize the calendar
			that.fullCalendar( { 
				header : h,
				slotMinutes : 15,
				editable : true,
				droppable : true,
				drop : function(date, allDay) {
					var originalEventObject = $(this).data('eventObject');
					var copiedEventObject = $.extend({}, originalEventObject);

					copiedEventObject.start = date;
					copiedEventObject.allDay = allDay;
					copiedEventObject.className = $(this).attr("data-class");

					that.fullCalendar('renderEvent', copiedEventObject, true);

					if ($('#drop-remove').is(':checked')) {
						$(this).remove();
					};
				},
				eventRender : function(event, element) {
					console.log("rendering event...", event);
					
					window.rendercalevent = event;
					var html = "<b>" + event.location + "</b> <br />";
					html += "<br />" + event.start.toUTCString();
					if (event.end)
						html += "<br />" + event.end.toUTCString();
					
					// delete button
					html += '<button class="btn red"\
						href="#"\
						data-id="' + event.id + '" \
						style="width: 200px"\
						onclick="window.remveCalEvent(this)">删除</button>';
					
					element.popover({
						html : true,
						trigger: "click",
						placement : "right",
						title : event.title+'<a class="pull-right" href="#">编辑</a>',
						content : html
					});
					element.click(function(e) {
//						console.log("show");
//						$('.fc-event').popover('hide');
//						$(this).popover("show");
						e.preventDefault();
					});
				}
			});
			
			for (var i in event_feeds) {
				that.fullCalendar('addEventSource', event_feeds[i]);
			}
			
			window.remveCalEvent = function(element) {
				Calendar.removeEvent($(element).attr("data-id"));
			};
		},
		addEvent : function(event) {
			if (debug) {
				window.addcalevent = clone(event);
				console.log("addEvent(debug): ", clone(event));
				
				that.fullCalendar('renderEvent', event, true);
				return;
			} else {
				$.ajax({
					url : "EventAction!setEvent",
					data : event,
					error : function() {
						window.alert("addEvent error!");
					},
					success : function(id) {
						event.id = Number(id);
						console.log("addEvent: ", clone(event));
						that.fullCalendar('renderEvent', event, true);
					}
				});
			}
		},
		removeEvent : function(event_id) {
			if (debug) {
				that.fullCalendar('removeEvents', Number(event_id));
				return;
			}
			
			$.ajax({
				url : "EventAction!deleteEvent?id=" + event_id,
				success : function() {
					that.fullCalendar('removeEvents', Number(event_id));
				}
			});

		},
		refreshEvents : function() {
			that.fullCalendar('rerenderEvents');
		}

	};

}();

var a_dialog = function() {
	var dialog = null;
	var form = null;
	var datetimebox = null;
	
	return ({
		init : function(id) {
			dialog = $(id);
			form = dialog.find("form");
			datetimebox = dialog.find('input.datetimebox');
			
			function initDatetimebox() {
				datetimebox.datetimepicker({
					autoclose : true,
					format: "yyyy-mm-dd hh:ii:ss",
					todayHighlight: true,
					todayBtn: true,
					language: "zh-CN"
				});
			}
			function initDatebox() {
				datetimebox.datepicker({
					autoclose : true,
					format: "yyyy-mm-dd",
					todayHighlight: true,
					todayBtn: true,
					language: "zh-CN"
				});
			}
			
			initDatetimebox();
			
			// click event on allDay checkbox
			dialog.find('input[name="allDay"]').click(function() {
				datetimebox.val("");

				if ($(this)[0].checked) {
					datetimebox.datetimepicker('remove');

					initDatebox();
				} else {
					datetimebox.datepicker('remove');

					initDatetimebox();
				}
			});
		},
		show : function() {
			function date2format(now) {
				var datetime = now.getFullYear()+"-"+now.getMonth()+"-"+now.getDay();
				datetime += " " + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds();
				
				return datetime;
			}
			
			// clear inputs value
			form.find("input").val("");
			
			var D = new Date();
			var y = D.getFullYear();
			var m = D.getMonth();
			var d = D.getDate();
			var hh = D.getHours();
			var mm = D.getMinutes();
			
			form.find('input[name="start"]').val($.fullCalendar.formatDate(new Date(y, m, d, hh, mm), "yyyy-MM-dd hh:mm:ss"));
			form.find('input[name="end"]').val($.fullCalendar.formatDate(new Date(y, m, d, hh, mm+30), "yyyy-MM-dd hh:mm:ss"));
			
			// hide alert
			if (! dialog.find(".alert").hasClass("hide"))
				dialog.find(".alert").addClass("hide");
			
			dialog.modal('show');
		},
		hide : function() {
			dialog.modal('hide');
		},
		validate : function() {
			var ok = true;

			var title = dialog.find('[name="title"]');
			if (title.val().length === 0) {
				title.closest('.control-group').addClass("error");
				title.next().html("必填！！");
				ok = false;
			}

			var start = dialog.find('[name="start"]');
			if (start.val().length === 0) {
				start.closest('.control-group').addClass("error");
				start.parent().next().html("必填！！");
				ok = false;
			}

			return ok;
		},
		submit : function() {
			function form2json(array) {
				var ret = {};
				$.each(array, function() {
					ret[this.name] = this.value;
				});
				return ret;
			}
			
			var event = form2json(form.serializeArray());
			event.allDay = dialog.find('[name="allDay"]')[0].checked;
			
			if (this.validate()) {
				console.log("validate ok!!", event);

				// clean error messages
				dialog.find(".control-group").removeClass("error");
				dialog.find(".help-inline").html("");
				
				// convert time string to unix timestamp
				if (event.allDay) {	// date
					var date = event.start.match(/\d+/g);
					event.start = new Date(date[0], date[1] - 1, date[2]).getTime()/1000;
					date = event.end.match(/\d+/g);
					event.end = new Date(date[0], date[1] - 1, date[2]).getTime()/1000;
				} else {			// date time
					var datetime = event.start.match(/\d+/g);
					event.start = new Date(datetime[0], datetime[1] - 1, datetime[2], datetime[3], datetime[4]).getTime()/1000;
					datetime = event.end.match(/\d+/g);
					event.end = new Date(datetime[0], datetime[1] - 1, datetime[2], datetime[3], datetime[4]).getTime()/1000;
				}
				
				Calendar.addEvent(event);
				a_dialog.hide();
			}
		}
	});

}();

a_dialog.init("#dialog_eventadd");

$('#event_add').unbind('click').click(function() {
	a_dialog.show();
});
$('#event_submit').unbind('click').click(function() {
	a_dialog.submit();
});