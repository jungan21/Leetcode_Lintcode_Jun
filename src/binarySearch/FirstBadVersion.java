package binarySearch;

/**
 * The code base version is an integer start from 1 to n. One day, someone
 * committed a bad version in the code case, so it caused this version and the
 * following versions are all failed in the unit tests. Find the first bad
 * version.
 * 
 * You can call isBadVersion to help you determine which version is the first
 * bad one. The details interface can be found in the code's annotation part.
 * 
 * http://www.jiuzhang.com/solutions/first-bad-version/
 */

class SVNRepo {
	public static boolean isBadVersion(int k) {
		return false;
	}
}

public class FirstBadVersion {

	public static void main(String[] args) {

	}

	/**
	 * @return: An integer which is the first bad version.
	 */
	public int findFirstBadVersion(int n) {
		int start = 1, end = n;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			// First version that is bad version
			if (SVNRepo.isBadVersion(mid)) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (SVNRepo.isBadVersion(start)) {
			return start;
		}
		return end;
	}
}
