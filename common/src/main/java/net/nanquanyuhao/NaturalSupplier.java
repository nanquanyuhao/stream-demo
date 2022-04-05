package net.nanquanyuhao;

import java.math.BigInteger;
import java.util.function.Supplier;

/**
 * 自然数生成支持类
 */
public class NaturalSupplier implements Supplier<BigInteger> {

    BigInteger next = BigInteger.ZERO;

    @Override
    public BigInteger get() {
        next = next.add(BigInteger.ONE);
        return next;
    }
}
