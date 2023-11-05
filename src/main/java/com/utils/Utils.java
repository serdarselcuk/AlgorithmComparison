package com.utils;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Set;

public class Utils {

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("sort is failed at array order:"+ i);
                return false;
            }
        }
        return true;
    }

    public static int[] generateLargeArray(int size) throws InputMismatchException {
        Random random = new Random();
        int[] arr = new int[size];
        Set<Integer> intSet = new HashSet<>();
        while (intSet.size() < size) {
            int i = random.nextInt();
            if (i > 0) {
                intSet.add(i);
            }
        }
        Integer[] array = intSet.toArray(Integer[]::new);
        int i = 0;
        if (arr.length != array.length) {
            throw new InputMismatchException("array 1 length=" + arr.length + "\n array 2 length=" + array.length);
        }
        for (Integer num :
                array) {
            arr[i] = array[i++];
        }
        return arr;

    }


}
