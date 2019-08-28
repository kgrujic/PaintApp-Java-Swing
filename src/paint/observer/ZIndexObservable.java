package paint.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ZIndexObservable {

    private  boolean zIndexButtons;

    private List<IObserverZIndex> observers;

    public ZIndexObservable(){
        observers = new ArrayList<IObserverZIndex>();
    }


    public void setzIndexButtons(boolean zIndexButtons) {
        this.zIndexButtons = zIndexButtons;
        notifyObservers();
    }





    public void addObserver(IObserverZIndex observer) {
        observers.add(observer);
    }


    public void removeObserver(IObserverZIndex observer) {
        observers.remove(observer);
    }


    public void notifyObservers() {
        Iterator<IObserverZIndex> it = observers.iterator();
        while (it.hasNext()) {
            it.next().updateButtonsForZIndex(zIndexButtons);
        }
    }

}


