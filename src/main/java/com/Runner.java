package com;

import static com.utils.Algorithmactory.*;

public class Runner {

    public static void main(String[] args) {

        TestTimeDifference.start(COMBINED_SORT, MERGE_SORT, new int[]{3,5,7,10,17,25,100,125}, 50, 100001);
    }
}
