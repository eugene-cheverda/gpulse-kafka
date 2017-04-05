package pulse.kafka.avro

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, DataInputStream, File}

import com.twitter.util.Future
import org.apache.avro.Schema
import org.apache.avro.file.DataFileWriter
import org.apache.avro.generic.{GenericDatumReader, GenericDatumWriter, GenericRecord}
import org.apache.avro.io.DecoderFactory

object AvroUtils {

  import pulse.common.syntax._

  def jsonToAvroBytes(json: String, schemaFile: File): Future[Array[Byte]] = {
    import pulse.kafka.extensions.managedByteArrayOutputStream
    import scala.concurrent.ExecutionContext.Implicits._
    import pulse.kafka.extensions.catsStdInstancesForFuture
    use(new ByteArrayOutputStream())(output => {
      for {
        s <- loadSchema(schemaFile)
        _ <- convertImpl(json, output, s)
      } yield output.toByteArray
    })
  }

  def getReader(input: ByteArrayInputStream, schemaSpec: Schema, w: DataFileWriter[GenericRecord]) = Future.value {
    val reader = new GenericDatumReader[GenericRecord](schemaSpec)
    val datum = reader.read(null, getJsonDecoder(input, schemaSpec))
    w.append(datum)
    w.flush()
    reader
  }

  private def convertImpl(json: String, output: ByteArrayOutputStream, schemaSpec: Schema): Future[GenericDatumReader[GenericRecord]] = {
    import pulse.kafka.extensions.managedByteArrayInputStream
    import scala.concurrent.ExecutionContext.Implicits._
    import pulse.kafka.extensions.catsStdInstancesForFuture
    use(new ByteArrayInputStream(json.getBytes))(input => {
      for {
        w <- getWriter(output, schemaSpec)
        r <- getReader(input, schemaSpec, w)
      } yield r
    })
  }

  private def getWriter(output: ByteArrayOutputStream, schemaSpec: Schema) = {
    Future.value {
      val writer = new DataFileWriter[GenericRecord](new GenericDatumWriter[GenericRecord]())
      writer.create(schemaSpec, output)
    }
  }

  private def getJsonDecoder(input: ByteArrayInputStream, schema: Schema) =
    DecoderFactory.get.jsonDecoder(schema, new DataInputStream(input))

  private def loadSchema(schemaFile: File): Future[Schema] =
    Future.value {
      new Schema.Parser().parse(schemaFile)
    }

}