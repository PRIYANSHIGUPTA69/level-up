import java.util.*;
public class Set5 {
    public static void display(int[] dp) {
        for (int ele : dp) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }
    public static void display2D(int[][] dp) {
        for (int[] d : dp) {
            display(d);
        }
        System.out.println();
    }
    public static void fill(int[] dp) {
        Arrays.fill(dp, -1);
    }

    public static void fill2D(int[][] dp) {
        for (int[] d : dp)
            fill(d);
    }
    public static int permutation(int[] arr, int tar, int[] dp) {
        if (tar == 0)
            return dp[tar] = 1;
        if (dp[tar] != -1)
            return dp[tar];
        int count = 0;
        for (int ele : arr) {
            if (tar - ele >= 0)
                count += permutation(arr, tar - ele, dp);
        }

        return dp[tar] = count;
    }
    public static int permutation_DP(int[] arr, int Tar, int[] dp) {
        dp[0] = 1;
        for (int tar = 1; tar <= Tar; tar++) {
            int count = 0;
            for (int ele : arr) {
                if (tar - ele >= 0)
                    count += dp[tar - ele];
            }
            dp[tar] = count;
        }
        return dp[Tar];
    }
    public static int permutation(int[] arr , int tar){
        int[] dp = new int[tar+1];
        Arrays.fill(dp ,-1);
        int ans = permutation_DP(arr, tar ,dp);
        display(dp);
        return ans;
    }
    public static int combination(int[] arr, int n, int tar, int[][] dp) {
        if (tar == 0)
            return dp[n][tar] = 1;
        if (dp[n][tar] != -1)
            return dp[n][tar];
        int count = 0;
        for (int i = n; i > 0; i--) {
            if (tar - arr[i - 1] >= 0)
                count += combination(arr, i, tar - arr[i - 1], dp);
        }

        return dp[n][tar] = count;
    }

    public static int combination_DP(int[] arr, int n, int Tar, int[] dp) {
        dp[0] = 1;
        for (int ele : arr) {
            for (int tar = ele; tar <= Tar; tar++) {
                if (tar - ele >= 0)
                    dp[tar] += dp[tar - ele];
            }
        }
        return dp[Tar];
    }
    public static int combination(int[] arr , int tar){
        int n = arr.length;
        int[] dp = new int[tar+1];
        int ans = combination_DP(arr, n, tar, dp);
        display(dp);
        return ans;
    }
    public long coinChange(int[] arr , int tar , long[] dp){
        if(tar == 0){
            return 0;
        }
        if(tar < 0){
            return Integer.MAX_VALUE;
        }
        if(dp[tar] != -1){
            return dp[tar];
        }
         dp[tar]=Integer.MAX_VALUE;
        for(int i=0; i<arr.length; i++){
            
                if( tar - arr[i] >= 0)
                dp[tar] = Math.min(coinChange(arr , tar - arr[i] , dp) +1 , dp[tar]);
            
        }
        return dp[tar];
    }
    public int coinChangeDP(int[] arr, int tar) {
        int[] dp = new int[tar+1];
        Arrays.fill(dp , (int)1e9);
        dp[0] = 0;
        for(int i=0; i<arr.length; i++){
            for(int j=1; j<= tar ; j++){
                if( j - arr[i] >= 0)
                dp[j] = Math.min( dp[j - arr[i]]+1 , dp[j]);
            }
        }
        return dp[tar] == (int)1e9 ?-1 : dp[tar];
    }
    public int coinChange(int[] arr, int tar) {
        long[] dp = new long[tar+1];
        Arrays.fill(dp , -1);
      long ans = coinChange(arr , tar , dp);
        return (int)ans == Integer.MAX_VALUE ? -1 : (int)ans;
    }
    public static void main(String[] args) {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int ans = combination(arr, tar);
        
        System.out.println(ans);
    }
}
