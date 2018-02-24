name := """couponRest"""
organization := "com.shenkar"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.2"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.shenkar.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.shenkar.binders._"
