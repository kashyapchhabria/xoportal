
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
object create_user extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<script id="create-surveyor" type="text/html">
<section>
		<form data-bind="submit: usermanagement.saveSurveyor">
			<center data-bind="ifnot: usermanagement.userId">
				<h2>"""),_display_(/*6.10*/Messages("user.create")),format.raw/*6.33*/("""</h2>
			</center>
			<center data-bind="if: usermanagement.userId">
				<h2 >"""),_display_(/*9.11*/Messages("user.edit")),format.raw/*9.32*/("""</h2>
			</center>
			<br>
				<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible: usermanagement.sholdShowMessage">
					 <div id="alert-message" class="text-center fontBlack"></div>
				</div>
			<div class="row">
				<div class="medium-6 small-centered columns">
						<label>"""),_display_(/*17.15*/Messages("user.fstname")),format.raw/*17.39*/("""</label>
						<input type="text" data-bind="value: usermanagement.firstName" required="required"/>
						<label>"""),_display_(/*19.15*/Messages("user.secname")),format.raw/*19.39*/("""</label>
						<input type="text" data-bind="value: usermanagement.lastName" required="required"/>
						
					   <label>"""),_display_(/*22.17*/Messages("user.email")),format.raw/*22.39*/("""</label>
					   <input type="email" data-bind="value: usermanagement.email" required="required"/>
					   <label>"""),_display_(/*24.17*/Messages("user.client")),format.raw/*24.40*/("""</label>
					   <select title='"""),_display_(/*25.25*/Messages("user.selclient")),format.raw/*25.51*/("""' id="selUsrSysClient" 
					   		data-bind='options:usermanagement.availableClients, optionsText: "name", value: usermanagement.selectedClient, optionsCaption:""""),_display_(/*26.139*/Messages("user.selclient")),format.raw/*26.165*/(""""'>
					   </select>
				</div>
			</div><br>
			<center data-bind="ifnot: usermanagement.userId">
				<button id="create_user" class="button extend" type="submit">"""),_display_(/*31.67*/Messages("user.create")),format.raw/*31.90*/("""</button>
			</center>
			<center data-bind="if: usermanagement.userId">
				<button id="update_surveyor" class="button extend" type="submit">"""),_display_(/*34.71*/Messages("user.update")),format.raw/*34.94*/("""</button>
			</center>
		</form>
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/create_user.scala.html
                  HASH: e340fb86ae9eabb4b70fbc8e3383fdc35553dcbe
                  MATRIX: 733->1|822->3|849->4|1051->180|1094->203|1199->282|1240->303|1634->670|1679->694|1820->808|1865->832|2014->954|2057->976|2199->1091|2243->1114|2303->1147|2350->1173|2540->1335|2588->1361|2781->1527|2825->1550|2995->1693|3039->1716
                  LINES: 26->1|29->1|30->2|34->6|34->6|37->9|37->9|45->17|45->17|47->19|47->19|50->22|50->22|52->24|52->24|53->25|53->25|54->26|54->26|59->31|59->31|62->34|62->34
                  -- GENERATED --
              */
          