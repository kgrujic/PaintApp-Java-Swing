package geometry;

import paint.PaintModel;
import paint.PointDialog;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape implements IMovable{

    private int x;
    private int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y,Color newColor) {
        super(newColor);
        this.x = x;
        this.y = y;
    }

    // Helpers

    public double distance(Point p){
        double dx = this.x - p.getX();
        double dy = this.y - p.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return getClass().getName() + "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point){
            Point tmp = (Point) obj;
            if (this.x == tmp.getX() && this.y == tmp.getY())
                return true;
            else
                return false;
        } else
            return false;

    }

    @Override
    public void moveOn(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void moveFor(int newX, int newY) {
        this.x += newX;
        this.y += newY;

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawLine(x - 2, y, x + 2,y);
        g.drawLine(x, y - 2, x, y + 2);

        if(isSelected())
            selected(g);

    }

    @Override
    public void selected(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawRect(x - 3, y - 3, 6, 6 );
    }

    @Override
    public boolean contains(int x, int y) {
        Point clickLocation = new Point(x, y);
        if(clickLocation.distance(this) <= 2)
            return true;
        else
            return false;
    }


    @Override
    public int compareTo(Object o) {
         Point coor = new Point(0,0);
         Point tmp = (Point) o;
         return (int) (this.distance(coor) - tmp.distance(coor));
    }

    @Override
    public <T extends JDialog> T createDialog(boolean isUpdate, Shape oldShape, PaintModel paintModel) {
        PointDialog pointDialog = new PointDialog(isUpdate,oldShape, paintModel);
        return (T) pointDialog;
    }

    @Override
    public void update(Object... properties) {
        for (int i=0; i < properties.length; i++){
            this.setX((int) properties[0]);
            this.setY((int) properties[1]);
            this.setOutlineColor((Color) properties[2]);
        }
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}