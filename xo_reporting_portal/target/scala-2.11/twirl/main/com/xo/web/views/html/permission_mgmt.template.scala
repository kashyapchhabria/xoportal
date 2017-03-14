
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
object permission_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="permission_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2>"""),_display_(/*6.10*/Messages("permission.mgmt")),format.raw/*6.37*/("""</h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:permissionmanagement.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
			<table id="allAvailablePermissions">
				<thead>
					<tr>
						<th>"""),_display_(/*18.12*/Messages("permission.mgmt.name")),format.raw/*18.44*/("""</th>
						<th>"""),_display_(/*19.12*/Messages("permission.mgmt.description")),format.raw/*19.51*/("""</th>
						<th>"""),_display_(/*20.12*/Messages("permission.mgmt.action")),format.raw/*20.46*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*24.26*/("""{"""),format.raw/*24.27*/("""data:permissionmanagement.availablePermissions, as: 'permissionRecord'"""),format.raw/*24.97*/("""}"""),format.raw/*24.98*/("""">
					<tr data-bind="ifnot: permissionRecord.deleted">
						<td data-bind='text: permissionRecord.name'></td>
						<td data-bind="text: permissionRecord.description"></td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: permissionRecord.active"><a
									title='"""),_display_(/*31.18*/Messages("permission.mgmt.dactprm")),format.raw/*31.53*/("""'
									data-bind='attr: """),format.raw/*32.27*/("""{"""),format.raw/*32.28*/("""id: "permission_active_"+permissionRecord.permissionId"""),format.raw/*32.82*/("""}"""),format.raw/*32.83*/(""", click:permissionRecord.toggleStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: permissionRecord.active"><a
									title='"""),_display_(/*36.18*/Messages("permission.mgmt.actprm")),format.raw/*36.52*/("""'
									data-bind='attr: """),format.raw/*37.27*/("""{"""),format.raw/*37.28*/("""id: "permission_active_"+permissionRecord.permissionId"""),format.raw/*37.82*/("""}"""),format.raw/*37.83*/(""", click:permissionRecord.toggleStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<!--<li><a title='"""),_display_(/*40.28*/Messages("permission.mgmt.resetpass")),format.raw/*40.65*/("""'
									data-bind='attr: """),format.raw/*41.27*/("""{"""),format.raw/*41.28*/("""id: "permission_repass_"+permissionRecord.permissionId"""),format.raw/*41.82*/("""}"""),format.raw/*41.83*/(""", click:permissionRecord.resetpassword'>
										<i class="fi-refresh"></i>
								</a></li>
								<li><a title='"""),_display_(/*44.24*/Messages("permission.mgmt.roles")),format.raw/*44.57*/("""'
									data-bind='attr: """),format.raw/*45.27*/("""{"""),format.raw/*45.28*/("""id: "permission_repass_"+permissionRecord.permissionId"""),format.raw/*45.82*/("""}"""),format.raw/*45.83*/(""", click:permissionRecord.showPermissionRoles'>
										<i class="fi-thumbnails"></i>
								</a></li>
								<li>
									<a  title='"""),_display_(/*49.22*/Messages("permission.mgmt.delprm")),format.raw/*49.56*/("""' data-bind='attr: """),format.raw/*49.75*/("""{"""),format.raw/*49.76*/("""id: "permission_delete_"+permissionRecord.permissionId"""),format.raw/*49.130*/("""}"""),format.raw/*49.131*/(""", click:permissionRecord.deletePermission'>
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
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/permission_mgmt.scala.html
                  HASH: 84d40f6e2fb55a9bef03057db23502f92373db05
                  MATRIX: 819->0|951->106|998->133|1402->510|1455->542|1499->559|1559->598|1603->615|1658->649|1752->715|1781->716|1879->786|1908->787|2226->1078|2282->1113|2338->1141|2367->1142|2449->1196|2478->1197|2674->1366|2729->1400|2785->1428|2814->1429|2896->1483|2925->1484|3071->1603|3129->1640|3185->1668|3214->1669|3296->1723|3325->1724|3471->1843|3525->1876|3581->1904|3610->1905|3692->1959|3721->1960|3887->2099|3942->2133|3989->2152|4018->2153|4101->2207|4131->2208
                  LINES: 29->1|34->6|34->6|46->18|46->18|47->19|47->19|48->20|48->20|52->24|52->24|52->24|52->24|59->31|59->31|60->32|60->32|60->32|60->32|64->36|64->36|65->37|65->37|65->37|65->37|68->40|68->40|69->41|69->41|69->41|69->41|72->44|72->44|73->45|73->45|73->45|73->45|77->49|77->49|77->49|77->49|77->49|77->49
                  -- GENERATED --
              */
          