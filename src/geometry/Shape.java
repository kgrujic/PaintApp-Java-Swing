package geometry;

import paint.PaintModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape implements Comparable{

    private Color outlineColor = Color.black;
    private boolean isSelected;


    public Shape() {
    }

    public Shape(Color newColor) {
        this.outlineColor = newColor;

    }


    public abstract void draw(Graphics g);
    public abstract void selected(Graphics g);
    public abstract boolean contains(int x,int y);
    public abstract <T extends JDialog> T createDialog(boolean isUpdate, Shape oldShape, PaintModel paintModel);
    public abstract void update(Shape newShape);


    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
