package homework.tree;

import java.util.*;
import java.util.Queue;

//下面的所有求结点总数的，和求叶子结点个数的题，均使用的遍历方法是前序遍历
class Node{
    public char val;
    public Node left;//左孩子
    public Node right;//右孩子

    public Node(char val){
        this.val=val;
    }
}
public class BinaryTree {
    /*private static class Node {  //这些参数定义在在类方法外或者内都可以，只是有些需稍加修改
            char val;
            Node left;
            Node right;

            public Node(char val) {
                this.val = val;
            }
        }*/
    // 1、构造二叉树
    public Node buildTree(){
        Node A=new Node('A');
        Node B=new Node('B');
        Node C=new Node('C');
        Node D=new Node('D');
        Node E=new Node('E');
        Node F=new Node('F');
        Node G=new Node('G');
        Node H=new Node('H');
        A.left=B;A.right=C;B.left=D;B.right=E;//这里是构造二叉树的关键
        C.left=F;C.right=G;E.right=H;
        return A;
    }
    // 2、判断两棵二叉树是否是完全相同
    public boolean issameTree(Node p,Node q){
        if(p==null && q==null){  //先判断根，再判断左子树和右子树
            return true;
        }
        if(p==null){
            return false;
        }
        if(q==null){
            return false;
        }
        return p.val==q.val && issameTree(p.left,q.left)
                && issameTree(p.right,q.right);
    }
    // 3、判断一棵树是否是另一棵树(s)的子树(t)，前提是这两棵树都不为空
    public boolean isSubTree(Node s,Node t){
        //在s中查找是否有和t相等的树
        if(s==null){
            return false;
        }
        //先看根所在的树是否和t相同
        if(issameTree(s,t)){
            return true;
        }
        //继续去左子树(右子树)中查找
        if(isSubTree(s.left,t)){
            return true;
        }
        if(isSubTree(s.right,t)){
            return true;
        }
        return false;
    }
    // 4、判断是否是对称二叉树（两棵树的根结点，具有相同的值，且每棵树的右子树都与另一棵树的左子树镜像对称）
    public boolean isMirror(Node p,Node q){
        if(p==null&&q==null){
            return true;
        }
        if(p==null||q==null){
            return false;
        }
        //p.left要和q,right相等，p.right要和q.left相等
        return p.val==q.val&&isMirror(p.left,q.right)&&isMirror(p.right,q.left);
    }
    // 5、判断一颗二叉树是否是平衡二叉树
    // 平衡二叉树：它是一棵空树或它的左右两个子树的高度差的绝对值不超过(小于等于)1，并且左右两个子树都是一棵平衡二叉树。
    //设置一个标志位，默认为是二叉树
    public boolean isBalanced=true;
    public boolean isBalanced(Node root) {
        //采用二叉树的深度来解决该问题，递归
        height(root);
        return isBalanced;
    }
    private int height(Node root){
        if(root==null||!isBalanced){
            return 0;
        }
        //分别遍历左右子树，求左右子树的深度
        int left=height(root.left);
        int right=height(root.right);
        if(Math.abs(left-right)>1){   //Math.abs 求绝对值
            isBalanced=false;
        }
        return 1+Math.max(left,right);
    }
    // 6、判断一棵二叉树是否是一棵完全二叉树
    public boolean isCompleteTree(Node root){
        if(root==null){
            return true;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.offer(root);
        //这个循环只要检测到一次为空就会跳出，但是后面可能还有结点不为空的可能
        while(!queue.isEmpty()){  //队列不为空时
            Node cur=queue.poll();  //弹出队首元素，并将该队首元素赋给cur
            if(cur!=null){
                queue.offer(cur.left);//再将当前cur的左孩子，右孩子入队列
                queue.offer(cur.right);
            }else{
                break;
            }
        }
        //该循环就是判断里面是否还有结点不为空的可能，如果有就不是一棵完全二叉树
        while(!queue.isEmpty()){
            Node cur1=queue.peek();//返回队列的首元素不删除
            if(cur1!=null){ //如果能走到这一步，说明在该结点出现前已经出现null了，所以该二叉树一定不是完全二叉树
                return false;
            }else{
                queue.poll();//弹出队首元素
            }
        }
        return true;
    }
    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        Node root=binaryTree.buildTree();
        System.out.println("判断两棵树是否相同："+binaryTree.issameTree(root,root.left));

        System.out.println("判断一棵树是否是另一棵树的子树："+binaryTree.isSubTree(root,root.left));

        System.out.println("判断是否是对称二叉树："+binaryTree.isMirror(root,root));

        System.out.println("判断是否是平衡二叉树："+binaryTree.isBalanced(root));

        System.out.println("判断是否是一棵完全二叉树："+binaryTree.isCompleteTree(root));
    }
}