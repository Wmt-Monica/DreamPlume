package compare.animal;

import java.util.Arrays;

/**
 * 动物类对象的插入排序算法
 */
public class AnimalTest {
    public static void main(String[] args) {
        Animal[] animals = new Animal[10];
        for (int i = 0; i < 10; i ++) {
            animals[i] = new Animal(((int)(Math.random()*100)+1),
                    (int)(Math.random()*100+1),
                    ((int)(Math.random()*50+1)),
                    ("animal"+i));
        }
        new AnimalTest().insertSort(animals);
        System.out.println(Arrays.toString(animals));
    }
    public void insertSort(Animal[] animals) {
        for (int i = 1,j; i < animals.length; i ++) {
            Animal step = animals[i];
            for (j = i; j > 0 && (animals[j-1].compareTo(step) == -1); j --) {
                animals[j] = animals[j-1];
            }
            animals[j] = step;
        }
    }
}
