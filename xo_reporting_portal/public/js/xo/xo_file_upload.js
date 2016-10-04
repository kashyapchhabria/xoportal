/**
 * 
 */
var AJAX_UPLOADER = {};

AJAX_UPLOADER.files = null;
AJAX_UPLOADER.serverUrl = "";

AJAX_UPLOADER.prepare_upload_data = function(event) {
	AJAX_UPLOADER.files = event.target.files;
};

AJAX_UPLOADER.bindFileUploadEvent = function() {
	// Add events
	$('input[type=file]').on('change', this.prepare_upload_data);
	$('form').on('submit', this.xo_ajax_file_upload);
};

AJAX_UPLOADER.xo_ajax_file_upload = function(event) {

	event.stopPropagation(); // Stop stuff happening
	event.preventDefault(); // Totally stop stuff happening

	// need to process spinner

	// Create a formdata object and add the files
	var data = new FormData();
	$.each(AJAX_UPLOADER.files, function(key, value) {
		data.append(key, value);
	});
	$.ajax({
		'url':AJAX_UPLOADER.serverUrl,
		'type':'POST',
		'data':data,
		'cache':false,
		'dataType':'json',
		'processData':false, // Don't process the files
		'contentType': false,
		'success' : function(data) {
			if (typeof data.error === 'undefined') {
				// Success so call function to process the form
				//console.log(data);
			} else {
				// Handle errors here
				//console.log('ERRORS: ' + data.error);
			}
		},
		'error' : function(jqXHR, textStatus, errorThrown) {
			// Handle errors here
			//console.log('ERRORS: ' + textStatus);
			// STOP LOADING SPINNER
		}
	});
};