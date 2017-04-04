package pulse.kafka.client

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

package object producer extends ToProducerOps {
  type Key              = Int
  type Payload          = Array[Byte]
  type EnvelopeProducer = KafkaProducer [Key, Payload]
  type Envelope         = ProducerRecord[Key, Payload]
}

