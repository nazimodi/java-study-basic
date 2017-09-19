package com.nazimodi.java.study.basic.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 解题思路：该题题意是为了求出能够覆盖所有岛屿的最小雷达数目，每个小岛对应x轴上的一个区间，在这个区间内的任何一个点放置雷达，则可以覆盖该小岛，
 * 区间范围的计算用[x-sqrt(d*d-y*y),x+sqrt(d*d-y*y)];这样，问题即转化为已知一定数量的区间，求最小数量的点，使得每个区间内斗至少存在一个点。
 * 每次迭代对于第一个区间，选择最右边一个点， 因为它可以让较多区间得到满足， 如果不选择第一个区间最右一个点（选择前面的点）， 那么把它换成最右
 * 的点之后，以前得到满足的区间， 现在仍然得到满足， 所以第一个区间的最右一个点为贪婪选择， 选择该点之后， 将得到满足的区间删掉，进行下一步迭
 * 代， 直到结束。
 * @author renqingwang on 2017/9/19.
 * @version 1.0
 */
public class POJ_1328_RadarInstallation {
    public static void main(String[] args) {

        double[][] result = new double[1005][2];
        int[] output = new int[1005];
        double temp, rightPoint;
        int islandNum, radarDistance, x, y, radarNum, round = 0;
        Scanner input = new Scanner(System.in);
        while (true) {
            round++;
            islandNum = input.nextInt();
            radarDistance = input.nextInt();
            input.nextLine();
            if (islandNum == 0 && radarDistance == 0) {
                break;
            }
            radarNum = 1;
            for (int i = 0; i < islandNum; i++) {
                x = input.nextInt();
                y = input.nextInt();
                if (y > radarDistance) {
                    radarNum = -1;
                    output[round] = -1;
                }
                temp = Math.sqrt(radarDistance * radarDistance - y * y);
                result[i][0] = x - temp;
                result[i][1] = x + temp;
            }
            if (radarNum != -1) {
                Arrays.sort(result, 0, islandNum, new Comparator<double[]>() {
                    @Override
                    public int compare(double[] o1, double[] o2) {
                        return Double.compare(o1[0], o2[0]);
                    }
                });
                rightPoint = result[0][1];
                for (int i = 1; i < islandNum; i++) {
                    if (rightPoint < result[i][0]) {
                        radarNum++;
                        rightPoint = result[i][1];
                    } else if (rightPoint > result[i][1]) {
                        rightPoint = result[i][1];
                    }
                }
                output[round] = radarNum;

            }
        }
        for (int i = 1; i < round; i++) {
            System.out.println("Case " + i + ": " + output[i]);
        }
    }
}