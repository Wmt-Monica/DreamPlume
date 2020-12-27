package homework.exercise.test8;
/**
 * 8、请在 BinaryTree 的 isSameBTree 和 getNodeNumIthLevel 方法中补充代码，并且在 main 方
 *    法中完成测试。（出题人：胡志成）
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

    class BinaryTree {
    private BTreeNode root ;

    public BinaryTree(char[] charArray) {
        root = createBinaryTree(charArray, 0);
    }
    public BTreeNode getRoot() {
        return root;
    }
    public static void main(String[] args) {
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
        BinaryTree binaryTree1 = new BinaryTree(charArray);
        System.out.println("===========层序遍历============");
        List<List<Character>> list = binaryTree1.sequencePrint(binaryTree1.getRoot());
        for (List<Character> list1 : list) {
            for (Character i : list1) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
        System.out.println("=============================");

        System.out.print("The node num of level 2 is:");  // 第二层总节点数
        int num = getNodeNumIthLevel(binaryTree1.getRoot(), 2);
        System.out.println(num);
        System.out.print("The node num of level 5 is:");
        int num2 = getNodeNumIthLevel(binaryTree1.getRoot(), 3);  // 第三层节点总数
        System.out.println(num2);

        char[] charArray2 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j'};
        BinaryTree binaryTree2 = new BinaryTree(charArray2);
        System.out.print("binaryTree1 equals to binaryTree2:");  // 判断 binaryTree1 是否与 binaryTree2 具有相等
        boolean equalOrNot = isSameBTree(binaryTree1.getRoot(), binaryTree2.getRoot());
        System.out.println(equalOrNot);
        System.out.print("binaryTree1 equals to binaryTree3:");  // 判断 binaryTree1 是否与 binaryTree3 具有相等
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
     * @param root 二叉树根节点
     * @param i 二叉树层数
     * @return 返回二叉树该层的总节点数
     */
    public static int getNodeNumIthLevel(BTreeNode root, int i) {
        int floor = 0;  // 树的遍历的层数
        int nums = 0; // 第 i 层的总节点数
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();  // 获取此时遍历层数的总节点数
            for (int k = 0; k < size; k ++) {  // 循环遍历 queue 中的节点，将其这些节点弹出并添加这些节点的所有子节点
                BTreeNode bTreeNode = queue.poll();
                if (bTreeNode.left != null) {
                    queue.add(bTreeNode.left);
                }
                if (bTreeNode.right != null) {
                    queue.add(bTreeNode.right);
                }
            }
            // 每当遍历完一层就将 floor 层数增加一，表示二叉树层数的下移
            floor ++;
            // 如果当 floor == i 说明已经遍历到了第 i 层
            if (floor == i) {
                nums = size;  // 此时将第 i 层的总子节点树赋值给 nums,然后跳出循环
                break;
            }
        }
        return nums;
    }

    // 层序遍历二叉树
    public List<List<Character>> sequencePrint(BTreeNode root) {
        List<List<Character>> list = new ArrayList<>();
        Queue<BTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Character> list1 = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                BTreeNode bTreeNode = queue.poll();
                list1.add(bTreeNode.data);
                if (bTreeNode.left != null) {
                    queue.add(bTreeNode.left);
                }
                if (bTreeNode.right != null) {
                    queue.add(bTreeNode.right);
                }
            }
            list.add(list1);
        }
        return list;
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