
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
object row_level_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="rowlevelpermission_mgmt" type="text/html">
	<section>
		<div class="row" data-equivalizer>
			<div class="small-10 small-centered columns">
				<br/>
				<fieldset class="small-block-grid-2 medium-block-grid-4  panel callout radious">
						<legend class="fi-key">&nbsp;"""),_display_(/*7.37*/Messages("admin.land.row-level")),format.raw/*7.69*/("""</legend>
						<ul class="small-block-grid-2 medium-block-grid-4">
							<li class ="text-center" data-equalizer-watch><a href="#userrowlevelpermission"><i class="fi-results-demographics text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*9.178*/Messages("admin.land.userrow-level")),format.raw/*9.214*/("""</span></a></li>
							<li class="text-center" data-equalizer-watch><a href="#rolerowlevelpermission"><i class="fi-torso-business text-center xo-icon"></i><br><span class="text-center">"""),_display_(/*10.171*/Messages("admin.land.rolerow-level")),format.raw/*10.207*/("""</span></a></li>
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
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/row_level_mgmt.scala.html
                  HASH: 0bff18072a44a92f11942aa553f9c8cba01fa801
                  MATRIX: 818->0|1127->283|1179->315|1451->560|1508->596|1723->783|1781->819
                  LINES: 29->1|35->7|35->7|37->9|37->9|38->10|38->10
                  -- GENERATED --
              */
          