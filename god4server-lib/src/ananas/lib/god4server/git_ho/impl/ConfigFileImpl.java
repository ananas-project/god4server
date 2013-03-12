package ananas.lib.god4server.git_ho.impl;

import ananas.lib.god4server.git_ho.ConfigFile;
import ananas.lib.god4server.git_ho.Repository;

public class ConfigFileImpl extends AbstractFileMonitor implements ConfigFile {

	public ConfigFileImpl(Repository repo) {
		super(repo, Const.config_file_name);
	}

}
