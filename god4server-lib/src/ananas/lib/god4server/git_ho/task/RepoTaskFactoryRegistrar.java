package ananas.lib.god4server.git_ho.task;

public interface RepoTaskFactoryRegistrar {

	void registerTaskFactory(String taskName, RepoTaskFactory taskFactory);

	RepoTaskFactory getTaskFactory(String taskName);

}
