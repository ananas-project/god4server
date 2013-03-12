package ananas.lib.god4server.head_only_git.impl;

import java.io.File;

import ananas.lib.god4server.head_only_git.Repository;
import ananas.lib.god4server.head_only_git.RepositoryFactory;

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
			Repository repo = new RepositoryImpl(dotDir);
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
		Repository repo = new RepositoryImpl(dotDir);
		if (!repo.init()) {
			return null;
		}
		return repo;
	}

}
