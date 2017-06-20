package gamemodel;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;

/**
 * Created by C12508463 on 18/11/2016.
 */
public class Explosion {
    private Point centre, drawingOrigin;
    private Color color;
    private int depth;
    private long creationTimeSeconds;
    private int endRadius = 20;
    private int radius = 0;
    private boolean shrinking = false;


    public Explosion(Point centre, Color color, int depth){
        this.centre = centre;
        this.drawingOrigin = new Point(centre.x - endRadius, centre.y - endRadius);
        this.color = color;
        this.depth = depth;
        this.color = new Color((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255,(float)0.5);
        creationTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public void drawExplosion(Graphics2D g2d){
        g2d.setColor(color);
        drawingOrigin = new Point(centre.x - radius, centre.y - radius);

        if(shrinking){
            if(radius > 0){
                g2d.fillOval(drawingOrigin.x, drawingOrigin.y , radius *2, radius * 2);
                radius -= 3;
            }
        }
        else{
            if(radius < endRadius * 2){
                g2d.fillOval(drawingOrigin.x, drawingOrigin.y, radius* 2 , radius*2);
                radius++;
            }
            else g2d.fillOval(drawingOrigin.x, drawingOrigin.y, radius * 2, radius * 2);
        }

    }

    public void startShrink(){
        shrinking = true;
    }

    public boolean isEnded(){
        return (shrinking && radius < 1);
    }

    public long getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public Point getCentre() {
        return centre;
    }

    public int getRadius() {
        return radius;
    }

    public int getDepth() {
        return depth;
    }
}
