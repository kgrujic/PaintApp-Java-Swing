package geometry;

import paint.PaintModel;
import paint.SquareDialog;

import javax.swing.*;
import java.awt.*;

public class Square extends AreaShape implements IMovable {

    protected Point upLeft;
    protected int sideLength;

    public Square() {
    }

    public Square(Point upLeft, int sideLength) {
        this.upLeft = upLeft;
        this.sideLength = sideLength;
    }

    public Square(Point upLeft, int sideLength, Color newOutlineColor,Color newInsideColor) {
        this.upLeft = upLeft;
        this.sideLength = sideLength;
        setOutlineColor(newOutlineColor);
        setInsideColor(newInsideColor);
    }

    // Helpers

    public Line diagonal(){
        return new Line(upLeft, new Point(upLeft.getX() + sideLength, upLeft.getY() + sideLength));
    }

    public Point squareCenter(){

        return diagonal().midPoint();
    }

    public int area(){ return sideLength * sideLength; }
    public int circumference() { return 4 * sideLength; }

    @Override
    public String toString() {
        return getClass().getName() + ":" + "Upper left corner: (" + upLeft.getX() + ", " + upLeft.getY() + "), Side length: " + sideLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Square){
            Square tmp = (Square) obj;
            if (this.upLeft.equals(tmp.upLeft) && this.sideLength == tmp.sideLength)
                return true;
            else
                return false;
        } else
            return false;
    }

    @Override
    public void fill(Graphics g) {
        g.setColor(getInsideColor());
        g.fillRect(upLeft.getX() + 1, upLeft.getY() + 1, sideLength - 1,sideLength - 1);
    }



    @Override
    public void moveOn(int newX, int newY) {
        upLeft.setX(newX);
        upLeft.setY(newY);
    }

    @Override
    public void moveFor(int newX, int newY) {
        upLeft.setX(upLeft.getX() + newX);
        upLeft.setY(upLeft.getY() + newY);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getOutlineColor());
        g.drawRect(upLeft.getX(),upLeft.getY(),sideLength,sideLength);
        fill(g);
        if (isSelected())
            selected(g);
    }

    @Override
    public void selected(Graphics g) {
        g.setColor(Color.blue);
        new Line(getUpLeft(),new Point(getUpLeft().getX() + sideLength, getUpLeft().getY())).selected(g);
        new Line(getUpLeft(), new Point(getUpLeft().getX(), getUpLeft().getY() + sideLength)).selected(g);
        new Line(new Point(getUpLeft().getX() + sideLength, getUpLeft().getY()), diagonal().getEndPoint()).selected(g);
        new Line(new Point(getUpLeft().getX(),getUpLeft().getY() + sideLength), diagonal().getEndPoint()).selected(g);
    }


    @Override
    public boolean contains(int x, int y) {
        if (this.getUpLeft().getX() <= x && x <= (this.getUpLeft().getX() + sideLength)
            && this.getUpLeft().getY() <= y && y <= (this.getUpLeft().getY() + sideLength))
            return true;
        else
            return false;
    }

    @Override
    public <T extends JDialog> T createDialog(boolean isUpdate, Shape oldShape, PaintModel paintModel) {
        SquareDialog sqrDialog = new SquareDialog(isUpdate,oldShape, paintModel);
        return (T) sqrDialog;

    }

    @Override
    public void update(Shape newShape) {
       Square tmpSquare = (Square) newShape;
            this.setUpLeft(tmpSquare.getUpLeft());
            this.setSideLength(tmpSquare.getSideLength());
            this.setInsideColor(tmpSquare.getInsideColor());
            this.setOutlineColor(tmpSquare.getOutlineColor());

    }

    @Override
    public int compareTo(Object o) {
         if (o instanceof Square) {
             Square tmp = (Square) o;
             return this.area() - tmp.area();
         } else
             return 0;
    }

    public Point getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(Point upLeft) {
        this.upLeft = upLeft;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

}
