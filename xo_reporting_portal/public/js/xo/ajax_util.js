/**
 * Xoanon ajax utils.
 */

//$.ajaxSetup({headers: {'X-Xoanon-Auth':'authtoken'}});

xo_ajax = function(ajax_request_obj) {

	if (ajax_request_obj.url) {
		$.ajax({
			'url' : ajax_request_obj.url,
			'type' : ajax_request_obj.httpmethod ? ajax_request_obj.httpmethod
					: 'POST',
			'data' : JSON.stringify(ajax_request_obj.data),
			'contentType' : 'application/json',
			'cache' : false,
			'dataType' : 'json',
			'success' : function(result) {
				try {
					ajax_request_obj.success(result);
				} catch (e) {
				}
			},
			'failure' : function(result) {
				try {
					ajax_request_obj.failure(result);
				} catch (e) {
				}
			},
			'beforeSend' : function() {
				
			}

		});
	}

};