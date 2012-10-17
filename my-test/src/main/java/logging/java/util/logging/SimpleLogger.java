package logging.java.util.logging;

import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Demonstrate Java's logging facilities, in conjunction with a logging config
 * file.
 */
public final class SimpleLogger {
	// PRIVATE //

	// This logger will inherit the config of its parent, and add
	// any further config as an override. A simple style is to use
	// all config of the parent except, perhaps, for logging level.

	// This style uses a hard-coded literal and should likely be avoided:
	// private static final Logger fLogger = Logger.getLogger("myapp.business");

	// This style has no hard-coded literals, but forces the logger
	// to be non-static.
	// private final Logger
	// fLogger=Logger.getLogger(this.getClass().getPackage().getName());

	// This style uses a static field, but hard-codes a class literal.
	// This is probably acceptable.
	private static final Logger fLogger = Logger.getLogger(SimpleLogger.class
			.getPackage().getName());

	SimpleLogger() {
		try {

			InputStream is = this.getClass().getResourceAsStream(
					"logging.property");
			LogManager.getLogManager().readConfiguration(is);
			// System.setProperty("java.util.logging.config.file",
			// "logging.property");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String argv[]) {
		SimpleLogger thing = new SimpleLogger();
		thing.doSomething();
	}

	public void doSomething() {
		fLogger.finest("this is finest");
		fLogger.finer("this is finer");
		fLogger.fine("this is fine");
		fLogger.config("this is config");
		fLogger.info("this is info");
		fLogger.warning("this is a warning");
		fLogger.severe("this is severe");

		// fLogger.logp(Level.INFO, this.getClass().toString(), "doSomething",
		// "blah");

		System.out.println(System
				.getProperty("java.util.logging.ConsoleHandler.level"));

	}

}
