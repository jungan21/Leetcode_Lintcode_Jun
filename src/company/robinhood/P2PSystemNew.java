package company.robinhoodsprint;

import java.util.*;
import java.util.stream.Collectors;

class UserAccount {
    int userId; int balance;
    public UserAccount(int userId, int balance) {   this.userId = userId; this.balance = balance; }
    public int getBalance() { return balance; }
    public void add(int amount){ this.balance += amount;}
}

class FriendRequest {
    int fromUserId; int toUserId;

    public FriendRequest(int fromUserId, int toUserId) {   this.fromUserId = fromUserId; this.toUserId = toUserId; }

    public int getFromUserId() { return fromUserId; }
    public int getToUserId() { return toUserId; }
}
class FriendshipManager {
    Map<String, FriendRequest> friendRequests = new HashMap<>();
    Map<Integer, Set<Integer>> friendshipGraph = new HashMap<>();
    public void addRequest(String reqId, int from, int to) { friendRequests.putIfAbsent(reqId, new FriendRequest(from, to));}
    public boolean areFriends(int u1, int u2) { return friendshipGraph.getOrDefault(u1, Collections.emptySet()).contains(u2);}

    public void acceptRequest(String reqId) {
        FriendRequest req = friendRequests.remove(reqId); // 处理完后移除或标记状态
        if (req != null) {
            Set<Integer> fromSet = friendshipGraph.get(req.getFromUserId()); //不能用getDefault(Collections.emptySet())
            Set<Integer> toSet = friendshipGraph.get(req.getToUserId());     //  Collections.emptySet()返回的Set不能改变i.e. add

            if(fromSet == null) fromSet = new HashSet<>(); if(toSet == null) toSet = new HashSet<>();
            if(!fromSet.contains(req.getToUserId())) fromSet.add(req.getToUserId());
            if(!toSet.contains(req.getFromUserId())) fromSet.add(req.getFromUserId());
            friendshipGraph.put(req.getFromUserId(), fromSet); friendshipGraph.put(req.getToUserId(), toSet); //重要

            // !!!！！！ 上面可合并到下面两句 ！！！！！！
            //friendshipGraph.computeIfAbsent(req.getFromUserId(), k -> new HashSet<>()).add(req.getToUserId());
            //friendshipGraph.computeIfAbsent(req.getToUserId(), k -> new HashSet<>()).add(req.getFromUserId());
        }
    }
}
// UserAccount(id, balance)| FriendRequest(from, to)| FriendshipManager(FriendshipManagerMap, friendshipMap<id, Set>)
// switch (requestType){ case "SIGNUP": }
public class P2PSystemNew {
    Map<Integer, UserAccount> users = new HashMap<>();
    FriendshipManager friendshipManager = new FriendshipManager();
    public void signUp(int userId, int balance) {users.putIfAbsent(userId, new UserAccount(userId, balance));}
    public void handleFriendRequest(String reqId, int from, int to) {friendshipManager.addRequest(reqId, from, to);}
    public void handleFriendAccept(String reqId) {friendshipManager.acceptRequest(reqId);}
    public void sendMoney(int from, int to, int amount) {
        UserAccount fromAcc = users.get(from); UserAccount toAcc = users.get(to);
        if(!friendshipManager.areFriends(from, to)) return;
        if (fromAcc.getBalance() >= amount) { fromAcc.add(-amount); toAcc.add(amount);}
    }

    public String getStatusReport() {
        return users.keySet().stream()
                .sorted()
                .map(id -> id + ":" + users.get(id).getBalance())
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        String[] requests = {
                "0/SIGNUP/0/100",  // 0: $100
                "1/SIGNUP/1/200",  // 1: $200
                "2/SIGNUP/10/1000", // 10: $1000
                "3/SEND_MONEY/0/1/10", // 0 -> 1号 Send money $10.   0: $100, 1: $200  失败
                "4/FRIEND_REQUEST/0/1", // 加好友
                "5/SEND_MONEY/1/0/10", //  1 -> 0号 Send money $10.   0: $100, 1: $200 失败
                "6/FRIEND_ACCEPT/4",  // 这里4表示的是第几个reqeust, 原始requestId
                "7/SEND_MONEY/0/1/10", //  0 -> 1号 Send money $10.   0: $90, 1: $210 成功
                 "8/SEND_MONEY/1/0/211"
        };

        P2PSystemNew system = new P2PSystemNew();
        for (String r : requests) {
            String[] parts = r.split("/");
            String requestType = parts[1]; // String type
            // 重要： Request Id 保持String,  Request Type String, 其余都是Integer
            switch (requestType) {
                case "SIGNUP": // "0/SIGNUP/0/100"
                    system.signUp(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    break;
                case "FRIEND_REQUEST": // "4/FRIEND_REQUEST/0/1"
                    system.handleFriendRequest(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                    break;
                case "FRIEND_ACCEPT": // "6/FRIEND_ACCEPT/4"， 这里4表示的是第几个reqeust, 原始requestId
                    system.handleFriendAccept(parts[2]);
                    break;
                case "SEND_MONEY": // "8/SEND_MONEY/1/0/211"
                    system.sendMoney(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                    break;
            }
        }
        System.out.println(system.getStatusReport());
    }
}
