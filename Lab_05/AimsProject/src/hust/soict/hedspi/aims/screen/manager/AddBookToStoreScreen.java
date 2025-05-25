package hust.soict.hedspi.aims.screen.manager;

import javax.swing.*;
import java.awt.*;
import hust.soict.hedspi.aims.Media.Book;
import hust.soict.hedspi.aims.Store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book to Store");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(20);

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(20);

        JLabel costLabel = new JLabel("Cost:");
        JTextField costField = new JTextField(20);

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorField = new JTextField(20);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            try {
                String title = titleField.getText().trim();
                String category = categoryField.getText().trim();
                float cost = Float.parseFloat(costField.getText().trim());
                String author = authorField.getText().trim();

                int id = store.getItemsInStore().size() + 1;
                Book book = new Book(id, title, category, cost);
                book.addAuthor(author);
                store.addMedia(book);

                JOptionPane.showMessageDialog(this, "Book added successfully!");
                new StoreManagerScreen(store);
                this.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost input", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(titleLabel); center.add(titleField);
        center.add(categoryLabel); center.add(categoryField);
        center.add(costLabel); center.add(costField);
        center.add(authorLabel); center.add(authorField);
        center.add(new JLabel()); center.add(addButton); // empty cell for layout

        return center;
    }
}