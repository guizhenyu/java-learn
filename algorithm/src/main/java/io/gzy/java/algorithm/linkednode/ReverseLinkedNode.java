package io.gzy.java.algorithm.linkednode;

import java.util.Objects;

/**
 * description: ReverseLinkedNode date: 2021/6/19 3:35 下午
 *
 * @author: guizhenyu
 */
public class ReverseLinkedNode {

  public static LinkedNode reverseNode(LinkedNode head) {

    if (Objects.isNull(head) || Objects.isNull(head.next) ){
      return head;
    }

    LinkedNode reverseNode = null;
    
    while (!Objects.isNull(head)){
      LinkedNode next = head.next;
      head.next = reverseNode;
      reverseNode = head;
      head = next;

    }

    return reverseNode;
    
    
  }

  public static void main(String[] args) {
    LinkedNode linkedNode = new LinkedNode(3);

    LinkedNode node1 = new LinkedNode(4);
    linkedNode.next = node1;
    LinkedNode node2 = new LinkedNode(5);
    node1.next = node2;
    LinkedNode node3 = new LinkedNode(6);
    node2.next = node3;

    LinkedNode node4 = new LinkedNode(12);
    node3.next = node4;

    LinkedNode node5 = new LinkedNode(15);
    node4.next = node5;

    LinkedNode node6 = new LinkedNode(61);
    node5.next = node6;
//    while (linkedNode.next != null){
//      System.out.println(linkedNode.data);
//      linkedNode = linkedNode.next;
//    }

    LinkedNode nodeNew = reverseNode(linkedNode);

    while (nodeNew != null){
      System.out.println(nodeNew.data);
      nodeNew = nodeNew.next;
    }

  }

}


class LinkedNode{

  public int data;

  public LinkedNode next;

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public LinkedNode getNext() {
    return next;
  }

  public void setNext(LinkedNode next) {
    this.next = next;
  }

  public LinkedNode(int data) {
    this.data = data;
  }
}