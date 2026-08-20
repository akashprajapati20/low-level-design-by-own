package org.lld;

public class Node {
    int key;
    int val;
    Node next;
    Node prev;

    public Node( int key,int val) {
        this.val = val;
        this.key = key;
        next=null;
        prev=null;
    }
}
