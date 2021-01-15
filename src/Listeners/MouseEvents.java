package Listeners;

import Constants.Constants;
import UI.GamePanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEvents implements MouseListener {

    JButton True_button,False_button;
    JLabel Question;
    GamePanel GP;
    public MouseEvents(JButton True, JButton False, JLabel Text, GamePanel GP)
    {
        this.True_button = True;
        this.False_button = False;
        this.Question = Text;
        this.GP = GP;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int question = GP.getCurrentQuestion();
        if(GP.GAMESTATE == 0) {


            if (e.getComponent() instanceof JButton) {


                if (e.getComponent() == True_button) {
                    if (Constants.Answers.get(question) == "T") {
                        JOptionPane.showMessageDialog(GP, Constants.Explainations.get(question));

                    } else {
                        JOptionPane.showMessageDialog(GP, Constants.Explainations.get(question));
                        GP.difficulty++;
                    }
                } else if (e.getComponent() == False_button) {
                    if (Constants.Answers.get(question) == "F") {
                        JOptionPane.showMessageDialog(GP, Constants.Explainations.get(question));
                    } else {
                        JOptionPane.showMessageDialog(GP, Constants.Explainations.get(question));
                        GP.difficulty++;
                    }
                }
                GP.nextQuestion();
                GP.ResetRound();
                if (GP.getCurrentQuestion() == Constants.Questions.size() -1) {

                    GP.GAMESTATE = 2;
                }
            }
            }
        else if(GP.GAMESTATE == 3){
            if(e.getComponent() instanceof JButton){

                GP.NameSet();

            }
        }
        }





    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
