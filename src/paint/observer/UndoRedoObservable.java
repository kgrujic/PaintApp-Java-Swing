package paint.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UndoRedoObservable {

    private  boolean redo;
    private  boolean undo;
    private List<IObserverUndoRedo> observers;

    public UndoRedoObservable(){
        observers = new ArrayList<IObserverUndoRedo>();
    }


    public void setRedo(boolean redo) {
        this.redo = redo;
        notifyObserversRedo();
    }

    public void setUndo(boolean undo) {
        this.undo = undo;
        notifyObserversUndo();
    }



    public void addObserver(IObserverUndoRedo observer) {
        observers.add(observer);
    }


    public void removeObserver(IObserverUndoRedo observer) {
        observers.remove(observer);
    }


    public void notifyObserversRedo() {
        Iterator<IObserverUndoRedo> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateRedo(redo);
        }
    }

    public void notifyObserversUndo() {
        Iterator<IObserverUndoRedo> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateUndo(undo);
        }
    }
}


