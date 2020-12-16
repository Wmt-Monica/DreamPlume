package compare;

import java.util.Comparator;

/**
 * 实现 comparator 接口，是专门的实现一个类用于对象间的两个实现对象的比较
 */
public class ComparatorTest implements Comparator<ComparatorTestClass> {

    @Override
    public int compare(ComparatorTestClass t1, ComparatorTestClass t2) {
        if (t1.age > t2.age){
            return 1;
        }else if (t1.age == t2.age){
            if (t1.height > t2.height){
                return 1;
            }else if (t1.height < t2.height){
                return -1;
            }
        }else if (t1.age < t2.age){
            return -1;
        }
        return 0;
    }
}

class ComparatorTestClass {
    int age;
    int height;
    String name;

    public ComparatorTestClass(int age, int height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public static void main(String[] args) {
        ComparatorTestClass c1 = new ComparatorTestClass(18,159,"王梦婷");
        ComparatorTestClass c2 = new ComparatorTestClass(19,160,"王梦婷");
        ComparatorTestClass c3 = new ComparatorTestClass(20,180,"石燔");
        ComparatorTestClass c4 = new ComparatorTestClass(21,182,"石燔");
        ComparatorTestClass c5 = new ComparatorTestClass(21,182,"石燔");
        System.out.println(new ComparatorTest().compare(c1,c2));
        System.out.println(new ComparatorTest().compare(c4,c3));
        System.out.println(new ComparatorTest().compare(c5,c4));
    }

}
