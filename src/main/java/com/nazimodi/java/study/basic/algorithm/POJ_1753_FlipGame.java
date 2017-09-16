package com.nazimodi.java.study.basic.algorithm;

import java.util.Scanner;

/**
 * @author renqingwang on 2017/9/16.
 * @version 1.0
 */
public class POJ_1753_FlipGame {
    private static int chess;
    private static boolean isEnd;

    public static void main(String[] args) {
        int i, j;
        StringBuilder input = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        for (i = 0; i < 4; i++) {
            input.append(scanner.nextLine().trim());

        }
        for (j = 1; j <= 16; j++) {
            chess = (chess << 1) + (input.substring(j - 1, j).equals("b") ? 1 : 0);
        }
        //scanner.close();
        isEnd = judge();
        if (isEnd) {
            System.out.println(0);
        } else {
            for (i = 1; i <= 16; i++) {
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
        if (chess == 65535 || chess == 0) {
            return true;
        }
        return false;
    }

    /**
     * 将指定位置的数据翻转
     *
     * @param i 翻转位置
     */
    private static void convert(int i) {
        chess ^= 1 << (16 - i);
        if (i % 4 != 0) {
            chess ^= 1 << (16 - (i + 1));
        }
        if (i % 4 != 1) {
            chess ^= 1 << (16 - (i - 1));
        }
        if (i > 4) {
            chess ^= 1 << (16 - (i - 4));
        }
        if (i < 13) {
            chess ^= 1 << (16 - (i + 4));
        }
    }

    /**
     * 从pos位置开始寻找最优解
     *
     * @param pos   从哪开始走
     * @param now   当前走了几步
     * @param total 允许翻棋子的总数
     */
    private static void dfs(int pos, int now, int total) {
        int i = pos + 1;
        if (now == total) {
            isEnd = judge();
            return;
        }

        for (; i <= 16; i++) {
            convert(i);
            dfs(i, now + 1, total);
            if (isEnd) {
                return;
            }
            convert(i);
        }
    }
}
