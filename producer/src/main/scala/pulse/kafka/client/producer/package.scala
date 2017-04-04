package pulse.kafka.client

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

package object producer extends ToProducerOps {
  type EnvelopeProducer = KafkaProducer[Int, Array[Byte]]
  type Envelope = ProducerRecord[Int, Array[Byte]]
}

