package homework.strStack;

public class SimulateQueue {

    static class arrayMange implements Queue{
        //创建一个数组模拟队列
        int maxSize = 20;
        int array[] = new int[maxSize];
        int font;
        int rear;

        @Override
        public boolean isEmpty() {
            return font == rear;
        }

        @Override
        public boolean isFull() {
            return ((rear+1)%maxSize == font);
        }

        @Override
        public int getSize() {
            return maxSize;
        }

        @Override
        public void putSize(int maxSize) {
            this.maxSize = maxSize;
            this.array = new  int[maxSize];
        }

        @Override
        public void add(int value) {
            if (isFull()){
                throw new RuntimeException("队列已满");
            }else {
                rear = (rear+1)%maxSize;
                array[rear] = value;
                System.out.println("array["+rear+"]="+array[rear]);
                System.out.println("rear:"+rear);
                System.out.println("font:"+font);
            }
        }

        @Override
        public void delete() {
            if (isEmpty()){
                throw new RuntimeException("队列为空");
            }else {
                font = (font+1)%maxSize;
                System.out.println("font:"+font);
            }
        }

        @Override
        public int getValue(int num) {
            return array[(num+1)%maxSize];
        }

        @Override
        public void showAll() {
           System.out.println("font:"+((font+1)%maxSize));
           System.out.println("rear:"+((rear+1)%maxSize));
        }
    }

    public static void main(String[] args) {
        arrayMange mange = new arrayMange();
        //赋值
        System.out.println("队列是否为空："+mange.isEmpty());
        for (int i = 0; i < mange.maxSize-1; i++){
            mange.add(i);
        }
        System.out.println("==============删除===============");
        mange.delete();
        mange.delete();
        mange.delete();
//        mange.add(20);
//        mange.add(21);
//        mange.add(22);

        mange.showAll();

    }
}
