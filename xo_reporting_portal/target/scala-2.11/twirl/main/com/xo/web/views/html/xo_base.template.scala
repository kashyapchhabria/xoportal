
package com.xo.web.views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._

/**/
object xo_base extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(content:Html):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.16*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html5>
<html lang="en">
<head>
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title data-bind="text: router.currentPage().title">"""),_display_(/*7.54*/Messages("xo.title")),format.raw/*7.74*/("""</title>
<link rel="shortcut icon" href='"""),_display_(/*8.34*/routes/*8.40*/.Assets.versioned("images/favicon.png")),format.raw/*8.79*/("""'>
<link rel="stylesheet" href='"""),_display_(/*9.31*/routes/*9.37*/.Assets.versioned("lib/foundation/css/foundation.css")),format.raw/*9.91*/("""'>
<link rel="stylesheet" href='"""),_display_(/*10.31*/routes/*10.37*/.Assets.versioned("stylesheets/datatable/css/data-table.css")),format.raw/*10.98*/("""'>
<link rel="stylesheet" href='"""),_display_(/*11.31*/routes/*11.37*/.Assets.versioned("stylesheets/datatable/css/dataTables.responsive.css")),format.raw/*11.109*/("""'>
<link rel="stylesheet" href='"""),_display_(/*12.31*/routes/*12.37*/.Assets.versioned("images/foundation-icons/foundation-icons/foundation-icons.css")),format.raw/*12.119*/("""'>
<link rel="stylesheet" href='"""),_display_(/*13.31*/routes/*13.37*/.Assets.versioned("stylesheets/font.css")),format.raw/*13.78*/("""'>
<link rel="stylesheet" href='"""),_display_(/*14.31*/routes/*14.37*/.Assets.versioned("stylesheets/basic.css")),format.raw/*14.79*/("""'>
<link href='"""),_display_(/*15.14*/routes/*15.20*/.Assets.versioned("stylesheets/freshwidget.css")),format.raw/*15.68*/("""' type="text/css" rel="stylesheet"/>
<link href='"""),_display_(/*16.14*/routes/*16.20*/.Assets.versioned("stylesheets/selectize.default.css")),format.raw/*16.74*/("""' type="text/css" rel="stylesheet"/>
<script src='"""),_display_(/*17.15*/routes/*17.21*/.Assets.versioned("lib/jquery/jquery.min.js")),format.raw/*17.66*/("""'></script>
<script src='"""),_display_(/*18.15*/routes/*18.21*/.Assets.versioned("lib/modernizr/modernizr.js")),format.raw/*18.68*/("""'></script>
<script src='"""),_display_(/*19.15*/routes/*19.21*/.Assets.versioned("js/xo/common_util.js")),format.raw/*19.62*/("""'></script>
<script type="application/javascript">
    setAppConext('"""),_display_(/*21.20*/com/*21.23*/.xo.web.util.XoUtil.getApplicationContext()),format.raw/*21.66*/("""');
    setAppUser('"""),_display_(/*22.18*/session()/*22.27*/.get("email")),format.raw/*22.40*/("""');
</script>

"""),_display_(/*25.2*/messages()),format.raw/*25.12*/("""
"""),format.raw/*26.1*/("""</head>
<body id="xoportalbody" style="background-color:white !important;">
"""),_display_(/*28.2*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*28.76*/ {_display_(Seq[Any](format.raw/*28.78*/("""
"""),format.raw/*29.1*/("""<div data-bind='ifnot: isTopBarVisibile,attr:"""),format.raw/*29.46*/("""{"""),format.raw/*29.47*/("""class:"sub-nav fixmenu""""),format.raw/*29.70*/("""}"""),format.raw/*29.71*/("""'>
	<a id="showorhidetopbar" data-bind="click: showReportMenus">&nbsp;<i class="fi-align-justify">&nbsp;</i>&nbsp;</a>
