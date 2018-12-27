package paint.mvc;

import geometry.Point;
import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class PaintModel {

    private ArrayList<Shape> Shapes;

    private  ArrayList<Shape> selectedShapes;


    public PaintModel() {
        Shapes = new ArrayList<Shape>();
        selectedShapes = new ArrayList<Shape>();
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
    public void addAllShapes(){

        ArrayList<Shape> shapesToAdd = (ArrayList<Shape>) Shapes.clone();

        Shapes.addAll(shapesToAdd);
    }


    //TODO update command
    public void update(Shape shapeToUpdate, Shape newShape){
        shapeToUpdate.update(newShape);
    }


    public Shape getSelectedShape(Point clickPoint) {

        Shape tmp = null;

        for(int i =  Shapes.size() - 1; i >= 0; i-- ) {
            if (Shapes.get(i).contains(clickPoint.getX(), clickPoint.getY())) {
                tmp = Shapes.get(i);
                break;
            }
        }
        return  tmp;
    }


    public void selectOrDeselectShape(Shape newSelectedShape) {

        if(newSelectedShape != null) {

            if (newSelectedShape.isSelected() == true && newSelectedShape != null) {
                newSelectedShape.setSelected(false);
                selectedShapes.remove(newSelectedShape);

            }
            else {

                if (newSelectedShape.isSelected() == false && newSelectedShape != null) {
                    newSelectedShape.setSelected(true);
                    selectedShapes.add(newSelectedShape);
                }


            }
        }
    }

    public void deselectAllShapes(){
        for (Shape sh: Shapes) {
            sh.setSelected(false);
        }
    }

    public ArrayList<Shape> getShapes(){
        return Shapes;
    }

    public boolean areTwoListsOfShapesEqual(ArrayList<Shape> list1,ArrayList<Shape> list2){
        return   list1.containsAll(list2) &&  list2.containsAll(list1) ;

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
