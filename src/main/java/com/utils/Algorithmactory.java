package com.utils;

import com.AlgorithmToTest;
import com.sorting.BubbleSort;
import com.sorting.CombinedSort;
import com.sorting.MergeSort;

import java.util.Locale;

public class Algorithmactory {
    public static String BUBBLE_SORT = "Bubble sort";
    public static String MERGE_SORT = "Merge sort";
    public static String COMBINED_SORT = "Combined sort";

    public static AlgorithmToTest getAlgorithm(String algorithmName, int[] array) {
        String alg = algorithmName.toUpperCase(Locale.ROOT);
        if (algorithmName.equalsIgnoreCase("sort")) {
            return null;
        }
        if (BUBBLE_SORT.toUpperCase(Locale.ROOT).contains(alg)) {
            return new BubbleSort(array);
        }
        if (MERGE_SORT.toUpperCase(Locale.ROOT).contains(alg)) {
            return new MergeSort(array);
        }
        if (COMBINED_SORT.toUpperCase(Locale.ROOT).contains(alg)) {
            return new CombinedSort(array);
        }
        return null;
    }
}
