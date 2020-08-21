
import java.util.*;

/***
 * LRU cache Implementation - Least recently used cache using java HashMap
 * and Own Doubly linkedList implementation
 * @author VenkataHari Shankar
 */
class LinkedList{

    private Node head, last;

    public Node addAsFirst(String key, String value){
        Node temp = new Node(key, value, head, null);

        if(head==null){
            last = temp;
        }else{
            head.prev = temp;
        }
        head = temp;
        return temp;
    }

    public Node removeLast(){
        Node temp = last;
        last = last.prev;
        last.next = null;
        return temp;
    }

    public boolean remove(Node node){
        if(node.prev != null && node.next!=null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }else if(node.prev !=null){ //Last Node in LL
            node.prev.next = null;
        }else{
            return false;
        }
        return true;
    }

    public Node getHead(){
        return head;
    }

    class Node{
        String key;
        String value;
        Node next;
        Node prev;
        Node(String key, String value, Node next, Node prev){
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}

public class LRUCache{
    private LinkedList list;
    private Map<String,LinkedList.Node> helperMap;
    private int capacity;

    LRUCache(int capacity){
        this.capacity = capacity;
        helperMap= new HashMap<>(capacity);
        list = new LinkedList();
    }

    public void put(String key, String value){

        if(helperMap.containsKey(key)){
            LinkedList.Node node = helperMap.get(key);
            if(list.remove(node)){
                //replacing with new value and adding this node at the starting of List
                LinkedList.Node newNode = list.addAsFirst(node.key, value);
                helperMap.put(key,newNode);
            }
        }else{
            if(helperMap.size()>=capacity){
                //Remove the lastNode in case it reaches the capacity
                LinkedList.Node node = list.removeLast();
                helperMap.remove(node.key);
            }
            LinkedList.Node node = list.addAsFirst(key, value);
            helperMap.put(key, node);

        }
    }

    public String get(String key){
        if(helperMap.containsKey(key)){
            LinkedList.Node node = helperMap.get(key);
            if(list.remove(node)){
                LinkedList.Node newNode = list.addAsFirst(node.key, node.value);
                helperMap.put(key,newNode);
            }
            return node.value;
        }
        return null;
    }

    public void printCache(){
        LinkedList.Node current = list.getHead();
        while(current!=null){
            System.out.print("[ "+current.key+" : "+current.value+" ]");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.put("1","One");
        cache.put("2","Two");
        cache.put("4","Four");
        cache.put("5","Five");
        cache.put("3","Three");
        cache.put("6","Six");
        cache.printCache();

    }

}


