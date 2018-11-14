package geometry;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public abstract class AreaShape extends Shape {

    private Color insideColor = Color.white;

    public abstract void fill(Graphics g);


    public Color getInsideColor() {
        return insideColor;
    }

    public void setInsideColor(Color insideColor) {
        this.insideColor = insideColor;
    }

}
