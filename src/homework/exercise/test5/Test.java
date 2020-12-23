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

        TreeNode root2 = new TreeNode("A");
        TreeNode nodeB2 = new TreeNode("B");
        TreeNode nodeC2 = new TreeNode("C");
        TreeNode nodeD2 = new TreeNode("D");
        TreeNode nodeE2 = new TreeNode("E");
        TreeNode nodeF2 = new TreeNode("F");
        TreeNode nodeG2 = new TreeNode("G");
        root2.setLeft(nodeB2);
        root2.setRight(nodeC2);
        nodeB2.setLeft(nodeD2);
        nodeC2.setLeft(nodeE2);
        nodeC2.setRight(nodeF2);
        nodeD2.setRight(nodeG2);
        boolean flag = root.isSameBTree(root,root2);
        System.out.println("\n两棵树是否相同 = "+flag);
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

    /**
     * 层序遍历
     * @param root 根节点
     */
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
                System.out.println();
            }
        }
    }


    /**
     * 判断两颗二叉树是否相同（结构相同且对应结点的数据相同）
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean isSameBTree(TreeNode root1, TreeNode root2) {
        // 一：判断两棵树的结构是否相同
        // 1):该节点都为 null 时该处结构相同（该节点可以是根节点，左节点，右节点）
        if (root1 == null && root2 == null) {
            return true;
        }else if (root1 == null) {
            // 2):该节点处，只有 root1 为 null, root2 不为 null,说明该处两棵树的结构不相同，返回 false
            return false;
        }else if (root2 == null) {
            // 3):该节点处，只有 root2 为 null, root1 不为 null,说明该处两棵树的结构不相同，返回 false
            return false;
        }

        // 二：判断两棵树的该处节点的数据是否相同
        // 1):当该处节点两棵树都不为 null 则判断其该节点的数据是否相同
        return (root1.data == root2.data) &&
                // 2):使用递归接着判断 root1 和 root2 的左子树和右子树是否相同
                (isSameBTree(root1.left,root2.left) && isSameBTree(root1.right,root2.right));
    }
}