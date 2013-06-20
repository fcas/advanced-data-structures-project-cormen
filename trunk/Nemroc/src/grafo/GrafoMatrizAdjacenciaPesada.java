package grafo;

import java.util.Iterator;

public class GrafoMatrizAdjacenciaPesada extends GrafoMatrizAdjacencia {

	protected double[][] a;

	protected double absentValue;

	public GrafoMatrizAdjacenciaPesada(int cardV, boolean directed,
			double absent) {
		super(cardV, directed);
		super.a = null;
		absentValue = absent;
		a = new double[cardV][cardV];

		for (int i = 0; i < cardV; i++)
			for (int j = 0; j < cardV; j++)
				a[i][j] = absent;
	}

	public void addEdge(Vertice u, Vertice v) {
		throw new UnsupportedOperationException();
	}

	public void addEdge(int u, int v) {
		throw new UnsupportedOperationException();
	}

	public void addEdge(Vertice u, Vertice v, double weight) {
		int uIndex = u.getIndex();
		int vIndex = v.getIndex();

		a[uIndex][vIndex] = weight;
		if (!directed)
			a[vIndex][uIndex] = weight;
	}

	public void addEdge(int u, int v, double weight) {
		a[u][v] = weight;
		if (!directed)
			a[v][u] = weight;
	}

	public Iterator edgeIterator(Vertice u) {
		return new EdgeIterator(u.getIndex());
	}

	public Iterator edgeIterator(int u) {
		return new EdgeIterator(u);
	}

	public IteradorArestasPesadas weightedEdgeIterator(Vertice u) {
		return weightedEdgeIterator(u.getIndex());
	}

	public IteradorArestasPesadas weightedEdgeIterator(int u) {
		return new EdgeIterator(u);
	}

	public class EdgeIterator extends GrafoMatrizAdjacencia.EdgeIterator
			implements IteradorArestasPesadas {

		public EdgeIterator(int v) {
			super(v);
		}

		public boolean hasNext() {
			int v = current + 1;

			while (v < a[u].length && a[u][v] == absentValue)
				v++;

			return v < a[u].length;
		}

		public Object next() {
			current++;
			while (a[u][current] == absentValue)
				current++;

			return vertices[current];
		}

		public double getWeight() {
			return a[u][current];
		}

		public void setWeight(double weight) {
			a[u][current] = weight;
		}
	}

	public boolean edgeExists(Vertice u, Vertice v) {
		return edgeExists(u.getIndex(), v.getIndex());
	}

	public boolean edgeExists(int u, int v) {
		return a[u][v] != absentValue;
	}

	public double getWeight(Vertice u, Vertice v) {
		return getWeight(u.getIndex(), v.getIndex());
	}

	public double getWeight(int u, int v) {
		return a[u][v];
	}

	public String toString() {
		String result = "";

		Iterator vertexIter = vertexIterator();
		while (vertexIter.hasNext()) {
			Vertice u = (Vertice) vertexIter.next();
			result += u + ":\n";

			IteradorArestasPesadas edgeIter = weightedEdgeIterator(u);
			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();
				double w = edgeIter.getWeight();
				result += "    " + v + ", weight = " + w + "\n";
			}
		}

		return result;
	}
}