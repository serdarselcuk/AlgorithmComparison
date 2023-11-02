package com.sorting;

import com.AlgorithmToTest;
import com.utils.TimeTestListener;

import java.sql.Time;
import java.util.Arrays;

public class BubbleSort implements AlgorithmToTest {
private int[] arr;
    private boolean done;

    public BubbleSort(Object arr){
        this.arr = ((int[])arr);
        done = false;
    }

    @Override
    public void run(TimeTestListener t) {
        t.start(this);
        sort();
        t.end();
        t.fireEndEvent();
        System.out.println(this);
        done = true;
    }

    @Override
    public int[] getSortedArray(){
        return done? arr: null;
    }

    public BubbleSort setDone(boolean done){
        this.done = done;
        return this;
    }

    // An optimized version of Bubble Sort
    void sort() {
        boolean swapped = true;
        int i = 0;
//        while there are elements needs to be swapped we are keeping to loop array. Since bigger elements are being
//        move to the end each time we can loop for 1 element less
        while (swapped && arr.length-i>0) {
            swapped = false;
            for (int j = 0; j < arr.length-i-1; j++) {
//          if current node is bigger than the next one than switch
                if (arr[j] > arr[j + 1]) {
                    swapped =  swap(j);
                }
            }
//       increasing turn number to understand when to stop
            i++;
        }
    }

//    swapping elements in the array for the given order with the next one
   private boolean swap(int order){
        if (order <= arr.length-1) {
            int temp = arr[order];
            arr[order] = arr[order + 1];
            arr[order + 1] = temp;
        }else{
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BubbleSort{" +
//                "\narr=" + Arrays.toString(arr) +
                "\narray length = "+ arr.length +
                '}';
    }
}
