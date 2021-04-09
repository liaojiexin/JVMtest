package test;

/**
 * @ClassName: JavaVMStackOOM
 * @Description: TODO 内存溢出
 * 每个线程分配到的栈内存越大，可以建立的线程数量自
 * 然就越少，建立线程时就越容易把剩下的内存耗尽，运行时有风险，可能会导致电脑假死
 * @version: 1.0
 * @author: liaojiexin
 * @date: 2021/4/9 14:02
 */

public class JavaVMStackOOM {

    /** VM Args：-Xss2M （这时候不妨设大些，请在32位系统下运行） */
    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
