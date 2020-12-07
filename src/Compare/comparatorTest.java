package Compare;

import java.util.Comparator;

/**
 * 实现 comparator 接口，是专门的实现一个类用于对象间的两个实现对象的比较
 */
public class comparatorTest implements Comparator<comparatorTestClass> {

    @Override
    public int compare(comparatorTestClass t1, comparatorTestClass t2) {
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

class comparatorTestClass{
    int age;
    int height;
    String name;

    public comparatorTestClass(int age, int height, String name) {
        this.age = age;
        this.height = height;
        this.name = name;
    }

    public static void main(String[] args) {
        comparatorTestClass c1 = new comparatorTestClass(18,159,"王梦婷");
        comparatorTestClass c2 = new comparatorTestClass(19,160,"王梦婷");
        comparatorTestClass c3 = new comparatorTestClass(20,180,"石燔");
        comparatorTestClass c4 = new comparatorTestClass(21,182,"石燔");
        comparatorTestClass c5 = new comparatorTestClass(21,182,"石燔");
        System.out.println(new comparatorTest().compare(c1,c2));
        System.out.println(new comparatorTest().compare(c4,c3));
        System.out.println(new comparatorTest().compare(c5,c4));
    }

}
