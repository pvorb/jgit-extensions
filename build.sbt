name := "jgit-extensions"

organization := "de.vorb"

version := "0.0.0"

scalaVersion := "2.10.1"


homepage := Some(url("https://github.com/pvorb/jgit-extensions"))

licenses := Seq("MIT License" -> url("http://vorb.de/license/mit.html"))

mainClass := None


// Dependencies
libraryDependencies += "org.eclipse.jgit" % "org.eclipse.jgit" % "2.3.+"


// Publishing information
publishMavenStyle := true

publishTo <<= version { (version: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (version.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <scm>
    <url>git@github.com:pvorb/jgit-extensions.git</url>
    <connection>scm:git:git@github.com:pvorb/jgit-extensions.git</connection>
  </scm>
  <developers>
    <developer>
      <id>pvorb</id>
      <name>Paul Vorbach</name>
      <email>paul@vorb.de</email>
      <url>http://paul.vorba.ch/</url>
      <timezone>+1</timezone>
    </developer>
  </developers>)
