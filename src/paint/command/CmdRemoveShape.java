package paint.command;

import geometry.*;
import paint.log.Helpers;
import paint.mvc.PaintModel;

public class CmdRemoveShape implements ICommand, Cloneable {

    private PaintModel model;
    private Shape shape;
    private Helpers helper;

    public CmdRemoveShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
        helper = new Helpers();
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
