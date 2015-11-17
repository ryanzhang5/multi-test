package org.ms.springquartz.quartz;

import java.util.Date;

public class HelloWorld {
	public HelloWorld() {
	}

	/**
	 * spring 检测要求不带参数
	 */
	public void execute() {
		System.out.println("how are you man!!" + new Date());
	}
}
