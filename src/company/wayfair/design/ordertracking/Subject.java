package company.wayfair.design.ordertracking;

public interface Subject<T>{
    public boolean registerObserver(Observer<T> observer);
    public void notifyAllObservers();
    public boolean deleteObserver(Observer<T> observer);
}