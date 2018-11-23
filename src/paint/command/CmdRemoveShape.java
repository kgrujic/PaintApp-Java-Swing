package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdRemoveShape implements ICommand {

    private PaintModel model;
    private Shape shape;

    public CmdRemoveShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }
    @Override
    public void execute() {
        model.remove(shape);
    }

    @Override
    public void unexecute() {
        model.add(shape);
    }
}
