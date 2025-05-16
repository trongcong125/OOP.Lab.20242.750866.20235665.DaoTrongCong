package hust.soict.hedspi.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  // Thêm import này để có ActionListener, ActionEvent

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(
            ComponentOrientation.RIGHT_TO_LEFT
        );

        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);
        cp.add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(200, 200);
        setVisible(true);
    }

    /** Phương thức khởi tạo và thêm các nút vào panel */
    private void addButtons(JPanel panelButtons) {
        ButtonListener btnListener = new ButtonListener();

        // Nút 1–9
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            panelButtons.add(btnNumbers[i]);
            btnNumbers[i].addActionListener(btnListener);
        }

        // Nút xoá ký tự cuối ("DEL")
        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(btnListener);

        // Nút 0
        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(btnListener);

        // Nút xóa toàn bộ ("C")
        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(btnListener);
    }

    /** Listener chung cho tất cả các nút */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String cmd = evt.getActionCommand();
            String text = tfDisplay.getText();

            switch (cmd) {
                case "C":      // Reset toàn bộ
                    tfDisplay.setText("");
                    break;
                case "DEL":    // Xoá ký tự cuối
                    if (!text.isEmpty()) {
                        tfDisplay.setText(text.substring(0, text.length() - 1));
                    }
                    break;
                default:       // Các nút số 0–9
                    tfDisplay.setText(text + cmd);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGrid());
    }
}