package ananas.lib.god4server.git_ho.task;

public interface RepoProgress {

	long totalBytes();

	int totalFiles();

	int totalSteps();

	String[] step();

	long processedBytes();

	int processedFiles();

	int processedSteps();

}
