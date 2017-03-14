// @SOURCE:/home/kashyap/xoworkspace/xoportal/xo_reporting_portal/conf/routes
// @HASH:5b330c619bb61c4932965e954d621df844db1010
// @DATE:Tue Mar 14 10:00:26 IST 2017


import scala.language.reflectiveCalls
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String): Unit = {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Assets_at0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at0_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:7
private[this] lazy val controllers_Assets_versioned1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("vassets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_versioned1_invoker = createInvoker(
controllers.Assets.versioned(fakeValue[String], fakeValue[Asset]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]),"GET", """""", Routes.prefix + """vassets/$file<.+>"""))
        

// @LINE:8
private[this] lazy val controllers_WebJarAssets_at2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("webjars/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_WebJarAssets_at2_invoker = createInvoker(
controllers.WebJarAssets.at(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.WebJarAssets", "at", Seq(classOf[String]),"GET", """""", Routes.prefix + """webjars/$file<.+>"""))
        

// @LINE:11
private[this] lazy val com_xo_web_controllers_XOBaseController_cspReportParser3_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("cspreport"))))
private[this] lazy val com_xo_web_controllers_XOBaseController_cspReportParser3_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).cspReportParser,
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "cspReportParser", Nil,"POST", """ Security related requests and reports """, Routes.prefix + """cspreport"""))
        

// @LINE:12
private[this] lazy val controllers_Assets_at4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("crossdomain.xml"))))
private[this] lazy val controllers_Assets_at4_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """""", Routes.prefix + """crossdomain.xml"""))
        

// @LINE:13
private[this] lazy val com_xo_web_controllers_XOBaseController_accessDenied5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("accessdenied"))))
private[this] lazy val com_xo_web_controllers_XOBaseController_accessDenied5_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).accessDenied,
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "accessDenied", Nil,"GET", """""", Routes.prefix + """accessdenied"""))
        

// @LINE:14
private[this] lazy val com_xo_web_controllers_UserController_jsonAccessDenied6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jsonaccessdenied"))))
private[this] lazy val com_xo_web_controllers_UserController_jsonAccessDenied6_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).jsonAccessDenied(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "jsonAccessDenied", Nil,"GET", """""", Routes.prefix + """jsonaccessdenied"""))
        

// @LINE:16
private[this] lazy val com_xo_web_controllers_UserController_renderLoginPage7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val com_xo_web_controllers_UserController_renderLoginPage7_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderLoginPage(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "renderLoginPage", Nil,"GET", """""", Routes.prefix + """"""))
        

// @LINE:19
private[this] lazy val com_xo_web_controllers_UserController_login8_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("auth/login"))))
private[this] lazy val com_xo_web_controllers_UserController_login8_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).login(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "login", Nil,"POST", """ REST services""", Routes.prefix + """auth/login"""))
        

// @LINE:20
private[this] lazy val com_xo_web_controllers_UserController_xossoCallBackHandler9_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logincallback"))))
private[this] lazy val com_xo_web_controllers_UserController_xossoCallBackHandler9_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).xossoCallBackHandler(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "xossoCallBackHandler", Nil,"GET", """""", Routes.prefix + """logincallback"""))
        

// @LINE:23
private[this] lazy val com_xo_web_controllers_UserController_logout10_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
private[this] lazy val com_xo_web_controllers_UserController_logout10_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).logout(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "logout", Seq(classOf[String]),"GET", """ User Logout""", Routes.prefix + """logout"""))
        

// @LINE:26
private[this] lazy val com_xo_web_controllers_UserController_pageDispatcher11_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("pagedispatcher"))))
private[this] lazy val com_xo_web_controllers_UserController_pageDispatcher11_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).pageDispatcher(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "pageDispatcher", Seq(classOf[String], classOf[String]),"GET", """ Xoportal Pages""", Routes.prefix + """pagedispatcher"""))
        

// @LINE:29
private[this] lazy val com_xo_web_controllers_ResourceTypeController_readAllAsKeyValue12_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("resourcetypes/keyvalue"))))
private[this] lazy val com_xo_web_controllers_ResourceTypeController_readAllAsKeyValue12_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ResourceTypeController]).readAllAsKeyValue(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ResourceTypeController", "readAllAsKeyValue", Nil,"GET", """ Resource types based requests""", Routes.prefix + """resourcetypes/keyvalue"""))
        

// @LINE:32
private[this] lazy val com_xo_web_controllers_UserController_toggleActiveStatus13_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/toggleactive/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserController_toggleActiveStatus13_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """ User REST services""", Routes.prefix + """users/toggleactive/$userId<[^/]+>"""))
        

// @LINE:33
private[this] lazy val com_xo_web_controllers_UserController_resetPasswordForUser14_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/resetpassword/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserController_resetPasswordForUser14_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPasswordForUser(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "resetPasswordForUser", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """users/resetpassword/$userId<[^/]+>"""))
        

// @LINE:34
private[this] lazy val com_xo_web_controllers_UserController_renderResetPasswordPage15_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/resetpasswordpage"))))
private[this] lazy val com_xo_web_controllers_UserController_renderResetPasswordPage15_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderResetPasswordPage(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "renderResetPasswordPage", Seq(classOf[String]),"GET", """""", Routes.prefix + """users/resetpasswordpage"""))
        

// @LINE:35
private[this] lazy val com_xo_web_controllers_UserController_resetPassword16_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/resetpassword"))))
private[this] lazy val com_xo_web_controllers_UserController_resetPassword16_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPassword(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "resetPassword", Seq(classOf[String]),"POST", """""", Routes.prefix + """users/resetpassword"""))
        

// @LINE:36
private[this] lazy val com_xo_web_controllers_UserController_verify17_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/verify"))))
private[this] lazy val com_xo_web_controllers_UserController_verify17_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).verify(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "verify", Seq(classOf[String]),"GET", """""", Routes.prefix + """users/verify"""))
        

// @LINE:37
private[this] lazy val com_xo_web_controllers_UserController_changePassword18_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/changepassword"))))
private[this] lazy val com_xo_web_controllers_UserController_changePassword18_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).changePassword(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "changePassword", Nil,"POST", """""", Routes.prefix + """users/changepassword"""))
        

// @LINE:38
private[this] lazy val com_xo_web_controllers_UserController_forgotPasswordPage19_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/forgotpassword"))))
private[this] lazy val com_xo_web_controllers_UserController_forgotPasswordPage19_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPasswordPage(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "forgotPasswordPage", Nil,"GET", """""", Routes.prefix + """users/forgotpassword"""))
        

// @LINE:39
private[this] lazy val com_xo_web_controllers_UserController_forgotPassword20_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/forgotpassword"))))
private[this] lazy val com_xo_web_controllers_UserController_forgotPassword20_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPassword(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "forgotPassword", Nil,"POST", """""", Routes.prefix + """users/forgotpassword"""))
        

// @LINE:40
private[this] lazy val com_xo_web_controllers_UserController_uploadUsers21_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/upload"))))
private[this] lazy val com_xo_web_controllers_UserController_uploadUsers21_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).uploadUsers(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "uploadUsers", Nil,"POST", """""", Routes.prefix + """users/upload"""))
        

// @LINE:41
private[this] lazy val com_xo_web_controllers_UserController_readAllAsKeyValue22_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/keyvalue"))))
private[this] lazy val com_xo_web_controllers_UserController_readAllAsKeyValue22_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAllAsKeyValue(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "readAllAsKeyValue", Nil,"GET", """""", Routes.prefix + """users/keyvalue"""))
        

// @LINE:42
private[this] lazy val com_xo_web_controllers_UserController_read23_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserController_read23_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "read", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """users/$userId<[^/]+>"""))
        

// @LINE:43
private[this] lazy val com_xo_web_controllers_UserController_update24_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserController_update24_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """users/$userId<[^/]+>"""))
        

// @LINE:44
private[this] lazy val com_xo_web_controllers_UserController_delete25_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserController_delete25_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """users/$userId<[^/]+>"""))
        

// @LINE:45
private[this] lazy val com_xo_web_controllers_UserController_readAll26_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users"))))
private[this] lazy val com_xo_web_controllers_UserController_readAll26_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "readAll", Nil,"GET", """""", Routes.prefix + """users"""))
        

// @LINE:46
private[this] lazy val com_xo_web_controllers_UserController_create27_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("users"))))
private[this] lazy val com_xo_web_controllers_UserController_create27_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "create", Nil,"POST", """""", Routes.prefix + """users"""))
        

// @LINE:49
private[this] lazy val com_xo_web_controllers_RoleController_readAll28_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles"))))
private[this] lazy val com_xo_web_controllers_RoleController_readAll28_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readAll", Nil,"GET", """ Role management""", Routes.prefix + """roles"""))
        

// @LINE:50
private[this] lazy val com_xo_web_controllers_RoleController_create29_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles"))))
private[this] lazy val com_xo_web_controllers_RoleController_create29_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "create", Nil,"POST", """""", Routes.prefix + """roles"""))
        

// @LINE:51
private[this] lazy val com_xo_web_controllers_RoleController_readAllAsKeyValue30_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/keyvalue"))))
private[this] lazy val com_xo_web_controllers_RoleController_readAllAsKeyValue30_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAllAsKeyValue(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readAllAsKeyValue", Nil,"GET", """""", Routes.prefix + """roles/keyvalue"""))
        

// @LINE:52
private[this] lazy val com_xo_web_controllers_RoleController_toggleActiveStatus31_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/toggleactive/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RoleController_toggleActiveStatus31_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """roles/toggleactive/$id<[^/]+>"""))
        

// @LINE:53
private[this] lazy val com_xo_web_controllers_RoleController_read32_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RoleController_read32_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "read", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """roles/$id<[^/]+>"""))
        

// @LINE:54
private[this] lazy val com_xo_web_controllers_RoleController_update33_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RoleController_update33_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """roles/$id<[^/]+>"""))
        

// @LINE:55
private[this] lazy val com_xo_web_controllers_RoleController_delete34_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RoleController_delete34_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """roles/$roleId<[^/]+>"""))
        

// @LINE:56
private[this] lazy val com_xo_web_controllers_RoleController_readUnassigned35_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("roles/unassigned/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RoleController_readUnassigned35_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readUnassigned(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readUnassigned", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """roles/unassigned/$id<[^/]+>"""))
        

// @LINE:59
private[this] lazy val com_xo_web_controllers_PermissionController_read36_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("permissions/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_PermissionController_read36_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "read", Seq(classOf[Integer]),"GET", """Permission management""", Routes.prefix + """permissions/$roleId<[^/]+>"""))
        

// @LINE:60
private[this] lazy val com_xo_web_controllers_PermissionController_readAll37_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("permissions"))))
private[this] lazy val com_xo_web_controllers_PermissionController_readAll37_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readAll", Nil,"GET", """""", Routes.prefix + """permissions"""))
        

// @LINE:61
private[this] lazy val com_xo_web_controllers_PermissionController_toggleActiveStatus38_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("permissions/toggleactive/"),DynamicPart("id", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_PermissionController_toggleActiveStatus38_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """permissions/toggleactive/$id<[^/]+>"""))
        

// @LINE:62
private[this] lazy val com_xo_web_controllers_PermissionController_readUnAssignedRolePermissions39_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("permissions/unassigned/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_PermissionController_readUnAssignedRolePermissions39_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedRolePermissions(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readUnAssignedRolePermissions", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """permissions/unassigned/$roleId<[^/]+>"""))
        

// @LINE:63
private[this] lazy val com_xo_web_controllers_PermissionController_readUnAssignedUserPermissions40_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("permissions/unassigned_user_permissions/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_PermissionController_readUnAssignedUserPermissions40_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedUserPermissions(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readUnAssignedUserPermissions", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """permissions/unassigned_user_permissions/$userId<[^/]+>"""))
        

// @LINE:67
private[this] lazy val com_xo_web_controllers_UserRoleController_readAll41_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userroles/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserRoleController_readAll41_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readAll(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "readAll", Seq(classOf[Integer]),"GET", """ user role REST services""", Routes.prefix + """userroles/$userId<[^/]+>"""))
        

// @LINE:68
private[this] lazy val com_xo_web_controllers_UserRoleController_create42_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userroles/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserRoleController_create42_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).create(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "create", Seq(classOf[Integer], classOf[Integer]),"POST", """""", Routes.prefix + """userroles/$userId<[^/]+>/$roleId<[^/]+>"""))
        

// @LINE:69
private[this] lazy val com_xo_web_controllers_UserRoleController_delete43_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userroles/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserRoleController_delete43_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).delete(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "delete", Seq(classOf[Integer], classOf[Integer]),"DELETE", """""", Routes.prefix + """userroles/$userId<[^/]+>/$roleId<[^/]+>"""))
        

// @LINE:70
private[this] lazy val com_xo_web_controllers_UserRoleController_toggleActiveStatus44_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userroles/toggleactive/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserRoleController_toggleActiveStatus44_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).toggleActiveStatus(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "toggleActiveStatus", Seq(classOf[Integer], classOf[Integer]),"PUT", """""", Routes.prefix + """userroles/toggleactive/$userId<[^/]+>/$roleId<[^/]+>"""))
        

// @LINE:71
private[this] lazy val com_xo_web_controllers_UserRoleController_readRolesCount45_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userroles/roles_count/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserRoleController_readRolesCount45_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readRolesCount(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "readRolesCount", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """userroles/roles_count/$roleId<[^/]+>"""))
        

// @LINE:74
private[this] lazy val com_xo_web_controllers_RolePermissionController_readAll46_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolepermission/"),DynamicPart("roleId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionController_readAll46_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).readAll(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "readAll", Seq(classOf[Integer]),"GET", """Roles Permission mgmt""", Routes.prefix + """rolepermission/$roleId<[^/]+>"""))
        

// @LINE:75
private[this] lazy val com_xo_web_controllers_RolePermissionController_toggleActiveStatus47_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolepermission/toggleactive/"),DynamicPart("rolePermissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionController_toggleActiveStatus47_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """rolepermission/toggleactive/$rolePermissionId<[^/]+>"""))
        

// @LINE:76
private[this] lazy val com_xo_web_controllers_RolePermissionController_delete48_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolepermission/"),DynamicPart("rolePermissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionController_delete48_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """rolepermission/$rolePermissionId<[^/]+>"""))
        

// @LINE:77
private[this] lazy val com_xo_web_controllers_RolePermissionController_create49_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolepermission/"),DynamicPart("roleId", """[^/]+""",true),StaticPart("/"),DynamicPart("permissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionController_create49_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).create(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "create", Seq(classOf[Integer], classOf[Integer]),"POST", """""", Routes.prefix + """rolepermission/$roleId<[^/]+>/$permissionId<[^/]+>"""))
        

// @LINE:80
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAll50_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp"))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAll50_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAll", Nil,"GET", """Role based RLP mgmt""", Routes.prefix + """rolerlp"""))
        

// @LINE:81
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAllByRoleAndResourType51_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp/"),DynamicPart("roleId", """[^/]+""",true),StaticPart("/"),DynamicPart("resourceTypeId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAllByRoleAndResourType51_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllByRoleAndResourType(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAllByRoleAndResourType", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """rolerlp/$roleId<[^/]+>/$resourceTypeId<[^/]+>"""))
        

// @LINE:82
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAllEntityInstances52_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp/ressourceinstanceids/"),DynamicPart("roleId", """[^/]+""",true),StaticPart("/"),DynamicPart("resourceTypeId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_readAllEntityInstances52_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllEntityInstances(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAllEntityInstances", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """rolerlp/ressourceinstanceids/$roleId<[^/]+>/$resourceTypeId<[^/]+>"""))
        

// @LINE:83
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_toggleActiveStatus53_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp/toggleactive/"),DynamicPart("rolePermissionId", """[^/]+""",true),StaticPart("/"),DynamicPart("entityId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_toggleActiveStatus53_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).toggleActiveStatus(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "toggleActiveStatus", Seq(classOf[Integer], classOf[String]),"PUT", """""", Routes.prefix + """rolerlp/toggleactive/$rolePermissionId<[^/]+>/$entityId<[^/]+>"""))
        

// @LINE:84
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_delete54_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp/"),DynamicPart("rolePermissionId", """[^/]+""",true),StaticPart("/"),DynamicPart("entityId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_delete54_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).delete(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "delete", Seq(classOf[Integer], classOf[String]),"DELETE", """""", Routes.prefix + """rolerlp/$rolePermissionId<[^/]+>/$entityId<[^/]+>"""))
        

// @LINE:85
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_create55_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rolerlp"))))
private[this] lazy val com_xo_web_controllers_RolePermissionResourceInstanceController_create55_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "create", Nil,"POST", """""", Routes.prefix + """rolerlp"""))
        

// @LINE:88
private[this] lazy val com_xo_web_controllers_UserPermissionController_readAll56_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userpermission/"),DynamicPart("userId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionController_readAll56_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).readAll(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "readAll", Seq(classOf[Integer]),"GET", """User Permission mgmt """, Routes.prefix + """userpermission/$userId<[^/]+>"""))
        

// @LINE:89
private[this] lazy val com_xo_web_controllers_UserPermissionController_create57_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userpermission/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("permissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionController_create57_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).create(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "create", Seq(classOf[Integer], classOf[Integer]),"POST", """""", Routes.prefix + """userpermission/$userId<[^/]+>/$permissionId<[^/]+>"""))
        

// @LINE:90
private[this] lazy val com_xo_web_controllers_UserPermissionController_delete58_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userpermission/"),DynamicPart("userpermissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionController_delete58_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """userpermission/$userpermissionId<[^/]+>"""))
        

// @LINE:91
private[this] lazy val com_xo_web_controllers_UserPermissionController_toggleActiveStatus59_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userpermission/toggleactive/"),DynamicPart("userpermissionId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionController_toggleActiveStatus59_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """userpermission/toggleactive/$userpermissionId<[^/]+>"""))
        

// @LINE:94
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAll60_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp"))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAll60_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAll", Nil,"GET", """User based RLP mgmt""", Routes.prefix + """userrlp"""))
        

// @LINE:95
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAllByUserAndResourType61_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("resourceTypeId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAllByUserAndResourType61_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllByUserAndResourType(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAllByUserAndResourType", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """userrlp/$userId<[^/]+>/$resourceTypeId<[^/]+>"""))
        

// @LINE:96
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAllEntityInstances62_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp/ressourceinstanceids/"),DynamicPart("userId", """[^/]+""",true),StaticPart("/"),DynamicPart("resourceTypeId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_readAllEntityInstances62_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllEntityInstances(fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAllEntityInstances", Seq(classOf[Integer], classOf[Integer]),"GET", """""", Routes.prefix + """userrlp/ressourceinstanceids/$userId<[^/]+>/$resourceTypeId<[^/]+>"""))
        

// @LINE:97
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_toggleActiveStatus63_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp/toggleactive/"),DynamicPart("userPermissionId", """[^/]+""",true),StaticPart("/"),DynamicPart("entityId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_toggleActiveStatus63_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).toggleActiveStatus(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "toggleActiveStatus", Seq(classOf[Integer], classOf[String]),"PUT", """""", Routes.prefix + """userrlp/toggleactive/$userPermissionId<[^/]+>/$entityId<[^/]+>"""))
        

// @LINE:98
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_delete64_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp/"),DynamicPart("userPermissionId", """[^/]+""",true),StaticPart("/"),DynamicPart("entityId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_delete64_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).delete(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "delete", Seq(classOf[Integer], classOf[String]),"DELETE", """""", Routes.prefix + """userrlp/$userPermissionId<[^/]+>/$entityId<[^/]+>"""))
        

// @LINE:99
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_create65_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("userrlp"))))
private[this] lazy val com_xo_web_controllers_UserPermissionResourceInstanceController_create65_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "create", Nil,"POST", """""", Routes.prefix + """userrlp"""))
        

// @LINE:105
private[this] lazy val com_xo_web_controllers_DashboardController_loadDashboardGroupData66_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard"))))
private[this] lazy val com_xo_web_controllers_DashboardController_loadDashboardGroupData66_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadDashboardGroupData(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadDashboardGroupData", Nil,"POST", """ Tableau related pages""", Routes.prefix + """dashboard"""))
        

// @LINE:106
private[this] lazy val com_xo_web_controllers_DashboardController_loadProjectDashboardData67_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard/project/"),DynamicPart("projectId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_DashboardController_loadProjectDashboardData67_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadProjectDashboardData(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadProjectDashboardData", Seq(classOf[String]),"POST", """""", Routes.prefix + """dashboard/project/$projectId<[^/]+>"""))
        

// @LINE:107
private[this] lazy val com_xo_web_controllers_DashboardController_loadViewData68_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("dashboard/view/"),DynamicPart("workbookId", """[^/]+""",true),StaticPart("/"),DynamicPart("viewId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_DashboardController_loadViewData68_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadViewData(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadViewData", Seq(classOf[String], classOf[String]),"POST", """""", Routes.prefix + """dashboard/view/$workbookId<[^/]+>/$viewId<[^/]+>"""))
        

// @LINE:108
private[this] lazy val com_xo_web_controllers_TableauRest_syncTableauReports69_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("sync_tableau_reports"))))
private[this] lazy val com_xo_web_controllers_TableauRest_syncTableauReports69_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauRest]).syncTableauReports,
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauRest", "syncTableauReports", Nil,"GET", """""", Routes.prefix + """sync_tableau_reports"""))
        

// @LINE:112
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_readAll70_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates"))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_readAll70_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "readAll", Nil,"GET", """""", Routes.prefix + """configtemplates"""))
        

// @LINE:113
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_create71_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates"))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_create71_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "create", Nil,"POST", """""", Routes.prefix + """configtemplates"""))
        

// @LINE:114
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_read72_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_read72_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "read", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """configtemplates/$configId<[^/]+>"""))
        

// @LINE:115
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_update73_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_update73_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configtemplates/$configId<[^/]+>"""))
        

