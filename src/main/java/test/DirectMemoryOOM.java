package test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ClassName: DirectMemoryOOM
 * @Description: TODO
 * 本机直接内存溢出  直接内存（Direct Memory）的容量大小可通过
 * -XX：MaxDirectMemorySize参数来指定，如果不 去指定，则默认与Java堆最大值（由-Xmx指定）一致
 * @version: 1.0
 * @author: liaojiexin
 * @date: 2021/4/9 16:58
 */
public class DirectMemoryOOM {
    /*** VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M */
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
