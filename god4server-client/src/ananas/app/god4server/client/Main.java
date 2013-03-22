package ananas.app.god4server.client;

import java.util.Properties;

import ananas.app.god4server.client.gui.LoginDialogCtrl;
import ananas.lib.blueprint3.core.Blueprint;
import ananas.lib.blueprint3.core.lang.BPEnvironment;

public class Main implements Runnable {

	private final Properties mArguments;

	public Main(Properties prop) {
		this.mArguments = prop;
	}

	public static void main(String[] arg) {
		Properties prop = ananas.lib.util.CommandLinePropertiesUtil
				.argumentsToProperties(arg);
		Thread thd = new Thread(new Main(prop));
		javax.swing.SwingUtilities.invokeLater(thd);
	}

	@Override
	public void run() {

		BPEnvironment envi = Blueprint.getInstance().defaultEnvironment();
		Class<?>[] list = this.getInfoClassList();
		for (Class<?> cls : list) {
			envi.loadNamespace(cls, true);
		}

		this.mArguments.get("");

		LoginDialogCtrl dlg = new LoginDialogCtrl();

		dlg.show();
	}

	private Class<?>[] getInfoClassList() {
		Class<?> list[] = {
				ananas.lib.blueprint3.loader.eom.EomReflectInfo.class,
				ananas.lib.blueprint3.awt.AwtNamespaceInfo.class,
				ananas.lib.blueprint3.awt.swing.SwingNamespaceInfo.class };
		return list;
	}

}
