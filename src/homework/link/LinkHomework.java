package homework.link;

import java.util.Stack;

public class LinkHomework {
    public static void main(String[] args) {
        ManageLink manageLink = new ManageLink();
        for (int i = 0; i < 10; i ++){
            manageLink.addLink(new Link(i));
        }
//        manageLink.printLinkList();
//        manageLink.reverse2();
//        manageLink.printLinkList();
//        manageLink.deleteNodes(5);
        manageLink.insertAt(4,22);
        manageLink.printLinkList();
    }
}

// 管理链表的类
class ManageLink {
    Link link = new Link(-1);  // 创建头链表

    // 添加链表
    public void addLink(Link newLink) {
        Link step = link;
        if (isEmpty()){
            step.next = newLink;
        }else {
            while (step.next != null) {
                step = step.next;
            }
            step.next = newLink;
            newLink.next = null;
        }
    }

    // 判断链表是否为空
    public boolean isEmpty() {
        if (link.next == null) {
            return true;
        }
        return false;
    }

    // 逆序链表(非头插法)
    public void reverse() {
        Stack<Link> stack = new Stack();
        Link step = link;
        while (step.next != null){
            stack.add(step.next);
            step = step.next;
        }

        Link t = link;
        while (!stack.isEmpty()) {
            t.next = stack.pop();
            System.out.println(t.next);
            t = t.next;
        }
        t.next = null;
    }

    //逆序链表(头插法)
    public void reverse2() {
       Link qLink = link.next;
       link.next = null;
       while (qLink != null){
           Link pLink = link.next;
           link.next = qLink;
           qLink = qLink.next;
           link.next.next = pLink;
       }
    }

    // 打印链表
   public void printLinkList() {
        Link t = link;
        while (t.next != null) {
            System.out.println(t.next.data);
            t = t.next;
        }
   }

   // 删除单链表第i个节点，以及第i+1个节点
    public void deleteNodes(int i) {
        Link step = link;
        for (int j = 1; step.next != null; j ++) {
            if (j != i){
                step = step.next;
            }else {
                step.next = step.next.next.next;
            }
        }
    }

    // 在单链表的第i-1个节点前，插入值为X的结点的算法
    public boolean insertAt(int i, int x) {
        Link step = link;
        for (int j = 0; step.next != null; j ++){
            if (j == i-2){
                Link addLink = new Link(x);
                addLink.next = step.next;
                step.next = addLink;
                return true;
            }
            step = step.next;
        }
        return false;
    }


}

// 链表类
class Link {
    int data;
    Link next;

    public Link(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Link{" +
                "data=" + data +
                '}';
    }
}