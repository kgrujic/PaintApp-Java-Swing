package paint.command;

import geometry.Shape;
import paint.log.Helpers;
import paint.mvc.PaintModel;

public class CmdAddShape implements ICommand,Cloneable{

    private PaintModel model;
    private Shape shape;

    private Helpers helper;

    public CmdAddShape() {
    }

    public CmdAddShape(PaintModel model, Shape shape) {
        this.model = model;
        this.shape = shape;
        this.helper = new Helpers();
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
