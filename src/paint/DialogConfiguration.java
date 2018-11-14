package paint;

import javax.swing.*;
import java.awt.*;

public class DialogConfiguration {

    public void setSizeAndPosition(JDialog dialog){
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
