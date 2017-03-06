
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
object create_role extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<script id="create-role" type="text/html">
<section>
		<form data-bind="submit: rolemanagement.saveRole">
			<center data-bind="ifnot: rolemanagement.roleId">
				<h2>"""),_display_(/*6.10*/Messages("role.create")),format.raw/*6.33*/("""</h2>
			</center>
			<center data-bind="if: rolemanagement.roleId">
				<h2 >"""),_display_(/*9.11*/Messages("role.edit")),format.raw/*9.32*/("""</h2>
			</center>
			<br>
				<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible: rolemanagement.sholdShowMessage">
					 <div id="alert-message" class="text-center fontBlack"></div>
				</div>
			<div class="row">
				<div class="medium-6 small-centered columns">
						<label>"""),_display_(/*17.15*/Messages("role.name")),format.raw/*17.36*/("""</label>
						<input type="text" data-bind="value: rolemanagement.name" required="required"/>
						<label>"""),_display_(/*19.15*/Messages("role.description")),format.raw/*19.43*/("""</label>
						<input type="text" data-bind="value: rolemanagement.description" required="required"/>					
				</div>
			</div><br>
			<center data-bind="ifnot: rolemanagement.roleId">
				<button id="create_role" class="button extend" type="submit">"""),_display_(/*24.67*/Messages("role.create")),format.raw/*24.90*/("""</button>
			</center>
			<center data-bind="if: rolemanagement.roleId">
				<button id="update_role" class="button extend" type="submit">"""),_display_(/*27.67*/Messages("role.update")),format.raw/*27.90*/("""</button>
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
                  DATE: Thu Feb 23 09:42:03 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/create_role.scala.html
                  HASH: dcc5cbe6483fdd178ea5f9e28495b77150837151
                  MATRIX: 733->1|822->3|849->4|1043->172|1086->195|1191->274|1232->295|1626->662|1668->683|1804->792|1853->820|2131->1071|2175->1094|2341->1233|2385->1256
                  LINES: 26->1|29->1|30->2|34->6|34->6|37->9|37->9|45->17|45->17|47->19|47->19|52->24|52->24|55->27|55->27
                  -- GENERATED --
              */
          