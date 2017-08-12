package com.nazimodi.java.study.basic;


/**
 * @author renqingwang on 2017/8/6.
 * @version 1.0
 */
public class QuickSort {
    private String namePri;
    public String namePub;
    public static void main(String[] args) {
        int[] sortArr = {49, 38, 65, 97, 76, 13, 27, 49};
        System.out.println("start");
        printArray(sortArr);

        quickSort(sortArr, 0, sortArr.length - 1);
    }

    public static void quickSort(int[] sortArray, int lowIndex, int highIndex) {
        int low = lowIndex;
        int high = highIndex;
        int middle = sortArray[(lowIndex + highIndex) / 2];
        System.out.println("lowIndex:" + lowIndex + " highIndex:" + highIndex + " middle:" + middle);
        if (lowIndex < highIndex) {
            while (low <= high) {
                while (low < highIndex && middle > sortArray[low])
                    low++;
                while (high > lowIndex && middle < sortArray[high])
                    high--;
                if (low <= high) {
                    swap(sortArray, low, high);
                    low++;
                    high--;
                }
            }
            if (high > lowIndex)
                quickSort(sortArray, lowIndex, high);
            if (low < highIndex)
                quickSort(sortArray, low, highIndex);
        }
    }

    private static void swap(int[] arr, int from, int to) {
        if (arr != null && from < arr.length && to < arr.length) {
            int temp = arr[from];
            arr[from] = arr[to];
            arr[to] = temp;
            printArray(arr);
        }
    }

    private static void printArray(int[] array) {
        StringBuilder string = new StringBuilder();
        for(int item: array) {
            string.append(" ").append(item);
        }
        System.out.println(string);
    }

    public void justForTest() {
        System.out.print(new Throwable().getStackTrace()[0].getMethodName() + "\n");
    }
}
