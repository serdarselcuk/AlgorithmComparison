package com.utils;

import com.AlgorithmToTest;

import java.util.ArrayList;
import java.util.List;

public class TimeTestListener {

    private long startTime;
    private long endTime;
    private boolean nanoTime = false;
    private List<Listener> listeners = new ArrayList<>();

    public TimeTestListener() {
    }

    public TimeTestListener(boolean nanoTime) {
        this.nanoTime = nanoTime;
    }

    public static TimeTestListener getInstance(boolean nanoTime) {
        TimeTestListener timeTestListener = new TimeTestListener(nanoTime);
        timeTestListener.addListener(new TimeTestListener.Listener() {
            @Override
            public void onStart() {
                System.out.println("Starting test...");
            }

            @Override
            public void onEnd() {
                long timeDifference = timeTestListener.getTimeDifference();
                System.out.println("Test finished in " + timeDifference + (nanoTime ? " nano-" : " milli-" + "seconds"));
            }
        });

        return timeTestListener;
    }

    public void start(AlgorithmToTest algorithm) {
        System.out.println("test started for " + algorithm.getClass().getSimpleName());
        startTime = nanoTime ? System.nanoTime() : System.currentTimeMillis();
    }

    public void end() {
        endTime = nanoTime ? System.nanoTime() : System.currentTimeMillis();
    }

    public long getTimeDifference() {
        return endTime - startTime;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void fireStartEvent() {
        for (Listener listener : listeners) {
            listener.onStart();
        }
    }

    public void fireEndEvent() {
        for (Listener listener : listeners) {
            listener.onEnd();
        }
    }

    public static interface Listener {
        void onStart();

        void onEnd();
    }

}
