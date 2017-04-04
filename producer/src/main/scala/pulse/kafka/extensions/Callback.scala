package pulse.kafka.extensions

import com.twitter.util.Promise
import fs2.Strategy
import org.apache.kafka.clients.producer.{RecordMetadata, Callback => KCallback}

object Callback {
  import pulse.kafka.extensions.PromiseOps.onComplete

  def apply(p: Promise[RecordMetadata])(implicit strategy: Strategy): KCallback = new KCallback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
      strategy(onComplete(p, metadata, exception))
    }
  }
}
