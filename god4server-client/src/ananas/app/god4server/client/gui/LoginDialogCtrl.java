package ananas.app.god4server.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import ananas.app.god4server.client.core.DefaultG4sClientFactory;
import ananas.app.god4server.client.core.G4sClient;
import ananas.app.god4server.client.core.G4sClientFactory;
import ananas.lib.blueprint3.core.dom.BPDocument;

public class LoginDialogCtrl extends AbstractGuiCtrl {

	private JFrame mFrame;
	private JComboBox mDropList;
	private JFileChooser mFileChooser;

	// <File>

	public LoginDialogCtrl() {
		BPDocument doc = this.getDocument(R.file.LoginDialog_xml);
		this.mFrame = (JFrame) doc.findTargetById(R.id.root);
		this.mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.mDropList = (JComboBox) doc.findTargetById(R.id.combo_path_list);
		this.mDropList.setEditable(true);
		// DefaultComboBoxModel model = new DefaultComboBoxModel();
		// this.mDropList.setModel(model);

		((JButton) doc.findTargetById(R.id.button_browse))
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						LoginDialogCtrl.this.onClickBrowse();
					}
				});

		((JButton) doc.findTargetById(R.id.button_cancel))
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						LoginDialogCtrl.this.onClickCancel();
					}
				});
		((JButton) doc.findTargetById(R.id.button_ok))
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						LoginDialogCtrl.this.onClickOK();
					}
				});

	}

	protected void onClickOK() {

		String path = this.mDropList.getSelectedItem().toString();
		File file = new File(path);

		G4sClientFactory cf = new DefaultG4sClientFactory();
		G4sClient client = null;

		if (!file.exists()) {
			// new
			String message = "The file is not existed. Create a new one?\n"
					+ file;
			int rlt = JOptionPane.showConfirmDialog(this.mFrame, message);
			if (rlt != JOptionPane.OK_OPTION) {
				return;
			}
			client = cf.newClient(file);
		} else {
			// open
			client = cf.openClient(file);
		}

		if (client == null) {
			String message = "failed:\n" + file;
			JOptionPane.showMessageDialog(this.mFrame, message);
			return;
		} else {
			// open mainFrame
			this.mFrame.setVisible(false);

			MainFrameCtrl frame = new MainFrameCtrl(this);
			frame.show();

		}

	}

	protected void onClickCancel() {

	}

	protected void onClickBrowse() {

		JFileChooser chooser = this.mFileChooser;
		if (chooser == null) {
			chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"JPG & GIF Images", "jpg", "gif");
			chooser.setFileFilter(filter);
			this.mFileChooser = chooser;
		}

		int returnVal = chooser.showOpenDialog(this.mFrame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			this.mDropList.setSelectedItem(file.getAbsolutePath());
		}

	}

	public void show() {
		this.mFrame.setVisible(true);
	}

}
