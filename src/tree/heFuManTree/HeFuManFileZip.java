package tree.heFuManTree;

import java.io.*;
import java.util.Map;

/**
 * 利用赫夫曼编码来对文件进行压缩
 */
public class HeFuManFileZip {

    HeFuManCode heFuManCode = new HeFuManCode();  // 赫夫曼编码操作的类对象

    public static void main(String[] args) {
        String fileSrc = "D:/github上传的文件/Wmtprogram2/DreamPlume/File/1.txt";
        String fileZipSrc = "D:/github上传的文件/Wmtprogram2/DreamPlume/File/1.zip";
        String upZipFileSrc = "D:/github上传的文件/Wmtprogram2/DreamPlume/File/1(2).txt";
        new HeFuManFileZip().createFileZip(fileSrc, fileZipSrc);
        new HeFuManFileZip().UnzipHeFuManFile(fileZipSrc, upZipFileSrc);
    }

    /**
     * 将原始文件按照赫夫曼编码的形式进行压缩
     * @param fileSrc 初始原件完整路径
     * @param fileZipSrc 压缩后的文件的完整路径
     */
    public void createFileZip(String fileSrc, String fileZipSrc) {
        File oldFile = new File(fileSrc);  // 根据未压缩文件的 String 路径创建 File 文件对象
        File newFile = new File(fileZipSrc);  // 根据压缩后文件存放的指定 String 路径创建 File 对象
        if (!newFile.exists()) {  // 如果该文件不存在就创建
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStream input = null;  // 创建输入流对象
        OutputStream output = null;  // 创建输出流对象
        ObjectOutputStream objectOutput = null; // 创建类对象输出流对象
        /*
           这里子啊输出流对象的基础上再创建对象输出流的对象的方式写入赫夫曼编码，是为了以后我们在恢复文件时使用
           注意：一定要把赫夫曼编码写入压缩文件，不然之后解压文件无法根据对应的赫夫曼编码进行解压
         */
        try {
            input = new BufferedInputStream(new FileInputStream(oldFile));  // 根据原始文件的 File 对象初始化输入流
            output = new FileOutputStream(newFile);  // 根据压缩文件的 File 对象初始化输出流
            objectOutput = new ObjectOutputStream(output);  // 根据压缩文件的 File 对象初始化对象输出流对象
            byte[] data = new byte[input.available()];  // 创建一个和源文件一样大小的 byte[] 数组
            System.out.println("data.length = "+data.length);
            input.read(data);  // 输入流对象 input 读取文件 oldFile 中的数据存入 data 数组中
            String s = new String(data, 0, data.length);
            System.out.println("s = "+s);
            String dataHeFuManCodes = heFuManCode.createHeFuManCode(s);
            System.out.println("dataHeFuManCodes = "+dataHeFuManCodes);
            objectOutput.writeObject(dataHeFuManCodes);  // 将赫夫曼编码的字符串转换成字节数组写入文件中
            objectOutput.writeObject(heFuManCode.heFuManCtrCodes);  // 将对应该原始文件创建的赫夫曼编码Map集合也写入压缩后的文件中
            objectOutput.flush();  // 刷新文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {  // 最后进行关闭所有流的操作
            try {
                input.close();
                output.close();
                objectOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("压缩文件创建成功");
        }
    }

    public void UnzipHeFuManFile(String heFuManZipFileSrc, String newFileSrc) {
        InputStream input;
        ObjectInputStream objectInput;
        OutputStream output;
        try {
            input = new FileInputStream(heFuManZipFileSrc);
            objectInput = new ObjectInputStream(input);
            output = new FileOutputStream(newFileSrc);
            String data = (String) objectInput.readObject();
            System.out.println("data = "+data);
            Map<Character,String> heFuManCtrCodes = (Map<Character, String>) objectInput.readObject();
            for (Map.Entry<Character,String> entry : heFuManCtrCodes.entrySet()) {
                System.out.println("ctr = "+entry.getKey()+"\tvalue = "+entry.getValue());
            }
            String unZipData = heFuManCode.heFuManCodeToString(data, heFuManCtrCodes);
            System.out.println("unZipData = "+unZipData);
            output.write(unZipData.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
