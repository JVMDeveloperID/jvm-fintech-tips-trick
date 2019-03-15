package tech.namas.sharing.numbers;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors; import java.util.stream.IntStream;

@Fork(4)
@State(Scope.Thread)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5)
public class PowerComparison {

    private int x;

    public static void main(String[] args) throws org.openjdk.jmh.runner.RunnerException {
        List<String> params = IntStream.range(1, 100).mapToObj(Integer::toString).collect(Collectors.toList());

        Options opt = new OptionsBuilder()
                .include(PowerComparison.class.getSimpleName())
                .param("x", params.toArray(new String[100]))
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void powMath() {
        Math.pow(3, x);
    }

    @Benchmark
    public void powBinary() {
        powerWithBinary(3, x);
    }

    static double powerWithBinary(double x, int exponent) {
        if (exponent < 0) return 1 / powerWithBinary(x, -exponent);

        double result = 1;

        while(exponent > 0) {
            if ((exponent & 1) == 1) result = result * x;

            x = x * x;
            exponent = exponent >> 1;
        }

        return result;
    }
}
