package company.robinhoods;

import heap.TopKFrequentWords;

import java.util.*;

/**
 * 解题思路该问题的核心是维护两个积压队列（Backlog）：
 * 采购订单（Buy Orders）积压队列：我们需要价格最高的买单先被撮合，因此使用大顶堆（Max-Heap）。
 * <p>
 * 销售订单（Sell Orders）积压队列：我们需要价格最低的卖单先被撮合，因此使用小顶堆（Min-Heap）。
 * <p>
 * 撮合规则：
 * 新来一个买单 (Buy)：看积压的卖单里有没有价格 <= 当前买单价的。如果有，消耗掉它们；如果最后买单还有剩余，入买单积压队列。
 * 新来一个卖单 (Sell)：看积压的买单里有没有价格 >= 当前卖单价的。如果有，消耗掉它们；如果最后卖单还有剩余，入卖单积压队列。
 */


class HeapBacklogOrders {

    public static void main(String[] args) {
        int[][] orders = {{7, 1000000000, 1}, {15, 3, 0}, {5, 999999995, 0}, {5, 1, 1}};

        System.out.println(new HeapBacklogOrders().getNumberOfBacklogOrders(orders));
        System.out.println(1_000_000 / 1000);
    }

    public int getNumberOfBacklogOrders(int[][] orders) {
        // 采购订单积压队列：大顶堆（价格从高到低）
        PriorityQueue<int[]> buyBacklogMaxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        // 销售订单积压队列：小顶堆（价格从低到高）
        PriorityQueue<int[]> sellBacklogMinHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // order 每一行（price, amount, order type）
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int orderType = order[2];

            if (orderType == 0) { // 当前是买单 (Buy)
                // 尝试与积压的卖单撮合：卖单价格 <= 买单价格
                // 新来一个买单 (Buy)：看积压的卖单里有没有价格 <= 当前买单价的。如果有，消耗掉它们；如果最后买单还有剩余，入买单积压队列。
                while (amount > 0 && !sellBacklogMinHeap.isEmpty() && sellBacklogMinHeap.peek()[0] <= price) {
                    int[] sellOrder = sellBacklogMinHeap.poll();
                    int sellAmount = sellOrder[1];
                    if (sellAmount > amount) {
                        sellOrder[1] -= amount;
                        sellBacklogMinHeap.offer(sellOrder);
                        amount = 0;
                    } else {
                        amount -= sellAmount;
                    }
                }
                // 如果还有剩余，Buy order, 还有剩余的钱，入买单积压队列
                if (amount > 0) {
                    buyBacklogMaxHeap.offer(new int[]{price, amount});
                }
            } else { // 当前是卖单 (Sell)
                // 尝试与积压的买单撮合：买单价格 >= 卖单价格
                // 新来一个卖单 (Sell)：看积压的买单里有没有价格 >= 当前卖单价的。如果有，消耗掉它们；如果最后卖单还有剩余，入卖单积压队列。。
                while (amount > 0 && !buyBacklogMaxHeap.isEmpty() && buyBacklogMaxHeap.peek()[0] >= price) {
                    int[] buyOrder = buyBacklogMaxHeap.poll();
                    int buyAmount = buyOrder[1];
                    if (buyAmount > amount) {
                        buyOrder[1] -= amount;
                        buyBacklogMaxHeap.offer(buyOrder);
                        amount = 0;
                    } else {
                        amount -= buyAmount;
                    }
                }
                // 如果还有剩余，入卖单积压队列
                if (amount > 0) {
                    sellBacklogMinHeap.offer(new int[]{price, amount});
                }
            }
        }

        // 计算结果
        long totalAmount = 0;
        int MOD = 1_000_000_007; // 就是表示：1,000,000,007

        while (!buyBacklogMaxHeap.isEmpty()) {
            //System.out.println(buyBacklogMaxHeap.peek()[1]);
            totalAmount = (totalAmount + buyBacklogMaxHeap.poll()[1]) % MOD; // % MOD就是怕整数过大 溢出
        }
        while (!sellBacklogMinHeap.isEmpty()) {
            totalAmount = (totalAmount + sellBacklogMinHeap.poll()[1]) % MOD;
        }

        return (int) totalAmount;
    }

}