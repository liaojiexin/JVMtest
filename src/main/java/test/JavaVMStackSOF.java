package test;

/**
 * @ClassName: JavaVMStackSOF
 * @Description: TODO   虚拟机栈和本地方法溢出溢出
 * @version: 1.0
 * @author: liaojiexin
 * @date: 2021/4/9 10:31
 */
public class JavaVMStackSOF {

    /*** VM Args：-Xss128k
     * 使用-Xss参数减少栈内存容量。
     *结果：抛出StackOverflowError异常，异常出现时输出的堆栈深度相应缩小。
     */
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
