package company.walmart.design;

import java.util.*;

public class Merchant implements Comparable<Merchant> {
    public String merchantName;
    public int location;

    public Merchant(String name, int location) {
        this.merchantName = name;
        this.location = location;
    }

    @Override
    public int compareTo(Merchant o) {
        return this.location - o.location;
    }

    @Override
    public String toString() {
        return this.merchantName;
    }

}

class Customer {
    public String customerName;
    public int location;

    public Customer(String customerName, int location) {
        this.customerName = customerName;
        this.location = location;
    }
}

class Order implements Comparable<Order> {
    public Customer customer;
    public Merchant merchant;
    public int createAt;

    public Order(Customer customer, Merchant merchant, int createAt) {
        this.customer = customer;
        this.merchant = merchant;
        this.createAt = createAt;
    }

    @Override
    public int compareTo(Order o) {
        return this.createAt - o.createAt;
    }

    @Override
    public String toString() {
        return String.format("ORDER: %s %s %d", customer.customerName, merchant.merchantName, createAt);
    }
}

class Platform {
    public Map<Merchant, List<Order>> allMerchantOrders = new HashMap<>();
    public Map<Customer, Queue<Order>> allCustomerOrders = new HashMap<>();

    public Set<Merchant> merchants = new TreeSet<>();
    public Set<Customer> customers = new HashSet<>();

    void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // recommend three different merchants to customer
    public Merchant[] recommend(Customer customer) {
        Set<Merchant> added = new HashSet<>();
        Merchant[] ret = new Merchant[3];
        int index = 0;
        // using qucik select or heap to get top 3, there is max heap with PriorityQueue
        // make sure not null so that avoid NPE
        Queue<Order> copyedcustomerOrders = new PriorityQueue<Order>(allCustomerOrders.getOrDefault(customer, new PriorityQueue<Order>(Collections.reverseOrder())));
        while (!copyedcustomerOrders.isEmpty()) {
            if (added.size() > 2) {
                break;
            }
            index = addIfAbsent(added, ret, index, copyedcustomerOrders.poll().merchant);
        }
        return added.size() > 2 ? ret : getNearMerchant(added, ret, index, customer.location);
    }

    private Merchant[] getNearMerchant(Set<Merchant> added, Merchant[] ret, int index, int customerLocation) {
        // using binary search to find user nearest position
        Object[] merchantArray = merchants.toArray();
        int n = merchantArray.length;
        int l = 0, r = n - 1;
        boolean flag = false;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (((Merchant)merchantArray[mid]).location < customerLocation) {
                l = mid + 1;
            } else if (((Merchant)merchantArray[mid]).location > customerLocation) {
                r = mid - 1;
            } else {
                l = mid - 1;
                r = mid + 1;
                flag = true;
                index = addIfAbsent(added, ret, index, ((Merchant)merchantArray[mid]));
                break;
            }
        }
        // swap l r if not found
        if (!flag) {
            int tmp = l;
            l = r;
            r = tmp;
        }
        // expand around position until had three recommend merchants
        while ((l >= 0 || r < n - 1) && added.size() < 3) {
            if (l < 0) {
                index = addIfAbsent(added, ret, index, ((Merchant)merchantArray[r]));
                r++;
            } else if (r >= n) {
                index = addIfAbsent(added, ret, index, ((Merchant)merchantArray[l]));
                l--;
            } else if (Math.abs(customerLocation - ((Merchant)merchantArray[l]).location) <= Math
                    .abs(customerLocation - ((Merchant)merchantArray[r]).location)) {
                index = addIfAbsent(added, ret, index, ((Merchant)merchantArray[l]));
                l--;
            } else {
                index = addIfAbsent(added, ret, index, ((Merchant)merchantArray[r]));
                r++;
            }
        }
        return ret;
    }

    // log order
    public void log(Merchant merchant, Customer customer, int time) {
        Order order = new Order(customer, merchant, time);
        List<Order> merchantOrderList = allMerchantOrders.getOrDefault(merchant, new ArrayList<Order>());
        Queue<Order> customerOrderHeap = allCustomerOrders.getOrDefault(customer,
                new PriorityQueue<Order>(Collections.reverseOrder()));
        merchantOrderList.add(order);
        customerOrderHeap.add(order);
        allMerchantOrders.put(merchant, merchantOrderList);
        allCustomerOrders.put(customer, customerOrderHeap);
    }

    private int addIfAbsent(Set<Merchant> added, Merchant[] ret, int index, Merchant merchant) {
        if (!added.contains(merchant)) {
            added.add(merchant);
            ret[index++] = merchant;
        }
        return index;
    }

    public void printCustomerLog(Customer customer) {
        Queue<Order> queue = new PriorityQueue<Order>(allCustomerOrders.getOrDefault(customer, new PriorityQueue<Order>(Collections.reverseOrder())));
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

}