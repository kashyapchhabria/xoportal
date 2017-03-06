
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
object disp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[List[com.xo.web.controllers.target],List[String],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(kpi_get:List[com.xo.web.controllers.target], target:List[String]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.68*/("""
"""),_display_(/*2.2*/for( widget <- kpi_get) yield /*2.25*/ {_display_(Seq[Any](format.raw/*2.27*/("""
    
    """),_display_(/*4.6*/if(widget.checked==1)/*4.27*/{_display_(Seq[Any](format.raw/*4.28*/("""
		"""),_display_(/*5.4*/if((target).contains(widget.name))/*5.38*/{_display_(Seq[Any](format.raw/*5.39*/("""
		"""),format.raw/*6.3*/("""<div class="ui checkbox" style="display: block !important;">
	       <input type="checkbox" class="kpi" id=""""),_display_(/*7.49*/widget/*7.55*/.name),format.raw/*7.60*/("""" onchange="check(this);" value=""""),_display_(/*7.94*/widget/*7.100*/.name),format.raw/*7.105*/("""" start=""""),_display_(/*7.115*/widget/*7.121*/.start),format.raw/*7.127*/("""" weightage=""""),_display_(/*7.141*/widget/*7.147*/.weight),format.raw/*7.154*/("""" checked>
		   <label>"""),_display_(/*8.14*/widget/*8.20*/.name),format.raw/*8.25*/("""</label><br>
		</div>
	   """)))}/*10.7*/else/*10.12*/{_display_(Seq[Any](format.raw/*10.13*/("""
	   """),format.raw/*11.5*/("""<div class="ui checkbox" style="display: block !important;">
	       <input type="checkbox" class="kpi" id=""""),_display_(/*12.49*/widget/*12.55*/.name),format.raw/*12.60*/("""" onchange="check(this);" value=""""),_display_(/*12.94*/widget/*12.100*/.name),format.raw/*12.105*/("""" start=""""),_display_(/*12.115*/widget/*12.121*/.start),format.raw/*12.127*/("""" weightage=""""),_display_(/*12.141*/widget/*12.147*/.weight),format.raw/*12.154*/("""">
		   <label>"""),_display_(/*13.14*/widget/*13.20*/.name),format.raw/*13.25*/("""</label><br>
		</div>
		""")))}),format.raw/*15.4*/("""
	""")))}),format.raw/*16.3*/("""
""")))}),format.raw/*17.2*/("""

"""))}
  }

  def render(kpi_get:List[com.xo.web.controllers.target],target:List[String]): play.twirl.api.HtmlFormat.Appendable = apply(kpi_get,target)

  def f:((List[com.xo.web.controllers.target],List[String]) => play.twirl.api.HtmlFormat.Appendable) = (kpi_get,target) => apply(kpi_get,target)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:03 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/disp.scala.html
                  HASH: adbf5ca6a8826e68b5f1cbfe0ac76eebeaeee438
                  MATRIX: 775->1|929->67|956->69|994->92|1033->94|1069->105|1098->126|1136->127|1165->131|1207->165|1245->166|1274->169|1409->278|1423->284|1448->289|1508->323|1523->329|1549->334|1586->344|1601->350|1628->356|1669->370|1684->376|1712->383|1762->407|1776->413|1801->418|1846->446|1859->451|1898->452|1930->457|2066->566|2081->572|2107->577|2168->611|2184->617|2211->622|2249->632|2265->638|2293->644|2335->658|2351->664|2380->671|2423->687|2438->693|2464->698|2519->723|2552->726|2584->728
                  LINES: 26->1|29->1|30->2|30->2|30->2|32->4|32->4|32->4|33->5|33->5|33->5|34->6|35->7|35->7|35->7|35->7|35->7|35->7|35->7|35->7|35->7|35->7|35->7|35->7|36->8|36->8|36->8|38->10|38->10|38->10|39->11|40->12|40->12|40->12|40->12|40->12|40->12|40->12|40->12|40->12|40->12|40->12|40->12|41->13|41->13|41->13|43->15|44->16|45->17
                  -- GENERATED --
              */
          