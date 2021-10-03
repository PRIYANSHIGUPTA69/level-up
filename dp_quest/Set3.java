import java.util.*;
// ===================== string ======================
public class Set3 {
    // 516. Longest Palindromic Subsequence
    public int longestPalindromeSubseqDP(String s){
        int[][] dp = new int[s.length()][s.length()];
        int n = s.length();
        for(int g=0; g<n;g++){
            for(int i=0,j=g; j<n;j++,i++){
                 if(i>=j){
                   dp[i][j] = i == j?1 : 0;
                    continue;
                }
               char si = s.charAt(i);
                char sj = s.charAt(j);
                if(si == sj){
                    dp[i][j] = 2+ dp[i+1][j-1];
                }else{
                     dp[i][j] = Math.max(dp[i][j-1] , dp[i+1][j]);
                }
            }
        }
     return dp[0][s.length()-1];
    }
    public static int longestPalindromeSubseq(String s , int i , int j , int[][] dp){
        if(i > j){
            return dp[i][j] =0;
        }
        if(i == j){
            return dp[i][j] = 1;
        }
        if(dp[i][j] != 0)return dp[i][j];
        char ch1 = s.charAt(i);
        char ch2 = s.charAt(j);
        if(ch1 == ch2){
            dp[i][j] = 2 + longestPalindromeSubseq(s , i+1 , j-1,dp);
        }else{
            dp[i][j] = Math.max(longestPalindromeSubseq(s , i , j-1,dp), longestPalindromeSubseq(s , i+1 , j,dp));
        }
        return dp[i][j];
    }
    public static int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        return longestPalindromeSubseq(s , 0 , s.length()-1,dp);
    }
    // longest common subseq  1143
    public  static int longestCommonSubsequence(String s1, String s2 , int m , int n , int[][] dp) {
        if(m== 0 || n==0){
            return dp[m][n] =0;
        }
         if(dp[m][n] != -1){
             return dp[m][n];
         }
         char ch1 = s1.charAt(m-1);
         char ch2 = s2.charAt(n-1);
         if(ch1 == ch2 ){
             dp[m][n] = 1 + longestCommonSubsequence(s1 , s2 , m-1 , n-1  , dp);
         }else{
             dp[m][n] =Math.max(longestCommonSubsequence(s1 , s2 , m-1 , n  , dp) , longestCommonSubsequence(s1 , s2 , m , n-1  , dp)) ;
         }
         return dp[m][n];
     }
    
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        for(int[] d:dp){
            Arrays.fill(d ,-1);
        }
        return longestCommonSubsequence(text1 , text2 , m , n, dp);
    }
     // 583
     public int minDistance(String word1, String word2) {
        return word1.length() + word2.length() - 2 * longestCommonSubsequence(word1, word2);
    }
    // 1035 => longest common subseq 
    public int maxUncrossedLines(int[] nums1, int[] nums2 , int[][] dp , int i , int j){
        if(i== nums1.length || j==nums2.length){
            return dp[i][j] =0;
        }
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        if(nums1[i] != nums2[j]){
            dp[i][j] = Math.max(maxUncrossedLines(nums1 , nums2 ,dp, i+1 , j) , maxUncrossedLines(nums1 , nums2 , dp , i , j+1));
        }else{
            dp[i][j] = maxUncrossedLines(nums1 , nums2 ,dp, i+1 , j+1)+1;
        }
        return dp[i][j];
        
    }
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for(int[] d:dp){
            Arrays.fill(d ,-1);
        }
        return maxUncrossedLines(nums1 , nums2 , dp , 0 , 0);
    }
    // leetcode 44
    public static  String removeStars(String p){
        if(p.length() <= 1){
            return p;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        int i=1;
        while(i < p.length()){
            while(i<p.length() && p.charAt(i) == '*' && sb.charAt(sb.length()-1) == '*'){
                i++;
            }
            if(i<p.length()){
                sb.append(p.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }
    public static int isMatch(String s, String p , int n , int m , int[][] dp){
        if(m==0 && n==0){
            return dp[n][m] = 1;
        }
        if(m == 0){
            return dp[n][m] = 0;
        }
        if(n==0 ){
            dp[n][m] = p.charAt(m-1) == '*' && m ==1?1:0;
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        char ch1 = s.charAt(n-1);
        char ch2 = p.charAt(m-1);
        if(ch1 == ch2 || ch2 == '?'){
            dp[n][m] = isMatch(s , p , n-1 , m-1 , dp);
        }else if(ch2 == '*'){
             boolean res = false ;
             res = res | (isMatch(s , p , n , m-1 , dp) == 1);
            res = res | (isMatch(s , p , n-1 , m , dp) == 1);
            dp[n][m] = res == true? 1:0;
        }else{
            dp[n][m] = 0;
        }
        return dp[n][m];
    }
    public static boolean isMatch(String s, String p) {
        
        String pat = removeStars(p);
        int n = s.length() ;
        int m = pat.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] d:dp){
            Arrays.fill(d ,-1);
        }
        
        return isMatch(s , pat , n , m, dp) == 1;
        
    }
    //1458. Max Dot Product of Two Subsequences
    public static int maxDotProduct(int[] nums1, int[] nums2 , int m , int n , int[][] dp) {
        if(m==0 || n==0){
            return dp[m][n] = -(int) 1e8;
        }
        if(dp[m][n] != -(int)1e9)return dp[m][n];
        int call1 = nums1[m-1]*nums2[n-1] + maxDotProduct(nums1 , nums2 , m-1 , n-1 , dp);
        int call2 = maxDotProduct(nums1 , nums2 , m-1 , n , dp);
        int call3 = maxDotProduct(nums1 , nums2 , m , n-1 , dp);
        dp[m][n] = Math.max(call1 , Math.max(call2 , call3));
        return dp[m][n] = Math.max(dp[m][n]  , nums1[m-1]*nums2[n-1]);
    }
   public static int maxDotProduct(int[] nums1, int[] nums2) {
       int m = nums1.length;
       int n = nums2.length;
       int[][] dp = new int[m+1][n+1];
       for(int[]d:dp){
           Arrays.fill(d ,-(int)1e9);
       }
       return maxDotProduct(nums1 , nums2 , m , n , dp);
   }
   // 5 longest palindome substring
   public static String longestPalindrome(String s) {
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int si = 0 , maxLen =0;
    for(int gap=0; gap<n; gap++){
        for(int i=0 , j = gap; j<n ; i++ , j++){
            if(gap == 0){
                dp[i][j] = true;
            }else if(gap == 1){
                dp[i][j] = s.charAt(i) == s.charAt(j) ? true:false;
            }else{
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = dp[i+1][j-1];
                }else{
                    dp[i][j] = false;
                }
            }
            if(dp[i][j] == true){
                if(maxLen < (j-i+1)){
                    maxLen = j-i+1;
                    si = i;
                }
            }
        }
    }
    return s.substring(si , si+maxLen);
}
// 132 palindrome partitionning 2
public static int minCut(String s , boolean[][] palindromeDp , int[] cutDp  , int si , int ei){
    if(palindromeDp[si][ei]){
        return 0;
    }
    if(cutDp[si] != -1)return cutDp[si];
    int minAns = (int) 1e8;
    for(int cut = si; cut<ei; cut++){
        if(palindromeDp[si][cut]){
            minAns = Math.min(minAns , minCut(s ,palindromeDp  , cutDp , cut+1 , ei)+1);
        }
    }
        return cutDp[si] = minAns;
}
public static int minCut(String s) {
    int n = s.length();
    boolean[][] palindromeDp = new boolean[n][n];
    for(int gap=0; gap<n; gap++){
        for(int i=0 , j = gap; j<n ; i++ , j++){
            if(gap == 0){
                palindromeDp[i][j] = true;
            }else if(gap == 1){
                palindromeDp[i][j] = s.charAt(i) == s.charAt(j) ? true:false;
            }else{
                if(s.charAt(i) == s.charAt(j)){
                    palindromeDp[i][j] = palindromeDp[i+1][j-1];
                }else{
                    palindromeDp[i][j] = false;
                }
            }
        }
    }
    int[] cutdp = new int[s.length()];
    Arrays.fill(cutdp ,-1);
    return minCut(s , palindromeDp , cutdp , 0 , n-1);

}
//  https://practice.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1
public static int fun(String s){
    int mod = (int)1e9+7;
    // Write your code here
    int aCount = 0 ;  // a s end hone wale subseq
    int bCount =0 ; // b s end hone wale subseq
    int cCount =0; // c s end hone wale subseq;
    for(int i=0; i<s.length(); i++){
        char ch = s.charAt(i);
        if(ch == 'a'){
            // a k pass 3 optionn h . ya toh vo saare a pr end hone wale subseq k sath aa jay ya unke sath na aaye ya phir apna akela sub bna le
            aCount = ((aCount + aCount)%mod +1);
        }else if(ch== 'b'){
            // b k pass 2 optionn h . ya toh vo saare ab(bCount) pr end hone wale subseq k sath aa jay ya unke sath na aaye ya phir a pr khtm hone wale sub bna le
            bCount = (bCount + (bCount + aCount)%mod)%mod;
        }else{
             // c k pass 2 optionn h . ya toh vo saare abc (cCount) pr end hone wale subseq k sath aa jay ya unke sath na aaye ya phir ab pr khtm hone wale sub bna le
            cCount = (cCount + (cCount+bCount)%mod)%mod;
        }
    }
    return cCount;
}
    public static void main(String[] args) {
        //uniquePathsWithObstacles()
     }
}
