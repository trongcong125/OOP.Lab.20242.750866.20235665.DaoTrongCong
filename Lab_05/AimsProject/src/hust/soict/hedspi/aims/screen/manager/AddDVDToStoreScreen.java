package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.Media.DigitalVideoDisc;
import hust.soict.hedspi.aims.Store.Store;

public class AddDVDToStoreScreen extends AddItemToStoreScreen {

    public AddDVDToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows now

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(20);

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField(20);

        JLabel directorLabel = new JLabel("Director:");
        JTextField directorField = new JTextField(20);

        JLabel lengthLabel = new JLabel("Length (minutes):");
        JTextField lengthField = new JTextField(20);

        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(e -> {
            String title = titleField.getText().trim();
            String category = categoryField.getText().trim();
            String director = directorField.getText().trim();
            int length = 0;
            float cost = 0.0f;

            try {
                cost = Float.parseFloat(costField.getText().trim());
                length = Integer.parseInt(lengthField.getText().trim());

                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);

                JOptionPane.showMessageDialog(this, "DVD added successfully!");

                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input for cost or length", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(titleLabel);
        center.add(titleField);
        center.add(categoryLabel);
        center.add(categoryField);
        center.add(costLabel);
        center.add(costField);
        center.add(directorLabel);
        center.add(directorField);
        center.add(lengthLabel);
        center.add(lengthField);
        center.add(new JLabel()); // empty cell
        center.add(addButton);

        return center;
    }
}