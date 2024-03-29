package geometry;

import paint.command.CommandListRepository;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Comparable, Serializable {

    private static final long serialVersionUID = 5510713369353881462L;
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

    public abstract <T extends JDialog> T createDialogForUpdate(boolean isUpdate, Shape oldShape, PaintModel paintModel, CommandListRepository commandListRepository);
    //public abstract void update(Shape newShape, boolean isSelected);
    public abstract void update(Shape newShape);
    public abstract Shape cloneInstance();


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
