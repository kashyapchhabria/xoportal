// @SOURCE:/home/kashyap/xoworkspace/xoportal/xo_reporting_portal/conf/routes
// @HASH:5b330c619bb61c4932965e954d621df844db1010
// @DATE:Tue Mar 14 10:00:26 IST 2017

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset
import _root_.play.libs.F

import Router.queryString


// @LINE:12
// @LINE:8
// @LINE:7
// @LINE:6
package controllers {

// @LINE:8
class ReverseWebJarAssets {


// @LINE:8
def at(file:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "webjars/" + implicitly[PathBindable[String]].unbind("file", file))
}
                        

}
                          

// @LINE:12
// @LINE:7
// @LINE:6
class ReverseAssets {


// @LINE:12
// @LINE:6
def at(file:String): Call = {
   (file: @unchecked) match {
// @LINE:6
case (file)  =>
  implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
  Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
                                         
   }
}
                                                

// @LINE:7
def versioned(file:Asset): Call = {
   implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
   Call("GET", _prefix + { _defaultPrefix } + "vassets/" + implicitly[PathBindable[Asset]].unbind("file", file))
}
                        

}
                          
}
                  

// @LINE:168
// @LINE:167
// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
// @LINE:145
// @LINE:143
// @LINE:140
// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
// @LINE:13
// @LINE:11
package com.xo.web.controllers {

// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
class ReverseXoConfigInstanceController {


// @LINE:122
def readAllInstances(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configinstances")
}
                        

// @LINE:121
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "configinstances")
}
                        

// @LINE:126
// @LINE:124
def update(configId:Integer): Call = {
   (configId: @unchecked) match {
// @LINE:124
case (configId)  =>
  import ReverseRouteContext.empty
  Call("PUT", _prefix + { _defaultPrefix } + "configinstances/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
                                         
   }
}
                                                

// @LINE:128
def readAll(configTemplateId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configinstances/all/" + implicitly[PathBindable[Integer]].unbind("configTemplateId", configTemplateId))
}
                        

// @LINE:127
def isConfigExist(configTemplateId:Integer, configShortName:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configinstances/search/" + implicitly[PathBindable[Integer]].unbind("configTemplateId", configTemplateId) + "/" + implicitly[PathBindable[String]].unbind("configShortName", dynamicString(configShortName)))
}
                        

// @LINE:129
def toggleActiveStatus(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "configinstances/toggleactive/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

// @LINE:123
def read(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configinstances/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

// @LINE:125
def delete(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "configinstances/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

}
                          

// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
class ReverseRolePermissionController {


// @LINE:77
def create(roleId:Integer, permissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "rolepermission/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId) + "/" + implicitly[PathBindable[Integer]].unbind("permissionId", permissionId))
}
                        

// @LINE:76
def delete(rolePermissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "rolepermission/" + implicitly[PathBindable[Integer]].unbind("rolePermissionId", rolePermissionId))
}
                        

// @LINE:75
def toggleActiveStatus(rolePermissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "rolepermission/toggleactive/" + implicitly[PathBindable[Integer]].unbind("rolePermissionId", rolePermissionId))
}
                        

// @LINE:74
def readAll(roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "rolepermission/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

}
                          

// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
class ReverseXoClientJobConfigController {


// @LINE:136
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "clientjobconfigs")
}
                        

// @LINE:135
def delete(): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "clientjobconfigs")
}
                        

// @LINE:137
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "clientjobconfigs")
}
                        

// @LINE:134
def runJob(clientId:Integer, jobId:Integer, configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "clientjobconfigs/runJob/" + implicitly[PathBindable[Integer]].unbind("clientId", clientId) + "/" + implicitly[PathBindable[Integer]].unbind("jobId", jobId) + "/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

// @LINE:133
def toggleActiveStatus(clientId:Integer, jobId:Integer, configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "clientjobconfigs/toggleactive/" + implicitly[PathBindable[Integer]].unbind("clientId", clientId) + "/" + implicitly[PathBindable[Integer]].unbind("jobId", jobId) + "/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

}
                          

// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
class ReverseViewGroupController {


// @LINE:151
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "viewgroup/all")
}
                        

// @LINE:153
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "viewgroup")
}
                        

// @LINE:155
def readGroupCount(viewGroupId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "viewgroup/groupcount/" + implicitly[PathBindable[Integer]].unbind("viewGroupId", viewGroupId))
}
                        

// @LINE:149
def toggleActiveStatus(viewGroupId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "viewgroup/toggleactive/" + implicitly[PathBindable[Integer]].unbind("viewGroupId", viewGroupId))
}
                        

// @LINE:152
def read(viewGroupId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "viewgroup/" + implicitly[PathBindable[Integer]].unbind("viewGroupId", viewGroupId))
}
                        

// @LINE:150
def delete(viewGroupId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "viewgroup/" + implicitly[PathBindable[Integer]].unbind("viewGroupId", viewGroupId))
}
                        

// @LINE:148
def readAllAsKeyValue(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "viewgroup/keyvalue")
}
                        

// @LINE:154
def update(): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "viewgroup/update")
}
                        

}
                          

// @LINE:108
class ReverseTableauRest {


// @LINE:108
def syncTableauReports(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "sync_tableau_reports")
}
                        

}
                          

// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
class ReverseUserPermissionResourceInstanceController {


// @LINE:94
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userrlp")
}
                        

// @LINE:96
def readAllEntityInstances(userId:Integer, resourceTypeId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userrlp/ressourceinstanceids/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("resourceTypeId", resourceTypeId))
}
                        

// @LINE:99
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "userrlp")
}
                        

// @LINE:95
def readAllByUserAndResourType(userId:Integer, resourceTypeId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userrlp/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("resourceTypeId", resourceTypeId))
}
                        

// @LINE:97
def toggleActiveStatus(userPermissionId:Integer, entityId:String): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "userrlp/toggleactive/" + implicitly[PathBindable[Integer]].unbind("userPermissionId", userPermissionId) + "/" + implicitly[PathBindable[String]].unbind("entityId", dynamicString(entityId)))
}
                        

// @LINE:98
def delete(userPermissionId:Integer, entityId:String): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "userrlp/" + implicitly[PathBindable[Integer]].unbind("userPermissionId", userPermissionId) + "/" + implicitly[PathBindable[String]].unbind("entityId", dynamicString(entityId)))
}
                        

}
                          

// @LINE:168
// @LINE:167
// @LINE:107
// @LINE:106
// @LINE:105
class ReverseDashboardController {


// @LINE:106
def loadProjectDashboardData(projectId:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "dashboard/project/" + implicitly[PathBindable[String]].unbind("projectId", dynamicString(projectId)))
}
                        

// @LINE:105
def loadDashboardGroupData(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "dashboard")
}
                        

// @LINE:168
def getFilterList(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "filterlist")
}
                        

// @LINE:167
def getDiffusionMap(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "diffusionMap")
}
                        

// @LINE:107
def loadViewData(workbookId:String, viewId:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "dashboard/view/" + implicitly[PathBindable[String]].unbind("workbookId", dynamicString(workbookId)) + "/" + implicitly[PathBindable[String]].unbind("viewId", dynamicString(viewId)))
}
                        

}
                          

