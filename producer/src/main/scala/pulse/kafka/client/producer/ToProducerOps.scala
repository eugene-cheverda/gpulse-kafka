package pulse.kafka.client.producer

trait ToProducerOps {
  implicit def wrap(producer: EnvelopeProducer): ProducerOps = new ProducerOps(producer)
}
