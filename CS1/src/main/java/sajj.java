import javax.swing.*;
import java.awt.event.*;

public class sajj {
	public static void main(String[] args) {
		JFrame frame = new JFrame("JList MouseEvent Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] data = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
		JList<String> list = new JList<>(data);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("helloworld");
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
					int index = list.locationToIndex(e.getPoint());
					if (index != -1) {
						JOptionPane.showMessageDialog(frame, "Double-clicked on item: " + data[index]);
					}
				}
			}
		});

		frame.add(new JScrollPane(list));
		frame.setSize(300, 200);
		frame.setVisible(true);

		// Tạo và kích hoạt một sự kiện MouseEvent tự động
		MouseEvent event = new MouseEvent(list, MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 10, 10, 1,
				false);
//		list.dispatchEvent(event);
	}
}
