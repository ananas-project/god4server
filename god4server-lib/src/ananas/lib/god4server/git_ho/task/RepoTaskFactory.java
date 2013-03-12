package ananas.lib.god4server.git_ho.task;

import java.util.Properties;

import ananas.lib.god4server.git_ho.Repository;

public interface RepoTaskFactory {

	RepoTask newTask(Repository repo, Properties prop);

}
