package com.nazimodi.java.study.basic.algorithm;

import java.util.Scanner;

/**
 * @author renqingwang on 2017/9/16.
 * @version 1.0
 */
public class POJ_1753_FlipGame {
    private static int[] chess = new int[17];
    private static boolean isEnd;
    private static int chessNum = 16;
    private static int length = 4;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            String input = scanner.nextLine();
            String[] row = input.split("");
            for (int j = 1; j <= length; j++) {
                chess[i * length + j] = row[j - 1].equals("b") ? 1 : 0;
            }
        }
        //scanner.close();
        isEnd = judge();
        if (isEnd) {
            System.out.println(0);
        } else {
            int i;
            for (i = 1; i <= chessNum; i++) {
                isEnd = false;
                dfs(0, 0, i);
                if (isEnd) {
                    break;
                }
            }
            if (isEnd) {
                System.out.println(i);
            } else {
                System.out.println("Impossible");
            }
        }
    }

    /**
     * 判断是否完结
     *
     * @return boolean
     */
    private static boolean judge() {
        for (int i = 2; i <= chessNum; i++) {
            if (chess[i - 1] != chess[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将指定位置的数据翻转
     *
     * @param i 翻转位置
     */
    private static void convert(int i) {
        chess[i] ^= 1;
        if (i % length != 0) {
            chess[i + 1] ^= 1;
        }
        if (i % length != 1) {
            chess[i - 1] ^= 1;
        }
        if (i > length) {
            chess[i - length] ^= 1;
        }
        if (i < 13) {
            chess[i + length] ^= 1;
        }
    }

    /**
     * 从pos位置开始寻找最优解
     *
     * @param pos 从哪开始走
     * @param now 当前走了几步
     * @param total 允许翻棋子的总数
     */
    private static void dfs(int pos, int now, int total) {
        int i = pos + 1;
        if (now == total) {
            isEnd = judge();
            return;
        }

        for (; i <= chessNum; i++) {
            convert(i);
            dfs(i, now + 1, total);
            if (isEnd) {
                return;
            }
            convert(i);
        }
    }
}
