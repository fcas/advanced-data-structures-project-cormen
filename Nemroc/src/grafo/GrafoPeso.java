package grafo;

import java.util.Iterator;

import menorcaminho.SSSP;

public class GrafoPeso extends SSSP {

	public GrafoPeso(GrafoListaAdjacenciaPesada theGraph) {
		super(theGraph);
	}

	public void computeShortestPaths(Vertice s) {
		initializeSingleSource(s);

		int cardV = g.getCardV();

		for (int i = 1; i <= cardV - 1; i++) {
			Iterator vertexIter = g.vertexIterator();

			while (vertexIter.hasNext()) {
				Vertice u = (Vertice) vertexIter.next();
				double du = getShortestPathInfo(u).getEstimate();
				IteradorArestasPesadas edgeIter = g.weightedEdgeIterator(u);

				while (edgeIter.hasNext()) {
					Vertice v = (Vertice) edgeIter.next();
					double w = edgeIter.getWeight();
					getShortestPathInfo(v).relax(u, du, w);
				}
			}
		}

		Iterator vertexIter = g.vertexIterator();

		while (vertexIter.hasNext()) {
			Vertice u = (Vertice) vertexIter.next();
			double du = getShortestPathInfo(u).getEstimate();
			IteradorArestasPesadas edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertice v = (Vertice) edgeIter.next();
				double w = edgeIter.getWeight();
				if (getShortestPathInfo(v).getEstimate() > du + w) {
					noNegWeightCycle = false;
					return;
				}
			}
		}
	}
}