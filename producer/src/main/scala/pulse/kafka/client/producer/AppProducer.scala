package pulse.kafka.client.producer

import com.twitter.util.Future
import pulse.common.syntax._


class AppProducer(properties: Map[String, String]) extends Producer {
  import pulse.kafka.extensions.catsStdInstancesForFuture
  import pulse.kafka.extensions.managedEnvelopeProducer
  import scala.concurrent.ExecutionContext.Implicits.global
  override def session(body: EnvelopeProducer => Future[ProducerResponse]) = use(EnvelopeProducer(properties)) {
    body
  }
}

object AppProducer {
  def apply(properties: Map[String, String]) = new AppProducer(properties)
}