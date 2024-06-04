import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListExample {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("JList Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        // Tạo JPanel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Tạo danh sách các giá trị
        String[] values = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};
        JList<String> list = new JList<>(values);
        
        // Thêm JList vào JPanel
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        
        // Thêm MouseListener để lắng nghe sự kiện nhấp chuột
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) { // Kiểm tra nhấp chuột 1 lần
                    int index = list.locationToIndex(evt.getPoint());
                    String selectedItem = list.getModel().getElementAt(index);
                    System.out.println("Selected Item: " + selectedItem);
                }
            }
        });
        
        // Thêm JPanel vào JFrame
        frame.add(panel);
        
        // Hiển thị JFrame
        frame.setVisible(true);
    }
}
