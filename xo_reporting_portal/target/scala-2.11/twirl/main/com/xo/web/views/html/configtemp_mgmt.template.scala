
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
object configtemp_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="conftemp_mgmt" type="text/html">
<section>
	<div class="row">
				<span>
    			 <center>
					<h2>"""),_display_(/*6.11*/Messages("config.tmpl.mgmt")),format.raw/*6.39*/("""</h2></center><br><br>
					<span>
						<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible:configmgmt.visibility">
							<div id="alert-message" class="text-center fontBlack"></div>
						</div>
					</span>
					<ul class="sub-nav xo-nav">
						<li class="active">
                    		<a title='Go Back' data-bind="click:configmgmt.backPage">&lt;&lt;&nbsp;"""),_display_(/*14.95*/Messages("config.mgmt.goback")),format.raw/*14.125*/("""</a>
                		</li>
    					<li class="active">
							<a title='"""),_display_(/*17.19*/Messages("config.mgmt.tmpl.newconfig")),format.raw/*17.57*/("""' id="createNewConfigTempl" data-bind='click:configmgmt.newConfigTemplate'>"""),_display_(/*17.133*/Messages("config.mgmt.tmpl.newconfig")),format.raw/*17.171*/("""</a>
						</li>
  					</ul>
				</span>
			<center>
			<table id="allAvailableConfigTemplates">
				<thead>
					<tr>
						<th>"""),_display_(/*25.12*/Messages("config.mgmt.tmpl.name")),format.raw/*25.45*/("""</th>
						<th>"""),_display_(/*26.12*/Messages("config.mgmt.tmpl.action")),format.raw/*26.47*/("""</th>
					</tr>
				</thead>
				<tbody data-bind="foreach: """),format.raw/*29.32*/("""{"""),format.raw/*29.33*/("""data:configmgmt.availableConfigTemplates, as: 'configTemplateRecord'"""),format.raw/*29.101*/("""}"""),format.raw/*29.102*/("""">
					<tr data-bind="ifnot: configTemplateRecord.deleted">
						<td data-bind='text: configTemplateRecord.shortName'></td>
						<td>
							<ul class="inline-list">
								<li>
									<a  title='"""),_display_(/*35.22*/Messages("config.mgmt.tmpl.editconfigtempl")),format.raw/*35.66*/("""' data-bind='attr: """),format.raw/*35.85*/("""{"""),format.raw/*35.86*/("""id: "conftemp_edit_"+configTemplateRecord.configId()"""),format.raw/*35.138*/("""}"""),format.raw/*35.139*/(""", click:configTemplateRecord.editConfigurations'>
										<i class="fi-pencil"></i>
									</a>
								</li>
								<li data-bind="if: configTemplateRecord.active">
									<a title='"""),_display_(/*40.21*/Messages("config.mgmt.tmpl.dactconfigtempl")),format.raw/*40.65*/("""'  data-bind='attr: """),format.raw/*40.85*/("""{"""),format.raw/*40.86*/("""id: "conftemp_active_"+configTemplateRecord.configId()"""),format.raw/*40.140*/("""}"""),format.raw/*40.141*/(""", click:configTemplateRecord.toggleStatus'>
										<i class="fi-unlock"></i>
									</a>
								</li>
								<li data-bind="ifnot: configTemplateRecord.active">
									<a title='"""),_display_(/*45.21*/Messages("config.mgmt.tmpl.actconfigtempl")),format.raw/*45.64*/("""' data-bind='attr: """),format.raw/*45.83*/("""{"""),format.raw/*45.84*/("""id: "conftemp_active_"+configTemplateRecord.configId()"""),format.raw/*45.138*/("""}"""),format.raw/*45.139*/(""", click:configTemplateRecord.toggleStatus'>
										<i class="fi-lock"></i>										
									</a>
								</li>
								<li>
									<a  title='"""),_display_(/*50.22*/Messages("config.mgmt.tmpl.delconfig")),format.raw/*50.60*/("""' data-bind='attr: """),format.raw/*50.79*/("""{"""),format.raw/*50.80*/("""id: "conftemp_delete_"+configTemplateRecord.configId()"""),format.raw/*50.134*/("""}"""),format.raw/*50.135*/(""", click:configTemplateRecord.deleteConfig'>
										<i class="fi-trash"></i>
									</a>
								</li>
								<li>
									<a  title='"""),_display_(/*55.22*/Messages("config.mgmt.tmpl.showconfiginstances")),format.raw/*55.70*/("""' data-bind='attr: """),format.raw/*55.89*/("""{"""),format.raw/*55.90*/("""id: "conftemp_repass_"+configTemplateRecord.configId()"""),format.raw/*55.144*/("""}"""),format.raw/*55.145*/(""", click:configTemplateRecord.showConfigInstances'>
										<i class="fi-thumbnails"></i>
									</a>
								</li>
								<li>
									<a  title='"""),_display_(/*60.22*/Messages("config.mgmt.inst.newconfig")),format.raw/*60.60*/("""' data-bind='attr: """),format.raw/*60.79*/("""{"""),format.raw/*60.80*/("""id: "conftemp_newins_"+configTemplateRecord.configId()"""),format.raw/*60.134*/("""}"""),format.raw/*60.135*/(""", click:configTemplateRecord.newConfigInstance'>
										<i class="fi-page-add"></i>
									</a>
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table></center>
		</div>
</section>
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/configtemp_mgmt.scala.html
                  HASH: 6a1157cfde7f9168d0b5f131f2a53c5f793e4cc0
                  MATRIX: 819->0|957->112|1005->140|1482->590|1534->620|1637->696|1696->734|1800->810|1860->848|2018->979|2072->1012|2116->1029|2172->1064|2261->1125|2290->1126|2387->1194|2417->1195|2647->1398|2712->1442|2759->1461|2788->1462|2869->1514|2899->1515|3117->1706|3182->1750|3230->1770|3259->1771|3342->1825|3372->1826|3587->2014|3651->2057|3698->2076|3727->2077|3810->2131|3840->2132|4017->2282|4076->2320|4123->2339|4152->2340|4235->2394|4265->2395|4433->2536|4502->2584|4549->2603|4578->2604|4661->2658|4691->2659|4871->2812|4930->2850|4977->2869|5006->2870|5089->2924|5119->2925
                  LINES: 29->1|34->6|34->6|42->14|42->14|45->17|45->17|45->17|45->17|53->25|53->25|54->26|54->26|57->29|57->29|57->29|57->29|63->35|63->35|63->35|63->35|63->35|63->35|68->40|68->40|68->40|68->40|68->40|68->40|73->45|73->45|73->45|73->45|73->45|73->45|78->50|78->50|78->50|78->50|78->50|78->50|83->55|83->55|83->55|83->55|83->55|83->55|88->60|88->60|88->60|88->60|88->60|88->60
                  -- GENERATED --
              */
          