
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
object user_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="user_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2>"""),_display_(/*6.10*/Messages("user.mgmt")),format.raw/*6.31*/("""</h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:usermanagement.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
		</span>

			<ul class="sub-nav xo-nav">
    			<li class="active">
					<a title='"""),_display_(/*19.17*/Messages("user.mgmt.newusr")),format.raw/*19.45*/("""' id="createNewUserBut" data-bind='click:usermanagement.newUser'>"""),_display_(/*19.111*/Messages("user.mgmt.newusr")),format.raw/*19.139*/("""</a>
				</li>
				<li class="active">
					<a title='"""),_display_(/*22.17*/Messages("user.mgmt.upload")),format.raw/*22.45*/("""' id="createNewUserBut_bulk" data-bind='click:usermanagement.bulkUploadUsers'>"""),_display_(/*22.124*/Messages("user.mgmt.upload")),format.raw/*22.152*/("""</a>
				</li>
  			</ul>
		</span>
			<table id="allAvailableUsers">
				<thead>
					<tr>
						<th>"""),_display_(/*29.12*/Messages("user.mgmt.name")),format.raw/*29.38*/("""</th>
						<th>"""),_display_(/*30.12*/Messages("user.mgmt.email")),format.raw/*30.39*/("""</th>
						<th>"""),_display_(/*31.12*/Messages("user.mgmt.lstapp")),format.raw/*31.40*/("""</th>
						<th>"""),_display_(/*32.12*/Messages("user.mgmt.action")),format.raw/*32.40*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*36.26*/("""{"""),format.raw/*36.27*/("""data:usermanagement.availableUsers, as: 'userRecord'"""),format.raw/*36.79*/("""}"""),format.raw/*36.80*/("""">
					<tr data-bind="ifnot: userRecord.deleted">
						<td data-bind='text: userRecord.name'></td>
						<td data-bind="text: userRecord.email"></td>
						<td data-bind="text: userRecord.lastLoginDt"></td>
						<td>
							<ul class="inline-list">
								<li><a title='"""),_display_(/*43.24*/Messages("user.mgmt.editusr")),format.raw/*43.53*/("""'
									data-bind='attr: """),format.raw/*44.27*/("""{"""),format.raw/*44.28*/("""id: "user_edit_"+userRecord.userId"""),format.raw/*44.62*/("""}"""),format.raw/*44.63*/(""", click:userRecord.loadUser'>
										<i class="fi-pencil"></i>
								</a></li>
								<li data-bind="if: userRecord.active"><a
									title='"""),_display_(/*48.18*/Messages("user.mgmt.dactusr")),format.raw/*48.47*/("""'
									data-bind='attr: """),format.raw/*49.27*/("""{"""),format.raw/*49.28*/("""id: "user_active_"+userRecord.userId"""),format.raw/*49.64*/("""}"""),format.raw/*49.65*/(""", click:userRecord.toggleStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: userRecord.active"><a
									title='"""),_display_(/*53.18*/Messages("user.mgmt.actusr")),format.raw/*53.46*/("""'
									data-bind='attr: """),format.raw/*54.27*/("""{"""),format.raw/*54.28*/("""id: "user_active_"+userRecord.userId"""),format.raw/*54.64*/("""}"""),format.raw/*54.65*/(""", click:userRecord.toggleStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<li><a title='"""),_display_(/*57.24*/Messages("user.mgmt.resetpass")),format.raw/*57.55*/("""'
									data-bind='attr: """),format.raw/*58.27*/("""{"""),format.raw/*58.28*/("""id: "user_repass_"+userRecord.userId"""),format.raw/*58.64*/("""}"""),format.raw/*58.65*/(""", click:userRecord.resetpassword'>
										<i class="fi-refresh"></i>
								</a></li>
								<li><a title='"""),_display_(/*61.24*/Messages("user.mgmt.roles")),format.raw/*61.51*/("""'
									data-bind='attr: """),format.raw/*62.27*/("""{"""),format.raw/*62.28*/("""id: "user_repass_"+userRecord.userId"""),format.raw/*62.64*/("""}"""),format.raw/*62.65*/(""", click:userRecord.showUserRoles'>
										<i class="fi-thumbnails"></i>
								</a></li>
								<li><a title='"""),_display_(/*65.24*/Messages("user.mgmt.permission")),format.raw/*65.56*/("""'
									data-bind='attr: """),format.raw/*66.27*/("""{"""),format.raw/*66.28*/("""id: "user_perm_"+userRecord.userId"""),format.raw/*66.62*/("""}"""),format.raw/*66.63*/(""", click:userRecord.showUserPermissions'>
										<i class="fi-checkbox"></i>
								</a></li>
								<!--<li>
									<a  title='"""),_display_(/*70.22*/Messages("user.mgmt.delusr")),format.raw/*70.50*/("""' data-bind='attr: """),format.raw/*70.69*/("""{"""),format.raw/*70.70*/("""id: "user_delete_"+userRecord.userId"""),format.raw/*70.106*/("""}"""),format.raw/*70.107*/(""", click:userRecord.deleteUser'>
										<i class="fi-trash"></i>
									</a>
								</li> -->
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/user_mgmt.scala.html
                  HASH: 2fbfb478fca9deab5cf940c662c2a1933797b8b6
                  MATRIX: 813->0|939->100|980->121|1390->504|1439->532|1533->598|1583->626|1665->681|1714->709|1821->788|1871->816|2001->919|2048->945|2092->962|2140->989|2184->1006|2233->1034|2277->1051|2326->1079|2420->1145|2449->1146|2529->1198|2558->1199|2860->1474|2910->1503|2966->1531|2995->1532|3057->1566|3086->1567|3263->1717|3313->1746|3369->1774|3398->1775|3462->1811|3491->1812|3675->1969|3724->1997|3780->2025|3809->2026|3873->2062|3902->2063|4038->2172|4090->2203|4146->2231|4175->2232|4239->2268|4268->2269|4408->2382|4456->2409|4512->2437|4541->2438|4605->2474|4634->2475|4777->2591|4830->2623|4886->2651|4915->2652|4977->2686|5006->2687|5168->2822|5217->2850|5264->2869|5293->2870|5358->2906|5388->2907
                  LINES: 29->1|34->6|34->6|47->19|47->19|47->19|47->19|50->22|50->22|50->22|50->22|57->29|57->29|58->30|58->30|59->31|59->31|60->32|60->32|64->36|64->36|64->36|64->36|71->43|71->43|72->44|72->44|72->44|72->44|76->48|76->48|77->49|77->49|77->49|77->49|81->53|81->53|82->54|82->54|82->54|82->54|85->57|85->57|86->58|86->58|86->58|86->58|89->61|89->61|90->62|90->62|90->62|90->62|93->65|93->65|94->66|94->66|94->66|94->66|98->70|98->70|98->70|98->70|98->70|98->70
                  -- GENERATED --
              */
          