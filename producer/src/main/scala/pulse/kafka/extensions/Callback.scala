package pulse.kafka.extensions

import com.twitter.util.Promise
import org.apache.kafka.clients.producer.{RecordMetadata, Callback => KCallback}

object Callback {
  import pulse.kafka.extensions.PromiseOps.onComplete

  def apply(p: Promise[RecordMetadata]): KCallback = new KCallback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
      onComplete(p, metadata, exception)
    }
  }
}
