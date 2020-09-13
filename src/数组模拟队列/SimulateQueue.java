package 数组模拟队列;

public class SimulateQueue {
    public static void main(String[] args) {
        ArrayQueue array = new ArrayQueue(10);
        for (int i=0;i<=8;i++){
            array.AddArray(i);
        }
        if (array.FullArray()){
            System.out.println("队列已满");
        }else {
            System.out.println("队列未满");
        }
        if (array.NullArray()){
            System.out.println("队列为空");
        }else {
            System.out.println("队列不为空");
        }
        array.AddArray(13);
        //     array.AddArray(14);
        array.ShowArray();
        array.ShowHeadArray();
        array.GetArray();
        if (array.NullArray()){
            System.out.println("队列为空");
        }
    }
    //1.使用数组模拟队列——编写一个ArrayQueue类
    static class ArrayQueue{
        int maxSize;   //1）：表示队列的最大容量
        int font;  //2）：队列头
        int rear;  //3）：队列尾
        int array [];  //4）：创建一个数组存入数据，模拟队列
        //2.创造队列的构造器
        public ArrayQueue(int ArrayMacSize){
            maxSize = ArrayMacSize;  //存放数组最大容量
            array = new int[maxSize];  //初始化模拟数组对象
            font = -1;  //1）：font指向队列头，分析是指向队列头的前一个位置
            rear = -1;  //2）：rear只想队列尾，指向队列尾的数据，是队列的最后一个数据
        }
        //3.判断队列是否为满
        public boolean FullArray(){
            return (rear == maxSize-1);
        }
        //4.判断队列是否为空
        public boolean NullArray(){
            return (font==rear);
        }
        //5.添加队列数据
        public void AddArray(int data){
            //判断队列是否为满
            if (FullArray()){
                //抛出异常
                throw new RuntimeException("队列已满，不能再添加数据");
            }else {
                rear++;
                array[rear] = data;
            }
        }
        //6.获取队列数据，（出队列）
        public void GetArray(){
            //判断队列是否为空
            if (NullArray()){
                throw new RuntimeException("队列为空，没有数据可以输出");
            }else {
                System.out.println("\n队列中的数据为：");
                while (font!=maxSize-1){
                    font++;
                    System.out.print("array["+font+"]="+array[font]+"\t");
                }
                System.out.println();
            }
        }
        //7.显示所有数据
        public void ShowArray(){
            //判断队列是否为空
            if(NullArray()){
                throw new RuntimeException("队列为空，没有任何数据");
            }else {
                System.out.println("显示队列所有数据为：");
                for (int data: array
                ) {
                    System.out.print(data+"\t");
                }
                System.out.println();
            }
        }
        //8.显示队列头数据
        public void ShowHeadArray(){
            //判断队列是否为空
            if(NullArray()){
                throw new RuntimeException("队列为空，没有任何数据输出");
            }else {
                System.out.print("队列的头数据为："+array[font+1]+"\t");
            }
        }
    }
}
