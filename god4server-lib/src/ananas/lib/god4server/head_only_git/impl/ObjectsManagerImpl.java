package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.ObjectsManager;
import ananas.lib.god4server.head_only_git.Repository;

public class ObjectsManagerImpl extends AbstractDirectoryMonitor implements
		ObjectsManager {

	public ObjectsManagerImpl(Repository repo) {
		super(repo, Const.objects_dir_name);
	}

}
