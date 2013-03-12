package ananas.lib.god4server;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;

import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.RepositoryFactory;
import ananas.lib.util.log4j.AbstractLoggerFactory;

public class Main {

	final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	public static void main(String[] args) {

		logger.info("app-begin");

		File baseDir;
		{
			URL url = Main.class.getProtectionDomain().getCodeSource()
					.getLocation();
			File path = new File(url.getPath());
			baseDir = path.getParentFile();
		}

		RepositoryFactory rf = Repository.Factory.getFactory();
		Repository repo1 = rf.openRepository(new File(baseDir,
				"aa/bb/cc/repo/xxx/yyy/zzz"));
		Repository repo2 = rf.newRepository(new File(baseDir, "aa/bb/cc/repo"));
		Repository repo3 = rf.openRepository(new File(baseDir,
				"aa/bb/cc/repo/xxx"));

		logger.debug("repo1 = " + repo1);
		logger.debug("repo2 = " + repo2);
		logger.debug("repo3 = " + repo3);

		
		repo3.commit() ;
		
		logger.info("app-end");
	}

}
