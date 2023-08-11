package org.labuladong.dynamicprograming;

import java.util.Arrays;

/**
 * @author luojx
 * @date 2022/6/23 10:48
 */
public class Main {

    /**
     * change coin
     */
    int res = Integer.MAX_VALUE;
    int[] memo;
    public int changeCoins(int[] coins, int amount){
        memo = new int[amount + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return coinRecursion(coins, amount);
    }
    private int coinRecursion(int[] coins, int amount){
        if (amount == 0){
            return 0;
        }
        if(amount < 0){
            return -1;
        }
        if(memo[amount] != Integer.MAX_VALUE){
            return memo[amount];
        }
        for (int coin : coins) {
            int changeTimes = coinRecursion(coins, amount - coin);
            if(changeTimes == -1){
                continue;
            }
            res = Math.min(res, 1 + changeTimes);
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
    }

    int[] dp;
    public int coinChangeFx(int[] coins, int amount){
        dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if(i - coin < 0){
                    continue;
                }
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
