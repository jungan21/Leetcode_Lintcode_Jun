package company.wayfair.design.ordertracking;

import java.util.ArrayList;

public class OrderManager implements Observer<Order> {

    private ArrayList<Order> orders;

    public OrderManager() {
        this.orders = new ArrayList<Order>();
    }

    public boolean addOrder(Order order) {
        if(!this.orders.contains(order)){
            this.orders.add(order);
            order.registerObserver(this);
            order.setStatus(OrderStatus.Received);
            return true;
        }

        return false;
    }

    @Override
    public void onChange(Order subject) {
        // TODO Auto-generated method stub
        String msg = String.format("Order #%d status changed - %s",
                subject.getId(),
                subject.getStatus().description);
        System.out.println(msg);
    }

}