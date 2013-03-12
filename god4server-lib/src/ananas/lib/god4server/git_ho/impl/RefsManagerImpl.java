package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.RefsManager;
import ananas.lib.god4server.git_ho.Repository;

public class RefsManagerImpl extends AbstractDirectoryMonitor implements
		RefsManager {

	public RefsManagerImpl(Repository repo) {
		super(repo, Const.refs_dir_name);
	}

}
