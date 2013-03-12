package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.RefsManager;
import ananas.lib.god4server.head_only_git.Repository;

public class RefsManagerImpl extends AbstractDirectoryMonitor implements
		RefsManager {

	public RefsManagerImpl(Repository repo) {
		super(repo, Const.refs_dir_name);
	}

}
