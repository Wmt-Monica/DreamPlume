package tree.binaryTree;

import java.util.List;

/**
 * 顺序存储二叉树
 */
/*
                            0
                 1                    2
          3          4          5          6
       7    8     9    10     11  12     13  14
 */
public class StorageOrderBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(14);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
        node4.left = node9;
        node4.right = node10;
        node5.left = node11;
        node5.right = node12;
        node6.left = node13;
        node6.right = node14;

        // 先将其层序遍历的二叉树 List 集合创建
        List<TreeNode> treeNodeList = root.cenXuTreeNode(root);
        System.out.println(treeNodeList);

        System.out.println("\n\n先序遍历顺序二叉树表的结果");
        new StorageOrderBinaryTree().preTreeNode(treeNodeList,0);

        System.out.println("\n\n中序遍历顺序二叉树表的结果");
        new StorageOrderBinaryTree().cenTreeNode(treeNodeList,0);

        System.out.println("\n\n后序遍历顺序二叉树表的结果");
        new StorageOrderBinaryTree().backTreeNode(treeNodeList,0);

    }

    /**
     * 根据顺序二叉树 List 集合将其二叉树的前序遍历打印出来
     * @param treeNodeList 顺序二叉树 List 集合
     * @param index 顺序二叉树集合获取 get() 方法的索引
     */
    public void preTreeNode(List<TreeNode> treeNodeList, int index) {
        if (treeNodeList.isEmpty()) {
            System.out.println("顺序二叉树集合为空不能前序遍历其二叉树");
            return;
        }
        System.out.print(treeNodeList.get(index).date+" ");
        if (index * 2 + 1 < treeNodeList.size()) {
            preTreeNode(treeNodeList, (index * 2 + 1));
        }
        if (index * 2 + 2 < treeNodeList.size()) {
            preTreeNode(treeNodeList, (index * 2 + 2));
        }
    }

    /**
     * 根据顺序二叉树 List 集合将其二叉树的中序遍历打印出来
     * @param treeNodeList 顺序二叉树 List 集合
     * @param index 顺序二叉树集合获取 get() 方法的索引
     */
    public void cenTreeNode(List<TreeNode> treeNodeList, int index) {
        if (treeNodeList.isEmpty()) {
            System.out.println("顺序二叉树集合为空不能中序遍历其二叉树");
            return;
        }
        if ((index * 2 + 1) < treeNodeList.size()) {
            cenTreeNode(treeNodeList, (index * 2 + 1));
        }
        System.out.print(treeNodeList.get(index).date+" ");
        if ((index * 2 + 2) < treeNodeList.size()) {
            cenTreeNode(treeNodeList, (index * 2 + 2));
        }
    }

    /**
     * 根据顺序二叉树 List 集合将其二叉树的后序遍历打印出来
     * @param treeNodeList 顺序二叉树 List 集合
     * @param index 顺序二叉树集合获取 get() 方法的索引
     */
    public void backTreeNode(List<TreeNode> treeNodeList, int index) {
        if (treeNodeList.isEmpty()) {
            System.out.println("顺序二叉树集合为空不能后序遍历其二叉树");
            return;
        }
        if ((index * 2 + 1) < treeNodeList.size()) {
            backTreeNode(treeNodeList, (index * 2 + 1));
        }
        if ((index * 2 + 1) < treeNodeList.size()) {
            backTreeNode(treeNodeList, (index * 2 + 2));
        }
        System.out.print(treeNodeList.get(index).date+" ");
    }
}
