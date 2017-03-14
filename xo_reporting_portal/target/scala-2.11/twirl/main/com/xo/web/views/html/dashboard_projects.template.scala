
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
object dashboard_projects extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<script id="menuTree" type="text/html">
	<li data-bind= 'attr: """),format.raw/*3.24*/("""{"""),format.raw/*3.25*/(""" """),format.raw/*3.26*/("""class: (subMenuItems().length > 0 ? "has-dropdown not-click" : "")"""),format.raw/*3.92*/("""}"""),format.raw/*3.93*/("""'>
		<a data-bind='text:name, click:loadPageData'></a>
		<ul class="dropdown" data-bind="template: """),format.raw/*5.45*/("""{"""),format.raw/*5.46*/(""" """),format.raw/*5.47*/("""name: 'menuTree', foreach: subMenuItems"""),format.raw/*5.86*/("""}"""),format.raw/*5.87*/("""" ></ul>
	</li>
</script>
<script id="tab_dashboard" type="text/html">
	<section id="tableauReportsPageSection" style="background-color: white;">
		<center data-bind="visible:(!tableaumgmt.errorText() || tableaumgmt.errorText().length <= 0)" >
			<img style="border: 0px" data-bind='visible: (tableaumgmt.placeHolderImageUrl().length > 0), attr: """),format.raw/*11.103*/("""{"""),format.raw/*11.104*/("""src: tableaumgmt.placeHolderImageUrl"""),format.raw/*11.140*/("""}"""),format.raw/*11.141*/("""'></img>
			<div id="tableauViewPlace" data-bind='attr:"""),format.raw/*12.47*/("""{"""),format.raw/*12.48*/("""style:(tableaumgmt.isTopBarVisibile() ? "background-color:white;margin-top:3px;" : "background-color:white;margin-top:-45px;")"""),format.raw/*12.174*/("""}"""),format.raw/*12.175*/("""'></div>
		</center>
		<center data-bind="visible:(tableaumgmt.errorText() && tableaumgmt.errorText().length > 0)" >
			<span class="alert">
				<h3>Report Loading Error:</h3>
				<p data-bind="text:tableaumgmt.errorText"></p>
			</span>
		</center>
	</section>
</script>
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:29 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/dashboard_projects.scala.html
                  HASH: bcc77b975d2e2a7023b92dd936324338d2ea11c5
                  MATRIX: 740->1|829->3|856->4|946->67|974->68|1002->69|1095->135|1123->136|1249->235|1277->236|1305->237|1371->276|1399->277|1774->623|1804->624|1869->660|1899->661|1982->716|2011->717|2166->843|2196->844
                  LINES: 26->1|29->1|30->2|31->3|31->3|31->3|31->3|31->3|33->5|33->5|33->5|33->5|33->5|39->11|39->11|39->11|39->11|40->12|40->12|40->12|40->12
                  -- GENERATED --
              */
          