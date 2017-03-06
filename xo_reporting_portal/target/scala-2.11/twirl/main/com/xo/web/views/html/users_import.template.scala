
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
import com.xo.web.views.html._
/**/
object users_import extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*2.1*/("""<script id="upload-users" type="text/html">
	<section>
		<br>
		<form enctype="multipart/form-data" data-bind="submit: usermanagement.uploadUsers">
			<center><h2>"""),_display_(/*6.17*/Messages("user.mgmt.upload")),format.raw/*6.45*/("""</h2></center><br>
				<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible: usermanagement.visibility">
					 <div id="alert-message" class="text-center fontBlack"></div>	 
				</div>		
			<div class="row">
			<div class="small-12 medium-6 small-centered columns ">
									
				<input  class="small-10 small-centered columns panel" multiple="multiple"  type="file" name="user_entries" id="user_entries" />	
				<div class = "row">
					<div class="small-3 small-centered columns">
						<input id="uploadUserEntries" class="button" type="submit" value=""""),_display_(/*16.74*/Messages("admin.upload.users")),format.raw/*16.104*/(""""></input>
					</div>
				</div>															
			</div>	
			</div>			
		</form>
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
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/users_import.scala.html
                  HASH: 0fb7138b0924b809b786c11dc42dfe013ea5fe46
                  MATRIX: 846->32|1036->196|1084->224|1753->866|1805->896
                  LINES: 29->2|33->6|33->6|43->16|43->16
                  -- GENERATED --
              */
          