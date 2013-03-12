package ananas.lib.god4server.head_only_git;

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
