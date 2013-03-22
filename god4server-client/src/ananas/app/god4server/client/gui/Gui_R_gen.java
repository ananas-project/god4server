package ananas.app.god4server.client.gui;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import ananas.lib.blueprint3.core.tools.R_file_generator;
import ananas.lib.util.CommandLinePropertiesUtil;

public class Gui_R_gen implements Runnable {

	public static void main(String[] args) {
		(new Gui_R_gen()).run();
	}

	@Override
	public void run() {
		Properties prop = new Properties(); // CommandLinePropertiesUtil.argumentsToProperties(args);

		prop.setProperty("-base-dir", this.getBaseDir());
		prop.setProperty("-res-dir", "src");
		prop.setProperty("-gen-dir", "gen");
		prop.setProperty("-R", "");
		prop.setProperty("-accept-file", ".xml .png");
		prop.setProperty("-accept-xml-file", ".xml");
		prop.setProperty("-accept-attr", "id command");

		String[] args = CommandLinePropertiesUtil.propertiesToArguments(prop);
		R_file_generator.main(args);

	}

	private String getBaseDir() {
		URL url = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();
		File dir = new File(url.getPath());
		return dir.getParentFile().getAbsolutePath();
	}
}
