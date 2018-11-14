package geometry;

import paint.LineDialog;
import paint.PaintModel;

import javax.swing.*;
import java.awt.*;

public class Line extends Shape{

    private Point startPoint;
    private Point endPoint;


    public Line() {
    }

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(Point startPoint, Point endPoint, Color newColor) {
        super(newColor);
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    // Helpers

    public void moveFor(int newX, int newY){
        startPoint.setX(startPoint.getX() + newX);
        startPoint.setY(startPoint.getY() + newY);
        endPoint.setX(endPoint.getX() + newX);
        endPoint.setY(endPoint.getY() + newY);
    }

    public Point midPoint(){
        int midX = (startPoint.getX() + endPoint.getX()) / 2;
        int midY = (startPoint.getY() + endPoint.getY()) / 2;

        return new Point(midX, midY);
    }

    public double length() { return startPoint.distance(endPoint); }

    @Override
    public String toString() {
        return getClass().getName() +
                "(" + startPoint.getX() + ", " + startPoint.getY() +
                ") --> (" + endPoint.getX() + ", " + endPoint.getY() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Line){
            Line tmp = (Line) obj;

            if(this.startPoint.equals(tmp.startPoint) && this.endPoint.equals(tmp.endPoint))
                return true;
            else
                return false;
        } else
            return false;
    }



    @Override
    public void draw(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawLine(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY());

        if(isSelected())
            selected(g);
    }

    @Override
    public void selected(Graphics g) {
        g.setColor(Color.blue);
        startPoint.selected(g);
        endPoint.selected(g);
        midPoint().selected(g);
    }

    @Override
    public boolean contains(int x, int y) {
      Point clickLocation = new Point(x, y);
      if(clickLocation.distance(startPoint) + clickLocation.distance(endPoint) - this.length() <= 0.5)
          return true;
      else
          return false;
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof Line){
            Line tmp = (Line) o;
            return (int) this.length() - (int) tmp.length();

        } else
            return 0;
    }

    @Override
    public <T extends JDialog> T createDialog(boolean isUpdate, Shape oldShape, PaintModel paintModel) {
        LineDialog lineDialog = new LineDialog(isUpdate,oldShape, paintModel);
        return (T) lineDialog;
    }

    @Override
    public void update(Object... properties) {
        for (int i=0; i < properties.length; i++){
            this.setStartPoint((Point) properties[0]);
            this.setEndPoint((Point) properties[1]);
            this.setOutlineColor((Color) properties[2]);
        }
    }


    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

}
