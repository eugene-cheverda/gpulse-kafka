package pulse.kafka.client.producer

import com.twitter.util.Future

import scala.util.Try

trait Producer {
  def session(body: EnvelopeProducer => Future[ProducerResponse]) : Future[ProducerResponse]
}






