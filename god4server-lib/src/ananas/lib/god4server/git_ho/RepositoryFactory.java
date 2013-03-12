package ananas.lib.god4server.git_ho;

import java.io.File;

public interface RepositoryFactory {

	Repository openRepository(File file);

	Repository newRepository(File file);

}
