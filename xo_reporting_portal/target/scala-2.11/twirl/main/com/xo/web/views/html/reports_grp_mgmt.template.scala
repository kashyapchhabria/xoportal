
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
object reports_grp_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="report_grp_mgmt" type="text/html">
    <section>
        <div class="row">
		<span>
			<center>
                <h2>"""),_display_(/*6.22*/Messages("reports.grp.mgmt.heading")),format.raw/*6.58*/("""</h2>
            </center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
                     class="padding5 small-8 large-4 small-centered columns alert-box alert round"
                     data-bind="visible:reportgrpmgmt.visibility">
                    <div id="alert-message" class="text-center fontBlack"></div>
                </div>
           <ul class="sub-nav xo-nav">
               <li class="active">
                   <a title="Add new Group" id="addgroup" data-reveal-id="addgroupModel" data-bind='click:reportgrpmgmt.loadPopup'>"""),_display_(/*17.133*/Messages("reports.grp.mgmt.add")),format.raw/*17.165*/("""</a>
                   <div id="addgroupModel" class="reveal-modal medium top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">"""),_display_(/*18.153*/Messages("reports.grp.mgmt.add")),format.raw/*18.185*/("""
								"""),format.raw/*19.9*/("""<span>
								<div data-alert id="popup-box-container"
                                     class="padding5 small-8 large-6 small-centered columns alert-box alert round"
                                     data-bind="visible:reportgrpmgmt.visibility">
                                    <div id="popup-message" class="text-center fontBlack"></div>
                                </div>
							</span>
                       <span>
							<input type="text" data-bind='value: reportgrpmgmt.groupName' />
						</span>
                       <center >
                           <button data-bind="click:reportgrpmgmt.saveGroups" id="create_group" class="button small round" type="submit">Add</button>
                       </center>
                       <a class="close-reveal-modal" aria-label="Close">&#215;</a>
                   </div>
               </li>
           </ul>
			<table id="allAvailableGroups">
                <thead>
                <tr>
					<th>"""),_display_(/*39.11*/Messages("reports.grp.mgmt.code")),format.raw/*39.44*/("""</th>
					<th>"""),_display_(/*40.11*/Messages("reports.grp.mgmt.name")),format.raw/*40.44*/("""</th>
                    <th>"""),_display_(/*41.26*/Messages("reports.grp.mgmt.order")),format.raw/*41.60*/("""</th>
                    <th>"""),_display_(/*42.26*/Messages("reports.grp.mgmt.action")),format.raw/*42.61*/("""</th>
                </tr>
                </thead>
                <tbody data-bind="foreach: """),format.raw/*45.44*/("""{"""),format.raw/*45.45*/("""data:reportgrpmgmt.availableGroups, as: 'groupRecord'"""),format.raw/*45.98*/("""}"""),format.raw/*45.99*/("""">
                <tr>
					<td>
						<span data-bind="text:groupRecord.viewGroupId"></span>
                    </td>
                    <td>
						<span data-bind='if:groupRecord.isEdit()'>
							<input type="text" data-bind='value: groupRecord.groupName' />
						</span>
						<span data-bind='ifnot:groupRecord.isEdit()'>
							<span data-bind="text:groupRecord.groupName"></span>
						</span>
                    </td>
                    <td>
						<span data-bind='if:groupRecord.isEdit()'>
							<input type="text" data-bind='value: groupRecord.displayOrder' />
						</span>
						<span data-bind='ifnot:groupRecord.isEdit()'>
							<span data-bind="text:groupRecord.displayOrder"></span>
						</span>
                    </td>
                    <td>
                        <ul class="inline-list">
                            <li data-bind='ifnot:groupRecord.isEdit()'>
                                <a  title='"""),_display_(/*69.45*/Messages("reports.grp.mgmt.edit")),format.raw/*69.78*/("""'
                                    data-bind='click:groupRecord.editable'>
                                    <i class="fi-pencil"></i>
                                </a>
                            </li>
                            <li data-bind='if:groupRecord.isEdit()'>
                                <a><i title="Save" class="fi-save" data-bind='click:groupRecord.saveGroups' ></i>
                                </a>
                            </li>
                            <li data-bind="if: groupRecord.active"><a
                                    title='"""),_display_(/*79.45*/Messages("reports.grp.mgmt.dact")),format.raw/*79.78*/("""'
                                    data-bind='click:groupRecord.toggleStatus'>
                                <i class="fi-unlock"></i>
                            </a></li>
                            <li data-bind="ifnot: groupRecord.active"><a
                                    title='"""),_display_(/*84.45*/Messages("reports.grp.mgmt.act")),format.raw/*84.77*/("""'
                                    data-bind='click:groupRecord.toggleStatus'>
                                <i class="fi-lock"></i>
                            </a></li>
                            <li>
                                <a title='"""),_display_(/*89.44*/Messages("reports.grp.mgmt.del")),format.raw/*89.76*/("""'>
                                    <i class="fi-trash" data-bind='click:groupRecord.deleteGroupCheck'></i>
                                </a>
                            </li>
                        </ul>
                    </td>
                </tr>
                </tbody>
            </table>
            </span>
            </span>
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
                  DATE: Tue Mar 14 10:00:29 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/reports_grp_mgmt.scala.html
                  HASH: bea025be3afe31ee7abadac2dab2bf54823dad45
                  MATRIX: 820->0|975->129|1031->165|1630->736|1684->768|1869->925|1923->957|1959->966|2960->1940|3014->1973|3057->1989|3111->2022|3169->2053|3224->2087|3282->2118|3338->2153|3462->2249|3491->2250|3572->2303|3601->2304|4565->3241|4619->3274|5225->3853|5279->3886|5601->4181|5654->4213|5933->4465|5986->4497
                  LINES: 29->1|34->6|34->6|45->17|45->17|46->18|46->18|47->19|67->39|67->39|68->40|68->40|69->41|69->41|70->42|70->42|73->45|73->45|73->45|73->45|97->69|97->69|107->79|107->79|112->84|112->84|117->89|117->89
                  -- GENERATED --
              */
          