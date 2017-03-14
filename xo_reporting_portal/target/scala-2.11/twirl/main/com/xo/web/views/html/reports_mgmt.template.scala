
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
object reports_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="report_mgmt" type="text/html">
    <section>
        <div class="row">
		<span>
			<center>
                <h2>"""),_display_(/*6.22*/Messages("reports.mgmt.heading")),format.raw/*6.54*/("""</h2>
            </center>
			<br>
		<br>
				<div data-alert id="alert-box-container"
                     class="padding5 small-8 large-4 small-centered columns alert-box alert round"
                     data-bind="visible:reportmgmt.visibility">
                    <div id="alert-message" class="text-center fontBlack"></div>
                </div>
			<table id="allAvailableReports">
                <thead>
                <tr>
                    <th>"""),_display_(/*18.26*/Messages("reports.mgmt.report.name")),format.raw/*18.62*/("""</th>
                    <th>"""),_display_(/*19.26*/Messages("reports.mgmt.report.display.name")),format.raw/*19.70*/("""</th>
                    <th>"""),_display_(/*20.26*/Messages("reports.mgmt.report.groupname")),format.raw/*20.67*/("""</th>
                    <th>"""),_display_(/*21.26*/Messages("reports.mgmt.report.order")),format.raw/*21.63*/("""</th>
                    <th>"""),_display_(/*22.26*/Messages("reports.mgmt.report.action")),format.raw/*22.64*/("""</th>
                </tr>
                </thead>
                <tbody
                        data-bind="foreach: """),format.raw/*26.45*/("""{"""),format.raw/*26.46*/("""data:reportmgmt.availableReports, as: 'reportsRecord'"""),format.raw/*26.99*/("""}"""),format.raw/*26.100*/("""">
                <tr>
                    <td data-bind='text: reportsRecord.reportName'></td>
                    <td>
						<span data-bind='if:reportsRecord.isEdit()'>
							<input type="text" data-bind='value: reportsRecord.displayName' placeholder='"""),_display_(/*31.86*/Messages("reports.mgmt.val")),format.raw/*31.114*/("""' />
						</span>
						<span data-bind='ifnot:reportsRecord.isEdit()'>
							<span data-bind="text:reportsRecord.displayName"></span>
						</span>
                    </td>
                    <td>
						<span data-bind='if:reportsRecord.isEdit()'>
                            <select data-bind='options:reportsRecord.availableGroups, optionsText: "groupName", value: reportsRecord.selectedGroup, optionsCaption:"Choose group.."'>"""),_display_(/*39.183*/Messages("reports.mgmt.val")),format.raw/*39.211*/("""</select>
						</span>
						<span data-bind='ifnot:reportsRecord.isEdit()'>
							<span data-bind="text:reportsRecord.groupName"></span>
						</span>
                    </td>
                    <td>
						<span data-bind='if:reportsRecord.isEdit()'>
							<input type="text" data-bind='value: reportsRecord.displayOrder' placeholder='"""),_display_(/*47.87*/Messages("reports.mgmt.val")),format.raw/*47.115*/("""' />
						</span>
						<span data-bind='ifnot:reportsRecord.isEdit()'>
							<span data-bind="text:reportsRecord.displayOrder"></span>
						</span>
                    </td>
                    <td>
                        <ul class="inline-list">
                            <li data-bind='ifnot:reportsRecord.isEdit()'>
                                <a  title='"""),_display_(/*56.45*/Messages("reports.mgmt.edit")),format.raw/*56.74*/("""'
                                    data-bind='click:reportsRecord.editable'>
                                    <i class="fi-pencil"></i>
                                </a>
                            </li>
                            <li data-bind='if:reportsRecord.isEdit()'>
                                <a><i title="Save" class="fi-save" data-bind='click:reportsRecord.saveReports' ></i>
                                </a>
                            </li>
                            <li data-bind="if: reportsRecord.active"><a
                                    title='"""),_display_(/*66.45*/Messages("reports.mgmt.dact")),format.raw/*66.74*/("""'
                                    data-bind='click:reportsRecord.toggleStatus'>
                                <i class="fi-unlock"></i>
                            </a></li>
                            <li data-bind="ifnot: reportsRecord.active"><a
                                    title='"""),_display_(/*71.45*/Messages("reports.mgmt.act")),format.raw/*71.73*/("""'
                                    data-bind='click:reportsRecord.toggleStatus'>
                                <i class="fi-lock"></i>
                            </a></li>
                            <li data-bind="if:reportsRecord.viewGroupId() , click:reportsRecord.checkDashboard" class="switch small" title='"""),_display_(/*75.142*/Messages("reports.mgmt.dashboard")),format.raw/*75.176*/("""' >
                                    <input type="checkbox" data-bind="checked:reportsRecord.dashboard">
                                <label></label>
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/reports_mgmt.scala.html
                  HASH: 3cd47da9157c6914a4b95a93bee5319a59eb9bc1
                  MATRIX: 816->0|967->125|1019->157|1507->618|1564->654|1622->685|1687->729|1745->760|1807->801|1865->832|1923->869|1981->900|2040->938|2188->1058|2217->1059|2298->1112|2328->1113|2613->1371|2663->1399|3126->1834|3176->1862|3545->2204|3595->2232|3992->2602|4042->2631|4657->3219|4707->3248|5033->3547|5082->3575|5429->3894|5485->3928
                  LINES: 29->1|34->6|34->6|46->18|46->18|47->19|47->19|48->20|48->20|49->21|49->21|50->22|50->22|54->26|54->26|54->26|54->26|59->31|59->31|67->39|67->39|75->47|75->47|84->56|84->56|94->66|94->66|99->71|99->71|103->75|103->75
                  -- GENERATED --
              */
          