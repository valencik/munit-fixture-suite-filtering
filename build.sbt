ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "munit-suite-fixture-bug",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
