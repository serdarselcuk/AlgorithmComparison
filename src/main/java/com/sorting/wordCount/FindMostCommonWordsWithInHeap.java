package com.sorting.wordCount;

import static com.utils.Constants.*;

import com.AlgorithmToTest;
import com.utils.TimeTestListener;

import java.util.*;

@Deprecated
public class FindMostCommonWordsWithInHeap implements AlgorithmToTest {
    private final HashMap<String,Integer> seenInTopWords = new HashMap<>();
    private static PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue, Collections.reverseOrder()));
    private String[] args;

    public FindMostCommonWordsWithInHeap(Object a){
        args = ((String)a).split(TEXT_SPLITTER);
    }

    public void run(TimeTestListener t) {
        System.out.println(FindMostCommonWords.class.getSimpleName()+" testing stars");
        t.start(this);
        this.find(args);
        t.end();
        t.fireEndEvent();

        int word = 1;

        while (DEFAULT_WORD_COUNT>=word && !minHeap.isEmpty()) {
            Map.Entry<String, Integer> wordFrequency = minHeap.poll();
            System.out.println("word "+word++ +" "+wordFrequency.getKey() + ": " + wordFrequency.getValue());
        }
    }

    @Override
    public int[] getSortedArray() {
        return new int[0];
    }

    private void find(String[] strings){
        for (String word : strings) {
            if (!seenInTopWords.containsKey(word)) {
                seenInTopWords.put(word, 1);
                minHeap.add(new AbstractMap.SimpleEntry<>(word, 1));

            } else {

                if(seenInTopWords.size()<5){
                    seenInTopWords.put(word, 1);
                    minHeap.add(new AbstractMap.SimpleEntry<>(word, 1));
                }else {
//                    int frequency = seenInTopWords.get(word);
//                   minHeap.stream().filter(p-> p.getKey().equals(word)).findFirst();
//                    wordFrequency.setValue(wordFrequency.getValue() + 1);
//                    minHeap.add(wordFrequency);
                }


            }
        }
    }
}
