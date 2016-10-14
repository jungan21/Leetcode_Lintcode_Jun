package String;

// crack code 1.5
public class ReplaceSpaceWith20 {

	public static void main(String[] args) {
		char[] str = { 'a', ' ', 'b' };
		replaceFun(str, str.length);

	}

	public static void replaceFun(char[] str, int length) {
		int spaceCount = 0;
		int newLength = 0;

		for (char c : str) {
			if (c == ' ') {
				spaceCount++;
			}
		}

		newLength = length + spaceCount * 2;

		char[] newStr = new char[newLength];
		int j = newLength - 1;
		for (int i = str.length - 1; i >= 0; i--) {
			if (str[i] == ' ') {
				newStr[j] = '0';
				newStr[j - 1] = '2';
				newStr[j - 2] = '%';
				j = j-3;
			} else {
				newStr[j] = str[i];
				j--;
			}

		}
		System.out.println(newStr);
	}
}