// @LINE:145
// @LINE:13
// @LINE:11
class ReverseXOBaseController {


// @LINE:13
def accessDenied(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "accessdenied")
}
                        

// @LINE:11
def cspReportParser(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "cspreport")
}
                        

// @LINE:145
def heartBeat(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "heartbeat")
}
                        

}
                          

// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
class ReverseRoleController {


// @LINE:49
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "roles")
}
                        

// @LINE:50
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "roles")
}
                        

// @LINE:54
def update(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "roles/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:56
def readUnassigned(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "roles/unassigned/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:52
def toggleActiveStatus(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "roles/toggleactive/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:53
def read(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "roles/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:55
def delete(roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "roles/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:51
def readAllAsKeyValue(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "roles/keyvalue")
}
                        

}
                          

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
class ReversePermissionController {


// @LINE:60
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "permissions")
}
                        

// @LINE:62
def readUnAssignedRolePermissions(roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "permissions/unassigned/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:61
def toggleActiveStatus(id:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "permissions/toggleactive/" + implicitly[PathBindable[Integer]].unbind("id", id))
}
                        

// @LINE:59
def read(roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "permissions/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:63
def readUnAssignedUserPermissions(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "permissions/unassigned_user_permissions/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

}
                          

// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
class ReverseUserRoleController {


// @LINE:67
def readAll(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userroles/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:70
def toggleActiveStatus(userId:Integer, roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "userroles/toggleactive/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:71
def readRolesCount(roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userroles/roles_count/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:68
def create(userId:Integer, roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "userroles/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

// @LINE:69
def delete(userId:Integer, roleId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "userroles/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId))
}
                        

}
                          

// @LINE:29
class ReverseResourceTypeController {


// @LINE:29
def readAllAsKeyValue(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "resourcetypes/keyvalue")
}
                        

}
                          

// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
class ReverseUserController {


// @LINE:45
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users")
}
                        

// @LINE:36
def verify(token:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/verify" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("token", token)))))
}
                        

// @LINE:46
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users")
}
                        

// @LINE:43
def update(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:35
def resetPassword(authToken:String): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users/resetpassword" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("authToken", authToken)))))
}
                        

// @LINE:14
def jsonAccessDenied(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jsonaccessdenied")
}
                        

// @LINE:40
def uploadUsers(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users/upload")
}
                        

// @LINE:34
def renderResetPasswordPage(authToken:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/resetpasswordpage" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("authToken", authToken)))))
}
                        

// @LINE:32
def toggleActiveStatus(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "users/toggleactive/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:16
def renderLoginPage(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix)
}
                        

// @LINE:39
def forgotPassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users/forgotpassword")
}
                        

// @LINE:37
def changePassword(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "users/changepassword")
}
                        

// @LINE:42
def read(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:44
def delete(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "users/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:41
def readAllAsKeyValue(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/keyvalue")
}
                        

// @LINE:38
def forgotPasswordPage(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "users/forgotpassword")
}
                        

// @LINE:26
def pageDispatcher(authToken:String, currentPage:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "pagedispatcher" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("authToken", authToken)), Some(implicitly[QueryStringBindable[String]].unbind("currentPage", currentPage)))))
}
                        

// @LINE:33
def resetPasswordForUser(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "users/resetpassword/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

// @LINE:20
def xossoCallBackHandler(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "logincallback")
}
                        

// @LINE:23
def logout(authToken:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "logout" + queryString(List(Some(implicitly[QueryStringBindable[String]].unbind("authToken", authToken)))))
}
                        

// @LINE:19
def login(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "auth/login")
}
                        

}
                          

// @LINE:140
class ReverseXoClientController {


// @LINE:140
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "clients")
}
                        

}
                          

// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
class ReverseUserPermissionController {


// @LINE:89
def create(userId:Integer, permissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "userpermission/" + implicitly[PathBindable[Integer]].unbind("userId", userId) + "/" + implicitly[PathBindable[Integer]].unbind("permissionId", permissionId))
}
                        

// @LINE:90
def delete(userpermissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "userpermission/" + implicitly[PathBindable[Integer]].unbind("userpermissionId", userpermissionId))
}
                        

// @LINE:91
def toggleActiveStatus(userpermissionId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "userpermission/toggleactive/" + implicitly[PathBindable[Integer]].unbind("userpermissionId", userpermissionId))
}
                        

// @LINE:88
def readAll(userId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "userpermission/" + implicitly[PathBindable[Integer]].unbind("userId", userId))
}
                        

}
                          

// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
class ReverseTableauViewController {


// @LINE:164
def isDashboardExist(viewGroupId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tableauview/checkdashboard/" + implicitly[PathBindable[Integer]].unbind("viewGroupId", viewGroupId))
}
                        

// @LINE:160
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tableauview/all")
}
                        

// @LINE:161
def read(tableauViewId:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tableauview/" + implicitly[PathBindable[String]].unbind("tableauViewId", dynamicString(tableauViewId)))
}
                        

// @LINE:163
def toggleDashboardStatus(tableauViewId:String): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "tableauview/toggledashboard/" + implicitly[PathBindable[String]].unbind("tableauViewId", dynamicString(tableauViewId)))
}
                        

// @LINE:158
def readAllAsKeyValue(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "tableauview/keyvalue")
}
                        

// @LINE:162
def update(): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "tableauview/update")
}
                        

// @LINE:159
def toggleActiveStatus(tableauViewId:String): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "tableauview/toggleactive/" + implicitly[PathBindable[String]].unbind("tableauViewId", dynamicString(tableauViewId)))
}
                        

}
                          

// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
class ReverseRolePermissionResourceInstanceController {


// @LINE:80
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "rolerlp")
}
                        

// @LINE:82
def readAllEntityInstances(roleId:Integer, resourceTypeId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "rolerlp/ressourceinstanceids/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId) + "/" + implicitly[PathBindable[Integer]].unbind("resourceTypeId", resourceTypeId))
}
                        

// @LINE:85
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "rolerlp")
}
                        

// @LINE:81
def readAllByRoleAndResourType(roleId:Integer, resourceTypeId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "rolerlp/" + implicitly[PathBindable[Integer]].unbind("roleId", roleId) + "/" + implicitly[PathBindable[Integer]].unbind("resourceTypeId", resourceTypeId))
}
                        

// @LINE:83
def toggleActiveStatus(rolePermissionId:Integer, entityId:String): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "rolerlp/toggleactive/" + implicitly[PathBindable[Integer]].unbind("rolePermissionId", rolePermissionId) + "/" + implicitly[PathBindable[String]].unbind("entityId", dynamicString(entityId)))
}
                        

// @LINE:84
def delete(rolePermissionId:Integer, entityId:String): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "rolerlp/" + implicitly[PathBindable[Integer]].unbind("rolePermissionId", rolePermissionId) + "/" + implicitly[PathBindable[String]].unbind("entityId", dynamicString(entityId)))
}
                        

}
                          

// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
class ReverseXoConfigTemplateController {


// @LINE:112
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configtemplates")
}
                        

