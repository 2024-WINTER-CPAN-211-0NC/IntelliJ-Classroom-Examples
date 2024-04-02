package org.humber.dsa.week11;

import java.util.Arrays;
import java.util.List;

public class BinarySearch {


    /**
     * Search for key using Binary Search algorithm
     * @return TRUE if key is found
     * FALSE, otherwise
     * */
    public static boolean search(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                low = mid + 1; // Discard the left half
            } else {
                high = mid - 1; // Discard the right half
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<Integer> keys = Arrays.asList(1, 10, 5, 4, 9, 11);

        System.out.println("Searching Elements from array: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10");

        for (int key : keys) {
            System.out.println("Element " + key + " is " + (search(sortedArray, key) ? "Found" : "Not Found"));
        }
    }
}
