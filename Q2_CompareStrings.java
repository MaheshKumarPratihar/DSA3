/*
Problem Description
Given 2 strings S and T containing lowercase and '#' characters. 
You have to check whether these 2 strings are same or not when typed into an editor('#' being the backspace character).
Note: Backspacing an empty string remains an empty string only.
For eg. a#bc means bc, and a##bcd means bcd.
Input format
There are 2t+1 lines of input.
First line contains an integer tests - Number of test cases.
First line of each test case contains a string S.
Second line of each test case contains a string T.

Output format
Print true if the 2 strings are same otherwise false, for each test case print in a different line.

Function definition
You have to complete the given function. It accepts two parameters - the two strings, 
and returns a boolean value, true if S and T are same and false if not.

Sample Input 1
2
as#sddff#
aa#sddf
a##b
ab

Sample Output 1
true
false
Explanation
In first test case both the strings become "asddf" when typed into an editor.

In second test case the string becomes "b" and "ab" which are not equal.

Constraints
1 <= tests <= 10

1 <= Length of each string <= 10^5
*/

import java.util.Stack;

public class Q2_CompareStrings {
    public static void main(String[] args) {
        System.out.println("_______Brute_______");

        String S = "as#sddff#";
        String T = "aa#sddf";
        System.out.println("example 1 -> S = " + S + " & T = " + T);
        System.out.println(backspaceStringCompareBrute(S, T));

        S = "a##b";
        T = "ab";
        System.out.println("example 2 -> S = " + S + " & T = " + T);
        System.out.println(backspaceStringCompareBrute(S, T));

        System.out.println();

        System.out.println("_______Optimal_______");

        S = "as#sddff#";
        T = "aa#sddf";
        System.out.println("example 1 -> S = " + S + " & T = " + T);
        System.out.println(backspaceStringCompareOptimal(S, T));

        System.out.println();

        S = "a##b";
        T = "ab";
        System.out.println("example 2 -> S = " + S + " & T = " + T);
        System.out.println(backspaceStringCompareOptimal(S, T));
    }

    // optimal -> putting the char in stack if we found # then pop the char, 😁easy-peasy
    static boolean backspaceStringCompareOptimal(String S, String T) {
        Stack<Character> STS = removeBackspacedChars(S);
        Stack<Character> STT = removeBackspacedChars(T);

        if (STS.size() != STT.size())
            return false;
        while (!STS.isEmpty()) {
            if (STS.pop() != STT.pop())
                return false;
        }
        return true;
    }

    static Stack<Character> removeBackspacedChars(String S) {
        Stack<Character> ST = new Stack<>();
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '#' && !ST.isEmpty()) {
                ST.pop();
            } else {
                ST.add(S.charAt(i));
            }
        }
        return ST;
    }

    // brute
    static boolean backspaceStringCompareBrute(String S, String T) {
        S = trim(S);
        T = trim(T);
        return S.equals(T);
    }

    static String trim(String S) {
        for (int i = 0; i < S.length(); i++) {
            int countBackspace = 0;
            int pos = i;

            while (pos < S.length() && S.charAt(pos) == '#') {
                countBackspace++;
                pos++;
            }
            if (countBackspace != 0) {
                if (i - countBackspace < 0) {
                    S = S.substring(pos);
                } else {
                    S = S.substring(0, i - countBackspace) + S.substring(pos);
                    i = -1;
                }
            }
        }
        return S;
    }
}
