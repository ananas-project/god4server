package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.LogsManager;
import ananas.lib.god4server.git_ho.Repository;

public class LogsManagerImpl extends AbstractDirectoryMonitor implements
		LogsManager {

	public LogsManagerImpl(Repository repo) {
		super(repo, Const.logs_dir_name);
	}

}
