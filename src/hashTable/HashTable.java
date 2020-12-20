package hashTable;

/**
 * 哈希表
 */
public class HashTable {
    private enum SEX {男,女}
    public static void main(String[] args) {
        StuHashTable stuHashTable = new StuHashTable(12);
        for (int i = 1; i <= 12; i ++) {
            for (int j = 1; j <= 10; j ++) {
                if (i < 10) {
                    if (j < 10){
                        stuHashTable.add(new Student("2019000100"+i+"0"+j,"wmt"+i+"班"+j,SEX.女));
                    }else {
                        stuHashTable.add(new Student("2019000100"+i+j,"wmt"+i+"班"+j,SEX.女));
                    }
                }else {
                    if (j < 10){
                        stuHashTable.add(new Student("201900010"+i+"0"+j,"wmt"+i+"班"+j,SEX.女));
                    }else {
                        stuHashTable.add(new Student("201900010"+i+j,"wmt"+i+"班"+j,SEX.女));
                    }
                }
            }
        }
//        stuHashTable.printStu();
//        stuHashTable.search("2019000101008");
        stuHashTable.delete("2019000101008");
        stuHashTable.printStu();

    }

    // 学生类
    static class Student {
        private String id;
        private String name;
        private SEX sex;
        private Student next;

        public Student(String id, String name, SEX sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sex=" + sex +
                    '}';
        }
    }

    //  属于一个班级的学生组成一个链表
    static class StuList {
        Student head;

        // 添加学生对象
        public void add(Student stu) {
            if (head == null) {
                head = stu;
            }else {
                Student step = head;
               while (true) {
                   if (step.next == null) {
                       step.next = stu;
                       break;
                   }else {
                       step = step.next;
                   }
               }
            }
        }

        // 遍历学生链表
        public void printStu() {
            Student step = head;
            while (step != null) {
                System.out.println(step);
                step = step.next;
            }
        }

        // 输入学生的 ID 寻找学生信息
        public void search(String stuID) {
            Student step = head;
            if (step == null){
                System.out.println("查无此人，查询失败");
                return;
            }
            while (true) {
                if (step.id.equals(stuID)) {
                    System.out.println(step);
                    break;
                }else {
                    step = step.next;
                    if (step == null) {
                        System.out.println("查无此人，查询失败");
                        break;
                    }
                }
            }
        }

        // 输入学生的id号在链表中删除学生信息
        public void delete(String stuID) {
            Student step = head;
            if (step == null) {
                System.out.println("无相关学生信息，删除失败");
            }else if (step.id.equals(stuID)) {
                step.next = head;
            }else {
                while (step.next != null){
                    if (step.next.id.equals(stuID)) {
                        step.next = step.next.next;
                        System.out.println("删除成功");
                        return;
                    }
                    step = step.next;
                }
                System.out.println("无相关学生信息，删除失败");
            }
        }
    }

    //  创建哈希表，将各个班级的学生链表添加入一个数组中
    static class StuHashTable {
        private StuList[] stuHashTable;
        int ClassNums;

        public StuHashTable(int classNums) {
            ClassNums = classNums;
            stuHashTable = new StuList[ClassNums];
            for (int i = 0; i < ClassNums; i ++) {
                StuList stuList = new StuList();
                stuHashTable[i] = stuList;
            }
        }

        // 将学生添加入哈希班级表中对应的链表中去
        public void add(Student stu) {
            int stuClass = (stu.id.charAt(stu.id.length()-4)-'0')*10+(stu.id.charAt(stu.id.length()-3)-'0');
            stuHashTable[stuClass-1].add(stu);
        }

        // 打印哈希表中的各个班级的学生
        public void printStu() {
            for (int i = 0; i < stuHashTable.length; i ++) {
                stuHashTable[i].printStu();
                System.out.println("===============================");
            }
        }

        // 输入学生的id号来查找学生信息
        public void search(String stuID) {
            int stuClass = (stuID.charAt(stuID.length()-4)-'0')*10+(stuID.charAt(stuID.length()-3)-'0');
            stuHashTable[stuClass-1].search(stuID);
        }

        // 输入学生id号来在哈希表中删除学生信息
        public void delete(String stuID) {
            int stuClass = (stuID.charAt(stuID.length()-4)-'0')*10+(stuID.charAt(stuID.length()-3)-'0');
            stuHashTable[stuClass-1].delete(stuID);
        }
    }
}

