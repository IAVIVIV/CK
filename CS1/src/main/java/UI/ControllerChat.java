package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DOMAIN.Repository;
import INF.Client;
import INF.RepositoryImp;

public class ControllerChat implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();
		if (nameBtn == "Send") {
			if (!ViewChat.txtT.getText().equals("")) {
				ViewChat.txtrSdgd.setText(
						ViewChat.txtrSdgd.getText() + ViewLogIn.username + " : " + ViewChat.txtT.getText() + "\n");
				Client client = new Client();
				client.cloneClient("192.168.1.3", ViewChat.txtNhpIp.getText(), ViewLogIn.username, ViewChat.selectUser,
						ViewChat.txtT.getText());
				ViewChat.txtT.setText("");
			}
		} else if (nameBtn == "accept_e") {
			String email = ViewChat.txtNhpEmail.getText();
			Repository r = new RepositoryImp();
			String newValue = r.findUser(email);
			if (!newValue.isEmpty()) {
				ViewChat.listModel.addElement(newValue);
			}
		}
	}
}
