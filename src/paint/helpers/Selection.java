package paint.helpers;

import geometry.Point;
import geometry.Shape;
import paint.mvc.PaintModel;

import java.util.ArrayList;

public class Selection {

    private PaintModel paintModel;



    public Selection(PaintModel paintModel){
        this.paintModel = paintModel;

    }

    public void addSelectedShape(Shape shape){

        paintModel.getSelectedShapes().add(shape);

    }

    public void removeSelectedShape(Shape shape){

        paintModel.getSelectedShapes().remove(shape);

    }

    public Shape getSelectedShape(Point clickPoint) {

        Shape tmp = null;

        for(int i =  paintModel.getShapes().size() - 1; i >= 0; i-- ) {
            if (paintModel.getShapes().get(i).contains(clickPoint.getX(), clickPoint.getY())) {
                tmp = paintModel.getShapes().get(i);
                break;
            }
        }

        return  tmp;
    }



    public void clearSelectedShapesList(){

            ArrayList<Shape> shapesToRemove = (ArrayList<Shape>) paintModel.getSelectedShapes().clone();

            paintModel.getSelectedShapes().removeAll(shapesToRemove);

    }





}
