
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
object role_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="role_mgmt" type="text/html">
<section>
	<div class="row">
				<span>
    			 <center>
					<h2>"""),_display_(/*6.11*/Messages("role.mgmt")),format.raw/*6.32*/("""</h2></center><br><br>
					<span>
						<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible:rolemanagement.visibility">
							<div id="alert-message" class="text-center fontBlack"></div>
						</div>
					</span>
					<dl class="sub-nav xo-nav">
    					<dd class="active">
							<a title="Create a new role" id="createNewRoleBut" data-bind='click:rolemanagement.newRole'>"""),_display_(/*14.101*/Messages("role.mgmt.newrole")),format.raw/*14.130*/("""</a>
						</dd>
  					</dl>
				</span>
			<center>
			<table id="allAvailableRoles">
				<thead>
					<tr>
						<th>"""),_display_(/*22.12*/Messages("role.mgmt.name")),format.raw/*22.38*/("""</th>					
						<th>"""),_display_(/*23.12*/Messages("role.mgmt.action")),format.raw/*23.40*/("""</th>
					</tr>
				</thead>
				<tbody data-bind="foreach: """),format.raw/*26.32*/("""{"""),format.raw/*26.33*/("""data:rolemanagement.availableRoles, as: 'roleRecord'"""),format.raw/*26.85*/("""}"""),format.raw/*26.86*/("""">
					<tr data-bind="ifnot: roleRecord.deleted">
						<td data-bind='text: roleRecord.name'></td>
						<td>
							<ul class="inline-list">
								<li>
									<a  title='"""),_display_(/*32.22*/Messages("role.mgmt.editrole")),format.raw/*32.52*/("""' data-bind='attr: """),format.raw/*32.71*/("""{"""),format.raw/*32.72*/("""id: "role_edit_"+roleRecord.roleId"""),format.raw/*32.106*/("""}"""),format.raw/*32.107*/(""", click:roleRecord.loadRole'>
										<i class="fi-pencil"></i>
									</a>
								</li>
								<li data-bind="if: roleRecord.active">
									<a title='"""),_display_(/*37.21*/Messages("role.mgmt.dactrole")),format.raw/*37.51*/("""'  data-bind='attr: """),format.raw/*37.71*/("""{"""),format.raw/*37.72*/("""id: "role_active_"+roleRecord.roleId"""),format.raw/*37.108*/("""}"""),format.raw/*37.109*/(""", click:roleRecord.toggleStatus'>
										<i class="fi-unlock"></i>
									</a>
								</li>
								<li data-bind="ifnot: roleRecord.active">
									<a title='"""),_display_(/*42.21*/Messages("role.mgmt.actrole")),format.raw/*42.50*/("""' data-bind='attr: """),format.raw/*42.69*/("""{"""),format.raw/*42.70*/("""id: "role_active_"+roleRecord.roleId"""),format.raw/*42.106*/("""}"""),format.raw/*42.107*/(""", click:roleRecord.toggleStatus'>
										<i class="fi-lock"></i>										
									</a>
								</li>								
								<li>
									<a  title='"""),_display_(/*47.22*/Messages("role.mgmt.delrole")),format.raw/*47.51*/("""' data-bind='attr: """),format.raw/*47.70*/("""{"""),format.raw/*47.71*/("""id: "role_delete_"+roleRecord.roleId"""),format.raw/*47.107*/("""}"""),format.raw/*47.108*/(""", click:roleRecord.deleteRoleCheck'>
										<i class="fi-trash"></i>
									</a>
								</li>
								<li>
									<a  title='"""),_display_(/*52.22*/Messages("role.mgmt.assignpermission")),format.raw/*52.60*/("""' data-bind='attr: """),format.raw/*52.79*/("""{"""),format.raw/*52.80*/("""id: "role_repass_"+roleRecord.roleId"""),format.raw/*52.116*/("""}"""),format.raw/*52.117*/(""", click:roleRecord.showRolesPermission'>
										<i class="fi-thumbnails"></i>
									</a>
								</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table></center>
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/role_mgmt.scala.html
                  HASH: a1168d556934d01504f49d90d21ad6a7faeb400f
                  MATRIX: 813->0|947->108|988->129|1479->592|1530->621|1678->742|1725->768|1774->790|1823->818|1912->879|1941->880|2021->932|2050->933|2255->1111|2306->1141|2353->1160|2382->1161|2445->1195|2475->1196|2663->1357|2714->1387|2762->1407|2791->1408|2856->1444|2886->1445|3081->1613|3131->1642|3178->1661|3207->1662|3272->1698|3302->1699|3477->1847|3527->1876|3574->1895|3603->1896|3668->1932|3698->1933|3859->2067|3918->2105|3965->2124|3994->2125|4059->2161|4089->2162
                  LINES: 29->1|34->6|34->6|42->14|42->14|50->22|50->22|51->23|51->23|54->26|54->26|54->26|54->26|60->32|60->32|60->32|60->32|60->32|60->32|65->37|65->37|65->37|65->37|65->37|65->37|70->42|70->42|70->42|70->42|70->42|70->42|75->47|75->47|75->47|75->47|75->47|75->47|80->52|80->52|80->52|80->52|80->52|80->52
                  -- GENERATED --
              */
          