package paint.strategy.importfiles;

import paint.mvc.PaintView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImportImage implements ImportStrategy {

    private PaintView paintView;
    public ImportImage(PaintView paintView){
        this.paintView = paintView;
    }

    @Override
    public void importFile() {
        try {
            FileDialog dialog = new FileDialog((Frame) null, "Selected File to Open");
            dialog.setMode(FileDialog.LOAD);
            dialog.setVisible(true);
            String file = dialog.getFile();

            if(file != null) {
                BufferedImage image = ImageIO.read(new File(file));
                paintView.setImage(image);
            }


        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }
}
