import java.util.*;
public class LinkedListQuestion {
    
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode segregateEvenOdd(ListNode head) {
 
        ListNode even = new ListNode(-1), odd = new ListNode(-1), ep = even, op = odd, curr = head;

        while (curr != null) {
            if ((curr.val & 1) == 0) {
                ep.next = curr;
                ep = ep.next;
            } else {
                op.next = curr;
                op = op.next;
            }

            curr = curr.next;
        }

        ep.next = op.next = null;
        ep.next = odd.next;

        return even.next;
    }
    public static int targetSumCombinationTab1d(int[] arr , int tar , int n , int[]dp){
        dp[0] = 1;
        for(int i=0; i<n; i++){
            for(int j=1; j<= tar ; j++){
                if( j - arr[i] >= 0)
                dp[j] += dp[j - arr[i]];
            }
        }
        return dp[tar];
    }
    
    public int[] findOriginalArray(int[] arr) {
        int N = arr.length;
        if ((N & 1) != 0) {
            return new int[0];
        }
        Arrays.sort(arr);
        int[] ans = new int[N >> 1];
        HashMap<Integer, Integer> st = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st.put(arr[i], st.getOrDefault(arr[i], 0) + 1);
        }
        for (int i = N - 1, j = 0; i >= 0; i--) {
            if ((arr[i] & 1) != 0 && st.containsKey(arr[i])) {
                return new int[0];
            } else if (!st.containsKey(arr[i])) {
                continue;
            } else if (!st.containsKey(arr[i] >> 1)) {
                return new int[0];
            } else {
                int f = st.get(arr[i] >> 1);
                if (f == 1) st.remove(arr[i] >> 1);
                else st.put(arr[i] >> 1, f - 1);
                ans[j++] = arr[i] >> 1;
                
                f = st.get(arr[i]);
                if (f == 1) st.remove(arr[i]);
                else st.put(arr[i], f - 1);
            }
        }
        return ans;
    }

    public static int targetSumCombination(int[] arr , int tar , int n , int[][]dp){
        if(tar == 0){
            return dp[n][tar] = 1;
        }
        if(dp[n][tar] != -1){
            return dp[n][tar];
        }
        int count = 0;
        for(int i = n; i>0; i--){
            if(tar - arr[i-1] >= 0){
                count += targetSumCombination(arr , tar - arr[i-1] , i , dp);
            }
        }
        return dp[n][tar] = count;
    }
    public static void fill2d(int[][] dp){
        for(int[] d : dp){
            Arrays.fill(d , -1);
        }
    }
    public static void targetSum(){
        int[] arr = {2  , 3, 5 , 4 , 7};
        int tar =12;
        int[][] dp = new int[arr.length+1][tar+1];
        fill2d(dp);
        int[] dp1 = new int[tar+1];
        System.out.println(targetSumCombinationTab1d(arr , tar , arr.length , dp1));
        System.out.println(targetSumCombination(arr , tar , arr.length , dp));
    }
    public static void main(String[] args) {
       targetSum();
    }
}
