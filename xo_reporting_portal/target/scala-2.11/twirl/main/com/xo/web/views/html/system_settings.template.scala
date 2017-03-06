
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
object system_settings extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="system_settings" type="text/html">
	<section>
		<div class="row" data-equivalizer>
			<div class="small-10 small-centered columns">
				<br/>
				<fieldset class="small-block-grid-2 medium-block-grid-4  panel callout radious">
						<legend class="fi-wrench">&nbsp;"""),_display_(/*7.40*/Messages("admin.land.settings")),format.raw/*7.71*/("""</legend>
						<ul class="small-block-grid-2 medium-block-grid-4">
							<li class ="text-center" data-equalizer-watch><a href="#configtemplate"><i class="fi-page-multiple text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*9.163*/Messages("admin.land.configs")),format.raw/*9.193*/("""</span></a></li>
							<li class="text-center" data-equalizer-watch><a href="#clientjobconfig"><i class="fi-database text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*10.158*/Messages("admin.land.clientjobconfigs")),format.raw/*10.197*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#reportmgmt"><i class="fi-graph-pie text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*11.155*/Messages("admin.land.report.settings")),format.raw/*11.193*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#reportgrpmgmt"><i class="fi-layout text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*12.155*/Messages("admin.land.report.group.settings")),format.raw/*12.199*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#configkpi"><i class="fi-graph-pie text-center xo-icon"></i><br><span class="text-center">Configure KPI Target</span></a></li>
						</ul>
				</fieldset>
			</div>
		</div>
	</section>
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/system_settings.scala.html
                  HASH: 975f9bc7f37a6544b2731db35bdcde42bd62396e
                  MATRIX: 819->0|1123->278|1174->309|1431->539|1482->569|1684->743|1745->782|1944->953|2004->991|2203->1162|2269->1206
                  LINES: 29->1|35->7|35->7|37->9|37->9|38->10|38->10|39->11|39->11|40->12|40->12
                  -- GENERATED --
              */
          