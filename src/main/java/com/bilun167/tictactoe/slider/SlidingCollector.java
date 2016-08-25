package com.bilun167.tictactoe.slider;

import static java.lang.Integer.max;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by taihuynh on 25/8/16.
 */
public class SlidingCollector<T> implements Collector<T, List<List<T>>, List<List<T>>> {

    private final int size;
    private final int step;
    private final int window;
    private final Queue<T> buffer = new ArrayDeque<>();
    private int totalIn = 0;

    public SlidingCollector(int size, int step) {
        this.size = size;
        this.step = step;
        this.window = max(size, step);
    }

    @Override
    public Supplier<List<List<T>>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<List<T>>, T> accumulator() {
        return (lists, t) -> {
            buffer.offer(t);
            ++totalIn;
            if (buffer.size() == window) {
                dumpCurrent(lists);
                shiftBy(step);
            }
        };
    }

    @Override
    public Function<List<List<T>>, List<List<T>>> finisher() {
        return lists -> {
            if (!buffer.isEmpty()) {
                final int totalOut = estimateTotalOut();
                if (totalOut > lists.size()) {
                    dumpCurrent(lists);
                }
            }
            return lists;
        };
    }

    private int estimateTotalOut() {
        return max(0, (totalIn + step - size - 1) / step) + 1;
    }

    private void dumpCurrent(List<List<T>> lists) {
        final List<T> batch = buffer.stream().limit(size).collect(Collectors.toList());
        lists.add(batch);
    }

    private void shiftBy(int by) {
        for (int i = 0; i < by; i++) {
            buffer.remove();
        }
    }

    @Override
    public BinaryOperator<List<List<T>>> combiner() {
        return (l1, l2) -> {
            throw new UnsupportedOperationException("Combining not possible");
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.noneOf(Characteristics.class);
    }

}
