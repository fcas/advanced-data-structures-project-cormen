package conjuntos;

public class ConjuntoDisjuntoLista implements IConjuntoDisjunto {

	private static class Node {
		public Object theObject;

		public Node next;

		public DisjointSet representative;

		public Node(Object x) {
			theObject = x;
			next = null;
			representative = null;
		}

		public String toString() {
			return theObject.toString();
		}
	}

	private static class DisjointSet {
		public Node head;

		public Node tail;

		public int size;

		public DisjointSet(Node x) {
			head = x;
			tail = x;
			size = 1;
		}

		public String toString() {
			String result = "";

			for (Node x = head; x != null; x = x.next)
				result += x.toString() + "\n";

			return result;
		}
	}

	public Object makeSet(Object x) {
		Node node = new Node(x);
		node.representative = new DisjointSet(node);
		return node;
	}

	public void union(Object x, Object y) {
		DisjointSet xSet = (DisjointSet) findSet(x);
		DisjointSet ySet = (DisjointSet) findSet(y);
		if (xSet.size >= ySet.size)
			append(xSet, ySet);
		else
			append(ySet, xSet);
	}

	private void append(DisjointSet first, DisjointSet second) {
		if (first.size == 0 || second.size == 0)
			throw new DisjointSetUnionException();

		for (Node x = second.head; x != null; x = x.next)
			x.representative = first;

		first.tail.next = second.head;
		first.tail = second.tail;
		first.size += second.size;

		second.head = null;
		second.tail = null;
		second.size = 0;
	}

	public Object findSet(Object x) {
		return ((Node) x).representative;
	}

	public String printSet(Object x) {
		return findSet(x).toString();
	}

	public static class DisjointSetUnionException extends RuntimeException {
	}
}