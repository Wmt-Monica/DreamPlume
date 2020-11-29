package homework.tree;

/**
 * 实现叉树的先序，后序，层序的遍历
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class ErChaShuDemo1_2020_11_2 {
    public static void main(String[] args) {
         /*       1
              2   3   4
            5 6   7   8 9
         */
        NaryTreeNode tree = new NaryTreeNode("A");
        tree.addChildNode(new NaryTreeNode("B"));
        tree.addChildNode(new NaryTreeNode("C"));
        tree.addChildNode(new NaryTreeNode("D"));
        List<NaryTreeNode> childList = tree.getChildren();
        NaryTreeNode children_3 = new NaryTreeNode("G");
        childList.get(0).addChildNode(new NaryTreeNode("E"));
        childList.get(0).addChildNode(new NaryTreeNode("F"));
        childList.get(2).addChildNode(children_3);
//        List<NaryTreeNode> children_2 = children_1.getChildren();
        List<NaryTreeNode> children_2 = new LinkedList<>();
        children_2.add(0,new NaryTreeNode("I"));
        children_2.add(1,new NaryTreeNode("J"));
        children_2.add(2,new NaryTreeNode("K"));
        NaryTreeNode children_1 = new NaryTreeNode("H",children_2);
        children_3.addChildNode(children_1);

        System.out.print("前根遍历:");
        for (String i : tree.preOrder()) {
            System.out.print(i + "  ");
        }
        System.out.println();

        System.out.print("后根遍历:");
        for (String i : tree.postOrder()) {
            System.out.print(i + "  ");
        }
        System.out.println();

        System.out.println("层次遍历:");
        for (List<String> i : tree.levelOrder()) {
            for (String a : i) {
                System.out.print(a + "  ");
            }
            System.out.println();
        }

    }
}


class NaryTreeNode {
    private String val;
    private List<NaryTreeNode> children;

    public NaryTreeNode(String val) {
        this.val = val;
        children = new ArrayList<NaryTreeNode>();
    }

    public NaryTreeNode(String val, List<NaryTreeNode> children) {
        this.val = val;
        if (children != null) this.children = children;
        else this.children = new ArrayList<NaryTreeNode>();
    }

    public String getVal() {
        return val;
    }

    public List<NaryTreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        if (children.isEmpty())
            return true;
        return false;
    }

    public boolean addChildNode(NaryTreeNode node) {
        children.add(node);
        return true;
    }

    /**
     * 先序遍历：根左右
     * 利用栈模拟递归调用
     * 将根结点压入栈中，当栈不空时执行：
     * 弹出栈中结点，将其放入结果队列中
     * 将该结点的孩子按照倒序依次放入栈中
     */
         /*       1
              2   3   4
            5 6   7   8 9
         */
    public List<String> preOrder() {
        Stack<NaryTreeNode> stack = new Stack<>();
        LinkedList<String> pre = new LinkedList<>();
        if (this == null) return pre;
        stack.add(this);
        while (!stack.isEmpty()) {
            NaryTreeNode node = stack.pop();
            pre.add(node.val);

            BianLi(node.children,pre);

            Stack<NaryTreeNode> reChildren = new Stack<>();
            reChildren.addAll(node.children);
            while (!reChildren.isEmpty()) {
                stack.push(reChildren.pop());
            }

        }
        return pre;
    }

    public void BianLi(List<NaryTreeNode> children,LinkedList<String> pre){
        if (children != null){
            for (NaryTreeNode t : children){
                if (t.children != null){
                    BianLi(t.children,pre);
                }else {
                    pre.add(t.val);
                }
            }
        }
    }


    /**
     * 后序遍历：左右根
     * 利用栈模拟递归调用
     * 将根结点压入栈中，当栈不空时执行：
     * 弹出栈中结点，将其头插放入结果队列中
     * 将该结点的孩子依次放入栈中
     */
         /*       1
              2   3   4
            5 6   7   8 9
         */
    public List<String> postOrder() {
        Stack<NaryTreeNode> stack = new Stack<>();
        LinkedList post = new LinkedList();
        if (this == null) return post;
        stack.add(this);
        while (!stack.isEmpty()) {
            NaryTreeNode node = stack.pop();
            post.addFirst(node.val);
            stack.addAll(node.children);
        }
        return post;
    }

    /**
     * 层次遍历：
     * 利用队列模拟递归调用
     * 将根结点压入队中，当队不空时执行：
     * 获取当前队列长度，当迭代次数小于当前队列长度时：
     * 弹出当前队头结点，将其放入当前层的结果队列中
     * 将该结点的孩子依次放入队列中
     * 将当前层的结果队列放入结果队列中
     */
         /*       1
              2   3   4
            5 6   7   8 9
         */

    public List<List<String>> levelOrder() {
        List<List<String>> result = new ArrayList<>();
        if (this == null) return result;
        Queue<NaryTreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            List<String> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NaryTreeNode node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;

    }
}