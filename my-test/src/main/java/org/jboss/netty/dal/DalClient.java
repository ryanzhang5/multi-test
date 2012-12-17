package org.jboss.netty.dal;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;

public class DalClient {
	public static void main(String[] args) throws Exception {
		String host = "localhost";
		int port = 8080;

		ChannelFactory factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				return Channels.pipeline(new ObjectEncoder(),new ObjectDecoder(),new ClientHandler());
			}
		});

		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("keepAlive", true);

		ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(
				host, port));

		System.out.println("waiting here");
		channelFuture.awaitUninterruptibly();
		System.out.println("now go");
		if (!channelFuture.isSuccess()) {
			channelFuture.getCause().printStackTrace();
		}

		System.out.println("now go2");
		channelFuture.getChannel().getCloseFuture().awaitUninterruptibly();
		System.out.println("now go3");
		factory.releaseExternalResources();

	}
}