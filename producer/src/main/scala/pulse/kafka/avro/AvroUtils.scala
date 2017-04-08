package pulse.kafka.avro

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, DataInputStream, File}

import com.twitter.util.Future
import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.{GenericDatumReader, GenericDatumWriter, GenericRecord}
import org.apache.avro.io.DecoderFactory
import pulse.kafka.extensions.managedByteArrayInputStream
import pulse.kafka.extensions.managedByteArrayOutputStream
import pulse.kafka.extensions.catsStdInstancesForFuture
import scala.concurrent.ExecutionContext.Implicits._

object AvroUtils {

  import pulse.common.syntax._


  def jsonToAvroBytes(json: String, schemaFile: File): Future[Array[Byte]] =
    use(new ByteArrayOutputStream()) { output =>
      for {
        s <- loadSchema(schemaFile)
        _ <- convertImpl(json, output, s)
      } yield output.toByteArray
    }

  private def convertImpl(json: String, output: ByteArrayOutputStream, schemaSpec: Schema): Future[GenericDatumReader[GenericRecord]] =

    use(new ByteArrayInputStream(json.getBytes)) { input =>
      for {
        w <- getWriter(output, schemaSpec)
        r <- getReader(input, schemaSpec, w)
      } yield r
    }

  def getReader(input: ByteArrayInputStream, schemaSpec: Schema, w: DataFileWriter[GenericRecord]) = Future.value {
    val reader = new GenericDatumReader[GenericRecord](schemaSpec)
    val datum = reader.read(null, getJsonDecoder(input, schemaSpec))
    w.append(datum)
    w.flush()
    reader
  }

  private def getJsonDecoder(input: ByteArrayInputStream, schema: Schema) =
    DecoderFactory.get.jsonDecoder(schema, new DataInputStream(input))

  private def getWriter(output: ByteArrayOutputStream, schemaSpec: Schema) = {
    Future.value {
      val writer = new DataFileWriter[GenericRecord](new GenericDatumWriter[GenericRecord]())
      writer.create(schemaSpec, output)
    }
  }

  private def loadSchema(schemaFile: File): Future[Schema] =
    Future {
      new Schema.Parser().parse(schemaFile)
    }
}