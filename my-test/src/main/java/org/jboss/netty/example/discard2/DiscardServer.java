package org.jboss.netty.example.discard2;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class DiscardServer {

	public static void main(String[] args) throws UnknownHostException {
		ChannelFactory factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ServerBootstrap bootstrap = new ServerBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

			public ChannelPipeline getPipeline() throws Exception {
				return Channels.pipeline(new SimpleChannelHandler() {
					public void messageReceived(ChannelHandlerContext ctx,
							MessageEvent e) {
						Channel channel = e.getChannel();
						ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
						ChannelBuffer outputBuffer = ChannelBuffers.buffer(2);
						while (buffer.readable()) {
							System.out.println((char) buffer.readByte());
						}

						outputBuffer.writeByte('a');
						outputBuffer.writeByte('c');
						channel.write(outputBuffer);

					}
				});
			}
		});

		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		InetSocketAddress inetSocketAddress = new InetSocketAddress(
				InetAddress.getByName("localhost"), 8083);
		bootstrap.bind(inetSocketAddress);
	}
}
