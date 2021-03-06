package thinking.nio;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;




public class ServerSocketChannelTest5 {
	
	public SocketChannel createSocketChannel(String hostName,int port){
		return null;
	}
	
	public void toTry(){
		// Create a selector and register two socket channels
		Selector selector = null;
		try {
		    // Create the selector
		    selector = Selector.open();

		    // Create two non-blocking sockets. This method is implemented in
		    // Creating a Non-Blocking Socket.
		    SocketChannel sChannel1 = createSocketChannel("hostname.com", 80);
		    SocketChannel sChannel2 = createSocketChannel("hostname.com", 80);

		    // Register the channel with selector, listening for all events
		    sChannel1.register(selector, sChannel1.validOps());
		    sChannel2.register(selector, sChannel1.validOps());
		} catch (IOException e) {
		}

		// Wait for events
		while (true) {
		    try {
		        // Wait for an event
		        selector.select();
		    } catch (IOException e) {
		        // Handle error with selector
		        break;
		    }

		    // Get list of selection keys with pending events
		    Iterator it = selector.selectedKeys().iterator();

		    // Process each key at a time
		    while (it.hasNext()) {
		        // Get the selection key
		        SelectionKey selKey = (SelectionKey)it.next();

		        // Remove it from the list to indicate that it is being processed
		        it.remove();

		        try {
		            processSelectionKey(selKey);
		        } catch (IOException e) {
		            // Handle error with channel and unregister
		            selKey.cancel();
		        }
		    }
		}
	}
	public void processSelectionKey(SelectionKey selKey) throws IOException {
	    // Since the ready operations are cumulative,
	    // need to check readiness for each operation
	    if (selKey.isValid() && selKey.isConnectable()) {
	        // Get channel with connection request
	        SocketChannel sChannel = (SocketChannel)selKey.channel();

	        boolean success = sChannel.finishConnect();
	        if (!success) {
	            // An error occurred; handle it

	            // Unregister the channel with this selector
	            selKey.cancel();
	        }
	    }
	    if (selKey.isValid() && selKey.isReadable()) {
	        // Get channel with bytes to read
	        SocketChannel sChannel = (SocketChannel)selKey.channel();

	        // See Reading from a SocketChannel
	    }
	    if (selKey.isValid() && selKey.isWritable()) {
	        // Get channel that's ready for more bytes
	        SocketChannel sChannel = (SocketChannel)selKey.channel();

	        // See Writing to a SocketChannel
	    }
	}
}
