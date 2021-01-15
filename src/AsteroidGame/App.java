package AsteroidGame;

import Constants.Constants;
import UI.GamePanel;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    //Will hold the information relevant to launching the entire program
    //Will initilize main menu and add the game panel which holds all graphical UI
    public App(){
        InitUI();

    }

    private void InitUI()
    {
        Score Scoresheet = new Score();
        Scoresheet.getScores();

        add(new GamePanel());
        setSize(Constants.WIDTH,Constants.HEIGHT);
        setTitle("am19528");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centers game
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() ->{
            var ex = new App();
            ex.setVisible(true);
        });
    }





}
