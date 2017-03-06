/**
 * 
 */

var xoappcontext;
var xoappusername;
function setAppConext(appCxt) {
	xoappcontext = appCxt;
	window.onunload = window.location.pathname != "/" ? confirmOnPageExitOrRefresh : null;
	//window.onbeforeunload = window.location.pathname != "/" ? confirmOnPageExitOrRefresh : null;

	if($('#xologout') != null) {
		$('#xologout').on('click', function(e){
			//window.onbeforeunload = null;
			window.onunload = null;
		});
	}
}

function setAppUser(appUser){
	xoappusername=appUser;
}

String.prototype.supplant = function(o) {
	return this.replace(/\{([^{}]*)\}/g, function(a, b) {
		var r = o[b];
		return typeof r === 'string' || typeof r === 'number' ? r : a;
	});
};

if (typeof String.prototype.trim !== 'function') {
	String.prototype.trim = function() {
		return this.replace(/^\s*(\S*(?:\s+\S+)*)\s*$/, "$1");
	};	
}

String.prototype.capitalize = function() {
    return this.toLowerCase().charAt(0).toUpperCase() + this.toLowerCase().slice(1);
};

if (typeof String.prototype.endsWith !== 'function') {
    String.prototype.endsWith = function(suffix) {
        return this.indexOf(suffix, this.length - suffix.length) !== -1;
    };
}

Array.prototype.pushAll = function(arrayelements) {
	if (arrayelements && arrayelements.length && arrayelements.length > 0) {
		var arrayIndex = 0;
		var totalElements = arrayelements.length;
		for (; arrayIndex < totalElements; arrayIndex++) {
			this.push(arrayelements[arrayIndex]);
		}
	}
};

/**
 * Returns the value of the location hash.
 * 
 * @return {string} Hash value with '#' prefix discarded.
 */
function getLocationHash() {
	return window.location.hash.substring(1);
}

/**
 * Updates the location hash with the specified string.
 * @param {string} str
 */
function setLocationHash(str) {
	window.location.hash = (str ? str : "");
	window.location.hash = (str ? str : "home");
}

function confirmOnPageExitOrRefresh(e) 
{
    // If we haven't been passed the event get the window.event
    e = e || window.event;

    var y = e.pageY || e.clientY;
    
    if(y < 0)  {	// Window closed
    	//alert("Window closed");
    	window.location.href = xoappcontext + "/logout?authToken=" + $('#authtokenvalue').attr('authtoken');
    }
    else {
    	//alert("Window refreshed");	// Window refreshed
    	if(window.location.href.indexOf("pagedispatcher") > 0) {    		
    		window.location.href = xoappcontext + "/pagedispatcher?authToken=" + $('#authtokenvalue').attr('authtoken') + '&currentPage=' + getLocationHash();
    	}
    }

    //var message = null;//'Any text will block the navigation and display a prompt';

    // For IE6-8 and Firefox prior to version 4
    /*if (e)
    {
        e.returnValue = message;
    }*/

    // For Chrome, Safari, IE8+ and Opera 12+
    //return message;
}

$(document).ready(function() {

	window.onunload = window.location.pathname != "/" ? confirmOnPageExitOrRefresh : null;
	//window.onbeforeunload = window.location.pathname != "/" ? confirmOnPageExitOrRefresh : null;

	if($('#xologout') != null) {
		$('#xologout').on('click', function(e){
			//window.onbeforeunload = null;
			window.onunload = null;
		});
	}
});

var msgtId;
function setGlobalMessage(messageObj,ui) {
	if(ui=="general") {
		$('#alert-box-container').removeClass('success warning info alert');
		if(messageObj) {
			$('#alert-box-container').addClass(messageObj.messageType)
			$('#alert-box-container').show();
			$('#alert-message').text(messageObj.message);
	
			clearTimeout(msgtId);
			msgtId = setTimeout(function(){
				$("#alert-box-container").hide().slideDown();
				$("#alert-box-container").hide();			
			}, 2000);			
		}
	} else {
		$('#popup-box-container').removeClass('success warning info alert');
		if(messageObj) {
			$('#popup-box-container').addClass(messageObj.messageType)
			$('#popup-box-container').show();
			$('#popup-message').text(messageObj.message);

			clearTimeout(msgtId);
			msgtId = setTimeout(function(){
				$("#popup-box-container").hide().slideDown();
				$("#popup-box-container").hide();
			}, 2000);		
		}
	}
	return messageObj;
}

function refreshFoundation() {
	if($(document).foundation) {						
		$(document).foundation();
		$(document).foundation('dropdown', 'reflow');
	}
}

