
package com.xo.web.views.html.email

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
object configure_reports_en extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""Hello Xoapp Support Team,

<table>
	<tr>
		<th>XoApp</th>
		<td>Xoportal</td>
	</tr>
	<tr>
		<th>Error</th>
		<td>Please reconfigure the xoportal reports because of tableau sync activity.</td>
	</tr>
</table>
 
<p>
Regards,
<br /> 
<i>Xoportal App</i>
</p>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:31 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/email/configure_reports_en.scala.html
                  HASH: aa0b561046681e58d496a81e1e66b0b6a75e21d4
                  MATRIX: 748->1|837->3|864->4
                  LINES: 26->1|29->1|30->2
                  -- GENERATED --
              */
          