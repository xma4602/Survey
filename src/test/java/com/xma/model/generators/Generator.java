package com.xma.model.generators;

import java.util.List;
import java.util.stream.IntStream;

public abstract class Generator<T> {
    public int count = 0;

    public static int LENGTH = 50;

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
