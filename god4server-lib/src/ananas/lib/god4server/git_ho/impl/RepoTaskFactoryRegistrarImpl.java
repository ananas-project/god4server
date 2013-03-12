package ananas.lib.god4server.git_ho.impl;

import java.util.Hashtable;
import java.util.Map;

import ananas.lib.god4server.git_ho.task.RepoTaskFactory;
import ananas.lib.god4server.git_ho.task.RepoTaskFactoryRegistrar;

public class RepoTaskFactoryRegistrarImpl implements RepoTaskFactoryRegistrar {

	final Map<String, RepoTaskFactory> mTable;

	public RepoTaskFactoryRegistrarImpl() {
		this.mTable = new Hashtable<String, RepoTaskFactory>();
	}

	@Override
	public void registerTaskFactory(String taskName, RepoTaskFactory taskFactory) {
		this.mTable.put(taskName, taskFactory);
	}

	@Override
	public RepoTaskFactory getTaskFactory(String taskName) {
		return this.mTable.get(taskName);
	}

}
