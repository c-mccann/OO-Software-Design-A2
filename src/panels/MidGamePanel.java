package panels;

import gamemodel.Levels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by C12508463 on 28/11/2016.
 */
public class MidGamePanel extends JPanel {
    private JLabel scoreLabel = new JLabel("Score:    ");
    private JLabel currentLevelAndWinCondition = new JLabel();
    private JButton playlevel = new JButton("Play");

    public MidGamePanel(){
    }

    public MidGamePanel(int level,int score){
        setLayout(new GridLayout(3,1));
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        this.scoreLabel.setForeground(Color.WHITE);
        this.scoreLabel.setText(scoreLabel.getText() + Integer.toString(score));

        add(scoreLabel);

        Levels currentLevel = Levels.values()[level-1];
        currentLevelAndWinCondition.setBackground(Color.BLACK);
        currentLevelAndWinCondition.setForeground(Color.WHITE);
        currentLevelAndWinCondition.setText("Level: " + level + "    Explode: " + currentLevel.getBallsToPass() + " of "
                + currentLevel.getBalls() + " balls to pass");
        add(currentLevelAndWinCondition);

        playlevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playlevel.isEnabled()){
                    CardLayout c = (CardLayout) getParent().getLayout();

                    // trying to create new environment view based on what level is picked in the combo boxes
                    if((currentLevel.ordinal() + 1) == 1){
                        getParent().add(new GamePanel(currentLevel.ordinal() + 1,0),
                                "Game",2);
                    }
                    else{
                        getParent().add(new GamePanel(currentLevel.ordinal() + 1,score),
                                "Game",2);
                    }
                    c.show(getParent(),"Game");
                }
            }
        });
        add(playlevel);
    }
}

