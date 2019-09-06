import java.io.*;
import java.util.*;

class Node {

    int data;
    Node left, right;

    public Node(int val) {
        data = val;
        left = right = null;
    }

}

class BinaryTree {
    Node root;

    BinaryTree(int val) {
        root = new Node(val);
    }

    BinaryTree() {
        root = null;
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new Node(5);
        bt.root.right = new Node(12);

        // bt.setLeft(5);
        // bt.setRight(12);
        System.out.println("Root node ==> "+ bt.root.data);
        System.out.println("Left node ==> "+ bt.root.left.data);
        System.out.println("Right node ==> "+ bt.root.right.data);
    }

}