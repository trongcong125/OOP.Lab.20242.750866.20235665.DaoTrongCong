package hust.soict.hedspi.aims.Store;

import hust.soict.hedspi.aims.Media.Media;
import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void addMedia(Media media) {
        itemsInStore.add(media);
        System.out.println("The item has been added to the store: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (itemsInStore.remove(media)) {
            System.out.println("The item has been removed from the store: " + media.getTitle());
        } else {
            System.out.println("Item not found in the store: " + media.getTitle());
        }
    }

    public void displayStore() {
        System.out.println("Items in Store:");
        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        } else {
            int index = 1;
            for (Media media : itemsInStore) {
                System.out.printf("%d. %s\n", index++, media.getTitle());
            }
        }
        System.out.println("Total items in store: " + itemsInStore.size());
    }
}