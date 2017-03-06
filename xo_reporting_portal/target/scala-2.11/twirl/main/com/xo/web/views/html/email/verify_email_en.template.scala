
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
object verify_email_en extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(verificationUrl: String, name: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.41*/("""
"""),format.raw/*2.1*/("""Hello """),_display_(/*2.8*/name),format.raw/*2.12*/(""",
<br />
<br />
<h3><center>Welcome to Xoanon Reporting Portal</center></h3>
<p>	Please click on the link below to verify your email address:</p> <br /> 
<p> <a href=""""),_display_(/*7.15*/verificationUrl),format.raw/*7.30*/("""">Email address verification link</a>. </p><br /> 
<p>	You will be required to change your password at the first login. </p>
<br /> <br /> 
<p>
	Regards,<br /> 
	<i>Xoportal Admin</i>
</p>"""))}
  }

  def render(verificationUrl:String,name:String): play.twirl.api.HtmlFormat.Appendable = apply(verificationUrl,name)

  def f:((String,String) => play.twirl.api.HtmlFormat.Appendable) = (verificationUrl,name) => apply(verificationUrl,name)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/email/verify_email_en.scala.html
                  HASH: 7006eea73f8728d1d8b9e220c0f8eb1e560e3ba6
                  MATRIX: 757->1|884->40|911->41|943->48|967->52|1161->220|1196->235
                  LINES: 26->1|29->1|30->2|30->2|30->2|35->7|35->7
                  -- GENERATED --
              */
          