package hust.soict.hedspi.javafx;

import java.awt.Color;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PainterController {

    @FXML
    private VBox drawingAreaPane;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
    	Circle newCircle = new Corcle(event.getX(),
    			event.getY(), 4 , Color.black);
    	drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
    	drawingAreaPane.getChildren().clear();
    }

}