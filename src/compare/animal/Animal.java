package compare.animal;

/**
 * 动物类
 */
public class Animal implements Comparable<Animal> {

    private int weight;  // 动物类属性体重
    private int height;  // 动物类属性身高
    private int age;  // 动物类属性年龄
    private enum sex {公,母}  // 动物类属性性别
    private String name;  // 动物类属性名字

    public Animal(int weight, int height, int age, String name) {
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.name = name;
    }

    /**
     * 比较逻辑：先比较年龄，年龄相等的情况下比较身高，身高相等的情况下比较体重
     * @param animal 比较对象属于 Animal 类
     * @return 返回 this 与 animal 的比较关系
     */
    @Override
    public int compareTo(Animal animal) {
        if (this.age > animal.age) {
            return 1;
        }else if (this.age < animal.age) {
            return -1;
        }else {
            if (this.height > animal.height) {
                return 1;
            }else if (this.height < animal.height) {
                return -1;
            }else {
                if (this.weight > animal.weight) {
                    return 1;
                }else if (this.weight < animal.weight) {
                    return -1;
                }
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "weight=" + weight +
                ", height=" + height +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}'+"\n";
    }
}
