package sample;

/**
 * Created by admin on 2016-04-13.
 */
public class Position {

    public int x;
    public int y;
    public int z;
    public int id;

    public Position(int x, int y, int z, int id){
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setId(int id) {
        this.id = id;
    }
}
