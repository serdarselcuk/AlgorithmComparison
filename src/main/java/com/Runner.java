package com;

import static com.utils.Algorithmactory.*;

public class Runner {

    public static void main(String[] args) {

        TestTimeDifference.start(COMBINED_SORT, MERGE_SORT,new int[]{3,5,7,10, 20, 30, 40, 50, 75, 100, 150, 300, 500, 1000}, 15, 100001);
    }
}
