package company.robinhoods;


import java.util.*;
import java.util.stream.Collectors;

/**
 * 总的 O(NlogN)
 * 
 * build Graph:  O(E) E是图的边。 
 * DFS找reachable:  O(V+E), 每个节点和边仅visit 1次
 * 计算Indegree： O(V+E)
 * TopSort:  O(V+E) 仅访问一次
 * Collection.sort: NlogN
 */
public class LoadFactorSystem {

    Map<String, List<String>> adjListGraph = new HashMap<>();
   // Set<String> parentServiceNodesSet = new HashSet<>();
    Set<String> reachableServiceNodesSet = new HashSet<>();
    Map<String, Integer> reachableNodesinDegree = new HashMap<>();

    Map<String, Integer> loadsResultsMap = new HashMap<>();

    public List<String> calculate(String[] dependencies, String entrypoint) {

        // 1. Build Graph:  input: {"A=B|C", "B=C", "C=D", "E=C"}; ==> Graph:{A=[B, C], B=[C], C=[D], E=[C]}
        buildGraph(dependencies);
        System.out.println("Graph:" + adjListGraph);

        // 2. DFS 寻找可达节点，排除孤立服务 ==> ReachableServiceNodes: [A, B, C, D]
        findReachableServiceNodes(entrypoint);
        if (!reachableServiceNodesSet.contains(entrypoint)) return Collections.emptyList();
        System.out.println("ReachableServiceNodes:" + reachableServiceNodesSet);

        // 3. 计算每个reachabled可达节点的入度 InDegree, ReachableServiceNodes:[A, B, C, D]
        calculateIndegreeforAllReachableNode();
        // inDegrees{B=1, C=2, D=1}
        System.out.println("inDegrees" + reachableNodesinDegree);

        // 4. 拓扑排序计算流量
        topSort(entrypoint);

        // 5. 格式化输出
//        return loadsResultsMap.keySet().stream()
//                .sorted()
//                .map(name -> name + "*" + loadsResultsMap.get(name))
//                .collect(Collectors.toList());

        List<String> finaResults = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : loadsResultsMap.entrySet()){
            finaResults.add(entry.getKey() + "*" + entry.getValue());
        }
        Collections.sort(finaResults);
        return finaResults;


    }

    private void buildGraph(String[] dependencies) {
        for (String dep : dependencies) {
            String[] parts = dep.split("=");
            if (parts.length < 1) continue;
            String parent = parts[0];
            //parentServiceNodesSet.add(parent);

            if (parts.length == 2 && !parts[1].isEmpty()) {
                // Arrays.asList(String [])
                adjListGraph.put(parent, Arrays.asList(parts[1].split("\\|")));
            }
        }
    }

    // DFS 判断是否reachable
    private void findReachableServiceNodes(String node) {
        //往Set添加失败，表示已经visisted过了
        // 递归出站. 判断不能省略， 否则结果不对.  已经存在了，直接return，否则报错
        if (!reachableServiceNodesSet.add(node)) {
            return;
        };
        for (String next : adjListGraph.getOrDefault(node, Collections.emptyList())) {
            findReachableServiceNodes(next);
        }
    }

    // calculate indegree for each reachabled nodes
    private void calculateIndegreeforAllReachableNode(){
        for (String node : reachableServiceNodesSet) {
            for (String neighbor : adjListGraph.getOrDefault(node, Collections.emptyList())) {
                // 重要的判断，确保children节点也是reachable的
                if (!reachableServiceNodesSet.contains(neighbor)) return;

                reachableNodesinDegree.put(neighbor, reachableNodesinDegree.getOrDefault(neighbor, 0) + 1);
//                if (reachableNodesinDegree.containsKey(neighbor)){
//                    int temp = reachableNodesinDegree.get(neighbor);
//                    reachableNodesinDegree.put(neighbor, temp+1);
//                } else {
//                    reachableNodesinDegree.put(neighbor, 1);
//                }


            }
        }
    }

    // topsort
    private void topSort(String entrypoint){
        Queue<String> queue = new LinkedList<>();
        queue.add(entrypoint);
        loadsResultsMap.put(entrypoint, 1); // entrypoint load=1

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currLoad = loadsResultsMap.get(curr);

            for (String next : adjListGraph.getOrDefault(curr, Collections.emptyList())) {
                if (!reachableServiceNodesSet.contains(next)) continue;

                loadsResultsMap.put(next, loadsResultsMap.getOrDefault(next, 0) + currLoad);
                // 计算过一次load, 就把相应的inDegree -1 更新
                reachableNodesinDegree.put(next, reachableNodesinDegree.get(next) - 1);
                if (reachableNodesinDegree.get(next) == 0) { // 拓扑排序，只有inDegree为0，才进queue
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        LoadFactorSystem calculator = new LoadFactorSystem();
        // A depend on (B and C)
       // List<String> deps = Arrays.asList("A=B|C", "B=C", "C=D", "E=C");

        String[] deps = {"A=B|C", "B=C", "C=D", "E=C"};
        try {
            List<String> result = calculator.calculate(deps, "A");
            System.out.println(result); // 输出: [A*1, B*1, C*2, D*2]
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
