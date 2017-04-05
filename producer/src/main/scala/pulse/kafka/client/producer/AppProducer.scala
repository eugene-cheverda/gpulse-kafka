package pulse.kafka.client.producer

import com.twitter.util.Future
import pulse.common.syntax._


class AppProducer(properties: Map[String, String]) extends Producer {
  import pulse.kafka.extensions._
  import scala.concurrent.ExecutionContext.Implicits.global
  import pulse.kafka.extensions.managedEnvelopeProducer
  override def session(body: EnvelopeProducer => Future[ProducerResponse]) = {
    use(EnvelopeProducer(properties))(body(_))
  }
}

object AppProducer {
  def apply(properties: Map[String, String]) = new AppProducer(properties)
}