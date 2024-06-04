package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import APP.UseCase;

public class ControllerRegister implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();
		if (nameBtn == "Đăng ký") {
			if (ViewRegister.txtMtKhu.getText().equals(ViewRegister.txtNhpLiMt.getText())) {
				String username = ViewRegister.txtTiKhon.getText();
				String email = ViewRegister.txtEmail.getText();
				String password = ViewRegister.txtMtKhu.getText();
				try {
					UseCase.register(username, email, password);
					ViewRegister.frame.setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
