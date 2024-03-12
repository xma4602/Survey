package com.xma.surveys.generators;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public abstract class Generator<T> {
    public static final int LENGTH = 50;

    protected int count = 0;
    protected final Random random = new Random(100);

    protected int generateLength() {
        return random.nextInt(LENGTH) + 1;
    }

    public T generate() {
        return generate(false);
    }

    public abstract T generate(boolean fill);

    public List<T> generatelist(int len) {
        return generateList(len, false);
    }

    public List<T> generateList(int len, boolean fill) {
        return IntStream.range(0, len)
                .mapToObj(i -> generate(fill))
                .toList();
    }
}