// @LINE:113
def create(): Call = {
   import ReverseRouteContext.empty
   Call("POST", _prefix + { _defaultPrefix } + "configtemplates")
}
                        

// @LINE:117
// @LINE:115
def update(configId:Integer): Call = {
   (configId: @unchecked) match {
// @LINE:115
case (configId)  =>
  import ReverseRouteContext.empty
  Call("PUT", _prefix + { _defaultPrefix } + "configtemplates/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
                                         
   }
}
                                                

// @LINE:118
def isConfigExist(configShortName:String): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configtemplates/search/" + implicitly[PathBindable[String]].unbind("configShortName", dynamicString(configShortName)))
}
                        

// @LINE:119
def toggleActiveStatus(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("PUT", _prefix + { _defaultPrefix } + "configtemplates/toggleactive/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

// @LINE:114
def read(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "configtemplates/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

// @LINE:116
def delete(configId:Integer): Call = {
   import ReverseRouteContext.empty
   Call("DELETE", _prefix + { _defaultPrefix } + "configtemplates/" + implicitly[PathBindable[Integer]].unbind("configId", configId))
}
                        

}
                          

// @LINE:143
class ReverseXoJobController {


// @LINE:143
def readAll(): Call = {
   import ReverseRouteContext.empty
   Call("GET", _prefix + { _defaultPrefix } + "jobs")
}
                        

}
                          
}
                  


// @LINE:12
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.javascript {
import ReverseRouteContext.empty

// @LINE:8
class ReverseWebJarAssets {


// @LINE:8
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.WebJarAssets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "webjars/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              

// @LINE:12
// @LINE:7
// @LINE:6
class ReverseAssets {


// @LINE:12
// @LINE:6
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
      if (true) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "crossdomain.xml" + _qS([(file == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("file", file))])})
      }
      }
   """
)
                        

// @LINE:7
def versioned : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.versioned",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "vassets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        

}
              
}
        

// @LINE:168
// @LINE:167
// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
// @LINE:145
// @LINE:143
// @LINE:140
// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
// @LINE:13
// @LINE:11
package com.xo.web.controllers.javascript {
import ReverseRouteContext.empty

// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
class ReverseXoConfigInstanceController {


// @LINE:122
def readAllInstances : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.readAllInstances",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances"})
      }
   """
)
                        

// @LINE:121
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances"})
      }
   """
)
                        

// @LINE:126
// @LINE:124
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.update",
   """
      function(configId) {
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
      }
   """
)
                        

// @LINE:128
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.readAll",
   """
      function(configTemplateId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/all/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configTemplateId", configTemplateId)})
      }
   """
)
                        

// @LINE:127
def isConfigExist : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.isConfigExist",
   """
      function(configTemplateId,configShortName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/search/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configTemplateId", configTemplateId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("configShortName", encodeURIComponent(configShortName))})
      }
   """
)
                        

// @LINE:129
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.toggleActiveStatus",
   """
      function(configId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

// @LINE:123
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.read",
   """
      function(configId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

// @LINE:125
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigInstanceController.delete",
   """
      function(configId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "configinstances/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

}
              

// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
class ReverseRolePermissionController {


// @LINE:77
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionController.create",
   """
      function(roleId,permissionId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rolepermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("permissionId", permissionId)})
      }
   """
)
                        

// @LINE:76
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionController.delete",
   """
      function(rolePermissionId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "rolepermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("rolePermissionId", rolePermissionId)})
      }
   """
)
                        

// @LINE:75
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionController.toggleActiveStatus",
   """
      function(rolePermissionId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "rolepermission/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("rolePermissionId", rolePermissionId)})
      }
   """
)
                        

// @LINE:74
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionController.readAll",
   """
      function(roleId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rolepermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

}
              

// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
class ReverseXoClientJobConfigController {


// @LINE:136
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientJobConfigController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clientjobconfigs"})
      }
   """
)
                        

// @LINE:135
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientJobConfigController.delete",
   """
      function() {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "clientjobconfigs"})
      }
   """
)
                        

// @LINE:137
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientJobConfigController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "clientjobconfigs"})
      }
   """
)
                        

// @LINE:134
def runJob : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientJobConfigController.runJob",
   """
      function(clientId,jobId,configId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "clientjobconfigs/runJob/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("clientId", clientId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("jobId", jobId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

// @LINE:133
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientJobConfigController.toggleActiveStatus",
   """
      function(clientId,jobId,configId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "clientjobconfigs/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("clientId", clientId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("jobId", jobId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

}
              

// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
class ReverseViewGroupController {


// @LINE:151
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/all"})
      }
   """
)
                        

// @LINE:153
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup"})
      }
   """
)
                        

// @LINE:155
def readGroupCount : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.readGroupCount",
   """
      function(viewGroupId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/groupcount/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("viewGroupId", viewGroupId)})
      }
   """
)
                        

// @LINE:149
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.toggleActiveStatus",
   """
      function(viewGroupId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("viewGroupId", viewGroupId)})
      }
   """
)
                        

// @LINE:152
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.read",
   """
      function(viewGroupId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("viewGroupId", viewGroupId)})
      }
   """
)
                        

// @LINE:150
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.delete",
   """
      function(viewGroupId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("viewGroupId", viewGroupId)})
      }
   """
)
                        

// @LINE:148
def readAllAsKeyValue : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.readAllAsKeyValue",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/keyvalue"})
      }
   """
)
                        

// @LINE:154
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ViewGroupController.update",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "viewgroup/update"})
      }
   """
)
                        

}
              

// @LINE:108
class ReverseTableauRest {


// @LINE:108
def syncTableauReports : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauRest.syncTableauReports",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "sync_tableau_reports"})
      }
   """
)
                        

}
              

// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
class ReverseUserPermissionResourceInstanceController {


// @LINE:94
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp"})
      }
   """
)
                        

// @LINE:96
def readAllEntityInstances : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.readAllEntityInstances",
   """
      function(userId,resourceTypeId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp/ressourceinstanceids/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("resourceTypeId", resourceTypeId)})
      }
   """
)
                        

// @LINE:99
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp"})
      }
   """
)
                        

// @LINE:95
def readAllByUserAndResourType : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.readAllByUserAndResourType",
   """
      function(userId,resourceTypeId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("resourceTypeId", resourceTypeId)})
      }
   """
)
                        

// @LINE:97
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.toggleActiveStatus",
   """
      function(userPermissionId,entityId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userPermissionId", userPermissionId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("entityId", encodeURIComponent(entityId))})
      }
   """
)
                        

// @LINE:98
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionResourceInstanceController.delete",
   """
      function(userPermissionId,entityId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "userrlp/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userPermissionId", userPermissionId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("entityId", encodeURIComponent(entityId))})
      }
   """
)
                        

}
              

// @LINE:168
// @LINE:167
// @LINE:107
// @LINE:106
// @LINE:105
class ReverseDashboardController {


// @LINE:106
def loadProjectDashboardData : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.DashboardController.loadProjectDashboardData",
   """
      function(projectId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard/project/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("projectId", encodeURIComponent(projectId))})
      }
   """
)
                        

