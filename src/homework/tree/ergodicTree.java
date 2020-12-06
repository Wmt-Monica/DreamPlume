package homework.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 树的遍历
 *                 A
 *        C                  B
 *   D          X
 *F        G        Y
 *       E        Q
 */
public class ergodicTree {
    static int nodeX = 0;
    static char maxNode;
    static TreeNode XFatherNode;
    public static void main(String[] args) {
        // 构建树
        TreeNode node10 = new TreeNode('Q');
        TreeNode node9 = new TreeNode('E');
        TreeNode node8 = new TreeNode('Y',node10);
        TreeNode node7 = new TreeNode('G',node9);
        TreeNode node6 = new TreeNode('F');
        TreeNode node5 = new TreeNode('X',node7,node8);
        TreeNode node4 = new TreeNode('D',node6);
        TreeNode node3 = new TreeNode('B');
        TreeNode node2 = new TreeNode('C',node4,node5);
        TreeNode node1 = new TreeNode('A',node2,node3);
        maxNode = node1.val;

        // 1.非递归实现先序树结构
        System.out.print("使用非递归的方式实现先序遍历二叉树：");
        List list = method1(node1);
        System.out.println(list);
        getSubDepth(node1,'X');
        System.out.println("\n以节点值为X的节点为根节点的子树的深度为："+nodeX);
        List list1 = postOrderInterator(node1);
        System.out.println(list1);
        System.out.println("================1===============");
        findMax(node1);
        System.out.println("maxNode = "+maxNode);
        System.out.println("===============2================");
        print2Nodes(node1);
        System.out.println("=================3==============");
        findFather(node1,'X');
        System.out.println(XFatherNode.val);
        System.out.println("===============4================");
        printNodeValues(node1);
        System.out.println("\n===============5================");
        invertTree(node1);
        List list2 = method1(node1);
        System.out.println(list2);
        System.out.println("\n===============6================");
        System.out.println(isSymmetric(node1));

    }

    /**
     * 功能：定义树结构
     *
     */
    static class TreeNode
    {
        char val;
        TreeNode left;
        TreeNode right;

        TreeNode(char val) {
            this.val = val;
        }

        TreeNode(char val,TreeNode left){
            this.val = val;
            this.left = left;
        }

        TreeNode(char val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }

    }

    /**
     * 功能：查找二叉树中最大的值，并且返回
     *
     * @param root 二叉树的根节点
     * @return 返回二叉树节点的最大值
     */
    public static char findMax(TreeNode root){
        if (root.val > maxNode){
            maxNode = root.val;
        }
        if (root.left != null){
            findMax(root.left);
        }
        if (root.right != null){
            findMax(root.right);
        }
        return maxNode;
    }

    /**
     * 功能：请输出二叉树中所有度为2的结点的值
     * @param root 二叉树的根节点
     */
    public static void print2Nodes(TreeNode root){
        if (root.left != null && root.right != null){
            System.out.println(root.val);
        }
        if (root.left != null){
            print2Nodes(root.left);
        }
        if (root.right != null){
            print2Nodes(root.right);
        }
    }

    /**
     * 功能：请查找值为x的结点的父节点，并且返回
     * @param root 二叉树的根节点
     * @param X 所查找的节点的 val 的值
     * @return 返回 val 值等于 X 节点的父节点
     */
    public static TreeNode findFather(TreeNode root, char X){
        if (!(root.left != null && root.left.val == 'X') && !(root.right != null && root.right.val == 'X')){
            if (root.left != null){
                findFather(root.left,X);
            }
            if (root.right != null){
                findFather(root.right,X);
            }
        }else {
            XFatherNode = root;
        }
        return XFatherNode;
    }

