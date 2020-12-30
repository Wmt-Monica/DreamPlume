package tree.binarySortTree;

import java.util.*;

/**
 * 二叉排序树
 */
public class BinarySortTree {
    public static void main(String[] args) {
        BinarySortTreeNodeManager<Integer> manager = new BinarySortTreeNodeManager();
        // 向顺序二叉树添加节点
        for (int  i = 0; i < 20; i ++) {
            manager.addNode(new BinarySortTreeNode((int)(Math.random()*10+1)));
        }

        // 层序创建的顺序二叉树
        List<List<BinarySortTreeNode>> lists = manager.sequencePrint();
        for (List<BinarySortTreeNode> nodes : lists) {
            for (BinarySortTreeNode node : nodes) {
                System.out.print("node = "+node.data+"\t");
                if (node.left != null) {
                    System.out.print("node.left = "+node.left.data+"||\t");
                }
                if (node.right != null) {
                    System.out.print("node.right = "+node.right.data+"||\t");
                }
            }
            System.out.println();
        }
        manager.deleteNode(manager.root);
        System.out.println("=================================");
        List<List<BinarySortTreeNode>> lists2 = manager.sequencePrint();
        for (List<BinarySortTreeNode> nodes : lists2) {
            for (BinarySortTreeNode node : nodes) {
                System.out.print("node = "+node.data+"\t");
                if (node.left != null) {
                    System.out.print("node.left = "+node.left.data+"||\t");
                }
                if (node.right != null) {
                    System.out.print("node.right = "+node.right.data+"||\t");
                }
            }
            System.out.println();
        }
        System.out.println("=================================");
    }
}

class BinarySortTreeNodeManager<E> {

    BinarySortTreeNode<E> root = null;  // 二叉排序树的根节点

    /**
     * 在二叉顺序树种删除节点
     * @param deleteNode 要删除的节点
     */
    public void deleteNode(BinarySortTreeNode deleteNode) {
        // 找到要删除节点 deleteNode 的父节点
        BinarySortTreeNode parentNode = searchParentNode(deleteNode);
        System.out.println("parentNode = "+parentNode.data);
        // 情况一：删除节点是叶子节点
        if (deleteNode.left == null && deleteNode.right == null) {
            // 当删除的节点是根节点时，直接将根节点赋值为 null
            if (deleteNode == root) {
                root = null;
                // 判断删除节点是父节点的左右节点
            }else if (parentNode.left == deleteNode) {
                parentNode.left = null;
            }else if (parentNode.right == deleteNode){
                parentNode.right = null;
            }

            // 情况二：删除节点有左右两个子节点
        }else if (deleteNode.left != null && deleteNode.right != null) {

            // 删除节点不是根节点
            if (deleteNode != root) {
                // 情况二有两种处理方法
                // 方法一：找到待删除节点的左子树的最大的节点（往左子树的右边找）
                // 方法二：找到待删除节点的右子树的最小的节点（往右子树的左边找）一样的思路不一样，这里采用方法一
                BinarySortTreeNode leftNode = deleteNode.left;
                while (leftNode.right != null) {
                    leftNode = leftNode.right;  // 将节点下移到左子树的右边最大的节点
                }
                // 如果删除节点是根节点
                if (parentNode.left == deleteNode) {
                    parentNode.left = leftNode;
                }else if (parentNode.right == deleteNode) {
                    parentNode.right = leftNode;
                }

                // 当删除的节点是根节点时
            }else {
                // 获取到左子树最大的子节点的数据
                BinarySortTreeNode leftNode = deleteNode.left;
                BinarySortTreeNode node = null;
                while (leftNode.right != null) {
                    if (leftNode.right.right == null) {  // 提前判断，这样获取寻找的节点的父节点
                        node = leftNode.right;
                        if (leftNode.right.left != null) {
                            leftNode.right = leftNode.right.left;
                        }
                        break;
                    }else {
                        leftNode = leftNode.right;
                    }
                }
                if (node != null) {
                    node.left = root.left;
                    node.right = root.right;
                    root = node;
                }else {  // 如果根节点的左子树没有右子节点时
                    leftNode.right = root.right;
                    root = leftNode;
                }
            }

            // 情况三：如果要删除的节点只有一个子节点
        }else {
            if (deleteNode != root) {  // 当删除的节点不是根节点是时
                // 判断删除节点是父节点的左右节点
                if (parentNode.left == deleteNode) {
                    // 判断要删除的节点是由左子节点还是右子节点
                    if (deleteNode.left != null) {
                        parentNode.left = deleteNode.left;
                    }else {
                        parentNode.left = deleteNode.right;
                    }
                }else if (parentNode.right == deleteNode){
                    // 判断要删除的节点是由左子节点还是右子节点
                    if (deleteNode.left != null) {
                        parentNode.right = deleteNode.left;
                    }else {
                        parentNode.right = deleteNode.right;
                    }
                }
            }else {  // 当删除的节点是根节点时
                if (root.left != null) {
                    root = root.left;
                }else {
                    root = root.right;
                }
            }
        }

    }

