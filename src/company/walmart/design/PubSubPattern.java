package company.walmart.design;

import java.util.HashMap;
import java.util.HashSet;

// Definition of PushNotification
  class PushNotification {
      public static void notify(int user_id, String the_message){

      }
   };


public class PubSubPattern {
    private HashMap<String, HashSet<Integer>> channels;

    public PubSubPattern(){
        channels = new HashMap<String, HashSet<Integer>>();
    }

    public void subscribe(String channel, int user_id) {
        if (!channels.containsKey(channel)) {
            channels.put(channel, new HashSet<Integer>());
        }
        HashSet<Integer> user_ids = channels.get(channel);
        user_ids.add(user_id);
    }


    public void unsubscribe(String channel, int user_id) {
        if (!channels.containsKey(channel)) {
            return;
        }

        HashSet<Integer> user_ids = channels.get(channel);
        user_ids.remove(user_id);
    }

    public void publish(String channel, String message) {
        if (!channels.containsKey(channel)) {
            return;
        }
        for (Integer user_id : channels.get(channel)) {
            PushNotification.notify(user_id, message);
        }
    }
}
