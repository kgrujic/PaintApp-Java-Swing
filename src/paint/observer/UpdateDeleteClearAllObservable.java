package paint.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateDeleteClearAllObservable {
    private  boolean updateButton;
    private  boolean deleteButton;
    private  boolean clearAllButton;
    private  boolean deselectAllButton;
    private List<IObserverUpdateDeleteClearAllDeselectAll> observers;

    public UpdateDeleteClearAllObservable(){
        observers = new ArrayList<IObserverUpdateDeleteClearAllDeselectAll>();
    }


    public void setUpdateButton(boolean updateButton) {
        this.updateButton = updateButton;
        notifyObserversUpdate();
    }

    public void setDeleteButton(boolean deleteButton) {
        this.deleteButton = deleteButton;
        notifyObserversDelete();
    }

    public void setClearAllButton(boolean clearAllButton) {
        this.clearAllButton = clearAllButton;
        notifyObserversClearAll();
    }


    public void setDeselectAllButton(boolean deselectAllButton) {
        this.deselectAllButton = deselectAllButton;
        notifyObserversDeselectAll();
    }



    public void addObserver(IObserverUpdateDeleteClearAllDeselectAll observer) {
        observers.add(observer);
    }


    public void removeObserver(IObserverUpdateDeleteClearAllDeselectAll observer) {
        observers.remove(observer);
    }


    public void notifyObserversUpdate() {
        Iterator<IObserverUpdateDeleteClearAllDeselectAll> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateUpdateButton(updateButton);
        }
    }
    public void notifyObserversDelete() {
        Iterator<IObserverUpdateDeleteClearAllDeselectAll> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateDeleteButton(deleteButton);
        }
    }
    public void notifyObserversClearAll() {
        Iterator<IObserverUpdateDeleteClearAllDeselectAll> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateClearAllButton(clearAllButton);
        }
    }
    public void notifyObserversDeselectAll() {
        Iterator<IObserverUpdateDeleteClearAllDeselectAll> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateDeselectAllButton(deselectAllButton);
        }
    }
}
