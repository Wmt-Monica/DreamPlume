package homework.exercise.test8;

import java.util.LinkedList;
import java.util.Queue;

class BinaryTree{
    BTreeNode root ;

    public BinaryTree(char[] charArray) {
        root = createBinaryTree(charArray, 0);
    }
    public BTreeNode getRoot() {
        return root;
    }
    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        BinaryTree binaryTree1 = new BinaryTree(charArray);
        System.out.print("The node num of level 2 is:");
        int num = getNodeNumIthLevel(binaryTree1.getRoot(), 2);
        System.out.println(num);
        System.out.print("The node num of level 5 is:");
        int num2 = getNodeNumIthLevel(binaryTree1.getRoot(), 5);
        System.out.println(num2);

        char[] charArray2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j'};
        BinaryTree binaryTree2 = new BinaryTree(charArray2);
        System.out.print("binaryTree1 equals to binaryTree2:");
        boolean equalOrNot = isSameBTree(binaryTree1.getRoot(), binaryTree2.getRoot());
        System.out.println(equalOrNot);
        System.out.print("binaryTree1 equals to binaryTree3:");
        BinaryTree binaryTree3 = new BinaryTree(charArray);
        boolean equalOrNot2 = isSameBTree(binaryTree1.getRoot(), binaryTree3.getRoot());
        System.out.println(equalOrNot2);

    }

    /**
     * 创建二叉树
     * @param array 二叉树的子节点 data 值
     * @param index 将对应的 array 数组中的数据插入到对应的节点中的数组下标
     * @return
     */
    private BTreeNode createBinaryTree(char[] array, int index) {
        BTreeNode node = null;
        if (index < array.length) {
            node = new BTreeNode(array[index]);
            node.left = createBinaryTree(array, index * 2 + 1);
            node.right = createBinaryTree(array, index * 2 + 2);
        }
        return node;
    }
    /**
     * 判断两颗二叉树是否相同（结构相同且对应结点的数据相同）
     *
     * @param root1
     * @param root2
     * @return
     */
    public static boolean isSameBTree(BTreeNode root1, BTreeNode root2) {
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
    /**
     * 求二叉树第 i 层的结点个数
     *
     * @param root
     * @param i
     * @return
     */
    public static int getNodeNumIthLevel(BTreeNode root, int i) {
        int floor = 1;  // 树的遍历的层数
        int nums = 0; // 第 i 层的总节点数
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BTreeNode bTreeNode = queue.poll();
            if (bTreeNode.left != null) {
                queue.add(bTreeNode.left);
            }
            if (bTreeNode.right != null) {
                queue.add(bTreeNode.right);
                // 遍历到了右子树说明已经遍历完了一层
                floor ++;
            }
            if (floor == i) {
                nums ++;
            }
        }
        return nums;
    }
    /**
     * 二叉树结点定义
     */
    class BTreeNode {
        char data;
        BTreeNode left;
        BTreeNode right;
        public BTreeNode(char value) {
            this.data = value;
        }
    }
}