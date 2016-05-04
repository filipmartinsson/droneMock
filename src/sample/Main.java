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




    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));

        primaryStage.setTitle("Hello World!");
        Scene scene = new Scene(root, 940, 545);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
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
