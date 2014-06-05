(function($) {
	/*
	 * Function: fnGetColumnData Purpose: Return an array of table values from a
	 * particular column. Returns: array string: 1d data array Inputs:
	 * object:oSettings - dataTable settings object. This is always the last
	 * argument past to the function int:iColumn - the id of the column to
	 * extract the data from bool:bUnique - optional - if set to false
	 * duplicated values are not filtered out bool:bFiltered - optional - if set
	 * to false all the table data is used (not only the filtered)
	 * bool:bIgnoreEmpty - optional - if set to false empty values are not
	 * filtered from the result array Author: Benedikt Forchhammer
	 * <b.forchhammer /AT\ mind2.de>
	 */
	$.fn.dataTableExt.oApi.fnGetColumnData = function(oSettings, iColumn,
			bUnique, bFiltered, bIgnoreEmpty) {
		// check that we have a column id
		if (typeof iColumn == "undefined")
			return new Array();

		// by default we only want unique data
		if (typeof bUnique == "undefined")
			bUnique = true;

		// by default we do want to only look at filtered data
		if (typeof bFiltered == "undefined")
			bFiltered = true;

		// by default we do not want to include empty values
		if (typeof bIgnoreEmpty == "undefined")
			bIgnoreEmpty = true;

		// list of rows which we're going to loop through
		var aiRows;

		// use only filtered rows
		if (bFiltered == true)
			aiRows = oSettings.aiDisplay;
		// use all rows
		else
			aiRows = oSettings.aiDisplayMaster; // all row numbers

		// set up data array
		var asResultData = new Array();

		for (var i = 0, c = aiRows.length; i < c; i++) {
			iRow = aiRows[i];
			var aData = this.fnGetData(iRow);
			var sValue = aData[iColumn];

			// ignore empty values?
			if (bIgnoreEmpty == true && sValue.length == 0)
				continue;

			// ignore unique values?
			else if (bUnique == true
					&& jQuery.inArray(sValue, asResultData) > -1)
				continue;

			// else push the value onto the result data array
			else
				asResultData.push(sValue);
		}

		return asResultData;
	};
}(jQuery));


