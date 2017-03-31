package pulse.kafka.client.producer

case class ProducerResponse(topic: String, offset: Long, partition: Int)
