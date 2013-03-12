package ananas.lib.god4server.git_ho.task;

import ananas.lib.god4server.git_ho.Repository;

public interface RepoTask extends Runnable {

	Repository getRepo();

	RepoProgress getCurrentProgress();

	void start();

	void abort();

	void join();

	boolean isStarted();

	boolean isFinished();

	boolean isSuccess();

	String getResult();

}
