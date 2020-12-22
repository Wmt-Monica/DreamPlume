package homework.exercise.test5;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5、创建一如下二叉树结构，并分别采用四种遍历：先根，中跟，后根，层次遍
 *    历。并在 main 中编写测试用例，输出四种遍历结果。（出题人：连斌）
 */
public class Test {
    public static void main(String[] args) {
        TreeNode root = new TreeNode("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        TreeNode nodeD = new TreeNode("D");
        TreeNode nodeE = new TreeNode("E");
        TreeNode nodeF = new TreeNode("F");
        TreeNode nodeG = new TreeNode("G");
        root.setLeft(nodeB);
        root.setRight(nodeC);
        nodeB.setLeft(nodeD);
        nodeC.setLeft(nodeE);
        nodeC.setRight(nodeF);
        nodeD.setRight(nodeG);

        System.out.println("============先序遍历============");
        root.prePrintTreeNode(root);
        System.out.println("\n============中序遍历============");
        root.midPrintTreeNode(root);
        System.out.println("\n============后序遍历============");
        root.backPrintTreeNode(root);
        System.out.println("\n============层序遍历============");
        root.cenPrintTreeNode(root);
    }
}
class TreeNode{
    private String data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode(String data) {
        this.data = data;
    }

    /**
     * 先序遍历
     * @param root 根节点
     */
    public void prePrintTreeNode(TreeNode root) {
        System.out.print(root.data+" ");
        if (root.left != null) {
            prePrintTreeNode(root.left);
        }
        if (root.right != null) {
            prePrintTreeNode(root.right);
        }
    }

    /**
     * 中序遍历
     * @param root 根节点
     */
    public void midPrintTreeNode(TreeNode root) {
        if (root.left != null) {
            midPrintTreeNode(root.left);
        }
        System.out.print(root.data+" ");
        if (root.right != null) {
            midPrintTreeNode(root.right);
        }
    }

    /**
     * 后序遍历
     * @param root 根节点
     */
    public void backPrintTreeNode(TreeNode root) {
        if (root.left != null) {
            backPrintTreeNode(root.left);
        }
        if (root.right != null) {
            backPrintTreeNode(root.right);
        }
        System.out.print(root.data+" ");
    }

    public void cenPrintTreeNode(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.data+" ");
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
    }
}