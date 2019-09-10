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
    static int maxDepth;

    BinaryTree(int val) {
        root = new Node(val);
    }

    BinaryTree() {
        root = null;
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

    public boolean searchElementInBtRecursive(Node rt, int key) {
        if(rt == null) return false;

        if(rt.data == key) return true;

        return searchElementInBtRecursive(rt.left,key) || searchElementInBtRecursive(rt.right,key);
    }

    public void printElementsInReverseLOT(Node rt) {
        if(rt == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(rt);
        Stack<Integer> s = new Stack<>();
        while(!q.isEmpty()){
            Node temp = q.remove();
            if(temp.right != null){
                q.add(temp.right);
            }
            if(temp.left != null){
                q.add(temp.left);
            }
            s.push(temp.data);
        }
        while(!s.isEmpty()){
            System.out.print(s.pop() + " ");
        }
    }

    public void deleteBinaryTree(Node rt) {
        
        if(rt == null) return;
            
        deleteBinaryTree(rt.left);
        deleteBinaryTree(rt.right);

        rt = null;

    }

    public void deepestNodeInBt(Node rt, int currDepth) {
        if(rt == null) return;

        currDepth ++;
        if(currDepth > maxDepth)
            maxDepth = currDepth;

        deepestNodeInBt(rt.left, currDepth);
        deepestNodeInBt(rt.right, currDepth);
    }

    public static void main(String args[]) {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new Node(5);
        bt.root.right = new Node(12);
        bt.root.left.left = new Node(-13);
        bt.root.left.right = new Node(50);
        bt.root.right.right = new Node(100);
        bt.root.right.right.left = new Node(200);
        System.out.println("Root node ==> "+ bt.root.data);
        System.out.println("Left node ==> "+ bt.root.left.data);
        System.out.println("Right node ==> "+ bt.root.right.data);
        System.out.println("Maximum in Binary Tree => " + bt.findMaxBtRecursive(bt.root));
        bt.levelOrderTraversal(bt.root);
        System.out.println("");
        System.out.println("Is the value "+ 12 + " present in this Binary Tree ? "+ bt.searchElementInBtRecursive(bt.root, 12));
        System.out.println("Binary tree elements printed in reverse order "); 
        bt.printElementsInReverseLOT(bt.root);
        System.out.println(" ");
        bt.deepestNodeInBt(bt.root,0);
        System.out.println("Maximum depth of Binary Tree "+ maxDepth);
    }

}