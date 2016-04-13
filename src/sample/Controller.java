package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import javafx.scene.Group;

import java.util.ArrayList;

public class Controller {

    @FXML
    private AnchorPane rooot;

    @FXML
    private TextField xPos;
    @FXML
    private TextField yPos;
    @FXML
    private TextField zPos;
    @FXML
    private Group mapGroup;
    @FXML
    private TableView posTableView;

    private ArrayList<Position> positionList = new ArrayList<>();
    ObservableList<Position> listItems = FXCollections.observableArrayList ();


    @FXML
    protected void addPosition() {
        System.out.println("Adding position");
        try{
            Position p = new Position(Integer.parseInt(xPos.getText()), Integer.parseInt(xPos.getText()), Integer.parseInt(xPos.getText()), positionList.size());
            positionList.add(p);
            listItems.add(p);
            posTableView.setItems(listItems);
        }catch (Exception e){
            //not a string
        }

    }
    @FXML
    protected void deletePosition() {
        System.out.println("Deleting position");
    }









}
