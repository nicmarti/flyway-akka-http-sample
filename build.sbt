// SBT 0.13.7 required (for blank lines removed)

// factor out common settings into a sequence
lazy val commonSettings = Seq(
  organization := "com.captaindash",
  version := "0.1.0",
  // set the Scala version used for the project
  scalaVersion := "2.10.6"
)


lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "Test debug flyway sur Clever cloud",
    ivyLoggingLevel := UpdateLogging.Full
  )

val akkaStreamV = "2.0.1"

val versionAkka = "2.3.14+"

// Flyway for database migrations
libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.191",
    // For simple HTTP server (required for Clevercloud
  "com.typesafe.akka" %% "akka-actor" % versionAkka,
  "com.typesafe.akka" %% "akka-testkit" % versionAkka,
  "com.typesafe.akka" %% "akka-slf4j" % versionAkka,
  "com.typesafe.akka" %% "akka-stream-experimental"             % akkaStreamV,
  "com.typesafe.akka" %% "akka-http-core-experimental"          % akkaStreamV,
  "com.typesafe.akka" %% "akka-http-experimental"               % akkaStreamV,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental"    % akkaStreamV,
  "com.typesafe.akka" %% "akka-http-testkit-experimental"       % akkaStreamV
)

// You can test flyway on your local server with 'sbt flywayInfo'
flywayUrl := "jdbc:h2:file:./target/foobar"

flywayUser := "SA"
