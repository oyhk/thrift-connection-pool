package com.mkfree.thriftconnectionpool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

@SuppressWarnings("deprecation")
public class GenericConnectionProvider<TFramedTransport> implements ConnectionProvider {
	/** 对象缓存池 */
	private GenericObjectPool<TFramedTransport> objectPool = null;

	public GenericConnectionProvider() {
		initThriftPool();
	}

	public void initThriftPool() {
		objectPool = new GenericObjectPool<TFramedTransport>();
		PoolableObjectFactory<TFramedTransport> factory = (PoolableObjectFactory<TFramedTransport>) new ThriftPoolableObjectFactory();
		objectPool.setFactory(factory);
	}

	public void closeThriftPool() {
		try {
			objectPool.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getPoolSize() {
		return objectPool.getMaxActive();
	}

	@Override
	public org.apache.thrift.transport.TFramedTransport getConnention() {
		try {
			return (org.apache.thrift.transport.TFramedTransport) objectPool.borrowObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void returnConnection(TFramedTransport framedTransport) {
		try {
			objectPool.returnObject(framedTransport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
