
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
<link rel="stylesheet" href='"""),_display_(/*15.31*/routes/*15.37*/.Assets.versioned("stylesheets/cssmenu.css")),format.raw/*15.81*/("""'>
<link href='"""),_display_(/*16.14*/routes/*16.20*/.Assets.versioned("stylesheets/freshwidget.css")),format.raw/*16.68*/("""' type="text/css" rel="stylesheet"/>
<link href='"""),_display_(/*17.14*/routes/*17.20*/.Assets.versioned("stylesheets/selectize.default.css")),format.raw/*17.74*/("""' type="text/css" rel="stylesheet"/>
<script src='"""),_display_(/*18.15*/routes/*18.21*/.Assets.versioned("lib/jquery/jquery.min.js")),format.raw/*18.66*/("""'></script>
<script src='"""),_display_(/*19.15*/routes/*19.21*/.Assets.versioned("lib/modernizr/modernizr.js")),format.raw/*19.68*/("""'></script>
<script src='"""),_display_(/*20.15*/routes/*20.21*/.Assets.versioned("js/xo/common_util.js")),format.raw/*20.62*/("""'></script>
<script type="application/javascript">
    setAppConext('"""),_display_(/*22.20*/com/*22.23*/.xo.web.util.XoUtil.getApplicationContext()),format.raw/*22.66*/("""');
    setAppUser('"""),_display_(/*23.18*/session()/*23.27*/.get("email")),format.raw/*23.40*/("""');
</script>




"""),_display_(/*29.2*/messages()),format.raw/*29.12*/("""
"""),format.raw/*30.1*/("""</head>
<body id="xoportalbody" style="background-color:white !important;">
"""),_display_(/*32.2*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*32.76*/ {_display_(Seq[Any](format.raw/*32.78*/("""
"""),format.raw/*33.1*/("""<div data-bind='ifnot: isTopBarVisibile,attr:"""),format.raw/*33.46*/("""{"""),format.raw/*33.47*/("""class:"sub-nav fixmenu""""),format.raw/*33.70*/("""}"""),format.raw/*33.71*/("""'>
	<a id="showorhidetopbar" data-bind="click: showReportMenus">&nbsp;<i class="fi-align-justify">&nbsp;</i>&nbsp;</a>
