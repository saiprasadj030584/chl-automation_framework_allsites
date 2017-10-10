import java.util.concurrent.ThreadLocalRandom;

public class Random_a {

	public static void main(String[] args) {
		long max = 999999999;
		long value1 = ThreadLocalRandom.current().nextLong(100000000, max);
		long value2 = ThreadLocalRandom.current().nextLong(100000000, max);
		long value3 = ThreadLocalRandom.current().nextLong(10, 99);
		System.out.println(value1);
		System.out.println(value2);
		System.out.println(value3);
	}
}
