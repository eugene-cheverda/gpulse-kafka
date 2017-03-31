import sbt._, Keys._
import dependencies._

libraryDependencies ++= Seq (
  cats.all,
  fs2.core,
  finagle.core,
  avro.core,
  typesafe.config,
  kafka.core,
  _test(scalatest.core)
)

console.settings
