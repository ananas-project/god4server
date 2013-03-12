package ananas.lib.god4server.git_ho.impl.task;

import java.util.Properties;

import org.apache.log4j.Logger;

import ananas.lib.god4server.git_ho.Repository;
import ananas.lib.god4server.git_ho.task.RepoProgress;
import ananas.lib.god4server.git_ho.task.RepoTask;
import ananas.lib.util.log4j.AbstractLoggerFactory;

public class AbstractTask implements RepoTask {

	final static Logger logger = (new AbstractLoggerFactory() {
	}).getLogger();

	private final Repository mRepo;
	private final Properties mProp;
	private Thread mThread;
	private boolean mIsStarted;
	private boolean mIsFinished;
	private boolean mIsSuccess = false;
	private String mResult;
	private boolean mIsDoAbort;
	private Thread m1stThread;

	public AbstractTask(Repository repo, Properties prop) {
		this.mRepo = repo;
		this.mProp = prop;
		this.mThread = null;
	}

	@Override
	public final void run() {
		logger.info(this + ".task_begin");
		this.mIsStarted = true;
		try {
			this.mIsSuccess = this._doRun();
		} catch (Exception e) {
			e.printStackTrace();
			this.mResult = e.getMessage();
			this.mIsSuccess = false;
		}
		this.mIsFinished = true;
		logger.info(this + ".task_end: success=" + this.mIsSuccess + " result="
				+ this.mResult);
	}

	private synchronized boolean _doRun() {
		if (this.m1stThread == null) {
			Thread thd = Thread.currentThread();
			this.m1stThread = thd;
		} else {
			return false;
		}
		return this.onRun();
	}

	protected boolean onRun() {
		return true;
	}

	public boolean isDoAbort() {
		return this.mIsDoAbort;
	}

	public Properties getProperties() {
		return this.mProp;
	}

	@Override
	public Repository getRepo() {
		return this.mRepo;
	}

	@Override
	public RepoProgress getCurrentProgress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		if (this.mThread != null) {
			return;
		}
		Thread thd = new Thread(this);
		thd.start();
		this.mThread = thd;
	}

	@Override
	public void abort() {
		this.mIsDoAbort = true;
	}

	@Override
	public void join() {
		try {
			this.mThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isStarted() {
		return this.mIsStarted;
	}

	@Override
	public boolean isFinished() {
		return this.mIsFinished;
	}

	@Override
	public boolean isSuccess() {
		return this.mIsSuccess;
	}

	@Override
	public String getResult() {
		return this.mResult;
	}

}
