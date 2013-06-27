package com.mkfree.thriftconnectionpool.provider;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.transport.TSocket;

import com.mkfree.thriftconnectionpool.factory.ThriftTSocketFactory;

/**
 * thrift TSocket 连接池提供对象
 * 
 * @author oyhk
 * 
 *         2013-6-19 下午12:38:56
 */
public class ThriftTSocketConnectionProvider implements ConnectionProvider {

	/** 对象缓存池 */
	private GenericObjectPool<TSocket> objectPool = null;
	private String host = "localhost";
	private int port = 9901;
	private int timeout = 5000;

	public ThriftTSocketConnectionProvider() {
		initThriftTSocketConnectionPool();
	}

	public ThriftTSocketConnectionProvider(String host, int port, int timeout) {
		this.host = host;
		this.port = port;
		this.timeout = timeout;
		initThriftTSocketConnectionPool();
	}

	private void initThriftTSocketConnectionPool() {
		GenericObjectPool.Config config = new GenericObjectPool.Config();
		objectPool = new GenericObjectPool<TSocket>(new ThriftTSocketFactory(host, port, timeout), config);
	}

	@Override
	public TSocket getConnention() {
		try {
			return objectPool.borrowObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void returnConnection(TSocket socket) {
		try {
			objectPool.returnObject(socket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
