
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
object configuration extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.1*/("""<script id="config" type="text/html">
    <section>
        <div class="row">
		<span>
			<center>
                <h2 data-bind='text: configmgmt.shortName()+" "+""""),_display_(/*6.67*/Messages("config.param.params")),format.raw/*6.98*/(""""'></h2>
            </center>
			<br><br>
			<span>
				<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible:configmgmt.visibility">
					<div id="alert-message" class="text-center fontBlack"></div>
				</div>
			</span>
            <ul class="sub-nav xo-nav">
                <li class="active">
                    <a title='Go Back' data-bind="click:configmgmt.backPage">&lt;&lt;&nbsp;"""),_display_(/*16.93*/Messages("config.mgmt.goback")),format.raw/*16.123*/("""</a>
                </li>
                <li class="active">
                    <a title='Add new configuration Parameter' data-bind="click:configmgmt.addParamRow">"""),_display_(/*19.106*/Messages("config.param.newparam")),format.raw/*19.139*/("""</a>
                </li>
                <li class="active">
                    <a title='Edit configuration name' data-bind="click:configmgmt.editShortname">"""),_display_(/*22.100*/Messages("config.param.editname")),format.raw/*22.133*/("""</a>
                </li>
            </ul>
		</span>


			<table id="configurations">
                <thead>
                <tr>
                    <th>"""),_display_(/*31.26*/Messages("config.param.key")),format.raw/*31.54*/("""</th>
                    <th>"""),_display_(/*32.26*/Messages("config.param.value")),format.raw/*32.56*/("""</th>
                    <th>"""),_display_(/*33.26*/Messages("config.param.actions")),format.raw/*33.58*/("""</th>
                </tr>
                </thead>
                <tbody data-bind="foreach: """),format.raw/*36.44*/("""{"""),format.raw/*36.45*/("""data:configmgmt.allConfigParams(), as: 'configuration'"""),format.raw/*36.99*/("""}"""),format.raw/*36.100*/("""">
                <tr>
                    <td>
						<span data-bind='if:configuration.isEdit()'>
							<input class="config_key" type="text" data-bind='value: configuration.configname' placeholder='"""),_display_(/*40.104*/Messages("config.param.key")),format.raw/*40.132*/("""' />
						</span>
						<span data-bind='ifnot:configuration.isEdit()'>
							<span data-bind="text:configuration.configname"></span>
						</span>
					</td>
                    <td>
						<span data-bind='if:configuration.isEdit()'>
							<input class="config_value" type="text" data-bind='value: configuration.configvalue' placeholder='"""),_display_(/*48.107*/Messages("config.param.value")),format.raw/*48.137*/("""' />
						</span>
						<span data-bind='ifnot:configuration.isEdit()'>
							<span data-bind="text:configuration.configvalue"></span>
						</span>
					</td>
					<td>
						<ul class="inline-list">
                        	<li data-bind='ifnot:configuration.isEdit()'>
								<a>
                            		<i data-bind='click:configuration.editable' title="Edit" class="fi-pencil"></i>
                        		</a>
							</li>
							<li data-bind='if:configuration.isEdit()'>
								<a>
                            		<i title="Save" class="fi-save" data-bind='click:configuration.update'></i>
                        		</a>
							</li>
							<li>
								<a>
                            		<i title="Delete" class="fi-trash" data-bind='click:configuration.deleteConfig' ></i>
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
                  DATE: Tue Mar 14 10:00:31 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/configuration.scala.html
                  HASH: b7e4631cf62b5b4fd2383e6481ffe3f72e355875
                  MATRIX: 817->0|1008->165|1059->196|1561->671|1613->701|1809->869|1864->902|2054->1064|2109->1097|2294->1255|2343->1283|2401->1314|2452->1344|2510->1375|2563->1407|2687->1503|2716->1504|2798->1558|2828->1559|3059->1762|3109->1790|3480->2133|3532->2163
                  LINES: 29->1|34->6|34->6|44->16|44->16|47->19|47->19|50->22|50->22|59->31|59->31|60->32|60->32|61->33|61->33|64->36|64->36|64->36|64->36|68->40|68->40|76->48|76->48
                  -- GENERATED --
              */
          