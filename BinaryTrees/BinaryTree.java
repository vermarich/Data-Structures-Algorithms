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

    public int findMaxBtRecursive(Node rt) {
        if(root.left == null && root.right == null) {
            return root.data;
        }

        int max = rt.data;
        int leftMax = rt.left == null ? rt.data : findMaxBtRecursive(rt.left);
        int rightMax = rt.right == null ? rt.data : findMaxBtRecursive(rt.right);

        if(max<leftMax){
            max = leftMax;
        }
        if(max<rightMax){
            max = rightMax;
        }

        return max;
    }

    public void levelOrderTraversal(Node rt) {
        if(rt == null) return;

        Queue<Node> q = new LinkedList<>();  
        q.add(rt);
        q.add(null);
        while(!q.isEmpty()){
            Node temp = q.remove();
            if(temp == null){
                if(q.size() == 0) return;
                System.out.println("");
                q.add(null);
                continue;
            }
            System.out.print(temp.data + " ");
            if(temp.left != null){
                q.add(temp.left);
            }
            if(temp.right != null){
                q.add(temp.right);
            }
        }    
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new Node(5);
        bt.root.right = new Node(12);
        bt.root.left.left = new Node(-13);
        bt.root.left.right = new Node(50);
        bt.root.right.right = new Node(100);
        System.out.println("Root node ==> "+ bt.root.data);
        System.out.println("Left node ==> "+ bt.root.left.data);
        System.out.println("Right node ==> "+ bt.root.right.data);
        System.out.println("Maximum in Binary Tree => " + bt.findMaxBtRecursive(bt.root));
        bt.levelOrderTraversal(bt.root);
    }

}