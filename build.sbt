name := "spark testing"

version := "0.0.1"

organization := "com.github.ezhulenev"

scalaVersion := "2.10.4"

scalacOptions += "-deprecation"

scalacOptions += "-feature"


// Resolvers

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

resolvers += "Scalafi Bintray Repo" at "http://dl.bintray.com/ezhulenev/releases"

// Library Dependencies

libraryDependencies ++= Seq(
  "org.slf4j"          % "slf4j-api"       % "1.7.7",
  "ch.qos.logback"     % "logback-classic" % "1.1.2",
  "com.scalafi"       %% "scala-openbook"  % "0.0.4",
  "org.apache.spark"  %% "spark-core"      % "1.1.0" excludeAll(
    ExclusionRule("commons-beanutils", "commons-beanutils-core"),
    ExclusionRule("commons-collections", "commons-collections"),
    ExclusionRule("commons-logging", "commons-logging"),
    ExclusionRule("org.slf4j", "slf4j-log4j12"),
    ExclusionRule("org.hamcrest", "hamcrest-core"),
    ExclusionRule("junit", "junit"),
    ExclusionRule("org.jboss.netty", "netty"),
    ExclusionRule("com.esotericsoftware.minlog", "minlog")
    )
)

// Test Dependencies

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"   % "2.2.0" % "test"
)