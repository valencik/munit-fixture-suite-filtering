ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "munit-suite-fixture-bug",
    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0-M5" % Test,
    libraryDependencies += "org.typelevel" %% "munit-cats-effect" % "2.0-cd8357d-SNAPSHOT" % Test
  )
