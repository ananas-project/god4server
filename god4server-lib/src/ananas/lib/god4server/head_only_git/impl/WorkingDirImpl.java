package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.Repository;
import ananas.lib.god4server.head_only_git.WorkingDirectory;

public class WorkingDirImpl extends AbstractDirectoryMonitor implements
		WorkingDirectory {

	public WorkingDirImpl(Repository repo) {
		super(repo, repo.getDirectory().getParentFile());
	}

}
