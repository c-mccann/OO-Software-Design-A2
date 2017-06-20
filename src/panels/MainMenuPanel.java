package panels;

import panels.mainmenupanelcomponents.LevelsRadioButtons;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Enumeration;
import javax.swing.JLabel;


/**
 * Created by carlmccann2 on 27/11/2016.
 */
public class MainMenuPanel extends JPanel {
    private JLabel chainReactionLabel = new JLabel("Chain Reaction", SwingConstants.CENTER);
    private LevelsRadioButtons levelsRadioButtons = new LevelsRadioButtons();
    private JButton startGame = new JButton("New Game");

    public MainMenuPanel(){
        setMinimumSize(new Dimension(640,480));
        setLayout(new GridLayout(7,1));
        setBackground(Color.BLACK);

        chainReactionLabel.setForeground(Color.WHITE);
        chainReactionLabel.setFont(new Font(chainReactionLabel.getFont().getName(),Font.PLAIN, 20));

        chainReactionLabel.setBackground(Color.WHITE);
        add(chainReactionLabel);

        JPanel filler = new JPanel();
        filler.setBackground(Color.BLACK);
        add(filler);
        add(filler);
        add(filler);

        ////////// MENU START ////////////////////////
        Enumeration<AbstractButton> radioButtons = levelsRadioButtons.getElements();

        JPanel levelMenu = new JPanel(new GridLayout(1,12));
        levelMenu.setSize(new Dimension(620,100));
        levelMenu.setBackground(Color.BLACK);
        while(radioButtons.hasMoreElements()){
            levelMenu.add(radioButtons.nextElement());
        }
        add(levelMenu);
        //////// MENU End /////////
        add(filler);
        add(filler);
        add(filler);

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startGame.isEnabled()){
                    System.out.println(levelsRadioButtons.getSelection().getMnemonic());
                    CardLayout c = (CardLayout) getParent().getLayout();

                    getParent().add(new MidGamePanel(levelsRadioButtons.getSelection().getMnemonic(),0),
                            "Mid Game",1);
                    c.show(getParent(),"Mid Game");
                }
            }
        });
        add(startGame);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
    }
}
