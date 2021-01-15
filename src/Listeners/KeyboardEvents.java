package Listeners;

import Constants.Constants;
import Model.Missile;
import Model.Player;
import UI.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardEvents extends KeyAdapter {

    Player player;
    GamePanel GP;
    public KeyboardEvents(Player player, GamePanel GP){
        super();
        this.player = player;
        this.GP = GP;
    }

    @Override
    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        player.KeyPressed(e);


        int key = e.getKeyCode();

        if(key == KeyEvent.VK_SPACE){
            {
                if(GP.GAMESTATE == 1){
                    GP.MissileList.add(new Missile(player.getX(),player.getY()));


                }
            }
        }
        else if(key == KeyEvent.VK_ESCAPE){

            if(GP.GAMESTATE == 1){
                GP.GAMESTATE = -1;
            }
            else if(GP.GAMESTATE == -1) {
                GP.GAMESTATE = 1;
            }


        }
    }
}
