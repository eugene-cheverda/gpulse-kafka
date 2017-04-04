import sbt._, Keys._
import dependencies._
import pulse.plugin._
import local.dependencies._

libraryDependencies ++= Seq (
  cats.all,
  fs2.core,
  finagle.core,
  avro.core,
  typesafe.config,
  kafka.core,
  _test(scalatest.core)
)

publishing.settings

local.settings
