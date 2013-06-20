package agm;

import java.util.Iterator;


import fila.DynamicSetElement;
import fila.MinHeapPriorityQueue;
import fila.MinPriorityQueue;
import fila.DynamicSetElement.Helper;
import grafo.Vertex;
import grafo.WeightedAdjacencyListGraph;
import grafo.WeightedEdgeIterator;

public class Prim implements MST {
	public WeightedAdjacencyListGraph computeMST(WeightedAdjacencyListGraph g) {
		MinPriorityQueue q = new MinHeapPriorityQueue();

		int cardV = g.getCardV();
		PrimInfo[] vertex = new PrimInfo[cardV];
		for (int i = 0; i < cardV; i++)
			vertex[i] = new PrimInfo(g.getVertex(i), q);

		q.decreaseKey(vertex[0].handle, new Double(0));

		while (!q.isEmpty()) {
			PrimInfo uInfo = (PrimInfo) q.extractMin();
			Vertex u = uInfo.theVertex;

			WeightedEdgeIterator edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertex v = (Vertex) edgeIter.next();
				PrimInfo vInfo = vertex[v.getIndex()];
				double weight = edgeIter.getWeight();
				if (vInfo.handle != null && weight < vInfo.key.doubleValue()) {
					vInfo.pi = u;
					q.decreaseKey(vInfo.handle, new Double(weight));
				}
			}
		}

		WeightedAdjacencyListGraph mst = (WeightedAdjacencyListGraph) g
				.useSameVertices();

		for (int i = 0; i < cardV; i++) {
			PrimInfo vInfo = vertex[i];
			if (vInfo.pi != null)
				mst.addEdge(vInfo.pi, vInfo.theVertex, vInfo.key.doubleValue());
		}

		return mst;
	}

	private static class PrimInfo implements DynamicSetElement {
		public Vertex theVertex;
		public Double key;

		public Vertex pi;
		public Object handle;

		public PrimInfo(Vertex v, MinPriorityQueue q) {
			theVertex = v;
			key = new Double(Double.POSITIVE_INFINITY);
			pi = null;
			handle = q.insert(this);
		}

		public void setKey(Comparable key) {
			this.key = (Double) key;
		}

		public Comparable getKey() {
			return key;
		}

		public int compareTo(Object e) {
			return DynamicSetElement.Helper.compareTo(this, e);
		}
	}
}