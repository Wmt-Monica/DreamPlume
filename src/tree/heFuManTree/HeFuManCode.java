package tree.heFuManTree;

import java.util.*;

/**
 * 赫夫曼编码：
 *      1）：利用赫夫曼树的特性构造前缀编码（前缀编码：任何字符的编码都不是其他字符编码的前缀）
 *      2）：利用Map集合将其赫夫曼编码解密
 */
public class HeFuManCode {
    public static void main(String[] args) {
        HeFuManCode heFuManCode = new HeFuManCode();
        List<Node<Character>> nodeList = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            nodeList.add(new Node((char)(Math.random()*('Z'-'A')+'A'),(int)(Math.random()*100)));
        }
        System.out.println("=============初始List集合==============");
        System.out.println(nodeList);
        System.out.println("=============快速排序后的List集合==============");
        heFuManCode.quickSortHeFuManTree(nodeList, 0, nodeList.size()-1);
        System.out.println(nodeList);
        System.out.println("=============创建赫夫曼树============");
        Node root = heFuManCode.createHeFuManTree(nodeList);
        System.out.println("赫夫曼树创建成功....");
        System.out.println("=============层序遍历赫夫曼树============");
        List<List<Node>> nodeLists = heFuManCode.sequencePrint(root);
        for (List<Node> nodeList1 : nodeLists) {
            for (Node node : nodeList1) {
                System.out.print(node+" ");
            }
            System.out.println();
        }


        System.out.println("=============使用赫夫曼编码将字符串转换成编码============");

        String str = "Cloud like clothes flower like appearance";  // 云想衣裳花想容
        System.out.println("=============创造并层序遍历赫夫曼树============");
        Node root1 = heFuManCode.createCtrCodesTree(str);
        List<List<Node>> nodeLists1 = heFuManCode.sequencePrint(root1);
        for (List<Node> nodeList2 : nodeLists1) {
            for (Node node : nodeList2) {
                System.out.print(node+" ");
            }
            System.out.println();
        }
        Map<Character, String> ctrNodesMap;
        ctrNodesMap = heFuManCode.createCtrCodes(root1);

        System.out.println("==========将根据要传输数据的字符创建的赫夫曼树的字符对应的赫夫曼编码==========");
        for (Map.Entry<Character,String> entry : ctrNodesMap.entrySet()) {
            System.out.println("char = "+entry.getKey()+"\tcode = "+entry.getValue());
        }

        System.out.println("========将原始字符串使用ctrNodesMap集合中的字符集编码进行转译========");
        String strCodes = heFuManCode.createHeFuManCodes(str, ctrNodesMap);
        System.out.println(strCodes);

        System.out.println("========将赫夫曼编码使用ctrNodesMap集合中的字符集编码进行解密========");
        String originalStr = heFuManCode.heFuManCodeToString(strCodes, ctrNodesMap);
        System.out.println(originalStr);

