package fila;

import excecoes.AtualizacaoPrioridadeException;

public interface FilaPrioridadeMinima {
	public Object insert(ElementoDinamico x);

	public ElementoDinamico minimum();

	public ElementoDinamico extractMin();

	public void decreaseKey(Object element, Comparable newKey)
			throws AtualizacaoPrioridadeException;

	public boolean isEmpty();
}