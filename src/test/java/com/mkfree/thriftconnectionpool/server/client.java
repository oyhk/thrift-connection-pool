package com.mkfree.thriftconnectionpool.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.junit.Test;

import com.mkfree.thriftconnectionpool.provider.ThriftTSocketConnectionProvider;
import com.mkfree.thriftconnectionpool.thrift.TestService;

public class client {

	private final ThriftTSocketConnectionProvider connectionProvider = new ThriftTSocketConnectionProvider();

	@Test
	public void getName() {
		try {
			TSocket framedTransport = connectionProvider.getConnention();
			TTransport transport = new TFramedTransport(framedTransport);
			TProtocol protocol = new TCompactProtocol(transport);// 使用高密度二进制协议
			TestService.Client client = new TestService.Client(protocol);
			String name = client.getName();
			System.out.println(name);
			connectionProvider.returnConnection(framedTransport);
		} catch (TException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getNameByExecutors() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						TSocket framedTransport = connectionProvider.getConnention();
						TProtocol protocol = new TCompactProtocol(framedTransport);// 使用高密度二进制协议
						TestService.Client client = new TestService.Client(protocol);
						String name;
						name = client.getName();
						System.out.println(name);
						connectionProvider.returnConnection(framedTransport);
					} catch (TException e) {
						e.printStackTrace();
					}
				}
			});
		}
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
