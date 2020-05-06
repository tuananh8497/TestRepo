package SnakeGame;
import java.awt.Color;

public class SnakeLinkedList {
  SnakeNode head = null;

  public void addHead(int x, int y, Color c) {
    head = new SnakeNode(x, y, c);
  }

  public void addJoint(int x, int y, Color c) {
    SnakeNode newJoint = new SnakeNode(x, y, c);
    newJoint.setNext(head.getNext());
    head.setNext(newJoint);
  }

  public void addTail(Color c) {
    SnakeNode curr = head;
    while (curr.getNext() != null) {
      curr = curr.getNext();
    }
    SnakeNode newJoint = new SnakeNode(curr.getX() + 10, curr.getY() + 10, c);
    curr.setNext(newJoint);
  }

  public SnakeNode getHead() {
    return head;
  }

  public SnakeNode getJoint(int i) {
    SnakeNode curr = head;
    for (int k = 0; k < i; k++) {
      curr = curr.getNext();
    }
    return curr;
  }

  public void addFirst(SnakeNode node) {
    node.setNext(head);
    head = node;
  }

  public void addLast(SnakeNode node) {
    if (head == null) {
      head = node;
      return;
    }

    SnakeNode curr = head;
    while (curr.getNext() != null) {
      curr = curr.getNext();
    }
    curr.setNext(node);
  }

  public void snakeMove(int dots, boolean left, boolean right, boolean up, boolean down,
      final int SIZE) {

    for (int z = dots; z > 0; z--) {
      if (z == 0) {
        getHead().getX();
        getHead().getY();
      } else {
        getJoint(z).setX(getJoint(z - 1).getX());
        getJoint(z).setY(getJoint(z - 1).getY());
      }
    }
    // Move the head to left
    if (Board.leftDirection) {
      head.setX(getHead().getX() - SIZE);
    }
    // Move the head to right
    if (Board.rightDirection) {
      head.setX(getHead().getX() + SIZE);
    }
    // Move the head up
    if (Board.upDirection) {
      head.setY(getHead().getY() - SIZE);
    }
    // Move the head down
    if (Board.downDirection) {
      head.setY(getHead().getY() + SIZE);
    }


  }
}
