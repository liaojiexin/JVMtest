package test;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: RuntimeConstantPoolOOM
 * @Description: TODO 运行时常量池导致的内存溢出异常
 * @version: 1.0
 * @author: liaojiexin
 * @date: 2021/4/9 15:14
 */
public class RuntimeConstantPoolOOM {
    /*** 在JDK 6或更早之前的HotSpot虚拟机中，常量池都是分配 在永久代中，我们可以通过
     *  VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M限制永久代的大小。
     *  -----------------------------------------------
     *  在JDK 7中继续使 用-XX：MaxPermSize参数或者在JDK 8及以上版本使用
     *  -XX：MaxMeta-spaceSize参数把方法区容量同 样限制在6MB，也都不会重现JDK 6中的溢出异常，
     *  循环将一直进行下去，永不停歇。jdk7/8以上就要用-Xmx参数限制最大堆到6MB就可以看到溢出异常，
     *  因为原本存放在永久代的字符串常量池被移至Java堆之中*/
    public static void main(String[] args) {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<String>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
