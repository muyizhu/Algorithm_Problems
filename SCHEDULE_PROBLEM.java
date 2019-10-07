import java.util.Arrays;

public class SCHEDULE_PROBLEM {
    //arrival: 公司到达时间
    //duration：公司活动时间
    //返回值：让最多的公司举行活动
    public static int schedule(int[] arrival, int[] duration){
        int max=0;
        int[] dp = new int[arrival.length];
        for(int i = 0;i<arrival.length;i++){
            if(dp[i] == 0){
                max = Math.max(helper(arrival,duration,i,dp),max);
            }
            System.out.println(Arrays.toString(dp));
        }
        return max;
    }
    public static int helper(int[] arrival,int[] duration,int index,int[] dp){
        int max = 1;
        for(int i = 0;i<arrival.length;i++){
            if(arrival[i]>=arrival[index]+duration[index]){
                if(dp[i] == 0){
                    dp[i] = helper(arrival,duration,i,dp);
                    max = Math.max(dp[i]+1,max);
                }
                else{
                    max = Math.max(dp[i]+1,max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {2,1,3,6};
        int[] b = {10,10,10,10};
        System.out.println(schedule(a,b));
    }
}
