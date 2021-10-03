import java.util.*;
// ==========================Two Pointer ===========================================
public class Set1{
    public int uniquePathsWithObstacles_memo(int m , int n , int[][] dp , int[][] obs){
        if(obs[m][n] == 1){
            return dp[m][n] =0;
        }
        if(m == 0 && n== 0){
            return 1;
        }
        if(dp[m][n] != -1){
            return dp[m][n];
        }
        if(m == 0){
            return dp[m][n] =  uniquePathsWithObstacles_memo( m  , n -1, dp , obs );
        }else if(n == 0){
            return dp[m][n] =  uniquePathsWithObstacles_memo( m -1 , n, dp , obs );
        }else{
            return dp[m][n] =  uniquePathsWithObstacles_memo( m -1 , n , dp , obs ) +  uniquePathsWithObstacles_memo( m  , n -1, dp , obs );
        }
    }
    public int uniquePathsWithObstacles_dp(int M , int N , int[][] dp , int[][] obs){
        for(int m = 0; m<= M; m++){
            for(int n =0; n <= N ; n++){
                if(obs[m][n] == 1){
                     dp[m][n] =0;
                    continue;
                }
                if(m == 0 && n== 0){
                    dp[m][n] = 1;
                    continue;
                }
                if(m == 0){
                     dp[m][n] =  dp[m][n-1]; 
                }else if(n == 0){
                    dp[m][n] = dp[m-1][n]; 
                }else{
                     dp[m][n] = dp[m][n-1] + dp[m-1][n];
                }
            }
        }
         return dp[M][N];
    }
    public int uniquePathsWithObstacles(int[][] obs) {
        int  m = obs.length; 
        int n = obs[0].length;
        int[][] dp = new int[m][n];
        for(int[] d : dp){
            Arrays.fill(d ,-1);
        }
        return uniquePathsWithObstacles_dp( m -1 , n -1, dp , obs );
    }
    public long uniquePaths_memo(int m , int n , long[][] dp){
        if(m == 0 && n==0){
            return dp[m][n] = 1;
        }
        if(dp[m][n] != 0){
            return dp[m][n];
        }
        
        if( m == 0){
            dp[m][n] =  uniquePaths_memo(m , n-1,dp) ;
        }else if ( n== 0){
            dp[m][n] = uniquePaths_memo(m-1 , n, dp);
        }else{
            dp[m][n] = uniquePaths_memo(m-1 , n, dp) + uniquePaths_memo(m , n-1,dp) ;
        }
        
        return dp[m][n];
    }
    public int uniquePaths(int m, int n) {
        long[][] dp = new long[m][n];
       return (int)uniquePaths_memo(m-1 , n-1, dp);

    }
    // 70 climb stairs
    public long climbStairs(int n , long[] dp){
        if(n==0){
            return dp[n] = 1;
        }
        if(n == 1){
            return dp[n] = 1;
        }
        if(dp[n-1] != 0){
            return dp[n];
        }
        return dp[n] = climbStairs(n-1 , dp) + climbStairs(n-2 , dp);
    }
    public int climbStairs(int n) {
        long[] dp = new long[n+1];
        return (int)climbStairs(n , dp);
    }

