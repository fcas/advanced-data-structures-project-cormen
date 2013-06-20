package grafo;

import java.util.Iterator;

import menorcaminho.SingleSourceShortestPaths;

public class WeightedAdjacencyGraph extends SingleSourceShortestPaths {

	public WeightedAdjacencyGraph(WeightedAdjacencyListGraph theGraph) {
		super(theGraph);
	}

	public void computeShortestPaths(Vertex s) {
		initializeSingleSource(s);

		int cardV = g.getCardV();

		for (int i = 1; i <= cardV - 1; i++) {
			Iterator vertexIter = g.vertexIterator();

			while (vertexIter.hasNext()) {
				Vertex u = (Vertex) vertexIter.next();
				double du = getShortestPathInfo(u).getEstimate();
				WeightedEdgeIterator edgeIter = g.weightedEdgeIterator(u);

				while (edgeIter.hasNext()) {
					Vertex v = (Vertex) edgeIter.next();
					double w = edgeIter.getWeight();
					getShortestPathInfo(v).relax(u, du, w);
				}
			}
		}

		Iterator vertexIter = g.vertexIterator();

		while (vertexIter.hasNext()) {
			Vertex u = (Vertex) vertexIter.next();
			double du = getShortestPathInfo(u).getEstimate();
			WeightedEdgeIterator edgeIter = g.weightedEdgeIterator(u);

			while (edgeIter.hasNext()) {
				Vertex v = (Vertex) edgeIter.next();
				double w = edgeIter.getWeight();
				if (getShortestPathInfo(v).getEstimate() > du + w) {
					noNegWeightCycle = false;
					return;
				}
			}
		}
	}
}