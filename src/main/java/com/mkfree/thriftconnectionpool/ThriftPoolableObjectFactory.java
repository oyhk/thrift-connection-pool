package com.mkfree.thriftconnectionpool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;

public class ThriftPoolableObjectFactory implements PoolableObjectFactory<TFramedTransport> {

	@Override
	public void activateObject(TFramedTransport socket) throws Exception {

	}

	@Override
	public void destroyObject(TFramedTransport socket) throws Exception {

	}

	@Override
	public TFramedTransport makeObject() throws Exception {
		TFramedTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9901));
		transport.open();
		return transport;
	}

	@Override
	public void passivateObject(TFramedTransport socket) throws Exception {

	}

	@Override
	public boolean validateObject(TFramedTransport socket) {
		return false;
	}

}