// @LINE:105
def loadDashboardGroupData : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.DashboardController.loadDashboardGroupData",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard"})
      }
   """
)
                        

// @LINE:168
def getFilterList : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.DashboardController.getFilterList",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "filterlist"})
      }
   """
)
                        

// @LINE:167
def getDiffusionMap : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.DashboardController.getDiffusionMap",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "diffusionMap"})
      }
   """
)
                        

// @LINE:107
def loadViewData : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.DashboardController.loadViewData",
   """
      function(workbookId,viewId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "dashboard/view/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("workbookId", encodeURIComponent(workbookId)) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("viewId", encodeURIComponent(viewId))})
      }
   """
)
                        

}
              

// @LINE:145
// @LINE:13
// @LINE:11
class ReverseXOBaseController {


// @LINE:13
def accessDenied : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XOBaseController.accessDenied",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "accessdenied"})
      }
   """
)
                        

// @LINE:11
def cspReportParser : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XOBaseController.cspReportParser",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "cspreport"})
      }
   """
)
                        

// @LINE:145
def heartBeat : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XOBaseController.heartBeat",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "heartbeat"})
      }
   """
)
                        

}
              

// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
class ReverseRoleController {


// @LINE:49
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "roles"})
      }
   """
)
                        

// @LINE:50
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "roles"})
      }
   """
)
                        

// @LINE:54
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.update",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:56
def readUnassigned : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.readUnassigned",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/unassigned/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:52
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.toggleActiveStatus",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:53
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.read",
   """
      function(id) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:55
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.delete",
   """
      function(roleId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:51
def readAllAsKeyValue : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RoleController.readAllAsKeyValue",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "roles/keyvalue"})
      }
   """
)
                        

}
              

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
class ReversePermissionController {


// @LINE:60
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.PermissionController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "permissions"})
      }
   """
)
                        

// @LINE:62
def readUnAssignedRolePermissions : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.PermissionController.readUnAssignedRolePermissions",
   """
      function(roleId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "permissions/unassigned/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:61
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.PermissionController.toggleActiveStatus",
   """
      function(id) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "permissions/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        

// @LINE:59
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.PermissionController.read",
   """
      function(roleId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "permissions/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:63
def readUnAssignedUserPermissions : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.PermissionController.readUnAssignedUserPermissions",
   """
      function(userId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "permissions/unassigned_user_permissions/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

}
              

// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
class ReverseUserRoleController {


// @LINE:67
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserRoleController.readAll",
   """
      function(userId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userroles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:70
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserRoleController.toggleActiveStatus",
   """
      function(userId,roleId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "userroles/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:71
def readRolesCount : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserRoleController.readRolesCount",
   """
      function(roleId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userroles/roles_count/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:68
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserRoleController.create",
   """
      function(userId,roleId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userroles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

// @LINE:69
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserRoleController.delete",
   """
      function(userId,roleId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "userroles/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId)})
      }
   """
)
                        

}
              

// @LINE:29
class ReverseResourceTypeController {


// @LINE:29
def readAllAsKeyValue : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.ResourceTypeController.readAllAsKeyValue",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "resourcetypes/keyvalue"})
      }
   """
)
                        

}
              

// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
class ReverseUserController {


// @LINE:45
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
      }
   """
)
                        

// @LINE:36
def verify : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.verify",
   """
      function(token) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/verify" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("token", token)])})
      }
   """
)
                        

// @LINE:46
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users"})
      }
   """
)
                        

// @LINE:43
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.update",
   """
      function(userId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:35
def resetPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.resetPassword",
   """
      function(authToken) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/resetpassword" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("authToken", authToken)])})
      }
   """
)
                        

// @LINE:14
def jsonAccessDenied : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.jsonAccessDenied",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jsonaccessdenied"})
      }
   """
)
                        

// @LINE:40
def uploadUsers : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.uploadUsers",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/upload"})
      }
   """
)
                        

// @LINE:34
def renderResetPasswordPage : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.renderResetPasswordPage",
   """
      function(authToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/resetpasswordpage" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("authToken", authToken)])})
      }
   """
)
                        

// @LINE:32
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.toggleActiveStatus",
   """
      function(userId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "users/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:16
def renderLoginPage : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.renderLoginPage",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:39
def forgotPassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.forgotPassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/forgotpassword"})
      }
   """
)
                        

// @LINE:37
def changePassword : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.changePassword",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "users/changepassword"})
      }
   """
)
                        

// @LINE:42
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.read",
   """
      function(userId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:44
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.delete",
   """
      function(userId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:41
def readAllAsKeyValue : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.readAllAsKeyValue",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/keyvalue"})
      }
   """
)
                        

// @LINE:38
def forgotPasswordPage : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.forgotPasswordPage",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/forgotpassword"})
      }
   """
)
                        

// @LINE:26
def pageDispatcher : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.pageDispatcher",
   """
      function(authToken,currentPage) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "pagedispatcher" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("authToken", authToken), (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("currentPage", currentPage)])})
      }
   """
)
                        

// @LINE:33
def resetPasswordForUser : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.resetPasswordForUser",
   """
      function(userId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "users/resetpassword/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

// @LINE:20
def xossoCallBackHandler : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.xossoCallBackHandler",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logincallback"})
      }
   """
)
                        

// @LINE:23
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.logout",
   """
      function(authToken) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout" + _qS([(""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("authToken", authToken)])})
      }
   """
)
                        

// @LINE:19
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserController.login",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "auth/login"})
      }
   """
)
                        

}
              

// @LINE:140
class ReverseXoClientController {


// @LINE:140
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoClientController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "clients"})
      }
   """
)
                        

}
              

// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
class ReverseUserPermissionController {


// @LINE:89
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionController.create",
   """
      function(userId,permissionId) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "userpermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("permissionId", permissionId)})
      }
   """
)
                        

// @LINE:90
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionController.delete",
   """
      function(userpermissionId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "userpermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userpermissionId", userpermissionId)})
      }
   """
)
                        

// @LINE:91
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionController.toggleActiveStatus",
   """
      function(userpermissionId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "userpermission/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userpermissionId", userpermissionId)})
      }
   """
)
                        

// @LINE:88
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.UserPermissionController.readAll",
   """
      function(userId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "userpermission/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("userId", userId)})
      }
   """
)
                        

}
              

// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
class ReverseTableauViewController {


// @LINE:164
def isDashboardExist : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.isDashboardExist",
   """
      function(viewGroupId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/checkdashboard/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("viewGroupId", viewGroupId)})
      }
   """
)
                        

// @LINE:160
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/all"})
      }
   """
)
                        

// @LINE:161
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.read",
   """
      function(tableauViewId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tableauViewId", encodeURIComponent(tableauViewId))})
      }
   """
)
                        

// @LINE:163
def toggleDashboardStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.toggleDashboardStatus",
   """
      function(tableauViewId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/toggledashboard/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tableauViewId", encodeURIComponent(tableauViewId))})
      }
   """
)
                        

