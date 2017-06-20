package gamemodel;

import java.awt.*;

/**
 * Created by C12508463 on 25/11/2016.
 */
public class EuclideanDistance {
    // used to help assign colors to balls that are distinct from the background
    public static boolean tooCloseToBlack(Color color){

        double distance = Math.sqrt(Math.pow(color.getRed() - Color.BLACK.getRed(),2) +
                        Math.pow(color.getGreen() - Color.BLACK.getGreen(),2) +
                Math.pow(color.getBlue() - Color.BLACK.getBlue(),2));
        if (distance < 130) return true;
        return false;
    }
}
