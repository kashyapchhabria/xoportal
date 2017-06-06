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
			$('#alert-box-container').css('visibility', 'visible');
			$('#alert-message').text(messageObj.message);
	
			clearTimeout(msgtId);
			msgtId = setTimeout(function(){
				$('#alert-box-container').css('visibility', 'hidden');
			}, 2000);			
		}
	} else {
		$('#popup-box-container').removeClass('success warning info alert');
		if(messageObj) {
			$('#popup-box-container').addClass(messageObj.messageType)
			$('#popup-box-container').css('visibility', 'visible');
			$('#popup-message').text(messageObj.message);

			clearTimeout(msgtId);
			msgtId = setTimeout(function(){
				$('#popup-box-container').css('visibility', 'hidden');
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
	$('#preloader').show(true);
	if(processor) {
		processValue = processor();
	}
	$('#preloader').fadeOut("slow");
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
		$('#preloader').show(true);
		if(dtTblObj) {
			dtTblObj.clear();
			dtTblObj.destroy();
		}
		if(builderMethod) {
			builderMethod(tblData);
		}
		if(componentId) {
			if(!dtOptions) {
				dtOptions = {'responsive' : false};	
			}
			
			dtTblObj = drawtable(componentId,dtOptions);
		}
		$('#preloader').fadeOut("slow");
		return dtTblObj;
	};
}
function drawtable(componentId,dtOptions){
	dtOptions.pagingType = "semantic";
	//---------------------------Pagination--------------------//
	$.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
	{
	    return {
	        "iStart":         oSettings._iDisplayStart,
	        "iEnd":           oSettings.fnDisplayEnd(),
	        "iLength":        oSettings._iDisplayLength,
	        "iTotal":         oSettings.fnRecordsTotal(),
	        "iFilteredTotal": oSettings.fnRecordsDisplay(),
	        "iPage":          oSettings._iDisplayLength === -1 ?
	            0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
	        "iTotalPages":    oSettings._iDisplayLength === -1 ?
	            0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	    };
	}
	 
	/* Semantic style pagination control */
	$.extend( $.fn.dataTableExt.oPagination, {
	    "semantic": {
	        "fnInit": function( oSettings, nPaging, fnDraw ) {
	            var oLang = oSettings.oLanguage.oPaginate;
	            var fnClickHandler = function ( e ) {
	                e.preventDefault();
	                if ( oSettings.oApi._fnPageChange(oSettings, e.data.action) ) {
	                    fnDraw( oSettings );
	                }
	            };
	 
	            $(nPaging).addClass('pagination').append(
	                    '<div class="ui tiny prev disabled button"><a href="#">'+oLang.sPrevious+'</a></div>'+
	                    '<div class="ui tiny next disabled button"><a href="#">'+oLang.sNext+'</a></div>'
	            );
	            var els = $('div', nPaging);
	            $(els[0]).bind( 'click.DT', { action: "previous" }, fnClickHandler );
	            $(els[1]).bind( 'click.DT', { action: "next" }, fnClickHandler );
	        },
	 
	        "fnUpdate": function ( oSettings, fnDraw ) {
	            var iListLength = 5;
	            var oPaging = oSettings.oInstance.fnPagingInfo();
	            var an = oSettings.aanFeatures.p;
	            var i, j, sClass, iStart, iEnd, iHalf=Math.floor(iListLength/2);
	 
	            if ( oPaging.iTotalPages < iListLength) {
	                iStart = 1;
	                iEnd = oPaging.iTotalPages;
	            }
	            else if ( oPaging.iPage <= iHalf ) {
	                iStart = 1;
	                iEnd = iListLength;
	            } else if ( oPaging.iPage >= (oPaging.iTotalPages-iHalf) ) {
	                iStart = oPaging.iTotalPages - iListLength + 1;
	                iEnd = oPaging.iTotalPages;
	            } else {
	                iStart = oPaging.iPage - iHalf + 1;
	                iEnd = iStart + iListLength - 1;
	            }
	 
	            for ( i=0, iLen=an.length ; i<iLen ; i++ ) {
	                // Remove the middle elements
	                $('div:gt(0)', an[i]).filter(':not(:last)').remove();
	 
	                // Add the new list items and their event handlers
	                for ( j=iStart ; j<=iEnd ; j++ ) {
	                    sClass = (j==oPaging.iPage+1) ? 'class="ui tiny active button"' : 'class="ui tiny button"';
	                    $('<div '+sClass+'><a href="#">'+j+'</a></div>')
	                        .insertBefore( $('div:last', an[i])[0] )
	                        .bind('click', function (e) {
	                            e.preventDefault();
	                            oSettings._iDisplayStart = (parseInt($('a', this).text(),10)-1) * oPaging.iLength;
	                            fnDraw( oSettings );
	                        } );
	                }
	 
	                // Add / remove disabled classes from the static elements
	                if ( oPaging.iPage === 0 ) {
	                    $('div:first', an[i]).addClass('ui tiny disabled button');
	                } else {
	                    $('div:first', an[i]).removeClass('disabled');
	                }
	 
	                if ( oPaging.iPage === oPaging.iTotalPages-1 || oPaging.iTotalPages === 0 ) {
	                    $('div:last', an[i]).addClass('disabled');
	                } else {
	                    $('div:last', an[i]).removeClass('disabled');
	                }
	            }
	        }
	    }
	} );
	//--------------------------------End Pagination---------------------------------//
	return $("#" + componentId).DataTable(dtOptions);
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

function loadPopup(ModalId){
	$('#popup-box-container').css('visibility', 'hidden');
	if(($('#'+ModalId).parent().attr("class")=="ui grid page")&(!($(".ui.modals.page").find('#'+ModalId).length>0))){
		$('#'+ModalId).modal('setting', 'closable', false).modal('show');
	}else if(($('#'+ModalId).parent().attr("class")=="ui grid page")&(($(".ui.modals.page").find('#'+ModalId).length>0))){
		$(".ui.modals.page").find('#'+ModalId).remove();
		$('#'+ModalId).modal('setting', 'closable', false).modal('show');
	}else{
		$('#'+ModalId).modal('setting', 'closable', false).modal('show');
	}
}

function backPage(){
	window.history.back();
}

function showmenu(){
	document.getElementById("top_nav_bar").style.display = "flex";
	document.getElementById("show_nav_bar").style.display = "none";
	if(document.getElementById("tableauViewPlace")!=null){
		document.getElementById("tableauViewPlace").setAttribute("class","with-margin");
	}
	if(document.getElementById("diffusionViewPlace")!=null){
		document.getElementById("diffusionViewPlace").setAttribute("class","with-margin");
	}
}

function hidemenu(){
	document.getElementById("top_nav_bar").style.display = "none";
	document.getElementById("show_nav_bar").style.display = "inline-block";
	if(document.getElementById("tableauViewPlace")!=null){
		document.getElementById("tableauViewPlace").setAttribute("class","no-margin");
	}
	if(document.getElementById("diffusionViewPlace")!=null){
		document.getElementById("diffusionViewPlace").setAttribute("class","no-margin");
	}
}

function openCommentNav(){
	$('.ui.sidebar').sidebar({dimPage: false, transition: 'overlay'}).sidebar("show");
}

function closeCommentNav(){
	$('.ui.sidebar').sidebar("hide");
}