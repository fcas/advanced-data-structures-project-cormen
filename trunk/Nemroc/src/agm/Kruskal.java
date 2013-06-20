package agm;

import java.util.Iterator;


import conjuntos.DisjointSetForest;
import conjuntos.DisjointSetUnion;
import fila.MaxHeap;
import grafo.Vertex;
import grafo.WeightedAdjacencyListGraph;
import grafo.WeightedEdgeIterator;

public class Kruskal implements MST {

	public WeightedAdjacencyListGraph computeMST(WeightedAdjacencyListGraph g) {

		WeightedAdjacencyListGraph a = (WeightedAdjacencyListGraph) g
				.useSameVertices();

		DisjointSetUnion forest = new DisjointSetForest();
		Object handle[] = new Object[a.getCardV()];
		Iterator vertexIter = g.vertexIterator();

		while (vertexIter.hasNext()) {
			Vertex v = (Vertex) vertexIter.next();
			handle[v.getIndex()] = forest.makeSet(v);
		}

		WeightedEdge[] edge = new WeightedEdge[g.getCardE()];
		int i = 0;
		vertexIter = g.vertexIterator();
		while (vertexIter.hasNext()) {
			Vertex u = (Vertex) vertexIter.next();

			WeightedEdgeIterator edgeIter = g.weightedEdgeIterator(u);
			while (edgeIter.hasNext()) {
				Vertex v = (Vertex) edgeIter.next();

				if (u.getIndex() < v.getIndex()) {
					double w = edgeIter.getWeight();
					edge[i++] = new WeightedEdge(u, v, w);
				}
			}
		}

		MaxHeap heap = new MaxHeap();
		(heap.makeSorter()).sort(edge);

		for (i = 0; i < edge.length; i++) {
			Object uHandle = handle[edge[i].u.getIndex()];
			Object vHandle = handle[edge[i].v.getIndex()];
			if (forest.findSet(uHandle) != forest.findSet(vHandle)) {
				a.addEdge(edge[i].u, edge[i].v, edge[i].w);
				forest.union(uHandle, vHandle);
			}
		}

		return a;
	}

	private static class WeightedEdge implements Comparable {

		public Vertex u;

		public Vertex v;

		public double w;

		public WeightedEdge(Vertex a, Vertex b, double weight) {
			u = a;
			v = b;
			w = weight;
		}

		public int compareTo(Object o) {
			WeightedEdge e = (WeightedEdge) o;
			if (w < e.w)
				return -1;
			else if (w == e.w)
				return 0;
			else
				return 1;
		}

		public String toString() {
			return "(" + u.getName() + "," + v.getName() + "," + w + ")";
		}
	}

}