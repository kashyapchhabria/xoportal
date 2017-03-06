
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
object role_permission_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="role_perm_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2 data-bind="text:rolespermissionmgmt.curRole().name + ' """),_display_(/*6.65*/Messages("role.perm.mgmt")),format.raw/*6.91*/("""'"></h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:rolespermissionmgmt.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
		</span>
			<dl class="sub-nav xo-nav">
				<dd class="active">
					<a title="Create a new permission" id="assignNewRolePermissionBut" data-reveal-id="permissionAssignModel" data-bind='click:rolespermissionmgmt.loadPopup'>"""),_display_(/*18.161*/Messages("role.perm.mgmt.newperm")),format.raw/*18.195*/("""</a>
                    <div id="permissionAssignModel" class="reveal-modal medium top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
                    		<span>
								<div data-alert id="popup-box-container"
									class="padding5 small-8 large-6 small-centered columns alert-box alert round"
									data-bind="visible:rolespermissionmgmt.visibility">
									<div id="popup-message" class="text-center fontBlack"></div>
								</div>
							</span>

                        <table id="allAvailablePermissions_pop">
                            <thead>
                            <tr>
                                <th>"""),_display_(/*31.38*/Messages("role.perm.mgmt.ungranted.name")),format.raw/*31.79*/("""</th>
                                <th>"""),_display_(/*32.38*/Messages("role.perm.mgmt.action")),format.raw/*32.71*/("""</th>
                            </tr>
                            </thead>
                            <tbody data-bind="foreach: """),format.raw/*35.56*/("""{"""),format.raw/*35.57*/("""data:rolespermissionmgmt.availablePermissions, as: 'permissionRecord'"""),format.raw/*35.126*/("""}"""),format.raw/*35.127*/("""">
                            <tr data-bind="ifnot: permissionRecord.deleted" >
                                <td data-bind='text: permissionRecord.name'></td>
                                <td class="text-center popupheight" ><input type="submit" class="small round button popupheight lightbluebutton" value="ADD" data-bind="click:permissionRecord.updateRolePermission"/></td>
                            </tr>
                            </tbody>
                        </table>

                        <a class="close-reveal-modal" aria-label="Close">&#215;</a>
                    </div>

                </dd>
			</dl>
		</span>
			<table id="allAvailablePermissions">
				<thead>
					<tr>
						<th>"""),_display_(/*52.12*/Messages("role.perm.mgmt.name")),format.raw/*52.43*/("""</th>
						<th>"""),_display_(/*53.12*/Messages("role.perm.mgmt.action")),format.raw/*53.45*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*57.26*/("""{"""),format.raw/*57.27*/("""data:rolespermissionmgmt.availableRolesPermission, as: 'rolePermRecord'"""),format.raw/*57.98*/("""}"""),format.raw/*57.99*/("""">
					<tr>
						<td data-bind='text: rolePermRecord.p_name'></td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: rolePermRecord.active"><a
									title='"""),_display_(/*63.18*/Messages("role.perm.mgmt.dactusr")),format.raw/*63.52*/("""'
									data-bind='attr: """),format.raw/*64.27*/("""{"""),format.raw/*64.28*/("""id: "perm_active_"+rolePermRecord.roleId+"_"+rolePermRecord.permissionId"""),format.raw/*64.100*/("""}"""),format.raw/*64.101*/(""", click:rolePermRecord.toggleRolePermissionStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: rolePermRecord.active"><a
									title='"""),_display_(/*68.18*/Messages("role.perm.mgmt.actusr")),format.raw/*68.51*/("""'
									data-bind='attr: """),format.raw/*69.27*/("""{"""),format.raw/*69.28*/("""id: "perm_active_"+rolePermRecord.roleId+"_"+rolePermRecord.permissionId"""),format.raw/*69.100*/("""}"""),format.raw/*69.101*/(""", click:rolePermRecord.toggleRolePermissionStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<li>
									<a  title='"""),_display_(/*73.22*/Messages("role.perm.mgmt.delusrrl")),format.raw/*73.57*/("""' data-bind='attr: """),format.raw/*73.76*/("""{"""),format.raw/*73.77*/("""id: "user_active_"+rolePermRecord.roleId+"_"+rolePermRecord.permissionId"""),format.raw/*73.149*/("""}"""),format.raw/*73.150*/(""", click:rolePermRecord.deleteRolePermission'>
										<i class="fi-trash"></i>
									</a>
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
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
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/role_permission_mgmt.scala.html
                  HASH: cbfba1ae53cdb6a2ebe4d2d22261e0b03cc8df0b
                  MATRIX: 824->0|1010->160|1056->186|1615->717|1671->751|2362->1415|2424->1456|2494->1499|2548->1532|2708->1664|2737->1665|2835->1734|2865->1735|3606->2449|3658->2480|3702->2497|3756->2530|3850->2596|3879->2597|3978->2668|4007->2669|4216->2851|4271->2885|4327->2913|4356->2914|4457->2986|4487->2987|4693->3166|4747->3199|4803->3227|4832->3228|4933->3300|4963->3301|5128->3439|5184->3474|5231->3493|5260->3494|5361->3566|5391->3567
                  LINES: 29->1|34->6|34->6|46->18|46->18|59->31|59->31|60->32|60->32|63->35|63->35|63->35|63->35|80->52|80->52|81->53|81->53|85->57|85->57|85->57|85->57|91->63|91->63|92->64|92->64|92->64|92->64|96->68|96->68|97->69|97->69|97->69|97->69|101->73|101->73|101->73|101->73|101->73|101->73
                  -- GENERATED --
              */
          