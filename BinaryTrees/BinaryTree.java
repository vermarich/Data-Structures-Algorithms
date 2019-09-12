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
    static int maxDepth, diameter, preOrderIndex = 0; 
    static Node lca = null;

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

    public boolean findIfTwoBinaryTreesAreStructurallyIdentical(Node rt1, Node rt2) {
        if(rt1 == null && rt2 == null) {
            return true;
        }

        if(rt1 == null || rt2 == null) {
            return false;
        }

        if(rt1.data == rt2.data){
            return findIfTwoBinaryTreesAreStructurallyIdentical(rt1.left,rt2.left) && findIfTwoBinaryTreesAreStructurallyIdentical(rt1.right,rt2.right);
        }else{
            return false;
        }

    }

    public int findDiameterOfBinaryTree(Node rt) {
        if(rt == null) return 0;

        int tempDia = 0;
        
        int lheight = findDiameterOfBinaryTree(rt.left);
        int rheight = findDiameterOfBinaryTree(rt.right);

        tempDia = lheight + rheight + 1;
        if(tempDia > diameter) {
            diameter = tempDia;
        }

        return lheight>rheight?lheight+1:rheight+1;
        
    }

    public void printAllRootToLeafPaths(Node rt, int a[], int arrLength) {
        if(rt == null) return;

        if (rt.left == null && rt.right == null) {
            for(int i=0;i<arrLength;i++)
                System.out.print(a[i] + " ");
            System.out.print(rt.data);
            System.out.println();
            return;
        }
        a[arrLength] = rt.data;
        arrLength ++;
        printAllRootToLeafPaths(rt.left, a, arrLength);
        printAllRootToLeafPaths(rt.right, a, arrLength);
    }

    public boolean findRootToLeafPathWithAGivenSum(Node rt, int a[], int arrLength, int key) {
        if(rt == null) return false;

        if (rt.left == null && rt.right == null) {
            int sum = 0;
            for(int i=0;i<arrLength;i++)
                sum += a[i];
            sum += rt.data;
            if (sum == key)
                return true;
            else
                return false;
        }
        a[arrLength] = rt.data;
        arrLength ++;
        return findRootToLeafPathWithAGivenSum(rt.left, a, arrLength, key) || findRootToLeafPathWithAGivenSum(rt.right, a, arrLength, key);
    }

    public void convertBinaryTreeToItsMirror(Node rt) {
        if(rt == null) return;

        Node temp = rt.left;
        rt.left = rt.right;
        rt.right = temp;

        convertBinaryTreeToItsMirror(rt.left);
        convertBinaryTreeToItsMirror(rt.right);

    }

    public boolean checkIfTwoTreesAreMirrorsOfEachOther(Node rt1, Node rt2) {
        if(rt1 == null && rt2 == null) return true;
        if(rt1 == null || rt2 == null) return false;

        return checkIfTwoTreesAreMirrorsOfEachOther(rt1.left,rt2.right) && checkIfTwoTreesAreMirrorsOfEachOther(rt1.right,rt2.left);
    }

    /* -------- This function doesn't take care of the condition when only one of the required nodes is present -------*/
    public Node findLowestCommonAncestorsOfTwoNodesInBt1(Node rt, int key1, int key2) { 
        if(rt == null) return null;
        Node left = null, right = null;

        if(rt.data == key1 || rt.data == key2){
           return rt;
        }

        left = findLowestCommonAncestorsOfTwoNodesInBt1(rt.left, key1, key2);
        right = findLowestCommonAncestorsOfTwoNodesInBt1(rt.right, key1, key2);

        if(left != null && right != null)
            return  rt;
        else if(left != null)
            return left;
        else return right;
        
    }

    /* ------ This gives a Null Pointer exception if both nodes are not present in the Binary Tree ------ */
    public Node findLowestCommonAncestorsOfTwoNodesInBt2(Node rt, int key1, int key2) { 
        if(rt == null) return null;
        Node r = null, left = null, right = null;

        if(rt.data == key1 || rt.data == key2){
           r = rt;
        }

        left = findLowestCommonAncestorsOfTwoNodesInBt2(rt.left, key1, key2);
        right = findLowestCommonAncestorsOfTwoNodesInBt2(rt.right, key1, key2);

        if((r != null && left != null) || (r!= null && right!= null))
            lca = r;
        else if((left != null && right != null))
            lca = rt;

        if(r!=null) return r;
        else if(left != null) return left;
        else return right;
    }

    // public Node buildBtFromPreAndInorderTraversal(int preorder[], int inorder[], int s2, int e2) {
    //     System.out.println("preOrderIndex " + preOrderIndex);
    //     System.out.println("s2 "+ s2 + " e2 " + e2);
    //     if(s2 > e2) return null;
        
    //     Node root = new Node(preorder[preOrderIndex++]);

    //     if(s2 == e2) return root;

       
        

    //     int index = -1;
    //     for(int i= s2; i< e2; i++) {
    //         if(inorder[i] == root.data){
    //             index = i;
    //             break;
    //         }
    //     }
    //     root.left = buildBtFromPreAndInorderTraversal(preorder, inorder, s2, index-1);
    //     root.right = buildBtFromPreAndInorderTraversal(preorder, inorder, index+1, e2);

    //     return root;
    // }


    public int sumTree(Node rt) {
        if(rt == null) return 0;

        int old_value = rt.data;

        rt.data = sumTree(rt.left) + sumTree(rt.right);
        return rt.data + old_value;

    }

    /*------ Value of leaf nodes is not changed and nodes can have negative values ------*/
    public Node sumTreeModified(Node rt) {
        if(rt == null) return null;

        int old_value = rt.data;

        Node leftSum = sumTreeModified(rt.left);
        Node rightSum = sumTreeModified(rt.right);

        if(leftSum==null && rightSum==null) {
            return rt;
        }

        if(leftSum!=null && rightSum!=null) {
            rt.data = leftSum.data + rightSum.data;
        }

        if(leftSum == null) {
            rt.data = rightSum.data;
        }

        if(rightSum == null) {
            rt.data = leftSum.data;
        }

        return new Node (rt.data + old_value);
    }

    public void printAllAncestorsOfANode(Node rt, int key, int index, int arr[]) {

        if(rt == null) return;

        arr[index++] = rt.data;

        if(rt.data == key) {
            for(int i=0;i<index;i++)
                System.out.println(arr[i]);
        return;
        }

        printAllAncestorsOfANode(rt.left, key, index, arr);
        printAllAncestorsOfANode(rt.right, key, index, arr);

    }

    public void zigzagTraversalOfBinaryTree(Node rt) {
        if (rt == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(rt);
        q.add(null);
        boolean rightToLeft = false;
        Stack<Integer> s = new Stack<>();

        while(!q.isEmpty()) {
            Node temp = q.remove();
            if(temp != null) {
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
                if(rightToLeft) {
                    s.push(temp.data);
                }else {
                    System.out.print(temp.data + " ");
                }
            }else {
                rightToLeft = !rightToLeft;
                while(!s.isEmpty()){
                    System.out.print(s.pop() + " ");
                }
                if(!q.isEmpty())
                    q.add(null);
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
        bt.root.right.right.left = new Node(200);
        bt.root.right.right.right = new Node(150);
        bt.root.right.right.right.left = new Node(170);
        bt.root.right.right.right.right = new Node(180);
        BinaryTree bt1 = new BinaryTree(10);
        bt1.root.left = new Node(5);
        bt1.root.right = new Node(12);
        bt1.root.left.left = new Node(-13);
        bt1.root.left.right = new Node(50);
        bt1.root.right.right = new Node(100);
        bt1.root.right.right.left = new Node(200);
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
        System.out.println("Are the two trees structurally identical ? " + bt.findIfTwoBinaryTreesAreStructurallyIdentical(bt.root,bt1.root));
        bt.findDiameterOfBinaryTree(bt.root);
        System.out.println("Diameter of the Binary Tree is -> " + diameter);
        System.out.println("All root to leaf paths are :- ");
        int[] arr = new int[100];
        bt.printAllRootToLeafPaths(bt.root, arr, 0);
        System.out.println("Root to leaf path with sum 452 exists ?" + bt.findRootToLeafPathWithAGivenSum(bt.root, arr, 0, 452));
        System.out.println("Converting the Binary Tree to its mirror ");
        bt.convertBinaryTreeToItsMirror(bt.root);
        System.out.println("Level order traversal of mirror of the tree ");
        bt.levelOrderTraversal(bt.root);
        BinaryTree mirrorTree = new BinaryTree(10);
        mirrorTree.root.left = new Node(5);
        mirrorTree.root.right = new Node(12);
        mirrorTree.root.left.left = new Node(-13);
        mirrorTree.root.left.right = new Node(50);
        mirrorTree.root.right.right = new Node(100);
        mirrorTree.root.right.right.left = new Node(200);
        mirrorTree.root.right.right.right = new Node(150);
        mirrorTree.root.right.right.right.left = new Node(170);
        mirrorTree.root.right.right.right.right = new Node(180);
        System.out.println("Level order traversal of mirror of the new tree ");
        bt.levelOrderTraversal(mirrorTree.root);
        System.out.println("Are theses two trees mirrors of each other? "+ bt.checkIfTwoTreesAreMirrorsOfEachOther(bt.root,mirrorTree.root));
        System.out.println("Re-Converting the Binary Tree to its mirror ");
        bt.convertBinaryTreeToItsMirror(bt.root);
        System.out.println("Level order traversal of tree in its original form ");
        bt.levelOrderTraversal(bt.root);
        System.out.println();
        System.out.println("Lowest common ancestors 1 " + (bt.findLowestCommonAncestorsOfTwoNodesInBt1(bt.root, 170, 180)).data);
        bt.findLowestCommonAncestorsOfTwoNodesInBt2(bt.root, 170, 180);
        System.out.println("Lowest common ancestors 2 " + lca.data); //This gives a Null Pointer exception if both nodes are not present in the Binary Tree
        
        // int pre[] = {1,2,3,4,5,6,7};
        // int ino[] = {4,2,5,1,6,3,7};
        // Node newTree = bt.buildBtFromPreAndInorderTraversal2(pre, ino, 0, 7);
        // System.out.println("The new tree built from preorder and inorder is :-");
        // bt.levelOrderTraversal(newTree);

        // BinaryTree bt2 = bt;
        // bt.sumTree(bt2.root);
        // bt.levelOrderTraversal(bt2.root);

        // BinaryTree bt3 = bt;
        // bt.sumTreeModified(bt3.root);
        // bt.levelOrderTraversal(bt3.root);

        int ancestors[] = new int[100];
        bt.printAllAncestorsOfANode(bt.root, 200, 0, ancestors);

        bt.zigzagTraversalOfBinaryTree(bt.root);

    }

}