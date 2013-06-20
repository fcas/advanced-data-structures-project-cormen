package grafo;

import java.util.Iterator;

public class GrafoListaAdjacenciaPesada extends GrafoListaAdjacencia {

	public GrafoListaAdjacenciaPesada(int cardV, boolean directed) {
		super(cardV, directed);
	}
	
	public void addEdge(Vertice u, Vertice v) {
		throw new UnsupportedOperationException();
	}
	
	public void addEdge(int u, int v) {
		throw new UnsupportedOperationException();
	}
	public void addEdge(Vertice u, Vertice v, double weight) {
		int uIndex = u.getIndex();
		Edge x = new WeightedEdge(v, adj[uIndex].head, weight);
		adj[uIndex].head = x;

		if (!directed) {
			int vIndex = v.getIndex();
			x = new WeightedEdge(u, adj[vIndex].head, weight);
			adj[vIndex].head = x;
		}

		e++;
	}

	public void addEdge(int u, int v, double weight) {
		Edge x = new WeightedEdge(adj[v].thisVertex, adj[u].head, weight);
		adj[u].head = x;

		if (!directed) {
			x = new WeightedEdge(adj[u].thisVertex, adj[v].head, weight);
			adj[v].head = x;
		}

		e++;
	}

	protected static class WeightedEdge extends Edge {
		
		private double weight;

		public WeightedEdge(Vertice v, Edge successor, double wgt) {
			super(v, successor);
			weight = wgt;
		}

		public void setWeight(double wgt) {
			weight = wgt;
		}

		public double getWeight() {
			return weight;
		}
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

	public class EdgeIterator extends GrafoListaAdjacencia.EdgeIterator implements
			IteradorArestasPesadas {

		public EdgeIterator(int v) {
			super(v);
		}

		public double getWeight() {
			return ((WeightedEdge) current).getWeight();
		}

		public void setWeight(double weight) {
			((WeightedEdge) current).setWeight(weight);
		}
	}

	protected GrafoListaAdjacencia makeEmptyGraph(int cardV, boolean directed) {
		return new GrafoListaAdjacenciaPesada(cardV, directed);
	}

	public String toString() {
		String result = "";

		Iterator vertexIter = vertexIterator();
		while (vertexIter.hasNext()) {
			if (directed) {
				Vertice u = (Vertice) vertexIter.next();
				result += u + ":\n";

				IteradorArestasPesadas edgeIter = weightedEdgeIterator(u);
				while (edgeIter.hasNext()) {
					Vertice v = (Vertice) edgeIter.next();
					double w = edgeIter.getWeight();
					result += "    " + v + ", peso = " + w + "\n";
				}
			} else {
				Vertice u = (Vertice) vertexIter.next();
				result += u + ":\n";

				IteradorArestasPesadas edgeIter = weightedEdgeIterator(u);
				while (edgeIter.hasNext()) {
					Vertice v = (Vertice) edgeIter.next();
					if (u.getIndex() < v.getIndex()) {
						double w = edgeIter.getWeight();
						result += "    " + v + ", peso = " + w + "\n";
					}
				}
			}

		}

		return result;
	}
}