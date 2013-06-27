package com.mkfree.thriftconnectionpool.factory;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.thrift.transport.TSocket;

/**
 * @author oyhk
 * 
 *         2013-6-19 下午12:32:29
 */
public class ThriftTSocketFactory implements PoolableObjectFactory<TSocket> {

	private String host;// ip or domain
	private int port;
	private int timeout;// seconds

	public ThriftTSocketFactory(String host, int port, int timeout) {
		super();
		this.host = host;
		this.port = port;
		this.timeout = timeout;
	}

	@Override
	public TSocket makeObject() throws Exception {
		TSocket socket = new TSocket(host, port, timeout);
		socket.open();
		return socket;
	}

	@Override
	public void destroyObject(TSocket obj) throws Exception {
		obj.close();
	}

	@Override
	public boolean validateObject(TSocket obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateObject(TSocket obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void passivateObject(TSocket obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
