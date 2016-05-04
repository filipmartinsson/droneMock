package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;


/**
 * Created by admin on 2016-04-13.
 */
public class Position {

    private final IntegerProperty x;
    private final IntegerProperty y;
    private final IntegerProperty z;
    private final IntegerProperty id;
    private final Circle circle;
    private double orgSceneX, orgSceneY;
    private double orgTranslateX, orgTranslateY;



    public Position(int x, int y, int z, int id){
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.z = new SimpleIntegerProperty(z);
        this.id = new SimpleIntegerProperty(id);
        this.circle = new Circle(10.0f, colorRandom());
        circle.setLayoutX(x);
        circle.setLayoutY(y);

    }

    private Color colorRandom(){
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return Color.rgb(red,green,blue);
    }


    public int getX() {
        return x.get();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public int getY() {
        return y.get();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public int getZ() {
        return z.get();
    }

    public IntegerProperty zProperty() {
        return z;
    }

    public void setZ(int z) {
        this.z.set(z);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public EventHandler<MouseEvent> circleOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    System.out.println(orgSceneX);
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
                }
            };

    public EventHandler<MouseEvent> circleOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    if(newTranslateX < 400 && newTranslateX > 10 && newTranslateY < 300 && newTranslateY > 10){
                        ((Circle)(t.getSource())).setTranslateX(newTranslateX);
                        ((Circle)(t.getSource())).setTranslateY(newTranslateY);
                        x.setValue(newTranslateX);
                        y.setValue(newTranslateX);
                    }




                }
            };



    public Circle getCircle(){
        return circle;
    }

}
