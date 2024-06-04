package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DOMAIN.Service;

public class ControllerLogIn implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		String nameBtn = e.getActionCommand();
		if (nameBtn == "Đăng nhập") {
			String taiKhoan = ViewLogIn.txtTiKhon.getText();
			String matKhau = ViewLogIn.txtMtKhu.getText();
			Service s = new Service();
			try {
				if (s.logIn(taiKhoan, matKhau)) {
					ViewLogIn.username = taiKhoan;
					ViewChat.main(null);
					ViewLogIn.frame.setVisible(false);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (nameBtn == "Tạo tài khoản mới") {
			ViewRegister.main(null);
		}
	}
}
