
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[List[com.xo.web.controllers.kpi_get],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(kpi_get:List[com.xo.web.controllers.kpi_get],segment:List[String]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.69*/("""


"""),format.raw/*4.1*/("""<!doctype html>
<html lang="en">
<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properties -->
  <title>Admin Layout</title>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.js"></script>
  
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.7/semantic.css">

  <style type="text/css">
  input[type="text"]"""),format.raw/*21.21*/("""{"""),format.raw/*21.22*/("""
      """),format.raw/*22.7*/("""width:50px !important;
  """),format.raw/*23.3*/("""}"""),format.raw/*23.4*/("""
  """),format.raw/*24.3*/(""".ui.form, select"""),format.raw/*24.19*/("""{"""),format.raw/*24.20*/("""
    """),format.raw/*25.5*/("""background: rgba(255,255,255,0.7) !important;
    margin-top: 10% !important;
    border-radius: 10px !important;
    border: 1px solid !important;
  """),format.raw/*29.3*/("""}"""),format.raw/*29.4*/("""
  """),format.raw/*30.3*/(""".ui.checkbox"""),format.raw/*30.15*/("""{"""),format.raw/*30.16*/("""
      """),format.raw/*31.7*/("""padding-left:10px;
  """),format.raw/*32.3*/("""}"""),format.raw/*32.4*/("""
  """),format.raw/*33.3*/("""option"""),format.raw/*33.9*/("""{"""),format.raw/*33.10*/("""
      """),format.raw/*34.7*/("""padding:10px !important;
  """),format.raw/*35.3*/("""}"""),format.raw/*35.4*/("""
  """),format.raw/*36.3*/(""".ui.checkbox:first-child,option:first-child"""),format.raw/*36.46*/("""{"""),format.raw/*36.47*/("""
      """),format.raw/*37.7*/("""padding-top:10px;
  """),format.raw/*38.3*/("""}"""),format.raw/*38.4*/("""
  """),format.raw/*39.3*/(""".ui.attached """),format.raw/*39.16*/("""{"""),format.raw/*39.17*/("""
    """),format.raw/*40.5*/("""border-top: solid rgba(232, 232, 233, 0.97) !important;
"""),format.raw/*41.1*/("""}"""),format.raw/*41.2*/("""
  """),format.raw/*42.3*/("""</style>
</head>
<body>
	<div class="ui two column aligned container stackable grid" id="content" style="width:100% !important;">
	   <div class="three wide column">
    	<div class="ui vertical pointing menu">
    		<a class="item active" data-tab="kpi selection">KPI Selection</a>
    		<a class="item" data-tab="target">Target</a>
    	</div>
    	</div>
    	<div class="twelve wide column">
    	<div class="ui bottom attached tab segment active" data-tab="kpi selection">
    		<div class="ui three column aligned container stackable grid" id="content">
    			<div class="four wide column" id="kpi selection left">
    				<h3>KPI List</h3>
    				<form class="ui form" id="kpi select ckecklist" style="height:250px; overflow: auto;">
    				"""),_display_(/*58.10*/for( widget <- kpi_get) yield /*58.33*/ {_display_(Seq[Any](format.raw/*58.35*/("""
                        """),format.raw/*59.25*/("""<div class="ui checkbox" style="display: block !important;">
                		"""),_display_(/*60.20*/if(widget.checked==0)/*60.41*/{_display_(Seq[Any](format.raw/*60.42*/("""
    						"""),format.raw/*61.11*/("""<input type="checkbox" class="select_kpi" onchange="selectlist(this);" value=""""),_display_(/*61.90*/widget/*61.96*/.name),format.raw/*61.101*/("""">
    						<label>"""),_display_(/*62.19*/widget/*62.25*/.name),format.raw/*62.30*/("""</label><br>
    					""")))}/*63.12*/else/*63.17*/{_display_(Seq[Any](format.raw/*63.18*/("""
    					   """),format.raw/*64.13*/("""<input type="checkbox" class="select_kpi" onchange="selectlist(this);" value=""""),_display_(/*64.92*/widget/*64.98*/.name),format.raw/*64.103*/("""" checked>
    						<label>"""),_display_(/*65.19*/widget/*65.25*/.name),format.raw/*65.30*/("""</label><br>
    					""")))}),format.raw/*66.11*/("""
    					"""),format.raw/*67.10*/("""</div>
                """)))}),format.raw/*68.18*/("""
    				"""),format.raw/*69.9*/("""</form>
    			</div>
    			<div class="four wide column" id="kpi selection right">
        			<center style="width:80% !important">    			
        				<h3>Selected KPI List</h3>
        				<select name="kpi selected" id="kpi selected" style="visibility: hidden; width: 150px; overflow: hidden;">
        
        				</select>
        				<br>
        				<br>
        				<button class="ui primary  button" id="select_submit" onclick="kpi_select_update();">Update</button>
    				</center>
    			</div>
    			<div class="six wide column" id="kpi_selection_segment">
    			
    			</div>			
    		</div>
    	</div>
    	<div class="ui bottom attached tab segment" data-tab="target">
    		<div class="ui three column aligned container stackable divided grid" id="content">
    			<div class="five wide column" id="left">
    				<div class="ui two column stackable grid">
    					<div class="row" id="dropdown">
    						<div class="ui selection dropdown">
    							<input type="hidden" name="segment" id="segment" onchange="segment(this.value);" value="A1">
    							<i class="dropdown icon"></i>
    							<div class="default text">Segment</div>
    							<div class="menu">
    							"""),_display_(/*97.13*/for(seg<-segment) yield /*97.30*/{_display_(Seq[Any](format.raw/*97.31*/("""
    								"""),format.raw/*98.13*/("""<div class="item" data-value=""""),_display_(/*98.44*/seg),format.raw/*98.47*/("""">"""),_display_(/*98.50*/seg),format.raw/*98.53*/("""</div>
    							""")))}),format.raw/*99.13*/("""
    							"""),format.raw/*100.12*/("""</div>
    						</div>
    					</div>
    				</div>
    				<div class="ui two column stackable grid" style="visibility: visible;" id="kpi_list">
    					<div class="row">
    						<div class="column">
    							<form id="checklist" class="ui form" style="height:250px; overflow: auto;">
    							</form>
    					   </div>
    						<div class="column">
    							Remaining KPI:
    							<label id="remainingkpi">3</label>
    						</div>					   
    					</div>
    				</div>
    			</div>
    			<div class="five wide column divided grid">
    				"""),_display_(/*118.10*/for( id <- 1 to 3 ) yield /*118.29*/ {_display_(Seq[Any](format.raw/*118.31*/("""
    				"""),format.raw/*119.9*/("""<div class="column" id="kpi """),_display_(/*119.38*/id),format.raw/*119.40*/(""" """),format.raw/*119.41*/("""tabel" style="display:none">
    					<label id="kpi """),_display_(/*120.26*/id),format.raw/*120.28*/(""" """),format.raw/*120.29*/("""label"></label>
    					<table class="ui celled table tablet stackable">
    						<thead>
    					   	<tr>
    					   		<th>Target</th>
    					   		<th>Weightage</th>					   		
    					 		</tr>
    					 	</thead>
    						<tbody>
    					   	<tr>
    					     		<td><input type='text' onkeypress='validate(event)' maxlength="4" id="input start """),_display_(/*130.100*/id),format.raw/*130.102*/(""""></td>
    					     		<td><input type='text' onkeypress='validate(event)' maxlength="4" id="input weight """),_display_(/*131.101*/id),format.raw/*131.103*/(""""></td>
    					   	</tr>					   	
    						</tbody>
    					</table>
    					<br>
    				</div>
    				""")))}),format.raw/*137.10*/("""
    				"""),format.raw/*138.9*/("""<div classs="column">
    					<button class="ui primary  button" onclick="update_target()">Update</button>
    				</div>
    			</div>
    			<div class="five wide column divided grid" id="disp_segment">
    			</div>
    		</div>
    	</div>
    	</div>    	
    </div>
    <script src=""""),_display_(/*148.19*/routes/*148.25*/.Assets.versioned("js/koapp/main/admin.js")),format.raw/*148.68*/("""" type="text/javascript"></script>
</body>
</html>"""))}
  }

  def render(kpi_get:List[com.xo.web.controllers.kpi_get],segment:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(kpi_get,segment)

  def f:((List[com.xo.web.controllers.kpi_get],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (kpi_get,segment) => apply(kpi_get,segment)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/index.scala.html
                  HASH: e7cd4c2ce1b3d243ca43f35ac2b667641f319c2d
                  MATRIX: 777->1|932->68|961->71|1655->737|1684->738|1718->745|1770->770|1798->771|1828->774|1872->790|1901->791|1933->796|2110->946|2138->947|2168->950|2208->962|2237->963|2271->970|2319->991|2347->992|2377->995|2410->1001|2439->1002|2473->1009|2527->1036|2555->1037|2585->1040|2656->1083|2685->1084|2719->1091|2766->1111|2794->1112|2824->1115|2865->1128|2894->1129|2926->1134|3009->1190|3037->1191|3067->1194|3846->1946|3885->1969|3925->1971|3978->1996|4085->2076|4115->2097|4154->2098|4193->2109|4299->2188|4314->2194|4341->2199|4389->2220|4404->2226|4430->2231|4472->2255|4485->2260|4524->2261|4565->2274|4671->2353|4686->2359|4713->2364|4769->2393|4784->2399|4810->2404|4864->2427|4902->2437|4957->2461|4993->2470|6225->3675|6258->3692|6297->3693|6338->3706|6396->3737|6420->3740|6450->3743|6474->3746|6524->3765|6565->3777|7158->4342|7194->4361|7235->4363|7272->4372|7329->4401|7353->4403|7383->4404|7465->4458|7489->4460|7519->4461|7904->4817|7929->4819|8066->4927|8091->4929|8234->5040|8271->5049|8590->5340|8606->5346|8671->5389
                  LINES: 26->1|29->1|32->4|49->21|49->21|50->22|51->23|51->23|52->24|52->24|52->24|53->25|57->29|57->29|58->30|58->30|58->30|59->31|60->32|60->32|61->33|61->33|61->33|62->34|63->35|63->35|64->36|64->36|64->36|65->37|66->38|66->38|67->39|67->39|67->39|68->40|69->41|69->41|70->42|86->58|86->58|86->58|87->59|88->60|88->60|88->60|89->61|89->61|89->61|89->61|90->62|90->62|90->62|91->63|91->63|91->63|92->64|92->64|92->64|92->64|93->65|93->65|93->65|94->66|95->67|96->68|97->69|125->97|125->97|125->97|126->98|126->98|126->98|126->98|126->98|127->99|128->100|146->118|146->118|146->118|147->119|147->119|147->119|147->119|148->120|148->120|148->120|158->130|158->130|159->131|159->131|165->137|166->138|176->148|176->148|176->148
                  -- GENERATED --
              */
          