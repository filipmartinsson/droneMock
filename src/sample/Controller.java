package sample;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import javafx.scene.Group;

public class Controller {

    @FXML
    private Text xPos;
    @FXML
    private Text yPos;
    @FXML
    private Text zPos;
    @FXML
    private Group mapGroup;

    @FXML
    protected void addPosition() {
        System.out.println("Creating position");
    }
    @FXML
    protected void deletePosition() {
        System.out.println("Deleting position");
    }









}
