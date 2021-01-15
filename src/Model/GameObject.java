package Model;

import Constants.Constants;

import java.awt.*;

public class GameObject {
    int x,y;
    int dx,dy;
    private boolean visible;
    private boolean dying;
    //All gameobjects orginate from this object, all gameobjects will have a sprite, positions and a visible boolean



    public GameObject(){
        visible = true;
    }

    public void reset(){
        this.x = Constants.WIDTH / 2;
    }


    public void die(){
        visible = false;
        setDying(true);
    }

    public boolean isVisible() {
        return visible;
    }

    public void move(){
        x+=dx;
        y+=dy;

    }

    public boolean isDying() {
        return dying;
    }

    public void setDying(boolean dying){
        this.dying = dying;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void draw(Graphics graphics){}

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
