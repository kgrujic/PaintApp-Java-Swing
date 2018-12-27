package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdSelectShape implements ICommand, Cloneable{
    private PaintModel model;
    private Shape shapeToSelect;


    public CmdSelectShape(PaintModel model, Shape shapeToSelect) {
        this.model = model;
        this.shapeToSelect = shapeToSelect;
    }
    @Override
    public void execute() {
        model.selectOrDeselectShape(shapeToSelect);
    }

    @Override
    public void unexecute() {
        model.deselectAllShapes();
    }

    @Override
    public CmdSelectShape clone() {
        try {
            return (CmdSelectShape) super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
