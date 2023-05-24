package org.example.utils;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong engineIdGenerator = new AtomicLong(1);
    private static final AtomicLong bodyIdGenerator = new AtomicLong(1);
    private static final AtomicLong accessoriesIdGenerator = new AtomicLong(1);
    private static final AtomicLong carsIdGenerator = new AtomicLong(1);


    public static long getNextEngineId() {
        return engineIdGenerator.getAndIncrement();
    }

    public static long getNextBodyId() {
        return bodyIdGenerator.getAndIncrement();
    }

    public static long getNextAccessoriesId() {
        return accessoriesIdGenerator.getAndIncrement();
    }
    public static long getNextCarId() {
        return carsIdGenerator.getAndIncrement();
    }
}
