
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
object forgot_password_result extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.16*/("""
"""),_display_(/*2.2*/xo_base/*2.9*/{_display_(Seq[Any](format.raw/*2.10*/("""
	"""),format.raw/*3.2*/("""<div class="panel active">
		<p>
			An email has been sent with a password reset link to your registered mail id.<br />
			Kindly check your inbox. If you are not able to see it then please look at your trash / spam folders.<br />
		</p>
	</div>
	
""")))}))}
  }

  def render(name:String): play.twirl.api.HtmlFormat.Appendable = apply(name)

  def f:((String) => play.twirl.api.HtmlFormat.Appendable) = (name) => apply(name)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/forgot_password_result.scala.html
                  HASH: 2761194998ce505f9b7b7345f22b55651dc2bf86
                  MATRIX: 751->1|853->15|880->17|894->24|932->25|960->27
                  LINES: 26->1|29->1|30->2|30->2|30->2|31->3
                  -- GENERATED --
              */
          