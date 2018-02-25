name := """couponRest"""
organization := "com.shenkar"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.2"
libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.3.0"
libraryDependencies += "org.reactivemongo" %% "play2-reactivemongo" % "0.12.7-play26"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.shenkar.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.shenkar.binders._"
