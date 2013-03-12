package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.IndexFile;
import ananas.lib.god4server.head_only_git.Repository;

public class IndexFileImpl extends AbstractFileMonitor implements IndexFile {

	public IndexFileImpl(Repository repo) {
		super(repo, Const.index_file_name);
	}

}
