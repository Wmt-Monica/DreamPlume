package homework.strwork.chainString;

public interface strMethod {
    int indexOf(String str);                 // 求串str的位置
    void replace(String str1, String str2);   // 将串中包含的str1替换成str2
    void toLowerCase();                       // 转换成小写
    void toUpperCase();                       // 转换成大写
    void concat(String str);                  // 串连接
}