// @LINE:116
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_delete74_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_delete74_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """configtemplates/$configId<[^/]+>"""))
        

// @LINE:117
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_update75_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_update75_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configtemplates/$configId<[^/]+>"""))
        

// @LINE:118
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_isConfigExist76_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/search/"),DynamicPart("configShortName", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_isConfigExist76_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).isConfigExist(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "isConfigExist", Seq(classOf[String]),"GET", """""", Routes.prefix + """configtemplates/search/$configShortName<[^/]+>"""))
        

// @LINE:119
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_toggleActiveStatus77_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configtemplates/toggleactive/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigTemplateController_toggleActiveStatus77_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configtemplates/toggleactive/$configId<[^/]+>"""))
        

// @LINE:121
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_create78_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances"))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_create78_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "create", Nil,"POST", """""", Routes.prefix + """configinstances"""))
        

// @LINE:122
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_readAllInstances79_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances"))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_readAllInstances79_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAllInstances(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "readAllInstances", Nil,"GET", """""", Routes.prefix + """configinstances"""))
        

// @LINE:123
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_read80_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_read80_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "read", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """configinstances/$configId<[^/]+>"""))
        

// @LINE:124
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_update81_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_update81_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configinstances/$configId<[^/]+>"""))
        

// @LINE:125
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_delete82_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_delete82_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """configinstances/$configId<[^/]+>"""))
        

