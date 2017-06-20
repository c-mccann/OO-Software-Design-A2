package panels.mainmenupanelcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by carlmccann2 on 27/11/2016.
 */
public class LevelsRadioButtons extends ButtonGroup {

    public LevelsRadioButtons(){

        for (int i = 1; i <= 12 ; i++) {
            JRadioButton tempRadioButton = new JRadioButton(Integer.toString(i));
            tempRadioButton.setBackground(Color.BLACK);
            tempRadioButton.setForeground(Color.WHITE);
            tempRadioButton.setMnemonic(i);
            tempRadioButton.setOpaque(false);
            if(i == 1) tempRadioButton.setSelected(true);
            this.add(tempRadioButton);
        }
    }
}
