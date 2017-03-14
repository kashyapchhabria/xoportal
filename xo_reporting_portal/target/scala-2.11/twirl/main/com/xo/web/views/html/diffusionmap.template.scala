
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
object diffusionmap extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="diffusion_map" type="text/html">
<!-- 
<ul class="large-block-grid-3">
	<li>1</li>
	<li>2</li>
	<li>3</li>
	<li>4</li>
	<li>5</li>
	<li>6</li>
</ul> -->
	<section id="tableauReportsPageSection" style="background-color: white;">
		<div style="margin-top:40px">
			<div class="large-2 columns end">
				<div class="row collapse">
            		<div class="small-10 columns">
						<dl class="dropdown"> 
							<dt>
								<a data-bind="click:tableaumgmt.toggleClass">
									Select &#x25BC;
									<p class="multiSel"></p>  
								</a>
							</dt>
							<dd>
								<div class="mutliSelect" >
									<ul>
										<li style="list-style: none;">
											<label>
		        								<input type="checkbox" data-bind=" checked: tableaumgmt.isAllSelected" />
		        								<span>All</span>
		        							</label>
		    							</li>
									<!-- ko foreach: """),format.raw/*32.27*/("""{"""),format.raw/*32.28*/(""" """),format.raw/*32.29*/("""data: tableaumgmt.filterList ,as:'sub_sgmt' """),format.raw/*32.73*/("""}"""),format.raw/*32.74*/(""" """),format.raw/*32.75*/("""-->
										<li style="list-style: none;">
											<label>
		        								<input type="checkbox" data-bind="attr:"""),format.raw/*35.58*/("""{"""),format.raw/*35.59*/("""disabled:sub_sgmt.isDisabled,value: sub_sgmt.name"""),format.raw/*35.108*/("""}"""),format.raw/*35.109*/(""",  checked: sub_sgmt.isChecked" />
		        								<span data-bind="text:sub_sgmt.name"></span>
		        							</label>
		    							</li>
									<!-- /ko -->
									</ul>
								</div>
							</dd>
						</dl>
            		</div>
            		<div class="small-2 columns">
              			<a data-bind="click:tableaumgmt.getSelectedFilters" class="button postfix">Go</a>
            		</div>
          		</div>
			</div>
		</div>
		<center data-bind="visible:(!tableaumgmt.errorText() || tableaumgmt.errorText().length <= 0)" >
			<img style="border: 0px" data-bind='visible: (tableaumgmt.placeHolderImageUrl().length > 0), attr: """),format.raw/*52.103*/("""{"""),format.raw/*52.104*/("""src: tableaumgmt.placeHolderImageUrl"""),format.raw/*52.140*/("""}"""),format.raw/*52.141*/("""'></img>
			<div id="tableauViewPlace" data-bind='attr:"""),format.raw/*53.47*/("""{"""),format.raw/*53.48*/("""style:(tableaumgmt.isTopBarVisibile() ? "background-color:white;margin-top:3px;" : "background-color:white;margin-top:-45px;")"""),format.raw/*53.174*/("""}"""),format.raw/*53.175*/("""'></div>
		</center>
		<center data-bind="visible:(tableaumgmt.errorText() && tableaumgmt.errorText().length > 0)" >
			<span class="alert">
				<h3>Report Loading Error:</h3>
				<p data-bind="text:tableaumgmt.errorText"></p>
			</span>
		</center>
	</section>
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/diffusionmap.scala.html
                  HASH: 0c96d6068beda7aef51567fa9373f2fab1ae4200
                  MATRIX: 816->0|1730->886|1759->887|1788->888|1860->932|1889->933|1918->934|2067->1055|2096->1056|2174->1105|2204->1106|2876->1749|2906->1750|2971->1786|3001->1787|3084->1842|3113->1843|3268->1969|3298->1970
                  LINES: 29->1|60->32|60->32|60->32|60->32|60->32|60->32|63->35|63->35|63->35|63->35|80->52|80->52|80->52|80->52|81->53|81->53|81->53|81->53
                  -- GENERATED --
              */
          