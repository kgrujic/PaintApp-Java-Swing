package paint.observer;

public interface IObserverUndoRedo {
    void updateRedo(boolean redo);
    void updateUndo(boolean undo);
}
