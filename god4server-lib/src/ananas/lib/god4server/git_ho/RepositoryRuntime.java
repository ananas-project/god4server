package ananas.lib.god4server.git_ho;

import ananas.lib.god4server.git_ho.task.RepoTaskFactoryRegistrar;

public interface RepositoryRuntime {

	RepoTaskFactoryRegistrar getTaskFactoryRegistrar();
}
