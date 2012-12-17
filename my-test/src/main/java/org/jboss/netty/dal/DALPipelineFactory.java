package org.jboss.netty.dal;

import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.serialization.ObjectDecoder;
import org.jboss.netty.handler.codec.serialization.ObjectEncoder;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;

public class DALPipelineFactory implements ChannelPipelineFactory {
	private static final ExecutionHandler exectionHandler = new ExecutionHandler(
			new OrderedMemoryAwareThreadPoolExecutor(5, 1000000, 10000000, 100,
					TimeUnit.MILLISECONDS));

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addLast("decoder", new ObjectDecoder(4096));
		pipeline.addLast("encoder", new ObjectEncoder());
		pipeline.addLast("exector", exectionHandler);
		// pipeline.addLast("handler", new DALNettyRequestHandler());
		return pipeline;
	}

}
