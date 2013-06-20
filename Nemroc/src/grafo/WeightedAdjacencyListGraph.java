package grafo;

import java.util.Iterator;

public class WeightedAdjacencyListGraph extends AdjacencyListGraph {

	public WeightedAdjacencyListGraph(int cardV, boolean directed) {
		super(cardV, directed);
	}
	
	public void addEdge(Vertex u, Vertex v) {
		throw new UnsupportedOperationException();
	}
	
	public void addEdge(int u, int v) {
		throw new UnsupportedOperationException();
	}
	public void addEdge(Vertex u, Vertex v, double weight) {
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

		public WeightedEdge(Vertex v, Edge successor, double wgt) {
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

	public Iterator edgeIterator(Vertex u) {
		return new EdgeIterator(u.getIndex());
	}

	public Iterator edgeIterator(int u) {
		return new EdgeIterator(u);
	}

	public WeightedEdgeIterator weightedEdgeIterator(Vertex u) {
		return weightedEdgeIterator(u.getIndex());
	}

	public WeightedEdgeIterator weightedEdgeIterator(int u) {
		return new EdgeIterator(u);
	}

	public class EdgeIterator extends AdjacencyListGraph.EdgeIterator implements
			WeightedEdgeIterator {

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

	protected AdjacencyListGraph makeEmptyGraph(int cardV, boolean directed) {
		return new WeightedAdjacencyListGraph(cardV, directed);
	}

	public String toString() {
		String result = "";

		Iterator vertexIter = vertexIterator();
		while (vertexIter.hasNext()) {
			if (directed) {
				Vertex u = (Vertex) vertexIter.next();
				result += u + ":\n";

				WeightedEdgeIterator edgeIter = weightedEdgeIterator(u);
				while (edgeIter.hasNext()) {
					Vertex v = (Vertex) edgeIter.next();
					double w = edgeIter.getWeight();
					result += "    " + v + ", peso = " + w + "\n";
				}
			} else {
				Vertex u = (Vertex) vertexIter.next();
				result += u + ":\n";

				WeightedEdgeIterator edgeIter = weightedEdgeIterator(u);
				while (edgeIter.hasNext()) {
					Vertex v = (Vertex) edgeIter.next();
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