function phoneNumberFormat(ph_number) {
	if(ph_number) {
		var formatted = ph_number.substring(0,3) + " " + ph_number.substring(3, 6) + " " + ph_number.substring(6, 9) + " " + ph_number.substring(9);
	}
	else {
		var formatted = ph_number;
	}
	return formatted;
}

function showPageLoader(processor) {
	var processValue = null;
	// Animate loader off screen
	$(".se-pre-con").show(true);
	if(processor) {
		processValue = processor();
	}
	$(".se-pre-con").fadeOut("slow");
	return processValue;
}

function appendUrlParam(eurl, key, value) {
	if(eurl) {
		if(eurl.indexOf('?') > 0) {
			eurl = eurl + '&';
		} else {
			eurl = eurl + '?';
		}
		eurl = eurl + key + '=' + value;
		return eurl;
	}
}

function BaseModel(ko, jq) {

	var self = this;

	if(ko) {
		ko.observableArray.fn.find = function(prop, data) {
			var valueToMatch = data[prop]; 
			return ko.utils.arrayFirst(this(), function(item) {
				return item[prop] === valueToMatch ? item : null; 
			});
		};
	}

	self.currentPage = ko.observable('');
	self.visibility = ko.observable(false);
	if($('#authtokenvalue')) {
		$.ajaxSetup({headers: {'X-Xoanon-Auth':$('#authtokenvalue').attr('authtoken')}});
	}

	self.buildDropDown = function(builderMethod, dropDownbuilderArgs) {
		if(dropDownbuilderArgs && dropDownbuilderArgs.data && dropDownbuilderArgs.dataHolder) {
			dropDownbuilderArgs.dataHolder.removeAll();
		}
		if(builderMethod) {
			builderMethod(dropDownbuilderArgs);
		}
	};

	self.dropDownBuilder = function(dropDownbuilderArgs) {
		if(dropDownbuilderArgs && dropDownbuilderArgs.data && dropDownbuilderArgs.dataHolder) {
			var dataHolder = dropDownbuilderArgs.dataHolder;
			var data = dropDownbuilderArgs.data;
			var dataIndex = 0;
			var totalData = data.length;
			for(;dataIndex < totalData; dataIndex++) {
				var entityDto = {
									'displayText' : data[dataIndex].value,
									'entityId' : data[dataIndex].key
								};
				dataHolder.push(entityDto);
			}
		}
	};
	
	self.buildDataTableWithData = function(componentId, builderMethod, tblData, dtOptions, dtTblObj) {
		if(dtTblObj) {
			dtTblObj.clear();
			dtTblObj.destroy();
		}
		if(builderMethod) {
			builderMethod(tblData);
		}
		if(componentId) {
			if(!dtOptions) {
				dtOptions = {'responsive' : true};	
			}
			dtTblObj = jq("#" + componentId).DataTable(dtOptions);
		}
		return dtTblObj;
	};
}

/**
 * Displaying an element in full screen mode.
 * @param elementId
 */
function showInFullScreen(elementId) {
	// in full-screen?
	exitFullScreen();
	 // full-screen available?
	if (
		    document.fullscreenEnabled || 
		    document.webkitFullscreenEnabled || 
		    document.mozFullScreenEnabled ||
		    document.msFullscreenEnabled
		) {

		var fullScreenElement = document.getElementById(elementId);

			// go full-screen
			if (fullScreenElement.requestFullscreen) {
				fullScreenElement.requestFullscreen();
			} else if (fullScreenElement.webkitRequestFullscreen) {
				fullScreenElement.webkitRequestFullscreen();
			} else if (fullScreenElement.mozRequestFullScreen) {
				fullScreenElement.mozRequestFullScreen();
			} else if (fullScreenElement.msRequestFullscreen) {
				fullScreenElement.msRequestFullscreen();
			}
	
	}
}

function exitFullScreen() {
	// in full-screen?
	if (
		document.fullscreenElement ||
		document.webkitFullscreenElement ||
		document.mozFullScreenElement ||
		document.msFullscreenElement
	) {

		// exit full-screen
		if (document.exitFullscreen) {
			document.exitFullscreen();
		} else if (document.webkitExitFullscreen) {
			document.webkitExitFullscreen();
		} else if (document.mozCancelFullScreen) {
			document.mozCancelFullScreen();
		} else if (document.msExitFullscreen) {
			document.msExitFullscreen();
		}

	}
}

function OpenInNewTab(url) {
	var win = window.open(url, '_blank');
	win.focus();
}