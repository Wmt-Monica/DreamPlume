package tree.binaryTree;

public class BinaryTree {
    /*
                                    0
                            1                  2
                        3       4          5        6
                     7                  8
                  9
     */
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
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
        binaryTree.prePrintBinaryTree(root);

        System.out.println("\n\n============中序遍历二叉树==========");
        binaryTree.cenPrintBinaryTree(root);

        System.out.println("\n\n============后序遍历二叉树==========");
        binaryTree.backPrintBinaryTree(root);
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
}