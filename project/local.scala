import sbt._
import pulse.plugin._
import bintray.BintrayKeys._
import sbt.Keys._

object local {
  object dependencies {

    object versions {

      val avro = "1.8.1"

      object pulse {
        val common = "1.0.5"
      }

    }

    object pulse {
      val common = "impulse-io" %% "common" % versions.pulse.common
    }

    object avro {
      val core = "org.apache.avro" % "avro" % versions.avro
    }

  }

  def settings = Seq(
    bintrayOrganization := Some("impulse-io"),
    publishMavenStyle := true
  )
}
