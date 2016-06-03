package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: Bubble sort
 * Creation date: 30.05.2016 14:22
 *
 * @author sks
 */
@State(Scope.Benchmark)
public class Bubble {

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
		int out, in, temp;
		for (out = 0; out < array.length - 1; out++) {
			for (in = 0; in < array.length - 1; in++) {
				if (array[in] > array[in + 1]) {
					temp = array[in];
					array[in] = array[in + 1];
					array[in + 1] = temp;
				}
			}
		}
		bh.consume(array);
	}

}
