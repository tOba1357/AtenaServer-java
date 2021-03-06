name := """SyussekiAppServer-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.37",
  "org.webjars" % "bootstrap" % "3.3.5",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

sources in (Compile, doc) := Seq.empty

publishArtifact in (Compile, packageDoc) := false

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
