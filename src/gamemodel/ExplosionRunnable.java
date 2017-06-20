package gamemodel;

import com.sun.xml.internal.bind.v2.TODO;
import panels.GameOverPanel;
import panels.GamePanel;
import panels.MidGamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by carlmccann2 on 29/11/2016.
 */
public class ExplosionRunnable implements Runnable {

    private volatile ConcurrentLinkedDeque<Explosion> explosions;
    private volatile GamePanel panel;
    private volatile CopyOnWriteArrayList<Ball> balls;
    private int level;

    public ExplosionRunnable(ConcurrentLinkedDeque<Explosion> explosions, CopyOnWriteArrayList<Ball> balls, int level, GamePanel panel){
        this.explosions = explosions;
        this.panel = panel;
        this.balls = balls;
        this.level = level;
    }

    @Override
    public void run() {
        boolean chainReactionOver = false;
        boolean reactionHasHappened = false;
        while(true){
            synchronized (this){
                for(Explosion explosion : explosions){
                    reactionHasHappened = true;
                    if(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
                            - explosion.getCreationTimeSeconds() > 3){

                        explosion.startShrink();
                    }
                    if(explosion.isEnded()) explosions.remove(explosion);
                }

                if(explosions.isEmpty() && reactionHasHappened) chainReactionOver = true;

                if(chainReactionOver){
                    Levels currentLevel = Levels.values()[level-1];
                    CardLayout cardLayout = (CardLayout)panel.getParent().getLayout();
                    JLabel tempScore = (JLabel)panel.getComponent(0);
                    String[] tempScoreSplit = tempScore.getText().split(" ");





                        if(currentLevel.getBalls() - balls.size() >= currentLevel.getBallsToPass()){
                            if(level == 12){
                                // EndGame
                                int newScore = Integer.parseInt(tempScoreSplit[tempScoreSplit.length-1]);
                                panel.getParent().add(new GameOverPanel(newScore),"Game Over",3);
                                cardLayout.show(panel.getParent(),"Game Over");

                            }
                            else{
                                int newScore = Integer.parseInt(tempScoreSplit[tempScoreSplit.length-1]);
                                panel.getParent().add(new MidGamePanel(level + 1,newScore),"Mid Game",1);
                                cardLayout.show(panel.getParent(),"Mid Game");
                            }

                        }
                        else{ // level failure
                            JLabel tempLevelScore = (JLabel) panel.getComponent(1);
                            String[] s1 = tempLevelScore.getText().split(" ");
                            int newScore = Integer.parseInt(tempScoreSplit[tempScoreSplit.length-1]) - Integer.parseInt(s1[s1.length-1]);

                            panel.getParent().add(new MidGamePanel(level, newScore),"Mid Game",1);
                            cardLayout.show(panel.getParent(),"Mid Game");
                        }


                    System.out.println((currentLevel.getBalls() - balls.size()) + "/" +  currentLevel.getBallsToPass());
                    break;
                }
            }
            panel.repaint();

            try{ Thread.sleep(20); }
            catch(Exception e){ e.printStackTrace(); }
        }
    }
}
