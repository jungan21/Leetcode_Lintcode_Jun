package company.robinhoods;

import java.util.*;


/**
 * 偏移量管理器：处理乱序到达的消息并计算可连续提交的最大偏移量。
 */
public class CommitOffset {

    // 存储已收到的偏移量
    private final Set<Integer> receivedOffsets = new HashSet<>();
    private int lastCommitedOffset = -1; // 记录当前已经提交到的最大位置

    /**
     * @param offset 新到达的消息偏移量
     * @return 如果提交点推进了，返回新的提交点；否则返回 -1。
     */
    // int[] inputOffsets = {2, 1, 0, 5, 4};
    public int receiveAndCommit(int offset) {
        // 记录当前收到的偏移量
        receivedOffsets.add(offset); // {2}
        //System.out.println("Set:" + receivedOffsets);

        int tempNextCommit = lastCommitedOffset; //-1

        /**
         *  当第三个元素 0 刚到达的时候： Set:[2, 1, 0]， temp = commitedOffset = -1
         *      开始执行while循环：
         *          nextCommit = temp +1 = -1 + 1 = 0， check Set 包含0， temp 从-1变为0
         *          then, nextCommit = temp + 1 = 0 + 1, check Set包含1， temp从0变为1
         *          then, nextCommit = temp + 1 = 1 + 1, check Set包含2， temp从1变为2
         *
         *           总之：当元素0到达后，发现Set里有，[0, 1, 2],
         *          commitedOffset就是我们要的结果，可以udpate为2，表示2之前的元素都存在可以commit
         *
         *  当第四个元素 5 刚到达的时候： Set:[2, 1, 0，5]， temp = commitedOffset = 2
         *      开始执行while循环， 由于commitedOffset=2，表示2之前都不用检查了，已经提交了。从temp+1= 3开始检查
         *
         */
        while (true) {
            int nextCommit = tempNextCommit + 1;
            if (receivedOffsets.contains(nextCommit)) {
                // 如果下一个序号已到达，则更新临时指针并继续探测
                tempNextCommit = nextCommit;
            } else {
                break;
            }
        }
        System.out.println("Temp" + tempNextCommit);

        if (tempNextCommit > lastCommitedOffset) {
            lastCommitedOffset = tempNextCommit;
            return lastCommitedOffset;
        }


        return -1;
    }


    /**
     * 模拟乱序到达的消息序列: 2 -> 1 -> 0 -> 5 -> 4
     *
     * 解释：
     *     收到 2: 缺少 0,1，不可提交 (-1)
     *     收到 1: 缺少 0，不可提交 (-1)
     *     收到 0: 0,1,2 全部集齐，提交到 2
     *     收到 5: 缺少 3,4，不可提交 (-1)
     *     收到 4: 仍缺少 3，不可提交 (-1)
     *
     */

    public static void main(String[] args) {
        //int[] inputOffsets = {2, 1, 0, 5, 4}; // 模拟乱序到达的偏移量
        int[] inputOffsets = {2, 1, 0};
        CommitOffset system = new CommitOffset();

        System.out.println("输入序列: " + Arrays.toString(inputOffsets));

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < inputOffsets.length; i++) {
            int result = system.receiveAndCommit(inputOffsets[i]);
            resultList.add(result);
        }

        System.out.print("输出序列: " + resultList); // 预期输出: [-1, -1, 2, -1, -1]



    }
}
