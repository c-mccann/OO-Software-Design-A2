import panels.GameOverPanel;
import panels.GamePanel;
import panels.MidGamePanel;
import panels.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by carlmccann2 on 27/11/2016.
 */
public class Driver extends JFrame {
    private int height = 480, width = 640;

    public Driver(){
        super("Chain Reaction");
        setMinimumSize(new Dimension(width,height));
        setMaximumSize(new Dimension(width,height));
        setResizable(false);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(createContentPane());
        setVisible(true);
        pack();
    }

    private Container createContentPane(){
        Container pane = new JPanel(new CardLayout());
        pane.setPreferredSize(new Dimension(width,height));
        String MAINMENUPANEL = "Main Menu";
        String GAMEPANEL = "Game";
        String MIDGAMEPANEL = "Mid Game";
        String GAMEOVERPANEL = "Game Over";
        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        MidGamePanel midGamePanel = new MidGamePanel();
        GamePanel gamePanel = new GamePanel(); // use empty constructor as placeholder
        GameOverPanel gameOverPanel = new GameOverPanel();

        pane.add(mainMenuPanel,MAINMENUPANEL,0);
        pane.add(midGamePanel,MIDGAMEPANEL,1);
        pane.add(gamePanel,GAMEPANEL,2);
        pane.add(gameOverPanel,GAMEOVERPANEL,3);

        return pane;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Driver();
        });
    }
}

