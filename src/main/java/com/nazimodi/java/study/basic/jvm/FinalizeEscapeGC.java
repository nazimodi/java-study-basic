package com.nazimodi.java.study.basic.jvm;

/**
 * 1.对象在gc时可以自我拯救
 * 2.这种自救机会只有一次，因为一个对象的finalize()方法只会被系统自动调用一次
 * @author renqingwang on 2017/11/2.
 * @version 1.0
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    static class TestObj {}

    public static void isAlive(FinalizeEscapeGC escapeGC) {
        if (escapeGC != null) {
            System.out.println("yes, i'm still alive");
        } else {
            System.out.println("no, i'm dead");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed");

        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        TestObj testObj = new TestObj();
        testObj = null;
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        isAlive(SAVE_HOOK);

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        isAlive(SAVE_HOOK);

    }
}
