
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
object tableau_view_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="tableau_view_mgmt" type="text/html">
    <section>
        <div class="row">
		<span>
			<center>
                <h2>Tableau View Management</h2>
            </center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
                     class="padding5 small-8 large-4 small-centered columns alert-box alert round"
                     data-bind="visible:permissionmanagement.visibility">
                    <div id="alert-message" class="text-center fontBlack"></div>
                </div>
			<table id="allAvailableTableauViews">
                <!--<thead>-->
                <!--<tr>-->
                    <!--<th>"""),_display_(/*18.30*/Messages("permission.mgmt.name")),format.raw/*18.62*/("""</th>-->
                    <!--<th>"""),_display_(/*19.30*/Messages("permission.mgmt.description")),format.raw/*19.69*/("""</th>-->
                    <!--<th>"""),_display_(/*20.30*/Messages("permission.mgmt.action")),format.raw/*20.64*/("""</th>-->
                <!--</tr>-->
                <!--</thead>-->
                <!--<tbody-->
                        <!--data-bind="foreach: """),format.raw/*24.49*/("""{"""),format.raw/*24.50*/("""data:permissionmanagement.availablePermissions, as: 'permissionRecord'"""),format.raw/*24.120*/("""}"""),format.raw/*24.121*/("""">-->
                <!--<tr data-bind="ifnot: permissionRecord.deleted">-->
                    <!--<td data-bind='text: permissionRecord.name'></td>-->
                    <!--<td data-bind="text: permissionRecord.description"></td>-->
                    <!--<td>-->
                        <!--<ul class="inline-list">-->
                            <!--<li data-bind="if: permissionRecord.active"><a-->
                                    <!--title='"""),_display_(/*31.49*/Messages("permission.mgmt.dactprm")),format.raw/*31.84*/("""'-->
                                    <!--data-bind='attr: """),format.raw/*32.58*/("""{"""),format.raw/*32.59*/("""id: "permission_active_"+permissionRecord.permissionId"""),format.raw/*32.113*/("""}"""),format.raw/*32.114*/(""", click:permissionRecord.toggleStatus'>-->
                                <!--<i class="fi-unlock"></i>-->
                            <!--</a></li>-->
                            <!--<li data-bind="ifnot: permissionRecord.active"><a-->
                                    <!--title='"""),_display_(/*36.49*/Messages("permission.mgmt.actprm")),format.raw/*36.83*/("""'-->
                                    <!--data-bind='attr: """),format.raw/*37.58*/("""{"""),format.raw/*37.59*/("""id: "permission_active_"+permissionRecord.permissionId"""),format.raw/*37.113*/("""}"""),format.raw/*37.114*/(""", click:permissionRecord.toggleStatus'>-->
                                <!--<i class="fi-lock"></i>-->
                            <!--</a></li>-->
                            <!--&lt;!&ndash;<li><a title='"""),_display_(/*40.60*/Messages("permission.mgmt.resetpass")),format.raw/*40.97*/("""'-->
                                <!--data-bind='attr: """),format.raw/*41.54*/("""{"""),format.raw/*41.55*/("""id: "permission_repass_"+permissionRecord.permissionId"""),format.raw/*41.109*/("""}"""),format.raw/*41.110*/(""", click:permissionRecord.resetpassword'>-->
                                    <!--<i class="fi-refresh"></i>-->
                            <!--</a></li>-->
                            <!--<li><a title='"""),_display_(/*44.48*/Messages("permission.mgmt.roles")),format.raw/*44.81*/("""'-->
                                <!--data-bind='attr: """),format.raw/*45.54*/("""{"""),format.raw/*45.55*/("""id: "permission_repass_"+permissionRecord.permissionId"""),format.raw/*45.109*/("""}"""),format.raw/*45.110*/(""", click:permissionRecord.showPermissionRoles'>-->
                                    <!--<i class="fi-thumbnails"></i>-->
                            <!--</a></li>-->
                            <!--<li>-->
                                <!--<a  title='"""),_display_(/*49.49*/Messages("permission.mgmt.delprm")),format.raw/*49.83*/("""' data-bind='attr: """),format.raw/*49.102*/("""{"""),format.raw/*49.103*/("""id: "permission_delete_"+permissionRecord.permissionId"""),format.raw/*49.157*/("""}"""),format.raw/*49.158*/(""", click:permissionRecord.deletePermission'>-->
                                    <!--<i class="fi-trash"></i>-->
                                <!--</a>-->
                            <!--</li> &ndash;&gt;-->
                        <!--</ul>-->
                    <!--</td>-->
                <!--</tr>-->
                <!--</tbody>-->
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/tableau_view_mgmt.scala.html
                  HASH: 78ee48abee8efc80d1f07ee2839319b91e8f1d13
                  MATRIX: 821->0|1502->654|1555->686|1620->724|1680->763|1745->801|1800->835|1976->983|2005->984|2104->1054|2134->1055|2618->1512|2674->1547|2764->1609|2793->1610|2876->1664|2906->1665|3219->1951|3274->1985|3364->2047|3393->2048|3476->2102|3506->2103|3743->2313|3801->2350|3887->2408|3916->2409|3999->2463|4029->2464|4262->2670|4316->2703|4402->2761|4431->2762|4514->2816|4544->2817|4827->3073|4882->3107|4930->3126|4960->3127|5043->3181|5073->3182
                  LINES: 29->1|46->18|46->18|47->19|47->19|48->20|48->20|52->24|52->24|52->24|52->24|59->31|59->31|60->32|60->32|60->32|60->32|64->36|64->36|65->37|65->37|65->37|65->37|68->40|68->40|69->41|69->41|69->41|69->41|72->44|72->44|73->45|73->45|73->45|73->45|77->49|77->49|77->49|77->49|77->49|77->49
                  -- GENERATED --
              */
          