import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DynamicButtonPanel {

	private static int buttonCount = 0;

	public static void main(String[] args) {
		// Tạo JFrame
		JFrame frame = new JFrame("Dynamic Button Panel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);

		// Tạo JPanel chính với BorderLayout
		JPanel mainPanel = new JPanel(new BorderLayout());

		// Tạo JPanel để chứa các nút, sử dụng GridLayout
		JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 5, 5));

		// Tạo JScrollPane để có thể cuộn nếu có nhiều nút
		JScrollPane scrollPane = new JScrollPane(buttonPanel);
		mainPanel.add(scrollPane, BorderLayout.CENTER);

		// Tạo nút "Add Button" để thêm nút mới
		JButton addButton = new JButton("Add Button");
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonCount++;
				String value = "Value " + buttonCount;
				JButton button = new JButton(value);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Khi nhấn nút, in giá trị ra console
						System.out.println(value);
					}
				});
				buttonPanel.add(button);

				// Cập nhật giao diện
				buttonPanel.revalidate();
				buttonPanel.repaint();
			}
		});

		// Thêm nút "Add Button" vào JPanel chính
		mainPanel.add(addButton, BorderLayout.SOUTH);

		// Thêm JPanel chính vào JFrame
		frame.getContentPane().add(mainPanel);

		// Hiển thị JFrame
		frame.setVisible(true);
	}
}
