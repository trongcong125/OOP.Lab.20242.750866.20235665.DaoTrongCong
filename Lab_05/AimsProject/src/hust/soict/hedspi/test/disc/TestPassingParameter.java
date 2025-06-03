package hust.soict.hedspi.test.screen.customer.store;

import hust.soict.hedspi.aims.Cart.Cart;
import hust.soict.hedspi.aims.Media.Book;
import hust.soict.hedspi.aims.Media.CompactDisc;
import hust.soict.hedspi.aims.Media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.customer.controller.ViewStoreController;
import hust.soict.hedspi.aims.Store.Store;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestViewStoreScreen extends Application {
    private static Store store;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final String START_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(START_FXML_FILE_PATH));

        Cart cart = new Cart();
        ViewStoreController viewStoreController = new ViewStoreController(store, cart);
        fxmlLoader.setController(viewStoreController);

        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
    	store = new Store();

    	store.addMedia(new DigitalVideoDisc("The Matrix", "Action", "Wachowski Brothers", 136, 25.0f));
    	store.addMedia(new DigitalVideoDisc("Inception", "Sci-Fi", " Nolan 123", 148, 28.0f));
    	store.addMedia(new DigitalVideoDisc("Avengers: Endgame", "Action", "Anthony 345", 181, 30.0f));

    	store.addMedia(new CompactDisc(1, "Greatest Hits", "Pop", 15.5f, "Jane Smith", 45, "Taylor Swift"));
    	store.addMedia(new CompactDisc(2, "Rock Music", "Rock", 18.0f, "John ABC", 50, "Queen"));
    	store.addMedia(new CompactDisc(3, "Jazz Music", "Jazz", 20.0f, "Mike XYZ ", 40, "Miles Davis"));

    	store.addMedia(new Book(4, "Clean Code", "Programming", 35.0f));
    	store.addMedia(new Book(5, "The  Programmer", "Programming", 32.5f));
    	store.addMedia(new Book(6, "9114", "Fiction", 22.0f));

        launch(args);
    }
}