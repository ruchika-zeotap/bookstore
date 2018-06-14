import com.typesafe.sbt.packager.MappingsHelper._

name := """bookstore"""
organization := "zeotap"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, PlayNettyServer).disablePlugins(PlayAkkaHttpServer, PlayScala)

resolvers += Resolver.mavenLocal

scalaVersion := "2.12.2"
libraryDependencies += "javax.mail" % "mail" % "1.4"
libraryDependencies += guice
libraryDependencies ++= Seq(
  ws,
  filters,
  evolutions,
  javaJdbc,
  "org.postgresql" % "postgresql" % "42.1.1"
)

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.9.2"
libraryDependencies += "com.rabbitmq" % "amqp-client" % "4.2.0"
libraryDependencies += "log4j" % "log4j" % "1.2.17"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.8.1"
libraryDependencies += "com.bazaarvoice.jolt" % "jolt-core" % "0.1.0"
libraryDependencies += "com.bazaarvoice.jolt" % "json-utils" % "0.1.0"
// https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.16"
// https://mvnrepository.com/artifact/com.itextpdf/itextpdf
libraryDependencies += "com.itextpdf" % "itextpdf" % "5.5.10"
libraryDependencies += "org.apache.axis" % "axis" % "1.4"
libraryDependencies += "commons-discovery" % "commons-discovery" % "0.5"
libraryDependencies += "javax.xml" % "jaxrpc-api" % "1.1"
libraryDependencies += "wsdl4j" % "wsdl4j" % "1.6.2"
// https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
libraryDependencies += "com.googlecode.json-simple" % "json-simple" % "1.1"
libraryDependencies += "org.apache.velocity" % "velocity" % "1.7"
// https://mvnrepository.com/artifact/javax.json/javax.json-api
//libraryDependencies += "javax.json" % "javax.json-api" % "1.0-b01"
libraryDependencies ++= Seq(
  javaJdbc % Test,
  "org.powermock" % "powermock-api-mockito" % "1.7.1",
  "org.powermock" % "powermock-module-junit4" % "1.7.1"
)
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.0"
libraryDependencies += "org.apache.poi" % "poi" % "3.17"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.3.0"
libraryDependencies += "io.jsonwebtoken" % "jjwt" % "0.9.0"
jacoco.settings

mappings in Universal ++= directory(baseDirectory.value / "public")

PlayKeys.devSettings ++= Seq("play.server.netty.maxHeaderSize" -> "16384")