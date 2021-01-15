package UI;

import AsteroidGame.Score;
import Constants.Constants;
import Listeners.KeyboardEvents;
import Listeners.MouseEvents;
import Model.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.Flow;

public class GamePanel extends JPanel {


    ArrayList<GameObject> GameObjects = new ArrayList<GameObject>();
    ArrayList<Enemy> Enemies = new ArrayList<Enemy>();;
    public ArrayList<Missile> MissileList = new ArrayList<Missile>();;



    KeyboardEvents KeyboardListener;
    MouseEvents ButtonListener;
    private Timer timer;
    private JButton True_button,False_button, SetName;
    private JPanel QuestionPanel, NamePanel;
    private TextField NameInput;
    private Player P1;
    private String PlayerName = "Playername";
    private int score = 0;
    private Score Scorefile;
    private int EnemyThrough = 0; //Count Enemies let through
    private double CurrentExec; // Count the
    private int CurrentQuestion;
    //public Missile missile;
    public int difficulty = 0;
    public int GAMESTATE = 3; //USed for controlling flow of game -1 is paused, 0 is question, 1 is game and 2 is ending, 3 is main menu
    public JLabel Question;


    public GamePanel() {
        InitGamePanel();
        InitScores();
    }

    private void InitScores(){
        Scorefile = new Score();
    }
    public void InitGamePanel() {
        this.setLayout(new FlowLayout());
        this.setBorder(new EmptyBorder(200,0,0,0));

        P1 = new Player();
        QuestionPanel = new JPanel();
        NamePanel = new JPanel();
        //QuestionPanel.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        GameObjects.add(P1);
        CurrentExec = 0;
        CurrentQuestion = 0;
        KeyboardListener = new KeyboardEvents(P1,this);
        this.setAlignmentX(CENTER_ALIGNMENT);
        InitButtons();
        setFocusable(true);
        addKeyListener(KeyboardListener);
        timer = new Timer(Constants.DELAY,new GameCycle());
        timer.start();
        MainMenu();

    }


    public void ResetRound(){ // Resets game with difficulty changes
        GAMESTATE = 1;
        score = 0 - (difficulty*2);
        QuestionPanel.setVisible(false);
        Enemies.clear();
        MissileList.clear();
        P1.reset();


    }



    private void InitButtons()
    {


        True_button = new JButton("True");
        False_button = new JButton("False");
        Question = new JLabel(Constants.Questions.get(0));
        QuestionPanel.setBorder(new EmptyBorder(20,20,20,20));
        QuestionPanel.add(Question);
        QuestionPanel.add(True_button);
        QuestionPanel.add(False_button);
        QuestionPanel.setAlignmentX(CENTER_ALIGNMENT);
        True_button.setAlignmentX(CENTER_ALIGNMENT);
        False_button.setAlignmentX(CENTER_ALIGNMENT);
        Question.setAlignmentX(CENTER_ALIGNMENT);


        BoxLayout BL = new BoxLayout(QuestionPanel,BoxLayout.Y_AXIS);
        QuestionPanel.setLayout(BL);
        this.add(QuestionPanel);

        ButtonListener = new MouseEvents(True_button,False_button,Question,this);

        True_button.addMouseListener(ButtonListener);
        False_button.addMouseListener(ButtonListener);

        QuestionPanel.setVisible(false);
    }

    private void EndGame(){
        Scorefile.Write(PlayerName+" "+EnemyThrough);
        repaint();
    }

    private void MainMenu(){
        BoxLayout BL = new BoxLayout(NamePanel,BoxLayout.Y_AXIS);
        NamePanel.setLayout(BL);
        NamePanel.setBorder(new EmptyBorder(10,10,10,10));
        NamePanel.setAlignmentX(CENTER_ALIGNMENT);

        NameInput = new TextField(10);
        SetName = new JButton("Set Name");
        SetName.setHorizontalAlignment(JButton.CENTER);
        SetName.setAlignmentX(CENTER_ALIGNMENT);
        SetName.addMouseListener(ButtonListener);

        NamePanel.add(NameInput);
        NamePanel.add(SetName);

        this.add(NamePanel);



    }

    private boolean HasSpace(String word){
        for (char c: word.toCharArray())
        {
            if(c == ' '){
                return true;
            }
        }
        return false;
    }



    public void NameSet(){
        String name = NameInput.getText();
        if(name.isEmpty() || name.isBlank() || HasSpace(name)){
            JOptionPane.showMessageDialog(this,"INVALID NAME! No spaces and one word only!","Warning!",JOptionPane.WARNING_MESSAGE);
        }
        else{
            NamePanel.setVisible(false);
            GAMESTATE = 1;

            this.PlayerName = name;
        }


    }


