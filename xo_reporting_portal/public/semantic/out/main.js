
$('#side_scrollbar').css('height', $(window.top).height());
$('.custom_scrollbar2').tinyscrollbar();

$(window).resize(function(event) {
	$('#side_scrollbar').css('height', $(window.top).height());
	$('.custom_scrollbar2').tinyscrollbar();
});


$('.message .close').on('click', function(e) {
	e.preventDefault();
	$(this).closest('.message').fadeOut();
});

$(document).on('click', '#lunch_settings', function(event) {
	event.preventDefault();
	$('#settings_bar').sidebar({
		overlay: true
	}).sidebar('toggle');
});


$(document).on('click', '#lunch_sidebar', function(event) {
	event.preventDefault();
	$('#side').sidebar('toggle');

	if ($('.is_home').length){
		setTimeout(function(){
			morris_chart();
		}, 200);
	}
});

$(document).on('click', '.menus_page .menu .item', function(event) {
	event.preventDefault();

	$(this).parent().find('.item').removeClass('active');

	$(this).addClass('active');
});

$(document).on('mouseover', '#lunch_sidebar', function(event) {
	event.preventDefault();
	$(this).find('.reorder').transition('pulse');
});

if ($('.is_home').length){
	$('.new_alerts .column').find('.segment').transition('tada');
}

$(document).on('click',  function(event) {
	
	var _target = $(event.target);

	//console.log(event.target.className);

	if ( 
		! _target.parents('div#side').length 
		&& ! _target.hasClass('side') 
		&& ! _target.hasClass('reorder') 
		&& ! _target.hasClass('settings_bar') 
		) {
		$('.ui.sidebar').sidebar('hide');
		}

if ( _target.hasClass('lunch_sidebar')) {
	if ($('.is_home').length || $('.is_chars').length){
		setTimeout(function(){
			morris_chart();
		}, 200);
	}
};

});

// INITIALIZATION

function initialization () {

	$('.custom_scrollbar').tinyscrollbar();
	$('.custom_scrollbar2').tinyscrollbar();

	//ui popup
	$('.popup').popup();

	//rating js
	$('.ui.rating').rating();

	$('.ui.accordion').accordion();

	// autogrowing textarea
	autogrowing_textarea();
	
	//tabs
	$('.demo.menu .item').tab({history: false});

	//checkbox
	$('.ui.checkbox').checkbox();

	//form dropdowns
	$('.ui.selection.dropdown').dropdown();

	//radio buttons
	$('.ui.radio.checkbox').checkbox();

	$('.chart').easyPieChart({size: 80,barColor:'#DB0731'});

	$('.ui.form_validation').form(form_validation_options);

	//enable dropdown
	$('.dropdown').dropdown({
		debug: false,
		on: 'hover',
		transition: 'scale'
	});

	dropdowns();

	$('#gallery').least({'scrollToGallery':false});

	sparklines();

	//$('.image_dimmer .dimmer' ).dimmer({on : 'hover'});

	dimmers();

	$('#footable').footable();

	morris_chart();
}

$(document).ready(function() {

	initialization();


	var url = window.location.pathname
		filename = url.substring(url.lastIndexOf('/')+1);

		// SIDEBAR
		$('.sidebar_dropdown').on('click',function(e) {

			$('.menu_dropdown').each(function() {
				$(this).slideUp();
				$(this).parent().find('.triangle').addClass('left');	
			});

			var menu = $(this).find('.menu_dropdown'),
			icon = $(this).find('.triangle');

			if (menu.is(":visible") == false) {
				icon.removeClass('left').addClass('down');
				menu.css({
					visibility: "visible",
					display: "none"
				}).slideDown('slow', function(){
					$('.custom_scrollbar2').tinyscrollbar();
				});
			}
			else{
				icon.removeClass('down').addClass('left');
				menu.css({
					visibility: "visible",
					display: "block"
				}).slideUp('slow', function(){
					$('.custom_scrollbar2').tinyscrollbar();			
				});
			}
		});
		// SIDEBAR

	if (filename && filename != 'index.php'){
		var menu = $('#side'),
			link = $('a[href="'+ filename +'"]');

		$('#home').removeClass('active');

		if (link.parents('.sidebar_dropdown').length)
		{
			link.closest('.sidebar_dropdown').trigger('click');
			link.parent().addClass('active');
			return;
		}

		link.addClass('active');
	}

	$(document).on('click', '#side .secondary .item', function(e) {
		e.preventDefault();

		var self = $(this),
		link_href = self.attr('href');

		if ( ! link_href ){
			window.location.href = $(this).find('a').attr('href');
		}

	});
});

