package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.RepositoryRuntime;
import ananas.lib.god4server.git_ho.impl.task.CommitTask;
import ananas.lib.god4server.git_ho.impl.task.PullTask;
import ananas.lib.god4server.git_ho.impl.task.PushTask;
import ananas.lib.god4server.git_ho.task.RepoTaskFactoryRegistrar;
import ananas.lib.god4server.git_ho.task.TaskName;

public class RepositoryRuntimeImpl implements RepositoryRuntime {

	private final RepoTaskFactoryRegistrar mTaskFactoryReg;

	RepositoryRuntimeImpl() {
		this.mTaskFactoryReg = new RepoTaskFactoryRegistrarImpl();
	}

	@Override
	public RepoTaskFactoryRegistrar getTaskFactoryRegistrar() {
		return this.mTaskFactoryReg;
	}

	public void init() {
		final RepoTaskFactoryRegistrar reg = this.mTaskFactoryReg;
		reg.registerTaskFactory(TaskName.commit, CommitTask.getFactory());
		reg.registerTaskFactory(TaskName.push, PushTask.getFactory());
		reg.registerTaskFactory(TaskName.pull, PullTask.getFactory());
	}

}
