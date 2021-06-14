package gcode.com.workspace.s_48;

import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] nums = {10,9,2,5,3,7,101,18};
        if(nums.length == 0) System.out.println(0);
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 1;
        for(int i=1;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            res = Math.max(res,dp[i]);
        }
        System.out.println(res);
    }
}