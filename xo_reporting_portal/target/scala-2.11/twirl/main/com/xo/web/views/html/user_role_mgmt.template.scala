
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
object user_role_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="user_role_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2 data-bind="text:userrolemgnt.curUser().name + ' """),_display_(/*6.58*/Messages("user.rl.mgmt")),format.raw/*6.82*/("""'"></h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:userrolemgnt.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
		</span>
			<dl	class="sub-nav xo-nav">
				<dd class="active">
					<a title="Create a new user" data-reveal-id="myModal" id="assignNewUserRoleBut"
						data-bind='click:userrolemgnt.loadPopup'>"""),_display_(/*19.49*/Messages("user.rl.mgmt.newusrrl")),format.raw/*19.82*/("""</a>
					<div id="myModal"class="reveal-modal small top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog" >				
							<span>
								<div data-alert id="popup-box-container"
									class="padding5 small-8 large-6 small-centered columns alert-box alert round"
									data-bind="visible:userrolemgnt.visibility">
									<div id="popup-message" class="text-center fontBlack"></div>
								</div>
							</span>
						<table id="UnassignedUserRoles">
								<thead>
									<tr>
										<th class="width65" >Roles</th>
										<th class="text-center">Assign</th>
									</tr>
								</thead>
								<tbody
									data-bind="foreach: """),format.raw/*36.30*/("""{"""),format.raw/*36.31*/("""data:userrolemgnt.availableUnassignedRoles, as: 'unassignedRoleRecord'"""),format.raw/*36.101*/("""}"""),format.raw/*36.102*/("""">
									<tr>
										<td data-bind='text: unassignedRoleRecord.name'></td>
										<td class="text-center">
										<input type="submit" class="small round button lightbluebutton popupheight" value="ADD" data-bind="click:unassignedRoleRecord.updateUserRole"/>
										</td>
									</tr>
								</tbody>
							</table>
											
							
					 	 <a class="close-reveal-modal" aria-label="Close">&#215;</a>
					</div>
				</dd>
			</dl>
		</span>
			<table id="allAvailableUserRoles">
				<thead>
					<tr>
						<th>"""),_display_(/*55.12*/Messages("user.rl.mgmt.name")),format.raw/*55.41*/("""</th>
						<th>"""),_display_(/*56.12*/Messages("user.rl.mgmt.action")),format.raw/*56.43*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*60.26*/("""{"""),format.raw/*60.27*/("""data:userrolemgnt.availableUserRoles, as: 'userRoleRecord'"""),format.raw/*60.85*/("""}"""),format.raw/*60.86*/("""">
					<tr>
						<td data-bind='text: userRoleRecord.name'></td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: userRoleRecord.active"><a
									title='"""),_display_(/*66.18*/Messages("user.rl.mgmt.dactusr")),format.raw/*66.50*/("""'
									data-bind='attr: """),format.raw/*67.27*/("""{"""),format.raw/*67.28*/("""id: "user_active_"+userRoleRecord.userId+"_"+userRoleRecord.roleId"""),format.raw/*67.94*/("""}"""),format.raw/*67.95*/(""", click:userRoleRecord.toggleUserRoleStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: userRoleRecord.active"><a
									title='"""),_display_(/*71.18*/Messages("user.rl.mgmt.actusr")),format.raw/*71.49*/("""'
									data-bind='attr: """),format.raw/*72.27*/("""{"""),format.raw/*72.28*/("""id: "user_active_"+userRoleRecord.userId+"_"+userRoleRecord.roleId"""),format.raw/*72.94*/("""}"""),format.raw/*72.95*/(""", click:userRoleRecord.toggleUserRoleStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<li>
									<a  title='"""),_display_(/*76.22*/Messages("user.rl.mgmt.delusrrl")),format.raw/*76.55*/("""' data-bind='attr: """),format.raw/*76.74*/("""{"""),format.raw/*76.75*/("""id: "user_active_"+userRoleRecord.userId+"_"+userRoleRecord.roleId"""),format.raw/*76.141*/("""}"""),format.raw/*76.142*/(""", click:userRoleRecord.deleteUserRole'>
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
</script>"""))}
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/user_role_mgmt.scala.html
                  HASH: 8d416121c075a7369a7c1700c64c735e33f4d2b0
                  MATRIX: 818->0|997->153|1041->177|1565->674|1619->707|2324->1384|2353->1385|2452->1455|2482->1456|3043->1990|3093->2019|3137->2036|3189->2067|3283->2133|3312->2134|3398->2192|3427->2193|3634->2373|3687->2405|3743->2433|3772->2434|3866->2500|3895->2501|4095->2674|4147->2705|4203->2733|4232->2734|4326->2800|4355->2801|4514->2933|4568->2966|4615->2985|4644->2986|4739->3052|4769->3053
                  LINES: 29->1|34->6|34->6|47->19|47->19|64->36|64->36|64->36|64->36|83->55|83->55|84->56|84->56|88->60|88->60|88->60|88->60|94->66|94->66|95->67|95->67|95->67|95->67|99->71|99->71|100->72|100->72|100->72|100->72|104->76|104->76|104->76|104->76|104->76|104->76
                  -- GENERATED --
              */
          