    /**
     * 查找 findNode 的父节点并返回
     * @param findNode 查找的节点对象
     * @return 返回 findNode 的父节点
     */
    public BinarySortTreeNode searchParentNode(BinarySortTreeNode findNode) {
        if (root == null) {
            return null;
        }
        if (findNode == root) {
            return root;
        }
        Queue<BinarySortTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinarySortTreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node.left != null) {
                if (node.left == findNode) {
                    break;
                }else {
                    queue.add(node.left);
                }
            }
            if (node.right != null) {
                if (node.right == findNode) {
                    break;
                }else {
                    queue.add(node.right);
                }
            }
        }
        return node;
    }


    /**
     * 二叉排序树管理类的添加有序的添加结点的方法
     * @param addNode 待添加的节点对象
     */
    public void addNode(BinarySortTreeNode<E> addNode) {
        if (root == null) {
            root = addNode;
        }else {
            BinarySortTreeNode<E> node = root;
            while (true) {
                if (addNode.compareTo(node) == 1) {  // 如果添加的节点的 data 值大于 node 的 data 值就放在 node 的右节点位置
                    if (node.right != null) {
                        node = node.right;
                    }else {
                        node.right = addNode;
                        break;
                    }
                    // 如果添加的新节点 addNode 的 data 值小于等于 node 的 data 值就放在 node 的左节点位置
                }
                if (addNode.compareTo(node) == -1 || addNode.compareTo(node) == 0) {
                    if (node.left != null) {
                        node = node.left;
                    }else {
                        node.left = addNode;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 层序遍历顺序二叉树，同时将其按照层序的顺序存入 List<List<BinarySortTreeNode>> 集合中并返回
     * @return 返回层序顺序二叉树的节点集合
     */
    public List<List<BinarySortTreeNode>> sequencePrint() {
        List<List<BinarySortTreeNode>> lists = new ArrayList<>();
        if (root == null) {
            System.out.println("root为空");
            return lists;
        }
        Queue<BinarySortTreeNode> queue = new LinkedList<>();
        queue.add(root);
        System.out.println("root = "+root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<BinarySortTreeNode> list = new ArrayList<>();
            for (int i = 0; i < size; i ++) {
                BinarySortTreeNode node = queue.poll();
                list.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            lists.add(list);
        }
        System.out.println("层序遍历完毕");
        return lists;
    }

}

// 二叉排序树的节点类对象
class BinarySortTreeNode<E> implements Comparable<BinarySortTreeNode> {
    E data;
    BinarySortTreeNode<E> left;
    BinarySortTreeNode<E> right;

    public BinarySortTreeNode(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(BinarySortTreeNode o) {
        if ((int)this.data > (int)o.data) {
            return 1;
        }else if ((int)this.data < (int)o.data) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "data=" + data +
                '}';
    }
}

