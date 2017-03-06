
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
object password_reset_en extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(url: String, name: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.29*/("""
"""),format.raw/*2.1*/("""Hello """),_display_(/*2.8*/name),format.raw/*2.12*/(""",
<br />
<br />
<h3><center>Welcome to Xoanon Reporting Portal</center></h3>
<p>
	A request was made to reset your password. <br />
	Click on the following link to reset your password:<br />
</p>
<p>
	<a href=""""),_display_(/*11.12*/url),format.raw/*11.15*/("""">Reset password</a>
</p>
<p>
	Regards,<br /> 
	<i>Xoportal Admin</i>
</p>"""))}
  }

  def render(url:String,name:String): play.twirl.api.HtmlFormat.Appendable = apply(url,name)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (url,name) => apply(url,name)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/email/password_reset_en.scala.html
                  HASH: 6cb6ff29d70ef69d37fbe1d4a8125e9f13d13ad6
                  MATRIX: 759->1|874->28|901->29|933->36|957->40|1195->251|1219->254
                  LINES: 26->1|29->1|30->2|30->2|30->2|39->11|39->11
                  -- GENERATED --
              */
          