    /**
     * 功能：请将二叉树的每个结点的值按照层序遍历的逆序输出
     * @param root 二叉树的根节点
     */
    public static void printNodeValues(TreeNode root){
        LinkedList<TreeNode> list = new LinkedList();
        Stack<Character> stack = new Stack<>();
        list.add(root);
        while (!list.isEmpty()){
            TreeNode step = list.poll();
            stack.add(step.val);
            if (step.left != null){
                list.add(step.left);
            }
            if (step.right != null){
                list.add(step.right);
            }
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }

    /**
     * 功能： 翻转一棵二叉树
     * @param root 二叉树的根节点
     * @return 返回反转后的树
     */
    public static TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode step = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(step);
        return root;
    }

    /**
     * 用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
     * @param root 二叉树的根节点
     * @return 返回该二叉树是否为对称的二叉树
     */
    public static boolean isSymmetric (TreeNode root){
        LinkedList<TreeNode> list = new LinkedList();
        Stack<Character> stack = new Stack<>();
        list.add(root);
        stack.add(root.val);
        while (!list.isEmpty()){
            TreeNode step = list.poll();
            if (stack.size() == 2 && step.val != stack.peek()){
                return false;
            }else if (stack.size() == 0 || stack.size() < 2 && step.val != stack.peek()){
                stack.add(step.val);
            }else if (step.val == stack.peek()){
                stack.pop();
            }

            if (step.left != null){
                list.add(step.left);
            }
            if (step.right != null){
                list.add(step.right);
            }
        }
        return true;
    }









    /**
     * 功能：.非递归实现先序树结构
     *
     * @param root 二叉树的根节点
     * @return 返回先序遍历二叉树的 val 值得 List 集合
     */
    public static List<Object> method1(TreeNode root){
        Stack<TreeNode> result = new Stack();
        result.add(root);
        List<Object> list = new LinkedList<>();
        if (root == null){
            return list;
        }
        while (!result.isEmpty()){
            root = result.pop();
            list.add(root.val);
            if (root.right != null){
                result.add(root.right);
            }
            if (root.left != null){
                result.add(root.left);
            }
        }
        return list;
    }

    /**
     * 功能：递归实现先序遍历二叉树寻找 val 值为 x 的节点
     *
     * @param btree 二叉树的根节点
     * @param x 在二叉树中寻找节点的 val 值
     */
    public static void getSubDepth(TreeNode btree, char x){
        if (btree.val != x){
            if (btree.left != null){
                getSubDepth(btree.left,x);
            }
            if (btree.right != null){
                getSubDepth(btree.right,x);
            }
        }else {
            // 如果找到值为 X 的节点将其作为参数传入method()方法中计算出其以该节点为根节点的子树的深度
            nodeX = getDepth(btree);
        }
    }


    /**
     * 功能：计算以 nodeX 为根节点的二叉树的深度
     *
     * @param t 根节点
     * @return 返回以参数 t 为根节点的二叉树的深度
     */
    public static int getDepth(TreeNode t){
        if (t == null){
            return 0;
        }else {
            return (getDepth(t.left) > getDepth(t.right) ? getDepth(t.left) : getDepth(t.right))+1;
        }
    }

    /**
     * 功能：采用非递归的方法实现后序遍历二叉树
     *
     * @param node 二叉树的根节点
     */
    public static List<Object> postOrderInterator(TreeNode node){

        //  创建一个 List 集合存储后序遍历二叉树节点的 val 值
        List<Object> list = new ArrayList<>();

        // 如果该该根节点为 null 空，直接返回
        if (node == null){
            return list;
        }

        // 创建一个栈存储二叉树的所有节点
        Stack<TreeNode> stack = new Stack<>();
        stack.add(node);
        while (!stack.isEmpty()){
            node = stack.pop();  // 让栈 stack 的栈顶重新赋值给 node 的同时，让该栈顶出栈
            list.add(node.val);  // 让 list 集合对象存储该节点的 val 值
            // 为了实现后序遍历二叉树 中 右 左顺序，所以，二叉树节点入栈采用先左后右
            if (node.right != null){
                stack.add(node.right);
            }
            if (node.left != null){
                stack.add(node.left);
            }
        }
        return list;
    }

}


