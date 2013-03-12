package ananas.lib.god4server.git_ho;

/**
 * this is the '.git' directory
 * */

public interface RepositoryDirectory extends DirectoryMonitor {

	ObjectsManager getObjectsManager();

	RefsManager getRefsManager();

	LogsManager getLogsManager();

	ConfigFile getConfig();

	IndexFile getIndex();

}
