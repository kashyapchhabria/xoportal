
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
object changepassword extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template1[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(resetForm: Form[_]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.22*/("""
"""),format.raw/*3.1*/("""<script id="password-change" type="text/html">
	<section class="small-16">				
			<form id="passwordReset" data-bind="submit: usermanagement.changepassword">
				<div data-alert id="alert-box-container" class="padding5 small-8 large-4 small-centered columns alert-box alert round" data-bind="visible: password.sholdShowMessage">
					 <div id="alert-message" class="text-center fontBlack"></div>	 
				</div>	
					<div class="row">
						"""),_display_(/*10.8*/if(resetForm.hasGlobalErrors)/*10.37*/ {_display_(Seq[Any](format.raw/*10.39*/("""
				        	"""),format.raw/*11.14*/("""<p>
				            	<div class="small-14 medium-4 small-centered columns alert-box alert">"""),_display_(/*12.89*/resetForm/*12.98*/.globalError.message),format.raw/*12.118*/("""</div>
				          	</p>
				        """)))}),format.raw/*14.14*/("""
						"""),format.raw/*15.7*/("""<div class = "panel small-14 medium-6 small-centered columns">

								"""),_display_(/*17.10*/inputPassword(
						          resetForm("password"),
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.oldPassword"),
								  '_label -> ""
						        )),format.raw/*22.16*/("""

						        """),_display_(/*24.16*/inputPassword(
						          resetForm("newPassword"),
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.newPassword"),
								  '_label -> ""
						        )),format.raw/*29.16*/("""

						        """),_display_(/*31.16*/inputPassword(
						          resetForm("repeatPassword"),
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.repeatPassword"),
								  '_label -> ""
						        )),format.raw/*36.16*/("""

								"""),format.raw/*38.9*/("""<input id="changePassword" type="submit" value=""""),_display_(/*38.58*/Messages("password.change")),format.raw/*38.85*/("""" class="button expand">
		        		</div>
					</div>
				</form>		
		</section>           
           
 </script>"""))}
  }

  def render(resetForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}): play.twirl.api.HtmlFormat.Appendable = apply(resetForm)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
}) => play.twirl.api.HtmlFormat.Appendable) = (resetForm) => apply(resetForm)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Thu Feb 23 09:42:05 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/changepassword.scala.html
                  HASH: 9d7f9dc929be4cc3db723d353224fa9c5728b3b2
                  MATRIX: 815->1|938->21|965->39|1431->479|1469->508|1509->510|1551->524|1670->616|1688->625|1730->645|1801->685|1835->692|1935->765|2156->965|2200->982|2424->1185|2468->1202|2698->1411|2735->1421|2811->1470|2859->1497
                  LINES: 28->1|31->1|32->3|39->10|39->10|39->10|40->11|41->12|41->12|41->12|43->14|44->15|46->17|51->22|53->24|58->29|60->31|65->36|67->38|67->38|67->38
                  -- GENERATED --
              */
          