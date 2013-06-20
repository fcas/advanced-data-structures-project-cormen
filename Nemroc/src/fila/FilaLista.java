package fila;

import grafo.SentinelDLL;

import java.util.Iterator;


public class FilaLista {
	private SentinelDLL list;

	public FilaLista() {
		list = new SentinelDLL();
	}

	public Object enqueue(Object o) {
		return list.insertAtTail(o);
	}

	public Object dequeue() {
		Iterator iter = list.iterator();
		if (iter.hasNext()) {
			Object head = iter.next();
			iter.remove();
			return head;
		} else
			throw new IllegalStateException("Called Dequeue on an empty queue");
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public String toString() {
		return list.toString();
	}
}