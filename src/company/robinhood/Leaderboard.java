package company.robinhoods;

import java.util.*;

/**
 * inpupt: 给定两个长度相等的字符串数组 referrers 和 new_users。referrers[i] 表示推荐人，new_users[i] 表示被推荐人。
 * 推荐关系可以传递（例如：A 推荐了 B，B 推荐了 C，则 A 的总推荐数计为 2）。
 *
 * output:  编写一个函数，返回一个排行榜。排行榜格式为 ["用户名:总数"]。
 * 按总推荐数从高到低排序；如果计数相同，则按用户名升序排序。
 *
 * Memory Search 重要：
 *  无记忆化搜索： （如 A->B -> C -> D）中，如果不使用记忆化，当计算 A 的推荐数时会遍历一次 BCD，
 *  而计算 B 的推荐数时又会重复遍历 CD。在最坏情况下（例如推荐关系构成一个长链或高度重叠的树状结构），复杂度可能达到 O(2^V) 或更高。
 *  这是因为同一个子树会被其所有的祖先节点重复触发计算。
 *
 *  有记忆化搜索：O(V + E) 使用 memo HashMap记录已计算的结果，可以将重复计算的时间复杂度从指数级降低到线性级。
 *  每个节点的状态一旦计算出来就会被存入缓存，后续遇到该节点时直接返回结果，确保每条边和每个节点只被访问一次
 *
 *   v node(用户数) E edge（推荐关系数）
 *   time: O(VlogV+E)  [图O(E), DFS有记忆化搜索每个节点和边仅便利一次O（V+E）， 排序O(VlogV)]
 *   space: O(2V+4E) = O(V+E)
 *
 */


class Referral {
    String user;
    int count;

    Referral(String user, int count) {
        this.user = user;
        this.count = count;
    }
}
public class Leaderboard {

    Map<String, List<String>> graph = new HashMap<>();
    Set<String> allRefUser = new HashSet<>();
    // for 记忆化搜索缓存：存储每个用户及其对应的总推荐数（包含间接推荐）
    Map<String, Integer> referralCountResultMemory = new HashMap<>();

    public List<String> leaderboard(List<String> ref_users, List<String> new_users) {
        if (ref_users.size() != new_users.size()) {
            return new ArrayList<>();
        }
        // Build Graph adjcent List:
        for (int i = 0; i < ref_users.size(); i++) {
            String ref_user = ref_users.get(i);
            String new_user = new_users.get(i);
            graph.putIfAbsent(ref_user, new ArrayList<>());
            graph.get(ref_user).add(new_user);

            allRefUser.add(ref_user);
        }

        System.out.println(graph);


        // DFS allRefUser to count
        List<Referral> tempResultList = new ArrayList<>();
        for (String user : allRefUser) {
            int count = dfs(user);
            if (count > 0) {
                tempResultList.add(new Referral(user, count));
            }
        }

        // 排序：按count降序，count相同时按user升序
        Collections.sort(tempResultList, (a, b) -> {
            if(a.count == b.count){
                return a.user.compareTo(b.user);
            }
            return b.count - a.count;
        });

        List<String> results = new ArrayList<>();
        for (Referral r : tempResultList) {
            results.add(r.user + ":" + r.count);
        }
        System.out.print(referralCountResultMemory);
        return results;
    }

    public int dfs(String node){
        // 重点：如果已经计算过，直接返回缓存结果
        if (referralCountResultMemory.containsKey(node)){
            return referralCountResultMemory.get(node);
        }

        int count = 0;
        if (graph.containsKey(node)){
            for(String child :graph.get(node)){
                count = count + 1 + dfs(child);
            }
        }
        //存入缓存记忆
        referralCountResultMemory.put(node, count);
        return count;
    }

//    给定两个长度相等的字符串数组 referrers 和 new_users。referrers[i] 表示推荐人，new_users[i]
//    表示被推荐人。推荐关系可以传递（例如：A 推荐了 B，B 推荐了 C，则 A 的总推荐数计为 2）。
    public static void main(String[] args) {
        List<String> rh_users = Arrays.asList("A", "B", "C");
        List<String> new_users = Arrays.asList("B", "C", "D");

        Leaderboard board = new Leaderboard();

        List<String> ans = board.leaderboard(rh_users, new_users);

        System.out.println(ans);

    }

}
