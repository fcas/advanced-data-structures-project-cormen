package project;

public class MinMax {
	public static Comparable minimum(Comparable[] array) {
		Comparable min = array[0];

		for (int i = 1; i < array.length; i++)
			if (min.compareTo(array[i]) < 0)
				min = array[i];

		return min;
	}

	public static Comparable maximum(Comparable[] array) {
		Comparable max = array[0];

		for (int i = 1; i < array.length; i++)
			if (max.compareTo(array[i]) > 0)
				max = array[i];
		return max;
	}
}