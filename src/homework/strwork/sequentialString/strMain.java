package homework.strwork.sequentialString;

//顺序串
public class strMain implements strMethod {
    char[] data;

    //构造函数，创建对象只需要传入一个字符串即可
    public strMain(String s) {
        this.data = s.toCharArray();
    }

    public static void main(String[] args) {
        String s = "   ihspao phsa asphdp  cdvsyuph   ";
        String s2 = "   ihspao phsa asphdp  cdvsyuph   ";
        String s3 = "   ihspao phsa ash   ";
        strMain str = new strMain(s);

        System.out.println(str.charAt(6));  //找到索引位置为6的字符
        System.out.println("\n------------------------------------------------------");

        System.out.println(str.length());  //返回字符串的长度
        System.out.println("\n------------------------------------------------------");

        System.out.println(str.substring(3,8));  //获取字符串索引从6到15的子串
        System.out.println("\n------------------------------------------------------");

        System.out.println(str.contains("ph"));  //看"ph"子串是否包含在字符春中
        System.out.println("\n------------------------------------------------------");

        System.out.println(str.equals(s2));  //看字符串是否和字符串s2相等
        System.out.println(str.equals(s3));  //看字符串是否和字符串s3相等
        System.out.println("\n------------------------------------------------------");

       String[] result = str.split("ph");  //按照字符串"ph"将字符串分割
        for (int i = 0; i < result.length; i ++){
            System.out.println("'"+result[i]+"'");
        }
        System.out.println("\n------------------------------------------------------");

        str.trim();
        System.out.println(str.data);
        System.out.println("\n------------------------------------------------------");
        str.remove("vsy");
        System.out.println(str.data);

    }

    // 求pos位置的字符
    @Override
    public char charAt(int pos) {
        return data[pos];
    }

    // 获取字符串的长度
    @Override
    public int length() {
        int j = 0;
        for (int i = 0; i < data.length && data[i] != '\0'; i ++){
            j = i;
        }
        return j+1;
    }

    // 求子串
    @Override
    public char[] substring(int start, int end) {
        char[] result = new char[end - start];
        for (int i = 0,step = start; i < (end - start); i ++){
            result[i] = data[step++];
        }
        return result;
    }

    // 判断是否包含串str
    @Override
    public boolean contains(String str) {
        int j = 0;
        for (int i = 0; i < length(); i ++){
            if (str.charAt(j) == data[i]){
                j++;
                if ( j == (str.length())){
                    return true;
                }
            }else {
                j = 0;
            }
        }
        return false;
    }

    // 判断是否相等
    @Override
    public boolean equals(String str) {
        for (int i = 0;i < length(); i ++){
            if (str.charAt(i) != data[i]){
                return false;
            }
        }
        return true;
    }

    // 字符串分割
    @Override
    public String[] split(String regex) {
        char[][] result = new char[length()][];  //创建分割后的字符二维数组
        int start = 0;
        int end = 0;
        int num = 0;
        String[] str = new String[0];
        String[] step = new String[0];

        boolean flag = false;
        for (int i = 0; i < length(); i ++){
            if (data[i] == regex.charAt(0)){
                for (int j = 0; j < regex.length(); j ++){
                    if (data[i+j] != regex.charAt(j)){
                        flag = false;
                        break;
                    }
                    flag = true;
                }
                if (flag){
                    str = new String[num+2];  //每次发现字符串中有要分割的字符串就重新定义str字符串
                    for (int k = 0; k < num; k ++){
                        str[k] = step[k];  //将预先存好的数据前面的已经分割好的字符串重新赋值
                    }
                    end = i;
                    int t = 0;

                    result[num] = substring(start,end);
                    str[num] = new String(result[num]);
                    start = end+regex.length();

                    result[num+1] = substring(start,length());
                    str[num+1] = new String(result[num+1]);
                    step = str;  //提前将分割好的数据存放在临时字符串数组中step
                    num++;
                }
            }
        }
        return str;

    }

    // 去掉字符串首尾两端的空格
    @Override
    public void trim() {
        int start = 0;  //首部空格个数
        boolean s = false;
        int end = 0;  //尾部空格个数
        boolean e = false;
        for (int i = 0,j = length()-1; i < length() || j > 0; i ++, j--){
            if (!s){
                if (data[i] == ' '){
                    start++;
                }else {
                    s = true;
                }
            }
            if (!e){
                if (data[j] == ' '){
                    end++;
                }else {
                    e = true;
                }
            }
        }

        char[] step = substring(start,length()-end);
        data = new char[length()-(start+end)+1];
        data = step;
    }

    //去除字符串中某个字符串串，假如存在子字符串就返回1，否则返回0
    @Override
    public int remove(String regex) {
        boolean flag = false;

        for (int i = 0; i < length(); i++) {
            if (data[i] == regex.charAt(0)) {
                for (int j = 0; j < regex.length(); j++) {
                    if (data[i + j] != regex.charAt(j)) {
                        flag = false;
                        break;
                    }
                    flag = true;
                }
                if (flag) {
                    if (i+regex.length() < length()){
                        for (int t = 0; t < (length() - (i + regex.length())); t ++){
                            data[i+t] = data[i+regex.length()+t];
                        }
                        data[length()-regex.length()] = '\0';
                    }
                }
            }
        }
        if (flag){
            return 1;
        }
        return 0;
    }

}
