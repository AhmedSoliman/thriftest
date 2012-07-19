package com.cloud9ers.director

import com.twitter.finagle.thrift.ThriftServerFramedCodec
import org.apache.thrift.protocol.TBinaryProtocol
import com.twitter.finagle.builder.{ServerBuilder, Server}
import java.net.InetSocketAddress
import com.twitter.util.Future

object ThriftServer {
  def main(args: Array[String]) {
    // Implement the Thrift Interface
    val processor = new LabsDirector.FutureIface {
      def freeze(s: String): Future[Unit] = {
      	println("Got FREEZE")
      	Future.Unit
    }
}

    // Convert the Thrift Processor to a Finagle Service
    val service = new LabsDirector.FinagledService(processor, new TBinaryProtocol.Factory())

    val server: Server = ServerBuilder()
      .bindTo(new InetSocketAddress(9999))
      .codec(ThriftServerFramedCodec())
      .name("thriftserver")
      .build(service)
  }
}