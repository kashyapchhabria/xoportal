
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
object useradmin_controls extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="admin-template" type="text/html">
<section>
		<div class="row" data-equivalizer>
  			<div class="small-10 small-centered columns">
				<br/>
				<fieldset class="small-block-grid-2 medium-block-grid-4 panel callout radious">
						<legend class="fi-key">&nbsp;"""),_display_(/*7.37*/Messages("admin.land.security")),format.raw/*7.68*/("""</legend>
						<ul class="small-block-grid-2 medium-block-grid-4">
							<li class ="text-center" data-equalizer-watch><a href="#usermanagement"><i class="fi-torsos-all text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*9.160*/Messages("admin.land.usermgmt")),format.raw/*9.191*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#rolemanagement"><i class="fi-social-myspace text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*10.164*/Messages("admin.land.role")),format.raw/*10.191*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#permissionmanagement"><i class="fi-key text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*11.159*/Messages("admin.land.permission")),format.raw/*11.192*/("""</span></a></li>					
	                    	<li class ="text-center" data-equalizer-watch><a href="#rowlevelpermission"><i class="fi-indent-more text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*12.180*/Messages("admin.land.row-level")),format.raw/*12.212*/("""</span></a></li>
						</ul>
				</fieldset>
				<br/>
				<fieldset class="small-block-grid-2 medium-block-grid-4  panel callout radious">
						<legend class="fi-wrench">&nbsp;"""),_display_(/*17.40*/Messages("admin.land.portalapp")),format.raw/*17.72*/("""</legend>
						<ul class="small-block-grid-2 medium-block-grid-4">
							<li class ="text-center" data-equalizer-watch><a href="#systemsettings"><i class="fi-widget text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*19.156*/Messages("admin.land.settings")),format.raw/*19.187*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#tabdashboard"><i class="fi-graph-trend text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*20.159*/Messages("admin.land.tableaumgmt")),format.raw/*20.193*/("""</span></a></li>
							<li class ="text-center" data-equalizer-watch><a href="#diffusionmap"><i class="fi-graph-trend text-center xo-icon"></i><br><span class="text-center">Diffusion Map</span></a></li>
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
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/useradmin_controls.scala.html
                  HASH: f01b37faa17086908e8d3664f46bfe803765b982
                  MATRIX: 822->0|1122->274|1173->305|1427->532|1479->563|1687->743|1736->770|1939->945|1994->978|2223->1179|2277->1211|2483->1390|2536->1422|2787->1645|2840->1676|3043->1851|3099->1885
                  LINES: 29->1|35->7|35->7|37->9|37->9|38->10|38->10|39->11|39->11|40->12|40->12|45->17|45->17|47->19|47->19|48->20|48->20
                  -- GENERATED --
              */
          