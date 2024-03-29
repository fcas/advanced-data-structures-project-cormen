package grafo;

import java.util.Iterator;

public class GrafoListaAdjacencia implements Grafo {
	protected boolean directed;

	protected int lastAdded;

	protected int e;

	public AdjListInfo[] adj;

	public GrafoListaAdjacencia(int cardV, boolean directed) {
		this.directed = directed;
		lastAdded = -1;
		adj = new AdjListInfo[cardV];
		e = 0;
	}

	public Vertice addVertex(String name) {
		lastAdded++; // the index for this vertex
		adj[lastAdded] = new AdjListInfo(new Vertice(lastAdded, name));
		return adj[lastAdded].thisVertex;
	}

	public Vertice addVertex(int index, String name) {
		lastAdded = index;
		adj[lastAdded] = new AdjListInfo(new Vertice(lastAdded, name));
		return adj[lastAdded].thisVertex;
	}

	public Vertice addVertex(Vertice v) {
		if (v.getIndex() == Vertice.UNKNOWN_INDEX) {
			lastAdded++;
			v.setIndex(lastAdded);
		} else
			lastAdded = v.getIndex();

		adj[lastAdded] = new AdjListInfo(v);
		return v;
	}

	public Vertice getVertex(int index) {
		return adj[index].thisVertex;
	}

	public void addEdge(Vertice u, Vertice v) {

		int uIndex = u.getIndex();
		Edge x = new Edge(v, adj[uIndex].head);
		adj[uIndex].head = x;

		if (!directed) {
			int vIndex = v.getIndex();
			x = new Edge(u, adj[vIndex].head);
			adj[vIndex].head = x;
		}

		e++;
	}

	public void addEdge(int u, int v) {

		Edge x = new Edge(adj[v].thisVertex, adj[u].head);
		adj[u].head = x;

		if (!directed) {
			x = new Edge(adj[u].thisVertex, adj[v].head);
			adj[v].head = x;
		}

		e++;
	}

	protected static class AdjListInfo {

		public Vertice thisVertex;

		public Edge head;

		public AdjListInfo(Vertice v) {
			thisVertex = v;
			head = null;
		}
	}

	protected static class Edge {

		public Vertice vertex;

		public Edge next;

		public Edge(Vertice v, Edge successor) {
			vertex = v;
			next = successor;
		}
	}

	public Iterator vertexIterator() {
		return new VertexIterator();
	}

	public class VertexIterator implements Iterator {

		protected int lastVisited;

		public VertexIterator() {
			lastVisited = -1;
		}

		public boolean hasNext() {
			return lastVisited < adj.length - 1;
		}

		public Object next() {
			return adj[++lastVisited].thisVertex;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public Iterator edgeIterator(Vertice u) {
		return new EdgeIterator(u.getIndex());
	}

	public Iterator edgeIterator(int u) {
		return new EdgeIterator(u);
	}

	public class EdgeIterator implements Iterator {

		protected Edge current;

		protected int index;

		public EdgeIterator(int v) {
			index = v;
			current = null;
		}

		public boolean hasNext() {
			if (current == null)
				return adj[index].head != null;
			else
				return current.next != null;
		}

		public Object next() {
			if (current == null)
				current = adj[index].head;
			else
				current = current.next;

			return current.vertex;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public int getCardV() {
		return adj.length;
	}

	public int getCardE() {
		return e;
	}

	public boolean isDirected() {
		return directed;
	}

	public GrafoListaAdjacencia useSameVertices() {
		GrafoListaAdjacencia newGraph = makeEmptyGraph(adj.length, directed);
		for (int i = 0; i < adj.length; i++)
			newGraph.addVertex(adj[i].thisVertex);

		return newGraph;
	}

	protected GrafoListaAdjacencia makeEmptyGraph(int cardV, boolean directed) {
		return new GrafoListaAdjacencia(cardV, directed);
	}

	public String toString() {
		String result = "";

		Iterator vertexIter = vertexIterator();
		while (vertexIter.hasNext()) {
			Vertice u = (Vertice) vertexIter.next();
			result += u + ":\n";

			Iterator edgeIter = edgeIterator(u);
			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();
				result += "    " + v + "\n";
			}
		}

		return result;
	}
}