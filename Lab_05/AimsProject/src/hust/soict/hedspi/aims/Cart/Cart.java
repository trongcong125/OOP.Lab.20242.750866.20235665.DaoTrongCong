package hust.soict.hedspi.aims.Cart;

import hust.soict.hedspi.aims.Media.Media;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;
    private ObservableList<Media> itemsOrdered = FXCollections.observableArrayList();

    public void addMedia(Media media) {
        if (itemsOrdered.size() < MAX_NUMBERS_ORDERED) {
            itemsOrdered.add(media);
            System.out.println("The media has been added: " + media.getTitle());
        } else {
            System.out.println("The cart is almost full. Cannot add more items.");
        }
    }

    public void removeMedia(Media media) {
        if (itemsOrdered.remove(media)) {
            System.out.println("The media has been removed: " + media.getTitle());
        } else {
            System.out.println("Media not found in cart.");
        }
    }

    public float totalCost() {
        float total = 0;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void displayCart() {
        System.out.println();
        int i = 1;
        for (Media media : itemsOrdered) {
            System.out.printf("%-2d %-20s %.2f\n", i++, media.getTitle(), media.getCost());
        }
        System.out.printf("\n%13s %15.2f\n", "Total Cost", totalCost());
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        int i = 1;
        for (Media media : itemsOrdered) {
            System.out.printf("%d. Media - [%s] - [%s] : [%.2f] $\n",
                    i++, media.getTitle(), media.getCategory(), media.getCost());
        }
        System.out.printf("Total cost: [%.2f]\n", totalCost());
        System.out.println("*************************************************");
    }

    public void searchById(int id) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found Media: " + media.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No Media found with ID: " + id);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Found Media: " + media.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Media found with title containing: " + title);
        }
    }

    public ObservableList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public boolean isEmpty() {
        return itemsOrdered.isEmpty();
    }

    public void clear() {
        itemsOrdered.clear();
    }

    public void exportInvoiceToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("************** INVOICE **************\n");
            writer.write("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Ordered Items:\n");
            int i = 1;
            for (Media media : itemsOrdered) {
                writer.write(String.format("%d. %s - %s : %.2f $\n",
                        i++, media.getTitle(), media.getCategory(), media.getCost()));
            }
            writer.write(String.format("Total cost: %.2f $\n", totalCost()));
            writer.write("*************************************\n");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing invoice to file: " + e.getMessage());
        }
    }
}