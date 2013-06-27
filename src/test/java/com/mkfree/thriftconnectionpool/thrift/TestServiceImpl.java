package com.mkfree.thriftconnectionpool.thrift;

import org.apache.thrift.TException;

import com.mkfree.thriftconnectionpool.thrift.TestService.Iface;

public class TestServiceImpl implements Iface {

	@Override
	public String getName() throws TException {
		return "oyhk";
	}

}
