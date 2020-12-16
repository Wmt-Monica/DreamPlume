package compare;

import java.util.*;

public class test {
    public static void main(String[] args) {
//        String[] departments = new String[] {"key1", "key2", "key2"};
//        Map<Integer, String> map = Arrays.stream(departments).collect(Collectors.toMap(String::hashCode, str -> str));

//        String[] departments = new String[] {"key1", "key2", null};
//        Map<Integer, String> map = Arrays.stream(departments).collect(Collectors.toMap(String::hashCode, str -> str, (v1, v2) -> v2));

//        List<String> list = new ArrayList<>(2);
//        list.add("WMT");
//        list.add("SF");
//        String[] array = list.toArray(new String[3]);
//        for (int i = 0; i < array.length; i ++) {
//            System.out.println(array[i]);
//        }
//        System.out.println("============================");
//        Map<Integer, String> map = Arrays.stream(array).collect(Collectors.toMap(String::hashCode, str -> str, (v1, v2) -> v2));

//        String[] str = new String[] { "WMT", "SF" };
//        List list = Arrays.asList(str);
//        list.add("SFSB");

//        List<Integer> list = new ArrayList<>();  // 创建存放 Integer 对象的 List 集合对象
//        for (int i = 0; i < 10; i ++) {  // 向 List 集合添加数据
//            list.add(i);
//        }
//        Iterator<Integer> iterator = list.iterator();  // 创建存放 Integer 对象的迭代器对象
//        while (iterator.hasNext()) {  // 利用迭代器遍历 List 集合对象，hasNext() 方法判断是否还有下一个对象
//            int item = iterator.next();  // next() 获取下一个对象的值
////            synchronized (iterator){  // 如果是高并发状态就要对其加上锁
//                if (item % 2 == 0) {  // 将 List 集合中偶数值移除
//                    iterator.remove();
//                }
////            }
//        }
//        for (int t : list)  // 使用 foreach 遍历 list 集合，将其剩余打印输出
//            System.out.println(t);


        Map<Integer,String> map = new HashMap<>();  // 创建 Map 的实现类 HashMap 对象 map
        for (int i = 0; i < 10; i ++) {  // 为 map 添加数据
            map.put(i,"WMT"+i);
        }
//        for (Map.Entry<Integer, String> entry : map.entrySet()) {  // 使用 entrySet 遍历 Map 类集合 KV
//            System.out.println("key= " + entry.getKey() + "\tvalue= " + entry.getValue());
//        }

        for (Integer key : map.keySet()) {
            String value = map.get(key);
            System.out.println("key= " + key + "\tvalue= " + value);
        }


    }
}
