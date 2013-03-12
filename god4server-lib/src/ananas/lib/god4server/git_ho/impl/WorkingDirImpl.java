package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.WorkingDirectory;

public class WorkingDirImpl extends AbstractDirectoryMonitor implements
		WorkingDirectory {

	public WorkingDirImpl(Repository repo) {
		super(repo, repo.getDirectory().getParentFile());
	}

}
