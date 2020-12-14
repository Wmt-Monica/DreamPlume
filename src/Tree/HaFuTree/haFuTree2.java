package Tree.HaFuTree;

import java.util.*;
import java.util.List;

public class haFuTree2 {
    public static void main(String[] args) {
        Node<Integer> node1 = new Node<>(2,-3);
        Node<Integer> node2 = new Node<>(4,-4);
        Node<Integer> node3 = new Node<>(6,9);
        Node<Integer> node4 = new Node<>(8,0);
        Node<Integer> node5 = new Node<>(-5,6);
        Node<Integer> node6 = new Node<>(4,5);
        Node<Integer> node7 = new Node<>(3,4);
        Node<Integer> node8 = new Node<>(5,3);
        Node<Integer> node9 = new Node<>(9,-1);

        Node<Integer> haFuTree = new Node<>(3,2);

        haFuTree.add(node1);
        haFuTree.add(node2);
        haFuTree.add(node3);
        haFuTree.add(node4);
        haFuTree.add(node5);
        haFuTree.add(node6);
        haFuTree.add(node7);
        haFuTree.add(node8);
        haFuTree.add(node9);

        haFuTree = haFuTree.creatHaFuTree();
//        haFuTree.preorderHaFuTree(haFuTree);
        haFuTree.sequenceHaFuTree(haFuTree);

    }
}

class Node<E> implements Comparable<Node>{  // 创建节点类
    E data;  // 节点的存储值
    int power;  // 节点的权
    Node<E> left;  // 左子节点
    Node<E> right;  // 右子节点
    List<Node> list = new ArrayList<>(); // 创建存放所有节点的 List 集合
    int NodeNums = -1; // 记录总节点数

    // 节点的构造方法
    public Node(int power, Node<E> left, Node<E> right) {
        this.power = power;
        this.left = left;
        this.right = right;
    }
    public Node(E data, int power){
        this.data = data;
        this.power = power;
    }


    // 实现 Comparable 接口，对节点进行比较
    @Override
    public int compareTo(Node node) {  // 先对节点的权比较之后在对节点的值比较
        if (this.power > node.power){
            return 1;
        }else if (this.power == node.power){
            if ( (int)this.data > (int)node.data ){
                return 1;
            }else if ( (int)this.data < (int)node.data ){
                return -1;
            }
        }else {
            return -1;
        }
        return 0;
    }

    public void add(Node newNode) {  // 将创建的新的节点添加到 List 集合中
        list.add(newNode);
        NodeNums++;
    }

    public Node creatHaFuTree() {
        Collections.sort(list);  // 先将节点集合排好序
        Collections.reverse(list);
        Node leftNode;  // 左子节点
        Node rightNode; // 右子节点
        Node haFuTree = null;  // 创建新的哈夫曼树根节点
        while (!list.isEmpty()) {
            if (haFuTree == null){
                leftNode = list.get(NodeNums);
                list.remove(NodeNums);
                NodeNums -- ;
                rightNode = list.get(NodeNums);
                list.remove(NodeNums);
                NodeNums -- ;
            }else {
                if (list.get(NodeNums).power > haFuTree.power){
                    leftNode = haFuTree;
                    rightNode = list.get(NodeNums);
                }else {
                    leftNode = list.get(NodeNums);
                    rightNode = haFuTree;
                }
                list.remove(NodeNums);
                NodeNums --;
            }
            Node parentNode = new Node(( leftNode.power + rightNode.power ),leftNode,rightNode);
            haFuTree = parentNode;  // 哈夫曼树的根节点设置为 parentNode
        }
        return haFuTree;
    }

    // 先序遍历哈夫曼树
    public void preorderHaFuTree(Node haFuTree){
        if (haFuTree == null){
            return;
        }
        System.out.println("data = "+haFuTree.data+"\tpower = "+haFuTree.power);
        preorderHaFuTree(haFuTree.left);
        preorderHaFuTree(haFuTree.right);
    }

    // 层序遍历哈夫曼树
    public void sequenceHaFuTree(Node haFuTree){
        LinkedList<Node> list = new LinkedList();
        Stack<Node> stack = new Stack<>();
        list.add(haFuTree);
        while (!list.isEmpty()) {
            Node step = list.poll();
            stack.add(step);
            System.out.println("data = "+step.data+"\tpower = "+step.power);
            if (step.left != null){
                System.out.println("leftNode data = "+step.left.data+ "\tleftNode power = "+step.left.power);
            }
            if (step.right != null) {
                System.out.println("rightNode data = "+step.right.data+ "\trightNode power = "+step.right.power);
            }
            System.out.println("==================================");
            if (step.left != null){
                list.add(step.left);
            }
            if (step.right != null) {
                list.add(step.right);
            }
        }
    }

}
