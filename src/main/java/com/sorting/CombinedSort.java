package com.sorting;

import com.AlgorithmToTest;
import com.TestTimeDifference;
import com.utils.TimeTestListener;

import static com.utils.Constants.BUBBLES_SORT_EFFICENCY_LIMIT;

public class CombinedSort implements AlgorithmToTest {
    private int[] arr;
    private MergeSort mergeSort;
    private BubbleSort bubble;
    private boolean done;

    public CombinedSort(int[] arr) {
        this.arr = arr;
        done = false;
    }

    public static void main(String[] args) {
        int[] array = TestTimeDifference.generateLargeArray(100);
        AlgorithmToTest a = new CombinedSort(array);
        a.run(TimeTestListener.getInstance(true));
        a.getSortedArray();
    }

    @Override
    public void run(TimeTestListener t) {
        t.start(this);
        merge(arr);
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
    public int[] merge(int[] array) {
        mergeSort = new MergeSort(arr);
        if (array.length < 2) return array;
        int mid = array.length / 2;
        // if provided array is smaller than 16 than we will apply bubble sort
        if (array.length < BUBBLES_SORT_EFFICENCY_LIMIT) {
            bubble = new BubbleSort(array);
            bubble.sort();
            return bubble.setDone(true).getSortedArray();
        }
        int[][] splitedArrays = mergeSort.splitArrays(array, mid);
        splitedArrays[0] = merge(splitedArrays[0]);
        splitedArrays[1] = merge(splitedArrays[1]);

        return mergeSort.merge(splitedArrays[0], splitedArrays[1]);
    }

    @Override
    public String toString() {
        return "\nCombinedSort{" +
//                "arr=" + Arrays.toString(arr) +
                "\n, array Length=" + arr.length +
                '}';
    }
}
