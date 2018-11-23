package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdUpdateShape implements ICommand {

    private PaintModel model;
    private Shape oldShape;
    private Shape newShape;

    public CmdUpdateShape(PaintModel model, Shape oldShape, Shape newShape) {
        this.model = model;
        this.oldShape = oldShape;
        this.newShape = newShape;
    }

    @Override
    public void execute() {
        model.update(oldShape,newShape);
    }

    @Override
    public void unexecute() {
        model.update(newShape,oldShape);
    }
}
