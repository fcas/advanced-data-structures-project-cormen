package fila;

import excecoes.KeyUpdateException;

public interface MinPriorityQueue {
	public Object insert(DynamicSetElement x);

	public DynamicSetElement minimum();

	public DynamicSetElement extractMin();

	public void decreaseKey(Object element, Comparable newKey)
			throws KeyUpdateException;

	public boolean isEmpty();
}