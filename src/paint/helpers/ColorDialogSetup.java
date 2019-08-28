package paint.helpers;

import javax.swing.*;
import java.awt.*;

public class ColorDialogSetup {

    public Color dialogSetOutlineColor(JButton btnOutlineColor){
        Color newColor = JColorChooser.showDialog(null,"Choose outline color",null);
        btnOutlineColor.setBackground(newColor);
        return newColor;
    }

    public Color dialogSetInsideColor(JButton btnInsideColor){
        Color newColor = JColorChooser.showDialog(null,"Choose inside color",null);
        btnInsideColor.setBackground(newColor);
        return newColor;
    }
}
