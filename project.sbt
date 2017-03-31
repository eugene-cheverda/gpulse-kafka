organization in Global := "pulse"

scalaVersion in Global := "2.11.8"

settings.common

lazy val kafka_client = project.in(file(".")).aggregate(producer)

lazy val producer = project
