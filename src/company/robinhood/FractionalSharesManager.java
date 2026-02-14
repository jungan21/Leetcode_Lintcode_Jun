package company.robinhoods;

import java.util.*;

public class FractionalSharesManager {
    private final Map<String, Integer> stocks = new HashMap<>();
    private final List<String> logs = new ArrayList<>();

    public FractionalSharesManager(String[] initialInventories) {
        for (String inv : initialInventories) {
            // "AAPL/30"
            String[] parts = inv.split("/");
            stocks.put(parts[0], Integer.parseInt(parts[1]));
        }
    }

    // quantityOrValue $42, price 100
    public void processOrder(String symbol, String side, String quantityOrValue, int price) {
        int quantity = parseQuantity(quantityOrValue, price);
        if ("B".equals(side)) handleBuy(symbol, quantity);
        else handleSell(symbol, quantity);
    }

    private void handleBuy(String symbol, int qty) {
        int currentStock = stocks.getOrDefault(symbol, 0);
        if (currentStock < qty) {  // 30 < 42
            // 假设库存currentStock = 30
            // if qty=42, need = 100
            // if qty=150, need = 200   ****/100 + 1需要向上round up, 再* 100
            int need = ((qty - currentStock - 1) / 100 + 1) * 100;
            stocks.put(symbol, currentStock + need);
            //logs.add(String.format("%s/%d/B/MARKET", symbol, need));
            logs.add(symbol + "/" + need + "/" + "B" + "/"+ "MARKET");
        }
        // 库存30 + 从市场买入的100，让后扣掉客户买的42 = 88 库存
        stocks.put(symbol, stocks.get(symbol) - qty); //库存充足情况下 ，直接将库存量减去客户购买量
        logs.add(String.format("%s/%d/S/CUSTOMER", symbol, qty));
    }

    private void handleSell(String symbol, int qty) {
        int total = stocks.getOrDefault(symbol, 0) + qty;
        logs.add(String.format("%s/%d/B/CUSTOMER", symbol, qty));

        // 如果某只股票的库存累计达到了 100 股或更多，系统会自动将“整百”的部分卖回给市场（MARKET）
        //  if total = 110, 需要卖100给市场
        //  if total = 230, 需要卖200给市场
        if (total >= 100) {
            int sellToMarket = (total / 100) * 100;
            total -= sellToMarket;
            logs.add(String.format("%s/%d/S/MARKET", symbol, sellToMarket));
        }
        stocks.put(symbol, total);
    }

    //主要作用是将订单中的购买单位（无论是直接的“股数”还是“金额”）统一转换为系统内部可以处理的股数（整数）。
    private int parseQuantity(String input, int price) {
        if (input.startsWith("$")) {
            // 逻辑: 金额 * 100 / 价格
            return Integer.parseInt(input.substring(1)) * 100 / price; // 42 * 100 /100 = 42股
        }
        return Integer.parseInt(input);
    }

    public List<String> getFinalInventory(String[] originalSymbols) {
        List<String> result = new ArrayList<>();
        for (String inv : originalSymbols) {
            String symbol = inv.split("/")[0];
            result.add(symbol + "/" + stocks.getOrDefault(symbol, 0));
        }
        return result;
    }

    public List<String> getLogs() {
        return logs;
    }


    public static void main(String[] args) {
        // 1. 模拟原始输入数据
        // 订单格式: 股票代码/动作(B=买, S=卖)/数量或金额/价格
        // symbol/side/quantityORvalue/Price
        String[] orders = {
                "AAPL/B/$42/100", // 买入价值$42的AAPL，价格为100 (应计算为42股)
                "AAPL/S/10/100",  // 客户卖出10股AAPL
                "AAPL/S/10/100"
                // "TSLA/B/150/200"  // 买入150股TSLA (如果库存不足，系统将向市场购买)
        };

        // 初始库存格式: 股票代码/数量
        String[] inventories = {"AAPL/30"};

        // 2. 初始化管理器并执行逻辑
        FractionalSharesManager manager = new FractionalSharesManager(inventories);

        System.out.println("--- 执行交易流程 ---");
        for (String order : orders) {
            String[] parts = order.split("/");
            String symbol = parts[0];
            String side = parts[1];
            String quantityOrValue = parts[2];
            int price = Integer.parseInt(parts[3]);

            System.out.println("处理订单: " + order);
            manager.processOrder(symbol, side, quantityOrValue, price);
        }

        // 3. 输出结果
        System.out.println("\n--- 最终库存状态 ---");
        System.out.println(manager.getFinalInventory(inventories));

        System.out.println("\n--- 系统操作日志 (Audit Logs) ---");
        manager.getLogs().forEach(System.out::println);

    }


}