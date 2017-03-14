
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
object login extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(loginForm: Form[_], forgotPassUrl: String):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import helper._

Seq[Any](format.raw/*1.45*/("""

"""),format.raw/*4.1*/("""
"""),_display_(/*5.2*/xo_base/*5.9*/{_display_(Seq[Any](format.raw/*5.10*/("""
			"""),format.raw/*6.4*/("""<head>
				<title>"""),_display_(/*7.13*/Messages("login.title")),format.raw/*7.36*/("""</title>
			</head>
			<section class="small-16">	
				<div class="row">
					<div class="small-14 medium-4 small-centered columns">
						<img  width="400" height="300" src='"""),_display_(/*12.44*/routes/*12.50*/.Assets.at("images/" + com.xo.web.util.XoUtil.getApplicationEndUser().toLowerCase() + "_logo.png")),format.raw/*12.148*/("""' alt="Unable to load">
					</div>
				</div>
				"""),_display_(/*15.6*/helper/*15.12*/.form(action = com.xo.web.controllers.routes.UserController.login)/*15.78*/ {_display_(Seq[Any](format.raw/*15.80*/("""
					"""),format.raw/*16.6*/("""<div class="row">
						"""),_display_(/*17.8*/if(loginForm.hasGlobalErrors)/*17.37*/ {_display_(Seq[Any](format.raw/*17.39*/("""
				        	"""),format.raw/*18.14*/("""<p>
				            	<div class="small-14 medium-4 small-centered columns alert-box alert">"""),_display_(/*19.89*/loginForm/*19.98*/.globalError.message),format.raw/*19.118*/("""</div>
				          	</p>
				        """)))}),format.raw/*21.14*/("""
						"""),format.raw/*22.7*/("""<div class = "panel small-14 medium-4 small-centered columns">

								"""),_display_(/*24.10*/inputText(
										  loginForm("email"),
										  '_showConstraints -> false,
										  'placeholder -> Messages("login.email"),
										  '_label -> ""
										)),format.raw/*29.12*/("""
		
						        """),_display_(/*31.16*/inputPassword(
						          loginForm("password"),
						          '_showConstraints -> false,
						          'placeholder -> Messages("login.password"),
								  '_label -> ""
						        )),format.raw/*36.16*/("""
								"""),format.raw/*37.9*/("""<input id="xologin" type="submit" onClick="crypt()" value=""""),_display_(/*37.69*/Messages("login.login")),format.raw/*37.92*/("""" class="button expand">
								<a href=""""),_display_(/*38.19*/forgotPassUrl),format.raw/*38.32*/("""" class="small-centered">"""),_display_(/*38.58*/Messages("password.forgot")),format.raw/*38.85*/("""</a>
		        		</div>
					</div>
				""")))}),format.raw/*41.6*/("""		
			"""),format.raw/*42.4*/("""</section>
            <script type="text/javascript" src='"""),_display_(/*43.50*/routes/*43.56*/.Assets.at("js/xo/json_2.js")),format.raw/*43.85*/("""'></script>
			<script type="text/javascript" src='"""),_display_(/*44.41*/routes/*44.47*/.Assets.at("js/xo/issac.js")),format.raw/*44.75*/("""'></script>
			<script type="text/javascript" src='"""),_display_(/*45.41*/routes/*45.47*/.Assets.at("js/xo/bCrypt.js")),format.raw/*45.76*/("""'></script>
            <script type="text/javascript" >
            var id;
            var bcrypt = new bCrypt();
            function enable()"""),format.raw/*49.30*/("""{"""),format.raw/*49.31*/("""
            	"""),format.raw/*50.14*/("""if(bcrypt.ready())"""),format.raw/*50.32*/("""{"""),format.raw/*50.33*/("""
            		"""),format.raw/*51.15*/("""clearInterval(id);
            	"""),format.raw/*52.14*/("""}"""),format.raw/*52.15*/("""
            """),format.raw/*53.13*/("""}"""),format.raw/*53.14*/("""

            """),format.raw/*55.13*/("""function crypt()"""),format.raw/*55.29*/("""{"""),format.raw/*55.30*/("""
            	"""),format.raw/*56.14*/("""try"""),format.raw/*56.17*/("""{"""),format.raw/*56.18*/("""
                	"""),format.raw/*57.18*/("""if($("#password").val().length > 0) """),format.raw/*57.54*/("""{"""),format.raw/*57.55*/("""
	            		"""),format.raw/*58.16*/("""bcrypt.hashpw($("#password").val(), bcrypt.gensalt(10), function(hashedPass)"""),format.raw/*58.92*/("""{"""),format.raw/*58.93*/("""
		            			"""),format.raw/*59.18*/("""$("#password").val(hashedPass);
		            		"""),format.raw/*60.17*/("""}"""),format.raw/*60.18*/(""", function() """),format.raw/*60.31*/("""{"""),format.raw/*60.32*/("""}"""),format.raw/*60.33*/(""");
                	"""),format.raw/*61.18*/("""}"""),format.raw/*61.19*/(""" 
            		"""),format.raw/*62.15*/("""//UserEntity.login();	// ajax login
	            """),format.raw/*63.14*/("""}"""),format.raw/*63.15*/("""catch(err)"""),format.raw/*63.25*/("""{"""),format.raw/*63.26*/("""
	                    """),format.raw/*64.22*/("""alert(err);
	                    return;
	            """),format.raw/*66.14*/("""}"""),format.raw/*66.15*/("""
        	"""),format.raw/*67.10*/("""}"""),format.raw/*67.11*/("""

            """),format.raw/*69.13*/("""$(document).ready(function()"""),format.raw/*69.41*/("""{"""),format.raw/*69.42*/("""
            	 """),format.raw/*70.15*/("""id = setInterval(enable,250);
             """),format.raw/*71.14*/("""}"""),format.raw/*71.15*/(""");


            </script>
            
            """)))}))}
  }

  def render(loginForm:Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},forgotPassUrl:String): play.twirl.api.HtmlFormat.Appendable = apply(loginForm,forgotPassUrl)

  def f:((Form[_$1] forSome { 
   type _$1 >: _root_.scala.Nothing <: _root_.scala.Any
},String) => play.twirl.api.HtmlFormat.Appendable) = (loginForm,forgotPassUrl) => apply(loginForm,forgotPassUrl)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Mar 14 10:00:30 IST 2017
                  SOURCE: /home/kashyap/xoworkspace/xoportal/xo_reporting_portal/app/com/xo/web/views/login.scala.html
                  HASH: 804ad051e17f412bdb036a0250c160267feef337
                  MATRIX: 813->1|959->44|987->63|1014->65|1028->72|1066->73|1096->77|1141->96|1184->119|1387->295|1402->301|1522->399|1600->451|1615->457|1690->523|1730->525|1763->531|1814->556|1852->585|1892->587|1934->601|2053->693|2071->702|2113->722|2184->762|2218->769|2318->842|2512->1015|2558->1034|2776->1231|2812->1240|2899->1300|2943->1323|3013->1366|3047->1379|3100->1405|3148->1432|3219->1473|3252->1479|3339->1539|3354->1545|3404->1574|3483->1626|3498->1632|3547->1660|3626->1712|3641->1718|3691->1747|3864->1892|3893->1893|3935->1907|3981->1925|4010->1926|4053->1941|4113->1973|4142->1974|4183->1987|4212->1988|4254->2002|4298->2018|4327->2019|4369->2033|4400->2036|4429->2037|4475->2055|4539->2091|4568->2092|4612->2108|4716->2184|4745->2185|4791->2203|4867->2251|4896->2252|4937->2265|4966->2266|4995->2267|5043->2287|5072->2288|5116->2304|5193->2353|5222->2354|5260->2364|5289->2365|5339->2387|5421->2441|5450->2442|5488->2452|5517->2453|5559->2467|5615->2495|5644->2496|5687->2511|5758->2554|5787->2555
                  LINES: 28->1|31->1|33->4|34->5|34->5|34->5|35->6|36->7|36->7|41->12|41->12|41->12|44->15|44->15|44->15|44->15|45->16|46->17|46->17|46->17|47->18|48->19|48->19|48->19|50->21|51->22|53->24|58->29|60->31|65->36|66->37|66->37|66->37|67->38|67->38|67->38|67->38|70->41|71->42|72->43|72->43|72->43|73->44|73->44|73->44|74->45|74->45|74->45|78->49|78->49|79->50|79->50|79->50|80->51|81->52|81->52|82->53|82->53|84->55|84->55|84->55|85->56|85->56|85->56|86->57|86->57|86->57|87->58|87->58|87->58|88->59|89->60|89->60|89->60|89->60|89->60|90->61|90->61|91->62|92->63|92->63|92->63|92->63|93->64|95->66|95->66|96->67|96->67|98->69|98->69|98->69|99->70|100->71|100->71
                  -- GENERATED --
              */
          