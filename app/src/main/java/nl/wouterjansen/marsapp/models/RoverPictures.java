package nl.wouterjansen.marsapp.models;

/**
 * Created by Wouter on 3/13/2018.
 */

public class RoverPictures {
    private int id;
    private String img_src;
    private String full_name;

    public RoverPictures(int id, String img_src, String full_name) {
        this.id = id;
        this.img_src = img_src;
        this.full_name = full_name;
    }

    public int getId() {
        return id;
    }

    public String getImg_src() {
        return img_src;
    }

    public String getFull_name() {
        return full_name;
    }

    @Override
    public String toString(){
        return "toString: { " + getId() + " - " + getFull_name() + " @ " + getImg_src() + " }";
    }
}
