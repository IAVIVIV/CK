import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ValueListPanel {

    public static void main(String[] args) {
        // Tạo một JFrame để chứa JPanel
        JFrame frame = new JFrame("Value List Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo một JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Tạo một danh sách các giá trị
        String[] values = {"Value 1", "Value 2", "Value 3", "Value 4", "Value 5"};

        // Tạo và thêm các JButton vào JPanel cho mỗi giá trị
        for (String value : values) {
            JButton button = new JButton(value);
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa nút
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Khi nhấn nút, in giá trị ra console
                    System.out.println(value);
                }
            });
            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Thêm khoảng cách giữa các nút
        }

        // Thêm JPanel vào JFrame
        frame.getContentPane().add(panel);
        
        // Hiển thị JFrame
        frame.setVisible(true);
    }
}