// @LINE:126
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_update83_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_update83_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).update(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "update", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configinstances/$configId<[^/]+>"""))
        

// @LINE:127
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_isConfigExist84_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/search/"),DynamicPart("configTemplateId", """[^/]+""",true),StaticPart("/"),DynamicPart("configShortName", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_isConfigExist84_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).isConfigExist(fakeValue[Integer], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "isConfigExist", Seq(classOf[Integer], classOf[String]),"GET", """""", Routes.prefix + """configinstances/search/$configTemplateId<[^/]+>/$configShortName<[^/]+>"""))
        

// @LINE:128
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_readAll85_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/all/"),DynamicPart("configTemplateId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_readAll85_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAll(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "readAll", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """configinstances/all/$configTemplateId<[^/]+>"""))
        

// @LINE:129
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_toggleActiveStatus86_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("configinstances/toggleactive/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoConfigInstanceController_toggleActiveStatus86_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """configinstances/toggleactive/$configId<[^/]+>"""))
        

// @LINE:133
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_toggleActiveStatus87_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clientjobconfigs/toggleactive/"),DynamicPart("clientId", """[^/]+""",true),StaticPart("/"),DynamicPart("jobId", """[^/]+""",true),StaticPart("/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_toggleActiveStatus87_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).toggleActiveStatus(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "toggleActiveStatus", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"PUT", """ Client Job configuration management""", Routes.prefix + """clientjobconfigs/toggleactive/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>"""))
        

// @LINE:134
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_runJob88_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clientjobconfigs/runJob/"),DynamicPart("clientId", """[^/]+""",true),StaticPart("/"),DynamicPart("jobId", """[^/]+""",true),StaticPart("/"),DynamicPart("configId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_runJob88_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).runJob(fakeValue[Integer], fakeValue[Integer], fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "runJob", Seq(classOf[Integer], classOf[Integer], classOf[Integer]),"PUT", """""", Routes.prefix + """clientjobconfigs/runJob/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>"""))
        

// @LINE:135
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_delete89_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clientjobconfigs"))))
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_delete89_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).delete(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "delete", Nil,"DELETE", """""", Routes.prefix + """clientjobconfigs"""))
        

// @LINE:136
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_readAll90_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clientjobconfigs"))))
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_readAll90_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "readAll", Nil,"GET", """""", Routes.prefix + """clientjobconfigs"""))
        

// @LINE:137
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_create91_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clientjobconfigs"))))
private[this] lazy val com_xo_web_controllers_XoClientJobConfigController_create91_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "create", Nil,"POST", """""", Routes.prefix + """clientjobconfigs"""))
        

// @LINE:140
private[this] lazy val com_xo_web_controllers_XoClientController_readAll92_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("clients"))))
private[this] lazy val com_xo_web_controllers_XoClientController_readAll92_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientController", "readAll", Nil,"GET", """ Clients mgmt""", Routes.prefix + """clients"""))
        

// @LINE:143
private[this] lazy val com_xo_web_controllers_XoJobController_readAll93_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("jobs"))))
private[this] lazy val com_xo_web_controllers_XoJobController_readAll93_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoJobController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoJobController", "readAll", Nil,"GET", """ Job mgmt""", Routes.prefix + """jobs"""))
        

// @LINE:145
private[this] lazy val com_xo_web_controllers_XOBaseController_heartBeat94_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("heartbeat"))))
private[this] lazy val com_xo_web_controllers_XOBaseController_heartBeat94_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).heartBeat(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "heartBeat", Nil,"GET", """""", Routes.prefix + """heartbeat"""))
        

// @LINE:148
private[this] lazy val com_xo_web_controllers_ViewGroupController_readAllAsKeyValue95_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/keyvalue"))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_readAllAsKeyValue95_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAllAsKeyValue(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readAllAsKeyValue", Nil,"GET", """View Group mgmt""", Routes.prefix + """viewgroup/keyvalue"""))
        

// @LINE:149
private[this] lazy val com_xo_web_controllers_ViewGroupController_toggleActiveStatus96_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/toggleactive/"),DynamicPart("viewGroupId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_toggleActiveStatus96_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).toggleActiveStatus(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "toggleActiveStatus", Seq(classOf[Integer]),"PUT", """""", Routes.prefix + """viewgroup/toggleactive/$viewGroupId<[^/]+>"""))
        

// @LINE:150
private[this] lazy val com_xo_web_controllers_ViewGroupController_delete97_route = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/"),DynamicPart("viewGroupId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_delete97_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).delete(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "delete", Seq(classOf[Integer]),"DELETE", """""", Routes.prefix + """viewgroup/$viewGroupId<[^/]+>"""))
        

// @LINE:151
private[this] lazy val com_xo_web_controllers_ViewGroupController_readAll98_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/all"))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_readAll98_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readAll", Nil,"GET", """""", Routes.prefix + """viewgroup/all"""))
        

// @LINE:152
private[this] lazy val com_xo_web_controllers_ViewGroupController_read99_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/"),DynamicPart("viewGroupId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_read99_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).read(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "read", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """viewgroup/$viewGroupId<[^/]+>"""))
        

// @LINE:153
private[this] lazy val com_xo_web_controllers_ViewGroupController_create100_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup"))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_create100_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).create(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "create", Nil,"POST", """""", Routes.prefix + """viewgroup"""))
        

// @LINE:154
private[this] lazy val com_xo_web_controllers_ViewGroupController_update101_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/update"))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_update101_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).update(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "update", Nil,"PUT", """""", Routes.prefix + """viewgroup/update"""))
        

// @LINE:155
private[this] lazy val com_xo_web_controllers_ViewGroupController_readGroupCount102_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("viewgroup/groupcount/"),DynamicPart("viewGroupId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_ViewGroupController_readGroupCount102_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readGroupCount(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readGroupCount", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """viewgroup/groupcount/$viewGroupId<[^/]+>"""))
        

// @LINE:158
private[this] lazy val com_xo_web_controllers_TableauViewController_readAllAsKeyValue103_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/keyvalue"))))
private[this] lazy val com_xo_web_controllers_TableauViewController_readAllAsKeyValue103_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAllAsKeyValue(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "readAllAsKeyValue", Nil,"GET", """ Tableau View mgmt""", Routes.prefix + """tableauview/keyvalue"""))
        

// @LINE:159
private[this] lazy val com_xo_web_controllers_TableauViewController_toggleActiveStatus104_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/toggleactive/"),DynamicPart("tableauViewId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_TableauViewController_toggleActiveStatus104_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleActiveStatus(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "toggleActiveStatus", Seq(classOf[String]),"PUT", """""", Routes.prefix + """tableauview/toggleactive/$tableauViewId<[^/]+>"""))
        

// @LINE:160
private[this] lazy val com_xo_web_controllers_TableauViewController_readAll105_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/all"))))
private[this] lazy val com_xo_web_controllers_TableauViewController_readAll105_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAll(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "readAll", Nil,"GET", """""", Routes.prefix + """tableauview/all"""))
        

// @LINE:161
private[this] lazy val com_xo_web_controllers_TableauViewController_read106_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/"),DynamicPart("tableauViewId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_TableauViewController_read106_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).read(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "read", Seq(classOf[String]),"GET", """""", Routes.prefix + """tableauview/$tableauViewId<[^/]+>"""))
        

// @LINE:162
private[this] lazy val com_xo_web_controllers_TableauViewController_update107_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/update"))))
private[this] lazy val com_xo_web_controllers_TableauViewController_update107_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).update(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "update", Nil,"PUT", """""", Routes.prefix + """tableauview/update"""))
        

// @LINE:163
private[this] lazy val com_xo_web_controllers_TableauViewController_toggleDashboardStatus108_route = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/toggledashboard/"),DynamicPart("tableauViewId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_TableauViewController_toggleDashboardStatus108_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleDashboardStatus(fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "toggleDashboardStatus", Seq(classOf[String]),"PUT", """""", Routes.prefix + """tableauview/toggledashboard/$tableauViewId<[^/]+>"""))
        

// @LINE:164
private[this] lazy val com_xo_web_controllers_TableauViewController_isDashboardExist109_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tableauview/checkdashboard/"),DynamicPart("viewGroupId", """[^/]+""",true))))
private[this] lazy val com_xo_web_controllers_TableauViewController_isDashboardExist109_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).isDashboardExist(fakeValue[Integer]),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "isDashboardExist", Seq(classOf[Integer]),"GET", """""", Routes.prefix + """tableauview/checkdashboard/$viewGroupId<[^/]+>"""))
        

// @LINE:167
private[this] lazy val com_xo_web_controllers_DashboardController_getDiffusionMap110_route = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("diffusionMap"))))
private[this] lazy val com_xo_web_controllers_DashboardController_getDiffusionMap110_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getDiffusionMap(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "getDiffusionMap", Nil,"POST", """ Diffusion Map""", Routes.prefix + """diffusionMap"""))
        

// @LINE:168
private[this] lazy val com_xo_web_controllers_DashboardController_getFilterList111_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("filterlist"))))
private[this] lazy val com_xo_web_controllers_DashboardController_getFilterList111_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getFilterList(),
HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "getFilterList", Nil,"GET", """""", Routes.prefix + """filterlist"""))
        
def documentation = List(("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """vassets/$file<.+>""","""controllers.Assets.versioned(path:String = "/public", file:Asset)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """webjars/$file<.+>""","""controllers.WebJarAssets.at(file:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """cspreport""","""@com.xo.web.controllers.XOBaseController@.cspReportParser"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """crossdomain.xml""","""controllers.Assets.at(path:String = "/public", file:String ?= "security/crossdomain.xml")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """accessdenied""","""@com.xo.web.controllers.XOBaseController@.accessDenied"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jsonaccessdenied""","""@com.xo.web.controllers.UserController@.jsonAccessDenied()"""),("""GET""", prefix,"""@com.xo.web.controllers.UserController@.renderLoginPage()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """auth/login""","""@com.xo.web.controllers.UserController@.login()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logincallback""","""@com.xo.web.controllers.UserController@.xossoCallBackHandler()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""@com.xo.web.controllers.UserController@.logout(authToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """pagedispatcher""","""@com.xo.web.controllers.UserController@.pageDispatcher(authToken:String, currentPage:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """resourcetypes/keyvalue""","""@com.xo.web.controllers.ResourceTypeController@.readAllAsKeyValue()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/toggleactive/$userId<[^/]+>""","""@com.xo.web.controllers.UserController@.toggleActiveStatus(userId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/resetpassword/$userId<[^/]+>""","""@com.xo.web.controllers.UserController@.resetPasswordForUser(userId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/resetpasswordpage""","""@com.xo.web.controllers.UserController@.renderResetPasswordPage(authToken:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/resetpassword""","""@com.xo.web.controllers.UserController@.resetPassword(authToken:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/verify""","""@com.xo.web.controllers.UserController@.verify(token:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/changepassword""","""@com.xo.web.controllers.UserController@.changePassword()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/forgotpassword""","""@com.xo.web.controllers.UserController@.forgotPasswordPage()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/forgotpassword""","""@com.xo.web.controllers.UserController@.forgotPassword()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/upload""","""@com.xo.web.controllers.UserController@.uploadUsers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/keyvalue""","""@com.xo.web.controllers.UserController@.readAllAsKeyValue()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$userId<[^/]+>""","""@com.xo.web.controllers.UserController@.read(userId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$userId<[^/]+>""","""@com.xo.web.controllers.UserController@.update(userId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users/$userId<[^/]+>""","""@com.xo.web.controllers.UserController@.delete(userId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users""","""@com.xo.web.controllers.UserController@.readAll()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """users""","""@com.xo.web.controllers.UserController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles""","""@com.xo.web.controllers.RoleController@.readAll()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles""","""@com.xo.web.controllers.RoleController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/keyvalue""","""@com.xo.web.controllers.RoleController@.readAllAsKeyValue()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/toggleactive/$id<[^/]+>""","""@com.xo.web.controllers.RoleController@.toggleActiveStatus(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/$id<[^/]+>""","""@com.xo.web.controllers.RoleController@.read(id:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/$id<[^/]+>""","""@com.xo.web.controllers.RoleController@.update(id:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/$roleId<[^/]+>""","""@com.xo.web.controllers.RoleController@.delete(roleId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """roles/unassigned/$id<[^/]+>""","""@com.xo.web.controllers.RoleController@.readUnassigned(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """permissions/$roleId<[^/]+>""","""@com.xo.web.controllers.PermissionController@.read(roleId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """permissions""","""@com.xo.web.controllers.PermissionController@.readAll()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """permissions/toggleactive/$id<[^/]+>""","""@com.xo.web.controllers.PermissionController@.toggleActiveStatus(id:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """permissions/unassigned/$roleId<[^/]+>""","""@com.xo.web.controllers.PermissionController@.readUnAssignedRolePermissions(roleId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """permissions/unassigned_user_permissions/$userId<[^/]+>""","""@com.xo.web.controllers.PermissionController@.readUnAssignedUserPermissions(userId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userroles/$userId<[^/]+>""","""@com.xo.web.controllers.UserRoleController@.readAll(userId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userroles/$userId<[^/]+>/$roleId<[^/]+>""","""@com.xo.web.controllers.UserRoleController@.create(userId:Integer, roleId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userroles/$userId<[^/]+>/$roleId<[^/]+>""","""@com.xo.web.controllers.UserRoleController@.delete(userId:Integer, roleId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userroles/toggleactive/$userId<[^/]+>/$roleId<[^/]+>""","""@com.xo.web.controllers.UserRoleController@.toggleActiveStatus(userId:Integer, roleId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userroles/roles_count/$roleId<[^/]+>""","""@com.xo.web.controllers.UserRoleController@.readRolesCount(roleId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolepermission/$roleId<[^/]+>""","""@com.xo.web.controllers.RolePermissionController@.readAll(roleId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolepermission/toggleactive/$rolePermissionId<[^/]+>""","""@com.xo.web.controllers.RolePermissionController@.toggleActiveStatus(rolePermissionId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolepermission/$rolePermissionId<[^/]+>""","""@com.xo.web.controllers.RolePermissionController@.delete(rolePermissionId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolepermission/$roleId<[^/]+>/$permissionId<[^/]+>""","""@com.xo.web.controllers.RolePermissionController@.create(roleId:Integer, permissionId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp/$roleId<[^/]+>/$resourceTypeId<[^/]+>""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.readAllByRoleAndResourType(roleId:Integer, resourceTypeId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp/ressourceinstanceids/$roleId<[^/]+>/$resourceTypeId<[^/]+>""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.readAllEntityInstances(roleId:Integer, resourceTypeId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp/toggleactive/$rolePermissionId<[^/]+>/$entityId<[^/]+>""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.toggleActiveStatus(rolePermissionId:Integer, entityId:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp/$rolePermissionId<[^/]+>/$entityId<[^/]+>""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.delete(rolePermissionId:Integer, entityId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rolerlp""","""@com.xo.web.controllers.RolePermissionResourceInstanceController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userpermission/$userId<[^/]+>""","""@com.xo.web.controllers.UserPermissionController@.readAll(userId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userpermission/$userId<[^/]+>/$permissionId<[^/]+>""","""@com.xo.web.controllers.UserPermissionController@.create(userId:Integer, permissionId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userpermission/$userpermissionId<[^/]+>""","""@com.xo.web.controllers.UserPermissionController@.delete(userpermissionId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userpermission/toggleactive/$userpermissionId<[^/]+>""","""@com.xo.web.controllers.UserPermissionController@.toggleActiveStatus(userpermissionId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp/$userId<[^/]+>/$resourceTypeId<[^/]+>""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.readAllByUserAndResourType(userId:Integer, resourceTypeId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp/ressourceinstanceids/$userId<[^/]+>/$resourceTypeId<[^/]+>""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.readAllEntityInstances(userId:Integer, resourceTypeId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp/toggleactive/$userPermissionId<[^/]+>/$entityId<[^/]+>""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.toggleActiveStatus(userPermissionId:Integer, entityId:String)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp/$userPermissionId<[^/]+>/$entityId<[^/]+>""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.delete(userPermissionId:Integer, entityId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """userrlp""","""@com.xo.web.controllers.UserPermissionResourceInstanceController@.create()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard""","""@com.xo.web.controllers.DashboardController@.loadDashboardGroupData()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard/project/$projectId<[^/]+>""","""@com.xo.web.controllers.DashboardController@.loadProjectDashboardData(projectId:String)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """dashboard/view/$workbookId<[^/]+>/$viewId<[^/]+>""","""@com.xo.web.controllers.DashboardController@.loadViewData(workbookId:String, viewId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """sync_tableau_reports""","""@com.xo.web.controllers.TableauRest@.syncTableauReports"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates""","""@com.xo.web.controllers.XoConfigTemplateController@.readAll()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates""","""@com.xo.web.controllers.XoConfigTemplateController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.read(configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.update(configId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.delete(configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.update(configId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/search/$configShortName<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.isConfigExist(configShortName:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configtemplates/toggleactive/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigTemplateController@.toggleActiveStatus(configId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances""","""@com.xo.web.controllers.XoConfigInstanceController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances""","""@com.xo.web.controllers.XoConfigInstanceController@.readAllInstances()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.read(configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.update(configId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.delete(configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.update(configId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/search/$configTemplateId<[^/]+>/$configShortName<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.isConfigExist(configTemplateId:Integer, configShortName:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/all/$configTemplateId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.readAll(configTemplateId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """configinstances/toggleactive/$configId<[^/]+>""","""@com.xo.web.controllers.XoConfigInstanceController@.toggleActiveStatus(configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clientjobconfigs/toggleactive/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>""","""@com.xo.web.controllers.XoClientJobConfigController@.toggleActiveStatus(clientId:Integer, jobId:Integer, configId:Integer)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clientjobconfigs/runJob/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>""","""@com.xo.web.controllers.XoClientJobConfigController@.runJob(clientId:Integer, jobId:Integer, configId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clientjobconfigs""","""@com.xo.web.controllers.XoClientJobConfigController@.delete()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clientjobconfigs""","""@com.xo.web.controllers.XoClientJobConfigController@.readAll()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clientjobconfigs""","""@com.xo.web.controllers.XoClientJobConfigController@.create()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """clients""","""@com.xo.web.controllers.XoClientController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """jobs""","""@com.xo.web.controllers.XoJobController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """heartbeat""","""@com.xo.web.controllers.XOBaseController@.heartBeat()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/keyvalue""","""@com.xo.web.controllers.ViewGroupController@.readAllAsKeyValue()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/toggleactive/$viewGroupId<[^/]+>""","""@com.xo.web.controllers.ViewGroupController@.toggleActiveStatus(viewGroupId:Integer)"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/$viewGroupId<[^/]+>""","""@com.xo.web.controllers.ViewGroupController@.delete(viewGroupId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/all""","""@com.xo.web.controllers.ViewGroupController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/$viewGroupId<[^/]+>""","""@com.xo.web.controllers.ViewGroupController@.read(viewGroupId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup""","""@com.xo.web.controllers.ViewGroupController@.create()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/update""","""@com.xo.web.controllers.ViewGroupController@.update()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """viewgroup/groupcount/$viewGroupId<[^/]+>""","""@com.xo.web.controllers.ViewGroupController@.readGroupCount(viewGroupId:Integer)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/keyvalue""","""@com.xo.web.controllers.TableauViewController@.readAllAsKeyValue()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/toggleactive/$tableauViewId<[^/]+>""","""@com.xo.web.controllers.TableauViewController@.toggleActiveStatus(tableauViewId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/all""","""@com.xo.web.controllers.TableauViewController@.readAll()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/$tableauViewId<[^/]+>""","""@com.xo.web.controllers.TableauViewController@.read(tableauViewId:String)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/update""","""@com.xo.web.controllers.TableauViewController@.update()"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/toggledashboard/$tableauViewId<[^/]+>""","""@com.xo.web.controllers.TableauViewController@.toggleDashboardStatus(tableauViewId:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tableauview/checkdashboard/$viewGroupId<[^/]+>""","""@com.xo.web.controllers.TableauViewController@.isDashboardExist(viewGroupId:Integer)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """diffusionMap""","""@com.xo.web.controllers.DashboardController@.getDiffusionMap()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """filterlist""","""@com.xo.web.controllers.DashboardController@.getFilterList()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Assets_at0_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at0_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:7
case controllers_Assets_versioned1_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned1_invoker.call(controllers.Assets.versioned(path, file))
   }
}
        

// @LINE:8
case controllers_WebJarAssets_at2_route(params) => {
   call(params.fromPath[String]("file", None)) { (file) =>
        controllers_WebJarAssets_at2_invoker.call(controllers.WebJarAssets.at(file))
   }
}
        

// @LINE:11
case com_xo_web_controllers_XOBaseController_cspReportParser3_route(params) => {
   call { 
        com_xo_web_controllers_XOBaseController_cspReportParser3_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).cspReportParser)
   }
}
        

// @LINE:12
case controllers_Assets_at4_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromQuery[String]("file", Some("security/crossdomain.xml"))) { (path, file) =>
        controllers_Assets_at4_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:13
case com_xo_web_controllers_XOBaseController_accessDenied5_route(params) => {
   call { 
        com_xo_web_controllers_XOBaseController_accessDenied5_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).accessDenied)
   }
}
        

// @LINE:14
case com_xo_web_controllers_UserController_jsonAccessDenied6_route(params) => {
   call { 
        com_xo_web_controllers_UserController_jsonAccessDenied6_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).jsonAccessDenied())
   }
}
        

// @LINE:16
case com_xo_web_controllers_UserController_renderLoginPage7_route(params) => {
   call { 
        com_xo_web_controllers_UserController_renderLoginPage7_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderLoginPage())
   }
}
        

// @LINE:19
case com_xo_web_controllers_UserController_login8_route(params) => {
   call { 
        com_xo_web_controllers_UserController_login8_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).login())
   }
}
        

// @LINE:20
case com_xo_web_controllers_UserController_xossoCallBackHandler9_route(params) => {
   call { 
        com_xo_web_controllers_UserController_xossoCallBackHandler9_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).xossoCallBackHandler())
   }
}
        

// @LINE:23
case com_xo_web_controllers_UserController_logout10_route(params) => {
   call(params.fromQuery[String]("authToken", None)) { (authToken) =>
        com_xo_web_controllers_UserController_logout10_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).logout(authToken))
   }
}
        

// @LINE:26
case com_xo_web_controllers_UserController_pageDispatcher11_route(params) => {
   call(params.fromQuery[String]("authToken", None), params.fromQuery[String]("currentPage", None)) { (authToken, currentPage) =>
        com_xo_web_controllers_UserController_pageDispatcher11_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).pageDispatcher(authToken, currentPage))
   }
}
        

// @LINE:29
case com_xo_web_controllers_ResourceTypeController_readAllAsKeyValue12_route(params) => {
   call { 
        com_xo_web_controllers_ResourceTypeController_readAllAsKeyValue12_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ResourceTypeController]).readAllAsKeyValue())
   }
}
        

// @LINE:32
case com_xo_web_controllers_UserController_toggleActiveStatus13_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserController_toggleActiveStatus13_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).toggleActiveStatus(userId))
   }
}
        

// @LINE:33
case com_xo_web_controllers_UserController_resetPasswordForUser14_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserController_resetPasswordForUser14_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPasswordForUser(userId))
   }
}
        

// @LINE:34
case com_xo_web_controllers_UserController_renderResetPasswordPage15_route(params) => {
   call(params.fromQuery[String]("authToken", None)) { (authToken) =>
        com_xo_web_controllers_UserController_renderResetPasswordPage15_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderResetPasswordPage(authToken))
   }
}
        

// @LINE:35
case com_xo_web_controllers_UserController_resetPassword16_route(params) => {
   call(params.fromQuery[String]("authToken", None)) { (authToken) =>
        com_xo_web_controllers_UserController_resetPassword16_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPassword(authToken))
   }
}
        

// @LINE:36
case com_xo_web_controllers_UserController_verify17_route(params) => {
   call(params.fromQuery[String]("token", None)) { (token) =>
        com_xo_web_controllers_UserController_verify17_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).verify(token))
   }
}
        

// @LINE:37
case com_xo_web_controllers_UserController_changePassword18_route(params) => {
   call { 
        com_xo_web_controllers_UserController_changePassword18_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).changePassword())
   }
}
        

// @LINE:38
case com_xo_web_controllers_UserController_forgotPasswordPage19_route(params) => {
   call { 
        com_xo_web_controllers_UserController_forgotPasswordPage19_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPasswordPage())
   }
}
        

// @LINE:39
case com_xo_web_controllers_UserController_forgotPassword20_route(params) => {
   call { 
        com_xo_web_controllers_UserController_forgotPassword20_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPassword())
   }
}
        

// @LINE:40
case com_xo_web_controllers_UserController_uploadUsers21_route(params) => {
   call { 
        com_xo_web_controllers_UserController_uploadUsers21_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).uploadUsers())
   }
}
        

// @LINE:41
case com_xo_web_controllers_UserController_readAllAsKeyValue22_route(params) => {
   call { 
        com_xo_web_controllers_UserController_readAllAsKeyValue22_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAllAsKeyValue())
   }
}
        

// @LINE:42
case com_xo_web_controllers_UserController_read23_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserController_read23_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).read(userId))
   }
}
        

// @LINE:43
case com_xo_web_controllers_UserController_update24_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserController_update24_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).update(userId))
   }
}
        

// @LINE:44
case com_xo_web_controllers_UserController_delete25_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserController_delete25_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).delete(userId))
   }
}
        

// @LINE:45
case com_xo_web_controllers_UserController_readAll26_route(params) => {
   call { 
        com_xo_web_controllers_UserController_readAll26_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAll())
   }
}
        

// @LINE:46
case com_xo_web_controllers_UserController_create27_route(params) => {
   call { 
        com_xo_web_controllers_UserController_create27_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).create())
   }
}
        

// @LINE:49
case com_xo_web_controllers_RoleController_readAll28_route(params) => {
   call { 
        com_xo_web_controllers_RoleController_readAll28_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAll())
   }
}
        

// @LINE:50
case com_xo_web_controllers_RoleController_create29_route(params) => {
   call { 
        com_xo_web_controllers_RoleController_create29_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).create())
   }
}
        

// @LINE:51
case com_xo_web_controllers_RoleController_readAllAsKeyValue30_route(params) => {
   call { 
        com_xo_web_controllers_RoleController_readAllAsKeyValue30_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAllAsKeyValue())
   }
}
        

// @LINE:52
case com_xo_web_controllers_RoleController_toggleActiveStatus31_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_xo_web_controllers_RoleController_toggleActiveStatus31_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).toggleActiveStatus(id))
   }
}
        

// @LINE:53
case com_xo_web_controllers_RoleController_read32_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_xo_web_controllers_RoleController_read32_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).read(id))
   }
}
        

// @LINE:54
case com_xo_web_controllers_RoleController_update33_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_xo_web_controllers_RoleController_update33_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).update(id))
   }
}
        

// @LINE:55
case com_xo_web_controllers_RoleController_delete34_route(params) => {
   call(params.fromPath[Integer]("roleId", None)) { (roleId) =>
        com_xo_web_controllers_RoleController_delete34_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).delete(roleId))
   }
}
        

// @LINE:56
case com_xo_web_controllers_RoleController_readUnassigned35_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_xo_web_controllers_RoleController_readUnassigned35_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readUnassigned(id))
   }
}
        

// @LINE:59
case com_xo_web_controllers_PermissionController_read36_route(params) => {
   call(params.fromPath[Integer]("roleId", None)) { (roleId) =>
        com_xo_web_controllers_PermissionController_read36_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).read(roleId))
   }
}
        

// @LINE:60
case com_xo_web_controllers_PermissionController_readAll37_route(params) => {
   call { 
        com_xo_web_controllers_PermissionController_readAll37_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readAll())
   }
}
        

// @LINE:61
case com_xo_web_controllers_PermissionController_toggleActiveStatus38_route(params) => {
   call(params.fromPath[Integer]("id", None)) { (id) =>
        com_xo_web_controllers_PermissionController_toggleActiveStatus38_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).toggleActiveStatus(id))
   }
}
        

// @LINE:62
case com_xo_web_controllers_PermissionController_readUnAssignedRolePermissions39_route(params) => {
   call(params.fromPath[Integer]("roleId", None)) { (roleId) =>
        com_xo_web_controllers_PermissionController_readUnAssignedRolePermissions39_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedRolePermissions(roleId))
   }
}
        

// @LINE:63
case com_xo_web_controllers_PermissionController_readUnAssignedUserPermissions40_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_PermissionController_readUnAssignedUserPermissions40_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedUserPermissions(userId))
   }
}
        

// @LINE:67
case com_xo_web_controllers_UserRoleController_readAll41_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserRoleController_readAll41_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readAll(userId))
   }
}
        

// @LINE:68
case com_xo_web_controllers_UserRoleController_create42_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("roleId", None)) { (userId, roleId) =>
        com_xo_web_controllers_UserRoleController_create42_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).create(userId, roleId))
   }
}
        

// @LINE:69
case com_xo_web_controllers_UserRoleController_delete43_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("roleId", None)) { (userId, roleId) =>
        com_xo_web_controllers_UserRoleController_delete43_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).delete(userId, roleId))
   }
}
        

// @LINE:70
case com_xo_web_controllers_UserRoleController_toggleActiveStatus44_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("roleId", None)) { (userId, roleId) =>
        com_xo_web_controllers_UserRoleController_toggleActiveStatus44_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).toggleActiveStatus(userId, roleId))
   }
}
        

// @LINE:71
case com_xo_web_controllers_UserRoleController_readRolesCount45_route(params) => {
   call(params.fromPath[Integer]("roleId", None)) { (roleId) =>
        com_xo_web_controllers_UserRoleController_readRolesCount45_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readRolesCount(roleId))
   }
}
        

// @LINE:74
case com_xo_web_controllers_RolePermissionController_readAll46_route(params) => {
   call(params.fromPath[Integer]("roleId", None)) { (roleId) =>
        com_xo_web_controllers_RolePermissionController_readAll46_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).readAll(roleId))
   }
}
        

// @LINE:75
case com_xo_web_controllers_RolePermissionController_toggleActiveStatus47_route(params) => {
   call(params.fromPath[Integer]("rolePermissionId", None)) { (rolePermissionId) =>
        com_xo_web_controllers_RolePermissionController_toggleActiveStatus47_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).toggleActiveStatus(rolePermissionId))
   }
}
        

// @LINE:76
case com_xo_web_controllers_RolePermissionController_delete48_route(params) => {
   call(params.fromPath[Integer]("rolePermissionId", None)) { (rolePermissionId) =>
        com_xo_web_controllers_RolePermissionController_delete48_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).delete(rolePermissionId))
   }
}
        

// @LINE:77
case com_xo_web_controllers_RolePermissionController_create49_route(params) => {
   call(params.fromPath[Integer]("roleId", None), params.fromPath[Integer]("permissionId", None)) { (roleId, permissionId) =>
        com_xo_web_controllers_RolePermissionController_create49_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).create(roleId, permissionId))
   }
}
        

// @LINE:80
case com_xo_web_controllers_RolePermissionResourceInstanceController_readAll50_route(params) => {
   call { 
        com_xo_web_controllers_RolePermissionResourceInstanceController_readAll50_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAll())
   }
}
        

// @LINE:81
case com_xo_web_controllers_RolePermissionResourceInstanceController_readAllByRoleAndResourType51_route(params) => {
   call(params.fromPath[Integer]("roleId", None), params.fromPath[Integer]("resourceTypeId", None)) { (roleId, resourceTypeId) =>
        com_xo_web_controllers_RolePermissionResourceInstanceController_readAllByRoleAndResourType51_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllByRoleAndResourType(roleId, resourceTypeId))
   }
}
        

// @LINE:82
case com_xo_web_controllers_RolePermissionResourceInstanceController_readAllEntityInstances52_route(params) => {
   call(params.fromPath[Integer]("roleId", None), params.fromPath[Integer]("resourceTypeId", None)) { (roleId, resourceTypeId) =>
        com_xo_web_controllers_RolePermissionResourceInstanceController_readAllEntityInstances52_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllEntityInstances(roleId, resourceTypeId))
   }
}
        

// @LINE:83
case com_xo_web_controllers_RolePermissionResourceInstanceController_toggleActiveStatus53_route(params) => {
   call(params.fromPath[Integer]("rolePermissionId", None), params.fromPath[String]("entityId", None)) { (rolePermissionId, entityId) =>
        com_xo_web_controllers_RolePermissionResourceInstanceController_toggleActiveStatus53_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).toggleActiveStatus(rolePermissionId, entityId))
   }
}
        

// @LINE:84
case com_xo_web_controllers_RolePermissionResourceInstanceController_delete54_route(params) => {
   call(params.fromPath[Integer]("rolePermissionId", None), params.fromPath[String]("entityId", None)) { (rolePermissionId, entityId) =>
        com_xo_web_controllers_RolePermissionResourceInstanceController_delete54_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).delete(rolePermissionId, entityId))
   }
}
        

// @LINE:85
case com_xo_web_controllers_RolePermissionResourceInstanceController_create55_route(params) => {
   call { 
        com_xo_web_controllers_RolePermissionResourceInstanceController_create55_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).create())
   }
}
        

// @LINE:88
case com_xo_web_controllers_UserPermissionController_readAll56_route(params) => {
   call(params.fromPath[Integer]("userId", None)) { (userId) =>
        com_xo_web_controllers_UserPermissionController_readAll56_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).readAll(userId))
   }
}
        

// @LINE:89
case com_xo_web_controllers_UserPermissionController_create57_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("permissionId", None)) { (userId, permissionId) =>
        com_xo_web_controllers_UserPermissionController_create57_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).create(userId, permissionId))
   }
}
        

// @LINE:90
case com_xo_web_controllers_UserPermissionController_delete58_route(params) => {
   call(params.fromPath[Integer]("userpermissionId", None)) { (userpermissionId) =>
        com_xo_web_controllers_UserPermissionController_delete58_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).delete(userpermissionId))
   }
}
        

// @LINE:91
case com_xo_web_controllers_UserPermissionController_toggleActiveStatus59_route(params) => {
   call(params.fromPath[Integer]("userpermissionId", None)) { (userpermissionId) =>
        com_xo_web_controllers_UserPermissionController_toggleActiveStatus59_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).toggleActiveStatus(userpermissionId))
   }
}
        

// @LINE:94
case com_xo_web_controllers_UserPermissionResourceInstanceController_readAll60_route(params) => {
   call { 
        com_xo_web_controllers_UserPermissionResourceInstanceController_readAll60_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAll())
   }
}
        

// @LINE:95
case com_xo_web_controllers_UserPermissionResourceInstanceController_readAllByUserAndResourType61_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("resourceTypeId", None)) { (userId, resourceTypeId) =>
        com_xo_web_controllers_UserPermissionResourceInstanceController_readAllByUserAndResourType61_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllByUserAndResourType(userId, resourceTypeId))
   }
}
        

// @LINE:96
case com_xo_web_controllers_UserPermissionResourceInstanceController_readAllEntityInstances62_route(params) => {
   call(params.fromPath[Integer]("userId", None), params.fromPath[Integer]("resourceTypeId", None)) { (userId, resourceTypeId) =>
        com_xo_web_controllers_UserPermissionResourceInstanceController_readAllEntityInstances62_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllEntityInstances(userId, resourceTypeId))
   }
}
        

// @LINE:97
case com_xo_web_controllers_UserPermissionResourceInstanceController_toggleActiveStatus63_route(params) => {
   call(params.fromPath[Integer]("userPermissionId", None), params.fromPath[String]("entityId", None)) { (userPermissionId, entityId) =>
        com_xo_web_controllers_UserPermissionResourceInstanceController_toggleActiveStatus63_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).toggleActiveStatus(userPermissionId, entityId))
   }
}
        

// @LINE:98
case com_xo_web_controllers_UserPermissionResourceInstanceController_delete64_route(params) => {
   call(params.fromPath[Integer]("userPermissionId", None), params.fromPath[String]("entityId", None)) { (userPermissionId, entityId) =>
        com_xo_web_controllers_UserPermissionResourceInstanceController_delete64_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).delete(userPermissionId, entityId))
   }
}
        

// @LINE:99
case com_xo_web_controllers_UserPermissionResourceInstanceController_create65_route(params) => {
   call { 
        com_xo_web_controllers_UserPermissionResourceInstanceController_create65_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).create())
   }
}
        

// @LINE:105
case com_xo_web_controllers_DashboardController_loadDashboardGroupData66_route(params) => {
   call { 
        com_xo_web_controllers_DashboardController_loadDashboardGroupData66_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadDashboardGroupData())
   }
}
        

// @LINE:106
case com_xo_web_controllers_DashboardController_loadProjectDashboardData67_route(params) => {
   call(params.fromPath[String]("projectId", None)) { (projectId) =>
        com_xo_web_controllers_DashboardController_loadProjectDashboardData67_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadProjectDashboardData(projectId))
   }
}
        

// @LINE:107
case com_xo_web_controllers_DashboardController_loadViewData68_route(params) => {
   call(params.fromPath[String]("workbookId", None), params.fromPath[String]("viewId", None)) { (workbookId, viewId) =>
        com_xo_web_controllers_DashboardController_loadViewData68_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadViewData(workbookId, viewId))
   }
}
        

// @LINE:108
case com_xo_web_controllers_TableauRest_syncTableauReports69_route(params) => {
   call { 
        com_xo_web_controllers_TableauRest_syncTableauReports69_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauRest]).syncTableauReports)
   }
}
        

// @LINE:112
case com_xo_web_controllers_XoConfigTemplateController_readAll70_route(params) => {
   call { 
        com_xo_web_controllers_XoConfigTemplateController_readAll70_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).readAll())
   }
}
        

// @LINE:113
case com_xo_web_controllers_XoConfigTemplateController_create71_route(params) => {
   call { 
        com_xo_web_controllers_XoConfigTemplateController_create71_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).create())
   }
}
        

// @LINE:114
case com_xo_web_controllers_XoConfigTemplateController_read72_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigTemplateController_read72_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).read(configId))
   }
}
        

// @LINE:115
case com_xo_web_controllers_XoConfigTemplateController_update73_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigTemplateController_update73_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).update(configId))
   }
}
        

// @LINE:116
case com_xo_web_controllers_XoConfigTemplateController_delete74_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigTemplateController_delete74_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).delete(configId))
   }
}
        

// @LINE:117
case com_xo_web_controllers_XoConfigTemplateController_update75_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigTemplateController_update75_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).update(configId))
   }
}
        

// @LINE:118
case com_xo_web_controllers_XoConfigTemplateController_isConfigExist76_route(params) => {
   call(params.fromPath[String]("configShortName", None)) { (configShortName) =>
        com_xo_web_controllers_XoConfigTemplateController_isConfigExist76_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).isConfigExist(configShortName))
   }
}
        

// @LINE:119
case com_xo_web_controllers_XoConfigTemplateController_toggleActiveStatus77_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigTemplateController_toggleActiveStatus77_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).toggleActiveStatus(configId))
   }
}
        

// @LINE:121
case com_xo_web_controllers_XoConfigInstanceController_create78_route(params) => {
   call { 
        com_xo_web_controllers_XoConfigInstanceController_create78_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).create())
   }
}
        

// @LINE:122
case com_xo_web_controllers_XoConfigInstanceController_readAllInstances79_route(params) => {
   call { 
        com_xo_web_controllers_XoConfigInstanceController_readAllInstances79_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAllInstances())
   }
}
        

// @LINE:123
case com_xo_web_controllers_XoConfigInstanceController_read80_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigInstanceController_read80_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).read(configId))
   }
}
        

// @LINE:124
case com_xo_web_controllers_XoConfigInstanceController_update81_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigInstanceController_update81_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).update(configId))
   }
}
        

// @LINE:125
case com_xo_web_controllers_XoConfigInstanceController_delete82_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigInstanceController_delete82_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).delete(configId))
   }
}
        

// @LINE:126
case com_xo_web_controllers_XoConfigInstanceController_update83_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigInstanceController_update83_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).update(configId))
   }
}
        

// @LINE:127
case com_xo_web_controllers_XoConfigInstanceController_isConfigExist84_route(params) => {
   call(params.fromPath[Integer]("configTemplateId", None), params.fromPath[String]("configShortName", None)) { (configTemplateId, configShortName) =>
        com_xo_web_controllers_XoConfigInstanceController_isConfigExist84_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).isConfigExist(configTemplateId, configShortName))
   }
}
        

// @LINE:128
case com_xo_web_controllers_XoConfigInstanceController_readAll85_route(params) => {
   call(params.fromPath[Integer]("configTemplateId", None)) { (configTemplateId) =>
        com_xo_web_controllers_XoConfigInstanceController_readAll85_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAll(configTemplateId))
   }
}
        

// @LINE:129
case com_xo_web_controllers_XoConfigInstanceController_toggleActiveStatus86_route(params) => {
   call(params.fromPath[Integer]("configId", None)) { (configId) =>
        com_xo_web_controllers_XoConfigInstanceController_toggleActiveStatus86_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).toggleActiveStatus(configId))
   }
}
        

// @LINE:133
case com_xo_web_controllers_XoClientJobConfigController_toggleActiveStatus87_route(params) => {
   call(params.fromPath[Integer]("clientId", None), params.fromPath[Integer]("jobId", None), params.fromPath[Integer]("configId", None)) { (clientId, jobId, configId) =>
        com_xo_web_controllers_XoClientJobConfigController_toggleActiveStatus87_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).toggleActiveStatus(clientId, jobId, configId))
   }
}
        

// @LINE:134
case com_xo_web_controllers_XoClientJobConfigController_runJob88_route(params) => {
   call(params.fromPath[Integer]("clientId", None), params.fromPath[Integer]("jobId", None), params.fromPath[Integer]("configId", None)) { (clientId, jobId, configId) =>
        com_xo_web_controllers_XoClientJobConfigController_runJob88_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).runJob(clientId, jobId, configId))
   }
}
        

// @LINE:135
case com_xo_web_controllers_XoClientJobConfigController_delete89_route(params) => {
   call { 
        com_xo_web_controllers_XoClientJobConfigController_delete89_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).delete())
   }
}
        

// @LINE:136
case com_xo_web_controllers_XoClientJobConfigController_readAll90_route(params) => {
   call { 
        com_xo_web_controllers_XoClientJobConfigController_readAll90_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).readAll())
   }
}
        

// @LINE:137
case com_xo_web_controllers_XoClientJobConfigController_create91_route(params) => {
   call { 
        com_xo_web_controllers_XoClientJobConfigController_create91_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).create())
   }
}
        

// @LINE:140
case com_xo_web_controllers_XoClientController_readAll92_route(params) => {
   call { 
        com_xo_web_controllers_XoClientController_readAll92_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientController]).readAll())
   }
}
        

// @LINE:143
case com_xo_web_controllers_XoJobController_readAll93_route(params) => {
   call { 
        com_xo_web_controllers_XoJobController_readAll93_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoJobController]).readAll())
   }
}
        

// @LINE:145
case com_xo_web_controllers_XOBaseController_heartBeat94_route(params) => {
   call { 
        com_xo_web_controllers_XOBaseController_heartBeat94_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).heartBeat())
   }
}
        

// @LINE:148
case com_xo_web_controllers_ViewGroupController_readAllAsKeyValue95_route(params) => {
   call { 
        com_xo_web_controllers_ViewGroupController_readAllAsKeyValue95_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAllAsKeyValue())
   }
}
        

// @LINE:149
case com_xo_web_controllers_ViewGroupController_toggleActiveStatus96_route(params) => {
   call(params.fromPath[Integer]("viewGroupId", None)) { (viewGroupId) =>
        com_xo_web_controllers_ViewGroupController_toggleActiveStatus96_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).toggleActiveStatus(viewGroupId))
   }
}
        

// @LINE:150
case com_xo_web_controllers_ViewGroupController_delete97_route(params) => {
   call(params.fromPath[Integer]("viewGroupId", None)) { (viewGroupId) =>
        com_xo_web_controllers_ViewGroupController_delete97_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).delete(viewGroupId))
   }
}
        

// @LINE:151
case com_xo_web_controllers_ViewGroupController_readAll98_route(params) => {
   call { 
        com_xo_web_controllers_ViewGroupController_readAll98_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAll())
   }
}
        

// @LINE:152
case com_xo_web_controllers_ViewGroupController_read99_route(params) => {
   call(params.fromPath[Integer]("viewGroupId", None)) { (viewGroupId) =>
        com_xo_web_controllers_ViewGroupController_read99_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).read(viewGroupId))
   }
}
        

// @LINE:153
case com_xo_web_controllers_ViewGroupController_create100_route(params) => {
   call { 
        com_xo_web_controllers_ViewGroupController_create100_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).create())
   }
}
        

// @LINE:154
case com_xo_web_controllers_ViewGroupController_update101_route(params) => {
   call { 
        com_xo_web_controllers_ViewGroupController_update101_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).update())
   }
}
        

// @LINE:155
case com_xo_web_controllers_ViewGroupController_readGroupCount102_route(params) => {
   call(params.fromPath[Integer]("viewGroupId", None)) { (viewGroupId) =>
        com_xo_web_controllers_ViewGroupController_readGroupCount102_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readGroupCount(viewGroupId))
   }
}
        

// @LINE:158
case com_xo_web_controllers_TableauViewController_readAllAsKeyValue103_route(params) => {
   call { 
        com_xo_web_controllers_TableauViewController_readAllAsKeyValue103_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAllAsKeyValue())
   }
}
        

// @LINE:159
case com_xo_web_controllers_TableauViewController_toggleActiveStatus104_route(params) => {
   call(params.fromPath[String]("tableauViewId", None)) { (tableauViewId) =>
        com_xo_web_controllers_TableauViewController_toggleActiveStatus104_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleActiveStatus(tableauViewId))
   }
}
        

// @LINE:160
case com_xo_web_controllers_TableauViewController_readAll105_route(params) => {
   call { 
        com_xo_web_controllers_TableauViewController_readAll105_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAll())
   }
}
        

// @LINE:161
case com_xo_web_controllers_TableauViewController_read106_route(params) => {
   call(params.fromPath[String]("tableauViewId", None)) { (tableauViewId) =>
        com_xo_web_controllers_TableauViewController_read106_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).read(tableauViewId))
   }
}
        

// @LINE:162
case com_xo_web_controllers_TableauViewController_update107_route(params) => {
   call { 
        com_xo_web_controllers_TableauViewController_update107_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).update())
   }
}
        

// @LINE:163
case com_xo_web_controllers_TableauViewController_toggleDashboardStatus108_route(params) => {
   call(params.fromPath[String]("tableauViewId", None)) { (tableauViewId) =>
        com_xo_web_controllers_TableauViewController_toggleDashboardStatus108_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleDashboardStatus(tableauViewId))
   }
}
        

// @LINE:164
case com_xo_web_controllers_TableauViewController_isDashboardExist109_route(params) => {
   call(params.fromPath[Integer]("viewGroupId", None)) { (viewGroupId) =>
        com_xo_web_controllers_TableauViewController_isDashboardExist109_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).isDashboardExist(viewGroupId))
   }
}
        

// @LINE:167
case com_xo_web_controllers_DashboardController_getDiffusionMap110_route(params) => {
   call { 
        com_xo_web_controllers_DashboardController_getDiffusionMap110_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getDiffusionMap())
   }
}
        

// @LINE:168
case com_xo_web_controllers_DashboardController_getFilterList111_route(params) => {
   call { 
        com_xo_web_controllers_DashboardController_getFilterList111_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getFilterList())
   }
}
        
}

}
     