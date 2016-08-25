package com.bilun167.tictactoe.slider;

import java.util.List;
import java.util.stream.Collector;

/**
 * Created by taihuynh on 25/8/16.
 */
public class CustomCollectors {
    public static <T> Collector<T, ?, List<List<T>>> sliding(int size) {
        return new SlidingCollector<>(size, 1);
    }

    public static <T> Collector<T, ?, List<List<T>>> sliding(int size, int step) {
        return new SlidingCollector<>(size, step);
    }
}
