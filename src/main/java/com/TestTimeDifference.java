package com;

import com.utils.Algorithmactory;
import com.utils.ComparisonSortPlot;
import com.utils.TimeTestListener;
import com.utils.Utils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static com.utils.Constants.DEFAULT_ARRAS_LENGTH_ARRAY;
import static com.utils.Constants.SEPARATOR_LINE;

public class TestTimeDifference {

    public static void main(String[] args) {

    }

    public static void start(String algorithm1, String algorithm2, int[] testArraysLength, int repeatCount, int nanoSecUntilArraylength) {

//        String[] text = args==null? generateLargeText(LARGE_TEXT_SIZE).split(TEXT_SPLITTER):args;
        int[] array1;
        TreeMap<Integer, Double> firstAlgResultsMap = new TreeMap<>();
        TreeMap<Integer, Double> secondAlgResultsMap = new TreeMap<>();

//        if length array not provided we will use default
        testArraysLength = (testArraysLength == null || testArraysLength.length <= 0) ?
                DEFAULT_ARRAS_LENGTH_ARRAY :
                testArraysLength;

        try {
//            for each array size we will generate an array and test algorithms for @repeatCount
            for (int arraySize : testArraysLength) {
                System.out.println(SEPARATOR_LINE + "\n" + "New array length: " + arraySize);
//                until array size reaches the value we will use nano-seconds
                boolean nanoTime = arraySize <= nanoSecUntilArraylength;
                int turn = 0;
                int repeat = repeatCount;
                long[] results1stAlg = new long[repeatCount];
                long[] results2ndAlg = new long[repeatCount];
                while (repeat-- > 0) {
                    array1 = Utils.generateLargeArray(arraySize);
                    int[] array2 = Arrays.copyOf(array1, array1.length);
                    System.out.println(SEPARATOR_LINE + "\nRepeat count: " + repeat + "\nArray size:" + array1.length + "\n" + SEPARATOR_LINE);
                    AlgorithmToTest alg1 = Algorithmactory.getAlgorithm(algorithm1, array1);
                    AlgorithmToTest alg2 = Algorithmactory.getAlgorithm(algorithm2, array2);
                    // if array size <6 time will be watched in nano-seconds
                    long result[] = compareAlgorithms(alg1, alg2, TimeTestListener.getInstance(nanoTime));
                    results1stAlg[turn] = result[0];
                    results2ndAlg[turn++] = result[1];
                }
                firstAlgResultsMap.put(arraySize, mifOfArray(results1stAlg));
                secondAlgResultsMap.put(arraySize, mifOfArray(results2ndAlg));
            }
        } catch (InputMismatchException e) {

            System.out.println("\nerror on array sizes!!!\n" + e);
        }

        System.out.println(algorithm1 + " results:" + firstAlgResultsMap + "\n" + algorithm2 + " results:" + secondAlgResultsMap);

//      printing graphic
        new ComparisonSortPlot(algorithm1, algorithm2).graphicalView(
                getTreeMapValuesInSortedOrder(firstAlgResultsMap),
                getTreeMapValuesInSortedOrder(secondAlgResultsMap),
                firstAlgResultsMap.keySet().stream().map(p -> p + "").collect(Collectors.toList()));

    }

    private static ArrayList<Double> getTreeMapValuesInSortedOrder(TreeMap<Integer, Double> treeMap) {

        // Create an ArrayList to store the TreeMap values.
        ArrayList<Double> values = new ArrayList<>();

        // Iterate over the TreeMap and add each value to the ArrayList in the correct order.
        for (Integer key : treeMap.keySet()) {
            values.add(treeMap.get(key));
        }

        return values;
    }

    private static double mifOfArray(long[] array) {
        long sum = 0;
        for (long element : array) {
            sum += element;
        }

        // Divide the sum by the number of elements in the array.
        return sum / array.length;
    }


    public static long[] compareAlgorithms(AlgorithmToTest a, AlgorithmToTest b, TimeTestListener t) {
        // Test the first method.
        long time_a = runAlgorithm(a, t);
        // Test the second method.
        long time_b = runAlgorithm(b, t);
        return new long[]{time_a, time_b};
    }

    static long runAlgorithm(AlgorithmToTest a, TimeTestListener t) {
        if (a == null) return -1;

        a.run(t);
        if(!Utils.isSorted(a.getSortedArray())){
            throw new AssertionError(a.getClass().getSimpleName()+" is not sorted\n"+ Arrays.toString(a.getSortedArray()));
        }
        System.out.println(SEPARATOR_LINE);
        return t.getTimeDifference();
    }


    private static String generateLargeText(int textSize) {
        // Generate a very large text.
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < textSize; i++) {
            Random random = new Random();
            byte[] bytes = new byte[10];
            random.nextBytes(bytes);
            // Convert the byte array to a string.
            text.append(new String(bytes, Charset.forName("UTF-8")));
        }

        return text.toString();
    }

}