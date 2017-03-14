
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
object missing_reports_en extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.16*/("""
"""),format.raw/*2.1*/("""Hello,
<br />
<br />
<h3><center>Welcome to Xoanon Reporting Portal</center></h3>
<p>	Please reconfigure the xoportal reports because of missing reports.</p> <br /> 
<br /> <br />
<p>
	Regards,<br /> 
	<i>Xoportal App</i>
</p>"""))}
  }

  def render(name:String): play.twirl.api.HtmlFormat.Appendable = apply(name)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (name) => apply(name)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:31 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/email/missing_reports_en.scala.html
                  HASH: f512d29ab969cc5851a5863334b895b3b2e3e584
                  MATRIX: 753->1|855->15|882->16
                  LINES: 26->1|29->1|30->2
                  -- GENERATED --
              */
          