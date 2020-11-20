package homework.strwork.chainString;


public class strMain implements strMethod{
    char[] data;

    //构造器
    public strMain(String s) {
        this.data = s.toCharArray();
    }

    public static void main(String[] args) {
        String s = "Fvsd sfFwfsv SGFsadGS";
        strMain str = new strMain(s);
        System.out.println(str.indexOf("fF"));
        System.out.println("\n------------------------------------");

        str.replace("wf","WMT");
        System.out.println(str.data);
        System.out.println("\n------------------------------------");

        str.toLowerCase();
        System.out.println(str.data);
        System.out.println("\n------------------------------------");

        str.toUpperCase();
        System.out.println(str.data);
        System.out.println("\n------------------------------------");

        str.concat("_WMT_AND_SF_1314520");
        System.out.println(str.data);
    }


    // 求串str的位置
    @Override
    public int indexOf(String str) {
        char[] strChar = str.toCharArray();
        for (int i = 0; i < data.length; i ++){
            if (data[i] == strChar[0]){
                boolean flag = true;  //判断是否为子串的位置
                for (int j = 0; j < strChar.length; j ++){
                    if (data[i+j] != strChar[j]){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    return i;
                }
            }
        }
        return -1;
    }

    // 将串中包含的str1替换成str2
    @Override
    public void replace(String str1, String str2) {
        int seat = indexOf(str1);
        String newStr = "";
        while (seat != -1){
            int seatEnd = seat + str1.length();

            char[] stepBehind = substring(seatEnd,data.length-1);

            if (seat != 0){
                char[] stepFront = substring(0,seat-1);
                newStr =  newStr + new String(stepFront) + str2 + new String(stepBehind);
            }else {
                newStr =  newStr + new String(stepBehind);
            }
            data = newStr.toCharArray();
            seat = indexOf(str1);
        }
    }

    // 转换成小写
    @Override
    public void toLowerCase() {
        for (int i = 0; i < data.length; i ++){
            if (data[i] >= 'A' && data[i] <= 'Z'){
                data[i] += 32;
            }
        }
    }

    // 转换成大写
    @Override
    public void toUpperCase() {
        for (int i = 0; i < data.length; i ++){
            if (data[i] >= 'a' && data[i] <= 'z'){
                data[i] -= 32;
            }
        }
    }

    // 串连接
    @Override
    public void concat(String str) {
        String s = new String(data) + str;
        data = s.toCharArray();
    }

    //获取start索引到end索引之间的数据
    public char[] substring(int start,int end){
        char[] step = new char[end-start+1];
        for (int i = start,k = 0; i <= end; i++){
            step[k++] = data[i];
        }
        return step;
    }
}
