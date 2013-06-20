package fila;

public class MaxHeap extends Heap {

	public MaxHeap() {
		super();
	}

	MaxHeap(Comparable[] array) {
		super(array);
	}

	public void heapify(int i) {
		int l = left(i);
		int r = right(i);
		int smallest = i;
		if (l < heapSize && array[l].compareTo(array[i]) > 0)
			smallest = l;
		if (r < heapSize && array[r].compareTo(array[smallest]) > 0)
			smallest = r;
		if (smallest != i) {
			exchange(i, smallest);
			heapify(smallest);
		}
	}
}