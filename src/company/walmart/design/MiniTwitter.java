package company.walmart.design;

import java.util.*;

// Definition of Tweet:
class Tweet {
	public int id;
	public int user_id;
	public String text;

	public static Tweet create(int user_id, String tweet_text) {
		// This will create a new tweet object,
		// and auto fill id
		return null;
	}
}

class Node {
	public int order;
	public Tweet tweet;

	public Node(int o, Tweet t) {
		this.order = o;
		this.tweet = t;
	}
}

class SortByOrder implements Comparator<Object> {
	public int compare(Object obj1, Object obj2) {
		Node node1 = (Node) obj1;
		Node node2 = (Node) obj2;
		// 时间的倒叙排列
		if (node1.order < node2.order)
			return 1;
		else
			return -1;
	}
}

public class MiniTwitter {
	private Map<Integer, Set<Integer>> friends;
	private Map<Integer, List<Node>> users_tweets;
	private int order;

	public MiniTwitter() {
		this.friends = new HashMap<Integer, Set<Integer>>();
		this.users_tweets = new HashMap<Integer, List<Node>>();
		this.order = 0;
	}

	public List<Node> getLastTen(List<Node> tmp) {
		int last = 10;
		if (tmp.size() < 10)
			last = tmp.size();
		return tmp.subList(tmp.size() - last, tmp.size());
	}

	public List<Node> getFirstTen(List<Node> tmp) {
		int last = 10;
		if (tmp.size() < 10)
			last = tmp.size();
		return tmp.subList(0, last);
	}

	public Tweet postTweet(int user_id, String tweet_text) {
		Tweet tweet = Tweet.create(user_id, tweet_text);
		if (!users_tweets.containsKey(user_id))
			users_tweets.put(user_id, new ArrayList<Node>());
		order += 1;
		users_tweets.get(user_id).add(new Node(order, tweet));
		return tweet;
	}

	// @param user_id an integer
	// return a list of 10 new feeds recently
	// and sort by timeline
	public List<Tweet> getNewsFeed(int user_id) {
		List<Node> tmp = new ArrayList<Node>();
		if (users_tweets.containsKey(user_id))
			tmp.addAll(getLastTen(users_tweets.get(user_id)));

		if (friends.containsKey(user_id)) {
			for (Integer user : friends.get(user_id)) {
				if (users_tweets.containsKey(user))
					tmp.addAll(getLastTen(users_tweets.get(user)));
			}
		}

		Collections.sort(tmp, new SortByOrder());
		List<Tweet> rt = new ArrayList<Tweet>();
		tmp = getFirstTen(tmp);
		for (Node node : tmp) {
			rt.add(node.tweet);
		}
		return rt;
	}

	// return a list of 10 new posts recently
	// and sort by timeline
	public List<Tweet> getTimeline(int user_id) {
		// Write your code here
		List<Node> tmp = new ArrayList<Node>();
		if (users_tweets.containsKey(user_id))
			tmp.addAll(getLastTen(users_tweets.get(user_id)));

		Collections.sort(tmp, new SortByOrder());
		List<Tweet> rt = new ArrayList<Tweet>();
		tmp = getFirstTen(tmp);
		for (Node node : tmp)
			rt.add(node.tweet);
		return rt;
	}

	public void follow(int from_user_id, int to_user_id) {
		if (!friends.containsKey(from_user_id))
			friends.put(from_user_id, new HashSet<Integer>());

		friends.get(from_user_id).add(to_user_id);
	}

	public void unfollow(int from_user_id, int to_user_id) {
		// Write your code here
		if (friends.containsKey(from_user_id))
			friends.get(from_user_id).remove(to_user_id);
	}
}