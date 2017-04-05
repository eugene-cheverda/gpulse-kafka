import pulse.plugin._

organization in Global := "impulse-io"

scalaVersion in Global := "2.11.8"

lazy val gpulse_kafka = project.in(file(".")).aggregate(producer)

lazy val producer = project

settings.common

publishing.ignore
