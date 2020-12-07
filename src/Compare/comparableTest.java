package Compare;

/**
 *  实现 comparable 接口，是一个类的属于自己的一个比较方法的重写
 */
public class comparableTest implements Comparable<comparableTest>{

    private int age;

    private int height;

    private String name;

    public comparableTest(int age, int height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public int compareTo(comparableTest otherComparableObject) {
        if (this.age > otherComparableObject.age){
            return 1;
        }else if (this.age == otherComparableObject.age){
            if (this.height > otherComparableObject.height){
                return 1;
            }else if (this.height < otherComparableObject.height){
                return -1;
            }
        }else if (this.age < otherComparableObject.age){
            return -1;
        }
        return 0;
    }
}

class comparableTestClass{
    public static void main(String[] args) {
        comparableTest test1 = new comparableTest(18,159,"王梦婷");
        comparableTest test2 = new comparableTest(19,160,"王梦婷");
        comparableTest test3 = new comparableTest(20,180,"石燔");
        comparableTest test4 = new comparableTest(21,182,"石燔");
        comparableTest test5 = new comparableTest(21,182,"石燔");
        System.out.println(test1.compareTo(test2));
        System.out.println(test3.compareTo(test2));
        System.out.println(test4.compareTo(test5));
    }
}
