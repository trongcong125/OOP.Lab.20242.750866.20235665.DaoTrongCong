package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;

import hust.soict.hedspi.aims.Media.CompactDisc;
import hust.soict.hedspi.aims.Store.Store;

public class AddCDToStoreScreen extends AddItemToStoreScreen {

    public AddCDToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows: 6 fields + 1 button

        // Create labels and input fields
        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(20);

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField(20);

        JLabel artistLabel = new JLabel("Artist:");
        JTextField artistField = new JTextField(20);

        JLabel directorLabel = new JLabel("Director:");
        JTextField directorField = new JTextField(20);

        JLabel lengthLabel = new JLabel("Length (minutes):");
        JTextField lengthField = new JTextField(20);

        JButton addButton = new JButton("Add CD");
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String category = categoryField.getText().trim();
                String artist = artistField.getText().trim();
                String director = directorField.getText().trim();
                float cost = Float.parseFloat(costField.getText().trim());
                int length = Integer.parseInt(lengthField.getText().trim());

                int id = store.getItemsInStore().size() + 1;

                CompactDisc cd = new CompactDisc(id, title, category, cost, director, length, artist);
                store.addMedia(cd);

                JOptionPane.showMessageDialog(this, "CD added successfully!");
                new StoreManagerScreen(store);  // Go back to store screen
                this.dispose();  // Close current window

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost or length", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(titleLabel); center.add(titleField);
        center.add(categoryLabel); center.add(categoryField);
        center.add(costLabel); center.add(costField);
        center.add(artistLabel); center.add(artistField);
        center.add(directorLabel); center.add(directorField);
        center.add(lengthLabel); center.add(lengthField);
        center.add(new JLabel()); center.add(addButton);  // empty label for spacing

        return center;
    }
}