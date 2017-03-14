
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
object user_perm_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="user_perm_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2 data-bind="text:userpermmgnt.curUser().name + ' """),_display_(/*6.58*/Messages("user.pr.mgmt")),format.raw/*6.82*/("""'"></h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:userpermmgnt.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
		</span>
			<dl	class="sub-nav xo-nav">
				<dd class="active">
					<a title="Create a user permission" data-reveal-id="myModal" id="assignNewUserPermBut"
						data-bind='click:userpermmgnt.loadPopup'>"""),_display_(/*19.49*/Messages("user.pr.mgmt.newusrpr")),format.raw/*19.82*/("""</a>
					<div id="myModal"class="reveal-modal medium top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog" >
							<span>
								<div data-alert id="popup-box-container"
									class="padding5 small-8 large-6 small-centered columns alert-box alert round"
									data-bind="visible:userpermmgnt.visibility">
									<div id="popup-message" class="text-center fontBlack"></div>
								</div>
							</span>
						<table id="UnassignedUserPermissions">
								<thead>
									<tr>
										<th>Permissions</th>
										<th>Assign</th>
									</tr>
								</thead>
								<tbody
									data-bind="foreach: """),format.raw/*36.30*/("""{"""),format.raw/*36.31*/("""data:userpermmgnt.availableUnassignedPermissions, as: 'unassignedPermissionRecord'"""),format.raw/*36.113*/("""}"""),format.raw/*36.114*/("""">
									<tr>
										<td data-bind='text: unassignedPermissionRecord.name'></td>
										<td class="text-center popupheight"><input type="submit" class="small round button lightbluebutton popupheight" value="ADD" data-bind="click:unassignedPermissionRecord.updateUserPermission"/></td>
									</tr>
								</tbody>
							</table>
											
							
					 	 <a class="close-reveal-modal" aria-label="Close">&#215;</a>
					</div>
				</dd>
			</dl>
		</span>
			<table id="allAvailableUserPermissions">
				<thead>
					<tr>
						<th>"""),_display_(/*53.12*/Messages("user.pr.mgmt.name")),format.raw/*53.41*/("""</th>
						<th>"""),_display_(/*54.12*/Messages("user.pr.mgmt.action")),format.raw/*54.43*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*58.26*/("""{"""),format.raw/*58.27*/("""data:userpermmgnt.availableUserPermissions, as: 'userPermissionRecord'"""),format.raw/*58.97*/("""}"""),format.raw/*58.98*/("""">
					<tr>
						<td data-bind='text: userPermissionRecord.name'></td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: userPermissionRecord.active"><a
									title='"""),_display_(/*64.18*/Messages("user.pr.mgmt.dactusr")),format.raw/*64.50*/("""'
									data-bind='attr: """),format.raw/*65.27*/("""{"""),format.raw/*65.28*/("""id: "user_active_"+userPermissionRecord.userId+"_"+userPermissionRecord.permId"""),format.raw/*65.106*/("""}"""),format.raw/*65.107*/(""", click:userPermissionRecord.toggleUserPermissionStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: userPermissionRecord.active"><a
									title='"""),_display_(/*69.18*/Messages("user.pr.mgmt.actusr")),format.raw/*69.49*/("""'
									data-bind='attr: """),format.raw/*70.27*/("""{"""),format.raw/*70.28*/("""id: "user_active_"+userPermissionRecord.userId+"_"+userPermissionRecord.permId"""),format.raw/*70.106*/("""}"""),format.raw/*70.107*/(""", click:userPermissionRecord.toggleUserPermissionStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<li>
									<a  title='"""),_display_(/*74.22*/Messages("user.pr.mgmt.delusrpr")),format.raw/*74.55*/("""' data-bind='attr: """),format.raw/*74.74*/("""{"""),format.raw/*74.75*/("""id: "user_active_"+userPermissionRecord.userId+"_"+userPermissionRecord.permId"""),format.raw/*74.153*/("""}"""),format.raw/*74.154*/(""", click:userPermissionRecord.deleteUserPermission'>
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
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/user_perm_mgmt.scala.html
                  HASH: 6df9181bc551a616bed959850a631b56e7dca3ba
                  MATRIX: 818->0|997->153|1041->177|1572->681|1626->714|2303->1363|2332->1364|2443->1446|2473->1447|3048->1995|3098->2024|3142->2041|3194->2072|3288->2138|3317->2139|3415->2209|3444->2210|3663->2402|3716->2434|3772->2462|3801->2463|3908->2541|3938->2542|4156->2733|4208->2764|4264->2792|4293->2793|4400->2871|4430->2872|4601->3016|4655->3049|4702->3068|4731->3069|4838->3147|4868->3148
                  LINES: 29->1|34->6|34->6|47->19|47->19|64->36|64->36|64->36|64->36|81->53|81->53|82->54|82->54|86->58|86->58|86->58|86->58|92->64|92->64|93->65|93->65|93->65|93->65|97->69|97->69|98->70|98->70|98->70|98->70|102->74|102->74|102->74|102->74|102->74|102->74
                  -- GENERATED --
              */
          