package company.walmart.design.pattern;

public class Singleton {
    private static Singleton instance;
    private Singleton (){}
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}


/**
 *  解释： https://juejin.cn/post/6844903858276139021
 *
 * // Performance is better than the synchronized keywords on method one(above one)
 * public class Singleton {
 *     private volatile static Singleton singleton;  //1:volatile修饰
 *     private Singleton (){}
 *     public static Singleton getSingleton() {
 *     if (singleton == null) {  //2:减少不要同步，优化性能
 *         synchronized (Singleton.class) {  // 3：同步，线程安全
 *         if (singleton == null) {
 *             singleton = new Singleton();  //4：创建singleton 对象
 *         }
 *         }
 *     }
 *     return singleton;
 *     }
 * }

 * public class Singleton {
 *     private static Singleton instance = new Singleton();
 *     private Singleton (){}
 *     public static Singleton getInstance() {
 *     return instance;
 *     }
 * }
 *

 *

 */
