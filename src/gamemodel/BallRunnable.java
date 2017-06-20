package gamemodel;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by C12508463 on 29/11/2016.
 */
public class BallRunnable implements Runnable {
    //////' write a class rather than lambda/AIC
    private volatile CopyOnWriteArrayList<Ball> balls;
    private volatile JPanel panel;
    private volatile ConcurrentLinkedDeque<Explosion> explosions;

    public BallRunnable(CopyOnWriteArrayList<Ball> balls, ConcurrentLinkedDeque<Explosion> explosions, JPanel panel){
        this.balls = balls;
        this.explosions = explosions;
        this.panel = panel;
    }

    @Override
    public void run() {
        while(true){
            synchronized (this){
                ArrayList<Ball> toBeRemoved = new ArrayList<>();
                for(Ball b : balls){

                    b.moveBall(480,640);
                    Explosion newExplosion = null;
                    //TODO
                    if((newExplosion = b.explosionCollision(explosions,(JLabel)panel.getComponent(0),
                            (JLabel)panel.getComponent(1))) != null)
                    {
                        toBeRemoved.add(b);
                        explosions.add(newExplosion);
                    }
                }
                //TODO
                balls.removeAll(toBeRemoved);
            }
            panel.revalidate();
            panel.repaint();
            try{ Thread.sleep(20); }
            catch(Exception e){ e.printStackTrace(); }
        }
    }
}
