package fila;

import excecoes.AtualizacaoPrioridadeException;

public interface FilaPrioridadeMaxima {
	public Object insert(ElementoDinamico x);

	public ElementoDinamico maximum();

	public ElementoDinamico extractMax();

	public void increaseKey(Object element, Comparable newKey)
			throws AtualizacaoPrioridadeException;

	public boolean isEmpty();
}