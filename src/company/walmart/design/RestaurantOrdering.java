package company.walmart.design;


import java.util.*;

// https://segmentfault.com/a/1190000040361339
public class RestaurantOrdering {
}

interface Command {
    void execute(); // 只需要定义一个统一的执行方法
}

class OrderCommand implements Command{

    //持有接受者对象
    private SeniorChef receiver;

    private Order order;

    public OrderCommand(SeniorChef receiver, Order order) {
        this.receiver = receiver;
        this.order = order;
    }

    @Override
    public void execute() {
        System.out.println(order.getDiningTable()+"桌的订单：");
        for (String key : order.getFoodDic().keySet()) {
            receiver.makefood(order.getFoodDic().get(key),key);
        }
        try {
            Thread.sleep(1000); //模拟做饭 睡眠1秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(order.getDiningTable()+"桌的饭弄好了");
    }
}

class Order {
    // 餐桌号码
    private int diningTable;

    //用来存储餐名并记录
    private Map<String, Integer> foodDic = new HashMap<String, Integer>();
    private int createAt;

    public int getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(int diningTable) {
        this.diningTable = diningTable;
    }

    public Map<String, Integer> getFoodDic() {
        return foodDic;
    }

    public void setFoodDic(String name, int num) {
        foodDic.put(name, num);

    }

}

class SeniorChef {
    //大厨师类 是命令的Receiver

    public void makefood(int num, String foodName) {
        System.out.println(num + "份" + foodName);
    }

}

class Waitor {

    //可以持有很多命令对象
    private ArrayList<Command> commands;

    public Waitor() {
        commands = new ArrayList<Command>();
    }

    public void setCommands(Command cmd) {
        commands.add(cmd);
    }

    //发出命令 订单来了 大厨师开始执行命令
    public void orderUp() {
        System.out.println("来活了...");
        for (int i = 0; i < commands.size(); i++) {
            Command cmd = commands.get(i);
            if (cmd != null) {
                cmd.execute();
            }
        }
    }
}

class Client {

    public static void main(String[] args) {
        //创建order
        Order order1 = new Order();
        order1.setDiningTable(1);
        order1.getFoodDic().put("西红柿炒鸡蛋", 1);
        order1.getFoodDic().put("罐装可乐", 2);

        Order order2 = new Order();
        order2.setDiningTable(2);
        order2.getFoodDic().put("酸溜土豆丝", 1);
        order2.getFoodDic().put("王老吉", 1);

        //创建接受者
        SeniorChef receiver = new SeniorChef();
        //将订单和接受者封装成命令对象
        OrderCommand cmd1 = new OrderCommand(receiver, order1);
        OrderCommand cmd2 = new OrderCommand(receiver, order2);
        //创建调用者 waitor
        Waitor invoke = new Waitor();
        invoke.setCommands(cmd1);
        invoke.setCommands(cmd2);

        //将订单给柜台 呼叫厨师
        invoke.orderUp();


    }
}