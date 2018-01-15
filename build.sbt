import scala.sys.process._

name := """play-react-template"""
organization := "rerorero.github.com"

version := "1.0-SNAPSHOT"

scalaVersion := "2.12.2"

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-Xlint:-unused,_",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  filters,
  "com.typesafe.play" %% "play-json" % play.core.PlayVersion.current,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalacheck" %% "scalacheck" % "1.13.4",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.0" % Test,
  "org.mockito" % "mockito-core" % "2.8.47" % Test
)

val frontProjectDir = file("front")
val npmBuild = taskKey[Unit]("execute npm install && npm run build")
npmBuild := {
  (Process("npm install", frontProjectDir) #&& Process("npm run build", frontProjectDir)).lineStream_! foreach println
}

val npmClean = taskKey[Unit]("execute npm run clean:once")
npmClean := {
  Process("npm run clean", frontProjectDir).lineStream_! foreach println
}

val compileAll = TaskKey[Unit]("compileAll", "Build Both Play and front")
compileAll := ((compile in Compile) dependsOn npmBuild).value

clean := (clean dependsOn npmClean).value

val distDir = file("dist")
cleanFiles += distDir

dist := {
  val zipFile = dist.value
  val target = distDir / zipFile.getName
  IO.copyFile(zipFile, target)
  zipFile
}
dist := (dist dependsOn compileAll).value

