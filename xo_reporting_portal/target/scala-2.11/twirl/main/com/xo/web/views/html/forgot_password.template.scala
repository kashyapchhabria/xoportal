
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
object forgot_password extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(forgotPassForm: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.27*/("""
"""),_display_(/*3.2*/xo_base/*3.9*/ {_display_(Seq[Any](format.raw/*3.11*/("""
		"""),format.raw/*4.3*/("""<section class="small-16">
			"""),_display_(/*5.5*/helper/*5.11*/.form(action = com.xo.web.controllers.routes.UserController.forgotPassword(), 'name -> "passwordReset")/*5.114*/ {_display_(Seq[Any](format.raw/*5.116*/("""
				"""),format.raw/*6.5*/("""<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round">
					 <div id="alert-message" class="text-center fontBlack"></div>	 
				</div>	
					<div class="row">
						"""),_display_(/*10.8*/if(forgotPassForm.hasGlobalErrors)/*10.42*/ {_display_(Seq[Any](format.raw/*10.44*/("""
				        	"""),format.raw/*11.14*/("""<p>
				            	<div class="small-14 medium-4 small-centered columns alert-box alert">"""),_display_(/*12.89*/forgotPassForm/*12.103*/.globalError.message),format.raw/*12.123*/("""</div>
				          	</p>
				        """)))}),format.raw/*14.14*/("""
						"""),format.raw/*15.7*/("""<div class = "panel small-14 medium-6 small-centered columns">
		
						        """),_display_(/*17.16*/inputText(
										  forgotPassForm("email"),
										  '_showConstraints -> false,
										  'placeholder -> Messages("login.email"),
										  '_label -> ""
										)),format.raw/*22.12*/("""
		
								"""),format.raw/*24.9*/("""<input id="forgotPassword" type="submit" value=""""),_display_(/*24.58*/Messages("password.reset")),format.raw/*24.84*/("""" class="button expand"></input>
		        		</div>
					</div>
				</form>		
		</section>
	""")))}),format.raw/*29.3*/("""
""")))}))}
  }

  def render(forgotPassForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(forgotPassForm)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (forgotPassForm) => apply(forgotPassForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:03 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/forgot_password.scala.html
                  HASH: 66cf5e5fd073c490c21eae5deee3d6292072259d
                  MATRIX: 816->1|944->26|971->45|985->52|1024->54|1053->57|1109->88|1123->94|1235->197|1275->199|1306->204|1563->435|1606->469|1646->471|1688->485|1807->577|1831->591|1873->611|1944->651|1978->658|2086->739|2285->917|2324->929|2400->978|2447->1004|2570->1097
                  LINES: 28->1|31->1|32->3|32->3|32->3|33->4|34->5|34->5|34->5|34->5|35->6|39->10|39->10|39->10|40->11|41->12|41->12|41->12|43->14|44->15|46->17|51->22|53->24|53->24|53->24|58->29
                  -- GENERATED --
              */
          