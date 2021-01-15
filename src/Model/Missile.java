package Model;

import java.awt.*;

public class Missile extends GameObject {
    public Missile(){

    }

    public Missile(int x, int y){
        initShot(x,y);

    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(this.x,this.y,5,8);
    }



    private void initShot(int x, int y){
        this.setVisible(true);
        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);

    }




}
