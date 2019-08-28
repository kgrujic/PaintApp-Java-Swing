package paint.strategy.exportfiles;

import paint.mvc.PaintView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ExportImage implements ExportStrategy {

    private PaintView paintView;

    public ExportImage(PaintView paintView){
        this.paintView = paintView;
    }

    @Override
    public void exportFile() {

        try {


            FileDialog d = new FileDialog(new JFrame(), "Save", FileDialog.SAVE);
            d.setVisible(true);
            String dir;
            dir = d.getDirectory();
            File oneFile = new File(dir + d.getFile() + ".jpg");

                BufferedImage bimage = new BufferedImage(paintView.getWidth(), paintView.getHeight(), BufferedImage.TYPE_INT_RGB);
                paintView.paint(bimage.getGraphics());
                ImageIO.write(bimage, "JPEG", oneFile);






        } catch (Exception ex) {

            ex.printStackTrace();

        }



    }
}
