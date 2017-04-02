package pulse.kafka.client.producer

import org.apache.kafka.clients.producer.RecordMetadata
import pulse.kafka.extensions.Callback

class ProducerOps(self: EnvelopeProducer) {

  import pulse.kafka.extensions.PromiseOps._
  import scala.concurrent.ExecutionContext.Implicits._

  def sendAsync(record: Envelope) = {
    toTwitter[RecordMetadata](p => {
      self.send(record, Callback(p))
    }).map(metadata => {
      ProducerResponse(metadata.topic(), metadata.offset(), metadata.partition())
    })
  }
}
