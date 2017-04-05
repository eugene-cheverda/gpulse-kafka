import sbt._
import pulse.plugin._
import bintray.BintrayKeys._
import sbt.Keys._

object local {
  object dependencies {

    object versions {
      val shapeless = "2.3.2"
      val simulacrum = "0.10.0"
      val commons = "3.5"
      val logback = "1.1.7"
      val scalalog = "3.5.0"
      val finch = "0.13.1"
      val circe = "0.7.0"
      val scopt = "3.5.0"
      val avro = "1.8.1"
      val typesafe = "1.3.1"
      val kafka = "0.10.2.0"
      object twitter {
        val server  = "1.27.0"
        val finagle = "6.42.0"
      }
      object pulse {
        val common = "1.0.5"
      }
      val log4s = "1.3.4"
    }

    object pulse {
      val common = "impulse-io" %% "common" % versions.pulse.common
    }

    object avro {
      val core = "org.apache.avro" % "avro" % versions.avro
    }

    object typesafe {
      val config = "com.typesafe" % "config" % versions.typesafe
    }

    object scopt {
      val core = "com.github.scopt" %% "scopt" % versions.scopt
    }

    object finch {
      val core  = "com.github.finagle" %% "finch-core"  % versions.finch
      val circe = "com.github.finagle" %% "finch-circe" % versions.finch
      val test  = "com.github.finagle" %% "finch-test"  % versions.finch
    }

    object circe {
      val core    = "io.circe" %% "circe-core"    % versions.circe
      val generic = "io.circe" %% "circe-generic" % versions.circe
      val parser  = "io.circe" %% "circe-parser"  % versions.circe
    }

    object finagle {
      val core   = "com.twitter" %% "finagle-http"   % versions.twitter.finagle
      val server = "com.twitter" %% "twitter-server" % versions.twitter.server
    }

    object logback {
      val core = "ch.qos.logback" % "logback-classic" % versions.logback
    }

    object logger {
      val core = "com.typesafe.scala-logging" %% "scala-logging" % versions.scalalog
    }

    object shapeless {
      val core = "com.chuusai" %% "shapeless" % versions.shapeless
    }

    object simulacrum {
      val core = "com.github.mpilquist" %% "simulacrum" % versions.simulacrum
    }

    object apache {
      val lang = "org.apache.commons" % "commons-lang3" % versions.commons
    }
    object log4s {
      val core = "org.log4s" %% "log4s" % versions.log4s
    }
  }

  def settings = Seq(
    bintrayOrganization := Some("impulse-io"),
    publishMavenStyle := true
  )
}
