package agm;

import java.util.Iterator;


import conjuntos.ConjuntoDisjuntoFloresta;
import conjuntos.IConjuntoDisjunto;
import fila.MaxHeap;
import grafo.Vertice;
import grafo.GrafoListaAdjacenciaPesada;
import grafo.IteradorArestasPesadas;

public class Kruskal implements AGM {

	public GrafoListaAdjacenciaPesada computeMST(GrafoListaAdjacenciaPesada g) {

		GrafoListaAdjacenciaPesada a = (GrafoListaAdjacenciaPesada) g
				.useSameVertices();

		IConjuntoDisjunto forest = new ConjuntoDisjuntoFloresta();
		Object handle[] = new Object[a.getCardV()];
		Iterator vertexIter = g.vertexIterator();

		while (vertexIter.hasNext()) {
			Vertice v = (Vertice) vertexIter.next();
			handle[v.getIndex()] = forest.makeSet(v);
		}

		WeightedEdge[] edge = new WeightedEdge[g.getCardE()];
		int i = 0;
		vertexIter = g.vertexIterator();
		while (vertexIter.hasNext()) {
			Vertice u = (Vertice) vertexIter.next();

			IteradorArestasPesadas edgeIter = g.weightedEdgeIterator(u);
			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();

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

		public Vertice u;

		public Vertice v;

		public double w;

		public WeightedEdge(Vertice a, Vertice b, double weight) {
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