package fila;

import java.util.Iterator;

abstract public class LinkedList {
	protected static class Node {
		public Node next;

		public Node prev;

		public Object info;

		public Node() {
			next = null;
			prev = null;
			info = null;
		}

		public Node(Object o) {
			next = null;
			prev = null;
			info = o;
		}
	}

	public static Object dereference(Object node) {
		return ((Node) node).info;
	}

	abstract public class ListIterator implements Iterator {

		abstract public boolean hasNext();

		abstract public Object next();

		abstract public void remove();
	}

	abstract public Object insert(Object o);

	abstract public Object insertAfter(Object o, Object after);

	abstract public void delete(Object handle);

	abstract public boolean isEmpty();

	abstract public Iterator iterator();

	abstract public void concatenate(LinkedList l);

	public String toString() {
		String result = "";
		Iterator iter = iterator();

		while (iter.hasNext())
			result += iter.next() + "\n";

		return result;
	}

	public void toArray(Object[] array) {
		Iterator iter = iterator();
		int i = 0;

		while (iter.hasNext())
			array[i++] = iter.next();
	}
}