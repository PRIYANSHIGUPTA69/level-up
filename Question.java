import java.util.*;

public class Question{
    public static int minDistanceDP2(String s , String t , int N , int M , int[][] dp , int[][] cost){
        for(int n=0; n<=N ;n++){
            for(int m=0; m<=M ; m++){
                if(n== 0 || m==0){
                    int c =0;
                    if(n==0 && m==0){
                        dp[n][m] = 0;
                    }else if(n == 0 ){
                        for(int k = 1; k<=m; k++){
                            //insert
                            c += cost[t.charAt(k-1) - 'a'][0];
                        }
                        dp[n][m] = c;
                    }else {
                        // m ==0 delete all characters of n
                        for(int k = 1; k<=n; k++){
                            //insert
                            c += cost[s.charAt(k-1) - 'a'][1];
                        }
                        dp[n][m] = c;
                    }
                    
                    continue; 
                }
                int insert = dp[n][m-1]  ;
                int delete = dp[n-1][m] ;
                int replace = dp[n-1][m-1]  ;
                if(s.charAt(n-1) == t.charAt(m-1)){
                    dp[n][m] = replace ;
                }else{
                    dp[n][m] = Math.min(Math.min(insert , delete ) , replace )+1;
                }
            }
        }
        return dp[N][M];
    }
    public static int minDistanceDP(String s , String t , int N , int M , int[][] dp , int r , int d , int i){
        for(int n=0; n<=N ;n++){
            for(int m=0; m<=M ; m++){
                if(n== 0 || m==0){
                    dp[n][m] = n==0?m ==0?0:m*i:n*d;
                    continue; 
                }
                int insert = dp[n][m-1]  ;
                int delete = dp[n-1][m] ;
                int replace = dp[n-1][m-1]  ;
                if(s.charAt(n-1) == t.charAt(m-1)){
                    dp[n][m] = replace ;
                }else{
                    dp[n][m] = Math.min(Math.min(insert+i , delete+d ) , replace + r)+1;
                }
            }
        }
        return dp[N][M];
    }
    public static int minDistance(String s , String t , int n , int m , int[][] dp){
        if(n== 0 || m==0){
            return n==0?m:n;
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        int insert = minDistance(s , t , n  , m-1 , dp);
        int delete  = minDistance(s , t , n-1 , m , dp);
        int replace = minDistance(s , t , n-1 , m-1 , dp);
        if(s.charAt(n-1) == t.charAt(m-1)){
            dp[n][m] = replace ;
        }else{
            dp[n][m] = Math.min(Math.min(insert , delete ) , replace)+1;
        }
        return dp[n][m];
    }
    public static int minDistanceMain(String word1, String word2 , int r  , int d , int i) {
        int n = word1.length() , m= word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int[] dp2 : dp){
            Arrays.fill(dp2 ,-1);
        }
        int ans = minDistanceDP(word1 , word2, n , m, dp , r , d , i) ;
        for(int k =0; k<dp.length; k++){
            for(int l=0; l<dp[0].length ; l++){
                System.out.print(dp[k][l] +" ");
            }
            System.out.println();
        }
        return ans;
    }
    public static int isMatch(String s, String p , int n , int m , int[][] dp){
        if(n == 0 || m ==0){
            if(n==0 && m==0){
                dp[n][m] = 1;
            }else if(m == 0){
                dp[n][m] = 0;
            }else{
                if(p.charAt(m-1) == '*'){
                    dp[n][m] = 1;
                }else{
                    dp[n][m] = 0;
                }
            }
            return dp[n][m];
        }
        if(dp[n][m] != -1){
            return dp[n][m];
        }
        char ch1 = s.charAt(n-1);
        char ch2 = p.charAt(m-1);
        if(ch1 == ch2 || ch2 == '?'){
            dp[n][m] = isMatch(s , p , n-1 , m-1 , dp);
        }else if(ch2 == '*'){
            if(isMatch(s , p , n-1 ,m , dp) == 1){
                dp[n][m] = 1;
            }else{
               dp[n][m] =  isMatch(s , p , n , m-1 , dp) == 1? 1: 0;
            }
            
        }else{
            dp[n][m] = 0;
        }
        return dp[n][m];
    }
    public static boolean isMatch(String s, String p) {
        String str = stringWithoutContiiguousStar(p);
        int[][] dp = new int[s.length()+1][str.length()+1];
        for(int[] d : dp){
            Arrays.fill(d , -1);
        }
           // System.out.println(str +" "+p);
       int ans= isMatch(s , str ,s.length() , str.length() , dp);
        return ans == 1;
    }
    public static String stringWithoutContiiguousStar(String p){
        if(p.length() <= 1){
            return p;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(p.charAt(0));
        int i=1;
        int n = p.length();
        while( i < n){
            while(i< n && sb.charAt(sb.length()-1) == '*' && p.charAt(i) ==  '*'){
                i++;
            }
            if(i < p.length() )
                sb.append(p.charAt(i));
            i++;
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        // Write your code here
      // int ans= minDistance("saturday", "sunday", 1, 2, 3);
      boolean ans = isMatch("adceb" , "*a*?");
        System.out.println(ans);
    }
}