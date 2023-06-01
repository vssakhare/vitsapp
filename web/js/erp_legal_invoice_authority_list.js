


jQuery(document).ready(function() {

	var inputinvoicestable =	jQuery('#tableinputinvoices').DataTable(
							{
							"order": [[ 0, "asc" ]],
							
							language: { search: "",searchPlaceholder: "Search..." }
							}
	);


	jQuery('#txtSearchStatus').on('change', function() {
		if (jQuery("#txtSearchStatus").val()==="ALL" || jQuery("#txtSearchStatus").val()==="-SELECT STATUS-")
			{
				jQuery.fn.dataTable.ext.search.pop();
				inputinvoicestable.draw();
			}
		else
			{
				jQuery.fn.dataTable.ext.search.pop();
				jQuery.fn.dataTable.ext.search.push(
				  function(settings, data, dataIndex) {
					  return jQuery(table.row(dataIndex).node()).attr('data-type') === jQuery("#txtSearchStatus").val();
					}
				);
				inputinvoicestable.draw();
			}
	});
        jQuery('#txtPendingSearchStatus').on('change', function() {
		if (jQuery("#txtPendingSearchStatus").val()==="ALL" || jQuery("#txtPendingSearchStatus").val()==="-SELECT STATUS-" )
			{
				jQuery.fn.dataTable.ext.search.pop();
				pendinginvoicestable.draw();
			}
		else
			{
				jQuery.fn.dataTable.ext.search.pop();
				jQuery.fn.dataTable.ext.search.push(
				  function(settings, data, dataIndex) {
					  return jQuery(table.row(dataIndex).node()).attr('data-type') === jQuery("#txtPendingSearchStatus").val();
					}
				);
				pendinginvoicestable.draw();
			}
	});
});



