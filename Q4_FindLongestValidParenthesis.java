import java.util.Stack;

public class Q4_FindLongestValidParenthesis {
    public static void main(String[] args) {
        String s = "()(()(())"; // valid paren is -> "()(())", of len 6
        System.out.println(longestValidParentheses(s));
    }

    static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                stack.push(i);
            }else {
                if(!stack.isEmpty()){
                    stack.pop();
                }
                if(!stack.isEmpty()){
                    maxLen = Math.max(maxLen, i - stack.peek());
                }else {
                    stack.push(i);
                }
            }
        }
        return maxLen;
    }
}
