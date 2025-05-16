package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.Cart.Cart;
import hust.soict.hedspi.aims.Store.Store;
import hust.soict.hedspi.aims.Media.DigitalVideoDisc;
import java.util.Scanner;

public class Aims {

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3\n> ");
    }

    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4\n> ");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2\n> ");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5\n> ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store();
        Cart cart = new Cart();

        store.addMedia(new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f));
        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-Fi", "George Lucas", 87, 24.95f));
        store.addMedia(new DigitalVideoDisc("Aladin", "Animation", 18.99f));

        boolean running = true;
        while (running) {
            showMenu();
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1; 
            }

            switch (choice) {
                case 1:
                    viewStore(scanner, store, cart);
                    break;
                case 2:
                    updateStore(scanner, store);
                    break;
                case 3:
                    viewCart(scanner, cart);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting AIMS. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void viewStore(Scanner scanner, Store store, Cart cart) {
        System.out.println("\n--- STORE CONTENT ---");
        store.displayStore();
        System.out.println("---------------------\n");
    }

    public static void updateStore(Scanner scanner, Store store) {
        System.out.println("\n--- UPDATE STORE ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter director: ");
        String director = scanner.nextLine();
        System.out.print("Enter length: ");
        int length = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter cost: ");
        float cost = Float.parseFloat(scanner.nextLine());

        DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
        store.addMedia(dvd);

        System.out.println("The DVD has been added to the store.\n");
    }

    public static void viewCart(Scanner scanner, Cart cart) {
        System.out.println("\n--- CURRENT CART ---");
        cart.displayCart();
        System.out.println("---------------------\n");
    }
}
