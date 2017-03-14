
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
import java.util.List;
import com.xo.web.util.XoUtil;
/**/
object xoapp_base extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[List[Html],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(contents: List[Html]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*3.24*/("""
"""),_display_(/*4.2*/xo_base/*4.9*/{_display_(Seq[Any](format.raw/*4.10*/("""

		"""),format.raw/*6.3*/("""<!-- Show the correct page content, based on the current URL fragment. -->
		<div id="content"
			data-bind="template: """),format.raw/*8.25*/("""{"""),format.raw/*8.26*/(""" """),format.raw/*8.27*/("""name: router.currentPage().view,data: router.currentPage().model """),format.raw/*8.92*/("""}"""),format.raw/*8.93*/("""">
		</div>
		"""),_display_(/*10.4*/for(template <- contents) yield /*10.29*/{_display_(Seq[Any](format.raw/*10.30*/("""
			"""),_display_(/*11.5*/template),format.raw/*11.13*/("""
		""")))}),format.raw/*12.4*/("""
		
		"""),format.raw/*14.3*/("""<!-- Templates used directly by the Router -->
		<script id="404-template" type="text/html">
  			<h2>404 - Page not found</h2>
		</script>

		<script id="error-template" type="text/html">
  			<h2>Error</h2>
  			<p data-bind="text: error"></p>
		</script>

		<script id="loading-template" type="text/html">
  			<h2>Loading...</h2>
		</script>
	</div>
""")))}))}
  }

  def render(contents:List[Html]): play.twirl.api.HtmlFormat.Appendable = apply(contents)

  def f:((List[Html]) => play.twirl.api.HtmlFormat.Appendable) = (contents) => apply(contents)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/xoapp_base.scala.html
                  HASH: cc97e2e895747dff526bf479e5a7b8557af3e859
                  MATRIX: 796->57|906->79|933->81|947->88|985->89|1015->93|1161->212|1189->213|1217->214|1309->279|1337->280|1378->295|1419->320|1458->321|1489->326|1518->334|1552->338|1585->344
                  LINES: 27->3|30->3|31->4|31->4|31->4|33->6|35->8|35->8|35->8|35->8|35->8|37->10|37->10|37->10|38->11|38->11|39->12|41->14
                  -- GENERATED --
              */
          