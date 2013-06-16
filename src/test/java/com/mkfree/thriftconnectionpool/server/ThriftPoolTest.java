package com.mkfree.thriftconnectionpool.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.junit.Test;

import com.mkfree.thriftclientpool.GenericConnectionProvider;

public class ThriftPoolTest {

	private final GenericConnectionProvider connectionProvider = new GenericConnectionProvider();

	@Test
	public void getName() {
		try {
			TFramedTransport framedTransport = connectionProvider.getConnention();
			TProtocol protocol = new TCompactProtocol(framedTransport);// 使用高密度二进制协议
			TestService.Client client = new TestService.Client(protocol);
			String name = client.getName();
			System.out.println(name);
			connectionProvider.returnConnection(framedTransport);
			int poolSize = connectionProvider.getPoolSize();
			System.out.println(poolSize);
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
						TFramedTransport framedTransport = connectionProvider.getConnention();
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
		int poolSize = connectionProvider.getPoolSize();
		System.out.println(poolSize);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
