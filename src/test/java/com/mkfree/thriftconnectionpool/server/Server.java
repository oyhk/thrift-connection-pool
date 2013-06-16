package com.mkfree.thriftconnectionpool.server;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;


public class Server {

	public static void startServer() {
		try {
			// 非阻塞传输
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(9901);
			// 异步IO，需要使用TFramedTransport，它将分块缓存读取。
			TTransportFactory transportFactory = new TFramedTransport.Factory();
			// 处理器
			TestService.Processor<TestServiceImpl> processor = new TestService.Processor<TestServiceImpl>(new TestServiceImpl());
			// 使用高密度二进制协议
			TProtocolFactory proFactory = new TCompactProtocol.Factory();

			// 创建服务器
			TServer server = new TThreadedSelectorServer(new Args(serverTransport).protocolFactory(proFactory).transportFactory(transportFactory).processor(processor));

			System.out.println("Start server on port 9901 ...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		startServer();
	}
}
