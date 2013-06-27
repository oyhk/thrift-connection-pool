package com.mkfree.thriftconnectionpool.provider;

import org.apache.thrift.transport.TSocket;

public interface ConnectionProvider {

	public TSocket getConnention();

	public void returnConnection(TSocket socket);

}
