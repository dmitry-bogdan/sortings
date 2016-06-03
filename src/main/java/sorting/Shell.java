package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: Shell sort
 * Creation date: 30.05.2016 15:40
 *
 * @author sks
 */
@State(Scope.Benchmark)
public class Shell {

	private int[] array;
	private int[] arraySample;

	@Param({"1000", "10000", "100000", "1000000"})
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
		int inner, outer;
		int temp;
		int h = 1;
		while (h <= array.length / 3) {
			h = h * 3 + 1;
		}
		while (h > 0) {
			for (outer = h; outer < array.length; outer++) {
				temp = array[outer];
				inner = outer;
				while (inner >= (h) && array[inner - h] >= temp) {
					array[inner] = array[inner - h];
					inner -= h;
				}
				array[inner] = temp;
			}
			h = (h - 1) / 3;
		}
		bh.consume(array);
	}
}
