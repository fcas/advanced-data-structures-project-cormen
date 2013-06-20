package agm;

import java.util.Iterator;


import fila.ElementoDinamico;
import fila.HeapMinimoFilaPrioridade;
import fila.FilaPrioridadeMinima;
import fila.ElementoDinamico.Helper;
import grafo.Vertice;
import grafo.GrafoListaAdjacenciaPesada;
import grafo.IteradorArestasPesadas;

public class Prim implements AGM {
	public GrafoListaAdjacenciaPesada computeMST(GrafoListaAdjacenciaPesada g) {
		FilaPrioridadeMinima q = new HeapMinimoFilaPrioridade();

		int cardV = g.getCardV();
		PrimInfo[] vertex = new PrimInfo[cardV];
		for (int i = 0; i < cardV; i++)
			vertex[i] = new PrimInfo(g.getVertex(i), q);

		q.decreaseKey(vertex[0].handle, new Double(0));

		while (!q.isEmpty()) {
			PrimInfo uInfo = (PrimInfo) q.extractMin();
			Vertice u = uInfo.theVertex;

			IteradorArestasPesadas edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();
				PrimInfo vInfo = vertex[v.getIndex()];
				double weight = edgeIter.getWeight();
				if (vInfo.handle != null && weight < vInfo.key.doubleValue()) {
					vInfo.pi = u;
					q.decreaseKey(vInfo.handle, new Double(weight));
				}
			}
		}

		GrafoListaAdjacenciaPesada mst = (GrafoListaAdjacenciaPesada) g
				.useSameVertices();

		for (int i = 0; i < cardV; i++) {
			PrimInfo vInfo = vertex[i];
			if (vInfo.pi != null)
				mst.addEdge(vInfo.pi, vInfo.theVertex, vInfo.key.doubleValue());
		}

		return mst;
	}

	private static class PrimInfo implements ElementoDinamico {
		public Vertice theVertex;
		public Double key;

		public Vertice pi;
		public Object handle;

		public PrimInfo(Vertice v, FilaPrioridadeMinima q) {
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
			return ElementoDinamico.Helper.compareTo(this, e);
		}
	}
}