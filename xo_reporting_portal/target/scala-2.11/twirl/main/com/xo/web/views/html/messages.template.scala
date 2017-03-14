
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

/* messages Template File */
object messages extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /* messages Template File */
  def apply/*2.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*2.4*/("""
"""),format.raw/*3.1*/("""<script type="text/javascript">
var messages = """),format.raw/*4.16*/("""{"""),format.raw/*4.17*/("""
	"""),format.raw/*5.2*/(""""ui.survey.confirmnext": """"),_display_(/*5.29*/Messages("ui.survey.confirmnext")),format.raw/*5.62*/("""",
	"ui.surveyadmin.selectfile": """"),_display_(/*6.33*/Messages("ui.surveyadmin.selectfile")),format.raw/*6.70*/("""",
	"ui.surveyadmin.selectinsertype": """"),_display_(/*7.38*/Messages("ui.surveyadmin.selectinsertype")),format.raw/*7.80*/("""",
	"ui.survey.confirmredirect": """"),_display_(/*8.33*/Messages("ui.survey.confirmredirect")),format.raw/*8.70*/("""",
	"ui.survey.submitfailed": """"),_display_(/*9.30*/Messages("ui.survey.submitfailed")),format.raw/*9.64*/("""",
	"ui.survey.custnumber": """"),_display_(/*10.28*/Messages("ui.survey.custnumber")),format.raw/*10.60*/("""",
	"ui.survey.waiting": """"),_display_(/*11.25*/Messages("ui.survey.waiting")),format.raw/*11.54*/(""""
"""),format.raw/*12.1*/("""}"""),format.raw/*12.2*/(""";
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/messages.scala.html
                  HASH: c98e14bccf19fb9f044c97c9a93af2e6a7f1be55
                  MATRIX: 778->30|867->32|894->33|968->80|996->81|1024->83|1077->110|1130->143|1191->178|1248->215|1314->255|1376->297|1437->332|1494->369|1552->401|1606->435|1663->465|1716->497|1770->524|1820->553|1849->555|1877->556
                  LINES: 26->2|29->2|30->3|31->4|31->4|32->5|32->5|32->5|33->6|33->6|34->7|34->7|35->8|35->8|36->9|36->9|37->10|37->10|38->11|38->11|39->12|39->12
                  -- GENERATED --
              */
          