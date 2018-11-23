package paint.command;

import paint.mvc.PaintModel;

public class CmdRemoveAllShapes implements ICommand {
    private PaintModel model;

    public CmdRemoveAllShapes(PaintModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.removeAllShapes();
    }

    @Override
    public void unexecute() {
        model.addAllShapes();
    }
}
