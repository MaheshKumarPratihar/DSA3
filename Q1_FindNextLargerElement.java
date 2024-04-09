import java.util.Arrays;
import java.util.Stack;

public class Q1_FindNextLargerElement {

    /*
     * Problem Description
     * Given an array A having N elements, the task is to find the next greater
     * element(NGE) for each element of the array in order of their appearance in
     * the array. If no such element exists, output -1. This should be achieved with
     * a time complexity of O(n).
     * 
     * Input format
     * There are 2 lines of input
     * 
     * The first line contains an integer N denoting the size of the array.
     * 
     * The second line contains N space separated positive integers denoting the
     * values in the array A.
     * 
     * Output format
     * Print N space separated integers, which are the next greater element for each
     * array element. If no such greater element exists for any element, output -1
     * 
     * Function definition
     * You have to implement the given function. It accepts the original array as an
     * argument, and returns the new next greater element array.
     * 
     * Constraints
     * 1 <= N <= 10^5
     * 
     * 0 <= Values in the array <= 10^9
     * 
     * Sample Input 1
     * 4
     * 
     * 1 3 2 4
     * 
     * Sample Output 1
     * 3 4 4 -1
     * 
     * Explanation 1
     * In the array, the next larger element to 1 is 3 , 3 is 4 , 2 is 4 and for 4,
     * there is no such greater element, hence -1.
     */
    public static void main(String[] args) {
        int[] nums = { 1, 3, 2, 4 };
        int n = nums.length;
        Utility.print1d(nums);
        System.out.println("\nresult Brute");
        Utility.print1d(nextLargerElementBrute(nums, n));
        System.out.println("\nresult Optimal");
        Utility.print1d(nextLargerElementOptimal(nums, n));
    }

    // Optimal -> using stack we push curElement at 'i' if stack is empty
    // if stack is not empty then pop elements untill elements are smaller than our current element in nums at 'i'
    // if cur element at nums[i] is smaller than stack peek than replace nums[i] with peek but 
    // first take nums[i] in tmp as it could be next greater element for any other element in nums.
    static int[] nextLargerElementOptimal(int nums[], int n) {
        Stack<Integer> ST = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            if (ST.isEmpty()) {
                ST.push(nums[i]);
                nums[i] = -1;
            } else {
                while (!ST.isEmpty() && ST.peek() <= nums[i]) {
                    ST.pop();
                }
                if (ST.isEmpty()) {
                    ST.push(nums[i]);
                    nums[i] = -1;
                } else {
                    int tmp = nums[i];
                    nums[i] = ST.peek();
                    ST.push(tmp);
                }
            }
        }
        return nums;
    }

    // brute -> using two loops to find the next greater element and then break if it's available
    static int[] nextLargerElementBrute(int nums[], int n) {
        int[] res = new int[n];
        Arrays.fill(res, -1);
        res[n - 1] = -1;

        int i = 0;
        while (i < n - 1) {
            int j = i + 1;
            while (j < n) {
                if (nums[i] < nums[j]) {
                    res[i] = nums[j];
                    break;
                }
                j++;
            }
            i++;
        }
        return res;
    }
}