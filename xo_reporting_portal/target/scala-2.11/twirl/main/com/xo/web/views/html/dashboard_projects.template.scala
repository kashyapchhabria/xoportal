
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
object dashboard_projects extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.4*/("""
"""),format.raw/*2.1*/("""<script id="menuTree" type="text/html">
	<li data-bind= 'attr: """),format.raw/*3.24*/("""{"""),format.raw/*3.25*/(""" """),format.raw/*3.26*/("""class: (subMenuItems().length > 0 ? "has-dropdown not-click" : "")"""),format.raw/*3.92*/("""}"""),format.raw/*3.93*/("""'>
		<a data-bind='text:name, click:loadPageData'></a>
		<ul class="dropdown" data-bind="template: """),format.raw/*5.45*/("""{"""),format.raw/*5.46*/(""" """),format.raw/*5.47*/("""name: 'menuTree', foreach: subMenuItems"""),format.raw/*5.86*/("""}"""),format.raw/*5.87*/("""" ></ul>
	</li>
</script>
<script id="tab_dashboard" type="text/html">
	<section id="tableauReportsPageSection" style="background-color: white;">
		<a style="visibility: hidden;" id="getFilters" class="button" data-bind="click:tableaumgmt.showPopup">Target Provisioning</a>
		<center data-bind="visible:(!tableaumgmt.errorText() || tableaumgmt.errorText().length <= 0)" >
			<img style="border: 0px" data-bind='visible: (tableaumgmt.placeHolderImageUrl().length > 0), attr: """),format.raw/*12.103*/("""{"""),format.raw/*12.104*/("""src: tableaumgmt.placeHolderImageUrl"""),format.raw/*12.140*/("""}"""),format.raw/*12.141*/("""'></img>
			<div id="tableauViewPlace" data-bind='attr:"""),format.raw/*13.47*/("""{"""),format.raw/*13.48*/("""style:(tableaumgmt.isTopBarVisibile() ? "background-color:white;margin-top:3px;" : "background-color:white;margin-top:-45px;")"""),format.raw/*13.174*/("""}"""),format.raw/*13.175*/("""'></div>
		</center>
		<center data-bind="visible:(tableaumgmt.errorText() && tableaumgmt.errorText().length > 0)" >
			<span class="alert">
				<h3>Report Loading Error:</h3>
				<p data-bind="text:tableaumgmt.errorText"></p>
				
			</span>
		</center>
		<div id="myModal" class="reveal-modal" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
			<div style='color:black'>
				<p>
					<b>Segment Name: </b><i data-bind="text:tableaumgmt.segment()" ></i><br>
					<b>Sub Segments: </b><i data-bind="text:tableaumgmt.subSegment()" ></i><br>
					<b>Regions: </b><i data-bind="text:tableaumgmt.measureList()" ></i>
				</p>
				<center><a data-bind='click:tableaumgmt.getMsisdns' class='button small'>Confirm</a></center>
				<a class='close-reveal-modal' aria-label='Close'>&#215;</a>
			</div>
		</div>
		<div id="appliedFilters"></div>
	</section>
</script>
"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:03 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/dashboard_projects.scala.html
                  HASH: 908556836928b556e47c98940df37cf1b6d66681
                  MATRIX: 740->1|829->3|856->4|946->67|974->68|1002->69|1095->135|1123->136|1249->235|1277->236|1305->237|1371->276|1399->277|1902->751|1932->752|1997->788|2027->789|2110->844|2139->845|2294->971|2324->972
                  LINES: 26->1|29->1|30->2|31->3|31->3|31->3|31->3|31->3|33->5|33->5|33->5|33->5|33->5|40->12|40->12|40->12|40->12|41->13|41->13|41->13|41->13
                  -- GENERATED --
              */
          