package ananas.lib.god4server.git_ho;

import ananas.lib.god4server.git_ho.impl.RepositoryFactoryImpl;
import ananas.lib.god4server.git_ho.task.RepoTask;

public interface Repository extends RepositoryDirectory {

	abstract class Factory implements RepositoryFactory {

		public static RepositoryFactory getFactory() {
			return new RepositoryFactoryImpl();
		}
	}

	RepositoryDirectory getRepositoryDirectory();

	WorkingDirectory getWorkingDirectory();

	RepositoryRuntime getRuntime();

	RepoTask commit();

}
