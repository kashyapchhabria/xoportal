
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
object access_denied extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](_display_(/*1.2*/xo_base/*1.9*/{_display_(Seq[Any](format.raw/*1.10*/("""
	"""),format.raw/*2.2*/("""<head>
		<title>"""),_display_(/*3.11*/Messages("accessdenied.header")),format.raw/*3.42*/("""</title>
	</head>
		<div class="uk-container uk-container-center  uk-text-center">
			<h2>"""),_display_(/*6.9*/Messages("accessdenied.header")),format.raw/*6.40*/("""</h2>
			<p>"""),_display_(/*7.8*/Messages("accessdenied.desc")),format.raw/*7.37*/("""</p>
        </div>
""")))}))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:03 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/access_denied.scala.html
                  HASH: 4a47c4e74463f6e90a975b7da8c0aa667d70d80a
                  MATRIX: 817->1|831->8|869->9|897->11|940->28|991->59|1107->150|1158->181|1196->194|1245->223
                  LINES: 29->1|29->1|29->1|30->2|31->3|31->3|34->6|34->6|35->7|35->7
                  -- GENERATED --
              */
          