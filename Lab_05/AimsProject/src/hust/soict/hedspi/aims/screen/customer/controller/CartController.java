package hust.soict.hedspi.aims.screen.customer.controller;

import hust.soict.hedspi.aims.Cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.Media.*;
import hust.soict.hedspi.aims.Store.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CartController {

    private Cart cart;
    private Store store;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TextField tfFilter;

    @FXML
    private ToggleGroup filterCategory;

    @FXML
    private TableColumn<Media, Integer> colMediaId;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediaCategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Label costLabel;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    private ObservableList<Media> mediaInCart;
    private FilteredList<Media> filteredMedia;

    public CartController(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        this.mediaInCart = FXCollections.observableArrayList(cart.getItemsOrdered());
    }

    @FXML
    public void initialize() {
        colMediaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMediaTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colMediaCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colMediaCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        filteredMedia = new FilteredList<>(mediaInCart, p -> true);
        tblMedia.setItems(filteredMedia);

        tfFilter.textProperty().addListener((observable, oldValue, newValue) -> updateFilteredMedia());
        filterCategory.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> updateFilteredMedia());

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> updateButtonState(newValue)
        );

        updateTotalCost();
    }

    private void updateFilteredMedia() {
        Toggle selectedToggle = filterCategory.getSelectedToggle();
        String filterText = tfFilter.getText().trim().toLowerCase();

        if (selectedToggle == null || filterText.isEmpty()) {
            filteredMedia.setPredicate(p -> true);
            return;
        }

        RadioButton selectedRadio = (RadioButton) selectedToggle;

        if ("By ID".equals(selectedRadio.getText())) {
            filteredMedia.setPredicate(media -> String.valueOf(media.getId()).contains(filterText));
        } else {
            filteredMedia.setPredicate(media -> media.getTitle().toLowerCase().contains(filterText));
        }
    }

    private void updateButtonState(Media media) {
        if (media != null) {
            btnRemove.setDisable(false);
            btnPlay.setDisable(!(media instanceof Playable));
        } else {
            btnRemove.setDisable(true);
            btnPlay.setDisable(true);
        }
    }

    private void updateTotalCost() {
        costLabel.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    private void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();

        if (media == null) {
            showSimpleAlert("No Selection", "Please select a media item to play.");
            return;
        }

        if (media instanceof Playable) {
            try {
                ((Playable) media).play();

                String title = media.getTitle();
                String category = media.getCategory();
                float cost = media.getCost();
                int length = 0;

                if (media instanceof CompactDisc cd) {
                    length = cd.getLength();
                } else if (media instanceof DigitalVideoDisc dvd) {
                    length = dvd.getLength();
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Now Playing");
                alert.setHeaderText("🎬 Media Playback");

                StringBuilder content = new StringBuilder();
                content.append("🎵 Now Playing: ").append(title).append("\n")
                       .append("📂 Category: ").append(category).append("\n")
                       .append("⏱ Duration: ").append(length).append(" minutes\n")
                       .append("💰 Cost: ").append(String.format("%.2f $", cost));

                Label label = new Label(content.toString());
                label.setStyle("-fx-font-size: 14px;");
                alert.getDialogPane().setContent(label);
                alert.showAndWait();

            } catch (PlayerException e) {
                showSimpleAlert("Playback Error", e.getMessage());
            }
        } else {
            showSimpleAlert("Unsupported Media", "This media cannot be played.");
        }
    }

    private void showSimpleAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();
        if (selectedMedia != null) {
            cart.removeMedia(selectedMedia);
            mediaInCart.remove(selectedMedia);
            updateTotalCost();
        }
    }

    @FXML
    void btnViewStorePressed(ActionEvent event) {
        try {
            final String STORE_FXML_FILE_PATH = "/hust/soict/hedspi/aims/screen/customer/view/Store.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(STORE_FXML_FILE_PATH));
            fxmlLoader.setController(new ViewStoreController(store, cart));

            Parent storeRoot = fxmlLoader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(storeRoot));
            stage.setTitle("Store");
            stage.show();
        } catch (IOException e) {
            showAlert("Navigation Error", "Could not load the store screen.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.isEmpty()) {
            showSimpleAlert("Cart is Empty", "There are no items to place an order.");
            return;
        }

        StringBuilder orderDetails = new StringBuilder("📦 Your Order Summary:\n\n");
        for (Media media : cart.getItemsOrdered()) {
            orderDetails.append("• ")
                        .append(media.getTitle())
                        .append(" - ")
                        .append(String.format("%.2f $", media.getCost()))
                        .append("\n");
        }
        orderDetails.append("\n💰 Total: ").append(String.format("%.2f $", cart.totalCost()));

        cart.exportInvoiceToFile("invoice.txt");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Confirmation");
        alert.setHeaderText("✅ Order Placed Successfully");
        Label content = new Label(orderDetails.toString());
        content.setStyle("-fx-font-size: 14px;");
        alert.getDialogPane().setContent(content);
        alert.showAndWait();

        cart.clear();
        mediaInCart.clear();
        updateTotalCost();
    }


}