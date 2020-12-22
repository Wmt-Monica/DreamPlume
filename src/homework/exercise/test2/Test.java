package homework.exercise.test2;

/**
 * 2、请实现有序单链表(一般的单链表对结点插入位置没有要求；对于有序单链
 *    表，结点插入后，单链表中各结点是有序排列的)。（出题人：崔延斌）
 */
public class Test {
    public static void main(String[] args) {
        LinkMange mange = new LinkMange();
        for (int i = 0; i < 10; i ++) {
            mange.add(new LinkNode((int)(Math.random()*30+1)));
        }
        mange.printLinkNodes();
    }
}

class LinkMange {
    LinkNode head = new LinkNode(-1);  // 表头链表,不存放实际数据
    public void add(LinkNode addLinkNode) {
        LinkNode step = head;
        while (step.getNext() != null) {
            if (step.getNext().getData() > addLinkNode.getData()) {
                addLinkNode.setNext(step.getNext());
                step.setNext(addLinkNode);
                return;
            }
            step = step.getNext();
        }
        step.setNext(addLinkNode);
    }

    public void printLinkNodes() {
        LinkNode step = head;
        while (step.getNext() != null) {
            System.out.println(step.getNext());
            step = step.getNext();
        }
    }
}

class LinkNode implements Comparable<LinkNode> {
    private int data;

    private LinkNode next;

    public LinkNode(int data) {
        this.data = data;
    }

    public LinkNode getNext() {
        return next;
    }

    public void setNext(LinkNode next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public int compareTo(LinkNode linkNode) {
        if (this.data > linkNode.data) {
            return 1;
        }else if (this.data < linkNode.data) {
            return -1;
        }else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "data=" + data +
                '}';
    }
}