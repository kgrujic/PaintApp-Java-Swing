package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

import java.util.ArrayList;

public class CmdBringToFront implements ICommand {

    private PaintModel paintModel;
    private ArrayList<Shape> selectedShapes;
    private ArrayList<Shape> Shapes;

    public CmdBringToFront(PaintModel paintModel){
        this.paintModel = paintModel;
        selectedShapes = paintModel.getSelectedShapes();
        Shapes = paintModel.getShapes();
    }
    @Override
    public void execute() {
        int indexOfSelectedShape = getIndexOfSelectedShape();

        for (int i = indexOfSelectedShape; i <= Shapes.size()-1;i++){
            CmdToFront cmdToFront = new CmdToFront(paintModel);
            cmdToFront.execute();
        }
    }

    @Override
    public void unexecute() {

        int indexOfSelectedShape = getIndexOfSelectedShape();

        for (int i = indexOfSelectedShape; i > 0;i--){
            CmdToBack cmdToBack = new CmdToBack(paintModel);
            cmdToBack.execute();
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
