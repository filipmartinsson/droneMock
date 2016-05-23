package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.geometry.Point2D;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controller {


    /* TODO

     1. DONE index numbers on balls
     2. be able to change coordinates manually
     3. limiter for coordinates pos
     3. DONE fix correct coordinates
     4. DONE print a txt with all coordinates

      */




    Circle circle_Red;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;

    @FXML
    private AnchorPane mapPane;

    @FXML
    private TableView posTableView;
    @FXML
    private TextField xPos;
    @FXML
    private TextField yPos;
    @FXML
    private TextField zPos;
    @FXML
    private Slider zSlider;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button transferButton;
    @FXML
    private Button generateButton;


    ObservableList<Position> listItems = FXCollections.observableArrayList ();

    @FXML
    public void initialize() {
        deleteButton.setDisable(true);
        xPos.setText("0");
        yPos.setText("0");
        zPos.setText("0");

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
        posTableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
                deleteButton.setDisable(false);
            }

        });

        xPos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(xPos.getText().length()>0 && yPos.getText().length()>0 && zPos.getText().length()>0) // add limits
                    addButton.setDisable(false);
                else
                    addButton.setDisable(true);
            }
        });
        yPos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(Integer.getInteger(xPos.getText()));
                if(xPos.getText().length()>0 && yPos.getText().length()>0 && zPos.getText().length()>0) //add limits
                        addButton.setDisable(false);
                else
                    addButton.setDisable(true);
            }
        });
        zPos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!((int)Math.floor(zSlider.getValue()) == Integer.parseInt(newValue))){
                    zSlider.setValue(Double.valueOf(newValue));
                }
                if(xPos.getText().length()>0 && yPos.getText().length()>0 && zPos.getText().length()>0)
                    addButton.setDisable(false);
                else
                    addButton.setDisable(true);
            }
        });







        zSlider.setMin(0);
        zSlider.setMax(700);
        zSlider.setValue(0);
        zSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zPos.setText(String.valueOf(Integer.valueOf(newValue.intValue())));

            }
        });


    }

    private boolean checkXCoordinates(){
        if((Integer.parseInt(xPos.getText()) >= 0 && (Integer.parseInt(xPos.getText()) <= 420)))
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in X-coordinate!");
            String s = "X-value needs to be between 0-420.";
            alert.setContentText(s);
            alert.show();

            return  false;
        }
    }

    private boolean checkYCoordinates(){
        if((Integer.parseInt(yPos.getText()) >= 0 && (Integer.parseInt(yPos.getText()) <= 420)))
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error in Y-coordinate!");
            String s = "Y-value needs to be between 0-420.";
            alert.setContentText(s);
            alert.show();

            return  false;
        }
    }
    private boolean checkZCoordinates(){
        if((Integer.parseInt(zPos.getText()) >= 0 && (Integer.parseInt(zPos.getText()) <= 700)))
            return true;
        else {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error in Z-coordinate!");
        String s = "Z-value needs to be between 0-700.";
        alert.setContentText(s);
        alert.show();

        return  false;
        }
    }

    @FXML
    protected void addPosition() {
        System.out.println("Adding position");
        try{
            if(checkXCoordinates() && checkYCoordinates() && checkZCoordinates()){
                Position p = new Position(Integer.parseInt(xPos.getText()), Integer.parseInt(yPos.getText()), Integer.parseInt(zPos.getText()), listItems.size());
                listItems.add(p);
                zPos.setText(String.valueOf((int)Math.floor(zSlider.getValue())));
                p.getStackPane().setOnMousePressed(p.circleOnMousePressedEventHandler);
                p.getStackPane().setOnMouseDragged(p.circleOnMouseDraggedEventHandler);
                mapPane.getChildren().addAll(p.getStackPane());
            }
        }catch (Exception e){
            //not a string
        }

    }
    @FXML
    protected void deletePosition() {
        try{
            int indexToRemove = -1;
            for (Object p : posTableView.getSelectionModel().getSelectedItems()) {
                indexToRemove = ((Position) p).getId();

            }
            listItems.get(indexToRemove).HideCircle();
            listItems.remove(indexToRemove);

            for (Position p : listItems) {
                if(p.getId()>indexToRemove)
                    p.setId(p.getId()-1);
            }
            deleteButton.setDisable(true);
        }
        catch (Exception e){
            System.out.println("fucked up exception");
        }




    }



    @FXML
    protected void generateCoordinates() throws FileNotFoundException {
        System.out.println("Generating coordinates");
        File desktopDir = new File(System.getProperty("user.home"), "Desktop");
        PrintWriter printWriter = new PrintWriter(new FileOutputStream(new File(desktopDir, "coordinates.txt")));
        for (Position pos : listItems) {
            printWriter.print("ID: " + String.valueOf(pos.getId()));
            printWriter.print(" X: " + String.valueOf(pos.getX()));
            printWriter.print(" Y: " + String.valueOf(pos.getY()));
            printWriter.print(" Z: " + String.valueOf(pos.getZ()));
            printWriter.println();
        }

        printWriter.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Script generated!");
        String s ="File destination: "+desktopDir+"/coordinates.txt";
        alert.setContentText(s);
        alert.show();


    }

    @FXML
    protected void transferCoordinates() {
        System.out.println("Generating coordinates");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Script could not be transferred!");
        String s ="This version does not support automatic transfer.";
        alert.setContentText(s);
        alert.show();
    }

    public static void addTextLimiter(final TextField tf, final int max) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (Integer.getInteger(tf.getText()) > max) {
                    String s = String.valueOf(max);
                    tf.setText(s);
                }
            }
        });
    }


}
