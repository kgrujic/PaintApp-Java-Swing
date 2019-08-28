package paint.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ImageObservable {

    private  boolean importImage;
    private  boolean saveImage;
    private List<IObserverImage> observers;

    public ImageObservable(){
        observers = new ArrayList<IObserverImage>();
    }


    public void setImportImage(boolean importImage) {
        this.importImage = importImage;
        notifyObserversImport();
    }

    public void setSaveImage(boolean saveImage) {
        this.saveImage = saveImage;
        notifyObserversSave();
    }



    public void addObserver(IObserverImage observer) {
        observers.add(observer);
    }


    public void removeObserver(IObserverImage observer) {
        observers.remove(observer);
    }


    public void notifyObserversImport() {
        Iterator<IObserverImage> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateImportImage(importImage);
        }
    }

    public void notifyObserversSave() {
        Iterator<IObserverImage> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateSaveImage(saveImage);
        }
    }


}


