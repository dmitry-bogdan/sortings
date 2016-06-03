package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: Insertion sort
 * Creation date: 30.05.2016 15:05
 *
 * @author sks
 */
@State(Scope.Benchmark)
public class Insertion {

	private int[] array;
	private int[] arraySample;

	@Param({"1000", "10000"})
	private int length;

	@Setup(Level.Iteration)
	public void init() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		arraySample = random.ints(length).toArray();
	}

	@Setup(Level.Invocation)
	public void cloneArray() {
		array = arraySample.clone();
	}

	@Benchmark
	public void sort(Blackhole bh) {
		int in, out, temp;
		for (out = 1; out < array.length; out++) {
			temp = array[out];
			in = out;
			while (in > 0 && array[in - 1] >= temp) {
				array[in] = array[in - 1];
				--in;
			}
			array[in] = temp;
		}
		bh.consume(array);
	}
}
