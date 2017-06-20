package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by C12508463 on 01/12/2016.
 */
public class GameOverPanel extends JPanel {
    JLabel finalScore = new JLabel();
    JButton mainMenu = new JButton("Main Menu");
    public GameOverPanel(){
    }

    public GameOverPanel(int score){
        //setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);

        finalScore.setForeground(Color.WHITE);
        finalScore.setText("Final Score: " + score);
        add(finalScore,BorderLayout.CENTER);

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mainMenu.isEnabled()){
                    CardLayout c = (CardLayout) getParent().getLayout();
                    c.show(getParent(),"Main Menu");
                }
            }
        });

        add(mainMenu,BorderLayout.SOUTH);
    }
}
