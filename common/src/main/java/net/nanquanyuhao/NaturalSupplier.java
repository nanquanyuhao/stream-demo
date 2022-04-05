package net.nanquanyuhao;

import java.util.function.Supplier;

/**
 * 自然数生成支持类
 */
public class NaturalSupplier implements Supplier<Long> {

    long x = 0;

    @Override
    public Long get() {
        x++;
        return x;
    }
}
