package ananas.app.god4server.client.gui;

import javax.swing.JFrame;

import ananas.lib.blueprint3.core.dom.BPDocument;

public class LoginDialogCtrl extends AbstractGuiCtrl {

	private JFrame mFrame;

	public LoginDialogCtrl() {
		BPDocument doc = this.getDocument(R.file.LoginDialog_xml);
		this.mFrame = (JFrame) doc.findTargetById(R.id.root);
	}

	public void show() {
		this.mFrame.setVisible(true);
	}

}
