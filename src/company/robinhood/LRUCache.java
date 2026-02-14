package company.robinhoods;

import java.util.*;

class Node {
    Node prev;
    Node next;
    int key;
    int value;

    Node(int key, int value) {
        this.key = key;
        this.value = value;

    }
}


public class LRUCache {
    int capacity = 3;

    private Map<Integer, Node> map = new HashMap<>();

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }


    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }

        Node current = map.get(key);

        // remove curent;

        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current to the tail

        move_to_tail(current);
        return map.get(key).value;
    }

    public void set(int key, int value){
        if (get(key) != -1){
            map.get(key).value = value;
            return;
        }

        if(map.size() == capacity) {
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        map.put(key, insert);
        move_to_tail(insert);
    }

    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;

        current.prev.next = current;
        current.next = tail;

    }

}




