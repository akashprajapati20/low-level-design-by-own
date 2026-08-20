package org.lld;

public class Main {
    public static void main(String[] args) {
    Node head=new Node(-1,-1);
    Node tail=new Node(-1,-1);
        DoublyLinkedList doublyLinkedList=new DoublyLinkedList(head,tail);
int maxSize=2;

        LRU lru=new LRU(doublyLinkedList,maxSize);
        Node node1=new Node(1,12);
        Node node2=new Node(2,22);
        Node node3=new Node(3,33);
       lru.putNode(node1);
       lru.putNode(node2);
       lru.putNode(node3);

        Node node=lru.getNode(3);
        if(node==null){
            System.out.println(" the node is null/deleted/do not exist");
        }else{
            System.out.println(" the node i get for key 1: "+ node.val );
        }



    }

}