package grafo;

import java.util.Iterator;

import excecoes.DeleteSentinelException;
import fila.ListaLigada;

public class SentinelDLL extends ListaLigada {

	protected final Node nil;

	public SentinelDLL() {

		nil = new Node();
		nil.next = nil;
		nil.prev = nil;
	}

	public class SentinelDLLIterator extends ListIterator {
		private Node current;

		public SentinelDLLIterator() {
			current = nil;
		}

		public boolean hasNext() {
			return current.next != nil;
		}

		public Object next() {
			current = current.next;
			return current.info;
		}

		public void remove() {
			if (current == nil)
				throw new IllegalStateException(
						"Called remove before iterator returned an object.");
			else {
				Node newCurrent = current.prev;

				delete(current);

				current = newCurrent;
			}
		}
	}

	public Object insert(Object o) {
		Node x = new Node(o);
		x.next = nil.next;
		nil.next.prev = x;
		nil.next = x;
		x.prev = nil;
		return x;
	}

	public Object insertAfter(Object o, Object after) {
		if (after == null)
			return insert(o);
		else {
			Node x = new Node(o);
			Node predecessor = (Node) after;
			x.next = predecessor.next;
			predecessor.next.prev = x;
			predecessor.next = x;
			x.prev = predecessor;
			return x;
		}
	}

	public Object insertAtTail(Object o) {
		return insertAfter(o, nil.prev);
	}

	public void delete(Object handle) {
		Node x = (Node) handle;

		if (handle == nil)
			throw new DeleteSentinelException();

		x.prev.next = x.next;
		x.next.prev = x.prev;
		x.prev = null;
		x.next = null;
	}

	public boolean isEmpty() {
		return nil.next == nil;
	}

	public Iterator iterator() {
		return new SentinelDLLIterator();
	}

	public void concatenate(ListaLigada l) {
		SentinelDLL other = (SentinelDLL) l;
		nil.prev.next = other.nil.next;
		other.nil.next.prev = nil.prev;
		other.nil.prev.next = nil;
		nil.prev = other.nil.prev;
	}
}