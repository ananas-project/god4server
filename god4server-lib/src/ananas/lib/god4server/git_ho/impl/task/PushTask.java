package ananas.lib.god4server.git_ho.impl.task;

import java.util.Properties;

import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.task.RepoTask;
import ananas.lib.god4server.git_ho.task.RepoTaskFactory;

public class PushTask extends AbstractTask {

	public PushTask(Repository repo, Properties prop) {
		super(repo, prop);
	}

	public static RepoTaskFactory getFactory() {
		return new RepoTaskFactory() {

			@Override
			public RepoTask newTask(Repository repo, Properties prop) {
				return new PushTask(repo, prop);
			}
		};
	}

	protected boolean onRun() {
		return true;
	}
}
