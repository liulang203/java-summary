# Java native method call JNI dll
## create Native method 

Class contain native method
```java
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
```
main class
```java
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
```
## javah generate header file of c++
```bash
javah -jni top.ddnet.jni.Java2Cpp
```
generate file "top_ddnet_jni_Java2Cpp.h"
```c++
/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class top_ddnet_jni_Java2Cpp */

#ifndef _Included_top_ddnet_jni_Java2Cpp
#define _Included_top_ddnet_jni_Java2Cpp
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     top_ddnet_jni_Java2Cpp
 * Method:    dllAdd
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllAdd
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     top_ddnet_jni_Java2Cpp
 * Method:    dllSub
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllSub
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     top_ddnet_jni_Java2Cpp
 * Method:    dllMul
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllMul
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     top_ddnet_jni_Java2Cpp
 * Method:    dllDiv
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllDiv
  (JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif

```
## create c++ project with VSCODE

cpp file content "top_ddnet_jni_Java2Cpp.cpp"
```c++
#include "top_ddnet_jni_Java2Cpp.h"

JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllAdd(JNIEnv *env, jobject obj, jint a, jint b)
{
    return a + b;
}

JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllSub(JNIEnv *env, jobject obj, jint a, jint b)
{
    return a - b;
}

JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllMul(JNIEnv *env, jobject obj, jint a, jint b)
{
    return a * b;
}

JNIEXPORT jint JNICALL Java_top_ddnet_jni_Java2Cpp_dllDiv(JNIEnv *env, jobject obj, jint a, jint b)
{
    return a / b;
}

```
complete cpp file in windows
```bash
#load vc build ENV
cmd /k "C:\Program Files (x86)\Microsoft Visual C++ Build Tools\vcbuildtools.bat" amd64

cl /EHsc -I C:/Java/jdk1.8.0_101/include -I C:/Java/jdk1.8.0_101/include/win32 -LD  top_ddnet_jni_Java2Cpp.cpp /Fe:javaCallCpp.dll
```
copy dll file to directory of java.exe

run the Main class
```console
add 8 and 3 is 11
sub 8 and 3 is 5
mul 8 and 3 is 24
div 8 and 3 is 2

```