import java.util.*;
// ==========================Lis ===========================================
public class Set4 {
    // 300 longest increasing subsequence 
    public static int lengthOfLIS(int[] nums , int ei , int[]dp){
        if(dp[ei] != 0){
            return dp[ei];
        }
        int maxLen = 1;
        for(int i=ei-1; i>=0; i--){
            if(nums[i] < nums[ei]){
                int rec = lengthOfLIS(nums , i , dp);
                maxLen = Math.max(maxLen , rec+1);
            }
        }
        return dp[ei] = maxLen;
    } 
    public static int lengthOfLIS(int[] arr) {
        int[] dp = new int[arr.length];
     int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            maxLen = Math.max(maxLen, lengthOfLIS(arr, i, dp));
        }

        return maxLen;
    }
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public int maxSumIS(int arr[], int n)  
	{  
	    //code here.
	      // Your code goes here
	    int maxSum =0;
	    int[] lis = new int[n]; 
        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=i-1; j>=0; j--){
                if(arr[i] > arr[j]){
                    sum = Math.max(sum , lis[j]);
                }
            }
            lis[i] = sum+arr[i];
            maxSum = Math.max(maxSum , lis[i]);
        }
        return maxSum;
	}
    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1
    public static int maxSumBitonicSubseq(int arr[], int n)
    {
        int[] lis = new int[n]; // longest inc subsequence sum from left to right
        for(int i=0; i<n; i++){
            int sum = 0;
            for(int j=i-1; j>=0; j--){
                if(arr[i] > arr[j]){
                    sum = Math.max(sum , lis[j]);
                }
            }
            lis[i] = sum+arr[i];
        }
        int[] lds = new int[n]; //longest dec subsequence sum from right to left 
        for(int i=n-1; i>=0; i--){
            int sum =0;
            for(int j = i+1; j<n; j++){
                if(arr[i] > arr[j]){
                    sum = Math.max(sum , lds[j]);
                }
            }
            lds[i] = sum + arr[i];
        }
        int maxSum =0;
        for(int i=0; i<n; i++){
            maxSum = Math.max(maxSum , lis[i]+lds[i]-arr[i]);
        }
        return maxSum;
    }
    // https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1
    public int minDeletions(int arr[], int n) 
	{ 
	   // min del == n - length of the longest increasing subseq
	   int[] lis = new int[n];
	   int lenOfLIS = 0;
	   for(int i=0; i<n; i++){
	       int len = 1;
	       for(int j = i-1; j>=0; j--){
	           if(arr[i] > arr[j]){
	               len = Math.max(len , lis[j]+1);
	           }
	       }
	       lis[i] = len ;
	       lenOfLIS = Math.max(lenOfLIS  , lis[i]);
	   }
	   return ( n - lenOfLIS);
	}
    // 673 https://leetcode.com/problems/number-of-longest-increasing-subsequence/submissions/
    public static class Pair{
        int lengthOfLIS = 0 ;
        int numOfLIS = 0 ;
        Pair(int a , int b){
            this.lengthOfLIS = a;
            this.numOfLIS = b;
        }
    }
    public static int findNumberOfLIS(int[] nums) {
        int maxLen = 0;
        int maxCount =0;
        Pair[] lis = new Pair[nums.length];
        for(int i=0; i<nums.length ; i++){
            lis[i] = new Pair(1 , 1);
        }
        for(int i=0; i<nums.length; i++){
            for(int j= i-1; j>=0; j--){
                if(nums[i] > nums[j]){
                    if(lis[j].lengthOfLIS + 1 > lis[i].lengthOfLIS ){
                        lis[i].lengthOfLIS = lis[j].lengthOfLIS+1;
                        lis[i].numOfLIS = lis[j].numOfLIS;
                    }else if(lis[j].lengthOfLIS+1 == lis[i].lengthOfLIS){
                        lis[i].numOfLIS += lis[j].numOfLIS ;
                    }
                }
            }
            if(lis[i]. lengthOfLIS > maxLen){
                maxLen = lis[i]. lengthOfLIS;
                maxCount = lis[i].numOfLIS;
            }else if(lis[i]. lengthOfLIS == maxLen){
                maxCount += lis[i].numOfLIS;
            }
        }
        return maxCount;
    }
   // https://www.geeksforgeeks.org/dynamic-programming-building-bridges/

   public static int buildingBridges(int[][] nums){
    Arrays.sort(nums, (a, b) -> {
        return a[1] - b[1];
    });
    int n = nums.length;
    int[] dp = new int[n];
    int maxLen =0;
    for(int i=0; i<n; i++){
        int len = 1;
        for(int j= i-1; j>=0 ; j--){
            if(nums[i][0] > nums[j][0]){
                len = Math.max(len , dp[j]);
            }
        }
        dp[i] = len;
        maxLen = Math.max(dp[i] , maxLen);
    }
    return maxLen;
   }
   // russian doll
   public int maxEnvelopes(int[][] nums) {
    Arrays.sort(nums, (a, b) -> {
          return a[1] - b[1];
    });
      int n = nums.length;
      int[] dp = new int[n];
      int maxLen =0;
      for(int i=0; i<n; i++){
          int len = 1;
          for(int j= i-1; j>=0 ; j--){
              if(nums[i][0] > nums[j][0] && nums[i][1] > nums[j][1]){
                  len = Math.max(len , dp[j]+1);
              }
          }
          dp[i] = len;
          maxLen = Math.max(dp[i] , maxLen);
      }
      return maxLen;
}
    public static void main(String[] args) {
        //uniquePathsWithObstacles()
    }
}
