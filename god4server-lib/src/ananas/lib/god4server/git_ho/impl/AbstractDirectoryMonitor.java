package ananas.lib.god4server.git_ho.impl;

import java.io.File;

import ananas.lib.god4server.git_ho.DirectoryMonitor;
import ananas.lib.god4server.git_ho.Repository;

public class AbstractDirectoryMonitor implements DirectoryMonitor {

	private final File mDir;
	private final Repository mRepo;

	public AbstractDirectoryMonitor(Repository repo, String name) {
		this.mRepo = repo;
		this.mDir = new File(repo.getDirectory(), name);
	}

	public AbstractDirectoryMonitor(Repository repo, File dir) {
		this.mRepo = repo;
		this.mDir = dir;
	}

	@Override
	public boolean init() {
		if (this.mDir.exists()) {
			return false;
		} else {
			return this.mDir.mkdirs();
		}
	}

	@Override
	public boolean check() {
		return this.check(false);
	}

	@Override
	public boolean check(boolean doFullCheck) {
		if (this.mDir.exists()) {
			if (this.mDir.isDirectory()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public File toFileObject() {
		return this.mDir;
	}

	@Override
	public Repository getOwnerRepo() {
		return this.mRepo;
	}

	@Override
	public File getDirectory() {
		return this.mDir;
	}

}
