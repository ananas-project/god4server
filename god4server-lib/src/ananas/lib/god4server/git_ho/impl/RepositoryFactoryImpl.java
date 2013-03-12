package ananas.lib.god4server.git_ho.impl;

import java.io.File;

import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.RepositoryFactory;
import ananas.lib.god4server.git_ho.RepositoryRuntime;

public class RepositoryFactoryImpl implements RepositoryFactory {

	@Override
	public Repository openRepository(File file) {
		File dotDir = null;
		for (; file != null; file = file.getParentFile()) {
			File tmpDot = new File(file, Const.repo_dir_name);
			if (tmpDot.exists()) {
				if (tmpDot.isDirectory()) {
					dotDir = tmpDot;
					break;
				}
			}
		}
		if (dotDir == null) {
			return null;
		} else {
			Repository repo = new RepositoryImpl(this._getRuntime(), dotDir);
			boolean rlt = repo.check();
			if (!rlt) {
				return null;
			}
			return repo;
		}
	}

	@Override
	public Repository newRepository(File file) {
		if (file.exists()) {
			// throw new RuntimeException("the dir is exists:" + file);
			return null;
		}
		File dotDir = new File(file, Const.repo_dir_name);
		Repository repo = new RepositoryImpl(this._getRuntime(), dotDir);
		if (!repo.init()) {
			return null;
		}
		return repo;
	}

	private static RepositoryRuntime s_runtime;

	private RepositoryRuntime _getRuntime() {
		RepositoryRuntime rtime = s_runtime;
		if (rtime == null) {
			RepositoryRuntimeImpl rtime2 = new RepositoryRuntimeImpl();
			rtime2.init();
			s_runtime = rtime = rtime2;
		}
		return rtime;
	}

}
