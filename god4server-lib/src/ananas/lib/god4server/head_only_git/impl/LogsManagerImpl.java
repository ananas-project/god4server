package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.LogsManager;
import ananas.lib.god4server.head_only_git.Repository;

public class LogsManagerImpl extends AbstractDirectoryMonitor implements
		LogsManager {

	public LogsManagerImpl(Repository repo) {
		super(repo, Const.logs_dir_name);
	}

}
