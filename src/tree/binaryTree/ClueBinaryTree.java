package tree.binaryTree;

/**
 * 线索二叉树
 */
public class ClueBinaryTree {
    public static void main(String[] args) {
        ClueTreeNode root = new ClueTreeNode(1);
        ClueTreeNode node3 = new ClueTreeNode(3);
        ClueTreeNode node6 = new ClueTreeNode(6);
        ClueTreeNode node8 = new ClueTreeNode(8);
        ClueTreeNode node10 = new ClueTreeNode(10);
        ClueTreeNode node14 = new ClueTreeNode(14);
        root.left = node3;
        root.right = node6;
        node3.left = node8;
        node3.right = node10;
        node6.left = node14;

        Manage manage = new Manage();
        System.out.println("\n================递归前序普通二叉树================");
        manage.prePrintBinaryTree(root);
        System.out.println("\n================递归中序普通二叉树================");
        manage.cenPrintBinaryTree(root);
        System.out.println("\n================递归后序普通二叉树================");
        manage.backPrintBinaryTree(root);
        System.out.println("\n================中序线索化普通二叉树================");
        manage.clueBinaryTree2(root);
        System.out.println("序列化完毕.....");
        System.out.println("================中序遍历普通二叉树================");
        manage.cenClueBinaryTree(root);
    }
}
class Manage {

    ClueTreeNode p;  // 指定当前 node 的前一个节点

    // 线索化普通的二叉树：变成中序线索二叉树
    public void clueBinaryTree2(ClueTreeNode node) {
        if (node == null) {
            return;
        }
        clueBinaryTree2(node.left);  // 先线索化二叉树的左子树

        if (node.left == null) {  // 当该节点的左子树为空
            node.left = p;  // 将该节点的上一个节点 p
            node.leftIsClue = true;  // 给该节点的左子树是否为做线索
        }
        // 当该节点的前一个节点不为空且前一个节点的右子节点为空
        if (p != null && p.right == null) {
            p.right = node;  // 就将该节点的前一个节点的右子节点指向该节点
            p.rightIsClue = true;  // 将该节点的前一个节点的右子树设置为线索
        }
        p = node;  // 使得每一次 p 都指向 当前节点的遍历顺序的前一个节点

        clueBinaryTree2(node.right);  // 线索化右子节点
    }

    // 遍历中序线索化的二叉树
    public void cenClueBinaryTree(ClueTreeNode root) {
        ClueTreeNode node = root;
        while (node != null) {
            while (!node.leftIsClue) {  // 首先让辅助遍历节点 node 下移到左叶子树
                node = node.left;
            }
            System.out.print(node.date + " ");  // 打印输出该节点的值
            while (node.rightIsClue) {  // 循环遍历右线索子树并打印输出其节点值
                node = node.right;
                System.out.print(node.date + " ");
            }
            node = node.right;  // 该节点的右子树不是线索子树，就将其替换成右子树
        }
    }

    // 前序递归遍历普通二叉树
    public void prePrintBinaryTree(ClueTreeNode root) {
        System.out.print(root.date+" ");
        if (root.left != null) {
            prePrintBinaryTree(root.left);
        }
        if (root.right != null) {
            prePrintBinaryTree(root.right);
        }
    }

    // 中序递归遍历普通二叉树
    public void cenPrintBinaryTree(ClueTreeNode root) {
        if (root.left != null) {
            cenPrintBinaryTree(root.left);
        }
        System.out.print(root.date+" ");
        if (root.right != null) {
            cenPrintBinaryTree(root.right);
        }
    }

    // 后序遍历普通二叉树
    public void backPrintBinaryTree(ClueTreeNode root) {
        if (root.left != null) {
            backPrintBinaryTree(root.left);
        }
        if (root.right != null) {
            backPrintBinaryTree(root.right);
        }
        System.out.print(root.date+" ");
    }

}
// 创建线索二叉树的节点对象
class ClueTreeNode {
    int date;
    ClueTreeNode left;  // 左子节点
    ClueTreeNode right; // 右子节点
    boolean leftIsClue;  // 左子节点是线索
    boolean rightIsClue;  // 右子节点是线索

    public ClueTreeNode(int date) {
        this.date = date;
    }
}

