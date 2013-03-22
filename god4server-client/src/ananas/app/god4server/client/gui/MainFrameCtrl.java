package ananas.app.god4server.client.gui;

import javax.swing.JFrame;

import ananas.lib.blueprint3.core.dom.BPDocument;

public class MainFrameCtrl extends AbstractGuiCtrl {

	private JFrame mFrame;
	private final LoginDialogCtrl mParent;

	public MainFrameCtrl(LoginDialogCtrl parent) {
		this.mParent = parent;
		BPDocument doc = this.getDocument(R.file.MainFrame_xml);
		this.mFrame = (JFrame) doc.findTargetById(R.id.root);
	}

	public void show() {
		this.mFrame.setVisible(true);
	}
}
