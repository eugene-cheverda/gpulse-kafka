import sbt._
import pulse.plugin._
import bintray.BintrayKeys._
import sbt.Keys._

object local {
  object dependencies {

    object versions {
      val log4s    = "1.3.4"
      val typesafe = "1.3.1"
      val avro     = "1.8.1"

      object twitter {
        val finagle = "6.42.0"
      }
    }

    object avro {
      val core = "org.apache.avro" % "avro" % versions.avro
    }

    object log4s {
      val core = "org.log4s" %% "log4s" % versions.log4s
    }

    object finagle {
      val core   = "com.twitter" %% "finagle-http"   % versions.twitter.finagle
    }

    object typesafe {
      val config = "com.typesafe" % "config" % versions.typesafe
    }
  }

  def settings = Seq(
    bintrayOrganization := Some("impulse-io"),
    publishMavenStyle := true
  )
}
