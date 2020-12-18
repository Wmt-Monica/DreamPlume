package 数组模拟队列;

import java.util.Stack;

/**
 * 用栈来实现队列
 */
public class StackImpQueue {
    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        if (stackQueue.isEmpty()) {
            System.out.println("队列为空");
        }else {
            System.out.println("队列不为空");
        }
        for (int i = 0; i < 10; i ++) {
            stackQueue.enQueue(i);
        }
        if (stackQueue.isEmpty()) {
            System.out.println("队列为空");
        }else {
            System.out.println("队列不为空");
        }
        System.out.println("队列的长度"+stackQueue.size());
        while (!stackQueue.isEmpty()) {
            System.out.print(stackQueue.deQueue()+" ");
        }
    }

    /**
     * 利用栈来实现队列的操作
     */
    public static class StackQueue{

        private Stack<Integer> headQueue = new Stack<>();  // 创建栈 headQueue 来模拟入队
        private Stack<Integer> endQueue = new Stack<>();  // 创建栈 endQueue 来模拟出队列

        //入队
        public void enQueue(int element) {
            headQueue.push(element);  // 使用 headQueue 栈入队
        }
        //出队
        public int deQueue(){
            if (isEmpty()) {
                throw new RuntimeException("队列为空");
            }else {
                if (!endQueue.isEmpty()) {
                    return endQueue.pop();
                }else {
                    while (!headQueue.isEmpty()) {
                        endQueue.push(headQueue.pop());
                    }
                    return endQueue.pop();
                }
            }
        }
        //判空
        public boolean isEmpty() {
            if (headQueue.isEmpty() && endQueue.isEmpty()) {
                return true;
            }
            return false;
        }
        //获取队列中的元素个数
        public int size() {
            return headQueue.size()+endQueue.size();
        }
    }

}