</div>
<div data-bind='if: isTopBarVisibile'>
<div class="sticky fixed" data-options="sticky_on:[small,large]">
<nav class="top-bar xoheader" role="navigation" data-topbar="">
		<section class="top-bar-section" data-bind="if : menuData && menuData().length > 0">
			<ul class="left" data-bind="template: """),format.raw/*40.42*/("""{"""),format.raw/*40.43*/(""" """),format.raw/*40.44*/("""name: 'menuTree', foreach: menuData"""),format.raw/*40.79*/("""}"""),format.raw/*40.80*/("""" ></ul>
		</section>
		<ul class="title-area">
			  
			 """),_display_(/*44.6*/if(session().get("roleName") != null)/*44.43*/ {_display_(Seq[Any](format.raw/*44.45*/("""
			 """),format.raw/*45.5*/("""<li class="toggle-topbar outlink">
			 	<a class="outlink" href=""""),_display_(/*46.32*/com/*46.35*/.xo.web.controllers.routes.UserController.logout(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN))),format.raw/*46.147*/("""">			 		
			 		<i style="padding:15;font-size:25px" class="outlink fi-widget size-48"></i><span></span>
			 	</a>
			 </li> """)))}),format.raw/*49.12*/("""
		"""),format.raw/*50.3*/("""</ul>
			 <section class="top-bar-section">
			 	"""),_display_(/*52.7*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*52.81*/ {_display_(Seq[Any](format.raw/*52.83*/("""
			 	"""),format.raw/*53.6*/("""<ul class="right">
			 		<li class="name">
			 			<a id="home_button" class="outlink xoheader" style="font-family:ralewaymedium;" href="#"><i class="fi-home">&nbsp;</i></a>
			 		</li>
			 		<li data-bind='if: isTopBarVisibile()'>
						<a data-bind='click: showReportMenus'><i class="fi-arrows-in">&nbsp;&nbsp;"""),_display_(/*58.82*/Messages("tableau.report.hidereportmenus")),format.raw/*58.124*/("""</i></a>
					</li>
					<li data-bind='if: isFullScreenAvailable'>
						<a data-bind='ifnot: isFullScreenEnabled, click: fullScreenView'><i class="fi-arrows-out">&nbsp;&nbsp;"""),_display_(/*61.110*/Messages("tableau.report.fullscreen")),format.raw/*61.147*/("""</i></a>
						<a data-bind='if: isFullScreenEnabled, click: closeFullScreen'><i class="fi-arrows-in">&nbsp;&nbsp;"""),_display_(/*62.107*/Messages("tableau.report.exitfullscreen")),format.raw/*62.148*/("""</i></a>
					</li>
			 		"""),_display_(/*64.8*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_X_SUPER_CLIENT) != null)/*64.86*/ {_display_(Seq[Any](format.raw/*64.88*/("""
			 	    """),format.raw/*65.10*/("""<li>
			 	    	<select style="margin:0 0 0;margin-top: 4px;" title='"""),_display_(/*66.65*/Messages("user.selclient")),format.raw/*66.91*/("""' id="selSupSysClient" 
				   			data-bind='options:allClients, optionsText: "name", value: selectedSupClient, optionsCaption:""""),_display_(/*67.106*/Messages("user.selclient")),format.raw/*67.132*/(""""'>
				   		</select>
			 	    </li>
			 	    """)))}),format.raw/*70.11*/("""
					
			 		"""),format.raw/*72.7*/("""<li class="outlink has-dropdown not-click" data-equalizer-watch>
			 			<a href="#" style="padding:15"><i class="outlink fi-widget size-48"></i></a>
			 			<ul class="dropdown">
			 				<li><a href="http://xoanon.helpdocsonline.com/o73n03uauu38vjg8l6rqrb7z0unyk6l9" target="_blank">User Guide</a></li>
			 				<li><a id="changepassword" class="outlink" href="#changepassword">"""),_display_(/*76.76*/Messages("base.changepassword")),format.raw/*76.107*/("""</a></li>
			 				<li><a onclick="composeSupport()">Support</a></li>
 			 				<li><a class="outlink" id="xologout" href=""""),_display_(/*78.54*/com/*78.57*/.xo.web.controllers.routes.UserController.logout(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN))),format.raw/*78.169*/("""">"""),_display_(/*78.172*/Messages("base.logout")),format.raw/*78.195*/("""</a></li>
			 			</ul>
			 		</li>
			 	</ul>
				 """)))}),format.raw/*82.7*/("""
			 """),format.raw/*83.5*/("""</section>
</nav>
</div>
</div>
""")))}),format.raw/*87.2*/("""
	"""),format.raw/*88.2*/("""<div id='xohtmlcontent' class="small-12 small-centered columns xo-content">
		"""),_display_(/*89.4*/content),format.raw/*89.11*/("""
	"""),format.raw/*90.2*/("""</div>

	<div class="footer" style="background-color: white;">
		<div class="row" style="background-color: white;">
			<div class="small-8 small-centered  columns" style="background-color: white;">
				<p style="font-size:0.7rem"class="copyright text-center">© Xoanon Analytics. All rights reserved.</p>
			</div>
		</div>
	</div>
	"""),_display_(/*99.3*/if(session().get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN) != null)/*99.77*/ {_display_(Seq[Any](format.raw/*99.79*/("""
		"""),format.raw/*100.3*/("""<div id="authtokenvalue" authtoken=""""),_display_(/*100.40*/session()/*100.49*/.get(com.xo.web.util.XoAppConstant.HEADER_AUTH_TOKEN)),format.raw/*100.102*/("""">
		</div>
	""")))}),format.raw/*102.3*/("""
	"""),_display_(/*103.3*/if(session().get(com.xo.web.util.XoAppConstant.CURRENT_PAGE) != null)/*103.72*/ {_display_(Seq[Any](format.raw/*103.74*/("""
		"""),format.raw/*104.3*/("""<div id="curpagehash" pagehash=""""),_display_(/*104.36*/session()/*104.45*/.get(com.xo.web.util.XoAppConstant.CURRENT_PAGE)),format.raw/*104.93*/("""">
		</div>
	""")))}),format.raw/*106.3*/("""
"""),format.raw/*107.1*/("""<script src='"""),_display_(/*107.15*/routes/*107.21*/.Assets.versioned("lib/fastclick/fastclick.js")),format.raw/*107.68*/("""'></script>
<script src='"""),_display_(/*108.15*/routes/*108.21*/.Assets.versioned("lib/foundation/js/foundation.min.js")),format.raw/*108.77*/("""'></script>
<script src='"""),_display_(/*109.15*/routes/*109.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.dropdown.js")),format.raw/*109.93*/("""'></script>
<script src='"""),_display_(/*110.15*/routes/*110.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.topbar.js")),format.raw/*110.91*/("""'></script>
<script src='"""),_display_(/*111.15*/routes/*111.21*/.Assets.versioned("lib/foundation/js/foundation/foundation.magellan.js")),format.raw/*111.93*/("""'></script>
<script src='"""),_display_(/*112.15*/routes/*112.21*/.Assets.versioned("js/tableau/tableau-2.min.js")),format.raw/*112.69*/("""'></script>

<div id='supportWidget' class="row">
</div>
</body>
<!-- Call the main.js -->
	
	"""),_display_(/*119.3*/if(session().get("email") != null)/*119.37*/ {_display_(Seq[Any](format.raw/*119.39*/("""
		"""),format.raw/*120.3*/("""<script type="text/javascript" src='"""),_display_(/*120.40*/routes/*120.46*/.Assets.versioned("js/freshdesk/freshwidget.js")),format.raw/*120.94*/("""'></script>
 		<script type="text/javascript">
		function composeSupport()"""),format.raw/*122.28*/("""{"""),format.raw/*122.29*/("""
			"""),format.raw/*123.4*/("""var page_name = getLocationHash();
 			if(page_name == '') """),format.raw/*124.25*/("""{"""),format.raw/*124.26*/("""
 				"""),format.raw/*125.6*/("""page_name = 'home';
 			"""),format.raw/*126.5*/("""}"""),format.raw/*126.6*/("""
			"""),format.raw/*127.4*/("""var html = "<div id='supportModal' class='reveal-modal support-modal' data-reveal='' data-options='close_on_background_click:false;close_on_esc:false;'>";
			html += "<iframe id= 'freshIframe'title='Feedback Form' class='freshwidget-embedded-form' id='freshwidget-embedded-form' src='https://xoanonanalytics.freshdesk.com/widgets/feedback_widget/new?&widgetType=embedded&screenshot=no&helpdesk_ticket[requester]="""),_display_(/*128.259*/session()/*128.268*/.get("email")),format.raw/*128.281*/("""&disable[custom_field][url_320535]=true&helpdesk_ticket[custom_field][url_320535]=XoPortal "+page_name+"' scrolling='no' height='500px' width='100%' frameborder='0' >";
			html += "</iframe>";
			html += "<a class='close-reveal-modal topRight' id='close_modal_deact'>×</a></div>";
			document.getElementById("supportWidget").innerHTML = html;
			$('#supportModal').foundation('reveal', 'open');
		"""),format.raw/*133.3*/("""}"""),format.raw/*133.4*/("""
		"""),format.raw/*134.3*/("""</script>
	""")))}),format.raw/*135.3*/("""
	
	"""),_display_(/*137.3*/if(session().get("roleName") != null && (com.xo.web.models.system.RoleEnum.Admin.name().equalsIgnoreCase(session().get("roleName"))))/*137.136*/{_display_(Seq[Any](format.raw/*137.137*/("""

		"""),format.raw/*139.3*/("""<script data-main=""""),_display_(/*139.23*/routes/*139.29*/.Assets.versioned("js/koapp/admin_main.js")),format.raw/*139.72*/("""" src=""""),_display_(/*139.80*/routes/*139.86*/.WebJarAssets.at(WebJarAssets.locate("require.js"))),format.raw/*139.137*/(""""></script>
  	""")))}),format.raw/*140.5*/(""" """),_display_(/*140.7*/if(session().get("roleName") != null && (com.xo.web.models.system.RoleEnum.Viewer.name().equalsIgnoreCase(session().get("roleName"))))/*140.141*/{_display_(Seq[Any](format.raw/*140.142*/("""
  		"""),format.raw/*141.5*/("""<script data-main=""""),_display_(/*141.25*/routes/*141.31*/.Assets.versioned("js/koapp/viewer_main.js")),format.raw/*141.75*/("""" src=""""),_display_(/*141.83*/routes/*141.89*/.WebJarAssets.at(WebJarAssets.locate("require.js"))),format.raw/*141.140*/(""""></script>
  	""")))}),format.raw/*142.5*/("""
"""),format.raw/*143.1*/("""<script type="text/javascript">

//$('.menu .item').tab();
//$('.ui.dropdown').dropdown();
//Wait for window load
$(window).load(function() """),format.raw/*148.27*/("""{"""),format.raw/*148.28*/("""
"""),format.raw/*149.1*/("""// Animate loader off screen
	$(".se-pre-con").fadeOut("slow");
"""),format.raw/*151.1*/("""}"""),format.raw/*151.2*/(""");
	$( document ).ready(function()"""),format.raw/*152.32*/("""{"""),format.raw/*152.33*/("""
		"""),format.raw/*153.3*/("""if($('#authtokenvalue')) """),format.raw/*153.28*/("""{"""),format.raw/*153.29*/("""
			"""),format.raw/*154.4*/("""$.ajaxSetup("""),format.raw/*154.16*/("""{"""),format.raw/*154.17*/("""headers: """),format.raw/*154.26*/("""{"""),format.raw/*154.27*/("""'X-Xoanon-Auth':$('#authtokenvalue').attr('authtoken')"""),format.raw/*154.81*/("""}"""),format.raw/*154.82*/("""}"""),format.raw/*154.83*/(""");
		"""),format.raw/*155.3*/("""}"""),format.raw/*155.4*/("""
		"""),format.raw/*156.3*/("""if($('#alert-box-container')) """),format.raw/*156.33*/("""{"""),format.raw/*156.34*/("""
			"""),format.raw/*157.4*/("""$('#alert-box-container').hide();
		"""),format.raw/*158.3*/("""}"""),format.raw/*158.4*/("""
		"""),format.raw/*159.3*/("""if($('#curpagehash') && $('#curpagehash').attr('pagehash')) """),format.raw/*159.63*/("""{"""),format.raw/*159.64*/("""
			"""),format.raw/*160.4*/("""//window.location.href = window.location.href + "#" + $('#curpagehash').attr('pagehash');
			setLocationHash($('#curpagehash').attr('pagehash'));
		"""),format.raw/*162.3*/("""}"""),format.raw/*162.4*/("""
"""),format.raw/*163.1*/("""}"""),format.raw/*163.2*/(""");
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
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/xo_base.scala.html
                  HASH: fd98d81238b50f81425ef9b0db034c1beb1708b0
                  MATRIX: 734->1|836->15|863->16|1078->205|1118->225|1186->267|1200->273|1259->312|1318->345|1332->351|1406->405|1466->438|1481->444|1563->505|1623->538|1638->544|1732->616|1792->649|1807->655|1911->737|1971->770|1986->776|2048->817|2108->850|2123->856|2186->898|2246->931|2261->937|2326->981|2369->997|2384->1003|2453->1051|2530->1101|2545->1107|2620->1161|2698->1212|2713->1218|2779->1263|2832->1289|2847->1295|2915->1342|2968->1368|2983->1374|3045->1415|3142->1485|3154->1488|3218->1531|3266->1552|3284->1561|3318->1574|3363->1593|3394->1603|3422->1604|3525->1681|3608->1755|3648->1757|3676->1758|3749->1803|3778->1804|3829->1827|3858->1828|4309->2251|4338->2252|4367->2253|4430->2288|4459->2289|4544->2348|4590->2385|4630->2387|4662->2392|4755->2458|4767->2461|4901->2573|5057->2698|5087->2701|5163->2751|5246->2825|5286->2827|5319->2833|5658->3145|5722->3187|5927->3364|5986->3401|6129->3516|6192->3557|6245->3584|6332->3662|6372->3664|6410->3674|6506->3743|6553->3769|6710->3898|6758->3924|6837->3972|6877->3985|7281->4362|7334->4393|7483->4515|7495->4518|7629->4630|7660->4633|7705->4656|7787->4708|7819->4713|7882->4746|7911->4748|8016->4827|8044->4834|8073->4836|8432->5169|8515->5243|8555->5245|8586->5248|8651->5285|8670->5294|8746->5347|8791->5361|8821->5364|8900->5433|8941->5435|8972->5438|9033->5471|9052->5480|9122->5528|9167->5542|9196->5543|9238->5557|9254->5563|9323->5610|9377->5636|9393->5642|9471->5698|9525->5724|9541->5730|9635->5802|9689->5828|9705->5834|9797->5904|9851->5930|9867->5936|9961->6008|10015->6034|10031->6040|10101->6088|10223->6183|10267->6217|10308->6219|10339->6222|10404->6259|10420->6265|10490->6313|10593->6387|10623->6388|10655->6392|10743->6451|10773->6452|10807->6458|10859->6482|10888->6483|10920->6487|11362->6900|11382->6909|11418->6922|11843->7319|11872->7320|11903->7323|11946->7335|11978->7340|12122->7473|12163->7474|12195->7478|12243->7498|12259->7504|12324->7547|12360->7555|12376->7561|12450->7612|12497->7628|12526->7630|12671->7764|12712->7765|12745->7770|12793->7790|12809->7796|12875->7840|12911->7848|12927->7854|13001->7905|13048->7921|13077->7922|13246->8062|13276->8063|13305->8064|13397->8128|13426->8129|13489->8163|13519->8164|13550->8167|13604->8192|13634->8193|13666->8197|13707->8209|13737->8210|13775->8219|13805->8220|13888->8274|13918->8275|13948->8276|13981->8281|14010->8282|14041->8285|14100->8315|14130->8316|14162->8320|14226->8356|14255->8357|14286->8360|14375->8420|14405->8421|14437->8425|14613->8573|14642->8574|14671->8575|14700->8576
                  LINES: 26->1|29->1|30->2|35->7|35->7|36->8|36->8|36->8|37->9|37->9|37->9|38->10|38->10|38->10|39->11|39->11|39->11|40->12|40->12|40->12|41->13|41->13|41->13|42->14|42->14|42->14|43->15|43->15|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|48->20|48->20|48->20|50->22|50->22|50->22|51->23|51->23|51->23|57->29|57->29|58->30|60->32|60->32|60->32|61->33|61->33|61->33|61->33|61->33|68->40|68->40|68->40|68->40|68->40|72->44|72->44|72->44|73->45|74->46|74->46|74->46|77->49|78->50|80->52|80->52|80->52|81->53|86->58|86->58|89->61|89->61|90->62|90->62|92->64|92->64|92->64|93->65|94->66|94->66|95->67|95->67|98->70|100->72|104->76|104->76|106->78|106->78|106->78|106->78|106->78|110->82|111->83|115->87|116->88|117->89|117->89|118->90|127->99|127->99|127->99|128->100|128->100|128->100|128->100|130->102|131->103|131->103|131->103|132->104|132->104|132->104|132->104|134->106|135->107|135->107|135->107|135->107|136->108|136->108|136->108|137->109|137->109|137->109|138->110|138->110|138->110|139->111|139->111|139->111|140->112|140->112|140->112|147->119|147->119|147->119|148->120|148->120|148->120|148->120|150->122|150->122|151->123|152->124|152->124|153->125|154->126|154->126|155->127|156->128|156->128|156->128|161->133|161->133|162->134|163->135|165->137|165->137|165->137|167->139|167->139|167->139|167->139|167->139|167->139|167->139|168->140|168->140|168->140|168->140|169->141|169->141|169->141|169->141|169->141|169->141|169->141|170->142|171->143|176->148|176->148|177->149|179->151|179->151|180->152|180->152|181->153|181->153|181->153|182->154|182->154|182->154|182->154|182->154|182->154|182->154|182->154|183->155|183->155|184->156|184->156|184->156|185->157|186->158|186->158|187->159|187->159|187->159|188->160|190->162|190->162|191->163|191->163
                  -- GENERATED --
              */
          