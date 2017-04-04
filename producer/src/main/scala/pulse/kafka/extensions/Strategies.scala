package pulse.kafka.extensions

import fs2.Strategy

object Strategies {
  implicit val strategy = Strategy.fromFixedDaemonPool(Runtime.getRuntime.availableProcessors)
}
