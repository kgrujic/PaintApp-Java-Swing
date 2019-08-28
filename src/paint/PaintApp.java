package paint;

import paint.mvc.PaintController;
import paint.mvc.PaintModel;

public class PaintApp {
    public static void main(String[] args){

        PaintModel paintModel = new PaintModel();
        PaintForm paintForm = new PaintForm();
        PaintController paintController = new PaintController(paintForm, paintModel);

        paintController.showMainFrame();

    }
}
