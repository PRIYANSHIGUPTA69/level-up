import java.util.*;
// ============================= cut strategy============================
public class Set2{

    // boolean paranthesis
    static int mod = 1003;
    public static class Pair{
        int tc ;
        int fc;
        Pair(int tc , int fc){
            this.tc = tc;
            this.fc = fc;
        }
    }
    static void eval(Pair left , Pair right , Pair ans , char op){
        long totalCount = ((left.tc + left.fc)*(right.tc + right.fc))%mod;
        // left.tc*right.tc+left.tc+right.fc + left.fc*right.fc+left.fc+left.fc*right.tc
        long falseCount = 0 , trueCount =0;
        if(op == '|'){
            // total count - false count
             falseCount = (left.fc*right.fc)%mod;
             trueCount = (totalCount - falseCount + mod)%mod;
        }else if(op ==  '&'){
          trueCount = (left.tc*right.tc)%mod;
          falseCount = (totalCount - trueCount + mod)%mod;
        }else{
             trueCount = ((left.tc*right.fc)+ (right.tc*left.fc))%mod;
            falseCount = (totalCount - trueCount + mod)%mod;
        }
        ans.tc += trueCount;
        ans.fc += falseCount;
    }
    static Pair countWays( String S , Pair[][] dp , int si , int ei){
      if(ei == si){
          Pair base = new Pair(0 ,0);
         base.tc= S.charAt(si) == 'T' ? 1:0 ;
         base.fc = S.charAt(si) == 'F' ? 1: 0;
         return dp[si][ei] = base;
      }
      if(dp[si][ei] != null){
          return dp[si][ei];
      }
       Pair ans = new Pair(0 ,0);
        for(int cut = si+1; cut<ei ; cut+=2){
            Pair left = countWays(S , dp , si , cut-1);
            Pair right = countWays(S , dp , cut+1 , ei);
            eval(left , right , ans , S.charAt(cut));
        }
        return dp[si][ei] = ans;
    }
    static int countWays(int N, String S){
        // code here
        Pair[][] dp = new Pair[N][N];
        Pair ans = countWays(S , dp , 0 , N-1);
        long count = ans.tc%mod;
        return (int)count;
    }

    // burst ballon
    public int maxCoins(int[] nums , int[][] dp , int si , int ei){
        if(dp[si][ei] != -1){
            return dp[si][ei];
        }
        int maxCoins = Integer.MIN_VALUE;
        int lele = si==0?1:nums[si-1];
        int rele = ei==nums.length-1?1:nums[ei+1];
        for(int cut = si ; cut<= ei; cut++){
            int left = cut == si ? 0 : maxCoins(nums , dp , si , cut-1 );
            int right = cut == ei? 0: maxCoins(nums , dp , cut+1 , ei);
            maxCoins = Integer.max(maxCoins , left + lele*nums[cut]*rele +right );
        }
        return dp[si][ei] = maxCoins;
    }
    public int maxCoins(int[] nums) {
     int[][] dp = new int[nums.length][nums.length];;
        for(int[] d: dp){
            Arrays.fill(d , -1);
        }
        return maxCoins(nums , dp , 0 , nums.length-1);
    }
    // optimal binary tree
    public int optimalBinarySearchTree(int[][] dp , int[] fre , int si , int ei){
        if(dp[si][ei] != 0){
            return dp[si][ei];
        }
        int min = (int)1e9;
        int sum =0;
        for(int cut = si; cut<= ei; cut++){
            int left = cut == si ? 0: optimalBinarySearchTree(dp , fre ,si , cut-1 );
            int right = cut == ei ? 0: optimalBinarySearchTree(dp , fre , cut+1 , ei);
            sum += fre[cut];
            min = Math.min(min , left+0+right);
        }
        return dp[si][ei] = min+sum;
    }
    public int optimalBinaryTree(int[] keys , int[] fre){
        int[][] dp = new int[keys.length][keys.length];
        return optimalBinarySearchTree(dp , fre , 0 , fre.length);
    }

    // 95 unique BSTs
    public static class TreeNode{
        int val ;
        TreeNode left = null;
        TreeNode right = null;
        TreeNode(int val){
            this.val = val;
        }
        TreeNode(int val , TreeNode left , TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public static List<TreeNode> generateTrees(int si , int ei){
        if(si > ei){
             List<TreeNode> base = new ArrayList<>();
            base.add(null);;
            return base;
        }
        if(si == ei){
            List<TreeNode> base = new ArrayList<>();
            TreeNode root = new TreeNode(si);
            base.add(root);
            return base;
        }
        List<TreeNode>res = new ArrayList<>();
        for(int cut = si; cut<= ei; cut++){
            List<TreeNode>left = generateTrees(si , cut-1);
            List<TreeNode>right = generateTrees(cut+1 , ei);
            for(TreeNode r : right){
                for(TreeNode l : left){
                    TreeNode root = new TreeNode(cut);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
    public static List<TreeNode> generateTrees(int n) {
        return generateTrees(1 , n);
    }

    // minimum score of triangulation
    public  static int minScoreTriangulation(int[] val , int[][] dp , int si  , int ei){
        if(ei - si<= 1){
            return dp[si][ei] = 0;
        }
        if(dp[si][ei] !=0 ){
            return dp[si][ei];
        }
        int res = (int)1e9;
        for(int cut = si+1; cut<ei ; cut++){
            int left = minScoreTriangulation(val  , dp, si , cut );
            int right = minScoreTriangulation(val  , dp , cut , ei);
            res = Math.min(res, left + val[si]*val[cut]*val[ei] + right);
        }
        return dp[si][ei] = res;
    }
    public static int minScoreTriangulation(int[] val) {
        int[][] dp = new int[val.length][val.length];
        return minScoreTriangulation(val , dp , 0 , val.length-1);
    }
    public static void main(String[] args) {
        //uniquePathsWithObstacles()
     }
}
