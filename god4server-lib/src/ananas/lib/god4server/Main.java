package ananas.lib.god4server;

import ananas.lib.util.logging.AbstractLoggerFactory;
import ananas.lib.util.logging.Logger;

public class Main {

	final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public static void main(String[] args) {

		logger.info("app-begin");

		logger.info("app-end");
	}

}