</div>
<div data-bind='if: isTopBarVisibile'>
<div class="sticky fixed" data-options="sticky_on:[small,large]">
<nav class="top-bar xoheader" role="navigation" data-topbar="">
		<section class="top-bar-section" data-bind="if : menuData && menuData().length > 0">
			<ul class="left" data-bind="template: """),format.raw/*36.42*/("""{"""),format.raw/*36.43*/(""" """),format.raw/*36.44*/("""name: 'menuTree', foreach: menuData"""),format.raw/*36.79*/("""}"""),format.raw/*36.80*/("""" ></ul>
		</section>
		<ul class="title-area">
			  
			 """),_display_(/*40.6*/if(session().get("roleName") != null)/*40.43*/ {_display_(Seq[Any](format.raw/*40.45*/("""
			 """),format.raw/*41.5*/("""<li class="toggle-topbar outlink">
			 	<a class="outlink" href=""""),_display_(/*42.32*/com/*42.35*/.xo.web.controllers.routes.UserController.logout(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN))),format.raw/*42.147*/("""">			 		
			 		<i style="padding:15;font-size:25px" class="outlink fi-widget size-48"></i><span></span>
			 	</a>
			 </li> """)))}),format.raw/*45.12*/("""
		"""),format.raw/*46.3*/("""</ul>
			 <section class="top-bar-section">
			 	"""),_display_(/*48.7*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*48.81*/ {_display_(Seq[Any](format.raw/*48.83*/("""
			 	"""),format.raw/*49.6*/("""<ul class="right">
			 		<li class="name">
			 			<a id="home_button" class="outlink xoheader" style="font-family:ralewaymedium;" href="#"><i class="fi-home">&nbsp;</i></a>
			 		</li>
			 		<li data-bind='if: isTopBarVisibile()'>
						<a data-bind='click: showReportMenus'><i class="fi-arrows-in">&nbsp;&nbsp;"""),_display_(/*54.82*/Messages("tableau.report.hidereportmenus")),format.raw/*54.124*/("""</i></a>
					</li>
					<li data-bind='if: isFullScreenAvailable'>
						<a data-bind='ifnot: isFullScreenEnabled, click: fullScreenView'><i class="fi-arrows-out">&nbsp;&nbsp;"""),_display_(/*57.110*/Messages("tableau.report.fullscreen")),format.raw/*57.147*/("""</i></a>
						<a data-bind='if: isFullScreenEnabled, click: closeFullScreen'><i class="fi-arrows-in">&nbsp;&nbsp;"""),_display_(/*58.107*/Messages("tableau.report.exitfullscreen")),format.raw/*58.148*/("""</i></a>
					</li>
			 		"""),_display_(/*60.8*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_X_SUPER_CLIENT) != null)/*60.86*/ {_display_(Seq[Any](format.raw/*60.88*/("""
			 	    """),format.raw/*61.10*/("""<li>
			 	    	<select style="margin:0 0 0;margin-top: 4px;" title='"""),_display_(/*62.65*/Messages("user.selclient")),format.raw/*62.91*/("""' id="selSupSysClient" 
				   			data-bind='options:allClients, optionsText: "name", value: selectedSupClient, optionsCaption:""""),_display_(/*63.106*/Messages("user.selclient")),format.raw/*63.132*/(""""'>
				   		</select>
			 	    </li>
			 	    """)))}),format.raw/*66.11*/("""
					
			 		"""),format.raw/*68.7*/("""<li class="outlink has-dropdown not-click" data-equalizer-watch>
			 			<a href="#" style="padding:15"><i class="outlink fi-widget size-48"></i></a>
			 			<ul class="dropdown">
			 				<li><a href="http://xoanon.helpdocsonline.com/o73n03uauu38vjg8l6rqrb7z0unyk6l9" target="_blank">User Guide</a></li>
			 				<li><a id="changepassword" class="outlink" href="#changepassword">"""),_display_(/*72.76*/Messages("base.changepassword")),format.raw/*72.107*/("""</a></li>
			 				<li><a onclick="composeSupport()">Support</a></li>
 			 				<li><a class="outlink" id="xologout" href=""""),_display_(/*74.54*/com/*74.57*/.xo.web.controllers.routes.UserController.logout(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN))),format.raw/*74.169*/("""">"""),_display_(/*74.172*/Messages("base.logout")),format.raw/*74.195*/("""</a></li>
			 			</ul>
			 		</li>
			 	</ul>
				 """)))}),format.raw/*78.7*/("""
			 """),format.raw/*79.5*/("""</section>
</nav>
</div>
</div>
""")))}),format.raw/*83.2*/("""
	"""),format.raw/*84.2*/("""<div id='xohtmlcontent' class="small-12 small-centered columns xo-content">
		"""),_display_(/*85.4*/content),format.raw/*85.11*/("""
	"""),format.raw/*86.2*/("""</div>

	<div class="footer" style="background-color: white;">
		<div class="row" style="background-color: white;">
			<div class="small-8 small-centered  columns" style="background-color: white;">
				<p style="font-size:0.7rem"class="copyright text-center">© Xoanon Analytics. All rights reserved.</p>
			</div>
		</div>
	</div>
	"""),_display_(/*95.3*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*95.77*/ {_display_(Seq[Any](format.raw/*95.79*/("""
		"""),format.raw/*96.3*/("""<div id="authtokenvalue" authtoken=""""),_display_(/*96.40*/session()/*96.49*/.get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN)),format.raw/*96.102*/("""">
		</div>
	""")))}),format.raw/*98.3*/("""
	"""),_display_(/*99.3*/if(session().get(com.xo.web.util.XoAppConstant.CURRENT_PAGE) != null)/*99.72*/ {_display_(Seq[Any](format.raw/*99.74*/("""
		"""),format.raw/*100.3*/("""<div id="curpagehash" pagehash=""""),_display_(/*100.36*/session()/*100.45*/.get(com.xo.web.util.XoAppConstant.CURRENT_PAGE)),format.raw/*100.93*/("""">
		</div>
	""")))}),format.raw/*102.3*/("""
"""),format.raw/*103.1*/("""<script src='"""),_display_(/*103.15*/routes/*103.21*/.Assets.versioned("lib/fastclick/fastclick.js")),format.raw/*103.68*/("""'></script>
<script src='"""),_display_(/*104.15*/routes/*104.21*/.Assets.versioned("lib/foundation/js/foundation.min.js")),format.raw/*104.77*/("""'></script>
<script src='"""),_display_(/*105.15*/routes/*105.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.dropdown.js")),format.raw/*105.93*/("""'></script>
<script src='"""),_display_(/*106.15*/routes/*106.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.topbar.js")),format.raw/*106.91*/("""'></script>
<script src='"""),_display_(/*107.15*/routes/*107.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.magellan.js")),format.raw/*107.93*/("""'></script>
<script src='"""),_display_(/*108.15*/routes/*108.21*/.Assets.versioned("js/tableau/tableau-2.min.js")),format.raw/*108.69*/("""'></script>

<div id='supportWidget' class="row">
</div>
</body>
<!-- Call the main.js -->
	
	"""),_display_(/*115.3*/if(session().get("email") != null)/*115.37*/ {_display_(Seq[Any](format.raw/*115.39*/("""
		"""),format.raw/*116.3*/("""<script type="text/javascript" src='"""),_display_(/*116.40*/routes/*116.46*/.Assets.versioned("js/freshdesk/freshwidget.js")),format.raw/*116.94*/("""'></script>
 		<script type="text/javascript">
		function composeSupport()"""),format.raw/*118.28*/("""{"""),format.raw/*118.29*/("""
			"""),format.raw/*119.4*/("""var page_name = getLocationHash();
 			if(page_name == '') """),format.raw/*120.25*/("""{"""),format.raw/*120.26*/("""
 				"""),format.raw/*121.6*/("""page_name = 'home';
 			"""),format.raw/*122.5*/("""}"""),format.raw/*122.6*/("""
			"""),format.raw/*123.4*/("""var html = "<div id='supportModal' class='reveal-modal support-modal' data-reveal='' data-options='close_on_background_click:false;close_on_esc:false;'>";
			html += "<iframe id= 'freshIframe'title='Feedback Form' class='freshwidget-embedded-form' id='freshwidget-embedded-form' src='https://xoanonanalytics.freshdesk.com/widgets/feedback_widget/new?&widgetType=embedded&screenshot=no&helpdesk_ticket[requester]="""),_display_(/*124.259*/session()/*124.268*/.get("email")),format.raw/*124.281*/("""&disable[custom_field][url_320535]=true&helpdesk_ticket[custom_field][url_320535]=XoPortal "+page_name+"' scrolling='no' height='500px' width='100%' frameborder='0' >";
			html += "</iframe>";
			html += "<a class='close-reveal-modal topRight' id='close_modal_deact'>×</a></div>";
			document.getElementById("supportWidget").innerHTML = html;
			$('#supportModal').foundation('reveal', 'open');
		"""),format.raw/*129.3*/("""}"""),format.raw/*129.4*/("""
		"""),format.raw/*130.3*/("""</script>
	""")))}),format.raw/*131.3*/("""
	
	"""),_display_(/*133.3*/if(session().get("roleName") != null && (com.xo.web.models.system.RoleEnum.Admin.name().equalsIgnoreCase(session().get("roleName"))))/*133.136*/{_display_(Seq[Any](format.raw/*133.137*/("""

		"""),format.raw/*135.3*/("""<script data-main=""""),_display_(/*135.23*/routes/*135.29*/.Assets.versioned("js/koapp/admin_main.js")),format.raw/*135.72*/("""" src=""""),_display_(/*135.80*/routes/*135.86*/.WebJarAssets.at(WebJarAssets.locate("require.js"))),format.raw/*135.137*/(""""></script>
  	""")))}),format.raw/*136.5*/(""" """),_display_(/*136.7*/if(session().get("roleName") != null && (com.xo.web.models.system.RoleEnum.Viewer.name().equalsIgnoreCase(session().get("roleName"))))/*136.141*/{_display_(Seq[Any](format.raw/*136.142*/("""
  		"""),format.raw/*137.5*/("""<script data-main=""""),_display_(/*137.25*/routes/*137.31*/.Assets.versioned("js/koapp/viewer_main.js")),format.raw/*137.75*/("""" src=""""),_display_(/*137.83*/routes/*137.89*/.WebJarAssets.at(WebJarAssets.locate("require.js"))),format.raw/*137.140*/(""""></script>
  	""")))}),format.raw/*138.5*/("""
"""),format.raw/*139.1*/("""<script type="text/javascript">

$(document).foundation();
$(document).foundation('reflow');

//Wait for window load
$(window).load(function() """),format.raw/*145.27*/("""{"""),format.raw/*145.28*/("""
"""),format.raw/*146.1*/("""// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");
"""),format.raw/*148.1*/("""}"""),format.raw/*148.2*/(""");

	$( document ).ready(function()"""),format.raw/*150.32*/("""{"""),format.raw/*150.33*/("""
		
		"""),format.raw/*152.3*/("""$(document).bind('click', function(e) """),format.raw/*152.41*/("""{"""),format.raw/*152.42*/("""
  		"""),format.raw/*153.5*/("""var $clicked = $(e.target);
 			if (!$clicked.parents().hasClass("dropdown")) 
 				$(".dropdown dd ul").hide();
		"""),format.raw/*156.3*/("""}"""),format.raw/*156.4*/(""");
		
		$(".dropdown dd ul li a").on('click', function() """),format.raw/*158.52*/("""{"""),format.raw/*158.53*/("""
  			"""),format.raw/*159.6*/("""$(".dropdown dd ul").hide();
		"""),format.raw/*160.3*/("""}"""),format.raw/*160.4*/(""");
		
		if($('#authtokenvalue')) """),format.raw/*162.28*/("""{"""),format.raw/*162.29*/("""
			"""),format.raw/*163.4*/("""$.ajaxSetup("""),format.raw/*163.16*/("""{"""),format.raw/*163.17*/("""headers: """),format.raw/*163.26*/("""{"""),format.raw/*163.27*/("""'X-Xoanon-Auth':$('#authtokenvalue').attr('authtoken')"""),format.raw/*163.81*/("""}"""),format.raw/*163.82*/("""}"""),format.raw/*163.83*/(""");
		"""),format.raw/*164.3*/("""}"""),format.raw/*164.4*/("""
		"""),format.raw/*165.3*/("""if($('#alert-box-container')) """),format.raw/*165.33*/("""{"""),format.raw/*165.34*/("""
			"""),format.raw/*166.4*/("""$('#alert-box-container').hide();
		"""),format.raw/*167.3*/("""}"""),format.raw/*167.4*/("""
		"""),format.raw/*168.3*/("""if($('#curpagehash') && $('#curpagehash').attr('pagehash')) """),format.raw/*168.63*/("""{"""),format.raw/*168.64*/("""
			"""),format.raw/*169.4*/("""//window.location.href = window.location.href + "#" + $('#curpagehash').attr('pagehash');
			setLocationHash($('#curpagehash').attr('pagehash'));
		"""),format.raw/*171.3*/("""}"""),format.raw/*171.4*/("""
"""),format.raw/*172.1*/("""}"""),format.raw/*172.2*/(""");

</script>
<div class="se-pre-con"></div>
</html>
"""))}
  }

  def render(content:Html): play.twirl.api.HtmlFormat.Appendable = apply(content)

  def f:((Html) => play.twirl.api.HtmlFormat.Appendable) = (content) => apply(content)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/xo_base.scala.html
                  HASH: 69377a5851c0f5c3d3c2fcde6283ef42824c3f33
                  MATRIX: 734->1|836->15|863->16|1078->205|1118->225|1186->267|1200->273|1259->312|1318->345|1332->351|1406->405|1466->438|1481->444|1563->505|1623->538|1638->544|1732->616|1792->649|1807->655|1911->737|1971->770|1986->776|2048->817|2108->850|2123->856|2186->898|2229->914|2244->920|2313->968|2390->1018|2405->1024|2480->1078|2558->1129|2573->1135|2639->1180|2692->1206|2707->1212|2775->1259|2828->1285|2843->1291|2905->1332|3002->1402|3014->1405|3078->1448|3126->1469|3144->1478|3178->1491|3220->1507|3251->1517|3279->1518|3382->1595|3465->1669|3505->1671|3533->1672|3606->1717|3635->1718|3686->1741|3715->1742|4166->2165|4195->2166|4224->2167|4287->2202|4316->2203|4401->2262|4447->2299|4487->2301|4519->2306|4612->2372|4624->2375|4758->2487|4914->2612|4944->2615|5020->2665|5103->2739|5143->2741|5176->2747|5515->3059|5579->3101|5784->3278|5843->3315|5986->3430|6049->3471|6102->3498|6189->3576|6229->3578|6267->3588|6363->3657|6410->3683|6567->3812|6615->3838|6694->3886|6734->3899|7138->4276|7191->4307|7340->4429|7352->4432|7486->4544|7517->4547|7562->4570|7644->4622|7676->4627|7739->4660|7768->4662|7873->4741|7901->4748|7930->4750|8289->5083|8372->5157|8412->5159|8442->5162|8506->5199|8524->5208|8599->5261|8643->5275|8672->5278|8750->5347|8790->5349|8821->5352|8882->5385|8901->5394|8971->5442|9016->5456|9045->5457|9087->5471|9103->5477|9172->5524|9226->5550|9242->5556|9320->5612|9374->5638|9390->5644|9484->5716|9538->5742|9554->5748|9646->5818|9700->5844|9716->5850|9810->5922|9864->5948|9880->5954|9950->6002|10072->6097|10116->6131|10157->6133|10188->6136|10253->6173|10269->6179|10339->6227|10442->6301|10472->6302|10504->6306|10592->6365|10622->6366|10656->6372|10708->6396|10737->6397|10769->6401|11211->6814|11231->6823|11267->6836|11692->7233|11721->7234|11752->7237|11795->7249|11827->7254|11971->7387|12012->7388|12044->7392|12092->7412|12108->7418|12173->7461|12209->7469|12225->7475|12299->7526|12346->7542|12375->7544|12520->7678|12561->7679|12594->7684|12642->7704|12658->7710|12724->7754|12760->7762|12776->7768|12850->7819|12897->7835|12926->7836|13098->7979|13128->7980|13157->7981|13249->8045|13278->8046|13342->8081|13372->8082|13406->8088|13473->8126|13503->8127|13536->8132|13679->8247|13708->8248|13794->8305|13824->8306|13858->8312|13917->8343|13946->8344|14008->8377|14038->8378|14070->8382|14111->8394|14141->8395|14179->8404|14209->8405|14292->8459|14322->8460|14352->8461|14385->8466|14414->8467|14445->8470|14504->8500|14534->8501|14566->8505|14630->8541|14659->8542|14690->8545|14779->8605|14809->8606|14841->8610|15017->8758|15046->8759|15075->8760|15104->8761
                  LINES: 26->1|29->1|30->2|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|49->21|49->21|49->21|50->22|50->22|50->22|53->25|53->25|54->26|56->28|56->28|56->28|57->29|57->29|57->29|57->29|57->29|64->36|64->36|64->36|64->36|64->36|68->40|68->40|68->40|69->41|70->42|70->42|70->42|73->45|74->46|76->48|76->48|76->48|77->49|82->54|82->54|85->57|85->57|86->58|86->58|88->60|88->60|88->60|89->61|90->62|90->62|91->63|91->63|94->66|96->68|100->72|100->72|102->74|102->74|102->74|102->74|102->74|106->78|107->79|111->83|112->84|113->85|113->85|114->86|123->95|123->95|123->95|124->96|124->96|124->96|124->96|126->98|127->99|127->99|127->99|128->100|128->100|128->100|128->100|130->102|131->103|131->103|131->103|131->103|132->104|132->104|132->104|133->105|133->105|133->105|134->106|134->106|134->106|135->107|135->107|135->107|136->108|136->108|136->108|143->115|143->115|143->115|144->116|144->116|144->116|144->116|146->118|146->118|147->119|148->120|148->120|149->121|150->122|150->122|151->123|152->124|152->124|152->124|157->129|157->129|158->130|159->131|161->133|161->133|161->133|163->135|163->135|163->135|163->135|163->135|163->135|163->135|164->136|164->136|164->136|164->136|165->137|165->137|165->137|165->137|165->137|165->137|165->137|166->138|167->139|173->145|173->145|174->146|176->148|176->148|178->150|178->150|180->152|180->152|180->152|181->153|184->156|184->156|186->158|186->158|187->159|188->160|188->160|190->162|190->162|191->163|191->163|191->163|191->163|191->163|191->163|191->163|191->163|192->164|192->164|193->165|193->165|193->165|194->166|195->167|195->167|196->168|196->168|196->168|197->169|199->171|199->171|200->172|200->172
                  -- GENERATED --
              */
          