    // 746 min cost climbing statirs
    public static int minCostClimbingStairs_DP(int[] cost , int Si  , int ei , int[] dp){
        for(int si = ei; si>=0 ; si--){
            if(si == ei){
                dp[si] = cost[si];
                continue;
            }
            int climbOne = cost[si] + ( si + 1> ei? 0:dp[si+1]) ;
            int climbTwo = cost[si] +( si+2 > ei? 0:dp[si+2] );
            dp[si] = Math.min(climbOne , climbTwo);
        }
        return dp[Si];
    }
    public static int minCostClimbingStairs(int[] cost , int si  , int ei , int[] dp){
        if(si > ei){
            return 0;
        }
        if(dp[si] != 0){
            return dp[si];
        }
        int climbOne = minCostClimbingStairs(cost , si+1 , ei ,dp) + cost[si];
        int climbTwo = minCostClimbingStairs(cost , si+2 , ei ,dp) + cost[si];
        return dp[si] = Math.min(climbOne , climbTwo);
    }
    public static int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        int ans = minCostClimbingStairs(cost , 0 , cost.length -1 ,dp);
        for(int i=0; i<n; i++ ){
            System.out.print(dp[i] + " ");
        }
        return Math.min(dp[0] , dp[1]);
    }
    // 91 
    public int numDecoding(String s , int idx , int[] dp){
        if(idx == s.length()){
            dp[idx] = 1;
        }
        if(dp[idx] != -1){
            return dp[idx];
        }
        char ch = s.charAt(idx);
        if(ch == '0'){
            return dp[idx] = 0;
            
        }
        int count = numDecoding(s , idx+1 , dp);
        if(idx < s.length() - 1){
            char ch1 = s.charAt(idx+1);
            int num = (ch-'0')*10 + (ch1-'0');
            if(num <= 26){
                count += numDecoding(s , idx+2 , dp);
            }
        }
        return dp[idx] = count;
    }
        public int numDecodingDP(String s , int Idx , int[] dp){
            dp[s.length()] = 1;
            for(int idx = s.length()-1 ; idx>=0 ; idx--){
                    char ch = s.charAt(idx);
                    if(ch == '0'){
                     dp[idx] = 0;
                        continue;

                    }
                    int count = dp[idx+1];
                    if(idx < s.length() - 1){
                        char ch1 = s.charAt(idx+1);
                        int num = (ch-'0')*10 + (ch1-'0');
                      
                        if(num <= 26){
                            count += dp[idx+2];
                        }
                    }
                dp[idx] = count;
       
            }
       return dp[0];
    }
    public static int numDecodings_twoPointer(String s){
        int a=1, b=0, sum=0;
        for(int idx = s.length()-1; idx>=0; idx--){
            sum =0;
            char ch = s.charAt(idx);
            if(s.charAt(idx) != '0'){
                sum += a;
                if(idx <s.length() -1){
                    char ch1 = s.charAt(idx+1);
                        int num = (ch-'0')*10 + (ch1-'0');
                      
                        if(num <= 26){
                            sum += b;
                        }
                } 
            }
            b = a;
            a=sum;
        }
        return a;
    }
    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        for(int i=0; i<s.length();i++){
            dp[i] = -1;
        }
        return numDecodings_twoPointer(s);
        
    }
    // 639 
    int mod = (int)1e9+7;
    public long numDecodings(String s , int idx , long[] dp){
        if(idx == s.length()){
            return dp[idx] =1;
        }
        if(dp[idx] != -1){
            return dp[idx];
        }
        char ch1 = s.charAt(idx);
        if(ch1 == '0'){
            return dp[idx] =0;
        }
        long count =0;
        if(ch1 == '*'){
            // make this single
            count = (count + 9*numDecodings(s , idx+1 , dp))%mod;
            if(idx+1 < s.length()){
                char ch2 = s.charAt(idx+1); 
                // 10 11 12 13 14 15 16 20 21 22 23 24 25 26
                if(ch2>= '0' && ch2 <= '6'){
                    count = (count+2*numDecodings(s , idx+2 ,dp))%mod;
                }else if(ch2 >= '7' && ch2 <= '9'){
                    // 17 18 19 
                    count = (count+1*numDecodings(s , idx+2 ,dp))%mod;
                }else{
                    // **___ => 11 12 13 14 15 16 17 18 19 21 22 23 24 25 26 => 15
                    count = (count + 15*numDecodings(s , idx+2 , dp))%mod;
                }
            }
        }else{
            // ch1 = > 1 to 9;
            count = (count + numDecodings(s  , idx+1 , dp))%mod;
            if(idx+1 < s.length()){
                 char ch2 = s.charAt(idx+1);
                if(ch2 == '*'){
                    if(ch1 == '1'){
                        count = (count + 9*numDecodings(s , idx+2 , dp))%mod;
                    }else if(ch1 == '2'){
                        count = (count + 6*numDecodings(s , idx+2 , dp))%mod;
                    }
                }else{
                    int num = (ch1 - '0')*10 + (ch2 - '0');
                    if(num <= 26){
                         count = (count+1*numDecodings(s , idx+2 ,dp))%mod;
                    }
                }
            }
        }
        return dp[idx] =count;
    }
    public int numDecodings2(String s) {
        int n = s.length();
        long[] dp = new long[n+1];
        Arrays.fill(dp ,-1);
        long ans = numDecodings(s , 0 , dp);
        return (int)ans;
    }
    // https://www.geeksforgeeks.org/count-number-of-ways-to-partition-a-set-into-k-subsets/
    public static int divideInKGroup(int n, int k, int[][] dp) {
        if (n == k || k == 1) {
            return dp[n][k] = 1;
        }

        if (dp[n][k] != 0)
            return dp[n][k];

        int selfGroup = divideInKGroup(n - 1, k - 1, dp);
        int partOfGroup = divideInKGroup(n - 1, k, dp) * k;

        return dp[n][k] = selfGroup + partOfGroup;
    }

    public static int divideInKGroup_DP(int N, int K, int[][] dp) {
        for (int n = 1; n <= N; n++) {
            for (int k = 1; k <= K; k++) {
                if (n == k || k == 1) {
                    dp[n][k] = 1;
                    continue;
                }

                int selfGroup = dp[n - 1][k - 1];// divideInKGroup(n - 1, k - 1, dp);
                int partOfGroup = dp[n - 1][k] * k;// divideInKGroup(n - 1, k, dp) * k;

                dp[n][k] = selfGroup + partOfGroup;
            }
        }

        return dp[N][K];
    }

    public static void divideInKGroup() {
        int n = 5;
        int k = 3;

        int[][] dp = new int[n + 1][k + 1];
        System.out.println(divideInKGroup(n, k, dp));
      
    }
    public static void main(String[] args) {
        //uniquePathsWithObstacles()
    }
}
