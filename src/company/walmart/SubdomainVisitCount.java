package company.walmart;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Leetcode 811. Subdomain Visit Count  https://leetcode.com/problems/subdomain-visit-count/
 */
public class SubdomainVisitCount {

    public List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String cpdomain : cpdomains){
            String count = cpdomain.split(" ")[0];
            String domain = cpdomain.split(" ")[1];
            addToMap(map, domain, Integer.valueOf(count));

            for (int i = 0; i < cpdomain.length(); i++){
                if (cpdomain.charAt(i) == '.'){
                    String subdomain = cpdomain.substring(i+1);
                    addToMap(map, subdomain, Integer.valueOf(count));
                }
            }
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            StringBuilder sb = new StringBuilder();

            sb.append(entry.getValue());
            sb.append(" ");
            sb.append(entry.getKey());
            result.add(sb.toString());
        }
        return result;
    }

    public static void addToMap(Map<String, Integer> map, String domain, Integer count){
//        if(map.containsKey(domain)){
//            int curCount =  map.get(domain) + count;
//            map.put(domain, curCount);
//        } else {
//            map.put(domain, count);
//        }
        // above line between 43- 48 also works. 和map.merge 效果一样
        map.merge(domain, count, Integer::sum);
    }
}
