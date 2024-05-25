package com.service.routing.util;

public class LinkedList {
	
	public LinkedNode head;

	public LinkedList() {
		this.head = null;
	}

	public void addToHead(Vertex data) {
		LinkedNode newHead = new LinkedNode(data);
        LinkedNode currentHead = this.head;
        this.head = newHead;
        if (currentHead != null) {
          this.head.setNextNode(currentHead);
        }
	}

    public void addToTail(Vertex data) {
        LinkedNode tail = this.head;
        if (tail == null) {
          this.head = new LinkedNode(data);
        } else {
          while (tail.getNextNode() != null) {
            tail = tail.getNextNode();
          }
          tail.setNextNode(new LinkedNode(data));
        }
    }

    public Vertex removeHead() {
        LinkedNode removedHead = this.head;
        if (removedHead == null) {
          return null;
        }
        this.head = removedHead.getNextNode();
        return removedHead.data;
    }
}