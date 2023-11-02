package com.sorting;

import com.AlgorithmToTest;
import com.TestTimeDifference;
import com.utils.TimeTestListener;

public class MergeSort implements AlgorithmToTest {
    private int[] arr;
    private boolean done;


    public MergeSort(Object arr) {
        this.arr = (int[]) arr;
        done = false;
    }

    public static void main(String[] args) {
        int[] array = TestTimeDifference.generateLargeArray(100);
        new MergeSort(array).run(TimeTestListener.getInstance(true));

    }

    @Override
    public int[] getSortedArray() {
        return done ? arr : null;
    }

    public int[] mergeSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        int[][] splitedArrays = splitArrays(array, mid);
        int[] l = mergeSort(splitedArrays[0]);
        int[] r = mergeSort(splitedArrays[1]);

        return merge(l, r);
    }

    public int[][] splitArrays(int[] array, int from) {
        int[] l = new int[from];
        int[] r = new int[array.length - from];

        for (int i = 0; i < from; i++) {
            l[i] = array[i];
        }
        for (int i = from; i < array.length; i++) {
            r[i - from] = array[i];
        }
        return new int[][]{l, r};
    }

    public int[] merge(int[] leftArray, int[] rightArray) {
        int[] array = new int[leftArray.length + rightArray.length];
        int i = 0, j = 0, k = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }
        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
        return array;
    }

    @Override
    public void run(TimeTestListener t) {
        t.start(this);
        arr = mergeSort(arr);
        t.end();
        t.fireEndEvent();
        System.out.println(this);
        done = true;
    }

    @Override
    public String toString() {
        return "MergeSort{" +
//                "\narr=" + Arrays.toString(arr) +
                "\narray length = " + arr.length +
                '}';
    }
}
