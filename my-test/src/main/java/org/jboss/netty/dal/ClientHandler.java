package org.jboss.netty.dal;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ClientHandler extends SimpleChannelHandler {
	public void channelConnected(ChannelHandlerContext ctx,
			final ChannelStateEvent e) {
		Runnable sender = new Runnable() {

			public void run() {
			//	while (true) {
					Request request = new Request();
					System.out.println("client send " + request);
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.getChannel().write(request);
			//	}
			}
		};
		Thread thread = new Thread(sender);
		thread.start();
	}

	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		Response buf = (Response) e.getMessage();
		System.out.println("client receive " + buf);
		e.getChannel().close();

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		e.getCause().printStackTrace();
	}
}