$(document).on('click', '.btn-setting', function(e) {
	e.preventDefault();

	$('.modal_settings')
	.modal('setting', 'transition', 'vertical flip')
	.modal('setting', 'debug', 'false')
	.modal('toggle');
});

$(document).on('click', '.btn-close', function(e) {
	e.preventDefault();

	var self = $(this);

	setTimeout(function() {
		self.parents('.box').fadeOut();
	}, 500);
	
});

$(document).on('click', '.btn-minimize', function(e) {
	e.preventDefault();
	var elm = $(this).parents('.box').find('.box-content');

	if (elm.is(':visible')){
		elm.fadeOut({easing : 'easeInCirc'});
	}
	else{
		elm.fadeIn({easing : 'easeInCirc'});
	}

});

$(document).on('click', '.reload', function(e) {
	e.preventDefault();

	var loader = $(this).parents('.box').find('.dimmer');

	//console.log(loader);

	loader.addClass('active');

	setTimeout(function() {
		loader.removeClass('active');
	}, 2000);
});

$(document).on('click', '.submit', function(event) {
	event.preventDefault();
	/* Act on the event */
	var self = $(this);
	self.addClass('loading');

	setTimeout(function() {
		self.removeClass('loading');
	}, 1000);
});

// Add comemnts list
$(document).on('click', '#add_comment', function(event) {
	event.preventDefault();
	/* Act on the event */
	var self = $(this),
	comment_obj = self.parent().find('input'),
	comment = comment_obj.val(),
	scroll = $('.comments .custom_scrollbar').tinyscrollbar(),
	comments_panel = self.parents('.comments'),
	html_element = '<div class="comment"><a class="avatar"><img src="img/avatar.jpg"></a><div class="content"><a class="author">You</a><div class="metadata"><div class="date">today</div></div><div class="text">'+comment+'</div></div></div>';

	if ( comment == ''){
		comment_obj.parents('.field').addClass('error');
		return false;
	}
	else{
		comment_obj.parents('.field').removeClass('error');
	}

	comments_panel.find('.overview').append(html_element);

	var first_overview = comments_panel.find('.overview:first'),
	view_port = comments_panel.find('.viewport:first'),
	thumb = comments_panel.find('.thumb:first') ,
	track = comments_panel.find('.track:first') ;

	first_overview.css({top: ((first_overview.height() - view_port.height()) * (-1)) });
	thumb.css({top: track.height() - thumb.height()});
	comment_obj.val('');
});

$(document).on('click', '.tasks_list .checkbox', function(event) {
	event.preventDefault();

	var elm = $(this).parents('.item').find('.ribbon');

	if (elm.hasClass('awesome_red')){
		elm.addClass('awesome_green').removeClass('awesome_red').text('Done');
	}else{
		elm.addClass('awesome_red').removeClass('awesome_green').text('Not Done');
	}

});

$(window).resize(function(event) {
	morris_chart();
});

// SETTINGS js

$(document).on('click', '#change_wide', function(event) {
	event.preventDefault();
	
	var _value = $(this).val(),
	_div = $("#general_container");

	if (_value !== ''){

		if (_value == 'fluid'){
			_div.removeClass('page').addClass('one column');
		}
		
		if(_value == 'boxed'){
			_div.removeClass('one column').addClass('page');
		}

		morris_chart();
	}

});

$(document).on('change', '#navbar_fixed', function(event) {
	event.preventDefault();
	/* Act on the event */
	var _elm = $(".navbar-inner ");

	if($(this).is(':checked'))
		_elm.css('position', 'fixed');
	else
		_elm.css('position', 'absolute');

	$('#settings_bar').sidebar({
		overlay: true
	}).sidebar('toggle');

});

