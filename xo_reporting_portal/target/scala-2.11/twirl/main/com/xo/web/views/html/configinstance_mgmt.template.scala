
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
object configinstance_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="confinstance_mgmt" type="text/html">
<section>
	<div class="row">
				<span>
    			 <center>
					<h2 data-bind='text: configmgmt.shortName()+" "+""""),_display_(/*6.56*/Messages("config.inst.mgmt")),format.raw/*6.84*/(""""'></h2>
				 </center><br><br>
					<span>
						<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible:configmgmt.visibility">
							<div id="alert-message" class="text-center fontBlack"></div>
						</div>
					</span>
					<ul class="sub-nav xo-nav">
						<li class="active">
                    		<a title='Go Back' data-bind="click:configmgmt.backPage">&lt;&lt;&nbsp;"""),_display_(/*15.95*/Messages("config.mgmt.goback")),format.raw/*15.125*/("""</a>
                		</li>
    					<li class="active">
							<a title='"""),_display_(/*18.19*/Messages("config.mgmt.inst.newconfig")),format.raw/*18.57*/("""' id="createNewConfigInst" data-bind='click:configmgmt.newConfigInstance'>"""),_display_(/*18.132*/Messages("config.mgmt.inst.newconfig")),format.raw/*18.170*/("""</a>
						</li>
  					</ul>
				</span>
			<center>
			<table id="allAvailableConfigInstances">
				<thead>
					<tr>
						<th>"""),_display_(/*26.12*/Messages("config.mgmt.inst.name")),format.raw/*26.45*/("""</th>
						<th>"""),_display_(/*27.12*/Messages("config.mgmt.inst.action")),format.raw/*27.47*/("""</th>
					</tr>
				</thead>
				<tbody data-bind="foreach: """),format.raw/*30.32*/("""{"""),format.raw/*30.33*/("""data:configmgmt.availableConfigInstances, as: 'configInstanceRecord'"""),format.raw/*30.101*/("""}"""),format.raw/*30.102*/("""">
					<tr data-bind="ifnot: configInstanceRecord.deleted">
						<td data-bind='text: configInstanceRecord.shortName'></td>
						<td>
							<ul class="inline-list">
								<li>
									<a  title='"""),_display_(/*36.22*/Messages("config.mgmt.inst.editconfigtempl")),format.raw/*36.66*/("""' data-bind='attr: """),format.raw/*36.85*/("""{"""),format.raw/*36.86*/("""id: "confinstance_edit_"+configInstanceRecord.configId"""),format.raw/*36.140*/("""}"""),format.raw/*36.141*/(""", click:configInstanceRecord.editConfigurations'>
										<i class="fi-pencil"></i>
									</a>
								</li>
								<li data-bind="if: configInstanceRecord.active">
									<a title='"""),_display_(/*41.21*/Messages("config.mgmt.inst.dactconfigtempl")),format.raw/*41.65*/("""'  data-bind='attr: """),format.raw/*41.85*/("""{"""),format.raw/*41.86*/("""id: "confinstance_active_"+configInstanceRecord.configId"""),format.raw/*41.142*/("""}"""),format.raw/*41.143*/(""", click:configInstanceRecord.toggleStatus'>
										<i class="fi-unlock"></i>
									</a>
								</li>
								<li data-bind="ifnot: configInstanceRecord.active">
									<a title='"""),_display_(/*46.21*/Messages("config.mgmt.inst.actconfigtempl")),format.raw/*46.64*/("""' data-bind='attr: """),format.raw/*46.83*/("""{"""),format.raw/*46.84*/("""id: "confinstance_active_"+configInstanceRecord.configId"""),format.raw/*46.140*/("""}"""),format.raw/*46.141*/(""", click:configInstanceRecord.toggleStatus'>
										<i class="fi-lock"></i>										
									</a>
								</li>								
								<li>
									<a  title='"""),_display_(/*51.22*/Messages("config.mgmt.inst.delconfig")),format.raw/*51.60*/("""' data-bind='attr: """),format.raw/*51.79*/("""{"""),format.raw/*51.80*/("""id: "confinstance_delete_"+configInstanceRecord.configId"""),format.raw/*51.136*/("""}"""),format.raw/*51.137*/(""", click:configInstanceRecord.deleteConfig'>
										<i class="fi-trash"></i>
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
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/configinstance_mgmt.scala.html
                  HASH: 1d4daee26873fc88e2ee501e13596eba7cf7fdb7
                  MATRIX: 823->0|1010->161|1058->189|1544->648|1596->678|1699->754|1758->792|1861->867|1921->905|2079->1036|2133->1069|2177->1086|2233->1121|2322->1182|2351->1183|2448->1251|2478->1252|2708->1455|2773->1499|2820->1518|2849->1519|2932->1573|2962->1574|3180->1765|3245->1809|3293->1829|3322->1830|3407->1886|3437->1887|3652->2075|3716->2118|3763->2137|3792->2138|3877->2194|3907->2195|4092->2353|4151->2391|4198->2410|4227->2411|4312->2467|4342->2468
                  LINES: 29->1|34->6|34->6|43->15|43->15|46->18|46->18|46->18|46->18|54->26|54->26|55->27|55->27|58->30|58->30|58->30|58->30|64->36|64->36|64->36|64->36|64->36|64->36|69->41|69->41|69->41|69->41|69->41|69->41|74->46|74->46|74->46|74->46|74->46|74->46|79->51|79->51|79->51|79->51|79->51|79->51
                  -- GENERATED --
              */
          