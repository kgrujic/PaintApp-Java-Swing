package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdSelectShape implements ICommand {
    private PaintModel model;
    private Shape shapeToSelect;


    public CmdSelectShape(PaintModel model, Shape shapeToSelect) {
        this.model = model;
        this.shapeToSelect = shapeToSelect;
    }
    @Override
    public void execute() {
        model.selectShape(shapeToSelect);
    }

    @Override
    public void unexecute() {
        model.deselectAllShapes();
    }
}
