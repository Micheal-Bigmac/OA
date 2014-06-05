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

function fnCreateSelect(aData, property) {
	var r = '<select class="full-wrap" name="parentid"><option value=""></option>', i, iLen = aData.length;
	for (i = 0; i < iLen; i++) {
		r += '<option value="' + aData[i] + '">' + aData[i] + '</option>';
	}
	return r + '</select>';
}

function fnCreateInput(aData, property) {
	var r = '<input type="text" class="full-wrap" ';

	if (property.name)
		r += 'name="' + property.name + '" ';
	if (property.value)
		r += 'value="' + property.value + '"/>';
	return r;
}

var PortletTable = function() {
	var oTable = null;
	var that = $("#portlet_table");
	var sel_url = "SupplierContractAction!SupplierContactListJson";
	var add_url = null;
	var del_url = null;
	
	return {
		// main function to initiate the module
		init : function() {

			if (!jQuery().dataTable) {
				return;
			}
			

			oTable = that.dataTable( {
				"aoColumns" : [
				    {
				    	"sTitle": "编号"
					},
					{ 
						"sTitle": "联系人名称"
					},
					{
						"sTitle": "供应商名称"
					}, 
					{
						"sTitle": "职位",
						"bSortable" : false
					},
					{
						"sTitle": "性别",
						"bSortable" : false
					}, 
					{
						"sTitle": "工作电话",
						"bSortable" : false
					}, 
					{
						"sTitle": "手机号",
						"bSortable" : false
					}, 
					{
						"sTitle": "QQ",
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
				"bDestroy": true
			});

			
			jQuery('#sample_1_wrapper .dataTables_filter input').addClass(
					"m-wrap medium"); // modify table search input
			jQuery('#sample_1_wrapper .dataTables_length select').addClass(
					"m-wrap small"); // modify table per page dropdown
			
			// jQuery('#sample_1_wrapper .dataTables_length select').select2();
			// // initialzie select2 dropdown

			var aSelected = [];
			
			$('tbody tr', that).live('click', function () {
		        var id = this.id;
		        var index = jQuery.inArray(id, aSelected);
		         
		        if ( index === -1 ) {
		            aSelected.push( id );
		        } else {
		            aSelected.splice( index, 1 );
		        }
		         
		        $(this).toggleClass('row_selected');
		    } );
			
			PortletTable.initToolBar();
			
			PortletTable.getData();
		},
		initToolBar: function() {
			// tool buttons
			var tool_add = $("#tool_add");
			var tool_remove = $("#tool_remove");			
			var tool_ok = $("#tool_ok");
			var tool_cancel = $("#tool_cancel");
			var tool_group = $("#tool_group span");
			var tool_edit = $("#tool_edit");
			var tool_refresh = $("#tool_refresh");
		
			var table_form = $("#portlet-form");			
			
			var nEditing = [];
			
			function editRow(nRow) {
				var aData = oTable.fnGetData(nRow);
				var iTerms = $('>td', nRow);
				var iNames = ["organization.name", "organization.description", "", ""];
				
				$(nRow).addClass("full-wrap");
				
				// name
				iTerms[0].innerHTML = fnCreateInputText(null, {name:iNames[0], value:iTerms[0].innerHTML});
				// sn
				iTerms[1].innerHTML = fnCreateInputText(null, {name:iNames[1], value:iTerms[2].innerHTML});
				// desp
				iTerms[2].innerHTML = fnCreateInputText(null, {name:iNames[2], value:iTerms[2].innerHTML});
				// pname
				iTerms[3].innerHTML = fnCreateSelect(oTable.fnGetColumnData(4), {name:iNames[2], value:iTerms[2].innerHTML});

			}
			
			function clickAdd() {
				var aiNew = oTable.fnAddData([ '', '', '', '', '' ]);
				var nRow = oTable.fnGetNodes(aiNew[0]);
				editRow(nRow);
				nEditing = nRow;
				
				tool_add.addClass("disable");
				
				tool_ok.removeClass("disable");
				tool_ok.one("click", clickOk);
				tool_remove.removeClass("disable");
				tool_remove.one("click", clickRemove);
			}
			
			function clickOk() {
				var data = table_form.serialize();
				console.log(data);
				
				// If submit OK!!
				$('>td', nEditing).each(function() {
					$(this).children().removeAttr("name");
				});
				
				tool_add.removeClass("disable");
				tool_add.one("click", clickAdd);
				
				tool_ok.addClass("disable");
				tool_remove.addClass("disable");
			}
			
			function clickRemove() {
				
			}
			
			tool_edit.click(function() {
				tool_group.show();
				tool_edit.hide();

				// hide id
				oTable.fnSetColumnVis(0, false);
			});

			tool_cancel.click(function() {
				tool_group.hide();
				tool_edit.show();

				oTable.fnSetColumnVis(0, true);
			});

			tool_add.unbind("click").click(function() {
				$("#myModal").modal("show");
			});
			
			
			tool_refresh.click(function() {
				oTable.fnClearTable(false);
				PortletTable.getData();
			});
			
		},
		getData : function() {
			console.log("refresh!!");
			
			$.post(sel_url, {}, function(res) {
				data = eval(res);
				console.log("getDate: " + data);
				
				oTable.fnAddData(data);
			});
		}
	};

}();