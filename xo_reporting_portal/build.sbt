import com.typesafe.sbt.SbtAspectj._
import com.typesafe.sbt.SbtAspectj.AspectjKeys._

name := """Xo Diffusion Map Reporting Portal"""

version := "4.4"

organization := "com.xo.web"

scalaVersion := "2.11.7"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

aspectjSettings

verbose in Aspectj := false

showWeaveInfo in Aspectj := false

//Keys.parallelExecution in Test:= false

//javaOptions in Test ++= Seq("-Xdebug","-Xrunjdwp:server=y,transport=dt_socket,address=4000,suspend=y")

javaOptions in Test ++= Seq("-Dconfig.file=conf/test.conf","-Xms512M","-Xmx2048M","-XX:MaxPermSize=2048M")

javaOptions ++= Seq("-Xmx512M", "-Xmx2048M", "-XX:MaxPermSize=2048M")

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

libraryDependencies ++= Seq(
	javaJdbc,
    filters withSources(),
	// Aspectj related packages
    "org.aspectj" % "aspectjrt" % "1.8.4",
    "org.aspectj" % "aspectjweaver" % "1.8.4",
    // Hikari related packages
    "com.edulify" %% "play-hikaricp" % "2.0.3" excludeAll(ExclusionRule(organization ="com.zaxxer"),ExclusionRule("HikariCP-parent")),
    "com.zaxxer" % "HikariCP-java6" % "2.3.7",
	// Hibernate related packages
    "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final",
	// MySQL packages
    "mysql" % "mysql-connector-java" % "5.1.34",
	// Bcrypt Encryption packages 
    "org.mindrot" % "jbcrypt" % "0.3m",
	// Web jars related packages
    "org.webjars" %% "webjars-play" % "2.3.0-3",
    "org.webjars" % "jquery" % "2.1.3",
    "org.webjars" % "knockout" % "3.3.0",
    "org.webjars" % "foundation" % "5.5.0",
    "org.webjars" % "selectize.js" % "0.12.1",
    // Apache http components
	"org.apache.httpcomponents" % "httpclient" % "4.3.6",
	// Mail plugin related packages
	// Comment the next line for local development:
	"com.feth" %% "play-easymail" % "0.6.7",
	// Serialization library kryo packages (To avoid asm lib issues we are using the kryo-shaded package). 
    "com.esotericsoftware" % "kryo-shaded" % "4.0.0",
	"commons-io" % "commons-io" % "2.5",
	// For Diffusion Map
	"org.apache.thrift" % "libfb303" % "0.9.0",
	"org.apache.thrift" % "libthrift" % "0.9.0",
	"log4j" % "log4j" % "1.2.14",
	"org.slf4j" % "slf4j-api" % "1.5.11",
	"org.slf4j" % "slf4j-log4j12" % "1.5.11",
	"org.apache.zookeeper" % "zookeeper" % "3.4.6",
	//Elastic Search
	"jp.co.bizreach" % "elasticsearch" % "2.3.5",
	// https://mvnrepository.com/artifact/com.univocity/univocity-parsers
	"com.univocity" % "univocity-parsers" % "2.3.0",
	// https://mvnrepository.com/artifact/io.searchbox/jest
	"io.searchbox" % "jest" % "2.0.4",
	"commons-io" % "commons-io" % "2.5"
	
)

doc in Compile <<= target.map(_ / "none")

inputs in Aspectj <+= compiledClasses

binaries in Aspectj <++= update map { report => report.matching(moduleFilter(organization = "org.aspectj"))}

products in Compile <<= products in Aspectj

products in Runtime <<= products in Compile

resolvers ++= Seq(
	// Common apache maven resolver
  	"Apache" at "http://repo1.maven.org/maven2/",
	// Bcrypt lib related resolver
  	"jBCrypt Repository" at "http://repo1.maven.org/maven2/org/"
)

// Hikari related packages
resolvers += Resolver.url("Edulify Repository", url("http://edulify.github.io/modules/releases/"))(Resolver.ivyStylePatterns)

// Additional java options
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint:unchecked", "-encoding", "UTF-8")



