package com.sorting;

import com.AlgorithmToTest;
import com.utils.TimeTestListener;
import com.utils.Utils;

import java.util.Arrays;

import static com.utils.Constants.BUBBLES_SORT_EFFICENCY_LIMIT;

public class CombinedSort implements AlgorithmToTest {
    private int[] arr;
    private boolean done;

    public CombinedSort(int[] arr) {
        this.arr = arr;
        done = false;
    }

    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1,0};
//                Utils.generateLargeArray(10);
        AlgorithmToTest a = new CombinedSort(array);
        a.run(TimeTestListener.getInstance(true));
        System.out.println(Arrays.toString(a.getSortedArray()));
    }

    @Override
    public void run(TimeTestListener t) {
        t.start(this);
        arr = combinedSort(arr);
        t.end();
        t.fireEndEvent();
        System.out.println(this);
        done = true;
    }

    @Override
    public int[] getSortedArray() {
        return done ? arr : null;
    }

    //modified merge method will use bubbleSort when array is smaller than 15
    public int[] combinedSort(int[] array) {
        if (array.length < 2) return array;
        int mid = array.length / 2;
        // if provided array is smaller than 16 than we will apply bubble sort
        if (array.length < BUBBLES_SORT_EFFICENCY_LIMIT) {
            BubbleSort bubble = new BubbleSort(array);
            bubble.sort();
            return bubble.setDone(true).getSortedArray();
        }
        int[][] splitedArrays = splitArrays(array, mid);
        splitedArrays[0] = combinedSort(splitedArrays[0]);
        splitedArrays[1] = combinedSort(splitedArrays[1]);

        return merge(splitedArrays[0], splitedArrays[1]);
    }

    public int[][] splitArrays(int[] array, int from) {
        int[] l = Arrays.copyOfRange(array, 0, from);
        int[] r = Arrays.copyOfRange(array, from, array.length);
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
    public String toString() {
        return "\nCombinedSort{" +
//                "arr=" + Arrays.toString(arr) +
                "\n, array Length=" + arr.length +
                '}';
    }
}
