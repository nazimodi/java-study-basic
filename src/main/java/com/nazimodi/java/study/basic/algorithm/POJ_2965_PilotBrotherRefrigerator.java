package com.nazimodi.java.study.basic.algorithm;

import java.util.Scanner;

/**
 * 参考高手的高效解法：
 > 证明:要使一个为'+'的符号变为'-',必须其相应的行和列的操作数为奇数;可以证明,如果'+'位置对应的行和列上每一个位置都进行一次操作,
 则整个图只有这一'+'位置的符号改变,其余都不会改变.
 > 设置一个4*4的整型数组,初值为零,用于记录每个点的操作数,那么在每个'+'上的行和列的的位置都加1,得到结果模2(因为一个点进行偶数次
 操作的效果和没进行操作一样,这就是楼上说的取反的原理),然后计算整型数组中一的
 > 个数即为操作数,一的位置为要操作的位置(其他原来操作数为偶数的因为操作并不发生效果,因此不进行操作)
 *********************************
 此上证其可以按以上步骤使数组中值都为‘-’
 ********************************
 在上述证明中将所有的行和列的位置都加1后，在将其模2之前,对给定的数组状态，将所有的位置操作其所存的操作数个次数，举例，如果a[i][j]==n,
 则对（i,j）操作n次，当所有的操作完后，即全为‘-’的数组。其实就是不模2的操作，作了许多的无用功。
 以上的操作次序对结果无影响，如果存在一个最小的步骤，则此步骤一定在以上操作之中。（简单说下：因为以上操作已经包含了所有可改变欲改变位置的操作了）
 而模2后的操作是去掉了所有无用功之后的操作，此操作同样包含最小步骤。
 但模2后的操作去掉任何一个或几个步骤后，都不可能再得到全为‘-’的。（此同样可证明：因为操作次序无影响，先进行最小步骤，得到全为‘-’，如果
 还剩下m步，则在全为‘-’的数组状态下进行这m步操作后还得到一个全为‘-’的数组状态，此只能是在同一个位置进行偶数次操作，与前文模2后矛盾，所以m=0），
 因此模2后的操作即为最小步骤的操作。
 
 * @author renqingwang on 2017/9/23.
 * @version 1.0
 */
public class POJ_2965_PilotBrotherRefrigerator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean[][] steps = new boolean[4][4];
        boolean temp;
        int[][] result = new int[16][2];
        //Arrays.fill(steps, new boolean[4]);
        String handle;
        int i, j, k, total = 0;
        for (i = 0; i < 4; i++) {
            handle = input.nextLine().trim();
            for (j = 0; j < 4; j++) {
                temp = handle.substring(j, j + 1).equals("+");
                if (temp) {
                    steps[i][j] = !steps[i][j];
                    for (k = 0; k < 4; k++) {
                        steps[i][k] = !steps[i][k];
                        steps[k][j] = !steps[k][j];
                    }
                }
            }
        }

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (steps[i][j]) {
                    result[total++] = new int[]{i + 1, j + 1};
                }
            }
        }
        if (total > 0) {
            System.out.println(total);
            for (i = 0; i < total; i++) {
                System.out.println(result[i][0] + " " + result[i][1]);
            }
        }
    }
}
