package Model;

import Constants.Constants;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {



    public Player()
    {
        initPlayer();
    }

    private void initPlayer(){
        //sets starting x/y and set sprite?
        int START_X = 270;
        setX(START_X);
        int START_Y = 540;
        setY(START_Y);
    }

    @Override
    public void move(){
        x+=dx; //change by select amount (move essentially)

        if(x <= 2){ //If player goes off board, set them back
            x = 2;
        }

        if(x >= Constants.WIDTH){
            x = Constants.WIDTH;
        }
    }

    @Override public void draw(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x,y,Constants.PLAYERWIDTH,Constants.PLAYERHEIGHT);
    }



    public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx= -5;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 5;

        }

    }


    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_LEFT){
            dx= 0;
        }

        if(key == KeyEvent.VK_RIGHT){
            dx = 0;
        }

    }





}
