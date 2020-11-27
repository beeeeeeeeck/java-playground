package org.bl.sorting;

import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class IntArray {
    public static Integer[] generate(int range, int size) {
        Random random = new Random();
        IntSupplier s = () -> random.nextInt(range);
        return IntStream.generate(s).limit(size).parallel().boxed().toArray(Integer[]::new);
    }

    public static Integer[] generate(int size) {
        return generate(size, size);
    }
}
