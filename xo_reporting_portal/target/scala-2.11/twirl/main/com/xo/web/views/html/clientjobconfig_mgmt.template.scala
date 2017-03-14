
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
object clientjobconfig_mgmt extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="clientjobconfig_mgmt" type="text/html">
<section>
	<div class="row">
				<span>
    			 <center>
					<h2>"""),_display_(/*6.11*/Messages("clientjobconfig.mgmt")),format.raw/*6.43*/("""</h2></center><br><br>
					<span>
						<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible:clientjobmgmt.visibility">
							<div id="alert-message" class="text-center fontBlack"></div>
						</div>
					</span>
					<ul class="sub-nav xo-nav">
    					<li class="active">
							<a title="Add new Configurations" id="addconfig" data-reveal-id="addconfigModel" data-bind='click:clientjobmgmt.loadPopup'>"""),_display_(/*14.132*/Messages("clientjobconfig.mgmt.add")),format.raw/*14.168*/("""</a>
							<div id="addconfigModel" class="reveal-modal medium top50" data-reveal aria-labelledby="modalTitle" aria-hidden="true" role="dialog">"""),_display_(/*15.142*/Messages("clientjobconfig.mgmt.add")),format.raw/*15.178*/("""
								"""),format.raw/*16.9*/("""<span>
								<div data-alert id="popup-box-container"
									 class="padding5 small-8 large-6 small-centered columns alert-box alert round"
									 data-bind="visible:clientjobmgmt.visibility">
									<div id="popup-message" class="text-center fontBlack"></div>
								</div>
							</span>
								<select title='"""),_display_(/*23.25*/Messages("clientjobconfig.mgmt.newClient")),format.raw/*23.67*/("""' id="selSysClient" data-bind='options:clientjobmgmt.availableClients, optionsText: "name", value: clientjobmgmt.selectedClient, optionsCaption: "Choose Client......"'>"""),_display_(/*23.236*/Messages("clientjobconfig.mgmt.Client")),format.raw/*23.275*/("""</select>
								<select title='"""),_display_(/*24.25*/Messages("clientjobconfig.mgmt.newJob")),format.raw/*24.64*/("""' id="selSysJob" data-bind='options:clientjobmgmt.addavailableJobs, optionsText: "jobName", value: clientjobmgmt.selectedJob, optionsCaption: "Choose Job......."'>"""),_display_(/*24.228*/Messages("clientjobconfig.mgmt.Job")),format.raw/*24.264*/("""</select>
								<select title='"""),_display_(/*25.25*/Messages("clientjobconfig.mgmt.newInstance")),format.raw/*25.69*/("""' id="selSysInstance" data-bind='options:clientjobmgmt.addavailableInstances, optionsText: "shortName", value: clientjobmgmt.selectedInstance, optionsCaption: "Choose Instance......"'>"""),_display_(/*25.254*/Messages("clientjobconfig.mgmt.Instance")),format.raw/*25.295*/("""</select>
								<center data-bind="click:clientjobmgmt.saveClientJobConfig">
									<button id="create_user" class="button small round" type="submit">Add</button>
								</center>
								<a class="close-reveal-modal" aria-label="Close">&#215;</a>
							</div>
						</li>
  					</ul>
				</span>
			<center>
			<table id="allClientJobsConfigs">
				<thead>
					<tr>
						<th>"""),_display_(/*38.12*/Messages("clientjobconfig.mgmt.client_name")),format.raw/*38.56*/("""</th>
						<th>"""),_display_(/*39.12*/Messages("clientjobconfig.mgmt.jobName")),format.raw/*39.52*/("""</th>
						<th>"""),_display_(/*40.12*/Messages("clientjobconfig.mgmt.configName")),format.raw/*40.55*/("""</th>
						<th>"""),_display_(/*41.12*/Messages("clientjobconfig.mgmt.lastMessage")),format.raw/*41.56*/("""</th>
						<th>"""),_display_(/*42.12*/Messages("clientjobconfig.mgmt.action")),format.raw/*42.51*/("""</th>
					</tr>
				</thead>
				<tbody data-bind="foreach: """),format.raw/*45.32*/("""{"""),format.raw/*45.33*/("""data:clientjobmgmt.allJobs, as: 'jobConfigRecord'"""),format.raw/*45.82*/("""}"""),format.raw/*45.83*/("""">
					<tr>
						<td data-bind='text: jobConfigRecord.client_name'></td>
						<td data-bind='text: jobConfigRecord.jobName'></td>
						<td data-bind='text: jobConfigRecord.configName'></td>
						<td>
							<span data-bind="if:jobConfigRecord.isRunning()" style="width:100px;height:25px;">
								<img src='"""),_display_(/*52.20*/routes/*52.26*/.Assets.versioned("images/pageloader.gif")),format.raw/*52.68*/("""'/>
							</span>
							<span data-bind="ifnot:jobConfigRecord.isRunning()">
								<a data-bind='text: jobConfigRecord.lastMessage, attr:"""),format.raw/*55.63*/("""{"""),format.raw/*55.64*/("""class:jobConfigRecord.statusType"""),format.raw/*55.96*/("""}"""),format.raw/*55.97*/("""'>
								</a>
							</span>
						</td>
						<td>
							<ul class="inline-list">
								<li data-bind="if: jobConfigRecord.active">
									<a title='"""),_display_(/*62.21*/Messages("clientjobconfig.mgmt.dactconfigtempl")),format.raw/*62.69*/("""'
									   data-bind='click:jobConfigRecord.toggleActiveStatus'>
										<i class="fi-unlock"></i>
									</a>
								</li>
								<li data-bind="ifnot: jobConfigRecord.active">
									<a title='"""),_display_(/*68.21*/Messages("clientjobconfig.mgmt.actconfigtempl")),format.raw/*68.68*/("""'
									   data-bind='click:jobConfigRecord.toggleActiveStatus'>
										<i class="fi-lock"></i>										
									</a>
								</li>
								<li>
									<a data-bind="click:jobConfigRecord.deleteConfig" title='"""),_display_(/*74.68*/Messages("clientjobconfig.mgmt.delconfig")),format.raw/*74.110*/("""'>
										<i class="fi-trash"></i>
									</a>
								</li>
								<li>
									<a data-bind="click:jobConfigRecord.runJob" title='"""),_display_(/*79.62*/Messages("clientjobconfig.mgmt.runconfig")),format.raw/*79.104*/("""'>
										<i class="fi-play"></i>
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
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/clientjobconfig_mgmt.scala.html
                  HASH: 2514687561d67b02e2c19be1bcb4d7b4aafe37c7
                  MATRIX: 824->0|969->119|1021->151|1542->644|1600->680|1774->826|1832->862|1868->871|2219->1195|2282->1237|2479->1406|2540->1445|2601->1479|2661->1518|2853->1682|2911->1718|2972->1752|3037->1796|3250->1981|3313->2022|3726->2408|3791->2452|3835->2469|3896->2509|3940->2526|4004->2569|4048->2586|4113->2630|4157->2647|4217->2686|4306->2747|4335->2748|4412->2797|4441->2798|4782->3112|4797->3118|4860->3160|5029->3301|5058->3302|5118->3334|5147->3335|5332->3493|5401->3541|5635->3748|5703->3795|5950->4015|6014->4057|6181->4197|6245->4239
                  LINES: 29->1|34->6|34->6|42->14|42->14|43->15|43->15|44->16|51->23|51->23|51->23|51->23|52->24|52->24|52->24|52->24|53->25|53->25|53->25|53->25|66->38|66->38|67->39|67->39|68->40|68->40|69->41|69->41|70->42|70->42|73->45|73->45|73->45|73->45|80->52|80->52|80->52|83->55|83->55|83->55|83->55|90->62|90->62|96->68|96->68|102->74|102->74|107->79|107->79
                  -- GENERATED --
              */
          