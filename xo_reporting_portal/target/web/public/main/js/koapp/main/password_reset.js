/**
 * 
 */
define([ 'knockout' ,'jquery'], function(ko,$) {
	function PasswordReset() {

		if($('#authtokenvalue')) {
			$.ajaxSetup({headers: {'X-Xoanon-Auth':$('#authtokenvalue').attr('authtoken')}});
		}

		var self = this;
		self.reset = function() {

			// Create a formdata object and add the files
			var data = $("#passwordReset").serialize();
			$.ajax({
				'url':  xoappcontext + '/changepassword',
				'type':'POST',
				'cache': false,
				'data':data,
				'success' : function(data) {
					setGlobalMessage(data,"general");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage(data,"general");
				}
			});
		};
		
		self.shouldShowMessage = function() {
			self.setVisibility(true);
		};
		
		self.setVisibility = ko.observable("");
	}
	return PasswordReset;
});