    public void update() {
        if(GAMESTATE == 1 && score<20) {


            for(Missile missile:MissileList){


            if(missile.isVisible()) { //Checking if the missle is actually hitting the enemies
                int missileX = missile.getX();
                int missileY = missile.getY();

                for (Enemy enemy : Enemies) {
                    int enemyX = enemy.getX();
                    int enemyY = enemy.getY();

                    if (enemy.isVisible() && missile.isVisible()) {
                        if (missileX >= (enemyX)
                                && missileX <= (enemyX + Constants.ENEMY_WIDTH)
                                && missileY >= (enemyY)
                                && missileY <= (enemyY + Constants.ENEMY_HEIGHT)) {
                            enemy.setDying(true);
                            score++;
                            missile.die();
                        }
                    }

                }
            }
                int y = missile.getY();
                y-=10;
                if(y < 0){
                    missile.die();
                }
                else{
                    missile.setY(y);

                }




            }

            for (Enemy enemy : Enemies) {
                    if (enemy.getY() > Constants.HEIGHT && !enemy.isDying()) {
                        enemy.die();
                        EnemyThrough++;
                    }

                if(!enemy.isDying()){
                    enemy.move();
                }
            }
            for(GameObject Gobj: GameObjects){
                if(!Gobj.isDying()){
                    Gobj.move();
                }
            }


            Random rn = new Random();
            int n = (560 - 40) + 1;
            int i = rn.nextInt() % n;
            int x = Math.abs(20 + i);
            int time = rn.nextInt((2000 - 500)+1) + 500;

            if ((System.currentTimeMillis() - CurrentExec) >= time) {


                    Enemies.add(new Enemy(x, 0-Constants.ENEMY_HEIGHT, difficulty));

                CurrentExec = System.currentTimeMillis();
            }


            if(score == 10){
                GAMESTATE = 0;
            }

        }
        else if(GAMESTATE == 2){
            timer.stop();
            EndGame();
        }
        repaint();



    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //All drawing functions in here
        doDrawing(g);

    }



    public void doDrawing(Graphics g){
        drawBackground(g);
        if (GAMESTATE == 1) {
            P1.draw(g);



            for (Enemy enemy : Enemies) {
                if(enemy.isVisible()) {
                    enemy.draw(g);
                    if (enemy.isDying()) {
                        enemy.die();
                    }
                }
            }

            for(Missile missile :MissileList){
                if(missile.isVisible()){
                    missile.draw(g);
                }
            }
            g.setFont(new Font("TimesRoman",Font.BOLD,20));
            g.setColor(Color.green);
            g.drawString("Virus Through: "+EnemyThrough,10,20);
        } else if (GAMESTATE == 0) {
            drawQuiz(g);

        }
        else if(GAMESTATE == 2){
            DrawEndScreen(g);
        }
        else if(GAMESTATE == 3){
            drawMainMenu(g);
        }
    }

    public void drawQuiz(Graphics g){
        Question.setText(Constants.Questions.get(CurrentQuestion));
        QuestionPanel.setVisible(true);
    }
    public void drawMainMenu(Graphics g) {
        g.setColor(Color.red);

        g.fillRect(100,20,400,90);
        g.setColor(Color.BLACK);
        g.setFont(new Font("TimesRoman",Font.ITALIC,20));
        g.drawString("Covid Critical:", 125,50);
        g.setFont(new Font("TimesRoman",Font.ITALIC,15));
        g.drawString("A vaccination Trivia Education Game", 175,100);
        g.setFont(new Font("TimesRoman",Font.ITALIC,20));
        g.setFont(new Font("TimesRoman",Font.PLAIN,14));
        g.setColor(Color.WHITE);
        g.fillRect(120,320,420,150);
        g.setColor(Color.BLACK);
        g.drawString("The More questions you get wrong, the more the virus will mutate!",125,350);
        g.drawString("The more it mutates, the faster it becomes!",125,390);
        g.setFont(new Font("TimesRoman",Font.BOLD,14));

        g.drawString("Try to stop as many as you can from getting through,",125,420);
        g.drawString("While answering as many questions right!",125,450);





    }



    public void DrawEndScreen(Graphics g){
        HashMap<String,Integer> Scores = Scorefile.getScores();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,Constants.WIDTH,Constants.HEIGHT);
        g.setColor(Color.WHITE);
        Graphics2D g2d = (Graphics2D)g;
        int space = 50;

        g2d.drawString("Best Scores, Least amount of virus allowed to live:",150,space);

        for(Map.Entry<String,Integer> entry: Scores.entrySet()){
            space+=50;
            g2d.drawString(entry.getKey()+" "+ entry.getValue().toString(),150,space);
            if(space == 300){
                break;
            }
        }
    }

    public void drawBackground(Graphics g) { //Background drawing task
        Image background;
        ImageIcon iibg = new ImageIcon("src/Background2.jpg");
        background = iibg.getImage();
        g.drawImage(background, 0, 0, Constants.WIDTH + 10, Constants.HEIGHT + 10, null);
    }


    private class GameCycle implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e){

        update();


    }
    }


    public int getCurrentQuestion() {
        return CurrentQuestion;
    }

    public void nextQuestion(){
        this.CurrentQuestion++;
    }
}



