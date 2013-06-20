package fila;

import excecoes.KeyUpdateException;

public interface MaxPriorityQueue {
	public Object insert(DynamicSetElement x);

	public DynamicSetElement maximum();

	public DynamicSetElement extractMax();

	public void increaseKey(Object element, Comparable newKey)
			throws KeyUpdateException;

	public boolean isEmpty();
}