package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.Cart.Cart;
import hust.soict.hedspi.aims.Media.Media;
import hust.soict.hedspi.aims.Store.Store;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewStoreController {

    private Store store;
    private Cart cart;

    @FXML
    private FlowPane flowPane;

    public ViewStoreController() {
    }

    public ViewStoreController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
    }

    @FXML
    public void initialize() {
        final String ITEM_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Item.fxml";
        int column = 0;
        int row = 1;

        for (Media media : store.getItemsInStore()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ITEM_FXML_FILE_PATH));
                ItemController itemController = new ItemController();
                fxmlLoader.setController(itemController);

                AnchorPane anchorPane = fxmlLoader.load();
                itemController.setData(media, cart);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                FlowPane.setMargin(anchorPane, new Insets(20, 10, 10, 10));
                flowPane.getChildren().add(anchorPane);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnViewCartPressed(javafx.event.ActionEvent event) {
        try {
            final String CART_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/ViewCart.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CART_FXML_FILE_PATH));
            fxmlLoader.setController(new CartController(store, cart));

            Parent cartRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(cartRoot));
            stage.setTitle("Cart");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}