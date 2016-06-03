import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import sorting.*;

import java.util.concurrent.TimeUnit;

/**
 * Description: Main class
 * Creation date: 30.05.2016 14:22
 *
 * @author sks
 */
public class Main {

	public static void main(String... args) {

		System.out.println("Test project started");
		Options opt = new OptionsBuilder()
				.threadGroups(1)
				.include(Bubble.class.getSimpleName())
				.include(Selection.class.getSimpleName())
				.include(Insertion.class.getSimpleName())
				.include(Shell.class.getSimpleName())
				.include(Quick.class.getSimpleName())
				.forks(1)
				.warmupIterations(5)
				.measurementIterations(10)
				.timeUnit(TimeUnit.MICROSECONDS)
				.measurementTime(TimeValue.milliseconds(100))
				.warmupTime(TimeValue.milliseconds(100))
				.mode(Mode.AverageTime)
				.build();
		try {
			new Runner(opt).run();
		} catch (RunnerException e) {
			e.printStackTrace();
		}

		System.out.println("Test project stopped");
	}

	private static void printArray(int[] array) {
		for (int i : array) {
			System.out.printf("%d ", i);
		}
		System.out.println();
	}
}
