package com.nazimodi.java.study.basic.algorithm;

import java.util.Scanner;

/**
 * 因为数的范围是1~100，每个数有100中调整的可能性，采用动态规划的思路。
 建立大小为(n+1)*101的二维数组rec记录所有可能调整的代价，第一行初始化为全0，其他行为最大值。
 rec中第i行对应A[i-1]。对于每个数A[i]，调整后的结果有100种，用rec[i][j]表示数字A[i]调整为j的最小代价。
 对于每个rec[i][j]，A[i-1]调整到k的代价加上A[i]调整到j的最小代价即为rec[i][j]的代价。而k又有100种选择，
 对于j，当|j-k|的绝对值不大于target时，代价最小，当前rec[i][j]为rec[i-1][k] +( j - A[i-1])，rec[i][j]
 保留所有可能代价中的最小代价。
 最后，rec[n][0~100]中的最小代价即为对整个数组调整后的最下代价。

 * @author renqingwang on 2017/12/9.
 * @version 1.0
 */
public class MinAdjustmentCost {
    public static int resolve(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //数组默认初始化为0
        //rec[i][j]表示第i个数调整到j的最小代价，k表示相邻两个数调整到某个值的所有可能
        int[][] minAdjustmentCost = new int[arr.length + 1][101];
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j <= 100; j++) {
                minAdjustmentCost[i][j] = Integer.MAX_VALUE;
                //如果k在target范围内，更新rec[i][j]的值为上一个数到k的代价，加上当前数到j的代价
                for (int k = Math.max(0, j - target), upper = Math.min(100, j + target); k <= upper; k++) {
                    minAdjustmentCost[i][j] = Math.min(minAdjustmentCost[i][j], minAdjustmentCost[i - 1][k] + Math.abs(arr[i - 1] - j));
                }
            }
        }

        for (int i = 0; i <= 100; i++) {
            if (result > minAdjustmentCost[arr.length][i])
                result = minAdjustmentCost[arr.length][i];
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = 0;

        while ((n = scanner.nextInt()) > 0) {

            int[] arr = new int[n];
            int target = 0, result = 0;
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(scanner.next());
            }
            target = scanner.nextInt();
            result = resolve(arr, target);
            System.out.println(result);
        }

    }
}
