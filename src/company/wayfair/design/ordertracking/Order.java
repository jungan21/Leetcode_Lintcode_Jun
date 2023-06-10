package company.wayfair.design.ordertracking;

import java.util.ArrayList;
import java.util.Date;

public class Order implements Subject<Order> {

    private ArrayList<Observer<Order>> observers;
    private int id;
    private Date expectedTime;
    private OrderStatus status;
    private DeliveryBoy deliveryBoy;

    public Order(int id, Date expectedTime){
        this.observers = new ArrayList<Observer<Order>>();
        this.id = id;
        this.expectedTime = expectedTime;
    }

    public int getId(){
        return this.id;
    }

    public void assignDeliveryBoy(DeliveryBoy deliveryBoy) {
        this.deliveryBoy = deliveryBoy;
        this.setStatus(OrderStatus.Assigned);
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        this.notifyAllObservers();
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    @Override
    public boolean registerObserver(Observer<Order> observer) {
        if(!this.observers.contains(observer)) {
            this.observers.add(observer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteObserver(Observer<Order> observer) {
        return this.observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer<Order> observer:this.observers) {
            observer.onChange(this);
        }
    }
}