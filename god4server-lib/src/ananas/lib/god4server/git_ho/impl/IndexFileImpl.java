package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.IndexFile;
import ananas.lib.god4server.git_ho.Repository;

public class IndexFileImpl extends AbstractFileMonitor implements IndexFile {

	public IndexFileImpl(Repository repo) {
		super(repo, Const.index_file_name);
	}

}
