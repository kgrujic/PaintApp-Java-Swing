package paint.mvc;

import geometry.Shape;
import paint.observer.UpdateDeleteClearAllObservable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class PaintModel {

    private ArrayList<Shape> Shapes;
    private  ArrayList<Shape> selectedShapes;

    private ArrayList<Shape> backUpShapes;



    public PaintModel() {
        Shapes = new ArrayList<Shape>();
        selectedShapes = new ArrayList<Shape>();
        backUpShapes = new ArrayList<Shape>();

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
        Shapes.addAll(backUpShapes);


        for (Shape sh: backUpShapes) {
            System.out.println(sh);

        }
    }

    public ArrayList<Shape> getSelectedShapes(){
        return selectedShapes;
    }

    public ArrayList<Shape> getShapes(){
        return Shapes;
    }

    public void deselectAllShapes(){
        for (Shape sh: Shapes) {
            sh.setSelected(false);
        }
        ArrayList<Shape> shapesToRemove = (ArrayList<Shape>) getSelectedShapes().clone();

        getSelectedShapes().removeAll(shapesToRemove);
    }


}
