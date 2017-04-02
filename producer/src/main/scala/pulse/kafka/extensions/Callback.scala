package pulse.kafka.extensions

import com.twitter.util.Promise
import org.apache.kafka.clients.producer.{RecordMetadata, Callback => KCallback}

import scala.concurrent.ExecutionContext

object Callback {
  import pulse.kafka.extensions.PromiseOps.onComplete

  def apply(p: Promise[RecordMetadata])(implicit tp: ExecutionContext): KCallback = new KCallback {
    override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
      tp.execute(new Runnable {
        override def run(): Unit = onComplete(p, metadata, exception)
      })
    }
  }
}
