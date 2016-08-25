name := "FunTicTacToe"

version := "1.0"

scalaVersion := "2.11.8"

val funTicTacToe = (project in file(".")).enablePlugins(plugins.JUnitXmlReportPlugin)

parallelExecution in Test := true

mainClass := Some("TicTacToe")

libraryDependencies ++= Seq(
	"com.google.inject" % "guice" % "3.0",
	"com.google.guava" % "guava" % "19.0",
	"com.google.guava" % "guava-collections" % "r03",
	
	"org.pcollections" % "pcollections" % "2.1.2",
	"ch.qos.logback" % "logback-classic" % "1.1.7",

	"com.fasterxml.jackson.core" % "jackson-core" % "2.8.0",
	"com.fasterxml.jackson.core" % "jackson-databind" % "2.8.0",
	"com.fasterxml.jackson.dataformat" % "jackson-dataformat-xml" % "2.8.0",

	"org.mockito" % "mockito-core" % "1.10.19",
	"org.hamcrest" % "hamcrest-all" % "1.3",
	"junit" % "junit" % "4.12" % Test
)