// @LINE:158
def readAllAsKeyValue : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.readAllAsKeyValue",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/keyvalue"})
      }
   """
)
                        

// @LINE:162
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.update",
   """
      function() {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/update"})
      }
   """
)
                        

// @LINE:159
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.TableauViewController.toggleActiveStatus",
   """
      function(tableauViewId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "tableauview/toggleactive/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("tableauViewId", encodeURIComponent(tableauViewId))})
      }
   """
)
                        

}
              

// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
class ReverseRolePermissionResourceInstanceController {


// @LINE:80
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp"})
      }
   """
)
                        

// @LINE:82
def readAllEntityInstances : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.readAllEntityInstances",
   """
      function(roleId,resourceTypeId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp/ressourceinstanceids/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("resourceTypeId", resourceTypeId)})
      }
   """
)
                        

// @LINE:85
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp"})
      }
   """
)
                        

// @LINE:81
def readAllByRoleAndResourType : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.readAllByRoleAndResourType",
   """
      function(roleId,resourceTypeId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("roleId", roleId) + "/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("resourceTypeId", resourceTypeId)})
      }
   """
)
                        

// @LINE:83
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.toggleActiveStatus",
   """
      function(rolePermissionId,entityId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("rolePermissionId", rolePermissionId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("entityId", encodeURIComponent(entityId))})
      }
   """
)
                        

// @LINE:84
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.RolePermissionResourceInstanceController.delete",
   """
      function(rolePermissionId,entityId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "rolerlp/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("rolePermissionId", rolePermissionId) + "/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("entityId", encodeURIComponent(entityId))})
      }
   """
)
                        

}
              

// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
class ReverseXoConfigTemplateController {


// @LINE:112
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates"})
      }
   """
)
                        

// @LINE:113
def create : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.create",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates"})
      }
   """
)
                        

// @LINE:117
// @LINE:115
def update : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.update",
   """
      function(configId) {
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
      if (true) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
      }
   """
)
                        

// @LINE:118
def isConfigExist : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.isConfigExist",
   """
      function(configShortName) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/search/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("configShortName", encodeURIComponent(configShortName))})
      }
   """
)
                        

// @LINE:119
def toggleActiveStatus : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.toggleActiveStatus",
   """
      function(configId) {
      return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/toggleactive/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

// @LINE:114
def read : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.read",
   """
      function(configId) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

// @LINE:116
def delete : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoConfigTemplateController.delete",
   """
      function(configId) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "configtemplates/" + (""" + implicitly[PathBindable[Integer]].javascriptUnbind + """)("configId", configId)})
      }
   """
)
                        

}
              

// @LINE:143
class ReverseXoJobController {


// @LINE:143
def readAll : JavascriptReverseRoute = JavascriptReverseRoute(
   "com.xo.web.controllers.XoJobController.readAll",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "jobs"})
      }
   """
)
                        

}
              
}
        


// @LINE:12
// @LINE:8
// @LINE:7
// @LINE:6
package controllers.ref {


// @LINE:8
class ReverseWebJarAssets {


// @LINE:8
def at(file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.WebJarAssets.at(file), HandlerDef(this.getClass.getClassLoader, "", "controllers.WebJarAssets", "at", Seq(classOf[String]), "GET", """""", _prefix + """webjars/$file<.+>""")
)
                      

}
                          

// @LINE:12
// @LINE:7
// @LINE:6
class ReverseAssets {


// @LINE:6
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      

// @LINE:7
def versioned(path:String, file:Asset): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.versioned(path, file), HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "versioned", Seq(classOf[String], classOf[Asset]), "GET", """""", _prefix + """vassets/$file<.+>""")
)
                      

}
                          
}
        

// @LINE:168
// @LINE:167
// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
// @LINE:145
// @LINE:143
// @LINE:140
// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
// @LINE:108
// @LINE:107
// @LINE:106
// @LINE:105
// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:29
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
// @LINE:13
// @LINE:11
package com.xo.web.controllers.ref {


// @LINE:129
// @LINE:128
// @LINE:127
// @LINE:126
// @LINE:125
// @LINE:124
// @LINE:123
// @LINE:122
// @LINE:121
class ReverseXoConfigInstanceController {


// @LINE:122
def readAllInstances(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAllInstances(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "readAllInstances", Seq(), "GET", """""", _prefix + """configinstances""")
)
                      

// @LINE:121
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "create", Seq(), "POST", """""", _prefix + """configinstances""")
)
                      

// @LINE:124
def update(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).update(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "update", Seq(classOf[Integer]), "PUT", """""", _prefix + """configinstances/$configId<[^/]+>""")
)
                      

// @LINE:128
def readAll(configTemplateId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).readAll(configTemplateId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "readAll", Seq(classOf[Integer]), "GET", """""", _prefix + """configinstances/all/$configTemplateId<[^/]+>""")
)
                      

// @LINE:127
def isConfigExist(configTemplateId:Integer, configShortName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).isConfigExist(configTemplateId, configShortName), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "isConfigExist", Seq(classOf[Integer], classOf[String]), "GET", """""", _prefix + """configinstances/search/$configTemplateId<[^/]+>/$configShortName<[^/]+>""")
)
                      

// @LINE:129
def toggleActiveStatus(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).toggleActiveStatus(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """configinstances/toggleactive/$configId<[^/]+>""")
)
                      

// @LINE:123
def read(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).read(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "read", Seq(classOf[Integer]), "GET", """""", _prefix + """configinstances/$configId<[^/]+>""")
)
                      

// @LINE:125
def delete(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigInstanceController]).delete(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigInstanceController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """configinstances/$configId<[^/]+>""")
)
                      

}
                          

// @LINE:77
// @LINE:76
// @LINE:75
// @LINE:74
class ReverseRolePermissionController {


// @LINE:77
def create(roleId:Integer, permissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).create(roleId, permissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "create", Seq(classOf[Integer], classOf[Integer]), "POST", """""", _prefix + """rolepermission/$roleId<[^/]+>/$permissionId<[^/]+>""")
)
                      

// @LINE:76
def delete(rolePermissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).delete(rolePermissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """rolepermission/$rolePermissionId<[^/]+>""")
)
                      

// @LINE:75
def toggleActiveStatus(rolePermissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).toggleActiveStatus(rolePermissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """rolepermission/toggleactive/$rolePermissionId<[^/]+>""")
)
                      

