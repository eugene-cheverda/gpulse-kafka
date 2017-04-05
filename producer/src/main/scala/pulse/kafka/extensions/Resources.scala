package pulse.kafka.extensions

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import pulse.common.Managed
import pulse.kafka.client.producer.EnvelopeProducer

trait Resources {
  implicit def managedByteArrayOutputStream = new Managed[ByteArrayOutputStream] {
    override def close(instance: ByteArrayOutputStream): Unit = instance.close()
  }

  implicit def managedEnvelopeProducer = new Managed[EnvelopeProducer] {
    override def close(instance: EnvelopeProducer): Unit = instance.close()
  }

  implicit def managedByteArrayInputStream = new Managed[ByteArrayInputStream] {
    override def close(instance: ByteArrayInputStream): Unit = instance.close()
  }
}