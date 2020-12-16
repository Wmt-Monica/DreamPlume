package homework.strStack;

public class StringStackDemo implements StringStack{
    StackNode font;
    StackNode rear;


    @Override
    public void push(StackNode newNode) {
        if ( rear == null ) {
            rear = newNode;
            font.next = rear;
            newNode.next = null;
        }else{
            rear.next = newNode;
            newNode.next = null;
            rear = newNode;
        }
    }

    @Override
    public void pop() {

    }


}

//创建节点对象
class StackNode{
    String s;
    StackNode next;

    public StackNode(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "s='" + s + '\'' +
                '}';
    }
}