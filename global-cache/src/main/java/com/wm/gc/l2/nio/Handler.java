package com.wm.gc.l2.nio;

import java.nio.channels.SelectionKey;

public interface Handler {
	public void handle(SelectionKey selectionKey);

	public void register(SelectionKey selectionKey);
}
