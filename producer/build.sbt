import sbt._, Keys._
import pulse.plugin._
import pulse.plugin.dependencies._
import local.dependencies._

libraryDependencies ++= Seq (
  cats.all,
  fs2.core,
  finagle.core,
  avro.core,
  typesafe.config,
  kafka.core,
  pulse.common,
  _test(scalatest.core)
)

publishing.settings

local.settings
