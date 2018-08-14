package top.ddnet.jni;

/**
 *
 * @author Vinson.Ding
 * @date 2018/8/14
 */
public class Java2Cpp {
    static {
        System.loadLibrary("javaCallCpp");
    }
    public native int dllAdd(int a,int b);
    public native int dllSub(int a,int b);
    public native int dllMul(int a,int b);
    public native int dllDiv(int a,int b);
}
