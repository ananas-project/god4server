package ananas.lib.god4server.head_only_git.impl;

import ananas.lib.god4server.head_only_git.ConfigFile;
import ananas.lib.god4server.head_only_git.Repository;

public class ConfigFileImpl extends AbstractFileMonitor implements ConfigFile {

	public ConfigFileImpl(Repository repo) {
		super(repo, Const.config_file_name);
	}

}
