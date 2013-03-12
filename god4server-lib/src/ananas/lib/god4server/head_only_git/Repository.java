package ananas.lib.god4server.head_only_git;

import ananas.lib.god4server.head_only_git.impl.RepositoryFactoryImpl;

public interface Repository extends RepositoryDirectory {

	abstract class Factory implements RepositoryFactory {

		public static RepositoryFactory getFactory() {
			return new RepositoryFactoryImpl();
		}
	}

	RepositoryDirectory getRepositoryDirectory();

	WorkingDirectory getWorkingDirectory();

	void commit();

}
