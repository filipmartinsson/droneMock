package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashMap;

public class Main extends Application {

    @FXML
    private TableView posTableView;

    private TableColumn col1 = new TableColumn("Order");
    private TableColumn col2 = new TableColumn("X-pos");
    private TableColumn col3 = new TableColumn("Y-pos");
    private TableColumn col4 = new TableColumn("Z-pos");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("Hello World!");


        col1.setCellValueFactory(
                new PropertyValueFactory<Position, Integer>("id"));
        col2.setCellValueFactory(
                new PropertyValueFactory<Position, Integer>("x"));
        col3.setCellValueFactory(
                new PropertyValueFactory<Position, Integer>("y"));
        col4.setCellValueFactory(
                new PropertyValueFactory<Position, Integer>("z"));
        root.
        posTableView.getColumns().addAll(col1, col2, col3, col4);

        primaryStage.setScene(new Scene(root, 940, 545));
        primaryStage.show();


    }


    public static void main(String[] args) {
        try {
            Class<?> macFontFinderClass = Class.forName("com.sun.t2k.MacFontFinder");
            Field psNameToPathMap = macFontFinderClass.getDeclaredField("psNameToPathMap");
            psNameToPathMap.setAccessible(true);
            psNameToPathMap.set(null, new HashMap<String, String>());
        } catch (Exception e) {
            // ignore
        }

        launch(args);
    }
}
