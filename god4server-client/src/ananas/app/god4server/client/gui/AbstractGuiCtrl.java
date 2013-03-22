package ananas.app.god4server.client.gui;

import ananas.lib.blueprint3.core.Blueprint;
import ananas.lib.blueprint3.core.dom.BPDocument;

public class AbstractGuiCtrl {

	protected BPDocument getDocument(String uri) {
		try {

			return Blueprint.loadDocument(uri);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
