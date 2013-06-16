package com.mkfree.thriftconnectionpool.server;

import org.apache.thrift.TException;

import com.mkfree.thriftclientpool.server.TestService.Iface;

public class TestServiceImpl implements Iface {

	@Override
	public String getName() throws TException {
		return "oyhk";
	}

}
