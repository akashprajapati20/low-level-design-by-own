package org.lld;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    Map<Integer, Node> map = new HashMap<>();
    DoublyLinkedList doublyLinkedList;
    int capacity;
    public LRU(DoublyLinkedList doublyLinkedList,int capacity) {
        this.doublyLinkedList = doublyLinkedList;
        this.capacity=capacity;
    }

    public Node getNode(int key){

        Node node= map.get(key);
        if(node!=null){
            doublyLinkedList.removeNode(node);
            doublyLinkedList.insertNodeAtFirst(node);
        }


       return node;
    }

    public void putNode(Node node){
        if(map.containsKey(node.key)){
            doublyLinkedList.removeNode(map.get(node.key));

        }
        if(doublyLinkedList.length>=capacity){
            Node removedNode=doublyLinkedList.removeLastNode();
            map.remove(removedNode.key);
        }
       map.put(node.key, node);
        doublyLinkedList.insertNodeAtFirst(node);
    }


}
