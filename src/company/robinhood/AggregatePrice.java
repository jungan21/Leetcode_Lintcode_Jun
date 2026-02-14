package company.robinhoods;
import java.util.ArrayList;
import java.util.List;

// Tick 最小变动价位/成交变动单位
class Tick {
    int price;
    int ts;  // timestamp

    Tick(int price, int ts) {
        this.price = price;
        this.ts = ts;
    }
}

/**
 *   给定一组包含价格和时间戳的原始数据（如 price:ts），需要按 10 秒为一个窗口进行聚合。
 *
 *   (开始时间, 开盘价, 收盘价, 最高价, 最低价)
 *   时间复杂度: O(T + W)，  T为input tick数据点， W为窗口个数
 */
public class AggregatePrice {

    public static void main(String[] args) {
        AggregatePrice aggregator = new AggregatePrice();
        String input = "1:0,3:10,2:12,4:19,5:35";
        String res = aggregator.aggregatePrice(input);
        System.out.println(res);
    }

    private String aggregatePrice(String input) {
        List<Tick> ticks = parseInput(input);
        if (ticks.isEmpty()) return "";

        int firstTs = ticks.get(0).ts;
        int lastTs = ticks.get(ticks.size() - 1).ts;

        int firstWindowStart = firstTs / 10 * 10;  //0/10*10 = 0
        int lastWindowStart = lastTs / 10 * 10; // 35/10*10 =30

        int index = 0;
        int lastPrice = -1;

        List<String> res = new ArrayList<>();

        //外循环是每10秒的时间window. startAt <= lastWindowStart, 如果写成< 会miss最后一个window
        for (int startAt = firstWindowStart; startAt <= lastWindowStart; startAt += 10) {
            int endAt = startAt + 10;//当前窗口的截止时间点
            boolean initialized = false;

            int open = 0;
            int close = 0;
            int max = 0;
            int min = 0;
            // 该index 一致往前跑，不会往回扫描，所有数据点仅被扫描一次
            while (index < ticks.size() && ticks.get(index).ts < endAt) {
                int price = ticks.get(index).price;

                if (!initialized) {
                    open = price;
                    close = price;
                    max = price;
                    min = price;
                    initialized = true;
                } else {
                    close = price;
                    if (max < price) {
                        max = price;
                    }
                    if (min > price) {
                        min = price;
                    }
                }
                index++;
                lastPrice = price; //很重要， 用于填充没有数据的10秒window.

            }
            /**
             * String input = "1:0,3:10,2:12,4:19,5:35";
             *  基于原始输入，
             *  [0-10秒window:  1:0 1组数据], [10-20秒window:  3:10, 2:12, 4:19 3组数据] , [30-40秒window:  5:35 1组数据]
             *
             *  Note:  [20-30秒window: 没有任何数据， 这样这个window数据可以用第19秒 lastPrice=4填充]， 否则出错都变成0：(20, 0, 0, 0, 0)
             */

            if (initialized) {
                //res.add(String.format("(%d, %d, %d, %d, %d)", startAt, open, close, max, min));
                res.add("(" + startAt + "," +  open + "," + close + "," + max + "," +  min + ")");
            } else  {
                // initialized == false, 表示该window没有数据，譬如第20秒的window没有任何tick
                if(lastPrice != -1) {
                   // res.add(String.format("(%d, %d, %d, %d, %d)", startAt, lastPrice, lastPrice, lastPrice, lastPrice));
                    res.add("(" + startAt + "," +  open + "," + close + "," + max + "," +  min + ")");
                }
            }
        }

        return String.join(",", res);
    }

    // String input = "1:0,3:10,2:12,4:19,5:35";   (2:12, 2 is price, 10 is time 第10秒)
    private  List<Tick> parseInput(String input) {
        List<Tick> res = new ArrayList<>();
        if (input == null || input.isEmpty()) return res;

        String[] parts = input.split(",");

        for (String part : parts) {
            String[] tickParts = part.split(":");
            if (tickParts.length < 2)  continue;

            int price = Integer.parseInt(tickParts[0]);
            int ts = Integer.parseInt(tickParts[1]);
            res.add(new Tick(price, ts));
        }
        return res;
    }

}
