package ananas.lib.god4server.git_ho.impl;

import java.io.File;
import java.util.List;
import java.util.Vector;

import ananas.lib.god4server.git_ho.AbstractMonitor;
import ananas.lib.god4server.git_ho.ConfigFile;
import ananas.lib.god4server.git_ho.IndexFile;
import ananas.lib.god4server.git_ho.LogsManager;
import ananas.lib.god4server.git_ho.ObjectsManager;
import ananas.lib.god4server.git_ho.RefsManager;
import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.RepositoryDirectory;
import ananas.lib.god4server.git_ho.RepositoryRuntime;
import ananas.lib.god4server.git_ho.WorkingDirectory;
import ananas.lib.god4server.git_ho.task.RepoTask;
import ananas.lib.god4server.git_ho.task.TaskName;

public class RepositoryImpl implements Repository {

	private final File mDotDir;
	private final RepositoryRuntime mRuntime;

	private WorkingDirectory mWorkingDir;
	private LogsManager mLogsManager;
	private ObjectsManager mObjectsMagr;
	private RefsManager mRefsManager;
	private ConfigFile mConfigFile;
	private IndexFile mIndexFile;

	public RepositoryImpl(RepositoryRuntime rtime, File dotDir) {
		if (!dotDir.getName().equals(Const.repo_dir_name)) {
			throw new RuntimeException("bad dot dir name:" + dotDir);
		}
		this.mRuntime = rtime;
		this.mDotDir = dotDir;
	}

	public String toString() {
		return this.mDotDir.getAbsolutePath();
	}

	@Override
	public ObjectsManager getObjectsManager() {
		ObjectsManager rlt = this.mObjectsMagr;
		if (rlt == null) {
			Repository repo = this;
			rlt = new ObjectsManagerImpl(repo);
			this.mObjectsMagr = rlt;
		}
		return rlt;
	}

	@Override
	public RefsManager getRefsManager() {
		RefsManager rlt = this.mRefsManager;
		if (rlt == null) {
			Repository repo = this;
			rlt = new RefsManagerImpl(repo);
			this.mRefsManager = rlt;
		}
		return rlt;
	}

	@Override
	public LogsManager getLogsManager() {
		LogsManager rlt = this.mLogsManager;
		if (rlt == null) {
			Repository repo = this;
			rlt = new LogsManagerImpl(repo);
			this.mLogsManager = rlt;
		}
		return rlt;
	}

	@Override
	public File getDirectory() {
		return this.mDotDir;
	}

	@Override
	public boolean init() {
		boolean rlt = true;
		List<AbstractMonitor> v = this._listAllMonitor();
		for (AbstractMonitor mo : v) {
			if (!mo.init()) {
				rlt = false;
			}
		}
		return rlt;
	}

	@Override
	public boolean check() {
		return this.check(false);
	}

	@Override
	public boolean check(boolean doFullCheck) {
		List<AbstractMonitor> v = this._listAllMonitor();
		for (AbstractMonitor mo : v) {
			if (!mo.check(doFullCheck)) {
				return false;
			}
		}
		return true;
	}

	private List<AbstractMonitor> _listAllMonitor() {
		List<AbstractMonitor> v = new Vector<AbstractMonitor>();
		v.add(this.getWorkingDirectory());
		v.add(this.getLogsManager());
		v.add(this.getObjectsManager());
		v.add(this.getRefsManager());
		v.add(this.getConfig());
		v.add(this.getIndex());
		return v;
	}

	@Override
	public File toFileObject() {
		return this.mDotDir;
	}

	@Override
	public RepositoryDirectory getRepositoryDirectory() {
		return this;
	}

	@Override
	public WorkingDirectory getWorkingDirectory() {
		WorkingDirectory dir = this.mWorkingDir;
		if (dir == null) {
			Repository repo = this;
			dir = new WorkingDirImpl(repo);
			this.mWorkingDir = dir;
		}
		return dir;
	}

	@Override
	public IndexFile getIndex() {
		IndexFile file = this.mIndexFile;
		if (file == null) {
			Repository repo = this;
			file = new IndexFileImpl(repo);
			this.mIndexFile = file;
		}
		return file;
	}

	@Override
	public Repository getOwnerRepo() {
		return this;
	}

	@Override
	public ConfigFile getConfig() {
		ConfigFile file = this.mConfigFile;
		if (file == null) {
			Repository repo = this;
			file = new ConfigFileImpl(repo);
			this.mConfigFile = file;
		}
		return file;
	}

	@Override
	public RepoTask commit() {
		RepoTask task = this.getRuntime().getTaskFactoryRegistrar()
				.getTaskFactory(TaskName.commit).newTask(this, null);
		task.start();
		return task;
	}

	@Override
	public RepositoryRuntime getRuntime() {
		return this.mRuntime;
	}
}
