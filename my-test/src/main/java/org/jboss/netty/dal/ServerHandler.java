package org.jboss.netty.dal;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerHandler extends SimpleChannelHandler {

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		Request buf = (Request) e.getMessage();
		System.out.println("----------------server receive " + buf);
		Response response = new Response();
		Channel channel = e.getChannel();
		System.out.println("----------------server send " + response);
		channel.write(response);
	}
}
