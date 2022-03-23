package io.github.ititus.commons.lexer;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class LexerBenchmark {

    @Benchmark
    public void add(Blackhole bh) {
        int a = 1;
        int b = 2;
        int sum = a + b;

        bh.consume(sum);
    }
}
