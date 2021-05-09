package adeo.leroymerlin.cdp.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class ListUtils {
	
	private ListUtils() {
		throw new UnsupportedOperationException("This class cannot be instantiated");
	}

	public static <T> Consumer<T> withCounter(BiConsumer<Integer, T> consumer) {
		final AtomicInteger counter = new AtomicInteger(0);

		return item -> consumer.accept(counter.incrementAndGet(), item);
	}
}
