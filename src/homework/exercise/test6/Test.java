package homework.exercise.test6;

/**
 * 6、经典面试题——约瑟夫环：编写一个程序求解约瑟夫（Joseph）问题。有 n
 *   个小孩围成一圈，给他们从 1 开始依次编号，从编号为 1 的小孩开始报数，数
 *   到第 m 个小孩出列，然后从出列的下一个小孩重新开始报数，数到第 m 个小孩
 *   又出列，…，如此反复直到所有的小孩全部出列为止，求整个出列序列。
 *   如当 n=6，m=5 时的出列序列是 5，4，6，2，3，1。并在 main 中给出测试用
 *   例，输出运行结果。（出题人：罗红梅、连斌）
 */
public class Test {
    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue();
        for (int i = 1; i <= 10; i ++) {
            circularQueue.add(new ChildNode(i));
        }
        circularQueue.printNodes();
        System.out.println("size = "+circularQueue.size());
        circularQueue.Joseph(3);
    }
}

class CircularQueue {
   ChildNode first;
   ChildNode last;

   // 约瑟夫环添加小孩节点
   public void add(ChildNode addNode) {
       if (first == null) {
           first = addNode;
           first.next = last;
           last = first;
           last.next = first;
       } else {
           ChildNode node = first;
           while (node.next != first) {
               node = node.next;
           }
           addNode.next = first;
           last.next = addNode;
           last = addNode;
       }
   }

   public void Joseph(int num) {
       ChildNode p = last;
       ChildNode step = first;
       int n = 1;
       int size = size();
       while (size != 0) {
           if (n == num) {
                System.out.println("第"+step.data+"序号的小孩出队");
                p.next = step.next;
                size --;
                n = 0;
           }
           p = p.next;
           step = step.next;
           n ++;
       }
   }

   // 打印约瑟夫环中的小孩节点
   public void printNodes() {
       if (first == null) {
           return;
       }
       ChildNode node = first;
       while (node.next != first) {
           System.out.print(node.data+" ");
           node = node.next;
       }
       System.out.println(node.data);
   }

   // 返回约瑟夫环的小孩个数
   public int size(){
       if (first == null) {
           return 0;
       }
       ChildNode node = first;
       int size = 1;
       while (node.next != first) {
           size ++;
           node = node.next;
       }
       return size;
   }
}

// 孩子节点
class ChildNode {
    int data;
    ChildNode next;

    public ChildNode(int data) {
        this.data = data;
    }
}