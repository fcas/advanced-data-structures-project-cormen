package grafo;

import java.util.Iterator;

public class GrafoMatrizAdjacencia implements Grafo {
	protected boolean directed;

	protected int lastAdded;

	protected int e;

	protected Vertice[] vertices;

	protected boolean[][] a;

	public GrafoMatrizAdjacencia(int cardV, boolean directed) {
		this.directed = directed;
		lastAdded = -1;
		vertices = new Vertice[cardV];
		a = new boolean[cardV][cardV];

		for (int i = 0; i < cardV; i++)
			for (int j = 0; j < cardV; j++)
				a[i][j] = false;

		e = 0;
	}

	public Vertice addVertex(String name) {
		lastAdded++;
		vertices[lastAdded] = new Vertice(lastAdded, name);
		return vertices[lastAdded];
	}

	public Vertice addVertex(int index, String name) {
		lastAdded = index;
		vertices[lastAdded] = new Vertice(lastAdded, name);
		return vertices[lastAdded];
	}

	public Vertice addVertex(Vertice v) {
		if (v.getIndex() == Vertice.UNKNOWN_INDEX) {
			lastAdded++;
			v.setIndex(lastAdded);
		} else
			lastAdded = v.getIndex();

		vertices[lastAdded] = v;
		return v;
	}

	public Vertice getVertex(int index) {
		return vertices[index];
	}

	public void addEdge(Vertice u, Vertice v) {
		addEdge(u.getIndex(), v.getIndex());
	}

	public void addEdge(int u, int v) {
		a[u][v] = true;
		if (!directed)
			a[v][u] = true;

		e++;
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
			return lastVisited < vertices.length - 1;
		}

		public Object next() {
			return vertices[++lastVisited];
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
		protected int current;
		int u;

		public EdgeIterator(int u) {
			this.u = u;
			current = -1;
		}

		public boolean hasNext() {
			int v = current + 1;
			while (v < a[u].length && !a[u][v])
				v++;

			return v < a[u].length;
		}

		public Object next() {
			current++;
			while (!a[u][current])
				current++;

			return vertices[current];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	public int getCardV() {
		return vertices.length;
	}

	public int getCardE() {
		return e;
	}

	public boolean isDirected() {
		return directed;
	}

	public boolean edgeExists(Vertice u, Vertice v) {
		return edgeExists(u.getIndex(), v.getIndex());
	}

	public boolean edgeExists(int u, int v) {
		return a[u][v];
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