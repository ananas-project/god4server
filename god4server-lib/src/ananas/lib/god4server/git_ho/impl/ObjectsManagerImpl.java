package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.ObjectsManager;
import ananas.lib.god4server.git_ho.Repository;

public class ObjectsManagerImpl extends AbstractDirectoryMonitor implements
		ObjectsManager {

	public ObjectsManagerImpl(Repository repo) {
		super(repo, Const.objects_dir_name);
	}

}
