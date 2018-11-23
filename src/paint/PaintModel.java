package paint;

import geometry.Point;
import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;



public class PaintModel {

    private ArrayList<Shape> Shapes;


    public PaintModel() {
        Shapes = new ArrayList<Shape>();
    }

    public void add(Shape newShape) {
        Shapes.add(newShape);
    }

    public void remove(Shape shape) {
        Shapes.remove(shape);
    }

    public void removeAllShapes(){

        ArrayList<Shape> shapesToRemove = (ArrayList<Shape>) Shapes.clone();

        Shapes.removeAll(shapesToRemove);
    }

    public ArrayList<Shape> getShapes(){
        return Shapes;
    }

    public void update(Shape shapeToUpdate, Shape newShape){
        shapeToUpdate.update(newShape);
    }


    public <T extends Shape> T getSelectedShape(Point clickPoint) {

        T tmp = null;

        for(int i =  Shapes.size() - 1; i >= 0; i-- ) {
            if (Shapes.get(i).contains(clickPoint.getX(), clickPoint.getY())) {
                tmp = (T)Shapes.get(i);
                break;
            }
        }
        return  tmp;
    }

    public void selectShape(Shape newSelectedShape) {

        deselectAllShapes();

        if(newSelectedShape != null){
            int indexOfNewSelectedShape = Shapes.indexOf(newSelectedShape);
            Shape shapeInList = Shapes.get(indexOfNewSelectedShape);
            shapeInList.setSelected(true);

        }

    }


    public void deselectAllShapes(){
        for (Shape sh: Shapes) {
            sh.setSelected(false);
        }
    }


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
