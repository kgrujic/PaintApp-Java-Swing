package paint.dialogs;

import javax.swing.*;
import java.awt.*;

public class DialogConfiguration {

    public void setSizeAndPositionUpdate(JDialog dialog, String shapeType){
        dialog.setTitle("Update " + shapeType);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

    public void setSizeAndPositionCreate(JDialog dialog,String shapeType){
        dialog.setTitle("Create " + shapeType);
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

    }

}
