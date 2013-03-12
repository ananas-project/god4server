package ananas.lib.god4server.head_only_git;

import java.io.File;

public interface RepositoryFactory {

	Repository openRepository(File file);

	Repository newRepository(File file);

}
