package homework.strwork.sequentialString;

public interface StrMethod {
    char charAt(int pos);                  // 求pos位置的字符
    int length();                          // 获取字符串的长度
    char[] substring(int start, int end);  // 求子串
    boolean contains(String str);          // 判断是否包含串str
    boolean equals(String str);            // 判断是否相等
    String[] split(String regex);          // 字符串分割
    void trim();                           // 去掉字符串首尾两端的空格
    int remove(String s);                  //去除字符串中某个字符串串，假如存在子字符串就返回1，否则返回0
}
