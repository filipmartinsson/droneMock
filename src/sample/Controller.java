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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


import javafx.fxml.FXML;
import javafx.scene.Group;

import java.util.ArrayList;

public class Controller {


    Circle circle_Red;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


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
    private Group mapGroup;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;

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
                if(xPos.getText().length()>0 && yPos.getText().length()>0 && zPos.getText().length()>0)
                    addButton.setDisable(false);
                else
                    addButton.setDisable(true);
            }
        });
        yPos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(xPos.getText().length()>0 && yPos.getText().length()>0 && zPos.getText().length()>0)
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


        circle_Red = new Circle(50.0f, Color.RED);
        circle_Red.setCursor(Cursor.HAND);
        circle_Red.setOnMousePressed(circleOnMousePressedEventHandler);
        circle_Red.setOnMouseDragged(circleOnMouseDraggedEventHandler);

        mapGroup = new Group();
        mapGroup.getChildren().addAll(circle_Red);
        System.out.println("CIRCLE MANIA!");

        zSlider.setMin(0);
        zSlider.setMax(5000);
        zSlider.setValue(0);
        zSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                zPos.setText(String.valueOf(Integer.valueOf(newValue.intValue())));

            }
        });



    }


    @FXML
    protected void addPosition() {
        System.out.println("Adding position");
        try{
            Position p = new Position(Integer.parseInt(xPos.getText()), Integer.parseInt(yPos.getText()), Integer.parseInt(zPos.getText()), listItems.size());
            listItems.add(p);
            zPos.setText(String.valueOf((int)Math.floor(zSlider.getValue())));
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





    //-----------------------------------------------







    EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                    ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                }
            };








}
