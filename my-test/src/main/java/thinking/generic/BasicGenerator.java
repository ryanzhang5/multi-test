package thinking.generic;

import java.util.ArrayList;

public class BasicGenerator<T> implements Generator<T> {
	private Class<T> type;

	public BasicGenerator(Class<T> type) {
		this.type = type;
	}

	public T next() {
		try {
			// Assumes type is a public class:
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// Produce a Default generator given a type token:
	public static <K> Generator<K> create(Class<K> type) {
		return new BasicGenerator<K>(type);
	}
}

class RyanGenerator<T> implements Generator<T> {
	public Class<T> type;

	public RyanGenerator(Class<T> type) {
		this.type = type;
	}

	public T next() {

		try {
			return (T) type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <K> Generator<K> getGenerator(Class<K> type) {
		return new RyanGenerator<K>(type);
	}

}
