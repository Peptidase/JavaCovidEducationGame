package Model;

import Constants.Constants;

import java.awt.*;

public class Enemy extends GameObject {


    int dy = 4;
    float speed;
    public Enemy(int x, int y, int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
        dy += speed;
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x,y, Constants.ENEMY_WIDTH,Constants.ENEMY_HEIGHT);
    }

    @Override
    public void move(){ //will change the x and y values of the alien.
        this.y+=dy;

    }

    @Override
    public int getX() {
        return super.getX();
    }

    @Override
    public void die() {
        super.die();
    }
}
