/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function DiffusionManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		
		self.test = function() {
			alert("diffusion");
		}
		return {
			test:self.test
		};
	}
	
	DiffusionManagerModel.prototype = new BaseModel(ko, $);
	DiffusionManagerModel.prototype.constructor = DiffusionManagerModel;
	return DiffusionManagerModel;
});
