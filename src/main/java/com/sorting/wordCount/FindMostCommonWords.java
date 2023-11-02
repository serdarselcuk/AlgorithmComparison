package com.sorting.wordCount;

import com.AlgorithmToTest;
import com.utils.TimeTestListener;

import java.util.*;
import static com.utils.Constants.*;

@Deprecated
public class FindMostCommonWords  implements AlgorithmToTest {
    private Map<String, Integer> wordFrequencies = new HashMap<>();
    private static List<Map.Entry<String, Integer>> mostCommonWords;
    private String[] args;

    public FindMostCommonWords(Object a){
        args = ((String)a).split(TEXT_SPLITTER);
    }

    @Override
    public void run(TimeTestListener t) {

        System.out.println(FindMostCommonWords.class.getSimpleName()+" testing stars");
        t.start(this);
       this.find(args);
        t.end();
        t.fireEndEvent();

        int word = 1;
        while (DEFAULT_WORD_COUNT >= word && mostCommonWords.size() >= word ) {
            Map.Entry<String, Integer> frequentWord = mostCommonWords.get(word-1);
            System.out.println("word "+word++ +" "+ frequentWord.getKey() + ": " + frequentWord.getValue());
        }
    }

    @Override
    public int[] getSortedArray() {
        return new int[0];
    }

    private void find(String[] strings){

        for (String word :strings) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        mostCommonWords = new ArrayList<>(wordFrequencies.entrySet());
        mostCommonWords.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));
    }

}
