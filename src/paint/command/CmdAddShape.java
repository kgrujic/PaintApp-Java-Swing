package paint.command;

import geometry.Shape;
import paint.mvc.PaintModel;

public class CmdAddShape implements ICommand {

    private PaintModel model;
    private Shape shape;

    public CmdAddShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
    }

    @Override
    public void execute() {
        model.add(shape);
    }

    @Override
    public void unexecute() {
        model.remove(shape);
    }
}
