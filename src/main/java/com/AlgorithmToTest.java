package com;

import com.utils.TimeTestListener;

public interface AlgorithmToTest {

    void run(TimeTestListener t);

    int[] getSortedArray();
}
