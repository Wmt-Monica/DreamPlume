package tree.avlTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 在二叉排序树的基础上完成平衡树的创建
 */
public class AVLTree {
    public static void main(String[] args) {
        AVLTreeManager<Integer> manager = new AVLTreeManager();
//        int[] nodes = {4,3,6,5,7,8};  // 左旋转节点测试
//        int[] nodes = {10,12,8,9,7,6};  // 右旋转节点测试
//        int[] nodes = {10,11,7,6,8,9};  // 右旋转节点测试
        int[] nodes = new int[10];
        for (int i = 0; i < nodes.length; i ++) {
            nodes[i] = (int)(Math.random()*50+1);
        }

        for (int i = 0; i < nodes.length; i ++) {
            manager.addNode(new AVLTreeNode(nodes[i]));
        }
        List<List<AVLTreeNode>> lists = manager.sequenceTree();
        for (List<AVLTreeNode> list : lists) {
            for (AVLTreeNode node : list) {
                System.out.print(node.data+" ");
            }
            System.out.println();
        }
    }
}

// 平衡二叉树的管理类
class AVLTreeManager<E> {

    AVLTreeNode<E> root = null;  // 平衡树的根节点

    /**
     * 为平衡二叉树添加节点
     * @param addNode 添加的节点对象
     */
    public void addNode(AVLTreeNode<E> addNode) {
        if (root == null) {
            root = addNode;
        }else {
            // 添加节点的方法如同二叉排序树的添加方法一样，父节点data值比左子节点大，比右子节点小
            AVLTreeNode node = root;
            while (true) {
                if (addNode.compareTo(node) < 0) {
                    if (node.left == null) {
                        node.left = addNode;
                        break;
                    }else {
                        node = node.left;
                    }
                }
                if (addNode.compareTo(node) > 0) {
                    if (node.right == null) {
                        node.right = addNode;
                        break;
                    }else {
                        node = node.right;
                    }
                }
            }
        }
        // 对添加节点后的二叉排序树进行判断如果右子树的深度大于左子树就进行左旋转
        rotateTree(judgeBalanced(),root);
    }

    /**
     * 左旋转二叉树：右子树的深度比左子树的深度大
     * @param judgeBalanced 判断时进行左旋转还是右旋转
     * @param root 要旋转的根节点
     */
    public void rotateTree(int judgeBalanced,AVLTreeNode<E> root) {
        if (judgeBalanced < -1) {  // 如果排序二叉树的右子树的深度比左子树深度大于超过1进行左旋转（降低右子树的深度）
            System.out.println("进行左旋转");
            // 1.根据二叉树的根节点进行拷贝创建一个新的节点 newNode
            AVLTreeNode<E> newNode = new AVLTreeNode(root.data);
            System.out.println("newNode = "+newNode.data);
            // 2.把新节点的左子树设置了当前节点的左子树
            newNode.left = root.left;
            System.out.println("newNode.left = "+newNode.left);
            // 3.把新结点的右子树设置为根节点的右子树的左子树
            newNode.right = root.right.left;
            System.out.println("newNode.right = "+newNode.right);
            // 4.把根节点的值换成其右子树的值
            root.data = (E) root.right.data;
            System.out.println("root = "+root);
            // 5.把当前节点的右子树设置成右子树的右子树
            root.right = root.right.right;
            System.out.println("root.right = "+root.right);
            // 6.把当前节点的左子树设置为新节点
            root.left = newNode;
            System.out.println("root.left = "+root.left);
        }else if (judgeBalanced > 1) {  // 如果排序二叉树的左子树的深度比右子树深度大于超过1进行右旋转（降低左子树的深度）

            // 当符合右旋转的条件时
            System.out.println("root.left.right.depth = "+countDepth(root.left.right));
            System.out.println("root.left.left.depth = "+countDepth(root.left.left));
            // 如果根节点的左子树的右子树高度大于根节点的左子树的左子树的高度时
            if (countDepth(root.left.right) > countDepth(root.left.left)) {
                System.out.println("右旋转前的左旋转，实现双旋转");  // 就对根节点的左子树尽心左旋转
                rotateTree(-2,root.left);  // 传入比 -1 小的参数让二叉排序树进行左旋转
            }
            System.out.println("进行右旋转");
            // 1.根据其根节点的值创建一个新的节点对象 newNode
            AVLTreeNode<E> newNode = new AVLTreeNode<>(root.data);
            System.out.println("newNode = "+newNode);
            // 2.把新节点的右子树设置为根节点的右子树
            newNode.right = root.right;
            System.out.println("newNode.right = "+newNode.right);
            // 3.把新节点的左子树设置为根节点的左子树的右子树
            newNode.left = root.left.right;
            System.out.println("newNode.left = "+newNode.left);
            // 4.把当前节点的值换成左子树的值
            root.data = (E)root.left.data;
            System.out.println("root = "+root);
            // 5.把根节点的左子树设置成左子树的左子树
            root.left = root.left.left;
            System.out.println("root.left = "+root.left);
            // 6.把根节点右子树设置为新节点
            root.right = newNode;
            System.out.println("root.right = "+root.right);
        }
    }

    /**
     * 获得左右子树的差值
     * @return 左子树深度减去右子树的深度
     */
    public int judgeBalanced() {
        int leftLength = countDepth(root.left);  // 左子树深度
        int rightLength = countDepth(root.right);  // 右子树深度
        return leftLength - rightLength;  // 如果两边子树的深度相差不超过1就说明该排序二叉树是平衡二叉树
    }

    /**
     * 返回二叉树的深度
     * @param root 二叉树的根节点
     * @return 返回以 root 为根节点的二叉树的深度
     */
    public int countDepth(AVLTreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = countDepth(root.left)+1;
        int rightDepth = countDepth(root.right)+1;
        return leftDepth > rightDepth ? leftDepth : rightDepth;
    }

    /**
     * 层序遍历平衡树
     * @return 返回层序遍历后的 List 集合
     */
    public List<List<AVLTreeNode>> sequenceTree() {
        Queue<AVLTreeNode> queue = new LinkedList<>();
        List<List<AVLTreeNode>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<AVLTreeNode> list = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                AVLTreeNode node = queue.poll();
                list.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            lists.add(list);
        }
        return lists;
    }

}
// 平衡树的节点类
class AVLTreeNode<E> implements Comparable<AVLTreeNode>{
    E data;
    AVLTreeNode left;
    AVLTreeNode right;

    public AVLTreeNode(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(AVLTreeNode o) {
        if ((int)this.data > (int)o.data) {
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "AVLTreeNode{" +
                "data=" + data +
                '}';
    }
}
