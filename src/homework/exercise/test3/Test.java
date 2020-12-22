package homework.exercise.test3;

import java.util.*;

/**
 * 3、写出 3+4/25*8-6 的逆波兰表达式，并描述清楚转化过程中操作数栈与运算
 *    符栈的变化情况。（出题人：李俊）
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        StringBuffer expression = new StringBuffer();
        expression.append("1*(23-3/4)+5");
        Stack<String> stack = test.againstPoland(expression);
        System.out.println("逆波兰表达式："+stack);
        Collections.reverse(stack);
        double result = test.calculation(stack);
        System.out.println("计算结果："+result);
    }

    /**
     * 方法：
     *      1.数字直接入 result 结果栈
     *      2.运算符要与 operators 运算符栈顶比较，优先级大则入栈，小于或等于则 operators 运算符栈出栈后再入栈
     *      3.若是'('则无条件入 operators 运算符栈
     *
     * @param expression 中缀表达式
     * @return 返回逆波兰表达式存储的栈
     */
    public Stack<String> againstPoland(StringBuffer expression) {
        Stack<String> result = new Stack<>();  // 结果栈
        Stack<String> operators = new Stack<>();  // 运算符栈
        String[] express = new String[expression.length()];  // 创建字符串数组存放中缀表达式中的数据

        // 遍历中缀表达式将数字和字符分开存放入字符转数组中，
        // 目的：避免运算数中含有多位数所以采用字符串而不是字符数组来存放
        for (int i = 0,j = 0; i < expression.length(); i ++) {
            if (isDigit(String.valueOf(expression.charAt(i)))){
                String nums = "";
                while (i < expression.length() && isDigit(String.valueOf(expression.charAt(i)))) {
                    nums += expression.charAt(i);
                    i ++;
                }
                i --;
                express[j++] = nums;
            }else if (isOperator(String.valueOf(expression.charAt(i)))) {
                String ope = String.valueOf(expression.charAt(i));
                express[j++] = ope;
            }
        }

        // 循环 expression 表达式中的每一个字符
        for (int i = 0; i < express.length && express[i] != null; i ++) {
            String c = express[i];  // 在循环中使用 charAt() 方法获取到 expression 字符串中的每一个字符
            if (isDigit(c)) {  // 当是数字字符时直接入 result 结果栈
                result.add(c);
            }else if (isOperator(c)) {  // 当 c 是运算符时
                if (operators.isEmpty()) {  //  运算符栈为空就直接将运算符入 operators 运算符栈
                    operators.add(c);
                }else if (c.equals("(")) {  // '(' 优先级最高直接入 operators 运算符栈
                    operators.add(c);
                }else if (operatorsComparable(operators.peek(),c) && !operators.peek().equals("(")) {
                    // operators 运算符栈的栈顶运算符的优先级小于等于 c 的运算符优先级，就将 operator 出栈到 result 结果栈中，再将 c 入栈
                    result.add(operators.pop());
                    operators.add(c);
                }else if (c.equals(")")) {  // 当 c 为')'时，就将'('之后的运算符入 operators 运算符栈中，并且'('出栈但不入 result 结果栈
                    while (!operators.peek().equals("(")) {
                        result.add(operators.pop());
                    }
                    operators.pop();
                }else {
                    operators.add(c);
                }
            }else {
                throw new RuntimeException("传入的表达式含有非法字符，转换为逆波兰表达式失败");
            }
        }

        // 遍历完 expression 中的所有字符之后将剩余在 operators 字符栈中的元素全部入 result 结果栈中并返回
        while (!operators.isEmpty()){
            result.add(operators.pop());
        }
        return result;
    }

    /**
     * 使用逆波兰表达式将计算结果返回
     * @param expression 逆波兰表达式
     * @return 返回该逆波兰表达式的计算结果
     */
    public double calculation(Stack<String> expression) {
        double result = 0;  // 计算结果
        Stack<String> stack = new Stack<>();  // 计算过程的辅助栈

        while (!expression.isEmpty()) {  // 遍历 expression 栈空间
            if (isDigit(expression.peek())) {  // 如果是数字就直接入栈
                stack.add(expression.pop());
            }else if (isOperator(expression.peek())) {  // 如果是字符就进行一下操作
                double num2 =Double.parseDouble(stack.pop());
                double num1 =Double.parseDouble(stack.pop());
                switch (expression.pop()) {  // 根据运算符计算出 result
                    case "+" : result = num1 + num2; break;
                    case "-" : result = num1 - num2; break;
                    case "*" : result = num1 * num2; break;
                    case "/" : result = (num1*1.0) / num2; break;
                }
                stack.add(String.valueOf(result));  //  将计算结果 result 压入 stack 栈中
            }
        }
        return result;
    }

    /**
     * 功能：判断 str字符是否为数字字符
     * @param ctr 传入一个字符
     * @return 返回该字符是否为一个数字字符
     */
    public boolean isDigit(String ctr) {
        char[] chars = ctr.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            if (!(chars[i] >= '0' && chars[i] <= '9')) {
                return false;
            }
        }
        return true;
    }

    // 用于存储运算符的 Map 集合，方便判断是否为运算符的方法实现提供便利
    static Map<String,Integer> operatorMap = new HashMap<>();
    static {
        operatorMap.put("+",1);
        operatorMap.put("-",1);
        operatorMap.put("*",2);
        operatorMap.put("/",2);
        operatorMap.put("(",3);
        operatorMap.put(")",3);
    }

    /**
     * 功能：判断 str字符是否为运算符字符
     * @param ctr 传入一个字符
     * @return 返回该字符是否为一个运算符字符
     */
    public boolean isOperator(String ctr) {
        return operatorMap.containsKey(ctr);
    }

    /**
     * 判断两个运算符之间的优先级
     * @param ope1 运算符
     * @param ope2 运算符
     * @return 返回 ope1 运算符的优先级是否小于等于 ope2运算符优先级
     */
    public boolean operatorsComparable(String ope1, String ope2) {
        return operatorMap.get(ope1) >= operatorMap.get(ope2);
    }
}
