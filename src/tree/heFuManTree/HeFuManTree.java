package tree.heFuManTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HeFuManTree {
    public static void main(String[] args) {
        HeFuManTree heFuManTree = new HeFuManTree();
        List<Node> list = new LinkedList<>();
        for (int i = 0; i < 10; i ++) {
            list.add(new Node((char)(Math.random()*('Z'-'A')+'A'),((int)(Math.random()*100+1))));
        }
        heFuManTree.quickHeFuManList(list, 0, list.size()-1);
        System.out.println(list);
        // 创建哈夫曼树
        Node root = heFuManTree.createHeFuManTree(list);
        System.out.println("=====================================");
        // 层序遍历
        List<List<Node>> lists = heFuManTree.cenXuPrint(root);
        for (List<Node> list1 : lists){
            for (Node node : list1) {
                System.out.print(node+"\t");
            }
            System.out.println();
        }
        System.out.println("=====================================");
        List<Node> nodes = new HeFuManTree().printHaFuManTree(root);
    }

    /**
     * 对 Node  List 集合的排序方法
     * @param list 无序的 Node List 无序集合
     * @param start 要对 list 集合排序的起始位置
     * @param end 要对 list 集合排序的终点位置
     */
    public void quickHeFuManList(List<Node> list, int start, int end) {
        if (start < end) {
            int l = start;
            int r = end+1;
            int step = list.get(start).power;
            while (true) {
                while (l < end && list.get(++ l).power >= step);
                while (r > start && list.get(-- r).power <= step);
                if (l < r) {
                    swap(list, l , r);
                }else {
                    break;
                }
            }
            swap(list, start, r);
            quickHeFuManList(list, start, r-1);
            quickHeFuManList(list, r+1, end);
        }
    }

    /**
     * 将 Node 的 List 集合中第 i 个元素与第 j 个元素交换
     * @param nodeList Node 的 List 集合
     * @param i nodeList对应第 i 个 Node 元素
     * @param j nodeList 对应的第 j 个 Node 元素
     */
    public void swap(List<Node> nodeList, int i, int j) {
        int step = nodeList.get(i).power;
        nodeList.get(i).power = nodeList.get(j).power;
        nodeList.get(j).power = step;
    }

    public Node createHeFuManTree(List<Node> nodeList) {
        while (nodeList.size() >= 2) {
            Node left = nodeList.get(nodeList.size()-1);  // 获得左子树
            Node right = nodeList.get(nodeList.size()-2);  // 获得右子树
            Node prentNode = new Node(null, left.power+right.power);
            prentNode.left = left;
            prentNode.right = right;
            System.out.println("prentNode = "+prentNode+"\tleft = "+left+"\tright = "+right);
            nodeList.remove(nodeList.size()-1);
            nodeList.remove(nodeList.size()-1);
            nodeList.add(prentNode);
        }
        return nodeList.get(0);
    }

    public List<Node> printHaFuManTree(Node root) {
        List<Node> nodes = new LinkedList<>();
        if (root == null) {
            return nodes;
        }
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            if (queue.peek().data != null) {
                nodes.add(queue.peek());
                System.out.println(nodes.get(nodes.size()-1));
            }
            Node step = queue.poll();
            if (step.left != null) {
                queue.add(step.left);
            }
            if (step.right != null) {
                queue.add(step.right);
            }
        }
        return nodes;
    }

    public List<List<Node>> cenXuPrint(Node root) {
        List<List<Node>> nodeList = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Node> list = new LinkedList<>();
            for (int i = 0; i < size; i ++) {
                Node step = queue.poll();
                list.add(step);
                System.out.println("step = "+step+"\t");
                if (step.left != null) {
                    queue.add(step.left);
                }
                if (step.right != null) {
                    queue.add(step.right);
                }
            }
            nodeList.add(list);
        }
        return nodeList;
    }

    static class Node<E> {
        int power;
        E data;
        Node left;
        Node right;

        public Node(E data, int power) {
            this.power = power;
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "power=" + power +
                    ", data=" + data +
                    '}';
        }
    }
}
