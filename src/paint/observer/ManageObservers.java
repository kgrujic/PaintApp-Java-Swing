package paint.observer;

import paint.command.CommandListRepository;
import paint.command.ICommand;
import paint.mvc.PaintController;
import paint.mvc.PaintModel;

import javax.swing.*;
import java.lang.reflect.Array;

public class ManageObservers implements IObserverUndoRedo, IObserverUpdateDeleteClearAllDeselectAll,IObserverLog,IObserverImage,IObserverZIndex {

    private PaintController paintController;
    private CommandListRepository commandListRepository;

    private JButton btnUndo;
    private JButton btnRedo;

    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnClearAll;
    private JButton btnDeselectAll;

    private JButton btnSaveLog;
    private JButton btnImportLog;
    private JButton btnSaveImage;
    private JButton btnImportImage;

    private JButton btnToFront;
    private JButton btnToBack;
    private JButton btnBringToFront;
    private JButton btnBringToBack;

    private UndoRedoObservable undoRedoObservable = new UndoRedoObservable();
    private UpdateDeleteClearAllObservable updateDeleteClearAllObservable = new UpdateDeleteClearAllObservable();
    private LogObservable logObservable = new LogObservable();
    private ImageObservable imageObservable = new ImageObservable();
    private ZIndexObservable zIndexObservable = new ZIndexObservable();


    public ManageObservers(PaintController paintController, CommandListRepository commandListRepository){
        this.paintController = paintController;
        this.commandListRepository = commandListRepository;

        btnUndo = paintController.getBtnUndo();
        btnRedo = paintController.getBtnRedo();

        btnDelete = paintController.getBtnDelete();
        btnUpdate = paintController.getBtnUpdate();
        btnClearAll = paintController.getBtnClearAll();
        btnDeselectAll = paintController.getBtnDeselectAll();

        btnSaveLog = paintController.getBtnSaveLog();
        btnImportLog = paintController.getBtnImportLog();
        btnSaveImage = paintController.getBtnSaveImage();
        btnImportImage = paintController.getBtnImportImage();

        btnToFront = paintController.getBtnToFront();
        btnToBack = paintController.getBtnToBack();
        btnBringToFront = paintController.getBtnBringToFront();
        btnBringToBack = paintController.getBtnBringToBack();

        undoRedoObservable.addObserver(this);
        updateDeleteClearAllObservable.addObserver(this);
        logObservable.addObserver(this);
        imageObservable.addObserver(this);
        zIndexObservable.addObserver(this);


    }

    @Override
    public void updateUpdateButton(boolean updateButton) {
        btnUpdate.setEnabled(updateButton);
    }

    @Override
    public void updateDeleteButton(boolean deleteButton) {
       btnDelete.setEnabled(deleteButton);
    }

    @Override
    public void updateClearAllButton(boolean clearAllButton) {
        btnClearAll.setEnabled(clearAllButton);
    }

    @Override
    public void updateDeselectAllButton(boolean deselectAllButton) {
        btnDeselectAll.setEnabled(deselectAllButton);
    }

    @Override
    public void updateRedo(boolean redo) {
        btnRedo.setEnabled(redo);
    }

    @Override
    public void updateUndo(boolean undo) {
        btnUndo.setEnabled(undo);
    }

    @Override
    public void updateImportImage(boolean importImage) {
        btnImportImage.setEnabled(importImage);
    }

    @Override
    public void updateSaveImage(boolean saveImage) {
        btnSaveImage.setEnabled(saveImage);
    }

    @Override
    public void updateImportLog(boolean importLog) {
        btnImportLog.setEnabled(importLog);
    }

    @Override
    public void updateSaveLog(boolean saveLog) {
        btnSaveLog.setEnabled(saveLog);
    }

    @Override
    public void updateButtonsForZIndex(boolean zIndexButtons) {
        btnToBack.setEnabled(zIndexButtons);
        btnToFront.setEnabled(zIndexButtons);
        btnBringToBack.setEnabled(zIndexButtons);
        btnBringToFront.setEnabled(zIndexButtons);
    }

    public void manageMyObservers(){

        PaintModel model = paintController.getPaintModel();
        ListModel<String> listModel = paintController.getListModel();


        undoRedoObservable.setUndo(model.getShapes().size() != 0);

        if(((DefaultListModel<String>) listModel).contains("Undo\n")){
            undoRedoObservable.setRedo(!(commandListRepository.getCurrentIndex() == commandListRepository.getCommandHistory().size()));
        }

        if(paintController.isClearALlOrDelete() || !((DefaultListModel<String>) listModel).contains("Undo\n")){
            undoRedoObservable.setRedo(false);
        }

        updateDeleteClearAllObservable.setUpdateButton(model.getSelectedShapes().size() == 1);
        updateDeleteClearAllObservable.setDeleteButton(!(model.getSelectedShapes().size() == 0));
        updateDeleteClearAllObservable.setClearAllButton(model.getShapes().size() != 0);
        updateDeleteClearAllObservable.setDeselectAllButton(model.getSelectedShapes().size() > 0);

        logObservable.setImportLog(((DefaultListModel<String>) listModel).isEmpty());
        logObservable.setSaveLog(((DefaultListModel<String>) listModel).isEmpty() == false);

        imageObservable.setImportImage(model.getShapes().size() == 0);
        imageObservable.setSaveImage(!(model.getShapes().size() == 0));

        zIndexObservable.setzIndexButtons(model.getSelectedShapes().size() == 1);

    }

}
