package company.robinhoods;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 账户类：封装用户资产与社交关系
 */
class UserAccount {
    private final int userId;
    private int balance;
    private final Set<Integer> friends;

    public UserAccount(int userId, int balance) {
        this.userId = userId;
        this.balance = balance;
        this.friends = new HashSet<>();
    }

    public int getUserId() { return userId; }
    public int getBalance() { return balance; }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void addFriend(int friendId) {
        this.friends.add(friendId);
    }

    public boolean isFriendWith(int friendId) {
        return this.friends.contains(friendId);
    }

    public boolean hasEnoughBalance(int amount) {
        return this.balance >= amount;
    }
}

class FriendRequest {
    private final int fromUserId;
    private final int toUserId;

    public FriendRequest(int fromUserId, int toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public int getFromUserId() { return fromUserId; }
    public int getToUserId() { return toUserId; }
}

public class P2PSystem {
    private final Map<Integer, UserAccount> userAccountsMap = new HashMap<>();
    private final Map<String, FriendRequest> pendingFriendRequestsMap = new HashMap<>();

    // SIGNUP: 注册新用户
    public void signUp(int userId, int balance) {
        userAccountsMap.putIfAbsent(userId, new UserAccount(userId, balance));
    }

    public void friendRequest(String requestId, int from, int to) {
        if (!userAccountsMap.containsKey(from) || !userAccountsMap.containsKey(to)) return;
        if (pendingFriendRequestsMap.containsKey(requestId)) return;

        UserAccount fromAcc = userAccountsMap.get(from);
        if (fromAcc.isFriendWith(to)) return;

        pendingFriendRequestsMap.put(requestId, new FriendRequest(from, to));
    }

    public void friendAccept(String requestId) {
        //重点
        FriendRequest req = pendingFriendRequestsMap.remove(requestId);
        if (req != null) {
            userAccountsMap.get(req.getFromUserId()).addFriend(req.getToUserId());
            userAccountsMap.get(req.getToUserId()).addFriend(req.getFromUserId());
        }
    }

    // SEND_MONEY: 转账逻辑
    public void sendMoney(int from, int to, int amount) {
        UserAccount fromAcc = userAccountsMap.get(from);
        UserAccount toAcc = userAccountsMap.get(to);

        // 校验逻辑
        if (fromAcc == null || toAcc == null) return;
        if (!fromAcc.isFriendWith(to) || !toAcc.isFriendWith(from)) return;
        if (!fromAcc.hasEnoughBalance(amount)) return;

        // 执行转账
        fromAcc.addBalance(-amount);
        toAcc.addBalance(amount);
    }

    // 获取所有账户余额报告

    public String getStatusReport() {

        List<Integer> allUserIDs = new ArrayList<>(userAccountsMap.keySet());
        Collections.sort(allUserIDs);

        List<String> results = new ArrayList<>();

        for (Integer userId : allUserIDs){
            results.add(userId + ":" + userAccountsMap.get(userId).getBalance());
        }
        return String.join("," , results);

    }


    public static void main(String[] args) {
        String[] requests = {
                "0/SIGNUP/0/100",  // 0号user注册$100
                "1/SIGNUP/1/200",
                "2/SIGNUP/10/1000", // 10号user注册$1000
                "3/SEND_MONEY/0/1/10", // 0号user -> 1号user send money $10
                "4/FRIEND_REQUEST/0/1", // 0号user -> 1号user send Friend Request
                "5/SEND_MONEY/1/0/10",
                "6/FRIEND_ACCEPT/4",  // 这里4表示的是第几个reqeust, 原始requestId
                "7/SEND_MONEY/0/1/10",
                "8/SEND_MONEY/1/0/211"
        };

        P2PSystem system = new P2PSystem();

        for (String r : requests) {
            String[] parts = r.split("/");
            String requestType = parts[1]; // String type
            try {
                switch (requestType) {
                    case "SIGNUP": // "0/SIGNUP/0/100"
                        system.signUp(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                        break;
                    case "FRIEND_REQUEST": // "4/FRIEND_REQUEST/0/1"
                        system.friendRequest(parts[0], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                        break;
                    case "FRIEND_ACCEPT": // "6/FRIEND_ACCEPT/4"， 这里4表示的是第几个reqeust, 原始requestId
                        system.friendAccept(parts[2]);
                        break;
                    case "SEND_MONEY": // "8/SEND_MONEY/1/0/211"
                       system.sendMoney(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                        break;
                }
            } catch (Exception ignored) {} // 生产环境应处理解析异常
        }

        System.out.println(system.getStatusReport());
    }
}