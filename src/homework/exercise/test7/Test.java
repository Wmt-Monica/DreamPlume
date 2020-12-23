package homework.exercise.test7;

public class Test {
    public static void main(String[] args) {
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][3] = 2;
        array[3][5] = 2;
        Test test = new Test();
        int[][] newArray = test.packedArray(array);
        System.out.println("===============压缩后的数组================");
        for (int[] i : newArray) {
            for (int j : i) {
                System.out.print(j+"\t");
            }
            System.out.println();
        }
        int[][] oldArray = test.decompressArray(newArray);
        System.out.println("===============解压后的数组================");
        for (int[] i : oldArray) {
            for (int j : i) {
                System.out.print(j+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 压缩数组
     * @param array 原始数组
     */
    public int[][] packedArray(int[][] array){
        int nums = 0;  // 有效数据个数
        for (int[] a : array) {
            for (int b : a) {
                if (b != 0) {
                    nums ++;
                }
            }
        }
        int[][] newArray = new int[nums+1][3];
        newArray[0][0] = array.length;  // 存放原数组的行数
        newArray[0][1] = array[0].length;  // 存放原数组的列数
        newArray[0][2] = nums;  // 存放原始数组的有效数据的个数

        int a = 1;
        for (int i = 0; i < array.length; i ++) {
            for (int j = 0; j < array[0].length; j ++) {
                if (array[i][j] != 0) {
                    newArray[a][0] = i;  // 存放该有效数据的所在原数组的行数
                    newArray[a][1] = j;  // 存放该有效数据的在所在原数组的列数
                    newArray[a][2] = array[i][j];  // 存放该有效数据的具体值
                    a ++;
                }
            }
        }
        return newArray;
    }

    /**
     * 将压缩数组解压并返回
     * @param array 压缩后的数组
     * @return 返回解压后的数组
     */
    public int[][] decompressArray(int[][] array) {
        int[][] oldArray = new int[array[0][0]][array[0][1]];  // 依据原始数组的行数和列数创建数组
        int num = 1; // 赋值的有效数据
        for (int i = 0; i < oldArray.length; i ++) {
            for (int j = 0; j < oldArray[0].length && num <= array[0][2]; j ++) {
                if (i == array[num][0] && j == array[num][1]) {
                    oldArray[i][j] = array[num][2];
                    num ++;
                }
            }
        }
        return oldArray;
    }
}
