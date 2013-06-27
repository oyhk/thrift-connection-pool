package com.mkfree.thriftconnectionpool.server1;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.mkfree.thriftconnectionpool.thrift.TestService;
import com.mkfree.thriftconnectionpool.thrift.TestServiceImpl;

public class Server1 {

	public static void main(String[] args) {
		try {
			TestService.Processor<TestServiceImpl> processor = new TestService.Processor<TestServiceImpl>(new TestServiceImpl());
			TServerTransport serverTransport = new TServerSocket(9901);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			System.out.println("Starting the simple server 9901...");
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}
