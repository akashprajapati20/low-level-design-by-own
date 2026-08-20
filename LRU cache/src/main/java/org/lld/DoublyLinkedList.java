package org.lld;

import java.util.List;

public class DoublyLinkedList {

    Node head;
    Node tail;
    int length;

    public DoublyLinkedList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;

        head.next=tail;
        tail.prev=head;
        head.prev=null;
        tail.next=null;
        length=0;
    }

    void insertNodeAtFirst(Node node){

     Node nextNode=head.next;
     head.next=node;
     node.prev=head;
     node.next=nextNode;
     nextNode.prev=node;
        length++;
    }


    void removeNode(Node node){
     Node prevNode=node.prev;
     Node nextNode=node.next;

     prevNode.next=nextNode;
     nextNode.prev=prevNode;
        length--;
    }

    Node removeLastNode(){
        Node removeNode=tail.prev;
        if(length==0)return null;
        if(tail.prev!=head){
            removeNode(removeNode);
            return removeNode;

        }

        return null;
    }

}
