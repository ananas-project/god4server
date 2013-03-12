package ananas.lib.god4server.git_ho.impl;

import java.io.File;
import java.io.IOException;

import ananas.lib.god4server.git_ho.FileMonitor;
import ananas.lib.god4server.git_ho.Repository;

public class AbstractFileMonitor implements FileMonitor {

	private final File mFile;
	private final Repository mRepo;

	public AbstractFileMonitor(Repository repo, String name) {
		this.mRepo = repo;
		this.mFile = new File(repo.getDirectory(), name);
	}

	@Override
	public boolean init() {
		if (this.mFile.exists()) {
			return false;
		} else {
			try {
				this.mFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
	}

	@Override
	public boolean check() {
		return this.check(false);
	}

	@Override
	public boolean check(boolean doFullCheck) {
		if (this.mFile.exists()) {
			if (this.mFile.isFile()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public File toFileObject() {
		return this.mFile;
	}

	@Override
	public Repository getOwnerRepo() {
		return this.mRepo;
	}

	@Override
	public File getFile() {
		return this.mFile;
	}

}
