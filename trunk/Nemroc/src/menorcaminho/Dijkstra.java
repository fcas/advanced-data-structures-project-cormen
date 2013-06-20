package menorcaminho;

import java.util.Iterator;


import fila.ElementoDinamico;
import fila.HeapMinimoFilaPrioridade;
import fila.FilaPrioridadeMinima;
import fila.ElementoDinamico.Helper;
import grafo.Vertice;
import grafo.GrafoListaAdjacenciaPesada;
import grafo.IteradorArestasPesadas;

public class Dijkstra extends SSSP {

	public Dijkstra(GrafoListaAdjacenciaPesada theGraph) {
		super(theGraph);
	}

	public void computeShortestPaths(Vertice s) {
		initializeSingleSource(s);

		FilaPrioridadeMinima q = new HeapMinimoFilaPrioridade();

		int cardV = g.getCardV();
		for (int i = 0; i < cardV; i++) {
			DijkstraInfo info = (DijkstraInfo) getShortestPathInfo(i);
			info.theVertex = g.getVertex(i);
			info.handle = q.insert(info);
		}

		q.decreaseKey(((DijkstraInfo) getShortestPathInfo(s)).handle,
				new Double(0));

		while (!q.isEmpty()) {
			DijkstraInfo uInfo = (DijkstraInfo) q.extractMin();
			uInfo.handle = null;
			Vertice u = uInfo.theVertex;
			double du = getShortestPathInfo(u).getEstimate();

			IteradorArestasPesadas edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();
				DijkstraInfo vInfo = (DijkstraInfo) getShortestPathInfo(v);
				double weight = edgeIter.getWeight();
				if (vInfo.relax(u, du, weight)) {

					q.decreaseKey(vInfo.handle, new Double(vInfo.getEstimate()));
				}
			}
		}
	}

	protected MenorCaminhoInfo newShortestPathInfo() {
		return new DijkstraInfo();
	}

	private static class DijkstraInfo extends MenorCaminhoInfo implements
			ElementoDinamico {

		public Vertice theVertex;

		public Object handle;

		public DijkstraInfo() {
			super();
		}

		public void setKey(Comparable key) {
			setEstimate(((Double) key).doubleValue());
		}

		public Comparable getKey() {
			return new Double(getEstimate());
		}

		public int compareTo(Object e) {
			return ElementoDinamico.Helper.compareTo(this, e);
		}
	}
}