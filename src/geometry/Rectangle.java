package geometry;

import paint.command.CommandListRepository;
import paint.mvc.PaintModel;
import paint.dialogs.RectangleDialog;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends Square {

    private int sideWidth;

    public Rectangle() {
    }

    public Rectangle(Point upLeft, int sideLength, int sideWidth) {
        super(upLeft, sideLength);
        this.sideWidth = sideWidth;
    }

    public Rectangle(Point upLeft, int sideLength, int sideWidth, Color newOutlineColor, Color newInsideColor) {
        super(upLeft, sideLength, newOutlineColor,newInsideColor);
        this.sideWidth = sideWidth;
    }

    // Helpers


    @Override
    public Line diagonal() {
        return new Line(upLeft, new Point(upLeft.getX() + getSideLength(), upLeft.getY() + sideWidth));
    }

    @Override
    public Point squareCenter() {
        return  diagonal().midPoint();
    }

    @Override
    public int area() { return sideWidth * getSideLength(); }

    @Override
    public int circumference() { return 2 * (sideWidth + getSideLength()); }

    @Override
    public String toString() {
        return getClass().getName() + ": Upper left corner: " + upLeft + ", Width: " + sideWidth + ", Length: " + getSideLength();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle){
            Rectangle tmp = (Rectangle) obj;
            if(this.upLeft.equals(tmp.upLeft) && this.sideWidth == tmp.sideWidth
                && this.getSideLength() == tmp.getSideLength())
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public void fill(Graphics g) {
        g.setColor(getInsideColor());
        g.fillRect(getUpLeft().getX() + 1, getUpLeft().getY() + 1, sideLength - 1, sideWidth - 1 );

    }


    @Override
    public void draw(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawRect(upLeft.getX(),upLeft.getY(),sideLength, sideWidth);
        fill(g);
        if (isSelected())
            selected(g);
    }

    @Override
    public void selected(Graphics g) {
        g.setColor(Color.blue);
        new Line(getUpLeft(),new Point(getUpLeft().getX() + sideLength, getUpLeft().getY())).selected(g);
        new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + sideWidth)).selected(g);
        new Line(new Point(getUpLeft().getX() + sideLength, getUpLeft().getY()), diagonal().getEndPoint()).selected(g);
        new Line(new Point(getUpLeft().getX(),getUpLeft().getY() + sideWidth), diagonal().getEndPoint()).selected(g);
    }

    @Override
    public boolean contains(int x, int y) {
        if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + sideLength)
                && this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + sideWidth))
            return true;
        else
            return false;
    }

    @Override
    public <T extends JDialog> T createDialog(boolean isUpdate, Shape oldShape, PaintModel paintModel, CommandListRepository commandListRepository) {
        RectangleDialog rectDialog = new RectangleDialog(isUpdate,oldShape, paintModel,commandListRepository);
        return (T) rectDialog;
    }

    @Override
    public void update(Shape newShape) {
        Rectangle tmpRectangle = (Rectangle) newShape;
            this.setUpLeft(tmpRectangle.getUpLeft());
            this.setSideLength(tmpRectangle.getSideLength());
            this.setSideWidth(tmpRectangle.getSideWidth());
            this.setInsideColor(tmpRectangle.getInsideColor());
            this.setOutlineColor(tmpRectangle.getOutlineColor());

    }


    public int getSideWidth() {
        return sideWidth;
    }

    public void setSideWidth(int sideWidth) {
        this.sideWidth = sideWidth;
    }
}
