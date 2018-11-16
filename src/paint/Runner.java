package paint;

public class Runner {
    public static void main(String[] args){

        PaintModel paintModel = new PaintModel();
        PaintForm paintForm = new PaintForm();
        PaintController paintController = new PaintController(paintForm, paintModel);

        paintController.showMainFrame();

    }
}
