package gamemodel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by C12508463 on 18/11/2016.
 */
public class Ball {
    private Point centre,drawingOrigin;
    private final int radius = 6;
    private int vx, vy;
    private Color color;

    public Ball(Point centre){
        Random random = new Random();
        this.centre = centre;
        this.drawingOrigin = new Point(centre.x - radius,centre.y - radius);

        //give random velocity between 1 and 3, then randomly make negative, for diversity in movement
        this.vx = random.nextInt(2) + 1;
        this.vy = random.nextInt(2) + 1;
        if(random.nextInt(2) == 1) vx *= -1;
        if(random.nextInt(2) == 1) vy *= -1;

        this.color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        while(EuclideanDistance.tooCloseToBlack(color)){
            this.color = new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        }
    }


    public synchronized void drawBall(Graphics2D g2d){
        // x, y, width, height
        g2d.setColor(color);
        g2d.fillOval(drawingOrigin.x, drawingOrigin.y, radius * 2, radius * 2);
    }

    public void moveBall(int containerHeight, int containerWidth){

        centre.x += vx;
        centre.y += vy;
        drawingOrigin = new Point(centre.x - radius,centre.y - radius);
        hitBoundaryLeftOrRight(containerWidth);
        hitBoundaryTopOrBottom(containerHeight);
    }

    public void hitBoundaryLeftOrRight(int containerWidth){
        if(centre.x + radius > containerWidth  || centre.x - radius < 0) vx = -vx;
    }
    public void hitBoundaryTopOrBottom(int containerHeight){
        if(centre.y + radius > containerHeight || centre.y - radius < 0) vy = -vy;
    }

    public synchronized Explosion explosionCollision(ConcurrentLinkedDeque<Explosion> explosions, JLabel scoreLabel, JLabel levelScoreLabel){
        for (Explosion e: explosions) {
            if(Point.distance(centre.getX(),centre.getY(),e.getCentre().getX(),e.getCentre().getY())
                    < radius + e.getRadius()){

                // update score
                String[] scoreLabelTextSplit = scoreLabel.getText().split(" ");
                int newScore = Integer.parseInt(scoreLabelTextSplit[scoreLabelTextSplit.length-1])
                        + (int)(Math.pow((double)e.getDepth()+1,3) * 100);
                scoreLabelTextSplit[scoreLabelTextSplit.length-1] = Integer.toString(newScore);
                StringBuilder stringBuilder = new StringBuilder();
                for(String s: scoreLabelTextSplit){
                    if(stringBuilder.length() > 0) stringBuilder.append(" ");
                    stringBuilder.append(s);
                }
                scoreLabel.setText(stringBuilder.toString());


                // update level score
                String[] levelScoreLabelSplit = levelScoreLabel.getText().split(" ");
                int newLevelScore = Integer.parseInt(levelScoreLabelSplit[levelScoreLabelSplit.length -1])
                        + (int)(Math.pow((double)e.getDepth()+1,3) * 100);
                levelScoreLabelSplit[levelScoreLabelSplit.length -1] = Integer.toString(newLevelScore);
                StringBuilder stringBuilder1 = new StringBuilder();
                for(String s: levelScoreLabelSplit){
                    if(stringBuilder1.length() > 0) stringBuilder1.append(" ");
                    stringBuilder1.append(s);
                }
                levelScoreLabel.setText(stringBuilder1.toString());

                return new Explosion(centre,color,e.getDepth()+1);
            }
        }
        return null;
    }
}
