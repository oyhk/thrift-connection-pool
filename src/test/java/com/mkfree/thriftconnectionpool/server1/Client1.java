package com.mkfree.thriftconnectionpool.server1;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.mkfree.thriftconnectionpool.provider.ThriftTSocketConnectionProvider;
import com.mkfree.thriftconnectionpool.thrift.TestService;

public class Client1 {

	public static void main(String[] args) {

		ThriftTSocketConnectionProvider connectionProvider = new ThriftTSocketConnectionProvider();
		try {
			TTransport transport = connectionProvider.getConnention();
			TProtocol protocol = new TBinaryProtocol(transport);
			TestService.Client client = new TestService.Client(protocol);
			String name = client.getName();
			System.out.println(name);
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
	}
}
