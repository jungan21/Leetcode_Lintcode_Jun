package math_bit;

/**
 * There are N gas stations along a circular route, where the amount of gas at
 * station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to
 * travel from station i to its next station (i+1). You begin the journey with
 * an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit
 * once, otherwise return -1.
 *
 * Example Given 4 gas stations with gas[i]=[1,1,3,1], and the
 * cost[i]=[2,2,1,1]. The starting gas station's index is 2.
 * 
 * Challenge O(n) time and O(1) extra space
 *
 *
 */
public class GasStation {

	public static void main(String[] args) {

	}

	/**
	 * http://www.cnblogs.com/grandyang/p/4266812.html
	 * 
	 * 我们首先要知道能走完整个环的前提是gas的总量要大于cost的总量，这样才会有起点的存在。假设开始设置起点start = 0,
	 * 并从这里出发，如果当前的gas值大于cost值
	 * ，就可以继续前进，此时到下一个站点，剩余的gas加上当前的gas再减去cost，看是否大于0，若大于0
	 * ，则继续前进。当到达某一站点时，若这个值小于0了
	 * ，则说明从起点到这个点中间的任何一个点都不能作为起点，则把起点设为下一个点，继续遍历。当遍历完整个环时，当前保存的起点即为所求。
	 */
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int total = 0, sum = 0, index = 0;

		for (int i = 0; i < gas.length; i++) {
			// total是比较总的gas和cost的量
			total += gas[i] - cost[i];
			// sum 时表示局部的
			sum += gas[i] - cost[i];
			if (sum < 0) {
				sum = 0;
				index = i + 1;
			}
		}
		return total < 0 ? -1 : index;
	}

}
