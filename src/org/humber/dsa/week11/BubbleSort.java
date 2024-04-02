package org.humber.dsa.week11;

public class BubbleSort {

    /**
     * Sort the Array using bubble sort.
     * */
    public static int[] sort(int[] arr) {
        //Write your code here
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] unsortedArrays = {
                {4, 5, 6, 1, 2, 3},
                {6, 5, 4, 3, 2, 1},
                {1, 2, 3, 6, 4, 5}
        };

        for (int[] array : unsortedArrays) {
            System.out.print("UnSorted Array: ");
            printArray(array);
            int[] sortedArray = sort(array);
            System.out.print("Sorted Array: ");
            printArray(sortedArray);
            System.out.println("--------------------------");
        }
    }

    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
