
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
object reset_password extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(resetForm: Form[_], authToken: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.41*/("""
"""),_display_(/*3.2*/xo_base/*3.9*/ {_display_(Seq[Any](format.raw/*3.11*/("""
		"""),format.raw/*4.3*/("""<section class="small-16">
			"""),_display_(/*5.5*/helper/*5.11*/.form(action = com.xo.web.controllers.routes.UserController.resetPassword(authToken), 'name -> "passwordReset")/*5.122*/ {_display_(Seq[Any](format.raw/*5.124*/("""
				"""),format.raw/*6.5*/("""<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible: password.sholdShowMessage">
					 <div id="alert-message" class="text-center fontBlack"></div>	 
				</div>	
					<div class="row">
						"""),_display_(/*10.8*/if(resetForm.hasGlobalErrors)/*10.37*/ {_display_(Seq[Any](format.raw/*10.39*/("""
				        	"""),format.raw/*11.14*/("""<p>
				            	<div class="small-14 medium-4 small-centered columns alert-box alert">"""),_display_(/*12.89*/resetForm/*12.98*/.globalError.message),format.raw/*12.118*/("""</div>
				          	</p>
				        """)))}),format.raw/*14.14*/("""
						"""),format.raw/*15.7*/("""<div class = "panel small-14 medium-6 small-centered columns">
		
						        """),_display_(/*17.16*/inputPassword(
						          resetForm("newPassword"),						          
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.newPassword"),
								  '_label -> ""
						        )),format.raw/*22.16*/("""
		
						        """),_display_(/*24.16*/inputPassword(
						          resetForm("repeatPassword"),						          
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.repeatPassword"),
								  '_label -> ""
						        )),format.raw/*29.16*/("""
								"""),format.raw/*30.9*/("""<input type="hidden" name="authToken" value=""""),_display_(/*30.55*/authToken),format.raw/*30.64*/(""""></input>
								<input id="resetPassword" type="submit" value=""""),_display_(/*31.57*/Messages("password.reset")),format.raw/*31.83*/("""" class="button expand"></input>
		        		</div>
					</div>
				</form>		
		</section>
	""")))}),format.raw/*36.3*/("""
""")))}))}
  }

  def render(resetForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},authToken:String): play.twirl.api.HtmlFormat.Appendable = apply(resetForm,authToken)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},String) => play.twirl.api.HtmlFormat.Appendable) = (resetForm,authToken) => apply(resetForm,authToken)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:04 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/reset_password.scala.html
                  HASH: cdb3570c67073ace16736b6854684f6c5677609b
                  MATRIX: 822->1|964->40|991->59|1005->66|1044->68|1073->71|1129->102|1143->108|1263->219|1303->221|1334->226|1638->504|1676->533|1716->535|1758->549|1877->641|1895->650|1937->670|2008->710|2042->717|2150->798|2390->1017|2436->1036|2682->1261|2718->1270|2791->1316|2821->1325|2915->1392|2962->1418|3085->1511
                  LINES: 28->1|31->1|32->3|32->3|32->3|33->4|34->5|34->5|34->5|34->5|35->6|39->10|39->10|39->10|40->11|41->12|41->12|41->12|43->14|44->15|46->17|51->22|53->24|58->29|59->30|59->30|59->30|60->31|60->31|65->36
                  -- GENERATED --
              */
          