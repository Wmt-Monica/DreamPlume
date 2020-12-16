package compare;

/**
 *  实现 comparable 接口，是一个类的属于自己的一个比较方法的重写
 */
public class ComparableTest implements Comparable<ComparableTest>{

    private int age;

    private int height;

    private String name;

    public ComparableTest(int age, int height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    @Override
    public int compareTo(ComparableTest otherComparableObject) {
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

class ComparableTestClass {
    public static void main(String[] args) {
        ComparableTest test1 = new ComparableTest(18,159,"王梦婷");
        ComparableTest test2 = new ComparableTest(19,160,"王梦婷");
        ComparableTest test3 = new ComparableTest(20,180,"石燔");
        ComparableTest test4 = new ComparableTest(21,182,"石燔");
        ComparableTest test5 = new ComparableTest(21,182,"石燔");
        System.out.println(test1.compareTo(test2));
        System.out.println(test3.compareTo(test2));
        System.out.println(test4.compareTo(test5));
    }
}
