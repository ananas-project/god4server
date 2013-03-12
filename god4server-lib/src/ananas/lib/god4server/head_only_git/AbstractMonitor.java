package ananas.lib.god4server.head_only_git;

import java.io.File;

public interface AbstractMonitor {

	boolean init();

	/**
	 * equals check(boolean doFullCheck = false );
	 * */
	boolean check();

	boolean check(boolean doFullCheck);

	File toFileObject();

	Repository getOwnerRepo();
}
