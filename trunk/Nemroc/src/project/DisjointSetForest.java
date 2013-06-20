package project;

public class DisjointSetForest implements DisjointSetUnion {

	private static class Node {
		public Object theObject;

		public Node p;

		public int rank;

		public Node(Object x) {
			theObject = x;
			p = this;
			rank = 0;
		}

		public String toString() {
			return theObject.toString() + ": p = " + p.theObject.toString()
					+ ", rank = " + rank;
		}
	}

	public Object makeSet(Object x) {
		return new Node(x);
	}

	public void union(Object x, Object y) {
		link((Node) findSet(x), (Node) findSet(y));
	}

	public Object findSet(Object x) {
		Node nodeX = (Node) x;
		if (nodeX != nodeX.p)
			nodeX.p = (Node) findSet(nodeX.p);
		return nodeX.p;
	}

	private void link(Node x, Node y) {
		if (x.rank > y.rank)
			y.p = x;
		else {
			x.p = y;
			if (x.rank == y.rank)
				y.rank++;
		}
	}
}