// @LINE:74
def readAll(roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionController]).readAll(roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionController", "readAll", Seq(classOf[Integer]), "GET", """Roles Permission mgmt""", _prefix + """rolepermission/$roleId<[^/]+>""")
)
                      

}
                          

// @LINE:137
// @LINE:136
// @LINE:135
// @LINE:134
// @LINE:133
class ReverseXoClientJobConfigController {


// @LINE:136
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "readAll", Seq(), "GET", """""", _prefix + """clientjobconfigs""")
)
                      

// @LINE:135
def delete(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).delete(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "delete", Seq(), "DELETE", """""", _prefix + """clientjobconfigs""")
)
                      

// @LINE:137
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "create", Seq(), "POST", """""", _prefix + """clientjobconfigs""")
)
                      

// @LINE:134
def runJob(clientId:Integer, jobId:Integer, configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).runJob(clientId, jobId, configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "runJob", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "PUT", """""", _prefix + """clientjobconfigs/runJob/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>""")
)
                      

// @LINE:133
def toggleActiveStatus(clientId:Integer, jobId:Integer, configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientJobConfigController]).toggleActiveStatus(clientId, jobId, configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientJobConfigController", "toggleActiveStatus", Seq(classOf[Integer], classOf[Integer], classOf[Integer]), "PUT", """ Client Job configuration management""", _prefix + """clientjobconfigs/toggleactive/$clientId<[^/]+>/$jobId<[^/]+>/$configId<[^/]+>""")
)
                      

}
                          

// @LINE:155
// @LINE:154
// @LINE:153
// @LINE:152
// @LINE:151
// @LINE:150
// @LINE:149
// @LINE:148
class ReverseViewGroupController {


// @LINE:151
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readAll", Seq(), "GET", """""", _prefix + """viewgroup/all""")
)
                      

// @LINE:153
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "create", Seq(), "POST", """""", _prefix + """viewgroup""")
)
                      

// @LINE:155
def readGroupCount(viewGroupId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readGroupCount(viewGroupId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readGroupCount", Seq(classOf[Integer]), "GET", """""", _prefix + """viewgroup/groupcount/$viewGroupId<[^/]+>""")
)
                      

// @LINE:149
def toggleActiveStatus(viewGroupId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).toggleActiveStatus(viewGroupId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """viewgroup/toggleactive/$viewGroupId<[^/]+>""")
)
                      

// @LINE:152
def read(viewGroupId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).read(viewGroupId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "read", Seq(classOf[Integer]), "GET", """""", _prefix + """viewgroup/$viewGroupId<[^/]+>""")
)
                      

// @LINE:150
def delete(viewGroupId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).delete(viewGroupId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """viewgroup/$viewGroupId<[^/]+>""")
)
                      

// @LINE:148
def readAllAsKeyValue(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).readAllAsKeyValue(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "readAllAsKeyValue", Seq(), "GET", """View Group mgmt""", _prefix + """viewgroup/keyvalue""")
)
                      

// @LINE:154
def update(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ViewGroupController]).update(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ViewGroupController", "update", Seq(), "PUT", """""", _prefix + """viewgroup/update""")
)
                      

}
                          

// @LINE:108
class ReverseTableauRest {


// @LINE:108
def syncTableauReports(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauRest]).syncTableauReports(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauRest", "syncTableauReports", Seq(), "GET", """""", _prefix + """sync_tableau_reports""")
)
                      

}
                          

// @LINE:99
// @LINE:98
// @LINE:97
// @LINE:96
// @LINE:95
// @LINE:94
class ReverseUserPermissionResourceInstanceController {


// @LINE:94
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAll", Seq(), "GET", """User based RLP mgmt""", _prefix + """userrlp""")
)
                      

// @LINE:96
def readAllEntityInstances(userId:Integer, resourceTypeId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllEntityInstances(userId, resourceTypeId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAllEntityInstances", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """userrlp/ressourceinstanceids/$userId<[^/]+>/$resourceTypeId<[^/]+>""")
)
                      

// @LINE:99
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "create", Seq(), "POST", """""", _prefix + """userrlp""")
)
                      

// @LINE:95
def readAllByUserAndResourType(userId:Integer, resourceTypeId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).readAllByUserAndResourType(userId, resourceTypeId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "readAllByUserAndResourType", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """userrlp/$userId<[^/]+>/$resourceTypeId<[^/]+>""")
)
                      

// @LINE:97
def toggleActiveStatus(userPermissionId:Integer, entityId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).toggleActiveStatus(userPermissionId, entityId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "toggleActiveStatus", Seq(classOf[Integer], classOf[String]), "PUT", """""", _prefix + """userrlp/toggleactive/$userPermissionId<[^/]+>/$entityId<[^/]+>""")
)
                      

// @LINE:98
def delete(userPermissionId:Integer, entityId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionResourceInstanceController]).delete(userPermissionId, entityId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionResourceInstanceController", "delete", Seq(classOf[Integer], classOf[String]), "DELETE", """""", _prefix + """userrlp/$userPermissionId<[^/]+>/$entityId<[^/]+>""")
)
                      

}
                          

// @LINE:168
// @LINE:167
// @LINE:107
// @LINE:106
// @LINE:105
class ReverseDashboardController {


// @LINE:106
def loadProjectDashboardData(projectId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadProjectDashboardData(projectId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadProjectDashboardData", Seq(classOf[String]), "POST", """""", _prefix + """dashboard/project/$projectId<[^/]+>""")
)
                      

// @LINE:105
def loadDashboardGroupData(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadDashboardGroupData(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadDashboardGroupData", Seq(), "POST", """ Tableau related pages""", _prefix + """dashboard""")
)
                      

// @LINE:168
def getFilterList(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getFilterList(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "getFilterList", Seq(), "GET", """""", _prefix + """filterlist""")
)
                      

// @LINE:167
def getDiffusionMap(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).getDiffusionMap(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "getDiffusionMap", Seq(), "POST", """ Diffusion Map""", _prefix + """diffusionMap""")
)
                      

// @LINE:107
def loadViewData(workbookId:String, viewId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.DashboardController]).loadViewData(workbookId, viewId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.DashboardController", "loadViewData", Seq(classOf[String], classOf[String]), "POST", """""", _prefix + """dashboard/view/$workbookId<[^/]+>/$viewId<[^/]+>""")
)
                      

}
                          

// @LINE:145
// @LINE:13
// @LINE:11
class ReverseXOBaseController {


// @LINE:13
def accessDenied(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).accessDenied(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "accessDenied", Seq(), "GET", """""", _prefix + """accessdenied""")
)
                      

// @LINE:11
def cspReportParser(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).cspReportParser(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "cspReportParser", Seq(), "POST", """ Security related requests and reports """, _prefix + """cspreport""")
)
                      

// @LINE:145
def heartBeat(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XOBaseController]).heartBeat(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XOBaseController", "heartBeat", Seq(), "GET", """""", _prefix + """heartbeat""")
)
                      

}
                          

// @LINE:56
// @LINE:55
// @LINE:54
// @LINE:53
// @LINE:52
// @LINE:51
// @LINE:50
// @LINE:49
class ReverseRoleController {


// @LINE:49
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readAll", Seq(), "GET", """ Role management""", _prefix + """roles""")
)
                      

// @LINE:50
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "create", Seq(), "POST", """""", _prefix + """roles""")
)
                      

// @LINE:54
def update(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).update(id), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "update", Seq(classOf[Integer]), "PUT", """""", _prefix + """roles/$id<[^/]+>""")
)
                      

// @LINE:56
def readUnassigned(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readUnassigned(id), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readUnassigned", Seq(classOf[Integer]), "GET", """""", _prefix + """roles/unassigned/$id<[^/]+>""")
)
                      

// @LINE:52
def toggleActiveStatus(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).toggleActiveStatus(id), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """roles/toggleactive/$id<[^/]+>""")
)
                      

// @LINE:53
def read(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).read(id), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "read", Seq(classOf[Integer]), "GET", """""", _prefix + """roles/$id<[^/]+>""")
)
                      

// @LINE:55
def delete(roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).delete(roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """roles/$roleId<[^/]+>""")
)
                      

// @LINE:51
def readAllAsKeyValue(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RoleController]).readAllAsKeyValue(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RoleController", "readAllAsKeyValue", Seq(), "GET", """""", _prefix + """roles/keyvalue""")
)
                      

}
                          

// @LINE:63
// @LINE:62
// @LINE:61
// @LINE:60
// @LINE:59
class ReversePermissionController {


// @LINE:60
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readAll", Seq(), "GET", """""", _prefix + """permissions""")
)
                      

// @LINE:62
def readUnAssignedRolePermissions(roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedRolePermissions(roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readUnAssignedRolePermissions", Seq(classOf[Integer]), "GET", """""", _prefix + """permissions/unassigned/$roleId<[^/]+>""")
)
                      

// @LINE:61
def toggleActiveStatus(id:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).toggleActiveStatus(id), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """permissions/toggleactive/$id<[^/]+>""")
)
                      

// @LINE:59
def read(roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).read(roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "read", Seq(classOf[Integer]), "GET", """Permission management""", _prefix + """permissions/$roleId<[^/]+>""")
)
                      

// @LINE:63
def readUnAssignedUserPermissions(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.PermissionController]).readUnAssignedUserPermissions(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.PermissionController", "readUnAssignedUserPermissions", Seq(classOf[Integer]), "GET", """""", _prefix + """permissions/unassigned_user_permissions/$userId<[^/]+>""")
)
                      

}
                          

// @LINE:71
// @LINE:70
// @LINE:69
// @LINE:68
// @LINE:67
class ReverseUserRoleController {


// @LINE:67
def readAll(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readAll(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "readAll", Seq(classOf[Integer]), "GET", """ user role REST services""", _prefix + """userroles/$userId<[^/]+>""")
)
                      

// @LINE:70
def toggleActiveStatus(userId:Integer, roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).toggleActiveStatus(userId, roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "toggleActiveStatus", Seq(classOf[Integer], classOf[Integer]), "PUT", """""", _prefix + """userroles/toggleactive/$userId<[^/]+>/$roleId<[^/]+>""")
)
                      

// @LINE:71
def readRolesCount(roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).readRolesCount(roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "readRolesCount", Seq(classOf[Integer]), "GET", """""", _prefix + """userroles/roles_count/$roleId<[^/]+>""")
)
                      

// @LINE:68
def create(userId:Integer, roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).create(userId, roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "create", Seq(classOf[Integer], classOf[Integer]), "POST", """""", _prefix + """userroles/$userId<[^/]+>/$roleId<[^/]+>""")
)
                      

// @LINE:69
def delete(userId:Integer, roleId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserRoleController]).delete(userId, roleId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserRoleController", "delete", Seq(classOf[Integer], classOf[Integer]), "DELETE", """""", _prefix + """userroles/$userId<[^/]+>/$roleId<[^/]+>""")
)
                      

}
                          

// @LINE:29
class ReverseResourceTypeController {


// @LINE:29
def readAllAsKeyValue(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.ResourceTypeController]).readAllAsKeyValue(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.ResourceTypeController", "readAllAsKeyValue", Seq(), "GET", """ Resource types based requests""", _prefix + """resourcetypes/keyvalue""")
)
                      

}
                          

// @LINE:46
// @LINE:45
// @LINE:44
// @LINE:43
// @LINE:42
// @LINE:41
// @LINE:40
// @LINE:39
// @LINE:38
// @LINE:37
// @LINE:36
// @LINE:35
// @LINE:34
// @LINE:33
// @LINE:32
// @LINE:26
// @LINE:23
// @LINE:20
// @LINE:19
// @LINE:16
// @LINE:14
class ReverseUserController {


// @LINE:45
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "readAll", Seq(), "GET", """""", _prefix + """users""")
)
                      

// @LINE:36
def verify(token:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).verify(token), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "verify", Seq(classOf[String]), "GET", """""", _prefix + """users/verify""")
)
                      

// @LINE:46
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "create", Seq(), "POST", """""", _prefix + """users""")
)
                      

// @LINE:43
def update(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).update(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "update", Seq(classOf[Integer]), "PUT", """""", _prefix + """users/$userId<[^/]+>""")
)
                      

// @LINE:35
def resetPassword(authToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPassword(authToken), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "resetPassword", Seq(classOf[String]), "POST", """""", _prefix + """users/resetpassword""")
)
                      

// @LINE:14
def jsonAccessDenied(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).jsonAccessDenied(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "jsonAccessDenied", Seq(), "GET", """""", _prefix + """jsonaccessdenied""")
)
                      

// @LINE:40
def uploadUsers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).uploadUsers(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "uploadUsers", Seq(), "POST", """""", _prefix + """users/upload""")
)
                      

// @LINE:34
def renderResetPasswordPage(authToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderResetPasswordPage(authToken), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "renderResetPasswordPage", Seq(classOf[String]), "GET", """""", _prefix + """users/resetpasswordpage""")
)
                      

// @LINE:32
def toggleActiveStatus(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).toggleActiveStatus(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """ User REST services""", _prefix + """users/toggleactive/$userId<[^/]+>""")
)
                      

// @LINE:16
def renderLoginPage(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).renderLoginPage(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "renderLoginPage", Seq(), "GET", """""", _prefix + """""")
)
                      

// @LINE:39
def forgotPassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPassword(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "forgotPassword", Seq(), "POST", """""", _prefix + """users/forgotpassword""")
)
                      

// @LINE:37
def changePassword(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).changePassword(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "changePassword", Seq(), "POST", """""", _prefix + """users/changepassword""")
)
                      

// @LINE:42
def read(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).read(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "read", Seq(classOf[Integer]), "GET", """""", _prefix + """users/$userId<[^/]+>""")
)
                      

// @LINE:44
def delete(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).delete(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """users/$userId<[^/]+>""")
)
                      

// @LINE:41
def readAllAsKeyValue(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).readAllAsKeyValue(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "readAllAsKeyValue", Seq(), "GET", """""", _prefix + """users/keyvalue""")
)
                      

// @LINE:38
def forgotPasswordPage(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).forgotPasswordPage(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "forgotPasswordPage", Seq(), "GET", """""", _prefix + """users/forgotpassword""")
)
                      

// @LINE:26
def pageDispatcher(authToken:String, currentPage:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).pageDispatcher(authToken, currentPage), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "pageDispatcher", Seq(classOf[String], classOf[String]), "GET", """ Xoportal Pages""", _prefix + """pagedispatcher""")
)
                      

// @LINE:33
def resetPasswordForUser(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).resetPasswordForUser(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "resetPasswordForUser", Seq(classOf[Integer]), "PUT", """""", _prefix + """users/resetpassword/$userId<[^/]+>""")
)
                      

// @LINE:20
def xossoCallBackHandler(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).xossoCallBackHandler(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "xossoCallBackHandler", Seq(), "GET", """""", _prefix + """logincallback""")
)
                      

// @LINE:23
def logout(authToken:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).logout(authToken), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "logout", Seq(classOf[String]), "GET", """ User Logout""", _prefix + """logout""")
)
                      

// @LINE:19
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserController]).login(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserController", "login", Seq(), "POST", """ REST services""", _prefix + """auth/login""")
)
                      

}
                          

// @LINE:140
class ReverseXoClientController {


// @LINE:140
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoClientController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoClientController", "readAll", Seq(), "GET", """ Clients mgmt""", _prefix + """clients""")
)
                      

}
                          

// @LINE:91
// @LINE:90
// @LINE:89
// @LINE:88
class ReverseUserPermissionController {


// @LINE:89
def create(userId:Integer, permissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).create(userId, permissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "create", Seq(classOf[Integer], classOf[Integer]), "POST", """""", _prefix + """userpermission/$userId<[^/]+>/$permissionId<[^/]+>""")
)
                      

// @LINE:90
def delete(userpermissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).delete(userpermissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """userpermission/$userpermissionId<[^/]+>""")
)
                      

// @LINE:91
def toggleActiveStatus(userpermissionId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).toggleActiveStatus(userpermissionId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """userpermission/toggleactive/$userpermissionId<[^/]+>""")
)
                      

// @LINE:88
def readAll(userId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.UserPermissionController]).readAll(userId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.UserPermissionController", "readAll", Seq(classOf[Integer]), "GET", """User Permission mgmt """, _prefix + """userpermission/$userId<[^/]+>""")
)
                      

}
                          

// @LINE:164
// @LINE:163
// @LINE:162
// @LINE:161
// @LINE:160
// @LINE:159
// @LINE:158
class ReverseTableauViewController {


// @LINE:164
def isDashboardExist(viewGroupId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).isDashboardExist(viewGroupId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "isDashboardExist", Seq(classOf[Integer]), "GET", """""", _prefix + """tableauview/checkdashboard/$viewGroupId<[^/]+>""")
)
                      

// @LINE:160
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "readAll", Seq(), "GET", """""", _prefix + """tableauview/all""")
)
                      

// @LINE:161
def read(tableauViewId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).read(tableauViewId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "read", Seq(classOf[String]), "GET", """""", _prefix + """tableauview/$tableauViewId<[^/]+>""")
)
                      

// @LINE:163
def toggleDashboardStatus(tableauViewId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleDashboardStatus(tableauViewId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "toggleDashboardStatus", Seq(classOf[String]), "PUT", """""", _prefix + """tableauview/toggledashboard/$tableauViewId<[^/]+>""")
)
                      

// @LINE:158
def readAllAsKeyValue(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).readAllAsKeyValue(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "readAllAsKeyValue", Seq(), "GET", """ Tableau View mgmt""", _prefix + """tableauview/keyvalue""")
)
                      

// @LINE:162
def update(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).update(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "update", Seq(), "PUT", """""", _prefix + """tableauview/update""")
)
                      

// @LINE:159
def toggleActiveStatus(tableauViewId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.TableauViewController]).toggleActiveStatus(tableauViewId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.TableauViewController", "toggleActiveStatus", Seq(classOf[String]), "PUT", """""", _prefix + """tableauview/toggleactive/$tableauViewId<[^/]+>""")
)
                      

}
                          

// @LINE:85
// @LINE:84
// @LINE:83
// @LINE:82
// @LINE:81
// @LINE:80
class ReverseRolePermissionResourceInstanceController {


// @LINE:80
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAll", Seq(), "GET", """Role based RLP mgmt""", _prefix + """rolerlp""")
)
                      

// @LINE:82
def readAllEntityInstances(roleId:Integer, resourceTypeId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllEntityInstances(roleId, resourceTypeId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAllEntityInstances", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """rolerlp/ressourceinstanceids/$roleId<[^/]+>/$resourceTypeId<[^/]+>""")
)
                      

// @LINE:85
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "create", Seq(), "POST", """""", _prefix + """rolerlp""")
)
                      

// @LINE:81
def readAllByRoleAndResourType(roleId:Integer, resourceTypeId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).readAllByRoleAndResourType(roleId, resourceTypeId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "readAllByRoleAndResourType", Seq(classOf[Integer], classOf[Integer]), "GET", """""", _prefix + """rolerlp/$roleId<[^/]+>/$resourceTypeId<[^/]+>""")
)
                      

// @LINE:83
def toggleActiveStatus(rolePermissionId:Integer, entityId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).toggleActiveStatus(rolePermissionId, entityId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "toggleActiveStatus", Seq(classOf[Integer], classOf[String]), "PUT", """""", _prefix + """rolerlp/toggleactive/$rolePermissionId<[^/]+>/$entityId<[^/]+>""")
)
                      

// @LINE:84
def delete(rolePermissionId:Integer, entityId:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.RolePermissionResourceInstanceController]).delete(rolePermissionId, entityId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.RolePermissionResourceInstanceController", "delete", Seq(classOf[Integer], classOf[String]), "DELETE", """""", _prefix + """rolerlp/$rolePermissionId<[^/]+>/$entityId<[^/]+>""")
)
                      

}
                          

// @LINE:119
// @LINE:118
// @LINE:117
// @LINE:116
// @LINE:115
// @LINE:114
// @LINE:113
// @LINE:112
class ReverseXoConfigTemplateController {


// @LINE:112
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "readAll", Seq(), "GET", """""", _prefix + """configtemplates""")
)
                      

// @LINE:113
def create(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).create(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "create", Seq(), "POST", """""", _prefix + """configtemplates""")
)
                      

// @LINE:115
def update(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).update(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "update", Seq(classOf[Integer]), "PUT", """""", _prefix + """configtemplates/$configId<[^/]+>""")
)
                      

// @LINE:118
def isConfigExist(configShortName:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).isConfigExist(configShortName), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "isConfigExist", Seq(classOf[String]), "GET", """""", _prefix + """configtemplates/search/$configShortName<[^/]+>""")
)
                      

// @LINE:119
def toggleActiveStatus(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).toggleActiveStatus(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "toggleActiveStatus", Seq(classOf[Integer]), "PUT", """""", _prefix + """configtemplates/toggleactive/$configId<[^/]+>""")
)
                      

// @LINE:114
def read(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).read(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "read", Seq(classOf[Integer]), "GET", """""", _prefix + """configtemplates/$configId<[^/]+>""")
)
                      

// @LINE:116
def delete(configId:Integer): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoConfigTemplateController]).delete(configId), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoConfigTemplateController", "delete", Seq(classOf[Integer]), "DELETE", """""", _prefix + """configtemplates/$configId<[^/]+>""")
)
                      

}
                          

// @LINE:143
class ReverseXoJobController {


// @LINE:143
def readAll(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[com.xo.web.controllers.XoJobController]).readAll(), HandlerDef(this.getClass.getClassLoader, "", "com.xo.web.controllers.XoJobController", "readAll", Seq(), "GET", """ Job mgmt""", _prefix + """jobs""")
)
                      

}
                          
}
        
    