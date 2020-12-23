package tree.binaryTree;

/**
 * 线索二叉树
 */
public class ClueBinaryTree {
    public static void main(String[] args) {
        ClueTreeNode<Integer> root = new ClueTreeNode<>(0);
        ClueTreeNode<Integer> node1 = new ClueTreeNode<>(1);
        ClueTreeNode<Integer> node2 = new ClueTreeNode<>(2);
        ClueTreeNode<Integer> node3 = new ClueTreeNode<>(3);
        ClueTreeNode<Integer> node4 = new ClueTreeNode<>(4);
        ClueTreeNode<Integer> node5 = new ClueTreeNode<>(5);
        ClueTreeNode<Integer> node6 = new ClueTreeNode<>(6);
        ClueTreeNode<Integer> node7 = new ClueTreeNode<>(7);
        ClueTreeNode<Integer> node8 = new ClueTreeNode<>(8);
        ClueTreeNode<Integer> node9 = new ClueTreeNode<>(9);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node5.left = node8;
        node7.left = node9;

        Manage manage = new Manage(root);
        manage.createClueBinaryTree(root);
    }
}
class Manage {

    ClueTreeNode root;  // 二叉树的根节点
    ClueTreeNode p;  // 指定当前 node 的前一个节点
    ClueTreeNode node;  // 当前指定的二叉树节点

    public Manage(ClueTreeNode root) {
        this.root = root;
        this.node = this.root;
    }

    // 线索化普通的二叉树：变成中序线索二叉树
    public void clueBinaryTree(ClueTreeNode root) {
       if (root != null) {
           clueBinaryTree(root.left);  // 先线索化二叉树的左子树
           if (root.left == null) {  // 当该节点的左子树为空
               root.left = p;  // 将该节点的上一个节点 p
               root.leftIsClue = true;  // 给该节点的左子树是否为做线索
           }
           // 当该节点的前一个节点不为空且前一个节点的右子节点为空
           if (p != null && p.right == null) {
               p.right = root;  // 就将该节点的前一个节点的右子节点指向该节点
               p.rightIsClue = true;  // 将该节点的前一个节点的右子树设置为线索
           }
           p = node;  // 使得每一次 p 都指向 当前节点的遍历顺序的前一个节点
           clueBinaryTree(root.right);  // 线索化右子节点
       }
    }

    public void createClueBinaryTree(ClueTreeNode root) {
        if (root != null) {
            clueBinaryTree(root);
            p.right = null;
            p.rightIsClue = true;
        }
        node = this.root;
    }
}
// 创建线索二叉树的节点对象
class ClueTreeNode<E> {
    E date;
    ClueTreeNode<E> left;  // 左子节点
    ClueTreeNode<E> right; // 右子节点
    boolean leftIsClue;  // 左子节点是线索
    boolean rightIsClue;  // 右子节点是线索

    public ClueTreeNode(E date) {
        this.date = date;
    }
}

