
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
object user_row_level_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="userrowlevelpermission_mgmt" type="text/html">
<section>
	<div class="row">
		<span>
			<center>
				<h2 data-bind="text:'"""),_display_(/*6.27*/Messages("admin.land.userrow-level")),format.raw/*6.63*/("""'"></h2>
			</center>
			<br>
		<br> <span>
				<div data-alert id="alert-box-container"
					class="padding5 small-8 large-4 small-centered columns alert-box alert round"
					data-bind="visible:rowlevelmgmt.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
		</span>
			<ul	class="sub-nav xo-nav">
				<li class="">
					<ul>
						<li class="active">
							<select id='availableUsers' style="margin-bottom: 0px;" 
									data-bind='options: rowlevelmgmt.availableusers, optionsText: "displayText", value: rowlevelmgmt.selectedUser, optionsCaption: """"),_display_(/*21.139*/Messages("rowlevel.crud.seluser")),format.raw/*21.172*/(""""'>
							</select>
						</li>
						<li class="active">
							<select id='availableResources' data-bind='options: rowlevelmgmt.availableresources,
                       		optionsText: "displayText",
                       		value: rowlevelmgmt.selectedResource,
                       		optionsCaption: """"),_display_(/*28.44*/Messages("rowlevel.crud.selentitytype")),format.raw/*28.83*/(""""' style="margin-bottom: 0px;"></select>
						</li>
					</ul>
				</li>
				<li class="active">
					<a title='"""),_display_(/*33.17*/Messages("rowlevel.crud.crud.create")),format.raw/*33.54*/("""' id="createUserRlp" data-bind='click:rowlevelmgmt.showPopup' data-reveal-id="addrlpModel">
						"""),_display_(/*34.8*/Messages("rowlevel.crud.crud.create")),format.raw/*34.45*/("""
					"""),format.raw/*35.6*/("""</a>
					<div id="addrlpModel" class="reveal-modal medium top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">
                    		<span>
								<div data-alert id="popup-box-container"
									class="padding5 small-8 large-6 small-centered columns alert-box alert round"
									data-bind="visible:rowlevelmgmt.visibility">
									<div id="popup-message" class="text-center fontBlack"></div>
								</div>
							</span>

                        <table id="allUnAssignedRlpInstances">
                            <thead>
                            <tr data-bind='if:rowlevelmgmt.selectedResource() && rowlevelmgmt.selectedResource().displayText'>
                                <th data-bind='text:rowlevelmgmt.selectedResource().displayText'></th>
                                <th>"""),_display_(/*49.38*/Messages("rowlevel.crud.action")),format.raw/*49.70*/("""</th>
                            </tr>
                            </thead>
                            <tbody data-bind="foreach: """),format.raw/*52.56*/("""{"""),format.raw/*52.57*/("""data:rowlevelmgmt.unAssignedInstances, as: 'unAssInstance'"""),format.raw/*52.115*/("""}"""),format.raw/*52.116*/("""">
                            	<tr data-bind="ifnot: unAssInstance.deleted" >
                                	<td data-bind='text: unAssInstance.displayText'></td>
                                	<td class="text-center popupheight" >
										<input type="submit" class="small round button popupheight lightbluebutton" value="ADD" data-bind="click:unAssInstance.addRecord"/>
									</td>
                            	</tr>
                            </tbody>
                        </table>

                        <a class="close-reveal-modal" aria-label="Close">&#215;</a>
                    </div>
				</li>
			</ul>
		</span>
			<table id="allRLPEntities">
				<thead>
					<tr>
						<th>"""),_display_(/*70.12*/Messages("rowlevel.crud.entity")),format.raw/*70.44*/("""</th>
						<th>"""),_display_(/*71.12*/Messages("rowlevel.crud.restype")),format.raw/*71.45*/("""</th>
						<th>"""),_display_(/*72.12*/Messages("rowlevel.crud.action")),format.raw/*72.44*/("""</th>
					</tr>
				</thead>
				<tbody
					data-bind="foreach: """),format.raw/*76.26*/("""{"""),format.raw/*76.27*/("""data:rowlevelmgmt.availableEntityInstances, as: 'entityInstanceRecord'"""),format.raw/*76.97*/("""}"""),format.raw/*76.98*/("""">
					<tr>
						<td data-bind='text: entityInstanceRecord.displayText'></td>
						<td data-bind='text: entityInstanceRecord.resourceTypeName'></td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: entityInstanceRecord.active"><a
									title='"""),_display_(/*83.18*/Messages("user.pr.mgmt.dactusr")),format.raw/*83.50*/("""'
									data-bind='click:entityInstanceRecord.toggleActiveStatus'>
										<i class="fi-unlock"></i>
								</a></li>
								<li data-bind="ifnot: entityInstanceRecord.active"><a
									title='"""),_display_(/*88.18*/Messages("user.pr.mgmt.actusr")),format.raw/*88.49*/("""'
									data-bind='click:entityInstanceRecord.toggleActiveStatus'>
										<i class="fi-lock"></i>
								</a></li>
								<li>
									<a  title='"""),_display_(/*93.22*/Messages("user.pr.mgmt.delusrpr")),format.raw/*93.55*/("""' data-bind='click:entityInstanceRecord.deleteRecord'>
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
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/user_row_level_mgmt.scala.html
                  HASH: 1bf15e503ba9cf1272e64feb0507f22e43751515
                  MATRIX: 823->0|984->135|1040->171|1664->767|1719->800|2056->1110|2116->1149|2257->1263|2315->1300|2440->1399|2498->1436|2531->1442|3385->2269|3438->2301|3598->2433|3627->2434|3714->2492|3744->2493|4475->3197|4528->3229|4572->3246|4626->3279|4670->3296|4723->3328|4817->3394|4846->3395|4944->3465|4973->3466|5271->3737|5324->3769|5554->3972|5606->4003|5789->4159|5843->4192
                  LINES: 29->1|34->6|34->6|49->21|49->21|56->28|56->28|61->33|61->33|62->34|62->34|63->35|77->49|77->49|80->52|80->52|80->52|80->52|98->70|98->70|99->71|99->71|100->72|100->72|104->76|104->76|104->76|104->76|111->83|111->83|116->88|116->88|121->93|121->93
                  -- GENERATED --
              */
          