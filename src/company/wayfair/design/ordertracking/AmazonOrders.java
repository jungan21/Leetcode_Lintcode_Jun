package company.wayfair.design.ordertracking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AmazonOrders {

    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();
        DeliveryBoy mainDeliveryBoy = new DeliveryBoy(100, "Main Delivery Boy");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        Date date1 = cal.getTime();

        Order order1 = new Order(1, date1);

        //Order Received
        orderManager.addOrder(order1);

        //Order Assigned
        order1.assignDeliveryBoy(mainDeliveryBoy);

        //Order Delivered
        order1.setStatus(OrderStatus.Delivered);

        //Picked Up
        order1.setStatus(OrderStatus.PickedUp);
    }
}
