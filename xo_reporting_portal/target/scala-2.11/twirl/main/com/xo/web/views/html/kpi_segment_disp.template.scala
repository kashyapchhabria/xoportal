
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
object kpi_segment_disp extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[String,List[com.xo.web.controllers.kpi_get],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(kpi_selected:String,kpi_get_target:List[com.xo.web.controllers.kpi_get]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.75*/("""
"""),_display_(/*2.2*/if(kpi_get_target.size!=0)/*2.28*/{_display_(Seq[Any](format.raw/*2.29*/("""
    """),format.raw/*3.5*/("""<h3>Segment With: """),_display_(/*3.24*/kpi_selected),format.raw/*3.36*/("""</h3>
    <table class="ui celled table tablet stackable">
        <thead>
        	<tr>
        	    <th>KPI Name</th>
        	    <th>Segment</th>
        		<th>Start Value</th>
        		<th>Weightage</th>					    		
          	</tr>
        </thead>
        <tbody>
        	"""),_display_(/*14.11*/for( widget <- kpi_get_target) yield /*14.41*/ {_display_(Seq[Any](format.raw/*14.43*/("""
        	    """),format.raw/*15.14*/("""<tr>
              		<td>"""),_display_(/*16.22*/widget/*16.28*/.kpi_name),format.raw/*16.37*/("""</td>    	    	
            	    <td>"""),_display_(/*17.23*/widget/*17.29*/.segment),format.raw/*17.37*/("""</td>
              		<td>"""),_display_(/*18.22*/widget/*18.28*/.kpi_start),format.raw/*18.38*/("""</td>
              		<td>"""),_display_(/*19.22*/widget/*19.28*/.kpi_weight),format.raw/*19.39*/("""</td>
                </tr>              		
            """)))}),format.raw/*21.14*/("""
        """),format.raw/*22.9*/("""</tbody>
    </tabel>
""")))}/*24.2*/else/*24.6*/{_display_(Seq[Any](format.raw/*24.7*/("""
"""),format.raw/*25.1*/("""null
""")))}))}
  }

  def render(kpi_selected:String,kpi_get_target:List[com.xo.web.controllers.kpi_get]): play.twirl.api.HtmlFormat.Appendable = apply(kpi_selected,kpi_get_target)

  def f:((String,List[com.xo.web.controllers.kpi_get]) => play.twirl.api.HtmlFormat.Appendable) = (kpi_selected,kpi_get_target) => apply(kpi_selected,kpi_get_target)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/kpi_segment_disp.scala.html
                  HASH: 038348a3cba7d00770d56cd3e124fe7f2011cbe6
                  MATRIX: 782->1|943->74|970->76|1004->102|1042->103|1073->108|1118->127|1150->139|1458->420|1504->450|1544->452|1586->466|1639->492|1654->498|1684->507|1749->545|1764->551|1793->559|1847->586|1862->592|1893->602|1947->629|1962->635|1994->646|2082->703|2118->712|2159->735|2171->739|2209->740|2237->741
                  LINES: 26->1|29->1|30->2|30->2|30->2|31->3|31->3|31->3|42->14|42->14|42->14|43->15|44->16|44->16|44->16|45->17|45->17|45->17|46->18|46->18|46->18|47->19|47->19|47->19|49->21|50->22|52->24|52->24|52->24|53->25
                  -- GENERATED --
              */
          