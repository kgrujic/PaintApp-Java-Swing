package geometry;

import paint.command.CommandListRepository;
import paint.dialogs.CircleDialog;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.awt.*;

public class Circle extends AreaShape implements IMovable{

    private static final long serialVersionUID = 5510713369353881462L;
    private Point center;
    private int r;



    public Circle() {
    }

    public Circle(Point center, int r) {
        this.center = center;
        this.r = r;
    }

    public Circle(Point center, int r, Color outlineColor,Color insideColor) {
        setInsideColor(insideColor);
        setOutlineColor(outlineColor);
        this.center = center;
        this.r = r;

    }

    // Helpers

    public double area() { return r * r * Math.PI; }
    public double circumference() { return 2 * r * Math.PI; }

    @Override
    public String toString() {
        return getClass().getName() + ":" + "Center: " + center + ", Radius: " + r;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Circle){
            Circle tmp = (Circle) obj;
            if (this.center.equals(tmp.center) && this.r == tmp.r)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public void fill(Graphics g) {
        g.setColor(getInsideColor());
        g.fillOval(center.getX() - r + 1, center.getY() - r + 1 , 2 * r - 2, r * 2 - 2);
    }



    @Override
    public void moveOn(int newX, int newY) {
        center.setX(newX);
        center.setY(newY);
    }

    @Override
    public void moveFor(int newX, int newY) {
        center.setX(center.getX() + newX);
        center.setY(center.getY() + newY);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawOval(center.getX() - r, center.getY() - r, 2 * r, r * 2);
        fill(g);
        if (isSelected())
            selected(g);
    }

    @Override
    public void selected(Graphics g) {
        new Line(new Point(center.getX(), center.getY() - r), new Point(center.getX(),center.getY() + r)).selected(g);

        new Line(new Point(center.getX() - r, center.getY()), new Point(center.getX() + r, center.getY())).selected(g);
    }

    @Override
    public boolean contains(int x, int y) {
        Point clickLocation = new Point(x , y);
        if (clickLocation.distance(center) <= r)
            return true;
        else
            return false;
    }

    @Override
    public <T extends JDialog> T createDialogForUpdate(boolean isUpdate, Shape oldShape, PaintModel paintModel, CommandListRepository commandListRepository) {
        CircleDialog crcDialog = new CircleDialog(isUpdate,oldShape, paintModel, commandListRepository);
        return (T) crcDialog;
    }



    @Override
    public void update(Shape newShape) {
            Circle tmpCircle = (Circle) newShape;
            this.setCenter(tmpCircle.getCenter());
            this.setR(tmpCircle.getR());
            this.setInsideColor(tmpCircle.getInsideColor());
            this.setOutlineColor(tmpCircle.getOutlineColor());
            this.setSelected(tmpCircle.isSelected());

    }

    @Override
    public Shape cloneInstance() {
        Circle originalCircle = new Circle();
            originalCircle.setCenter(this.getCenter());
            originalCircle.setR(this.getR());
            originalCircle.setInsideColor(this.getInsideColor());
            originalCircle.setOutlineColor(this.getOutlineColor());
            originalCircle.setSelected(this.isSelected());

        return originalCircle;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Circle){
            Circle tmp = (Circle) o;
            return this.r - tmp.r;
        } else
            return 0;

    }



    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }
}
