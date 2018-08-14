package top.ddnet.jni;

/**
 *
 * @author Vinson.Ding
 * @date 2018/8/14
 */
public class Main {
    public static void main(String[] args) {
        Java2Cpp java2Cpp = new Java2Cpp();
        System.out.println("add 8 and 3 is " + java2Cpp.dllAdd(8, 3));
        System.out.println("sub 8 and 3 is " + java2Cpp.dllSub(8, 3));
        System.out.println("mul 8 and 3 is " + java2Cpp.dllMul(8, 3));
        System.out.println("div 8 and 3 is " + java2Cpp.dllDiv(8, 3));
    }
}
