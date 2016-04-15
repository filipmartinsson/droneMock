package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.fxml.FXML;
import javafx.scene.Group;

import java.util.ArrayList;

public class Controller {


    @FXML
    private TableView posTableView;
    @FXML
    private TextField xPos;
    @FXML
    private TextField yPos;
    @FXML
    private TextField zPos;
    @FXML
    private Group mapGroup;

    private ArrayList<Position> positionList = new ArrayList<>();
    ObservableList<Position> listItems = FXCollections.observableArrayList ();

    @FXML
    public void initialize() {


        TableColumn col1 = new TableColumn("Order");
        TableColumn col2 = new TableColumn("X-pos");
        TableColumn col3 = new TableColumn("Y-pos");
        TableColumn col4 = new TableColumn("Z-pos");
        col1.setCellValueFactory(
                new PropertyValueFactory<Position,Integer>("id")
        );
        col2.setCellValueFactory(
                new PropertyValueFactory<Position,Integer>("x")
        );
        col3.setCellValueFactory(
                new PropertyValueFactory<Position,Integer>("y")
        );
        col4.setCellValueFactory(
                new PropertyValueFactory<Position,Integer>("z")
        );
        posTableView.setItems(listItems);
        posTableView.getColumns().addAll(col1, col2, col3, col4);

    }


    @FXML
    protected void addPosition() {
        System.out.println("Adding position");
        try{
            Position p = new Position(Integer.parseInt(xPos.getText()), Integer.parseInt(yPos.getText()), Integer.parseInt(zPos.getText()), positionList.size());
            positionList.add(p);
            listItems.add(p);
        }catch (Exception e){
            //not a string
        }

    }
    @FXML
    protected void deletePosition() {
        System.out.println("Deleting position");
    }









}
