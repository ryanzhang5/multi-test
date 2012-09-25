package com.wm.gc.jmxadmin;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main2 {
	public static void main(String[] args) throws Exception{
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName("com.wm.gc.jmxadmin:type=Hello");
		
		mbs.registerMBean("name", name);
	}
}
