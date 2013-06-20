package grafo;

public class Particionador {
	public int partition(Comparable[] array, int p, int r) {
		Comparable x = array[r]; // x is the pivot
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (array[j].compareTo(x) <= 0) {
				i++;
				exchange(array, i, j);
			}
		}
		exchange(array, i + 1, r);
		return i + 1;
	}

	public void exchange(Object[] array, int i, int j) {
		Object t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
}