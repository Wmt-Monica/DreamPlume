package tree.binaryTree;

import java.util.*;

public class BinaryTree {
    /*
                                    0
                            1                  2
                        3       4          5        6
                     7             8
                  9
     */
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(0);
        TreeNode<Integer> node1 = new TreeNode<>(1);
        TreeNode<Integer> node2 = new TreeNode<>(2);
        TreeNode<Integer> node3 = new TreeNode<>(3);
        TreeNode<Integer> node4 = new TreeNode<>(4);
        TreeNode<Integer> node5 = new TreeNode<>(5);
        TreeNode<Integer> node6 = new TreeNode<>(6);
        TreeNode<Integer> node7 = new TreeNode<>(7);
        TreeNode<Integer> node8 = new TreeNode<>(8);
        TreeNode<Integer> node9 = new TreeNode<>(9);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node5.left = node8;
        node7.left = node9;

        System.out.println("==========先序遍历二叉树==========");
        root.prePrintBinaryTree(root);

        System.out.println("\n\n============中序遍历二叉树==========");
        root.cenPrintBinaryTree(root);

        System.out.println("\n\n============后序遍历二叉树==========");
        root.backPrintBinaryTree(root);

        System.out.println("\n\n============先序遍历二叉树查找 X 值节点==========");
        TreeNode treeNodeX1 = root.preSearchTreeNode(root,8);
        System.out.println(treeNodeX1);

        System.out.println("\n\n============中序遍历二叉树查找 X 值节点==========");
        TreeNode treeNodeX2 = root.cenSearchTreeNode(root,8);
        System.out.println(treeNodeX2);

        System.out.println("\n\n============后序遍历二叉树查找 X 值节点==========");
        TreeNode treeNodeX3 = root.backSearchTreeNode(root,8);
        System.out.println(treeNodeX3);

        System.out.println("\n\n============遍历二叉树删除 X 值节点==========");
        root.delTreeNode(root,8);
        root.prePrintBinaryTree(root);

        System.out.println("\n\n============层序遍历二叉树并将其层序顺序存放入 List 集合中并返回==========");
        List<TreeNode> treeNodeList = root.cenXuTreeNode(root);
        System.out.println(treeNodeList);
    }
}

class TreeNode<E> {
    E date;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E date) {
        this.date = date;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "TreeNode{" +
                "date=" + date +
                '}';
    }

    //  递归先序遍历二叉树
    public void prePrintBinaryTree(TreeNode root) {
        System.out.print(root.date+" ");
        if (root.left != null) {
            prePrintBinaryTree(root.left);
        }
        if (root.right != null) {
            prePrintBinaryTree(root.right);
        }
    }

    // 递归中序遍历二叉树
    public void cenPrintBinaryTree(TreeNode root) {
        if (root.left != null) {
            cenPrintBinaryTree(root.left);
        }
        System.out.print(root.date+" ");
        if (root.right != null) {
            cenPrintBinaryTree(root.right);
        }
    }

    // 递归后序遍历二叉树
    public void backPrintBinaryTree(TreeNode root) {
        if (root.left != null) {
            backPrintBinaryTree(root.left);
        }
        if (root.right != null) {
            backPrintBinaryTree(root.right);
        }
        System.out.print(root.date+" ");
    }

    // 使用先序遍历查找节点值为 X 的节点
    public TreeNode<Integer> preSearchTreeNode(TreeNode root, int X) {
        if (root == null) {
            return null;
        }
        if (root.date.equals(X)) {
            return root;
        }else {
            System.out.println("节点"+root+"不是我们要寻找的值");
        }
        TreeNode treeNode = null;
        if (root.left != null) {
            treeNode = preSearchTreeNode(root.left, X);
        }
        if (treeNode != null) {
            return treeNode;
        }
        if (root.right != null) {
            treeNode = preSearchTreeNode(root.right, X);
        }
        return treeNode;
    }

    // 使用中序遍历查找节点值为 X 的节点
    public TreeNode<Integer> cenSearchTreeNode(TreeNode root, int X) {
        TreeNode treeNodeX = null;
        if (root.left != null) {
            treeNodeX = cenSearchTreeNode(root.left, X);
        }
        if (root.date.equals(X)) {
            return root;
        }else {
            System.out.println("节点"+root+"不是我们要寻找的值");
        }
        if (treeNodeX != null) {
            return treeNodeX;
        }
        if (root.right != null) {
            treeNodeX = cenSearchTreeNode(root.right, X);
        }
        return treeNodeX;
    }

    // 使用后序遍历查找节点值为 X 的节点
    public TreeNode<Integer> backSearchTreeNode(TreeNode root, int X) {
        TreeNode treeNode = null;
        if (root.left != null) {
            treeNode = backSearchTreeNode(root.left, X);
        }
        if (treeNode != null) {
            return treeNode;
        }
        if (root.right != null) {
            treeNode = backSearchTreeNode(root.right, X);
        }
        if (treeNode != null) {
            return treeNode;
        }
        if (root.date.equals(X)) {
            return root;
        }else {
            System.out.println("节点"+root+"不是我们要寻找的值");
        }
        return treeNode;
    }

    // 删除二叉树指定节点值为 X 的节点
    public void delTreeNode(TreeNode root, int X) {
        if (root.left != null && root.left.date.equals(X)) {
            root.left = null;
            return;
        }
        if (root.right != null && root.right.date.equals(X)) {
            root.right = null;
            return;
        }
        if (root.left != null) {
            delTreeNode(root.left, X);
        }
        if (root.right != null) {
            delTreeNode(root.right, X);
        }
    }

    // 层序遍历并将其二叉树按照层序的顺序存储于List集合中
    public List<TreeNode> cenXuTreeNode(TreeNode root) {
        List<TreeNode> treeNodeList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            treeNodeList.add(treeNode);
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        return treeNodeList;
    }
}