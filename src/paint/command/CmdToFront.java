package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

import java.util.ArrayList;
import java.util.Collections;

public class CmdToFront implements ICommand {

    private PaintModel paintModel;
    private ArrayList<Shape> selectedShapes;
    private ArrayList<Shape> Shapes;


    public CmdToFront(PaintModel paintModel){
        this.paintModel = paintModel;
        selectedShapes = paintModel.getSelectedShapes();
        Shapes = paintModel.getShapes();

    }
    @Override
    public void execute() {
        int indexOfSelectedShape = getIndexOfSelectedShape();
        if(indexOfSelectedShape != Shapes.size() - 1) {
            Collections.swap(Shapes, indexOfSelectedShape, indexOfSelectedShape + 1);
        }
    }

    @Override
    public void unexecute() {
        int indexOfSelectedShape = getIndexOfSelectedShape();
        if(indexOfSelectedShape != 0) {
            Collections.swap(Shapes, indexOfSelectedShape - 1, indexOfSelectedShape);
        }

    }

    private int getIndexOfSelectedShape(){
        int indexOfSelectedShape = 0;
        if(selectedShapes.size() == 1){
            Shape selectedShape = selectedShapes.get(0);
            indexOfSelectedShape = Shapes.indexOf(selectedShape);
        }

        return indexOfSelectedShape;
    }


}
