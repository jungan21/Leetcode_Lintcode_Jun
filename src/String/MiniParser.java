package String;

import java.util.List;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
	// Constructor initializes an empty nested list.
	public NestedInteger();

	// Constructor initializes a single integer.
	public NestedInteger(int value);

	// @return true if this NestedInteger holds a single integer, rather than a
	// nested list.
	public boolean isInteger();

	// @return the single integer that this NestedInteger holds, if it holds a
	// single integer
	// Return null if this NestedInteger holds a nested list
	public Integer getInteger();

	// Set this NestedInteger to hold a single integer.
	public void setInteger(int value);

	// Set this NestedInteger to hold a nested list and adds a nested integer to
	// it.
	public void add(NestedInteger ni);

	// @return the nested list that this NestedInteger holds, if it holds a
	// nested list
	// Return null if this NestedInteger holds a single integer
	public List<NestedInteger> getList();
}

public class MiniParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
public NestedInteger deserialize(String s) {
        
        // return a NestedInteger with a single integer
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }
        
        int len = s.length();
        Stack<NestedInteger> stack = new Stack<>();
        StringBuilder num = new StringBuilder();
        
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
                if(ch == '['){
                   stack.push(new NestedInteger()); 
                // judge if it's a num before ','
                } else if(ch == ','){
                    if (num.length() != 0) {
                        stack.peek().add(new NestedInteger(Integer.parseInt(num.toString())));
                        clearNum(num);
                    }
                } else if (ch == ']'){
                    // judge if it's a num before ']'
                     if (num.length() != 0) {
                        stack.peek().add(new NestedInteger(Integer.parseInt(num.toString())));
                        clearNum(num);
                    }
                    NestedInteger ni = stack.pop();
                    
                    if (!stack.isEmpty()) {
                        stack.peek().add(ni);
                    } else {
                        return ni;
                    }
                } else {
                    num.append(ch);
                }
        }
        
        return null;
    }
    
    public void clearNum(StringBuilder sb) {
        sb.delete(0, sb.length());
    }
	public NestedInteger deserialize(String s) {
		 NestedInteger ret = new NestedInteger();
	        if (s == null || s.length() == 0) return ret;
	        // base case
	        if (s.charAt(0) != '[') {
	            ret.setInteger(Integer.parseInt(s));
	        }
	        else if (s.length() > 2) {
	            int start = 1, count = 0;
	            for (int i = 1; i < s.length(); i++) {
	                char c = s.charAt(i);
	                if (count == 0 && (c == ',' || i == s.length() - 1)) {
	                    ret.add(deserialize(s.substring(start, i)));
	                    start = i + 1;
	                }
	                else if (c == '[') count++;
	                else if (c == ']') count--;
	            }
	        }
	        return ret;

	}

}
