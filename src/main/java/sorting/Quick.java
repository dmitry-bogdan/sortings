package sorting;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Description: Quick sort
 * Creation date: 31.05.2016 12:00
 *
 * @author sks
 */
@State(Scope.Benchmark)
public class Quick {

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
		bh.consume(quickSort(0, array.length - 1));

	}

	private int quickSort(int left, int right) {
		int pivot = 0;
		if ((right - left) > 0) {
			pivot = partition(left, right);
			quickSort(left, pivot - 1);
			quickSort(pivot + 1, right);
		}
		return pivot;
	}

	private int partition(int left, int right) {
		int i = left, j = right;
		int pivot = array[right];
		int temp;
		while (i < j) {
			while (i < j && array[i] <= pivot) {
				i++;
			}
			while (i < j && array[j] >= pivot) {
				j--;
			}
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		if (i != right) {
			array[right] = array[i];
			array[i] = pivot;
		}
		return i;
	}
}
