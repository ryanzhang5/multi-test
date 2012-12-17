package org.jboss.netty.example.time;

import java.util.Date;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class TimeClientHandler2 extends SimpleChannelHandler {
	private final ChannelBuffer buf = ChannelBuffers.dynamicBuffer();

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		ChannelBuffer m = (ChannelBuffer) e.getMessage();
		buf.writeBytes(m);

		if (buf.readableBytes() >= 4) {
			long currentTimeMillis = buf.readInt() * 1000L;
			System.out.println("----------------- "+new Date(currentTimeMillis));
			e.getChannel().close();
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}
}