var PortletTable = function() {
	var oTable = null;
	var that = $("#portlet_table");
	var select_names = null;
	var select_values = null;
	
	var sel_url = "OrganizationAction!getListJson?index=-1";
	var add_url = "OrganizationAction!add";
	var mod_url = "OrganizationAction!update";
	var del_url = "OrganizationAction!delete";
	
	var edit_mode = false;
	var select_mode = true;	// false while editing a row
	
	return {
		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}
			
			// begin first table
			oTable = that.dataTable( {
				"aoColumns" : [
				    {
				    	"sTitle": "机构序号"
					},
					{ 
						"sTitle": "机构名称"
					},
					{
						"sTitle": "机构编号"
					}, 
					{
						"sTitle": "机构描述",
						"bSortable" : false
					}, 
					{
						"sTitle": "所属机构",
						"bSortable" : false
					}
				],
				"aLengthMenu" : [ 
				    [ 5, 15, 20, -1 ],
					[ 5, 15, 20, "All" ] // change per page values here
				],
				// set the initial value
				"iDisplayLength" : 15,
				"sDom" : "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "显示 _MENU_ 记录",
					"oPaginate" : {
						"sPrevious" : "上页",
						"sNext" : "下页"
					},
					"sInfo" : "共_TOTAL_条记录  (第_START_条-第_END_条)"
				},
				"fnCreatedRow": function(nRow, aData, iDataIndex) {
					$(nRow).click(function() {
						if (edit_mode && select_mode)
							$(this).toggleClass('row_selected');
					});
				}
			});

			window.oTable = oTable;
			
			jQuery('#sample_1_wrapper .dataTables_filter input').addClass(
					"m-wrap medium"); // modify table search input
			jQuery('#sample_1_wrapper .dataTables_length select').addClass(
					"m-wrap small"); // modify table per page dropdown
			
			PortletTable.initToolBar();
			
			PortletTable.getData();
		},
		initToolBar: function() {
			var tool_add = $("#tool_add");
			var tool_remove = $("#tool_remove");
			
			var tool_ok = $("#tool_ok");
			var tool_cancel = $("#tool_cancel");
			
			var tool_group = $("#tool_group span");
			var tool_edit = $("#tool_edit");
			var tool_refresh = $("#tool_refresh");
			
			var table_form = $("#portlet-form");
			
			var nEditing = null;
			
			// BASIC FUNCTIONS
			function editRow(nRow) {
				function fnCreateSelect() {
					var r = '<select class="full-wrap" name="parentid">';
					var i, iLen = select_names.length;
					
					r += '<option value=""></option>';
					
					for (i = 0; i < iLen; i++) {
						r += '<option value="' + select_values[i] + '">' + select_names[i] + '</option>';
					}
					return r + '</select>';
				}
				
				var aData = oTable.fnGetData(nRow);
				$(nRow).addClass("full-wrap");
				var iTerms = $('>td', nRow);
				console.log(iTerms);
				
				// name
				iTerms[0].innerHTML = '<input class="full-wrap" type="text"name="organization.name" value="' + iTerms[0].innerHTML + '" />';
				// sn
				iTerms[1].innerHTML = '<input class="full-wrap" type="text" name="organization.description" value="' + iTerms[1].innerHTML + '"/>';
				// desp
				iTerms[2].innerHTML = '<input class="full-wrap" type="text" name="" value="' + iTerms[2].innerHTML + '"/>';
				// pname
				iTerms[3].innerHTML = fnCreateSelect();

			}
			
			tool_edit.click(function() {
				tool_group.show();
				tool_edit.hide();
				
				edit_mode = true;
				// hide id
				oTable.fnSetColumnVis(0, false);
			});

			tool_cancel.click(function() {
				tool_group.hide();
				tool_edit.show();

				edit_mode = false;
				
				oTable.fnSetColumnVis(0, true);
				tool_refresh.click();
			});
			
			// TOOL ADD
			tool_add.mode = false;
			tool_add.modify = function(status) {
				if (tool_add.mode == status)
					return;
				
				// change tool_add icon
				if (status) {
					// to modify mode
				} else {
					// to add mode
				}
				tool_add.mode = status;
			};
			
			tool_add.click(function() {
				if (! $(this).hasClass('disable')) {
					select_mode = false;
					
					if (tool_add.mode == false) { // add a new row
						var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
						var nRow = oTable.fnGetNodes(aiNew[0]);
						//window.nRow = clone(nRow);
						console.log(nRow);
						nEditing = nRow;
						editRow(nRow);
					} else { 					// edit select row
						nEditing = $(".row_selected")[0];
						window.nRow = nEditing;
						$(".row_selected").removeClass('row_selected');
						editRow(nEditing);
					}
					
					tool_add.addClass('disable');
					tool_remove.removeClass('disable');
					tool_ok.removeClass('disable');
				}
			});
			
			tool_ok.click(function() {
				if (! $(this).hasClass('disable')) {
					// FIXME: validate...
					
					
					var data = table_form.serialize();
					console.log("tool_ok: ", data);

					if (tool_add.mode == false)	// add a new row
						$.ajax({
							url: add_url,
							data: data,
							success: function(res) {
								// FIXME: check res
								
								// If submit OK!!
								tool_refresh.click();
								select_mode = true;
								tool_add.removeClass('disable');
								tool_remove.addClass('disable');
								tool_ok.addClass("disable");
							}
						});
					else	// update a row
						$.ajax({
							url: mod_url,
							data: data,
							success: function(res) {
								// FIXME: check res
								
								// If submit OK!!
								tool_refresh.click();
								select_mode = true;
								tool_add.removeClass('disable');
								tool_remove.addClass('disable');
								tool_ok.addClass("disable");
							}
						});
				}
			});
			
			tool_remove.click(function() {
				if (! $(this).hasClass('disable')) {
					if (select_mode) {
						$(".row_selected").each(function() {
							var aData = oTable.fnGetData($(this)[0]);
							console.log(aData[0]);
							
							$.ajax({
								url: del_url + "?deleteOrgId=" + aData[0],
								success: function(res) {
									console.log("delete ok!!");
									// If submit OK!!
									tool_refresh.click();
									select_mode = true;
								}, 
								error: function(res) {
									console.log("delete failed!!");
								}
							});
						});
						
						tool_refresh.click();
						$(this).addClass('disable');
					} else { // add/edit mode
						// FIXME: if edit a row, roll back row data
						// if add a row, delete that row
						// right now, simply refresh all data
						tool_refresh.click();
						tool_remove.addClass("disable");
						select_mode = true;
					}
				}
			});
			
			tool_refresh.click(function() {
				oTable.fnClearTable(false);
				PortletTable.getData();
			});
			
			// row selection
			that.click(function() {
				console.log("click!!!", edit_mode, select_mode);
				
		        if (edit_mode && select_mode) {
		        	if ($(".row_selected").length > 0) {
		        		tool_add.modify(true); // change tool_add to modify
			        	tool_remove.removeClass("disable");

		        		if ($(".row_selected").length == 1) {
			        		tool_add.removeClass('disable');
			        	} else {
			        		// disable add
			        		tool_add.addClass('disable');
			        	}
		        	} else {
		        		tool_add.modify(false);
		        		tool_add.removeClass("disable");
		        		tool_remove.addClass("disable");
		        	}
		        }
		    } );
			
		},
		getData : function() {
			$.post(sel_url, {}, function(res) {
				data = eval(res);
				
				oTable.fnAddData(data);
				
				select_names = oTable.fnGetColumnData(4);
				select_values = oTable.fnGetColumnData(5);
//				console.log(select_names, select_values);
			});
		}
	};

}();


$('#portlet-title').affix({offset:{ top: 137 }});

$(window).resize(function() {
	$('#portlet-title').width($('#portlet-form').width() - 20);
});
