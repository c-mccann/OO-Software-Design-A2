package panels;

import com.sun.javafx.collections.ListListenerHelper;
import gamemodel.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 18/11/2016.
 */
public class GamePanel extends JPanel {
    private volatile CopyOnWriteArrayList<Ball> balls = new CopyOnWriteArrayList<>();
    private volatile ConcurrentLinkedDeque<Explosion> explosions = new ConcurrentLinkedDeque<>();
    private JLabel scoreLabel;
    private int level;
    private int score;


    public GamePanel(){
    }

    public GamePanel(int level,int score){
        setSize(new Dimension(640,480));
        this.level = level;
        this.score = score;
        setBackground(Color.BLACK);
        Random random = new Random();

        int levelScore = 0;
        scoreLabel = new JLabel("Score:      " + score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.BLACK);
        add(scoreLabel,BorderLayout.BEFORE_FIRST_LINE);
        JLabel levelScoreLabel = new JLabel("Level Score:      " + levelScore);
        scoreLabel.setForeground(Color.WHITE);
//        scoreLabel.setBackground(Color.BLACK);
        add(scoreLabel,BorderLayout.BEFORE_FIRST_LINE);

        levelScoreLabel.setBackground(Color.BLACK);
        levelScoreLabel.setForeground(Color.WHITE);
        add(levelScoreLabel, BorderLayout.BEFORE_FIRST_LINE);

        final boolean[] clickedYet = {false};

        Levels currentLevel = Levels.values()[level-1];
        for (int i = 0; i < currentLevel.getBalls() ; i++) {
            Point p = new Point(random.nextInt(600)+20,random.nextInt(440) + 20); // hardcoded, +20 to keep off boundaries
            synchronized (this){
                balls.add(new Ball(p));
            }
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if(!clickedYet[0]){
                    explosions.add(new Explosion(mouseEvent.getPoint(), new Color(1,1,1,(float)0.5),0));
                    clickedYet[0] = true;
                }
            }
        });

        Thread ballMovementThread = new Thread(new BallRunnable(balls,explosions,this));
        Thread explosionThread = new Thread(new ExplosionRunnable(explosions,balls,level,this));
        ballMovementThread.setDaemon(true);
        explosionThread.setDaemon(true);
        ballMovementThread.start();
        explosionThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        synchronized (this){
            for(Ball ball: balls)  ball.drawBall(g2d);
            for(Explosion explosion : explosions) explosion.drawExplosion(g2d);
        }
    }
}