        System.out.println("尝试使用包装好的赫夫曼编码方法 createHeFuManCode() 方法来返回编码来进行比较");
        String srcHeFuManCode = heFuManCode.createHeFuManCode(str);
        System.out.println(srcHeFuManCode.equals(strCodes));
    }

    Map<Character,String> heFuManCtrCodes = null;  // 创建成员变量赫夫曼编码
    public String createHeFuManCode(String data) {
        // 1.根据传输数据的字符来创建赫夫曼树
        Node root = createCtrCodesTree(data);
        // 2.根据创建的赫夫曼树创建 Map 赫夫曼编码集合
        heFuManCtrCodes = createCtrCodes(root);  // 此处成员变量赫夫曼编码完成了初始化
        // 3.将发送数据 data 根据创建的赫夫曼编码 Map 集合生成对象的赫夫曼编码字符串并返回
        String dataHeFuManCodes = createHeFuManCodes(data, heFuManCtrCodes);
        return dataHeFuManCodes;
    }

    /**
     * 将压缩转译之后的字符串编码转换成
     * @param heFuManCode
     * @param ctrNodesMap 要解密字符串相应的 Map 字符对应赫夫曼编码集合
     * @return 返回使用赫夫曼编码字符串的原始字符串
     */
    public String heFuManCodeToString(String heFuManCode, Map<Character,String> ctrNodesMap) {
        // 创建 StringBuild 类的 result 对象方便使用 append() 方法连接每串编码所对应的字符
        StringBuilder result = new StringBuilder();
        StringBuilder ctrCode = new StringBuilder();  // 用于在 ctrNodesMap 集合寻找相应字符的
        for (int i = 0; i < heFuManCode.length(); i ++) {
            // 变量 i 用于遍历赫夫曼编码
            ctrCode.append(heFuManCode.charAt(i));  // 用于拼接还未找到对应字符的赫夫曼编码
            // 因为对应的字符的赫夫曼编码是前缀编码所以可以使用边拼接边在 Map 集合中寻找对应字符的操作
            if (ctrNodesMap.containsValue(ctrCode.toString())) {
                char ctr = findCtr(ctrCode.toString(), ctrNodesMap);
                result.append(ctr);  // 根据寻找到的字符使用 append() 方法拼接到 result 中去
                ctrCode = new StringBuilder();  // 初始化 ctrCode 对象方便寻找下一个赫夫曼所对应的字符
            }
        }
        return result.toString();
    }

    /**
     * 使用 Entry 方法遍历 Map 集合，根据参数 value 值寻找对应的 key 值
     * @param value ctrNodesMap 字符赫夫曼编码的 Map 集合
     * @param ctrNodesMap 赫夫曼编码对应的 Map 集合
     * @return 返回参数 value 在 ctrNodesMap 中对应的 key 值
     */
    public char findCtr(String value,Map<Character,String> ctrNodesMap) {
        char result = 0;
        for (Map.Entry<Character,String> entry : ctrNodesMap.entrySet()) {
            if (entry.getValue().equals(value)) {
                result = entry.getKey();
                break;
            }
        }
        return result;
    }

    /**
     * 将对应的字符串和相应的赫夫曼编码 Map 集合中编码进行转译
     * @param str 未进行编码的原始字符串
     * @param map 根据传输字符串数据创建的赫夫曼 Map 集合
     * @return 返回使用赫夫曼编码后的字符串
     */
    public String createHeFuManCodes(String str, Map<Character,String> map) {
        // 创建 StringBuilder 类对象 result 用户拼接原始字符串中对应的赫夫曼编码，方便使用 append() 进行拼接
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i ++) {  // 使用 for 循环遍历 str 字符串中的每一个字符
            char c = str.charAt(i);
            result.append(map.get(c));  // 为了方便打印看到效果在每一个编码之后加上一个空格
        }
        return result.toString();
    }

    /**
     * 规定赫夫曼树中向左表示编码 0 ，向右表示编码 1，广度遍历赫夫曼树将节点的赫夫曼编码重新赋值
     * @param root 根据传输数据的字符所创建的赫夫曼树
     */
    public Map<Character,String> createCtrCodes(Node<Character> root) {
        Queue<Node<Character>> queue = new LinkedList<>();  // 创建方便对赫夫曼树进行广度遍历的辅助队列 queue
        Map<Character,String> map = new HashMap<>();  // 存放各个字符的在赫夫曼树中对应的赫夫曼编码
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            Node<Character> node = queue.poll();
            if (node.left != null) {
                node.left.code += node.code+"0";
                if (node.left.data != null) {
                    map.put((Character) node.left.data,node.left.code);
                }
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.code += node.code+"1";
                if (node.right.data != null) {
                    map.put((Character) node.right.data,node.right.code);
                }
                queue.add(node.right);
            }
        }
        return map;
    }

    /**
     * 根据对应的传输的字符串创建相应的赫夫曼树
     * @param str 将要传输数据的字符串
     * @return 返回根据要传输的数据而创建的赫夫曼树
     */
    public Node<Character> createCtrCodesTree(String str) {
        char[] ctrs = str.toCharArray();
        List<Node<Character>> ctrNodes = new ArrayList();
        Map<Character, Integer> ctrNodesMap = new HashMap<>();
        for (char ctr : ctrs) {
            Integer value = ctrNodesMap.get(ctr);
            if (value == null) {
                ctrNodesMap.put(ctr, 1);
            }else {
                ctrNodesMap.put(ctr, value+1);
            }
        }
        for (Map.Entry<Character,Integer> entry : ctrNodesMap.entrySet()) {
            ctrNodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        //  将 Node 节点类的 List 集合使用快速降序排序
        quickSortHeFuManTree(ctrNodes,0, ctrNodes.size()-1);
        Node root = createHeFuManTree(ctrNodes);
        System.out.println("赫夫曼树创建成功....");
        return root;
    }

    /**
     * 采用快速排序将节点类List集合按照节点的power进行降序排序
     * @param nodeList 节点类List集合
     * @param start 快速排序的起始
     * @param end 快速排序的结尾
     */
    public void quickSortHeFuManTree(List<Node<Character>> nodeList, int start, int end) {
        if (start < end) {
            int left = start;
            int right = end+1;
            Node baseNode = nodeList.get(start);
            while (true) {
                while (left < end && nodeList.get(++ left).power >= baseNode.power);
                while (right > start && nodeList.get(-- right).power <= baseNode.power);
                if (left < right) {
                    swap(nodeList, left, right);
                }else {
                    break;
                }
            }
            swap(nodeList, start, right);
            quickSortHeFuManTree(nodeList, start, right-1);
            quickSortHeFuManTree(nodeList, right+1, end);
        }
    }

    /**
     * 将 nodeList 中对应下标的 i 和 j 中的节点进行交换
     * @param nodeList 节点类List集合
     * @param i nodeList集合的下标 i
     * @param j nodeList集合的下标 j
     */
    public void swap(List<Node<Character>> nodeList, int i, int j) {
        Node<Character> node1 = nodeList.get(i);
        Node<Character> node2 = nodeList.get(j);
        nodeList.set(i, node2);
        nodeList.set(j, node1);
    }

    /**
     * 创建赫夫曼树
     * @param nodeList 由节点的权值 power 降序的 List 集合
     * @return 返回创建好的赫夫曼树的根节点
     */
    public Node createHeFuManTree(List<Node<Character>> nodeList) {
       while (nodeList.size() >= 2) {
           Node left = nodeList.get(nodeList.size()-1);
           Node right = nodeList.get(nodeList.size()-2);
           Node parentNode = new Node(null, left.power+right.power);
           parentNode.left = left;
           parentNode.right = right;
           nodeList.remove(nodeList.size()-1);
           nodeList.remove(nodeList.size()-1);
           nodeList.add(parentNode);
           quickSortHeFuManTree(nodeList, 0, nodeList.size()-1);
       }

       return nodeList.get(0);
    }

    /**
     * 层序遍历赫夫曼树
     * @param root 赫夫曼树的根节点
     */
    public List<List<Node>> sequencePrint(Node root) {
        List<List<Node>> nodeLists = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Node> nodeList = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                Node step = queue.poll();
                nodeList.add(step);
                if (step.left != null) {
                    queue.add(step.left);
                }
                if (step.right != null) {
                    queue.add(step.right);
                }
            }
            nodeLists.add(nodeList);
        }
        return nodeLists;
    }

    public void sequenceCode(Node root, StringBuffer ctrCode, StringBuffer[] ctrCodes, int i) {
        if (root.left != null) {
            System.out.println("root.left.data = "+root.left.data+"\troot.left.power = "+root.left.power);
            ctrCode.append("1");
            if (root.left.data != null) {
                ctrCodes[i] = ctrCode;
                System.out.println("ctrCodes["+i+"] = "+ctrCodes[i]);
                i ++;
            }
            sequenceCode(root.left, ctrCode, ctrCodes, i);
        }
        if (root.right != null) {
            ctrCode.append("0");
            System.out.println("root.right.data = "+root.right.data+"\troot.right.power = "+root.right.power);
            if (root.right.data != null) {
                ctrCodes[i] = ctrCode;
                System.out.println("ctrCodes["+i+"] = "+ctrCodes[i]);
                i ++;
            }
            sequenceCode(root.right, ctrCode, ctrCodes, i);
        }
    }

    static class Node<E>{
        E data;
        int power;
        Node left;
        Node right;
        String code = "";  // 节点类在赫夫曼树种所对应的编码

        public Node(E data, int power) {
            this.data = data;
            this.power = power;
        }

        @Override
        public String toString() {
            return ""+data+"\t"+power+"||\t";
        }

    }

}
