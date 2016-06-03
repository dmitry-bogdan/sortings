package sorting;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: Selection sort
 * Creation date: 30.05.2016 14:49
 *
 * @author sks
 */
@State(Scope.Benchmark)
public class Selection {

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
	public void sort() {
		int i, j, min, temp;
		int arraySize = array.length;
		for (i = 0; i <= arraySize - 1; i++) {
			min = i;
			for (j = i; j <= arraySize - 1; j++) {
				if (array[min] > array[j]) {
					min = j;
				}
			}
			temp = array[i];
			array[i] = array[min];
			array[min] = temp;

		}
	}
}
