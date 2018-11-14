package paint;

import geometry.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Dialog extends JDialog {


    // Dialog look
    protected DialogConfiguration dialogConfiguration;

    // Update
    protected boolean isUpdate;
    protected Shape oldShape;
    protected PaintModel paintModel;
    protected Color outlineColor;
    protected Color insideColor;

    // Create
    protected boolean readyToAdd;

    public abstract void setFieldsValuesForUpdate(Shape tmpShape);

    public abstract void setVisibilityToFalse();


    public void setArgumentsForUpdate(boolean isUpdate, Shape oldShape, PaintModel model) {
        this.isUpdate = isUpdate;
        this.oldShape = oldShape;
        this.paintModel = model;
    }

    public void setupContentPane(JPanel contentPane, JButton buttonOK) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public void setupDialog(DialogConfiguration dialogConfiguration) {
        dialogConfiguration = new DialogConfiguration();
        dialogConfiguration.setSizeAndPosition(this);

    }

    public void setupColorButtonsListeners(JButton btnOutlineColor, JButton btnInsideColor){

        if(btnInsideColor != null) {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = paintModel.dialogSetOutlineColor(btnOutlineColor);
                }
            });

            btnInsideColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    insideColor = paintModel.dialogSetInsideColor(btnInsideColor);
                }
            });
        }
        else {
            btnOutlineColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    outlineColor = paintModel.dialogSetOutlineColor(btnOutlineColor);
                }
            });
        }
    }






    }





