package paint.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LogObservable {

    private  boolean importLog;
    private  boolean saveLog;
    private List<IObserverLog> observers;

    public LogObservable(){
        observers = new ArrayList<IObserverLog>();
    }


    public void setImportLog(boolean importLog) {
        this.importLog = importLog;
        notifyObserversImport();
    }

    public void setSaveLog(boolean saveLog) {
        this.saveLog = saveLog;
        notifyObserversSave();
    }



    public void addObserver(IObserverLog observer) {
        observers.add(observer);
    }


    public void removeObserver(IObserverLog observer) {
        observers.remove(observer);
    }


    public void notifyObserversImport() {
        Iterator<IObserverLog> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateImportLog(importLog);
        }
    }

    public void notifyObserversSave() {
        Iterator<IObserverLog> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateSaveLog(saveLog);
        }
    }
}


